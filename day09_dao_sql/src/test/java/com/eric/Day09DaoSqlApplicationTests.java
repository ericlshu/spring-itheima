package com.eric;

import com.eric.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class Day09DaoSqlApplicationTests {

    // @Autowired
    // private BookMapper bookMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads()
    {
        // Book book = bookMapper.selectById(10);
        // log.warn("book : " + book);
    }

    @Test
    void testJdbcTemplate()
    {
        String sql= "insert into tbl_book values (null,?,?,?)";
        jdbcTemplate.update(sql,"SpringBoot Name","SpringBoot Type","SpringBoot Description");

        sql = "select * from tbl_book";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        // System.out.println("maps = " + maps);

        RowMapper<Book> rowMapper = (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            String description = rs.getString("description");
            return new Book(id, name, type, description);
        };

        List<Book> bookList = jdbcTemplate.query(sql, rowMapper);
        for (Book book : bookList)
        {
            log.warn("book : " + book);
        }
    }

    @Test
    void testH2Database()
    {
        String sql= "insert into book values (?,?)";
        int result = jdbcTemplate.update(sql, 5,"SpringBoot Name5");
        System.out.println("result = " + result);

        sql = "select * from book";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println("maps = " + maps);
    }
}
