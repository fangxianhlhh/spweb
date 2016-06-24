<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>


<head>
	<style>
	.tdstyle{
		text-align:right;
		padding-top: 10px;
		padding-left: 30px;
	}
	.tdstyle1{
		text-align:left;
		padding-top: 10px;
	}
	
	</style>
	<script type="text/javascript">
	</script>
</head>

<form id="formSysUserPwd" name="formSysUserPwd" method="post">
	<input type="hidden" id="userId_pw" name="userId" value="${param.userId }">
	<table>
			<tr>
				<td class ="tdstyle">用户名：</td>
					<td class ="tdstyle1" >
					<input  type="text" id="LoginName_pw" name="loginName" value="${param.loginName}" readonly/>
				   </td>
			</tr>
			<tr>
				<td class ="tdstyle">新密码：</td>
				<td class ="tdstyle1">
					<input type="password" id="newPwd" name="newPwd" class="easyui-validatebox" data-options="required:true,validType:['pwd','length[6,32]']" style="width: 130px;">
				</td>
			</tr>
			<tr>
				<td class ="tdstyle">确认密码：</td>
				<td class ="tdstyle1">
					<input type="password" id="pwd" name="pwd" class="easyui-validatebox" data-options="required:true,validType:'equalTo[\'#newPwd\']',invalidMessage:'两次输入密码不匹配'" style="width: 130px;" value=''>
				</td>
			</tr>
	</table>
</form>
<script>
	$(function(){
		$.extend($.fn.validatebox.defaults.rules, { 
			equalTo: {
		        validator:function(value,param){
		            return $(param[0]).val() == value;
		        },
		        message:'两次密码不一致'
		    },
		    pwd:{
	        	 validator: function (value){
	        		 var valid= /^[0-9a-zA-Z]*$/g;  
	        	     return (valid.test(value));
	                },
	                message: '只能输入数字或字母'
	         	},
			}); 

	});
</script>