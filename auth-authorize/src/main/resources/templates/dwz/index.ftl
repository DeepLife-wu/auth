<#include "/dwz/common/context.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- css && js link -->
<link href="/dwzui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/dwzui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/dwzui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<!--[if IE]>
<link href="/dwzui/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="/dwzui/js/speedup.js" type="text/javascript"></script><script src="/dwzui/js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="/dwzui/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="/dwzui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/dwzui/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="/dwzui/js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="/dwzui/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="/dwzui/js/dwz.min.js" type="text/javascript"></script>
<script src="/dwzui/js/dwz.regional.zh.js" type="text/javascript"></script>

<title>${titleName}</title>
<script type="text/javascript">
$(function(){
	DWZ.init("/dwzui/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/dwzui/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>

</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">
					<li><font color="red">${(guoyaoAccount.username)!''}</font></li>
					<li><font color="white" style="font-weight: bold; font-size: 4">欢迎你!&nbsp;&nbsp;${(guoyaoAccount.name)!''}</font></li>
					<li><a href="/dwz/user/password/${guoyaoAccount.id}" target="dialog" mask="true"><span>修改密码</span></a></li>
					<li><a href="/logout">注销</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">blue</div></li>
					<li theme="green"><div>green</div></li>
					<li theme="purple"><div>purple</div></li>
					<li theme="silver"><div>silver</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>${titleName}</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
				  <div class="accordionHeader">
					<h2><span>Folder</span>${titleName }</h2>
				  </div>
				  <div class="accordionContent">
						<ul class="tree treeFolder">
							<#list menuList as menu>
								<#if menu.childrenList?size gt 0>
									<li>
									  <a href="javascript:;">${menu.name }</a>
										  <ul>
											<#list menu.childrenList as childMenu>
												<li><a href="${childMenu.menuUrl }?tabId=${childMenu.id }" target="navTab" rel="${childMenu.id}">${childMenu.name }</a></li>
											</#list>
										 </ul>
									</li>
								</#if>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main">
								<a href="javascript:void(0)">
									<span><span class="home_icon">我的主页</span></span>
								</a>
							</li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<!--<div class="tabsMore">more</div>-->
				</div>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div>
						<div class="accountInfo">
							<div style="width: 98%; margin-top: 10px; text-align: center; font-size: 25px;">欢迎来到国药${titleName}</div>
						</div>
						<div class="pageFormContent" layoutH="75">
							<table width="100%" border="1px">
								<thead>
									<tr style="height: 30px;">
										<th width="4%">版本号</th>
										<th width="75%">版本记录</th>
										<th width="10%">版本开发人员</th>
										<th width="11%">更新时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>001</td>
										<td></td>
										<td>吴超</td>
										<td>2019-01-25</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>