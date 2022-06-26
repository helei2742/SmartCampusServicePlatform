package org.pg7.scsp.service.impl;

import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.NewsRedisDto;
import org.pg7.scsp.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class NewsServiceImplTest {
    @Autowired
    NewsServiceImpl service;
    @Test
    void queryById() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(service.queryById(13));
                }
            }).start();

        }
        Thread.sleep(100000);
    }

    @Test
    void queryHotX() {
        int i = service.updateRedisSeeCountToDB();
        System.out.println(i);
    }

    @Test
    void updateRedisSeeCountToDB() {
        Result r1 = service.queryHotX(10);
        List<NewsRedisDto> l1 = (List<NewsRedisDto>) r1.getData();
        System.out.println(l1.size());
        l1.forEach(System.out::println);

        System.out.println("--------------------------");
        Result r2 = service.queryHotX(2);
        List<NewsRedisDto> l2 = (List<NewsRedisDto>) r2.getData();
        System.out.println(l2.size());
        l2.forEach(System.out::println);
        System.out.println("--------------------------");

        Result r3 = service.queryHotX(3);
        List<NewsRedisDto> l3 = (List<NewsRedisDto>) r3.getData();
        System.out.println(l3.size());
        l3.forEach(System.out::println);
        System.out.println("--------------------------");

        Result r5 = service.queryHotX(5);
        List<NewsRedisDto> l5 = (List<NewsRedisDto>) r5.getData();
        System.out.println(l5.size());
        l5.forEach(System.out::println);
    }
}