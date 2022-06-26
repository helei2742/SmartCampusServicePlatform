package org.pg7.scsp.jobs;

import lombok.extern.slf4j.Slf4j;
import org.pg7.scsp.service.impl.NewsServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateNewsSeeCountJob implements Job {

    @Autowired
    private NewsServiceImpl newsService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("正在更新数据库新闻查看数量");
        int i = newsService.updateRedisSeeCountToDB();
        log.info("更新数据库新闻查看数量完毕, 更新了"+i+"条");
    }
}
