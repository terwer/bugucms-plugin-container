# bugucms
轻量级、插件化的内容管理系统

# 相关技术
JDK1.8.162   
Spring Boot 2.0.0   
Maven   
Tomcat 8.5.28   

# 启动
## 本地启动   
（1）打开``pom.xml``里面```spring-boot-starter-tomcat```的```<scope>provided</scope>```   
（2）运行```BugucmsApplication.java```里面的```main```方法  

## Tomcat启动
（1）注释掉``pom.xml``里面```spring-boot-starter-tomcat```的```<scope>provided</scope>``` ，或者直接复制```pom-tomcat.xml```到```pom.xml```   
（2）使用Intellij Idea  
Build --> Build Artifacts --> 选择要打包编译的war --> 在项目target文件夹下出现新的war包   
（3）将war包放到tomcat的webapps目录下   