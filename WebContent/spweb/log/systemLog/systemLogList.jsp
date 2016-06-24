<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>

<style>
.datagrid-header-rownumber,.datagrid-cell-rownumber {
   width:40px;
 }
</style>

<div id="tb_systemlogList" style="padding: 5px; height: auto">
		<div style="margin-top:3px;">
			<label>操作用户：</label>
			<input type="text" id="loginName" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">&nbsp;&nbsp;
			操作时间：
			<input type="text" id="systemlog_startTime" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px" data-options="editable:false"/>&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;到：
			<input type="text" id="systemlog_endTime" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px" data-options="editable:false"/>&nbsp;&nbsp;
		<a id="noticeListSearchButton" class="easyui-linkbutton" style="margin-left:20px;" onclick="doSystemlogSearch()" data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="doSystemLogClear()" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
		</div>
</div>
<table id="datagrid_systemlogList"  class="datagrid"></table>

<script>
	function doSystemLogClear(){
		$('#systemlog_startTime').datebox("setValue","");
		$('#systemlog_endTime').datebox("setValue","");
		$('#loginName').val("");
	}

	$(function() {
		
		//日期控件
		$('#systemlog_startTime').datebox({required:false});
		$('#systemlog_endTime').datebox({required:false});
		
		var height = $('.indexcenter').height();
		$('#datagrid_systemlogList')
				.datagrid(
						{
							url : '${ctx}/sysActionLog/getSystemLogListPage.do',
							idField : 'actionId',
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
										field : 'actionId',
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
										field : 'actionLogs',
										align : 'center',
										title : '操作说明',
										width : 150
									},
									{
										field : 'loginTime',
										align : 'center',
										title : '操作时间',
										width : 50
									}
									] ],
							onDblClickRow: function(rowIndex, rowData) {
								systemlog_detailFun(rowData.actionId);
							}

						});
		var p = $('#datagrid_systemlogList').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10
			pageList : [ 20, 30, 40, 50, 100 ],//可以设置每页记录条数的列表
			beforePageText : '第',//页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});

	function doSystemlogSearch() {
		var systemlog_startTime = $('#systemlog_startTime').datebox('getValue');
		var systemlog_endTime = $('#systemlog_endTime').datebox('getValue');
		if(systemlog_startTime>systemlog_endTime && systemlog_startTime != '' && systemlog_endTime != ''){
			$.messager.alert('提示','起始日期必须小于截止日期！','info');
			return false;
		}
		 $('#datagrid_systemlogList').datagrid('load',{  
			 loginName: $('#loginName').val(),
			 systemlog_startTime: $('#systemlog_startTime').datebox('getValue'),
			 systemlog_endTime: $('#systemlog_endTime').datebox('getValue'),
		 	 key: 'loginName,systemlog_startTime,systemlog_endTime'
		 });  
		}
		
	
	function systemlog_detailFun(actionId) {
		$('<div></div>').dialog({
			href:'${ctx}/spweb/log/systemLog/systemLogDetail.jsp?actionId='+actionId,
			width:1000,
			height:600,
			modal : true,
			resizable:false,
			title:'操作日志详细',
		    onClose: function() {
		        $(this).dialog('destroy');
		    }

		});
		
	}

</script>
