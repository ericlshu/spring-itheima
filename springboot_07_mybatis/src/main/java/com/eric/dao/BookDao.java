package com.eric.dao;

import com.eric.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao
{
    @Select("select * from tbl_book where id = #{id}")
    Book getById(Integer id);
}
