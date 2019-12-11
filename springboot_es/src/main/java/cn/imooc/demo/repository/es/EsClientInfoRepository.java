package cn.imooc.demo.repository.es;

import cn.imooc.demo.entity.es.EsClientInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsClientInfoRepository extends ElasticsearchRepository<EsClientInfo,Integer> {
}
