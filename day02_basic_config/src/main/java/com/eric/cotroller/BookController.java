package com.eric.cotroller;

import com.eric.config.DataSourceConfig;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-18 14:45
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private Environment environment;

    @Resource
    private DataSourceConfig dataSourceConfig;

    @GetMapping
    public String getById()
    {
        String result = "spring boot quick start ......";
        System.out.println("result = " + result);

        String lesson = environment.getProperty("lesson");
        System.out.println("lesson = " + lesson);

        String name = environment.getProperty("enterprise.name");
        System.out.println("name = " + name);

        String sub1 = environment.getProperty("enterprise.subject[0]");
        System.out.println("sub1 = " + sub1);

        System.out.println("dataSourceConfig = " + dataSourceConfig);

        return result;
    }
}
