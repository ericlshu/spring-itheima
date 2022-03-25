package com.eric;

import com.eric.domain.BookCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 12:46
 */
@SpringBootTest
public class CaseDataTest {
    
    @Autowired
    private BookCase bookCase;

    @Test
    void test()
    {
        System.out.println("bookCase = " + bookCase);
    }
}
