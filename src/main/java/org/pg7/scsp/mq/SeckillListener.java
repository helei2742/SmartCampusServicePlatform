package org.pg7.scsp.mq;

import org.pg7.scsp.utils.MQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SeckillListener {

    @RabbitListener(queues = MQConstants.SECKILL_COURSE_QUEUE)
    public void listenSeckill(Long orderId){
        System.out.println(orderId);

    }
}
