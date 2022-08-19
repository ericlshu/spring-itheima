package org.springframework.boot;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/**
 * 绑定 spring.main 前缀的 key value 至 SpringApplication, 请通过 debug 查看
 */
@Slf4j
public class Step6
{
    public static void main(String[] args) throws IOException
    {
        SpringApplication application = new SpringApplication();
        ApplicationEnvironment env = new ApplicationEnvironment();
        env.getPropertySources().addLast(new ResourcePropertySource("step4", new ClassPathResource("step4.properties")));
        env.getPropertySources().addLast(new ResourcePropertySource("step6", new ClassPathResource("step6.properties")));

        // User user = Binder.get(env).bind("user", User.class).get();
        // log.info("user : {}", user);

        // User user = new User();
        // Binder.get(env).bind("user", Bindable.ofInstance(user));
        // log.info("user : {}", user);

        log.info("Application before user  : {}", application);
        Binder.get(env).bind("spring.main", Bindable.ofInstance(application));
        log.info("Application before after : {}", application);
    }

    @Data
    static class User
    {
        private String firstName;
        private String middleName;
        private String lastName;
    }
}
