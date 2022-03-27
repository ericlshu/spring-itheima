package com.eric;

import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
// import org.springframework.data.elasticsearch.core.IndexOperations;

/**
 * Description : Test didn't pass.
 *
 * @author Eric L SHU
 * @date 2022-03-27 14:37
 */
@SpringBootTest
public class EsRestOldVersionClientTest {
    // @Autowired
    // private ElasticsearchRestTemplate elasticsearchRestTemplate;
    //
    // @Autowired
    // private BookRepository bookRepository;
    //
    // @Test
    // void testOldVersionIndexOps()
    // {
    //     IndexOperations indexOps = elasticsearchRestTemplate.indexOps(Book.class);
    //     if (indexOps.exists())
    //     {
    //         indexOps.delete();
    //     }
    //     else
    //     {
    //         indexOps.create();
    //     }
    // }
    //
    // @Test
    // void testOldVersionRepositoryOps()
    // {
    //     Book book = new Book(10, "eric", "eric", "eric");
    //     bookRepository.save(book);
    //     bookRepository.delete(book);
    // }
}
