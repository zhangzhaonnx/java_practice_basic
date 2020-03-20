## 作业描述

用JDBC实现StudentRepository类，使的程序能够通过数据库管理学生。

## 提示&要求

- 使用本地数据库，并自行创建Student类对应的表
- 连接数据库时需要添加额外的参数，不然会出现中文乱码和时间错乱的问题，如
    ```
    jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong
    ```
# JDBC

## 学习目标
- 了解JDBC，并掌握基本使用
- 能够使用JDBC进行简单的增删查改操作

## 参考资料:
- `文档` [JDBC 使用说明](https://www.runoob.com/w3cnote/jdbc-use-guide.html)
- `扩展` [Getting Started with the JDBC API](https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/guide/jdbc/getstart/GettingStartedTOC.fm.html)

```sql
CREATE TABLE student (
    id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(100) NOT NULL,
    admission_year INT NOT NULL,
    birthday Date NOT NULL,
    class_id VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

