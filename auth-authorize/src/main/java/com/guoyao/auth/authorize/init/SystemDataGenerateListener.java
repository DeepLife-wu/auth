/**
 * 
 */
package com.guoyao.auth.authorize.init;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.guoyao.auth.authorize.init.DataGenerator;

import lombok.extern.slf4j.Slf4j;

/**<pre>系统初始化器</pre>
 * @author wuchao
 * @Date 【2019年1月25日:下午2:12:36】
 */
@Slf4j
@Component
public class SystemDataGenerateListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private List<DataGenerator> dataGeneratorList;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(CollectionUtils.isNotEmpty(dataGeneratorList)) {
			dataGeneratorList.sort((initor1,initor2)->{
				return initor1.getIndex().compareTo(initor2.getIndex());
			});
			
			dataGeneratorList.stream().forEach(generator->{
				try {
					generator.execute();
				} catch (Exception e) {
					log.info("系统数据初始化失败(" + generator.getClass().getSimpleName() + ")",e);
				}
			});
		}
	}
}
