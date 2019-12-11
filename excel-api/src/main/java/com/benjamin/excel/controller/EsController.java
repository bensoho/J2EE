package com.benjamin.excel.controller;

import com.benjamin.excel.pojo.EsClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
@Controller
public class EsController {


    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/index1")
    public String index(){


        return "index1";
    }

//    @ResponseBody
//    @RequestMapping("/search")
//    public List<EsClientInfo> search(@RequestParam String name){
//
//        System.out.println("网页传过来的参数："+name);
//
//        HashMap map = new HashMap();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(queryStringQuery(name))
//                .build();
//        List<EsClientInfo>list = elasticsearchTemplate.queryForList(searchQuery, EsClientInfo.class);
//        return list;
//    }

}