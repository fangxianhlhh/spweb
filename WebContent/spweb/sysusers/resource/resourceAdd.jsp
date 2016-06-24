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
		$('#parendResId_add').combotree({
			id: 'parendTree',
			editable:false,
			multiple:false,
			onlyLeafCheck:true,
			valueField:'id',    
		    textField:'text',
		    onBeforeLoad: function(data){
		    	$.ajax({
					url: '${ctx}/sysRole/getRoleTreeList.do',
					data: {Timestamp : new Date().getTime()},
					dataType: 'json',
					async: false,
					success: function(data){
						data.unshift({ "text":"请选择","id":""});
			            $("#parendResId_add").combotree('loadData', data);
					}
				});
			},
			onLoadSuccess:function(node,data){
				$('#parendTree.tree-icon').remove();
			},
			onExpand:function(node){
				$('#parendTree.tree-title').removeClass("tree-folder-open");
			}
		}); 
		
		 $('#resOrder').numberbox({    
			    min:1,    
			    precision:0
			});  
	</script>

</head>

<form id="formSysresourceAdd" name="formSysresourceAdd" method="post">
	<table style="padding-top: 20px; padding-left: 20px;">
			<tr>
				<td class="tdstyle">资源名称：</td>
				<td class="td1style">
					<input type="text" id="resName" name="resName" class="easyui-validatebox" 
					data-options="required:true" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px"
					validType="resName['4','15','${ctx}/sysResource/checkValidResName.do']">
				</td>
				<td class="tdstyle">资源类型：</td>
				<td class="td1style">
				<select id="roleId_add" name="resType" class="easyui-combobox" data-options="required:true,editable:false,panelHeight:'auto'" style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px" >
				  <option value="1">菜单</option>
				  <option value="2">按钮</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="tdstyle">资源顺序：</td>
				<td class="td1style">
					<input type="text" id="resOrder" name="resOrder" class="easyui-validatebox" data-options="validType:'length[0,30]'" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 130px">
				</td>
				<td class="tdstyle">父资源：</td>
				<td class="td1style">
					<input type="text" id="parendResId_add" name="parendResId" class="easyui-validatebox" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 180px">
				</td>
			</tr>
			<tr>
				<td class="tdstyle">资源地址：</td>
				<td colspan=3 class="td1style">
					<input type="text" id="resUrl" name="resUrl" class="easyui-validatebox" 
					style="line-height: 20px; height: 20px; border: 1px solid #ccc; width: 330px">
				</td>
			</tr>
			<tr>
				<td class="tdstyle">资源描述：</td>
				<td colspan=3 class="td1style">
					<textarea id="descs" name="remarkDesc" class="easyui-validatebox easyui_form_input" 
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
            resName:{//资源名验证
      				validator:function (value, param) {
      					if (value.length < param[0]) {
      						$.fn.validatebox.defaults.rules.resName.message = '用户名不能小于' + param[0] + '位数！';
      						return false;
      					} 
      					if (value.length > param[1]) {
      						$.fn.validatebox.defaults.rules.resName.message = '用户名不能大于' + param[1] + '位数！';
      						return false;
      					}else {
      							//ajax异步验证用户名是否存在
      						 	var result = $.ajax({
      								url: param[2],
      								data: {resName:value},
      								type: 'post',
      								dataType: 'json',
      								async: false,
      								cache: false
      							}).responseText;
      							if (result == 'false') {
      								$.fn.validatebox.defaults.rules.resName.message = '资源名已存在！';
      								return false;
      							} else {
      								return true;
      							} 
      					}
      				},
      				message: ''
      			}
			}); 
	});
	
</script>
