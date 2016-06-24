<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>


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
		//角色权限下拉列表
		$('#roleId_add').combobox({
			editable:false,
			multiple:true,
			valueField:'roleId',    
		    textField:'roleName',
		    onBeforeLoad: function(data){
		    	$.ajax({
					url: '${ctx}/sysRole/getSysRole.do',
					data: {Timestamp : new Date().getTime()},
					dataType: 'json',
					async: false,
					success: function(data){
						data.unshift({ "roleName":"请选择","roleId":""});
			            $("#roleId_add").combobox('loadData', data);
					}
				});
			},
			onSelect :function(record){
				if(record.roleId == null || record.roleId==""){
					 $("#roleId_add").combobox('unselect', record.roleId);
				}
			}
		}); 
	</script>

</head>

<form id="formSysUser" name="formSysUser" method="post">
	<table style="padding-top: 20px; padding-left: 20px;">
			<tr>
				<td class="tdstyle">登录名：</td>
				<td class="td1style">
					<input type="text" id="loginName" name="loginName" class="easyui-validatebox" 
					data-options="required:true" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"
					validType="loginName['4','15','${ctx}/sysUser/checkValidLoginName.do']">
				</td>
				<td class="tdstyle">角色权限：</td>
				<td class="td1style">
					<input id="roleId_add" name="roleId" validType="role" style="width:130px;line-height: 20px; height: 20px; border: 1px solid #ccc; " >&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td class="tdstyle">密码：</td>
				<td class="td1style">
					<input type="password" id="password" name="password" class="easyui-validatebox" 
					data-options="required:true,validType:['pwd','length[6,32]']" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
				<td class="tdstyle">确认密码：</td>
				<td class="td1style">
					<input type="password" id="rePwd" name="rePwd" class="easyui-validatebox" 
					data-options="required:true,validType:['repwd','length[6,32]']" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
			</tr>
			<tr>
				<td class="tdstyle">姓名：</td>
				<td class="td1style">
					<input type="text" id="userName" name="userName" class="easyui-validatebox" data-options="validType:'length[0,30]'" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
				<td class="tdstyle">联系电话：</td>
				<td class="td1style">
					<input type="text" id="phone" name="phone" class="easyui-validatebox" 
					data-options="validType:'phone'" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
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
					style="width: 400px;height:80px;border: 1px solid #ccc; " data-options="validType:'length[0,200]'"></textarea>
				</td>
			</tr>
	</table>
</form>

<script>

	$(function(){
		$.extend($.fn.validatebox.defaults.rules, { 
			 phone: {// 验证联系电话
	                validator: function (value) {
	                    return /^1(3|4|5|8)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	                },
	                message: '电话号码格式不正确，请使用下面格式:13865263836或者:020-88888888'
	            },
	         pwd:{
	        	 validator: function (value){
	        		 var valid= /^[0-9a-zA-Z]*$/g;  
	        	     return (valid.test(value));
	                },
	                message: '只能输入数字或字母'
	         	},
	         repwd:{
		        	 validator: function (value){
		        		 var valid= $("#rePwd").val();  
		        	     return valid == value;
		                },
		                message: '两次输入密码不一致'
		         	},
			role:{
            	validator: function (value) {
            		if(value!='请选择'){
            			return true;
            		}
            	},
            	message: '请选择角色权限'
            },
            email:{
            	validator:function(value){
            		 var part=/^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,5}$/;
            		return part.test(value);
            	},
            	message:'邮箱格式不正确'
            },
//             enterprise:{
//             	validator: function (value) {
//             		if($('#roleID').combobox("getText") == '企业'){
//             			if(value!='请选择'){
//                 			return true;
//                 		}
//             		}else{
//             			return true;
//             		}
//             	},
//             	message: '请选择所在企业'
//             },
            loginName:{//用户登录名的验证
      				validator:function (value, param) {
      					if (value.length < param[0]) {
      						$.fn.validatebox.defaults.rules.loginName.message = '用户名不能小于' + param[0] + '位数！';
      						return false;
      					} 
      					if (value.length > param[1]) {
      						$.fn.validatebox.defaults.rules.loginName.message = '用户名不能大于' + param[1] + '位数！';
      						return false;
      					}else {
      						if (!/^(?!_)[\w]+$/.test(value)) {
      							$.fn.validatebox.defaults.rules.loginName.message = '用户名只能英文字母、数字及下划线的组合，并且首位不能为下划线！';
      							return false;
      						} else {
      							//ajax异步验证用户名是否存在
      						 	var result = $.ajax({
      								url: param[2],
      								data: {loginName:value},
      								type: 'post',
      								dataType: 'json',
      								async: false,
      								cache: false
      							}).responseText;
      							if (result == 'false') {
      								$.fn.validatebox.defaults.rules.loginName.message = '用户名已存在！';
      								return false;
      							} else {
      								return true;
      							} 
      						}
      					}
      				},
      				message: ''
      			}
			}); 
	});
	
	
	/* $("#roleID").change(function(){
		var role =$("#roleID").combobox("getValue");
		alert(role);
		if(role =='客服'){
			$("#comp").hide();
		}
	}); */
	function company(){
		var role =$("#roleID").combobox("getText");
		if(role=='客服电话'){
			$("#comp").hide();
		}
	}
	
</script>
