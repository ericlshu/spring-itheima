package org.springframework.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

@Slf4j
public class Step4
{
    public static void main(String[] args) throws IOException, NoSuchFieldException
    {
        ApplicationEnvironment env = new ApplicationEnvironment();
        env.getPropertySources().addLast(
                new ResourcePropertySource("step4", new ClassPathResource("step4.properties"))
        );

        ConfigurationPropertySources.attach(env);
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }

        log.info(env.getProperty("user.first-name"));
        log.info(env.getProperty("user.middle-name"));
        log.info(env.getProperty("user.last-name"));
    }
}
