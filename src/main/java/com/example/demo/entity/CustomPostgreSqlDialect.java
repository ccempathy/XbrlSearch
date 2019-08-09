package com.example.demo.entity;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

/**
 * Created by Administrator on 2019/8/8 0008.
 */
public class CustomPostgreSqlDialect extends PostgreSQL9Dialect {

    public CustomPostgreSqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
