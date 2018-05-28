package com.zyouke.es;

import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyouke.bean.Area;

public class Es {

    private static final Logger logger = LoggerFactory.getLogger(Es.class);
    private static final String INDEX = "zyouke_db"; 
    private static final String TYPE = "area";
    
    public static void creatIndexByEs(List<Area> list,EsConnection esConnection) {
	TransportClient client = esConnection.getClient();
	long start = System.currentTimeMillis();
	try {
	    new XContentFactory();
	    isExists(client);
	    XContentBuilder builder = XContentFactory.jsonBuilder().startObject().startObject(TYPE)
		    		      .startObject("properties").startObject("id").field("type", "long").field("store", "yes").endObject()
		    		      .startObject("code").field("type", "string").field("store", "yes").endObject()
		    		      .startObject("level").field("type", "long").field("store", "yes").endObject()
		    		      .startObject("value").field("type", "string").field("store", "yes").endObject()
		    		      .startObject("fullName").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
		    		      .endObject()
		    		      .endObject();
	    PutMappingRequest mapping = Requests.putMappingRequest(INDEX).type(TYPE).source(builder);
	    client.admin().indices().putMapping(mapping).actionGet();
	    ObjectMapper mapper = new ObjectMapper();
	    BulkRequestBuilder bulkRequest = client.prepareBulk();
	    for (Area area : list) {
		bulkRequest.add(client.prepareIndex(INDEX, TYPE, area.getId() + "").setSource(mapper.writeValueAsBytes(area)));
	    }
	    BulkResponse bulkResponse = bulkRequest.get(TimeValue.timeValueSeconds(5));
	    if (bulkResponse.hasFailures()) {
		for (BulkItemResponse bulkItemResponse : bulkResponse) {
		    System.out.println("建立索引失败的id : " + bulkItemResponse.getId() + "错误消息为" + bulkItemResponse.getFailureMessage());
		}
	    } else {
		System.out.println("建立索引耗时:" + (System.currentTimeMillis() - start) / 1000);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(),e);
	}finally {
	    esConnection.close();
	}

    }

    public static void search(EsConnection esConnection){
	TransportClient client = esConnection.getClient();
	MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
	SearchResponse searchResponse = client.prepareSearch(INDEX)
	      .setTypes(TYPE)
	      .setQuery(matchAllQuery)
	      .setFrom(0)
	      .setSize(10)
	      .get();
	long totalHits = searchResponse.getHits().getTotalHits();
	SearchHit[] hits = searchResponse.getHits().hits();
	for (SearchHit searchHit : hits) {
	    System.out.println(searchHit.getSourceAsString());
	}
	esConnection.close();
    }
    
    public static void search1(EsConnection esConnection,String keyWord){
	TransportClient client = esConnection.getClient();
	TermQueryBuilder termQuery = QueryBuilders.termQuery("value",keyWord);
	SearchResponse searchResponse = client.prepareSearch(INDEX)
	      .setTypes(TYPE)
	      .setQuery(termQuery)
	      .addSort("fullName", SortOrder.DESC)
	      .setFrom(0)
	      .setSize(10)
	      .get();
	long totalHits = searchResponse.getHits().getTotalHits();
	SearchHit[] hits = searchResponse.getHits().hits();
	for (SearchHit searchHit : hits) {
	    System.out.println(searchHit.getSourceAsString());
	}
	esConnection.close();
    }
    
    public static void search2(EsConnection esConnection,String keyWord){
	TransportClient client = esConnection.getClient();
	MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("value",keyWord);
	//matchQuery.analyzer("ik");
	SearchResponse searchResponse = client.prepareSearch(INDEX)
	      .setTypes(TYPE)
	      .setQuery(matchQuery)
	      .setFrom(0)
	      .setSize(10)
	      .get();
	long totalHits = searchResponse.getHits().getTotalHits();
	SearchHit[] hits = searchResponse.getHits().hits();
	for (SearchHit searchHit : hits) {
	    System.out.println(searchHit.getSourceAsString());
	}
	esConnection.close();
    }
    
    public static void search3(EsConnection esConnection,String keyWord){
	
    }
    
    
    /**
     * 删除索引库
     * 
     * @throws UnknownHostException
     */
    public static void deleteIndex(EsConnection esConnection) {
	TransportClient client = esConnection.getClient();
	try {
	    ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
	    String[] indexs = response.getState().getMetaData().getConcreteAllIndices();
	    for (String index : indexs) {
		DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete(index).execute().actionGet();

	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(),e);
	}finally {
	    esConnection.close();
	}
    }
    
    
    private synchronized static TransportClient isExists(TransportClient client) {
	if (!client.admin().indices().prepareExists(INDEX).get().isExists()) {
	    client.admin().indices().prepareCreate(INDEX).get();
	}
	return client;
    }
    
    
    
    
    
    
    
    
}
