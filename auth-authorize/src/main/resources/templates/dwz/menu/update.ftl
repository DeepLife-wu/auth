<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form action="/dwz/menu/update/${menuForm.id}" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent" layoutH="57">
			<dl>
				<dt>菜单名称:</dt>
				<dd>
					<input type="text" name="name" maxlength="20" class="required" value="${(menuForm.name)!''}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>优先级:</dt>
				<dt>
					<input type="text" name="sort" maxlength="20" class="required digits" value="${(menuForm.sort)!''}"/>
				</dt>
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
