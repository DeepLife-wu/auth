<#include "/dwz/common/context.ftl">

<div class="pageContent">
	<form id="permissionForm" action="/dwz/user/grant/${userForm.id}" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="100">
			<fieldset>
				<div class="row">
					<div class="col-md-5 col-sm-12">
						<div class="panel" defH="250">
							<h1>未授权资源</h1>
							<div>
								<select id="unRoleList" multiple="multiple" class="col-md-12 col-sm-12" style="height: 250px;">
									<#list unRoleList as unRole>
										<option ondblclick="listToList('unRoleList','hasRole',false)" value="${unRole.id }">${unRole.name}</option>
									</#list>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-2 col-sm-12">
						<div style="height: 250px; padding-top: 40%; padding-left: 20%;">
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="listToList('unRoleList','hasRole',false)">选中添加&gt;&gt;</button>
								</div>
							</div>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="listToList('unRoleList','hasRole',true)">添加全部&gt;&gt;</button>
								</div>
							</div>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="listToList('hasRole','unRoleList',false)">选中取消&lt;&lt;</button>
								</div>
							</div>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="listToList('hasRole','unRoleList',true)">取消全部&lt;&lt;</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5 col-sm-12">
						<div class="panel" defH="250">
							<h1>已授权资源</h1>
							<div>
								<select name="hasRole" id="hasRole" multiple="multiple" class="col-md-12 col-sm-12" style="height: 250px;">
									<#list hasRole as has>
										<option ondblclick="listToList('hasRole','unRoleList',false)" value="${has.id }">${has.name}</option>
									</#list>
								</select>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
		 
		 
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="formSubmit();">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">
	function formSubmit() {
		$("#hasRole").find("option").each(function() {
			this.selected = true;
		});
	}

	/** 
	fromid:源list的id. 
	toid:目标list的id. 
	isAll参数(true或者false):是否全部移动或添加 
	 */
	function listToList(fromid, toid, isAll) {
		if (isAll == true) { //全部移动 
			$("#" + fromid + " option").each(function() {
				$("<option ondblclick=listToList('" + toid + "','" + fromid + "',false) value='" + this.value + "' >" + this.text + "</option>").appendTo(
					$("#" + toid + ":not(:has(option[value=" + $(this).val() + "]))"));
			});
			$("#" + fromid).empty(); //清空源list
		} else if (isAll == false) {
			$("#" + fromid + " option:selected").each(function() {
				//将源list中的option添加到目标list,当目标list中已有该option时不做任何操作.
				$("<option ondblclick=listToList('" + toid + "','" + fromid + "',false) value='" + this.value + "' >" + this.text + "</option>").appendTo(
					$("#" + toid + ":not(:has(option[value=" + $(this).val() + "]))"));
				//目标list中已经存在的option并没有移动,仍旧在源list中,将其清空.
				if ($("#" + fromid + " option[value='" + $(this).val() + "']").length > 0)
					$("#" + fromid + " option[value='" + $(this).val() + "']").remove();
			});
		}
	}
</script>
