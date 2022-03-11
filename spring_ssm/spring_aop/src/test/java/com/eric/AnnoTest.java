package com.eric;

import com.eric.anno.Target;
import com.eric.anno.TargetInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 15:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-anno.xml")
public class AnnoTest {
    public static final Logger LOGGER = LogManager.getLogger();

    @Resource(name = "target")
    private TargetInterface target;

    @Test
    public void testSave()
    {
        String save = target.save();
        LOGGER.debug("result : " + save);
    }
}
