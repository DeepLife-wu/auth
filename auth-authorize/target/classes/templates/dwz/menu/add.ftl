<#include "/dwz/common/context.ftl">
<div class="pageContent">
	<form id="permissionForm" action="/dwz/menu/add" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="57">
			<p>
				<label>菜单名称:</label>
				<input type="text" name="name" maxlength="20" class="required" />
			</p>
			<p>
				<label>菜单优先级:</label>
				<label>
					<input type="text" name="sort" maxlength="200" class="required digits" />
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
