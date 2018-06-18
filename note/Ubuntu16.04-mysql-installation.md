Refer: [Ubuntu 16.04安装MySQL及问题解决](https://www.linuxidc.com/Linux/2017-05/143861.htm)
- sudo apt-get install mysql-server

## 配置远程访问
-编辑配置文件
 
 sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf  将bind-address = 127.0.0.1注释
 
- 修改用户权限
    - 登录MySQL: mysql -u root -p 
    - 远程访问: mysql -h 192.168.0.201 -P 3306 -u root -p123 123  为root用户密码
 
- 用户权限：grant all on *.* to root@'%' identified by '1';  --添加一个用户名是root且密码是1的远程访问用户
 
- 刷新：flush privileges;
 
- 查看是否添加成功：select user,host,authentication_string from user; --在5.7中用户密码是列authentication_string
 
- 退出：quit
 
-重启MySQL服务
 
 /etc/init.d/mysql restart

## `Mysql`重装
- 删除mysql
```
sudo apt-get autoremove --purge mysql-server-5.5
sudo apt-get remove mysql-common
```
- 清理残留数据
```
dpkg -l |grep ^rc|awk '{print $2}' |sudo xargs dpkg -P
``` 
- 重新安装mysql
```
sudo apt-get install mysql-server
sudo apt-get install mysql-client
(未使用)sudo apt-get install php5-mysql
```

## 开启远程连接
```进入mysql -u root -p root
GRANT ALL PRIVILEGES ON *.* TO 'Ubuntu'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
`Ubuntu`用户，`123456`密码
FLUSH PRIVILEGES; 更新数据库
```
```shell
sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
添加'#'注释掉其中的"bind-address = 127.0.0.1"
service mysql restart
```
