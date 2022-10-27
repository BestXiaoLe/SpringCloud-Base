package com.bestxiaole.server.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @Document 用来声明ES索引信息
 * indexName 索引名称
 * shards 主分区数量，默认5
 * replicas 副本分区数量，默认1
 * createIndex true：SpringBoot启动后自动创建索引，此时映射Mapping为空，默认为true；false：SpringBoot启动后不会自动创建索引，需要手动创建索引。
 */
@Document(indexName = "userinfo_index", shards = 5, replicas = 1, createIndex = false)
public class UserInfo {

    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    //type : 字段数据类型
    //analyzer : 分词器类型
    //index : 是否索引，默认为true
    @Field(type = FieldType.Text, analyzer = "ik_max_word", index = true)
    private String address;

    private Date creat_time;

    public UserInfo(String id, String name, String address, Date creat_time) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.creat_time = creat_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }
}