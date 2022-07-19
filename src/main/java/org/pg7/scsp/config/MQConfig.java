package org.pg7.scsp.config;


import org.pg7.scsp.utils.MQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MQConfig {

    @Bean
    public TopicExchange seckillExchange(){
        return new TopicExchange(MQConstants.SECKILL_COURSE_EXCHANGE, true, false);
    }

    @Bean
    public Queue seckillQueue(){
        return new Queue(MQConstants.SECKILL_COURSE_QUEUE, true);
    }

    @Bean
    public Binding seckillCourseBinding(){
       return BindingBuilder.bind(seckillQueue()).to(seckillExchange()).with(MQConstants.SECKILL_COURSE_KEY);
    }
}
