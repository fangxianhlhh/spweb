<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>
<script type="text/javascript" src="${ctx}/js/resources/role/role.js"></script>
<script type="text/javascript" src="${ctx}/js/resources/inf.js"></script>
<style>
a{
	text-decoration: none;
}
.datagrid-header-rownumber,.datagrid-cell-rownumber {
   width:40px;
 }
 .fs_13{
 	font-size:13px;
 }
 .tree_div{
 margin-left: 10px; 
 overflow: auto; 
 width: 308px; 
 padding-top: 10px; 
 height: 304px !important;
 height: 314px;
 }
 .tree_bg_div{
 width:320px;
 height: 316px !important;
 height:317px;
 margin-left:15px;
 margin-top:5px;
 padding-top:1px;
 background: url('${ctx}/images/u135.png') no-repeat;
 }
</style>

<script>
function doSysRoleSearch(){
	$('#datagrid_sysRole').datagrid('clearSelections');
    $('#datagrid_sysRole').datagrid('load',{ 
    	roleName: $.trim($("#role_roleName").val())
    });  
}
	
//清空
/* function doClear() {
	$('#role_roleName').val("");
}	 */
</script>

<form>
	<div  style="padding:5px;height:auto">
			角色名： <input type="text" id="role_roleName" name="roleName" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 120px">&nbsp;&nbsp;
			<a onclick="doSysRoleSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
	<div style="display:none;">
		<!-- 新增窗口 -->
		<div id="add_dialog_sysRole">
			<div>
				<div style="margin-left:14px;margin-top:10px;font-size:13px;height:24px;line-height: 24px;">
					角色名：
					<input id="add_sysRoleName" name="roleName" type="text" style="margin-bottom: 2px;" autocomplete="off" maxlength="10" />
				</div>
				<div>
					<div style="width: 200px; height: 20px; margin-left: 25px; margin-top: 10px;">
						
						<span class="fs_13" style="float: left; width: 60px; height: 20px; line-height: 25px;margin-left:5px;">权限分配</span>
					</div>
					<div class="tree_bg_div">
						<div class="tree_div">
							<ul id="add_tree_sysRole"></ul>
						</div>
					</div>
				</div>
				<div style="margin-top:10px;text-align: center">
					<a href="javascript:void(0);" id="addSureBtn_sysRole">确定</a>&nbsp;&nbsp;
					<a href="javascript:void(0);" id="addCancelBtn_sysRole">取消</a>
				</div>
			</div>
		</div>
		
		<!-- 编辑窗口 -->
		<div id="edit_dialog_sysRole">
			<div>
				<div style="margin-left:14px;margin-top:10px;font-size:13px;height:24px;line-height: 24px;">
					角色名：<input id="edit_sysRoleName" name="roleName" type="text" style="margin-bottom: 2px;" autocomplete="off" maxlength="10" />
							<input id="edit_sysRoleId" type="hidden" />
				</div>
				<div>
					<div style="width: 200px; height: 20px; margin-left: 25px; margin-top: 10px;">
						
						<span class="fs_13" style="float: left; width: 60px; height: 20px; line-height: 25px;margin-left:5px;">权限分配</span>
					</div>
					<div class="tree_bg_div">
						<div class="tree_div">
							<ul id="edit_tree_sysRole"></ul>
						</div>
					</div>
				</div>
				<div style="margin-top:10px;text-align: center">
					<a href="javascript:void(0);" id="editSureBtn_sysRole">确定</a>&nbsp;&nbsp;
					<a href="javascript:void(0);" id="editCancelBtn_sysRole">取消</a>
				</div>
			</div>
		</div>
		
		<!-- 查看详情窗口 -->
		<div id="detail_dialog_sysRole">
			<div>
				<div style="margin-left:14px;margin-top:10px;font-size:13px;height:24px;line-height: 24px;">
					角色名：<input id="detail_sysRoleName" name="roleName" type="text" style="margin-bottom: 2px; " autocomplete="off" maxlength="10" readonly/>
							<input id="detail_sysRoleId" type="hidden" />
				</div>
				<div>
					<div style="width: 200px; height: 20px; margin-left: 25px; margin-top: 10px;">
						
						<span class="fs_13" style="float: left; width: 60px; height: 20px; line-height: 25px;margin-left:5px;">权限分配</span>
					</div>
					<div class="tree_bg_div">
						<div class="tree_div">
							<ul id="detail_tree_sysRole"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
</form>

<table id="datagrid_sysRole" toolbar="#tb_sysRole" class="datagrid"></table>
<div  id="tb_sysRole" >
	<div>
		<a href="javascript:void(0);" id="addBtn_sysRole">添加</a>
		<!-- 
		<a href="javascript:void(0);" id="editBtn_role">编辑</a>
		<a href="javascript:void(0);" id="deleteBtn_role">删除</a>
		 -->
		&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</div>
