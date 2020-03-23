## Seata部署方案

### 1.Seata-Server部署

1.修改`seata/conf/file.conf`文件

```conf
 # 事务组名称
  vgroup_mapping.fsp_tx_group = "default"
  
 store {
  ## store mode: file、db
  mode = "db"

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    driver-class-name = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "root"
    password = "12345678"
   ...
  }
}
```

2.在mysql中用`seata/conf/db_store.sql`建表



### 2.CS部署



1.`pom.xml`增加配置：

```xml
<!-- seata-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
    <exclusions>
        <exclusion>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>io.seata</groupId>
    <artifactId>seata-all</artifactId>
    <version>0.9.0</version>
</dependency>
<!-- seata-->
```



2.业务数据库里面必须添加`db_undo_log.sql`，生成undo_log，用于回滚。

3.需要将`file.conf/registry.conf`拷贝进`resource`文件夹下。

4.在主业务类上添加`@GlobalTranstional`注解