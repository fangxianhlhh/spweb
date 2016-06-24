<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<!-- 禁止浏览器从本地缓存中调阅页面。-->
<meta http-equiv="pragram" content="no-cache">
<!-- 网页不保存在缓存中，每次访问都刷新页面。-->
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
<title>XXX后台管理平台</title>

<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/themes/gray/easyui.css" id="easyuiTheme"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/themes/icon.css"></link>

<link rel="stylesheet" type="text/css" href="${ctx}/css/css.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/main/common.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/main/portal.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/js/uploadify_3.2/uploadify.css"></link>
<script type="text/javascript" src="${ctx}/js/jquery-2.0.3.min.js"></script>

<!-- 百度编辑器 -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor1_4_3/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
 
 <!-- easyui -->
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/main/jquery.easyui.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/main/jeasyui.common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- 图片上传工具 -->
<script type="text/javascript" src="${ctx}/js/uploadify_3.2/jquery.uploadify.js"></script>

<script type="text/javascript" src="${ctx}/js/common.js"></script>

</head>

<body class="easyui-layout">
	<noscript>
		<div style="position: absolute; z-index: 100000; height: 246px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="${ctx}/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #fff; padding: 0px; background-image: url('${ctx}/images/top-right.gif');">
		<div class="site_title" style="color: #FFF">XXX后台管理平台</div>
		<div id="sessionInfoDiv"
			style="position: absolute; right: 5px; top: 10px; color: #FFF;">
			[<strong id="name_tag">${sessionScope.userSessionInfo.userName}</strong>],欢迎你！
		</div>
		
		 <div style="position: absolute; right: 10px; bottom: 0px;">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-tip'">个人中心</a>
			<a href="javascript:void(0);" style="text-decoration:none;" onclick="logOut()">退出系统</a>
		</div> 
		
	 	<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	 		<c:if test="${sessionScope.userSessionInfo.isAdmin != true}">
				<div onclick="index_changeUserInfo('${ctx}');">个人信息</div>
			</c:if>
			<div onclick="index_changePwd('${ctx}');">修改密码</div>
			<!-- <div onclick="logOut()">退出系统</div> -->
		</div>
	</div>
	
	<div data-options="region:'west',split:true,title:'平台概况'" style="width: 218px; overflow-x:hidden" id="nav">
		<div data-options="region:'center'"style="width:214px;margin-top:2px;height:400px" id="menu">
			<div id="menu_div" class="easyui-accordion sider" data-options="fit:true,border:false">
				<c:forEach var="menu" items="${result.resList}" >
					<div title="${menu.resName }" data-options="iconCls:'icon-mini-add'" style="padding: 10px;">
						<ul class="easyui-tree" data-options="animate:true">
							<c:forEach var="s" items="${menu.resChildren }">
								<li><a href="javascript:void(0);" cmshref="${ctx}${s.resUrl}" type="user_list" rel="" >${s.resName }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
				<!--waiceng-->
				<!--//左侧菜单导航-->
			</div>
		</div>
		<!--accordion-->
	</div>
	
	<!--west-->
	<div data-options="region:'south',border:false" style="height: 50px; background: #fff;">
		<div id="footer">
			Copyright &copy; 2016 by XXXX企业发展有限公司.<br> All Rights Reserved<br>
		</div>
	</div>
	
	<!--//主体内容部分-->
	<div data-options="region:'center'" class="indexcenter"  title="欢迎使用XXX后台管理平台">
		<div id="tabs_index" class="easyui-tabs" fit="true" border="false">
<!-- 			<div title="首页" style="overflow-x: hidden;" -->
<!-- 				data-options="href:'index/main_index.jsp'"> -->
<!-- 			</div> -->
		</div>
	</div>
	
	<!--center-->
	<!--//主体内容部分-->
	<div id="dialog_cms" data-options="iconCls:'icon-save'"></div>
</body>
<!-- 触发事件 -->
<script>
	//点击首页日期控件的事件触发
/* 	$('#index_Calendar').calendar({
		onSelect : function(date) {
			
			$('<div id="tianqi" style="left:66; top:66"></div>').dialog({
				href:'${ctx}/views/system/tianqi.jsp',
				width:286,
				height:138,
				title:'天气情况',
				modal : true,
				onClose : function() {
					$(this).dialog('destroy');
				}
				});
		}
	});
	 */
	 
	 // 判断对某个资源是否拥有权限
	 function checkPrivilege(resID) {
		 var flag = false;
		 $.each(eval("("+'${result.resList}'+")"), function(i,val) {
			 if (val == resID) {
		    	  flag = true;
		    	  // 跳出循环体
		    	  return false;
		     }
		 });
		 
		 return flag;
	 }
	
</script>

</html>
