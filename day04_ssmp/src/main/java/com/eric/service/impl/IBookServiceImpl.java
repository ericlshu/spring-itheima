package com.eric.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import com.eric.service.IBookService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:29
 */
@Service
public class IBookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService
{

    @Resource
    private BookMapper bookMapper;

    private final Counter counter;

    public IBookServiceImpl(MeterRegistry meterRegistry)
    {
        counter = meterRegistry.counter("分页查询次数：");
    }

    @Override
    public IPage<Book> getPage(int current, int size)
    {
        return bookMapper.selectPage(new Page<>(current, size), null);
    }

    @Override
    public IPage<Book> getPage(int current, int size, Book book)
    {
        // 统计分页查询执行的次数，并在spring-boot-admin进行展示
        counter.increment();

        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(book.getType()), Book::getType, book.getType());
        lambdaQueryWrapper.like(Strings.isNotEmpty(book.getName()), Book::getName, book.getName());
        lambdaQueryWrapper.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription, book.getDescription());
        return bookMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }
}
