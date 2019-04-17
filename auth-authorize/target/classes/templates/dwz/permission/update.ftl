<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form action="/dwz/permission/update/${permissionForm.id}" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent" layoutH="57">
			<dl>
				<dt>资源中文名称:</dt>
				<dd>
					<input type="text" name="name" maxlength="20" class="required" value="${(permissionForm.name)!''}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>资源链接:</dt>
				<dt>
					<input type="text" name="url" maxlength="200" class="required" value="${(permissionForm.url)!''}"/>
				</dt>
			</dl>
			
			<dl>
				<dt>资源请求方式:</dt>
				<dd>
					<select name="requestMethod">
						<option value="0" <#if (permissionForm.requestMethod==0)!false>selected="selected"</#if>>GET</option>
						<option value="2" <#if (permissionForm.requestMethod==2)!false>selected="selected"</#if>>POST</option>
					</select>
				</dd>
			</dl>
			
			<dl>
				<dt>资源类型:</dt>
				<dd>
					<select name="type">
						<option value="0" <#if (permissionForm.type==0)!false>selected="selected"</#if>>按钮</option>
						<option value="1" <#if (permissionForm.type==1)!false>selected="selected"</#if>>菜单</option>
					</select>
				</dd>
			</dl>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">更新</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
