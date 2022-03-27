package com.eric;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
        CreateIndexRequest request = new CreateIndexRequest("book");
        client.indices().create(request, RequestOptions.DEFAULT);
    }
    
}
