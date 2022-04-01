package com.eric.service.impl.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 15:50
 */
@Configuration
public class RabbitmqTopicMessageConfig {

    @Bean
    public Queue topicQueue1()
    {
        return new Queue("topic_queue_1");
    }

    @Bean
    public Queue topicQueue2()
    {
        return new Queue("topic_queue_2");
    }

    @Bean
    public TopicExchange topicExchange()
    {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingTopic1()
    {
        return BindingBuilder.bind(topicQueue1())
                .to(topicExchange())
                .with("topic.*.id");
    }

    @Bean
    public Binding bindingTopic2()
    {
        return BindingBuilder.bind(topicQueue2())
                .to(topicExchange())
                .with("topic.orders.*");
    }

}
