# SpringCloud-Base

SpringCloud聚合工程基础框架：整合Nacos、Gateway、Sentinel，集成Logback、Mybatis-generator。


环境配置
/base-env/software.txt

基础数据库文件
/base-env/db/*

Nacos配置中心文件
/base-env/nacos/*

Seata配置文件
/base-env/Seata/*

逆向工程
mybatis-generator:generate -e

Sentinel启动命令
java -Dserver.port=7070 -Dcsp.sentinel.dashboard.server=localhost:7070 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar