package com.example.newssearch.common.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhuZhengYang
 * @since 2022/8/14
 */
@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_NAME = "news.all";
    public static final String QUEUE_NAME = "news.top";

    //定义交换机
    @Bean
    public Exchange topicExchange() {

        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //定义队列
    @Bean
    public Queue newsQueue() {

        return QueueBuilder.durable(QUEUE_NAME).build();

    }

    //将交换机于队列进行绑定
    @Bean
    public Binding topicBinding(@Qualifier("newsQueue") Queue queue, @Qualifier("topicExchange") Exchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with("#").noargs();
    }


}
