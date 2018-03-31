# bugucms
轻量级、插件化的内容管理系统

# 相关技术
JDK1.8.162   
Spring Boot 2.0.0   
LogBack      
FreeMarker    
Maven 3.5.3      
Tomcat 8.5.28   
MySQL 5.7.19   
Oracle 11g

# 启动
## 注意
ojdbc6.jar需要手动安装，在项目的lib目录下，需要用命令```mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=ojdbc6.jar```安装   
## 本地启动   
（1）打开``pom.xml``里面```spring-boot-starter-tomcat```的```<scope>provided</scope>```   
（2）运行```BugucmsApplication.java```里面的```main```方法  

## Tomcat启动
（1）注释掉``pom.xml``里面```spring-boot-starter-tomcat```的```<scope>provided</scope>``` ，或者直接复制```pom-tomcat.xml```到```pom.xml```   
（2）使用Intellij Idea  
Build --> Build Artifacts --> 选择要打包编译的war --> 在项目target文件夹下出现新的war包   
（3）将war包放到tomcat的webapps目录下   