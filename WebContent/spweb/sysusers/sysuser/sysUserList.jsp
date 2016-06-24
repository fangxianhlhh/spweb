<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>

	<script type="text/javascript">
		//角色权限
		$('#roleId').combobox({
			required: true,
			valueField:'roleId',    
		    textField:'roleName',
		    onBeforeLoad: function(data){
		    	$.ajax({
					url: '${ctx}/sysRole/getSysRole.do',
					data: {Timestamp : new Date().getTime()},
					dataType: 'json',
					success: function(data){
						data.unshift({ "roleName":"全部","roleId":""});
			            $("#roleId").combobox('loadData', data);
					}
				});
			}
		}); 
	</script>

<form>
	<div style="padding:5px;height:auto">
		<div>
			用&nbsp;户&nbsp;名&nbsp;： <input type="text" id="loginName" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 160px">&nbsp;&nbsp;
			角色权限：<input id="roleId" name="roleId" data-options="required:true,editable:false">&nbsp;&nbsp;
			创建时间：<input type="text" id="createTime_s" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"/>&nbsp;-&nbsp;
			<input type="text" id="createTime_e" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"/>&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="doUserSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</form>
<table id="datagrid_sysuser" toolbar="#tb_sysuser" class="datagrid">
</table>
<div id="tb_sysuser">
	<div>
		<a href="javascript:void(0);" id="sysuser_add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	</div>
</div>
<script>
/**
 * 获取日期格式
 * @method getDateString
 * @param date 日期对象
 * @return 日期格式
 */
function getDateString(date) {
	var y = date.getFullYear();  
	var m = date.getMonth() + 1;  
	var d = date.getDate();  
	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);  
}

// 当月第一天
var date = new Date();
date.setDate(1);
var currMonthFirstDay = getDateString(date);

// 当月当天
var date = new Date();
var currMonthDay = getDateString(date);
	
//提交查询条件
function doUserSearch(){  
	var createTime_s = $('#createTime_s').datebox('getValue');
	var createTime_e = $('#createTime_e').datebox('getValue');
	if(createTime_e != ''){
		if(createTime_s > createTime_e){
			$.messager.alert('提示','起始日期必须小于截止日期','info');
			return false;
		}
	}
    $('#datagrid_sysuser').datagrid('load',{  
    	loginName:$('#loginName').val(),
    	roleId: $('#roleId').combobox('getValue'),
    	createTime_s: $('#createTime_s').datebox('getValue'),
    	createTime_e: $('#createTime_e').datebox('getValue'),
        key:'loginName,roleId,createTime_s,createTime_e'
    });
}  	
	
//清空
function doClear() {
	$('#loginName').val("");
	$('#createTime_s').datebox("setValue", '');
	$('#createTime_e').datebox("setValue", '');
	$('#roleId').combobox('setValue', '全部');
}	
	

$(function(){
	//日期控件
	$('#createTime_s').datebox({required:false});
	$('#createTime_e').datebox({required:false});
	
	
	var height = $('.indexcenter').height();
	var classId = 'sysuser';
	var urljson = '${ctx}/sysUser/getSysUserListPage.do';
	$('#datagrid_'+classId).datagrid({
		url:urljson,
		idField:'userId',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		checkbox:true,
		height:height-65,
		singleSelect:true,
		columns:[[  
		{
			field:'userId',
			title:'ID',
			hidden:true
		},{
			field:'loginName',
			title:'登录名',
			align:'left',
			width:60
		},{
			field:'userName',
			title:'姓名',
			align:'left',
			width:80
		},{
			field:'roleName',
			title:'角色权限',
			align:'left',
			width:70
		},{
			field:'phone',
			title:'联系电话',
			align:'left',
			width:70
		},{
			field:'createTime',
			title:'创建时间',
			align:'left',
			width:80
		},{
			field:'lastLoginTime',
			title:'最后登录时间',
			align:'left',
			width:80
		},{
			field:'action',
			title:'编辑',
			align:'center',
			width:80,
			formatter : function(value, row, index) {
				return '<a href="javascript:void(0);" onclick="sysuser_pwdFun(\''+row.userId+'\',\''+row.loginName+'\')" style="text-decoration: none;">重置密码</a>&nbsp;&nbsp;'+
				   '<a href="javascript:void(0);" onclick="sysuser_editFun(\''+row.userId+'\',\''+row.roleName+'\')" style="text-decoration: none;">修改</a>&nbsp;&nbsp;'+
				   '<a href="javascript:void(0);" onclick="sysuser_removeFun(\''+row.userId+'\')" style="text-decoration: none;">删除</a>';
			}
		}
		]],
		onDblClickRow: function(rowIndex, rowData) {
			sysuser_editFun(rowData.userId);
		}
	});
	var p = $('#datagrid_'+classId).datagrid('getPager');
	
	$(p).pagination({
		pageSize: 20,//每页显示的记录条数，默认为10
		pageList: [20,30,40,50,100],//可以设置每页记录条数的列表
		beforePageText: '第',//页数文本框前显示的汉字
		afterPageText: '页    共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$('#sysuser_add').click(function() {
		var submitUrl = '${ctx}/sysUser/sysUserAdd.do';
		var toAddUrl = '${ctx}/spweb/sysusers/sysuser/sysUserAdd.jsp';
		sysuser_addFun(toAddUrl, submitUrl);
	});
// 	$('#user_del').click(function() {
// 		user_removeFun();
// 	});
});

/**
 * 添加人员
 */
function sysuser_addFun(href,url) {
	$('#datagrid_sysuser').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	$('<div id="dialog_sysuser_add"></div>').dialog({
	href:href,
	width:590,
	height:390,
	modal : true,
	resizable:true,
	//collapsible:true,
	//maximizable:true,
	title:'添加系统管理用户',
	buttons : [ {
		text : '确认',
		handler : function() {
			var d = $('#dialog_sysuser_add').closest('.window-body');
			$('#formSysUser').form('submit', {
			url : url,
			success : function(result) {
					var r = $.parseJSON(result);
					if (r.success) {
						$('#datagrid_sysuser').datagrid('load');
						d.dialog('destroy');
					} 
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
			}
			});
		}
	},{
		text:'关闭',
		handler:function() {
			$("#dialog_sysuser_add").dialog('destroy');
		}
	} ],
	onClose : function() {
		$(this).dialog('destroy');
	}
	});
}

/**
 * 删除人员
 */
function sysuser_removeFun(userId) {
		$.messager
				.confirm(
						'确认',
						'您是否要删除当前选中的条目？',
						function(r) {
							if (r) {
								$.ajax({
											url : '${ctx}/sysUser/sysUserDelete.do',
											data : {
												userId : userId
											},
											dataType : 'json',
											success : function(result) {
												if (result.success) {
													$('#datagrid_sysuser').datagrid('load');
													$('#datagrid_sysuser')
															.datagrid('uncheckAll')
															.datagrid('unselectAll')
															.datagrid('clearSelections');
												}
												$.messager.show({
													title : '提示',
													msg : result.msg
												});
											}
										});
							}
						});
}

	/**
	 * 显示修改用户信息对话框
	 */
	function sysuser_editFun(userId,roleName) {
		urlBefor = '${ctx}/spweb/sysusers/sysuser/sysUserEdit.jsp';
		urlEnd = '${ctx}/sysUser/updateSysUser.do';
		href = urlBefor + '?userId=' + userId+'&roleName='+roleName;		
	
		title = '修改管理员信息';
		$('#datagrid_sysuser').datagrid('uncheckAll')
				.datagrid('unselectAll').datagrid('clearSelections');
		$('<div id="dialog_sysuser_edit"></div>').dialog(
				{
					href : href,
					width : 590,
					height : 390,
					modal : true,
					resizable : true,
					//collapsible : true,
					//maximizable : true,
					title : title,
					buttons : [ {
						text : '确定',
						//iconCls : 'icon-edit',
						handler : function() {
							var d = $('#dialog_sysuser_edit').closest('.window-body');
							$('#formSysUserEdit').form('submit',
									{
										url : urlEnd,
										onSubmit:function(){
											var roleId=$("#roleId").val();
											if(roleId ==null || roleId ==''){
												$.messager.show({
													title : '提示',
													msg : '用户角色不能为空！'
												});
												return;
											}
										},
										success : function(result) {
											var r = $.parseJSON(result);
											if (r.success) {
												$('#datagrid_sysuser').datagrid('load');
												d.dialog('destroy');
											}
											$.messager.show({
												title : '提示',
												msg : r.msg
											});
										}
									});
							}
					},{
						text:'关闭',
						handler:function() {
							$("#dialog_sysuser_edit").dialog('destroy');
						}
					} ],
					onClose : function() {
						$(this).dialog('destroy');
					},
					onLoad : function() {
						$('#formSysUserEdit').form(
								'load',
								'${ctx}/sysUser/sysUserDetail.do?userId='+ userId + '&t=' + (new Date()).valueOf()
								);
						$('#formSysUserEdit').form({
							onLoadSuccess:function(){
								var roleIds=$("#roleIds").val();
								var roles=roleIds.split(',');
								for(var i=0;i<roles.length;i++){
									if(roles[i] !=null && roles[i] !=''){
										 $("#roleId_ed").combobox('select', roles[i]);
									}
								}
							}
						});
					} 
				});
	}
	
	
	/**
	 * 修改密码
	 */
	function sysuser_pwdFun(userId,loginName) {
		urlBefor = '${ctx}/spweb/sysusers/sysuser/changePwd.jsp';
		urlEnd = '${ctx}/sysUser/updateSysUser.do';
		href = urlBefor + '?userId=' + userId+'&loginName='+loginName;
		title = '重置密码';
		$('#datagrid_sysuser').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div id="dialog_sysuser_pwd"></div>').dialog(
				{
					href : href,
					width : 320,
					height : 200,
					modal : true,
					resizable : true,
					//collapsible : true,
					//maximizable : true,
					title : title,
					buttons : [ {
						text : '确定',
						//iconCls : 'icon-edit',
						handler : function() {
							var d = $('#dialog_sysuser_pwd').closest('.window-body');
							$('#formSysUserPwd').form('submit',
									{
										url : urlEnd,
										success : function(result) {
											var r = $.parseJSON(result);
											if (r.success) {
												$('#datagrid_sysuser').datagrid('load');
												d.dialog('destroy');
											}
											$.messager.show({
												title : '提示',
												msg : r.msg
											});
										}
									});
						}
					},{
						text:'关闭',
						handler:function() {
							$("#dialog_sysuser_pwd").dialog('destroy');
						}
					}  ],
					onClose : function() {
						$(this).dialog('destroy');
					},
					/* onLoad : function() {

						$('#formSysUserPwd').form('load',
								'${ctx}/smw/user/userDetail.do?userId='
										+ userId + '&t=' + (new Date()).valueOf());
					} */
				});
	}

</script>