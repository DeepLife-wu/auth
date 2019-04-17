/**
 * 
 */
package com.guoyao.auth.authorize.init;

/**<pre>数据生成器，设计此接口的目的是封装系统数据的初始化行为，开发人员可以向系统中添加此接口的实现，来增加自定义的数据初始化行为.</pre>
 * @author wuchao
 * @Date 【2019年1月24日:下午2:23:18】
 */
public interface DataGenerator {

	/**
	 * <pre>初始化器的执行顺序，数值越大的初始化器越靠后执行</pre>
	 * @return
	 */
	Integer getIndex();
	
	/**
	 * <pre>执行数据生成器</pre>
	 * @throws Exception
	 */
	void execute() throws Exception;
}
