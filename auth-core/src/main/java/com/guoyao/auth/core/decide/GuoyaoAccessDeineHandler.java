/**
 * 
 */
package com.guoyao.auth.core.decide;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wuchao
 * @Date 【2019年2月15日:上午11:02:13】
 */
public class GuoyaoAccessDeineHandler implements AccessDeniedHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "权限不足请联系管理员！");
		map.put("code", "403");
		
		if(request.getRequestURI().indexOf("dwz") > 0) {
			map.put("statusCode", "300");
		}
		response.getWriter().print(JSONObject.toJSON(map));
	}
}
