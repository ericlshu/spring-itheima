package com.eric;

import com.alibaba.fastjson.JSON;
import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
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
    CreateIndexRequest request;

    @BeforeEach
    void setUp()
    {
        client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://localhost:9200")));
        request = new CreateIndexRequest("books");
    }

    @AfterEach
    void tearDown() throws IOException
    {
        client.close();
    }

    @Test
    public void testCreateIndex() throws IOException
    {
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建索引
     */
    @Test
    void testCreateIndexByIK() throws IOException
    {
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
}
