<#include "/dwz/common/context.ftl">
<#include "/dwz/common/page_header.ftl">

<form method="post" rel="pagerForm"	action="/dwz/menu/list" class="pageForm required-validate" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>菜单名称:</label>
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
			<@security.authorize access='@rbacService.hasPermission("/dwz/menu/add", authentication)'>
			<li>
				<a class="add" href="/dwz/menu/add" target="dialog" mask="true"><span>添加</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/menu/update", authentication)'>
			<li>
				<a class="edit" href="/dwz/menu/update/{mid}" target="dialog" mask="true" warn="请选择菜单"><span>修改</span></a>
			</li>
			</@security.authorize>
		
			<@security.authorize access='@rbacService.hasPermission("/dwz/menu/delete", authentication)'>
			<li>
				<a class="delete" href="/dwz/menu/delete/{mid}" target="ajaxTodo" warn="请选择菜单" title="你确定删除吗?"><span>删除</span> </a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/menu/child/add", authentication)'>
			<li>
				<a class="add" href="/dwz/menu/child/add/{mid}" target="dialog" mask="true" warn="请选择菜单"><span>添加子菜单</span></a>
			</li>
			</@security.authorize>
			
			<@security.authorize access='@rbacService.hasPermission("/dwz/menu/child/list", authentication)'>
			<li>
				<a class="edit" href="/dwz/menu/child/list?parentId={mid}" rel="childTabId"  target="navTab" warn="请选择菜单"><span>查看子菜单</span></a>
			</li>
			</@security.authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr class="orgcenter">
				<th>菜单名称</th>
				<th>优先级</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<#list page.content as m>
				<tr  target="mid" rel="${m.id}" class="orgcenter">
					<td>${(m.name)!''}</td>
					<td>${(m.sort)!''}</td>
					<td>${(m.createTime)!''}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	<#include "/dwz/common/page_footer.ftl">
</div>
