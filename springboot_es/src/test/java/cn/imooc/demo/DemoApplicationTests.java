package cn.imooc.demo;

import cn.imooc.demo.entity.es.EsClientInfo;
import cn.imooc.demo.repository.es.EsClientInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    EsClientInfoRepository repository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEs(){
        Iterable<EsClientInfo> all = repository.findAll();
        Iterator<EsClientInfo> iterator = all.iterator();
        EsClientInfo next = iterator.next();
        System.out.println(next.getAddress_1());

    }

    @Test
    //搜索标题中的关键字
    public void search(){
//        Optional<EsClientInfo> one = repository.findById(10);
//        System.out.println(one.toString());
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery("shenzhen VAN HEURCK Co Ltd"))
                .build();
        List<EsClientInfo>list = elasticsearchTemplate.queryForList(searchQuery, EsClientInfo.class);
        for (EsClientInfo es:list){
            System.out.println(es.getCompany_name());
        }

    }
}