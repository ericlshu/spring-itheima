package com.eric.repository;

import com.eric.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 14:26
 */
@Component
public interface BookRepository extends ElasticsearchRepository<Book, String> {
}
