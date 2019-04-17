<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/user/password/${userForm.id}" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>密码:</label>
				<label>
					<input id="password" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="50" alt="字母、数字、下划线 6-20位"/>
				</label>
			</p>
			
			<p>
				<label>确认密码:</label>
				<label>
					<input type="password" name="repassword" class="required" equalto="#password"/>
				</label>
			</p>
		 </div>
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
