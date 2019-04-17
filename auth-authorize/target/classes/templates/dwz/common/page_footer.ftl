<#assign targetType="${(param.targetType)!'navTab'}">

<div class="panelBar">
	<div class="pages">
		<span>每页</span>
		<select name="numPerPage" onchange="dwzPageBreak({targetType:'${targetType}',data:{numPerPage:this.value}})">
			<#list [10, 20, 30, 40, 50] as s>
			   <option value="${s}" <#if page.size == s>selected="selected"</#if> >${s}</option>
			</#list>
		</select>

		<span>条，共${page.totalElements}条</span>
	</div>
	<div class="pagination" targetType="${targetType }" totalCount="${page.totalElements}" numPerPage="${page.size }" pageNumShown="5" currentPage="${page.number + 1}">
	</div>
</div>

























