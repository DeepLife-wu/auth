<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/user/add" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>用户中文名称:</label>
				<input type="text" name="name" minlength="2" maxlength="20" class="required" />
			</p>
			
			<p>
				<label>用户账号:</label>
				<label>
					<input type="text" name="username" minlength="6" maxlength="200" class="required" />
				</label>
			</p>
			
			<p>
				<label>用户年龄:</label>
				<label>
					<input type="text" name="age" class="digits" min="1" max="100" class="required" />
				</label>
			</p>
			
			<p>
				<label>用户性别:</label>
				<label>
					<select name="gender" class="required">
						<option value="0" selected="selected">男</option>
						<option value="1" >女</option>
					</select>
				</label>
			</p>
			
			<p>
				<label>用户生日</label>
				<label>
					<input type="text" name="birthday" class="required date" minDate="1900-01-01" dateFmt="yyyy-M-dd" readonly="true"/>
				</label>
			</p>
			
			<p>
				<label>用户邮箱</label>
				<label>
					<input type="text" name="email" class="required email" alt="请输入您的电子邮件"/>
				</label>
			</p>
			
			<p>
				<label>手机号:</label>
				<label>
					<input type="text" name="phone" class="required phone" alt="请输入您的电话" />
				</label>
			</p>
			
			<p>
				<label>用户状态:</label>
				<label>
					<select name="status" class="required">
						<option value="0">锁定</option>
						<option value="1" selected="selected">启用</option>
					</select>
				</label>
			</p>
			
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
				<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
