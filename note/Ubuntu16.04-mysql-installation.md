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
