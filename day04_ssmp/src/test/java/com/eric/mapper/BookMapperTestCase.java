package com.eric.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eric.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 13:53
 */
@Slf4j
@SpringBootTest
public class BookMapperTestCase {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void testGetById()
    {
        Book book = bookMapper.getById(1);
        log.debug("book : " + book);
    }

    @Test
    void testSelectById()
    {
        Book book = bookMapper.selectById(1);
        log.info("book : " + book);
    }

    @Test
    void testSelectList()
    {
        List<Book> bookList = bookMapper.selectList(null);
        for (Book book : bookList)
        {
            log.info("book : " + book);
        }
    }

    @Test
    void testSave()
    {
        Book book = new Book();
        book.setType("计算机理论");
        book.setName("Java编程思想（第4版）");
        book.setDescription("Java学习必读经典，殿堂级著作！赢得了全球程序员的广泛赞誉。");

        int insert = bookMapper.insert(book);
        log.info("insert : " + insert);
        log.info("book : " + book);
    }

    @Test
    void testSelectByPage()
    {
        IPage<Book> page = new Page<>(2, 5);
        IPage<Book> books = bookMapper.selectPage(page, null);
        List<Book> records = books.getRecords();
        for (Book bk : records)
        {
            log.info("book : " + bk);
        }
    }

    @Test
    void testQuery()
    {
        String type = "小说";
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(type != null, (Book::getType), type);
        List<Book> books = bookMapper.selectList(lambdaQueryWrapper);
        for (Book book : books)
        {
            log.debug("book : " + book);
        }
    }
}
