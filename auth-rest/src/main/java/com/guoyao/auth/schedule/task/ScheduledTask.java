/**
 * 
 */
package com.guoyao.auth.schedule.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guoyao.auth.authorize.web.util.DateUtil;
import com.guoyao.auth.service.SolrService;

import lombok.extern.slf4j.Slf4j;
 

/**
 * @author wuchao
 * @Date 【2019年3月18日:上午9:15:59】
 */
@Slf4j
@Component
public class ScheduledTask {
	
	@Autowired
	private SolrService solrService;
 
    /**
     * 每间隔10秒输出时间
     * @throws Exception 
     */
//  @Scheduled(fixedRate = 5000)
	@Scheduled(cron="0 0 1 ? * MON")
    public void logTime() throws Exception {
        log.info("定时任务(建立索引库)，现在时间：" + DateUtil.ymdHms.format(new Date()));
        solrService.syncBookinfo2IndexStock();
    }
}
