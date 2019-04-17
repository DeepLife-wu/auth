<#include "/dwz/common/context.ftl">
<div class="pageContent">
	<form id="addChildMenuForm" action="/dwz/menu/add" method="POST" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		 <table class="table" width="100%" layoutH="60">
			<thead>
				<tr>
					<th><input type="checkbox" class="checkboxCtrl" group="id" /> </th>
					<th>菜单名称</th>
				</tr>
			</thead>
			<tbody>
				<#list permissionList as p>
					<tr target="permission_id" rel="${p.id }">
						<td><input type="checkbox" name="id" id="id" value="${p.id }" /></td>
						<td>${p.name }</td>
					</tr>
				</#list>
			</tbody>
		</table>
		 
		 <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="submitAddMenu();">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function submitAddMenu(){
		var id = document.getElementsByName("id");
		var ids = "";
		for( var i=0;i<id.length;i++ ){
			if( id[i].checked ){
				ids += id[i].value + ",";				
			}
		}
		
		if(ids.length == 0 ){
			alertMsg.error("请至少选择一个资源");
			return;
		}
		
		ids = ids.substring(0,ids.length - 1);
		$.ajax( {   
		     type : "POST",   
		     url : "/dwz/menu/child/add/${parentId}",   
		     dataType: "json", 
		     data : { "ids" : ids},
		     success : function(json) {   
		    	 dialogAjaxDone(json); 
		     } 
		 });
	}
</script>