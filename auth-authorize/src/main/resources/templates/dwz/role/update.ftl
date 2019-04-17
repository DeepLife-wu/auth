<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/role/update/${roleForm.id}" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>角色码:</label>
				<input type="text" name="code" maxlength="20" class="required" value="${(roleForm.code)!''}"/>
			</p>
			
			<p>
				<label>角色中文名称:</label>
				<label>
					<input type="text" name="name" maxlength="200" class="required" value="${(roleForm.name)!''}"/>
				</label>
			</p>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">更新</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
