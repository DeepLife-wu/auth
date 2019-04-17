///**
// * 
// */
//package com.guoyao.auth.authorize.web.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.alibaba.fastjson.JSONObject;
//import com.guoyao.auth.authorize.web.form.PermissionForm;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author wuchao
// * @Date 【2019年1月17日:下午2:41:38】
// */
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PermissionControllerTest {
//	
//	@Autowired
//	private WebApplicationContext wac;
//	private MockMvc mockMvc;
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	}
//	
//	/**
//	 * Test method for {@link com.guoyao.auth.authorize.web.controller.PermissionController#create(com.guoyao.auth.authorize.web.form.PermissionForm)}.
//	 */
//	@Test
//	public void testCreate() throws Exception {
//		PermissionForm pf = new PermissionForm(1L, "添加", "/permission", 2, 0, new Date(), new Date());
//		String content = JSONObject.toJSON(pf).toString();
//		log.info(content);
//		String result = mockMvc.perform(post("/permission").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content(content))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.id").value("1"))
//		.andReturn().getResponse().getContentAsString();
//		log.info(result);
//	}
//	
//	@Test
//	public void testUpdate() throws Exception {
//		PermissionForm pf = new PermissionForm(1L, "更新", "/permission", 3, 0, new Date(), new Date());
//		String content = JSONObject.toJSON(pf).toString();
//		log.info(content);
//		String reuslt = mockMvc.perform(put("/permission/1").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content(content))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
//				.andReturn().getResponse().getContentAsString();
//		log.info(reuslt);
//	}
//	
//	@Test
//	public void testDelete() throws Exception {
//		mockMvc.perform(delete("/permission/1")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk());
//	}
//	
//	@Test
//	public void testQuery() throws Exception {
//		String result = mockMvc.perform(
//				get("/permission").param("name", "测试").param("url", "/permission")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
//				.andReturn().getResponse().getContentAsString();
//		log.info(result);
//	}
//	
//	@Test
//	public void testGetInfo() throws Exception {
//		String result = mockMvc.perform(get("/permission/1")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk())
//				.andReturn().getResponse().getContentAsString();
//		log.info(result);
//	}
//}
