package com.example.demo.entity;


import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
@Entity
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
public class Xbrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String xbrlpath;
//    @Type(type = "JsonDataUserType")
    @Column(name = "xbrlparsecontent",columnDefinition="jsonb")
    private String xbrlparsecontent;
    private String xbrlname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXbrlpath() {
        return xbrlpath;
    }

    public void setXbrlpath(String xbrlpath) {
        this.xbrlpath = xbrlpath;
    }

    public String getXbrlparsecontent() {
        return xbrlparsecontent;
    }

    public void setXbrlparsecontent(String xbrlparsecontent) {
        this.xbrlparsecontent = xbrlparsecontent;
    }

    public String getXbrlname() {
        return xbrlname;
    }

    public void setXbrlname(String xbrlname) {
        this.xbrlname = xbrlname;
    }
}
