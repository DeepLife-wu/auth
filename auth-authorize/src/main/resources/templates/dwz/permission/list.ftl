<#include "/dwz/common/context.ftl">
<#include "/dwz/common/page_header.ftl">

<form method="post" rel="pagerForm"	action="/dwz/permission/list" class="pageForm required-validate" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>资源中文名称:</label>
				<input type="text" name="name" value="${(condition.name)!''}"/>
			</li>
			<li>
				<label>资源url:</label>
				<input type="text" name="url" value="${(condition.url)!''}"/>
			</li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >搜索</button></div></div></li>
		</ul>
	</div>
</div>
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<@security.authorize access='@rbacService.hasPermission("/dwz/permission/add", authentication)'>
			<li>
				<a class="add" href="/dwz/permission/add" target="dialog" mask="true"><span>添加</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/permission/update", authentication)'>
			<li>
				<a class="edit" href="/dwz/permission/update/{permissionId}" target="dialog" mask="true" warn="请选择资源"><span>修改</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/permission/delete", authentication)'>
			<li>
				<a class="delete" href="/dwz/permission/delete/{permissionId}" target="ajaxTodo" warn="请选择资源" title="你确定删除吗?"><span>删除</span> </a>
			</li>
			</@security.authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr class="orgcenter">
				<th>资源名称</th>
				<th>资源链接</th>
				<th>资源请求方式</th>
				<th>资源类型</th>
				<th>创建时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
			<#list page.content as p>
				<tr  target="permissionId" rel="${p.id}" class="orgcenter">
					<td>${(p.name)!''}</td>
					<td>${(p.url)!''}</td>
					<td>${(p.getHttpMethodEnum().message)!''}</td>
					<td>${(p.getLinkTypeEnum().message)!''}</td>
					<td>${(p.createTime)!''}</td>
					<td>${(p.updateTime)!''}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	<#include "/dwz/common/page_footer.ftl">
</div>
