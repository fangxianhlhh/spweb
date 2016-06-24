<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>

	<script type="text/javascript">
		//角色权限下拉列表
		$('#roleId_ed').combobox({
			editable:false,
			multiple:true,
			valueField:'roleId',    
		    textField:'roleName',
		    onBeforeLoad: function(data){
		    	$.ajax({
					url: '${ctx}/sysRole/getSysRole.do',
					data: {Timestamp : new Date().getTime()},
					dataType: 'json',
					success: function(data){
			            $("#roleId_ed").combobox('loadData', data);
					}
				});
			},
			onSelect :function(record){
				if(record.roleId == null || record.roleId==""){
					 $("#roleId_ed").combobox('unselect', record.roleId);
				}
			}
		}); 
	</script>
	
	
<head>
	<style>
	.tdstyle{
		padding-top: 5px;
		text-align:right;
		padding-left: 10px;
	}
	.td1style{
		padding-top: 5px;
		text-align:left;
		padding-left: 10px;
	}
	</style>
	<script type="text/javascript">
	</script>

</head>

<form id="formSysUserEdit" name="formSysUserEdit" method="post">
	<input type="hidden" id="userId" name="userId">
	<input type="hidden" id="roleIds" name="roleIds">
	<table style="padding-top: 20px; padding-left: 20px;">
			<tr>
				<td class="tdstyle">用户名：</td>
				<td  class="td1style">
				 <input  type="text" id="loginName" name="loginName"  readonly/>
				</td>
				<td class="tdstyle">姓名：</td>
				<td class="td1style">
					<input type="text" id="userName" name="userName" class="easyui-validatebox"  data-options="validType:'length[0,30]'"  
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 132px">
				</td>
			</tr>
			<tr>
				<td class="tdstyle">角色权限：</td>
				<td class="td1style">
					<input id="roleId_ed" name="roleId" validType="role" style="width:132px;line-height: 20px; height: 20px; border: 1px solid #ccc; ">&nbsp;&nbsp;
				</td>
				<td class="tdstyle">联系电话：</td>
				<td class="td1style"><input type="text" id="phone" name="phone" class="easyui-validatebox" data-options="validType:'phone'" 
				style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 132px"></td>
			</tr>
			<tr>
				<td class="tdstyle">联系邮箱：</td>
				<td class="td1style">
					<input type="text" id="email" name="email" class="easyui-validatebox" data-options="validType:'email'" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
				
			</tr>
			<tr>
				<td class="tdstyle">备注：</td>
				<td colspan=3 class="td1style">
					<textarea id="descs" name="descs" class="easyui-validatebox easyui_form_input" 
					style="width: 400px;height:80px;border: 1px solid #ccc;" data-options="validType:'length[0,500]'"></textarea>
				</td>
			</tr>
	</table>
</form>

<script>
	$(function(){
// 		var roleStr = $('#roleId').combobox('getText');
		$.extend($.fn.validatebox.defaults.rules, { 
			 phone: {// 验证联系电话
	                validator: function (value) {
	                    return /^1(3|4|5|8)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	                },
	                message: '电话号码格式不正确，请使用下面格式:13865263836或者:020-88888888'
	            },
	         email:{
	            	validator:function(value){
	            		 var part=/^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$/;
	            		return part.test(value);
	            	},
	            	message:'邮箱格式不正确'
	            },
             role:{
            	validator: function (value) {
//             		if($('#roleId').combobox('getText') != null && $('#roleId').combobox('getText') != ''){
//             			if(roleStr == '企业' && value !='企业'){
//             				$.fn.validatebox.defaults.rules.loginName.message = '角色不可变更！';
//             				return false;
//                 		}
//             		}
            		if(value!='请选择'){
            			return true;
            		}
            	},
            	message: '请选择角色权限'
            }
		}); 

	});
</script>
