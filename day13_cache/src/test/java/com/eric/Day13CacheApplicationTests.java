package com.eric;

import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class Day13CacheApplicationTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void contextLoads()
    {
        List<Book> bookList = bookMapper.selectList(null);
        for (Book book : bookList)
        {
            log.info(book.toString());
        }
    }

}
