/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.freemark;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guoyao.auth.authorize.web.consts.AppConstants;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.query.LogQueryCondition;
import com.guoyao.auth.authorize.web.form.LogForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:27:53】
 */
@Slf4j
@Controller
@RequestMapping("/dwz/log")
public class LogController extends AbstractController {
	/*<!-- <td>${(l.method)!''}</td> -->
	<!-- <td>${(l.params?eval)!''}</td> -->*/
	@RequestMapping("/list")
	public String list(String tabId,@Valid LogQueryCondition condition, BindingResult errors, 
			@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model) {
		if(StringUtils.isNotBlank(tabId)) super.tabId = tabId;
        List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<LogForm> page = logService.findByCondition(condition,pageable);
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
		return "/dwz/log/list";
	}
}
