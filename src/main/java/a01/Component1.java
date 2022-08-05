package a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Component1
{
    private static final Logger log = LoggerFactory.getLogger(Component1.class);

    @Resource
    private ApplicationEventPublisher context;

    public void register()
    {
        log.debug("用户注册");
        context.publishEvent(new UserRegisteredEvent(this));
    }
}
