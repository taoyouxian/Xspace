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

git remote -v
- 添加源为dbiir
git remote add dbiir https://taoyx@bitbucket.org/dbiir/pixels.git 
- 抓取最新的master上面的代码
git pull dbiir master

git push 直接push到自己的fork上面

- 根据ssh来git操作
- 配置.mvn
```
mvn -N io.takari:maven:wrapper
```
