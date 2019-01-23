## map个数 源码分析
[MapReduce Input Split（输入分/切片）详解](https://blog.csdn.net/dr_guo/article/details/51150278)

[决定Mapper](http://ronxin999.blog.163.com/blog/static/42217920201279112163/)

[mapred-default.xml](http://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/mapred-default.xml)

[map和reduce的个数](https://www.jianshu.com/p/32be1e1af578)

[深度分析如何在Hadoop中控制Map的数量](https://blog.csdn.net/StrongerBit/article/details/7440111)

![Map-Reduce](http://images.cnitblog.com/blog/306623/201306/23175247-1cff38de2f154503bccd89a5d057f696.x-png)
![FileInputFormat](https://img-blog.csdn.net/20160414132403422?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

minSize=max{minSplitSize, mapreduce.input.fileinputformat.split.minsize(default:1L)} 

maxSize=mapreduce.input.fileinputformat.split.maxsize(default:Long.MAX_VALUE)

splitSize=max{minSize, min{maxSize, dfs.block.size(default:128M, 64M)}}

- 最后在这里决定要分块的大小:
    - computeSplitSize方法就是取maxSize和blockSize中小的一个，然后再和minSize比，取大的一个。
    - maxSize如果我们不设置，那就是long的最大值，那肯定是比blockSize大，那splitSize的值就是blockSize的值，那就是一个块一个mapper了。
    - 如果我们设置了mapreduce.input.fileinputformat.split.maxsize的值，比blockSize要小，那一个文件块就要发分为多个mapper了。
    - minSize 如果我们没有设置的话，就是1，那一个块就要划分为2个splits也就是两个maper了。

- 新的api并没有计算总的文件大小。也没有用到job里的设置的maper的个数，因为根本没有传进来。


getSplits()负责将文件切分成多个分片(InputSplit)，但InputSplit并没有实际切分文件，而只是说明了如何切分数据，也就是说，InputSplit只是逻辑上的切分。createRecordReader()则创建了RecordReader，用来从InputSplit读取记录。
每个InputSplit对应一个map任务。作为map的输入，在逻辑上提供了这个map任务所要处理的key-value对。

分片的大小并不是随意分配的。 
拥有许多分片，意味着处理每个分片所需要的时间少于处理整个谁数据所花的时间。因此，如果我们并行处理每个分片，且每个分片数据比较小，那么整个处理过程将获得更好的负载平衡。

如果分片切分得太小，那么管理分片的总时间和构建map任务的总时间将决定作业的整个执行时间。对于大多数作业来说，一个合理的分片大小趋向于HDFS的一个块的大小，默认为64MB，不过可以针对集群调整这个默认值，或对新建的每个文件具体指定。

3. 如何调整Map的数量

　　有了上面的分析，下面调整Map的数量就很容易了。

　　3.1 减小Map-Reduce job 启动时创建的Mapper数量

　　当处理大批量的大数据时，一种常见的情况是job启动的mapper数量太多而超出了系统限制，导致Hadoop抛出异常终止执行。解决这种异常的思路是减少mapper的数量。具体如下：

　　3.1.1 输入文件size巨大，但不是小文件

　　这种情况可以通过增大每个mapper的input size，即增大minSize或者增大blockSize来减少所需的mapper的数量。增大blockSize通常不可行，因为当HDFS被hadoop namenode -format之后，blockSize就已经确定了(由格式化时dfs.block.size决定)，如果要更改blockSize，需要重新格式化HDFS，这样当然会丢失已有的数据。所以通常情况下只能通过增大minSize，即增大mapred.min.split.size的值。

　　3.1.2 输入文件数量巨大，且都是小文件

　　所谓小文件，就是单个文件的size小于blockSize。这种情况通过增大mapred.min.split.size不可行，需要使用FileInputFormat衍生的CombineFileInputFormat将多个input path合并成一个InputSplit送给mapper处理，从而减少mapper的数量。具体细节稍后会更新并展开。

　　3.2 增加Map-Reduce job 启动时创建的Mapper数量

　　增加mapper的数量，可以通过减小每个mapper的输入做到，即减小blockSize或者减小mapred.min.split.size的值。


hadooop提供了一个设置map个数的参数mapred.map.tasks，我们可以通过这个参数来控制map的个数。但是通过这种方式设置map的个数，并不是每次都有效的。原因是mapred.map.tasks只是一个hadoop的参考数值，仅仅是一个提示，最终map的个数，还取决于其他的因素。
        
        block_size : hdfs的文件块大小，默认为128M，可以通过参数dfs.block.size设置
        total_size : 输入文件整体的大小
        input_file_num : 输入文件的个数
             
（1）默认map个数
     如果不进行任何设置，默认的map个数是和blcok_size相关的。
     default_num = total_size / block_size;

（2）期望大小
     可以通过参数mapred.map.tasks来设置程序员期望的map个数，但是这个个数只有在大于default_num的时候，才会生效。
     goal_num = mapred.map.tasks;

（3）设置处理的文件大小
     可以通过mapred.min.split.size 设置每个task处理的文件大小，但是这个大小只有在大于block_size的时候才会生效。
     split_size = max(mapred.min.split.size, block_size);
     split_num = total_size / split_size;

（4）计算的map个数
compute_map_num = min(split_num,  max(default_num, goal_num))

     除了这些配置以外，mapreduce还要遵循一些原则。 mapreduce的每一个map处理的数据是不能跨越文件的，也就是说min_map_num >= input_file_num。 所以，最终的map个数应该为：
     final_map_num = max(compute_map_num, input_file_num)

经过以上的分析，在设置map个数的时候，可以简单的总结为以下几点：
（1）如果想增加map个数，则设置mapred.map.tasks 为一个较大的值。
（2）如果想减小map个数，则设置mapred.min.split.size 为一个较大的值。
（3）如果输入中有很多小文件，依然想减少map个数，则需要将小文件merger为大文件，然后使用准则2。

### 数据存在哪里
    hadoop集群遵循的是主/从的架构，namenode很多时候都不作为文件的读写操作，只负责任务的调度和掌握数据块在哪些datanode的分布，保存的是一些数据结构，是namespace或者类似索引之类的东西，真正的数据存储和对数据的读写是发生在datanode里的。找到${HADOOP_HOME}/ect/hadoop/hdfs-site.xml文件，里面有你自己定义的dfs.datanode.data.dir一项就是你数据存放的位置。

### 数据块设置偏大
    hadoop和hdfs本身就是面向海量文件的，在数据量一定的条件下，数据块适当大一点，数据块的个数就少，于是：1）在进行磁盘搜索的时候，磁头寻址时间相对减少 2）这个原因更重要，namenode会将数据块的类似“inode”的信息放进内存，方便后面找到数据块，而每个这样的数据结构是150字节，因此，数据块个数少的话存在内存中的“inode”减少，处理的压力也减少。但是数据块大小也不是越大越好，要兼顾到每个块的传输效率。
             这就是为什么hadoop适合存储个数相对少但单个文件数据量大，而不适合存储数量多但单个文件数据量小的文件，尽管可能两者在总量上一样大小。在这点上，淘宝针对自身的业务特点，为存储巨量的小图片开发的TFS（taobao file system）就很适合数量多而单个数据量小的类型文件。另外也有很多人提出合并的方法，也即在存储文件之前对文件大小做判断，如果size符合预设的阈值则直接存储，否则先对这些相对小的文件做合并形成大文件再存储。
## Mapreduce作业的处理流程
按照时间顺序包括：
1. [input阶段]获取输入数据进行分片作为map的输入
2. [map阶段]过程对某种输入格式的一条记录解析成一条或多条记录 
3. [shffle阶段]对中间数据的控制，作为reduce的输入 
4. [reduce阶段]对相同key的数据进行合并 
5. [output阶段]按照格式输出到指定目录

[hive合并小文件](https://blog.csdn.net/haohaizijhz/article/details/70338449)



    
