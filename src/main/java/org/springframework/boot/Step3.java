package org.springframework.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

@Slf4j
public class Step3
{
    public static void main(String[] args) throws IOException
    {
        // 系统环境变量, properties, yaml
        ApplicationEnvironment env = new ApplicationEnvironment();
        env.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        env.getPropertySources().addLast(new ResourcePropertySource(new ClassPathResource("step3.properties")));
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }
        log.info("JAVA_HOME : {}", env.getProperty("JAVA_HOME"));

        log.info("server.port : {}", env.getProperty("server.port"));
    }
}
