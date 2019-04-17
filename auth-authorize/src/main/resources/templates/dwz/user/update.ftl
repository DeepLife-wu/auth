<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/user/update/${userForm.id}" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>用户中文名称:</label>
				<input type="text" name="name" minlength="2" maxlength="20" class="required" value="${(userForm.name)!''}"/>
			</p>
			
			<p>
				<label>用户账号:</label>
				<label>
					<input type="text" name="username" minlength="6" maxlength="200" class="required" value="${(userForm.username)!''}" readonly="true"/>
				</label>
			</p>
			
			<p>
				<label>用户年龄:</label>
				<label>
					<input type="text" name="age" class="digits" min="1" max="100" class="required" value="${(userForm.age)!''}" />
				</label>
			</p>
			
			<p>
				<label>用户性别:</label>
				<label>
					<select name="gender" class="required">
						<option value="0" <#if (userForm.gender==0)!false>selected="selected"</#if>>男</option>
						<option value="1" <#if (userForm.gender==1)!false>selected="selected"</#if>>女</option>
					</select>
				</label>
			</p>
			
			<p>
				<label>用户生日</label>
				<label>
					<input type="text" id="birthday" name="birthday" class="required date" minDate="1900-01-01" dateFmt="yyyy-M-dd" readonly="true" value="${(userForm.birthday)!''}"/>
				</label>
			</p>
			
			<p>
				<label>用户邮箱</label>
				<label>
					<input type="text" name="email" class="required email" alt="请输入您的电子邮件" value="${(userForm.email)!''}"/>
				</label>
			</p>
			
			<p>
				<label>手机号:</label>
				<label>
					<input type="text" name="phone" class="required phone" alt="请输入您的电话" value="${(userForm.phone)!''}"/>
				</label>
			</p>
			
			<p>
				<label>用户状态:</label>
				<label>
					<select name="status">
						<option value="0" <#if (userForm.status==0)!false>selected="selected"</#if>>锁定</option>
						<option value="1" <#if (userForm.status==1)!false>selected="selected"</#if>>启用</option>
					</select>
				</label>
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
