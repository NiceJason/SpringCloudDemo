package com.nicebin.search.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nicebin.api.core.Result;
import com.nicebin.search.entity.User;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author DiaoJianBin
 * @Description Elasticsearch测试
 * @Date 2021/4/26 21:08
 */
@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    @Autowired
    RestHighLevelClient client;

    @RequestMapping("/doInsert")
    public Result doInsert(@RequestBody User user, @RequestHeader(value = "index") String index, @RequestHeader(value = "id", required = false) String id) throws IOException {

        StringBuilder message = new StringBuilder();

        //1、添加新文档需要调用IndexRequest请求，如果没有索引则自动生成
        IndexRequest indexRequest = new IndexRequest(index);

        //添加id，如果id没有传入则自动生成
        if (id != null) {
            indexRequest.id(id);
        } else {
            indexRequest.id();
        }

        //source方法；将文档源设置为索引
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        //发送请求
        //同步执行 当以下列方式执行IndexRequest时，客户端在继续执行代码之前，会等待返回索引响应:
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        /**indexResponse 示例：
         * {
         *     "_shards" : {
         *         "total" : 2,
         *         "failed" : 0,
         *         "successful" : 1
         *     },
         *     "_index" : "twitter",
         *     "_type" : "_doc",
         *     "_id" : "1",
         *     "_version" : 1,
         *     "_seq_no" : 0,
         *     "_primary_term" : 1,
         *     "result" : "created"
         * }
         */
        //获取结果，进行比较
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            message.append("创建成功 ");
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            message.append("更新成功 ");
        }
        //获取分片信息
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();

        //创建失败
        if (shardInfo.getFailed() > 0) {
            ArrayList<Object> reason = new ArrayList<>();
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                reason.add(failure.reason());
            }
            message.append("创建失败");
            return  Result.error(message.toString()+" "+reason);
        } else {
            message.append("集群部分创建成功");
        }
        return Result.success(message.toString());
    }

    @RequestMapping("/doUpdate")
    public Result doUpdate(@RequestBody User user, @RequestHeader(value = "index") String index, @RequestHeader(value = "id") String id) throws IOException {

        //构建更新请求体
        UpdateRequest request = new UpdateRequest(index, id);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        //发送请求
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        //处理请求结果
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            return Result.success("创建成功");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return  Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @RequestMapping("/doDelete")
    public Result doDelete(@RequestBody User user, @RequestHeader(value = "index") String index, @RequestHeader(value = "id") String id) throws IOException {

        //构建更新请求体
        DeleteRequest request = new DeleteRequest(index, id);

        //发送请求
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        //处理请求结果
        if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败 执行序列号：" + deleteResponse.getSeqNo());
        }
    }

    /**
     * 创建搜索请求对象SearchRequest，设置查询的指定索引和类型
     * 创建搜索内容对象SearchSourceBuilder
     * 创建查询对象MatchQueryBuilder，以及MatchQueryBuilder对象的配置
     * 将查询对象MatchQueryBuilder添加到搜索内容对象SearchSourceBuilder中，以及SearchSourceBuilder对象的配置
     * 将搜索内容对象SearchSourceBuilder添加到搜索请求对象SearchRequest中
     * @param params  如
     *                {
     *                  "fieldName":"name",
     *                  "value":"小明"
     *                  }
     * @param index
     * @return
     * @throws IOException
     */
    @RequestMapping("/getByMatch")
    public Result getByMatch(@RequestBody JSONObject params, @RequestHeader(value = "index", required = false) String index) throws IOException {
        //1、创建搜索请求对象SearchRequest，设置查询的指定索引
        SearchRequest searchRequest;
        if(index == null){
            searchRequest = new SearchRequest();
        }else{
            searchRequest = new SearchRequest(index);
        }


        //2、创建搜索内容对象SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //3、创建查询对象MatchQueryBuilder，以及MatchQueryBuilder对象的配置
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(params.getString("fieldName"), params.getString("value"));
        //启动模糊查询
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        //设置最大扩展选项以控制查询的模糊过程
        matchQueryBuilder.maxExpansions(10);

        //4、将查询对象MatchQueryBuilder添加到/搜索内容对象SearchSourceBuilder中，以及SearchSourceBuilder对象的配
        searchSourceBuilder.query(matchQueryBuilder);
        //设置查询的起始索引位置
        searchSourceBuilder.from(0);
        //设置查询的数量
        searchSourceBuilder.size(5);
        //设置超时时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //5、将搜索内容对象SearchSourceBuilder添加到搜索请求对象SearchRequest中
        searchRequest.source(searchSourceBuilder);

        //发送搜索请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        //解析响应结果
        ArrayList list = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> map = hit.getSourceAsMap();
            list.add(JSONObject.parseObject(JSON.toJSONString(map)).toJavaObject(User.class));
        }
        return Result.success(list);
    }

    /**
     * 创建搜索请求对象SearchRequest，设置查询的指定索引和类型
     * 创建搜索内容对象SearchSourceBuilder
     * 创建查询对象MatchQueryBuilder，以及MatchQueryBuilder对象的配置
     * 将查询对象MatchQueryBuilder添加到搜索内容对象SearchSourceBuilder中，以及SearchSourceBuilder对象的配置
     * 将搜索内容对象SearchSourceBuilder添加到搜索请求对象SearchRequest中
     * @param params  如
     *                {
     *                  "fieldName":"name",
     *                  "value":"小明"
     *                  }
     * @param index
     * @return
     * @throws IOException
     */
    @RequestMapping("/getByTerm")
    public Result getByTerm(@RequestBody JSONObject params, @RequestHeader(value = "index", required = false) String index) throws IOException {
        //1、创建搜索请求对象SearchRequest，设置查询的指定索引
        SearchRequest searchRequest;
        if(index == null){
            searchRequest = new SearchRequest();
        }else{
            searchRequest = new SearchRequest(index);
        }

        //2、创建搜索内容对象SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //3、创建查询对象TermQueryBuilder，以及TermQueryBuilder对象的配置
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder(params.getString("fieldName"),params.getString("value"));


        //4、将查询对象TermQueryBuilder添加到/搜索内容对象SearchSourceBuilder中，以及SearchSourceBuilder对象的配
        searchSourceBuilder.query(termQueryBuilder);
        //设置查询的起始索引位置
        searchSourceBuilder.from(0);
        //设置查询的数量
        searchSourceBuilder.size(5);
        //设置超时时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //5、将搜索内容对象SearchSourceBuilder添加到搜索请求对象SearchRequest中
        searchRequest.source(searchSourceBuilder);

        //发送搜索请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        //解析响应结果
        ArrayList list = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> map = hit.getSourceAsMap();
            list.add(JSONObject.parseObject(JSON.toJSONString(map)).toJavaObject(User.class));
        }
        return Result.success(list);
    }
}
