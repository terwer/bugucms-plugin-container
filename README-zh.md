# bugucms

轻量级，插件化的开发平台

![BuguCMS](https://ghproxy.com/https://raw.githubusercontent.com/terwer/bugucms/82078355d55db99479df464ca0fb56960f899a93/logo.jpg)

释义：BuguCMS，全称 Bugu Content Manage System，中文即布谷内容管理系统，灵感取自布谷鸟，鸣声响亮，二声一度，希望用户在使用时像耳边时刻响起布谷鸟叫声一样愉悦。

# 亮点

- 插件支持，支持自定义插件和扩展
- 多模板引擎支持，支持主流模板引擎Thymeleaf、Freemarker、Velocity
- 多数据库支持，主持主流数据库Oracle、SQL Server、MySQL、H2

# 准备

先安装公共插件接口 

https://gitee.com/youweics/shared-plugin-interfaces

# 运行

## docker(推荐)

```
docker compose up --build
```

## 自定义

```
chmod +x run.sh
./run.sh
```