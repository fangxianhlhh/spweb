<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>

<style>
.datagrid-header-rownumber,.datagrid-cell-rownumber {
   width:40px;
 }
</style>

<div id="tb_logLists" style="padding: 5px; height: auto">
		<div style="margin-top:3px;">
			<label>操作用户：</label>
			<input type="text" id="loginName" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">&nbsp;&nbsp;
			创建时间：
			<input type="text" id="loginlog_startTime" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px" data-options="editable:false"/>&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;到：
			<input type="text" id="loginlog_endTime" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px" data-options="editable:false"/>&nbsp;&nbsp;
		<a id="noticeListSearchButtons" class="easyui-linkbutton" style="margin-left:20px;" onclick="doLoginlogSearch()" data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="doLoginLogClear()" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
		</div>
</div>
<table id="datagrid_loginlogList"  class="datagrid"></table>

<script>
	function doLoginLogClear(){
		$('#loginlog_startTime').datebox("setValue","");
		$('#loginlog_endTime').datebox("setValue","");
		$('#unames').val("");
	}

	$(function() {
		
		//日期控件
		$('#loginlog_startTime').datebox({required:false});
		$('#loginlog_endTime').datebox({required:false});
		
		var height = $('.indexcenter').height();
		$('#datagrid_loginlogList')
				.datagrid(
						{
							url : '${ctx}/sysLoginLog/getLoginLogListPage.do',
							idField : 'lid',
							pagination : true,
							/* pageSize : 20,
							pageList : [ 20, 30, 40, 50, 100 ], */
							rownumbers : true,
							fitColumns : true,
							checkbox : true,
							height : height - 115,
							singleSelect:true,
							columns : [ [
									{
										field : 'lid',
										align : 'left',
										title : '',
										hidden : true
									},
									{
										field : 'loginName',
										align : 'center',
										title : '操作用户',
										width : 20
									},
									{
										field : 'loginIp',
										align : 'center',
										title : 'ip地址',
										width : 50
									},
									{
										field : 'loginTime',
										align : 'center',
										title : '登录时间',
										width : 50
									}
									] ],
							onDblClickRow: function(rowIndex, rowData) {
								loginlog_detailFun(rowData.lid);
							}

						});
		var p = $('#datagrid_loginlogList').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10
			pageList : [ 20, 30, 40, 50, 100 ],//可以设置每页记录条数的列表
			beforePageText : '第',//页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});
	
	function doLoginlogSearch() {
		var loginlog_startTime = $('#loginlog_startTime').datebox('getValue');
		var loginlog_endTime = $('#loginlog_endTime').datebox('getValue');
		if(loginlog_startTime>loginlog_endTime && loginlog_startTime != '' && loginlog_endTime != ''){
			$.messager.alert('提示','起始日期必须小于截止日期！','info');
			return false;
		}
		 $('#datagrid_loginlogList').datagrid('load',{  
			 loginName: $('#loginName').val(),
			 loginlog_startTime: $('#loginlog_startTime').datebox('getValue'),
			 loginlog_endTime: $('#loginlog_endTime').datebox('getValue'),
		 	 key: 'loginName,loginlog_startTime,loginlog_endTime'
		 });  
		}
		
	
	function loginlog_detailFun(lid) {
		$('<div></div>').dialog({
			href:'${ctx}/spweb/log/loginLog/loginLogDetail.jsp?lid='+lid,
			width:1000,
			height:600,
			modal : true,
			resizable:false,
			title:'登录日志详细',
		    onClose: function() {
		        $(this).dialog('destroy');
		    }

		});
		
	}

</script>
