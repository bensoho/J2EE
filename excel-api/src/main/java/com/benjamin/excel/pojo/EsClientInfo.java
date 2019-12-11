package com.benjamin.excel.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "client_info",type = "doc",useServerConfiguration = true,
        createIndex = false)
public class EsClientInfo {

    @Id
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String company_id;

    @Field(type = FieldType.Keyword)
    private String company_name;
    @Field(type = FieldType.Text,analyzer = "standard")
    private String address_1;
    @Field(type = FieldType.Text,analyzer = "standard")
    private String address_2;
    @Field(type = FieldType.Keyword)
    private String city;
    @Field(type = FieldType.Keyword)
    private String state_name;
    @Field(type = FieldType.Keyword)
    private String country_code;
    @Field(type = FieldType.Keyword)
    private String country_name;
    @Field(type = FieldType.Keyword)
    private String zip;
    @Field(type = FieldType.Keyword)
    private String tax_id;
    private String notes;
    private Date update_time;
}