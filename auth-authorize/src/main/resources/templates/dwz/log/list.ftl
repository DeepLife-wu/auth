<#include "/dwz/common/context.ftl">
<#include "/dwz/common/page_header.ftl">

<form method="post" rel="pagerForm"	action="/dwz/log/list" class="pageForm required-validate" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>开始日期:</label>
				<input type="text" name="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" value="${(condition.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}"/>
				
			</li>
			<li>
				<label>结束日期:</label>
				<input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" value="${(condition.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}"/>
			</li>
		</ul>
		<ul class="searchContent">	
			<li>
				<label>用户账号:</label>
				<input type="text" name="username" value="${(condition.username)!''}"/>
			</li>
			<li>
				<label>用户操作:</label>
				<input type="text" name="operation" value="${(condition.operation)!''}"/>
			</li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >搜索</button></div></div></li>
		</ul>
	</div>
</div>
</form>

<div class="pageContent">
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr class="orgcenter">
				<th>用户名</th>
				<th>用户操作</th>
				<th>执行时长(毫秒)</th>
				<th>IP地址</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<#list page.content as l>
				<tr  target="logId" rel="${l.id}" class="orgcenter">
					<td>${(l.username)!''}</td>
					<td>${(l.operation)!''}</td>
					<td>${(l.time)!''}</td>
					<td>${(l.ip)!''}</td>
					<td>${(l.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	<#include "/dwz/common/page_footer.ftl">
</div>
