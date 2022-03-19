package com.eric.dao.impl;

import com.eric.dao.BookDao;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-19 20:03
 */
@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save()
    {
        System.out.println("com.eric.dao.impl.BookDaoImpl.save ... ");
    }
}
