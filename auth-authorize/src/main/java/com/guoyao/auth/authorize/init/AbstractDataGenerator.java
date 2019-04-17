/**
 * 
 */
package com.guoyao.auth.authorize.init;

import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月24日:下午2:25:46】
 */
@Slf4j
public abstract class AbstractDataGenerator implements DataGenerator {

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.init.DataGenerator#init()
	 */
	@Transactional
	@Override
	public void execute() throws Exception {
		if(isNeed()) {
			log.info("使用" + getClass().getSimpleName() + "初始化数据");
			init();
			log.info("使用" + getClass().getSimpleName() + "初始化数据完毕");
		}
	}
	
	/**
	 * <pre>实际的数据初始化逻辑</pre>
	 * @throws Exception
	 */
	protected abstract void init() throws Exception;

	/**
	 * <pre>判断指定的表是否需要初始化(表中有数据则不需要)</pre>
	 * @return
	 */
	protected abstract boolean isNeed();
}
