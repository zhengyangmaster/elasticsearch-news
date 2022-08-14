package com.example.newssearch.utils;

import com.example.newssearch.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhuZhengYang
 * @description
 * @since 2022/8/14
 */
@Component
public class GetMessage {
    @RabbitListener(queues = RabbitmqConfig.QUEUE_NAME)
    public void listenerQueue(Message message) {

        System.out.println(message);


    }
}
