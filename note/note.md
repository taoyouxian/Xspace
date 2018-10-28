## 添加依赖包至文件夹
<!-- 把jar复制到target目录下的lib目录下 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <!-- ${project.build.directory}就是我们通常看到的target目录 -->
                            <outputDirectory>${project.build.directory}/pixels</outputDirectory>
                            <excludeTransitive>false</excludeTransitive>
                            <stripVersion>true</stripVersion>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>   <!-- 告知 maven-jar-plugin 添加一个 Class-Path 元素到 MANIFEST.MF 文件 -->
                            <classpathPrefix>lib/</classpathPrefix> <!-- classpathPrefix 指出，相对存档文件，所有的依赖项应该位于 “lib” 文件夹 -->
                            <!--<mainClass>theMainClass</mainClass> --> <!-- 当用户使用 lib 命令执行 JAR 文件时，使用该元素定义将要执行的类名 -->
                        </manifest>
                    </archive>
                </configuration>
            </plugin>

## git设置远端源

```
git remote set-url origin https://taoyx@bitbucket.org/taoyx/pixels.git https://taoyx@bitbucket.org/dbiir/pixels.git
```
- 删除源
git remote remove origin

git remote -v
- 添加源为dbiir
git remote add dbiir git@bitbucket.org:dbiir/pixels.git
- 添加本地源
git remote add origin git@bitbucket.org:taoyx/pixels.git
- 抓取最新的master上面的代码
git pull dbiir master

git push 直接push到自己的fork上面

- 根据ssh来git操作
- 配置.mvn
```
mvn -N io.takari:maven:wrapper
```

## scp
- plugins
```sh
scp -r pixels-presto-0.1.0-SNAPSHOT presto@presto00:/home/presto/opt/presto-server-0.192/plugin
```
or
```
scp -r pixels-presto-0.1.0-SNAPSHOT presto@presto00:~/opt/presto-server-0.192/plugin
```

- metadata server
```
scp pixels-daemon-0.1.0-SNAPSHOT-full.jar presto@presto00:~/opt/presto-server-0.192
```
- properties
```
scp pixels.properties presto@presto00:~/opt/presto-server-0.192
```
- mv-plugins.sh
```sh
#!/bin/bash
rm /home/presto/opt/presto-server-0.192/data/var/log/server.log
for ((i=1; i<=4; i++))
do
scp -r /home/presto/opt/presto-server-0.192/plugin/pixels-presto-0.1.0-SNAPSHOT/
 presto@presto0$i:/home/presto/opt/presto-server-0.192/plugin/  
scp /home/presto/opt/presto-server-0.192/pixels.properties presto@presto0$i:/hom
e/presto/opt/presto-server-0.192/
ssh presto@presto0$i rm /home/presto/opt/presto-server-0.192/data/var/log/server.l
og
exit
```

## run
- Thread1
```sh
java -Drole=main -jar pixels-daemon-0.1.0-SNAPSHOT-full.jar metadata
```
- Thread2
```sh
./bin/presto --server localhost:8080 --catalog pixels --schema pixels 
```

## connector
- module.xml
```xml
<packaging>presto-plugin</packaging>
```
- pom.xml
```xml
 <plugins>
    <plugin>
        <groupId>com.facebook.presto</groupId>
        <artifactId>presto-maven-plugin</artifactId>
        <version>0.3</version>
        <extensions>true</extensions>
    </plugin>
</plugins>
```

## 日志
presto的Master、Worker节点，看查询执行需要的节点数量，从而查看执行日志在哪部分节点上，有利于快速查看到Split之后的日志信息