/**
 * 
 */
package com.guoyao.auth.authorize.web.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;
import com.guoyao.auth.authorize.annotation.SysLog;
import com.guoyao.auth.authorize.exception.DwzDataException;
import com.guoyao.auth.authorize.model.GuoyaoLog;
import com.guoyao.auth.authorize.service.LogService;
import com.guoyao.auth.authorize.web.dwz.DwzCode;
import com.guoyao.auth.authorize.web.util.IpUtil;
import com.guoyao.auth.authorize.web.util.RequestHolder;
import com.guoyao.auth.authorize.web.util.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2018年12月13日:下午2:34:57】
 */
@Slf4j
@Aspect
@Component
public class ApplicationAspect {
	/** 拦截 dwz freemark下的控制器,对请求参数进行校验*/
	private static final String AUTHORIZE_USER_CONTROLLER_ASPECT = "execution(* com.guoyao.auth.authorize.web.controller.freemark.*.*(..))";
	
	@Around(AUTHORIZE_USER_CONTROLLER_ASPECT)
	public Object handleUserControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
//		log.info(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
		Object[] args = pjp.getArgs();
		StringBuffer requestUrl = RequestHolder.request().getRequestURL();
		String requestMethod = RequestHolder.request().getMethod();
		log.info("客户端请求链接:{} 客户端请求方式:{} 客户端请求参数:{}",requestUrl,requestMethod,args);
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult errors = (BindingResult) arg;
				if(errors.hasErrors()) {
					throw new DwzDataException(DwzCode.ERROR.getCode(),errors.getFieldError().getDefaultMessage());
				}
			}
		}
		Object result = pjp.proceed();
		return result;
	}
	
	/** 拦截作用系统日志注解的方法进行日志记录*/
	private static final String AUTHORIZE_LOG_ASPECT = "@annotation(com.guoyao.auth.authorize.annotation.SysLog)";
	@Autowired
	private LogService logService;
	
	@Around(AUTHORIZE_LOG_ASPECT)
	public Object handleSysLog(ProceedingJoinPoint point) throws Throwable { 
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveSysLog(point, time);
		return result;
	}
	
	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		GuoyaoLog sysLog = new GuoyaoLog();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String argList = "";
			
			if(ArrayUtils.isNotEmpty(args))
				if(args.length >= 3) 
				{
					for(int i = 0;i < 2; i++) {
						argList += JSONObject.toJSONString(args[i]) + ";";
					}
				} 
				else
				{
					argList = JSONObject.toJSONString(args[0]);
				}
			String params = JSONObject.toJSONString(argList);
			sysLog.setParams(params);
		}catch (Exception e){
			log.error(e.getStackTrace().toString());
		}

		//获取request
		HttpServletRequest request = RequestHolder.request();
		//设置IP地址
		sysLog.setIp(IpUtil.getIpAddr(request));
		//用户名
		String username = RequestHolder.getCurrentUser().getUsername();
		sysLog.setUsername(username);
		sysLog.setTime(time);
		sysLog.setCreateTime(new Date());
		//保存系统日志
		logService.save(sysLog);
	}
}
