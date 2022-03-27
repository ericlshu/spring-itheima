package com.eric;

import com.alibaba.fastjson.JSON;
import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 14:42
 */
@Slf4j
@SpringBootTest
public class EsRestHighLevelClientTest {

    @Test
    public void test() throws IOException
    {
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        System.out.println("restHighLevelClient = " + restHighLevelClient);

        CreateIndexRequest request = new CreateIndexRequest("books");
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        restHighLevelClient.close();
    }

    RestHighLevelClient client;

    @BeforeEach
    void setUp()
    {
        client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://localhost:9200")));
    }

    @AfterEach
    void tearDown() throws IOException
    {
        client.close();
    }

    @Test
    public void testCreateIndex() throws IOException
    {
        CreateIndexRequest request = new CreateIndexRequest("books");
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建索引
     */
    @Test
    void testCreateIndexByIK() throws IOException
    {
        CreateIndexRequest request = new CreateIndexRequest("books");
        String source = "{\n" +
                "    \"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\", \n" +
                "                \"analyzer\":\"ik_max_word\", \n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"type\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"description\":{\n" +
                "                \"type\":\"text\", \n" +
                "                \"analyzer\":\"ik_max_word\", \n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        request.source(source, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Autowired
    private BookMapper bookMapper;

    /**
     * 添加文档
     */
    @Test
    void testCreateDoc() throws IOException
    {
        Book book = bookMapper.selectById(1);

        IndexRequest request = new IndexRequest("books");
        request.id(book.getId().toString());

        String json = JSON.toJSONString(book);
        request.source(json, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 添加所有文档
     */
    @Test
    void testCreateAllDocs() throws IOException
    {
        List<Book> bookList = bookMapper.selectList(null);
        BulkRequest bulkRequest = new BulkRequest();
        for (Book book : bookList)
        {
            IndexRequest request = new IndexRequest("books");
            request.id(book.getId().toString());
            String json = JSON.toJSONString(book);
            request.source(json, XContentType.JSON);
            bulkRequest.add(request);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 按id查询文档
     */
    @Test
    void testGetRequest() throws IOException
    {
        GetRequest request = new GetRequest("books", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String resultAsJson = response.getSourceAsString();
        System.out.println("resultAsJson = " + resultAsJson);
    }

    /**
     * 按条件查询文档
     */
    @Test
    void testSearch() throws IOException
    {
        SearchRequest request = new SearchRequest("books");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("type","悬疑推理"));
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits)
        {
            String sourceAsString = hit.getSourceAsString();
            System.out.println("sourceAsString = " + sourceAsString);
            Book book = JSON.parseObject(sourceAsString, Book.class);
            log.info("book = " + book);
        }
    }
}
