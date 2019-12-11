package com.benjamin.excel.mapper;

import com.benjamin.excel.pojo.EsClientInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsClientInfoRepository extends ElasticsearchRepository<EsClientInfo,Integer> {
}
