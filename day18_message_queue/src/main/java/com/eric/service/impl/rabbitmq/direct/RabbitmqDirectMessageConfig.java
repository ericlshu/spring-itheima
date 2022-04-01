package com.eric.service.impl.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 15:50
 */
@Configuration
public class RabbitmqDirectMessageConfig {

    @Bean
    public Queue directQueue()
    {
        return new Queue("direct_queue");
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingDirect()
    {
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with("direct");
    }

}
