<#include "/dwz/common/context.ftl">
<#include "/dwz/common/page_header.ftl">

<form method="post" rel="pagerForm"	action="/dwz/user/list" class="pageForm required-validate" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>账号:</label>
				<input type="text" name="username" value="${(condition.username)!''}"/>
			</li>
			<li>
				<label>邮箱:</label>
				<input type="text" name="email" value="${(condition.email)!''}"/>
			</li>
			<li>
				<label>手机号:</label>
				<input type="text" name="phone" value="${(condition.phone)!''}"/>
			</li>
		</ul>
		<ul class="searchContent">
		 <li>
			<label>状态:</label>
			<select name="status" >
	        	<option value="">请选择</option>
	       		<option value="0" <#if (condition.status==0)!false>selected="selected"</#if> >锁定</option>
	       		<option value="1" <#if (condition.status==1)!false>selected="selected"</#if> >启用</option>
	        </select>
		 </li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >搜索</button></div></div></li>
		</ul>
	</div>
</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<@security.authorize access='@rbacService.hasPermission("/dwz/user/add", authentication)'>
			<li>
				<a class="add" href="/dwz/user/add" target="dialog" mask="true" height="420"><span>添加</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/user/update", authentication)'>
			<li>
				<a class="edit" href="/dwz/user/update/{userId}" target="dialog" mask="true" warn="请选择用户" height="420"><span>修改</span></a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/user/delete", authentication)'>
			<li>
				<a class="delete" href="/dwz/user/delete/{userId}" target="ajaxTodo" warn="请选择用户" title="你确定删除吗?"><span>删除</span> </a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/user/grant", authentication)'>
			<li>
				<a class="edit" href="/dwz/user/grant/{userId}" target="dialog" width="1000" height="470" mask="true" warn="请选择用户"><span>授权</span></a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/user/password", authentication)'>
			<li>
				<a class="edit" href="/dwz/user/password/{userId}" target="dialog" mask="true" warn="请选择用户"><span>修改密码</span> </a>
			</li>
			</@security.authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr class="orgcenter">
				<th>名称</th>
				<th>账号</th>
				<th>年龄</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>邮箱</th>
				<th>手机号</th>
				<th>状态</th>
				<th>操作人</th>
				<th>操作人IP</th>
				<th>最后一次登录时间</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>登录次数</th>
			</tr>
		</thead>
		<tbody>
			<#list page.content as u>
				<tr  target="userId" rel="${u.id}" class="orgcenter">
					<td>${u.name!''}</td>
					<td>${u.username!''}</td>
					<td>${u.age!''}</td>
					<td>${u.getGenderEnum().message}</td>
					<td>${u.birthday!''}</td>
					<td>${u.email!''}</td>
					<td>${u.phone!''}</td>
					<td>${u.getUserStatusEnum().message}</td>
					<td>${u.operator!''}</td>
					<td>${u.operateIp!''}</td>
					<td>${u.lastLoginTime!''}</td>
					<td>${u.createTime!''}</td>
					<td>${u.updateTime!''}</td>
					<td>${u.loginCount!''}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	<#include "/dwz/common/page_footer.ftl">
</div>
