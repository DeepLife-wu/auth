<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/permission/add" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>资源中文名称:</label>
				<input type="text" name="name" maxlength="20" class="required" />
			</p>
			
			<p>
				<label>资源链接:</label>
				<label>
					<input type="text" name="url" maxlength="200" class="required" />
				</label>
			</p>
			
			<p>
				<label>资源请求方式:</label>
				<select name="requestMethod">
					<option value="0" selected="selected">GET</option>
					<option value="2">POST</option>
				</select>
			</p>
			
			<p>
				<label>资源类型:</label>
				<select name="type">
					<option value="0" selected="selected">按钮</option>
					<option value="1">菜单</option>
				</select>
			</p>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
