package com.eric.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import com.eric.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:29
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
}
