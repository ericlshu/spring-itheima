package com.eric;

import com.eric.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * 基础查询
 * -- 查询全部         db.集合.find();
 * -- 查第一条         db.集合.findOne()
 * -- 查询指定数量文档   db.集合.find().limit(10)		        //查10条文档
 * -- 跳过指定数量文档   db.集合.find().skip(20)		        //跳过20条文档
 * -- 统计            db.集合.count()
 * -- 排序            db.集合.sort({age:1})				    //按age升序排序
 * -- 投影            db.集合名称.find(条件,{name:1,age:1})	    //仅保留name与age域
 * <p>
 * 条件查询
 * -- 基本格式         db.集合.find({条件})
 * -- 模糊查询         db.集合.find({域名:/正则表达式/})		    // 等同SQL中的like，比like强大，可以执行正则所有规则
 * -- 条件比较运算      db.集合.find({域名:{$gt:值}})		    // 等同SQL中的数值比较操作，例如：name>18
 * -- 包含查询         db.集合.find({域名:{$in:[值1，值2]}})	    // 等同于SQL中的in
 * -- 条件连接查询      db.集合.find({$and:[{条件1},{条件2}]})	// 等同于SQL中的and、or
 */
@Slf4j
@SpringBootTest
class Day11DaoMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testSave()
    {
        Book book = new Book(2, "dev2", "springboot2", "springboot2");
        Book save = mongoTemplate.save(book);
        System.out.println("save = " + save);
    }

    @Test
    void testFind()
    {
        List<Book> bookList = mongoTemplate.findAll(Book.class);
        for (Book book : bookList)
        {
            log.info("bookList = " + bookList);
        }
    }

}
