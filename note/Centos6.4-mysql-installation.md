# link
- [Centos6.4安装配置mysql](http://www.cnblogs.com/xizhunet/p/5133511.html) 

## mysql简介
MySQL是一个关系型数据库管理系统，由瑞典MySQL AB公司开发，目前属于Oracle公司。MySQL是一种关联数据库管理系统，关联数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。MySQL的SQL语言是用于访问数据库的最常用标准化语言。MySQL软件采用了双授权政策（本词条“授权政策”），它分为社区版和商业版，由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，一般中小型网站的开发都选择MySQL作为网站数据库。由于其社区版的性能卓越，搭配PHP和Apache可组成良好的开发环境。

## 卸载掉原有mysql
因为mysql数据库在Linux上实在是太流行了，所以目前下载的主流Linux系统版本基本上都集成了mysql数据库在里面，我们可以通过如下命令来查看我们的操作系统上是否已经安装了mysql数据库。
```
[root@master ~]# rpm -qa | grep mysql　　// 这个命令就会查看该操作系统上是否已经安装了mysql数据库
```
有的话，我们就通过 `rpm -e` 命令 或者 `rpm -e --nodeps` 命令来卸载掉
```
[root@master ~]# rpm -e mysql　　// 普通删除模式
[root@master ~]# rpm -e --nodeps mysql　　// 强力删除模式，如果使用上面命令删除时，提示有依赖的其它文件，则用该命令可以对其进行强力删除
```
在删除完以后我们可以通过 `rpm -qa | grep mysql` 命令来查看mysql是否已经卸载成功！！

## 通过yum来进行mysql的安装
```
[root@master ~]# yum list | grep mysql
[root@master ~]# yum install -y mysql-server mysql mysql-devel
[root@master ~]# rpm -qi mysql-server
```

## mysql数据库的初始化及相关配置
我们在安装完mysql数据库以后，会发现会多出一个mysqld的服务，这个就是咱们的数据库服务，我们通过输入 `service mysqld start` 命令就可以启动我们的mysql服务。

注意：如果我们是第一次启动mysql服务，mysql服务器首先会进行初始化的配置，如：
```
[root@master ~]# service mysqld start
```
这时我们会看到第一次启动mysql服务器以后会提示非常多的信息，目的就是对mysql数据库进行初始化操作，当我们再次重新启动mysql服务时，就不会提示这么多信息了，如：
```
[root@master ~]# service mysqld restart
```
我们在使用mysql数据库时，都得首先启动mysqld服务，我们可以 通过  `chkconfig --list | grep mysqld` 命令来查看mysql服务是不是开机自动启动，如：
```
[root@master ~]# chkconfig --list | grep mysqld
```
我们发现mysqld服务并没有开机自动启动，我们当然可以通过 `chkconfig mysqld on` 命令来将其设置成开机启动，这样就不用每次都去手动启动了
```
[root@master ~]# chkconfig mysqld on
[root@master ~]# chkconfig --list | grep mysql
```
所以我们可以通过 该命令来给我们的root账号设置密码(注意：这个root账号是mysql的root账号，非Linux的root账号)
```
[root@master ~]# mysqladmin -u root password 'root'　　// 通过该命令给root账号设置密码为 root
```

## mysql数据库的主要配置文件
`/etc/my.cnf` 这是mysql的主配置文件
我们可以查看一下这个文件的一些信息
```
[root@master ~]# cat /etc/my.cnf
```


