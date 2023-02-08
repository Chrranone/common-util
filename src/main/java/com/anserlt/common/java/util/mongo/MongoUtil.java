package com.anserlt.common.java.util.mongo;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Anserlt
 */
@Service
public class MongoUtil {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 聚合查询
     */
    public void find() {
        LocalDateTime now = LocalDateTime.now();
        // 开始时间、结束时间的时间戳
        String startTime = String.valueOf(now.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        String endTime = String.valueOf(now.plusMinutes(5).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

        Aggregation aggregation = Aggregation.newAggregation(
                // 过滤时间
                Aggregation.match(Criteria.where("recordStartTime").gte(startTime).lte(endTime)),
                // 排序
                Aggregation.sort(Sort.Direction.ASC, "recordStartTime"),
                // 分组起别名。
                // fixme，在此处，需要特别注意的是，group会将被group的字段映射到结果集合的”_id“字段上
                Aggregation.group("mainDeviceCode").count().as("flowNum")
                        .sum("exceptionStatus").as("exceptionFlow"),
                // 输出的结果集中包含的字段
                // fixme 在此处，需要特别注意的是，and 以及 previousOperation 这两步操作，是为结果集中的”_id“字段起别名，新名字为mainDeviceCode
                Aggregation.project("flowNum", "exceptionFlow").and("mainDeviceCode").previousOperation());

        // AggregationResults aggregationResults = mongoTemplate.aggregate(aggregation, DocumentType, DocumentType);
        // AggregationResults<Object> aggregationResults = mongoTemplate.aggregate(aggregation, DocumentType, 被映射的类.class);
        // aggregationResults.getMappedResults()  获取结果集合

    }

}
