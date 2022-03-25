package com.eric.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eric.domain.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 13:51
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
