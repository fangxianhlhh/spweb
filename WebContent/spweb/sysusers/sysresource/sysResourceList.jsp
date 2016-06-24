<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>


<script type="text/javascript">

</script>

<form>
	<div style="padding:5px;height:auto">
		<div>
			资&nbsp;源&nbsp;名&nbsp;： <input type="text" id="resName" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 160px">&nbsp;&nbsp;
			创建时间：<input type="text" id="createTime_s" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"/>&nbsp;-&nbsp;
			<input type="text" id="createTime_e" size="20"
			style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"/>&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="doUserSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</form>

<table id="datagrid_sysresource" toolbar="#tb_sysresource" class="datagrid">
</table>
<div id="tb_sysresource">
	<div>
		<a href="javascript:void(0);" id="sysresource_add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	</div>
</div>

<script>
$(function(){

	//日期控件
	$('#createTime_s').datebox({required:false});
	$('#createTime_e').datebox({required:false});
	
	
	var height = $('.indexcenter').height();
	var classId = 'sysresource';
	var urljson = '${ctx}/sysResource/getResourceTreeList.do';
	
	$('#datagrid_'+classId).treegrid({
		url:urljson,
		idField:'resId',
		treeField:'resName',
		animate:true, 
	//	pagination:true,
		rownumbers:true,
		fitColumns:true,
		checkbox:true,
		height:height-65,
	// singleSelect:true, //只允许选择一行
		columns:[[  
		{
			field:'resId',
			title:'ID',
			hidden:true
		},{
			field:'resName',
			title:'资源名称',
			align:'left',
			width:150
		},{
			field:'resType',
			title:'资源类型',
			align:'left',
			width:30,
			formatter : function(value, row, index) {
				if(Number(row.resType) == 1){
					return "菜单";
				}else if(Number(row.resType) == 2){
					return "按钮";
				}
			}
		},{
			field:'resUrl',
			title:'资源地址',
			align:'left',
			width:170
		},{
			field:'resLevel',
			title:'资源深度',
			align:'left',
			width:30
		},{
			field:'resOrder',
			title:'资源顺序',
			align:'left',
			width:30
		},{
			field:'remarkDesc',
			title:'资源描述',
			align:'left',
			width:80
		},{
			field:'action',
			title:'操作',
			align:'center',
			width:80,
			formatter : function(value, row, index) {
				return '<a href="javascript:void(0);" onclick="sysuser_editFun(\''+row.userId+'\',\''+row.roleName+'\')" style="text-decoration: none;">修改</a>&nbsp;&nbsp;'+
				   '<a href="javascript:void(0);" onclick="sysuser_removeFun(\''+row.userId+'\')" style="text-decoration: none;">删除</a>';
			}
		}
		]],
		onDblClickRow: function(rowIndex, rowData) {
			sysuser_editFun(rowData.userId);
		},
        pagination: true,
        pageSize: 2,
        pageList: [2,10,20]
	});
	
});

</script>