<#include "/dwz/common/context.ftl">
<#include "/dwz/common/page_header.ftl">

<form method="post" rel="pagerForm"	action="/dwz/role/list" class="pageForm required-validate" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>角色码:</label>
				<input type="text" name="code" value="${(condition.code)!''}"/>
			</li>
			<li>
				<label>角色中文名称:</label>
				<input type="text" name="name" value="${(condition.name)!''}"/>
			</li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >搜索</button></div></div></li>
		</ul>
	</div>
</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<@security.authorize access='@rbacService.hasPermission("/dwz/role/add", authentication)'>
			<li>
				<a class="add" href="/dwz/role/add" target="dialog" mask="true"><span>添加</span></a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/role/update", authentication)'>
			<li>
				<a class="edit" href="/dwz/role/update/{roleId}" target="dialog" mask="true" warn="请选择角色"><span>修改</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/role/delete", authentication)'>
			<li>
				<a class="delete" href="/dwz/role/delete/{roleId}" target="ajaxTodo" warn="请选择角色" title="你确定删除吗?"><span>删除</span> </a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/role/grant", authentication)'>
			<li>
				<a class="edit" href="/dwz/role/grant/{roleId}" target="dialog" width="1000" height="470" mask="true" warn="请选择角色"><span>授权</span></a>
			</li>
			</@security.authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr class="orgcenter">
				<th>角色名称</th>
				<th>角色码</th>
				<th>创建时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
			<#list page.content as r>
				<tr  target="roleId" rel="${r.id}" class="orgcenter">
					<td>${(r.name)!''}</td>
					<td>${(r.code)!''}</td>
					<td>${(r.createTime)!''}</td>
					<td>${(r.updateTime)!''}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	<#include "/dwz/common/page_footer.ftl">
</div>
