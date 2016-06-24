<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>


<script type="text/javascript">

</script>

<form>
	<div style="padding:5px;height:auto">
	</div>
</form>

<table id="datagrid_sysresource"   class="datagrid">
</table>
<!-- <div id="tb_sysresource">
	<div>
		<a href="javascript:void(0);" id="sysresource_add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	</div>
</div> -->

<script>
$(function(){

	var height = $('.indexcenter').height();
	var classId = 'sysresource';
	var urljson = '${ctx}/sysResource/getResourceList.do';
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
				if(row.state =='closed'){
					return '<a href="javascript:void(0);" onclick="sysresource_editFun(\''+row.resId+'\',\''+row.resName+'\')" style="text-decoration: none;">修改</a>&nbsp;&nbsp;';
				}else{
					return '<a href="javascript:void(0);" onclick="sysresource_editFun(\''+row.resId+'\',\''+row.resName+'\')" style="text-decoration: none;">修改</a>&nbsp;&nbsp;'+
					   '<a href="javascript:void(0);" onclick="sysresource_removeFun(\''+row.resId+'\')" style="text-decoration: none;">删除</a>';	
				}
			}
		}
		]],
		toolbar : [{
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				sysresource_appendFun();
			}
		}, '-', {
			text : '展开',
			iconCls : 'icon-redo',
			handler : function() {
				var node = $('#datagrid_'+classId).treegrid('getSelected');
				if (node) {
					$('#datagrid_'+classId).treegrid('expandAll', node.resId);
				}else {
					$('#datagrid_'+classId).treegrid('expandAll');
				}
			}
		}, '-', {
			text : '折叠',
			iconCls : 'icon-undo',
			handler : function() {
				var node = $('#datagrid_'+classId).treegrid('getSelected');
				if (node) {
					$('#datagrid_'+classId).treegrid('collapseAll', node.resId);
				} else {
					$('#datagrid_'+classId).treegrid('collapseAll');
				}
			}
		}, '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$('#datagrid_'+classId).treegrid('reload');
			}
	  }],
	   onBeforeLoad:function(row,param){
		     // 此处就是异步加载地所在
		     if (row){
		      $(this).treegrid('options').url = '${ctx}/sysResource/getResourceList.do?parendResId='+row.resId;
		     }else{
		      $(this).treegrid('options').url = '${ctx}/sysResource/getResourceList.do';
		     }
		 }/* ,
        pagination: true,
        pageSize: 2,
        pageList: [2,10,20] */
	});
	
});

//添加资源
function sysresource_appendFun(){
	var url = '${ctx}/sysResource/sysResourceAdd.do';
	var href = '${ctx}/spweb/sysusers/resource/resourceAdd.jsp';
	
	$('#datagrid_sysresource').treegrid('uncheckAll').treegrid('unselectAll').treegrid('clearSelections');
	$('<div id="dialog_sysresource_add"></div>').dialog({
	href:href,
	width:590,
	height:390,
	modal : true,
	resizable:true,
	title:'添加系统资源',
	buttons : [{
		text : '确认',
		handler : function() {
			var d = $('#dialog_sysresource_add').closest('.window-body');
			$('#formSysresourceAdd').form('submit', {
			url : url,
			success : function(result) {
					var r = $.parseJSON(result);
					if (r.success) {
						$('#datagrid_sysresource').treegrid('load');
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
			$("#dialog_sysresource_add").dialog('destroy');
		}
	} ],
	onClose : function() {
		$(this).dialog('destroy');
	}
	});
};

//修改资源信息
function sysresource_editFun(resId,resName){
	urlBefor = '${ctx}/spweb/sysusers/resource/resourceEdit.jsp';
	urlEnd = '${ctx}/sysResource/updateSysResource.do';
	href = urlBefor + '?resId=' + resId+'&resName='+resName;		

	title = '修改资源信息';
	$('#datagrid_sysresource').treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	$('<div id="dialog_sysResource_edit"></div>').dialog(
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
						var d = $('#dialog_sysResource_edit').closest('.window-body');
						$('#formSysresourceEdit').form('submit',
								{
									url : urlEnd,
									onSubmit:function(){
									},
									success : function(result) {
										var r = $.parseJSON(result);
										if (r.success) {
											$('#datagrid_sysresource').treegrid('load');
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
						$("#dialog_sysResource_edit").dialog('destroy');
					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
					$('#formSysresourceEdit').form(
							'load',
							'${ctx}/sysResource/sysResourceDetail.do?resId='+ resId + '&t=' + (new Date()).valueOf()
							);
					$('#formSysresourceEdit').form({
						onLoadSuccess:function(){
							
						}
					});
				} 
			});
};

//删除资源
function sysresource_removeFun(resId){
	$.messager.confirm('确认',
		'您是否要删除当前选中的条目？',
		function(r){
			if (r) {
				$.ajax({
					url : '${ctx}/sysResource/sysResourceDelete.do',
					data : {
						resId : resId
					},
					dataType : 'json',
					success : function(result) {
						if (result.success) {
				    		$('#datagrid_sysresource').treegrid('load');
							$('#datagrid_sysresource').treegrid('uncheckAll')
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





</script>