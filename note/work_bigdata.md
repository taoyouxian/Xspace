- RAM的溢出因子是？

溢出因子（Spill factor）是临时文件中储存文件的大小，也就是Hadoop-temp目录。

- hdfs-site.xml的3个主要属性

  ```
  dfs.namenode.name.dir决定的是元数据存储的路径以及DFS的存储方式（磁盘或是远端）
  dfs.datanode.data.dir决定的是数据存储的路径
  fs.checkpoint.dir用于第二Namenode
  ```

- Fsck的全名？

  全名是：File System Check

- 

- 正常工作的hadoop集群中hadoop都分别需要启动那些进程，他们分别是什么，作用是什么？

  ```
  hadoop1.X
      namenode:管理集群，并记录datanode文件的信息.
      Secnodnamenode:可以做冷备，对一定范围的数据做快照备份。
      Datenode:存储数据
      Jobtracker:管理任务，并将任务分配给tasktracker。
      Tasktracker:任务执行方.
  
  Hadoop2.x
      NameNode:
      ResourceManager:资源调度器，它按照一定的约束条件（比如队列容量限制等）将集群中的资源分配给各个应用程序
      DataNode:
      NodeManager:是YARN中每个节点上的代理，它管理Hadoop集群中单个计算节点，包括与ResourceManger保持通信，监督Container的生命周期管理，监控每个Container的资源使用（内存、CPU等）情况，追踪节点健康状况，管理日志和不同应用程序用到的附属服务
      SecondaryNameNode
  ```

  

## presto

```
coordinator=true
node-scheduler.include-coordinator=false
http-server.http.port=8080
query.max-memory=96GB
query.max-memory-per-node=12GB
discovery-server.enabled=true
discovery.uri=http://dbiir01:8080
task.max-worker-threads=24
#task.min-drivers=1
#task.concurrency=4
```

[Hbase Connector](https://www.analysys.cn/article/detail/20019023) 



[MPPDB、Hadoop与传统数据库技术对比与适用场景](https://blog.csdn.net/qq_42189083/article/details/80610092)

● Hadoop在处理非结构化和半结构化数据上具备优势，尤其适合海量数据批处理等应用要求。

● MPP适合替代现有关系数据机构下的大数据处理，具有较高的效率。

```
MPP适合多维度数据自助分析、数据集市等；Hadoop适合海量数据存储查询、批量数据ETL、非机构化数据分析(日志分析、文本分析)等。

由上述对比可预见未来大数据存储与处理趋势：MPPDB+Hadoop混搭使用，用MPP处理PB级别的、高质量的结构化数据，同时为应用提供丰富的SQL和事物支持能力；用Hadoop实现半结构化、非结构化数据处理。这样可以同时满足结构化、半结构化和非结构化数据的高效处理需求。
```

- 问题

  - MPP架构设计中遇到的最大问题就是“落后者”(straggler)。

    ```
    如果某个节点在执行任何任务时都比其他的节点慢，那么不管集群规模多大，整体的执行性能都会由这个“有问题”的节点决定了。这种情况很容易发生，比如磁盘做了Raid，但是有磁盘突然坏了，raid的性能就会下降了，或者因为硬件或者OS的问题导致CPU性能下降，都可能会产生“慢节点”的问题。
    ```

  - MPP的第二个重概念是“并发”（并发是指可以有效的同时运行的查询数），即每个executor执行同样的数据处理逻辑，处理的数据则是这个executor所在的节点的本地存储的数据分片

    ```
    即当查询开始执行时，每个节点都在并行的执行完全相同的任务， 就是说MPP支持的并发数和集群的节点数没有关系。
    ```

- 批处理设计理念

  - 





- MPP

  Vertica ， Greenplum ， Aster Data 的MPP 都是纯软件的share nothing 并且不共享磁盘的， 数据移动和重分布完全是靠计算机集群完成。