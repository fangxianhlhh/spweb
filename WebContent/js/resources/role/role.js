var I_role = {
	validTypes : ["notNull"],	
	//角色列表展示
	init:function(){
		var height = $('.indexcenter').height();
		//var classId = 'role';
		var urljson = '../sysRole/getSysRoleList.do';
		$('#datagrid_sysRole').datagrid({
			url:urljson,
			idField:'roleId',
			pagination:true,
			rownumbers:true,
			fitColumns:true,
			height:height-65,
			singleSelect:true,
			columns:[[  
				{
					field:'roleId',
					title:'ID',
					hidden:true
				},{
					field:'roleName',
					title:'名称',
					align:'left',
					width:100
				}
				,{
					field:'createUserName',
					title:'创建人',
					align:'center',
					width:60
				}
				,{
					field:'createTime',
					title:'创建时间',
					align:'center',
					width:100
				}
				,{
					field:'updateUserName',
					title:'更新人',
					align:'center',
					width:60
				}
				,{
					field:'updateTime',
					title:'更新时间',
					align:'center',
					width:100
				}
				,{
					field:'action',
					title:'操作',
					align:'center',
					width:120,
					formatter : function(value, row, index) {
						return "<a href='#' onclick='I_role.editBtnAction(\""+row.roleId+"\",\""+row.roleName+"\")'>修改</a>&nbsp;&nbsp;"+
							   "<a href='#' onclick='I_role.deleteBtnAction(\""+row.roleId+"\")'>删除</a>&nbsp;&nbsp;"+
							   "<a href='#' onclick='I_role.detailBtnAction(\""+row.roleId+"\",\""+row.roleName+"\")'>查看详情</a>&nbsp;&nbsp;" ;
					}
				}
			]],
//			frozenColumns:[[{
//				field:'ck',
//				checkbox:true
//			}]],
			pagination:true,
			pageSize:20,
			pageList: [20,30,40,50,100],
			onDblClickRow:function(rowIndex,rowData){
				var roleId = rowData.roleId;
				var roleName = rowData.roleName;
				I_role.detailBtnAction(roleId,roleName);
			}
		});
		
		//按键功能
		// 新增按钮
		$('#addBtn_sysRole').linkbutton({
			iconCls: 'icon-add',
			text : '添加',
			plain:true
		}).unbind().bind("click",I_role.addBtnAction);
		
		//编辑（修改）按钮
		$('#editBtn_sysRole').linkbutton({
			iconCls: 'icon-edit',
			text : '编辑',
			plain:true
		}).unbind().bind("click",I_role.editBtnAction);
		
		// 删除按钮
		$('#deleteBtn_sysRole').linkbutton({
			iconCls: 'icon-remove',
			text : '删除',
			plain:true
		}).unbind().bind("click",I_role.deleteBtnAction);
		
		//查看详情按钮
		$('#detailBtn_sysRole').linkbutton({
			iconCls: 'icon-detail',
			text : '查看详情',
			plain:true
		}).unbind().bind('click',I_role.detailBtnAction);
		
		// 新增 确认按钮
		$('#addSureBtn_sysRole').linkbutton({
			text : '确定'
		}).unbind().bind("click",I_role.addSureBtnAction);
		
		//新增取消按钮
		$('#addCancelBtn_sysRole').linkbutton({
			text : '取消'
		}).unbind().bind("click",I_role.addCancelBtnAction);
		
		//编辑确认按钮
		$('#editSureBtn_sysRole').linkbutton({
			text : '确定'
		}).unbind().bind("click",I_role.editSureBtnAction);
		
		//编辑取消按钮
		$('#editCancelBtn_sysRole').linkbutton({
			text : '取消'
		}).unbind().bind("click",I_role.editCancelBtnAction);
		
		//跳出窗口
		//新增角色窗口
		$('#add_dialog_sysRole').dialog({
			title: '新建角色',
			width: 365,
			height: 470,
			closed: true,
			cache: false,
			modal: true
		});
		
		//编辑角色窗口
		$('#edit_dialog_sysRole').dialog({
			title: '编辑角色',
			width: 365,
			height: 470,
			closed: true,
			cache: false,
			modal: true
		});
		//查看详情窗口
		$('#detail_dialog_sysRole').dialog({
			title: '查看角色',
			width: 365,
			height: 470,
			closed: true,
			cache: false,
			modal: true
			
		});
		
		//添加角色-树形权限菜单
		$("#add_tree_sysRole").tree({
			url:"../sysRole/getRoleTreeList.do",
			checkbox:true,
			onlyLeafCheck:false,
			cascadeCheck:true,
			onLoadSuccess:function(node, data){
				//$(".tree-icon").remove();
			}
		});
		
		//修改角色-树形权限菜单
		$("#edit_tree_sysRole").tree({
			url:"../sysRole/getRoleTreeList.do",
			checkbox:true,
			onlyLeafCheck:false,
			cascadeCheck:true,
			onLoadSuccess:function(node, data){
				//$(".tree-icon").remove();
			}
		//,
			/*onCheck: function(node,checked ){
				$(this).tree("cascadCheckOwn", {
					isChecked:checked,
					id:node.id,
					target:node.target,
					deepCascade:true //是否级联选择
						
				});
			}*/
		});
		
		//查看角色-树形权限菜单
		$("#detail_tree_sysRole").tree({
			url:"../sysRole/getRoleTreeList.do",
			checkbox:true,
			onlyLeafCheck:false,
			cascadeCheck:true,
			onLoadSuccess:function(node, data){
				
			}
		});
		
		//input  validatebox
		$("#add_sysRoleName").validatebox({
	        required: true,
	        validType:"checkExist['../sysRole/checkExist.do']"
	    });
		$("#edit_sysRoleName").validatebox({
	        required: true
	    });
	},
	
	//点击新增按钮功能
	addBtnAction:function(){
		$("#add_sysRoleName").unbind().bind("keyup",function(){
			var b = $.trim($("#add_sysRoleName").val());
			setTimeout(function(){
				var n = $.trim($("#add_sysRoleName").val());
				if(b==n){
					 window.d = $.post("../sysRole/checkExist.do",{"newValue":n},function(data){
						 if(data.result == true){
							 inf.checkExistTrue("add_sysRoleName","角色名");
						 }else{
							 inf.checkExistFalse("add_sysRoleName",I_role.validTypes);
						 }
					 },"json");		
				}
			}, 300);
		});
		//清空
		$("#add_sysRoleName").val("");
		inf.checkExistFalse("add_sysRoleName",I_role.validTypes);
		var allChecked = $("#add_tree_sysRole").tree("getChecked");
		for(var i=0;i<allChecked.length;i++){
			var node = $("#add_tree_sysRole").tree("find",allChecked[i].id);
			$("#add_tree_sysRole").tree("uncheck",node.target);
		}
		$('#add_dialog_sysRole').dialog("open");
	},
	
	//点击修改按钮功能
	editBtnAction:function(id,name){
		$('#datagrid_sysRole').datagrid('clearSelections');
		//清空原有勾选
		var allChecked = $("#edit_tree_sysRole").tree("getChecked");
		for(var i=0;i<allChecked.length;i++){
			var node = $("#edit_tree_sysRole").tree("find",allChecked[i].id);
			$("#edit_tree_sysRole").tree("uncheck",node.target);
		}
		//赋值
		var roleId = id;
		var roleName = name;
		$("#edit_sysRoleName").unbind().bind("keyup",function(){
			var b = $.trim($("#edit_sysRoleName").val());
			setTimeout(function(){
				var n = $.trim($("#edit_sysRoleName").val());
				if(b==n){
					 window.d = $.post("../sysRole/checkExist.do",{"newValue":n,"oldValue":roleName},function(data){
						 if(data.result == true){
							 inf.checkExistTrue("edit_sysRoleName","角色名");
						 }else{
							 inf.checkExistFalse("edit_sysRoleName",I_role.validTypes);
						 }
					 },"json");		
				}
			}, 300);
		});
		
		
		$("#edit_sysRoleId").val(roleId);
		$("#edit_sysRoleName").val(roleName);
		inf.checkExistFalse("edit_sysRoleName",I_role.validTypes);
		inf.loading({all:"0",id:"edit_dialog_sysRole"});
		$.post("../sysRole/getPrivilegeListByRoleId.do",{"roleId":roleId},function(data){
			for(var i=0;i<data.length;i++){
				var node = $("#edit_tree_sysRole").tree("find",data[i].resId);
				$("#edit_tree_sysRole").tree("check",node.target);
			}
			inf.loaded();
		},"json");
		$('#edit_dialog_sysRole').dialog("open");
		
	},
	//点击删除按钮功能
	deleteBtnAction:function(roleId){
		
		$.post("../sysRole/sysRoleCheckUser.do",{"roleId":roleId},function(data){
			if(data.result == true){
				$.messager.alert("信息","该角色下存有用户信息,不能删除");
			}else if(data.result == false){
					$.messager.confirm('信息','确定删除该角色吗?',function(r){
						if(r){
							var roleIds = [];
							roleIds.push(roleId);
							
							$('#datagrid_sysRole').datagrid("loading");
							$.ajax({
								url : '../sysRole/sysRoleDelete.do',
								data : {
									roleIds : roleIds.join(',')
								},
								dataType : 'json',
								success : function(result) {
									if (result.success) {
											$('#datagrid_sysRole').datagrid('clearSelections');
											$('#datagrid_sysRole').datagrid('reload');
											$.messager.show({
												title : '提示',
												msg : '删除成功'
											});
									}else{
										$.messager.show({
											title : '提示',
											msg : '删除失败'
										});
									}
								}
							});
						}
					});
			}else{
				$.messager.alert("信息",data.result);
			}
		},"json");
	},
	
	//查看角色
	detailBtnAction:function(roleId,roleName){
		$('#datagrid_sysRole').datagrid('clearSelections');
		//清空原有勾选
		var allChecked = $("#detail_tree_sysRole").tree("getChecked");
		for(var i=0;i<allChecked.length;i++){
			var node = $("#detail_tree_sysRole").tree("find",allChecked[i].id);
			$("#detail_tree_sysRole").tree("uncheck",node.target);
		}
		//赋值
		var roleId = roleId;
		var roleName = roleName;
		
		$("#detail_sysRoleId").val(roleId);
		$("#detail_sysRoleName").val(roleName);
		inf.checkExistFalse("detail_sysRoleName",I_role.validTypes);
		inf.loading({all:"0",id:"detail_dialog_sysRole"});
		$.post("../sysRole/getPrivilegeListByRoleId.do",{"roleId":roleId},function(data){
			for(var i=0;i<data.length;i++){
				var node = $("#detail_tree_sysRole").tree("find",data[i].resId);
				$("#detail_tree_sysRole").tree("check",node.target);
			}
			inf.loaded();
		},"json");
		$('#detail_dialog_sysRole').dialog("open");
		
	
	},
	//点击新增确认
	addSureBtnAction:function(){
		if($("#add_sysRoleName").validatebox("isValid")){
			var sysRoleName = $("#add_sysRoleName").val();
			var allChecked = $("#add_tree_sysRole").tree("getChecked");
			var resourceIds = [];
			for(var i=0;i<allChecked.length;i++){
				resourceIds.push(allChecked[i].id);
			}
			resourceIds = resourceIds.join(",");
			
			inf.loading({all:"0",id:"add_dialog_sysRole"});		
			$.post("../sysRole/sysRoleAdd.do",{"roleName":sysRoleName,"resourceIds":resourceIds},
				function(data){
				I_role.addCancelBtnAction();
				inf.loaded();
				if(data.result == true){ 
						$('#datagrid_sysRole').datagrid("reload");
						$.messager.show({
							title : '提示',
							msg : '保存成功'
						});
				}else{
					$.messager.show({
						title : '提示',
						msg : '保存失败'
					});
				}
			},"json");
			
		}
	},
	//点击新增取消
	addCancelBtnAction:function(){
		$('#add_dialog_sysRole').dialog("close");
	},
	// 编辑确认
	editSureBtnAction:function(){
		if($("#edit_sysRoleName").validatebox("isValid")){
			var roleId = $("#edit_sysRoleId").val();
			var roleName = $("#edit_sysRoleName").val();
			var allChecked = $("#edit_tree_sysRole").tree("getChecked");
			var resourceIds = [];
			for(var i=0;i<allChecked.length;i++){
				resourceIds.push(allChecked[i].id);
			}
			resourceIds = resourceIds.join(",");
			
			inf.loading({all:"0",id:"edit_dialog_sysRole"});
			$.post("../sysRole/sysRoleUpdate.do",{"roleId":roleId,"roleName":roleName,"resourceIds":resourceIds},
					function(data){
				I_role.editCancelBtnAction();
				inf.loaded();
				if(data.result == true){
						$('#datagrid_sysRole').datagrid("reload");
						$.messager.show({
							title : '提示',
							msg : '修改成功'
						});
				}else{
					$.messager.show({
						title : '提示',
						msg : '修改失败'
					});
				}
				
			},"json");
			
		}
	},
	// 编辑取消
	editCancelBtnAction:function(){
		$('#edit_dialog_sysRole').dialog("close");
	}
};

$(function(){
	I_role.init();
});

///
//* 扩展树 treegrid 表格级联勾选方法： treegrid
//* @param {Object} container
//* @param {Object} options
//* @return {TypeName} 
//*/
$.extend($.fn.treegrid.methods,{
//	/
//	 * 级联选择
//    * @param {Object} target
//    * @param {Object} param 
//	 *		param包括两个参数:
//    *			id:勾选的节点ID
//    *			deepCascade:是否深度级联
//    * @return {TypeName} 
//	 */
	cascadeCheck : function(target,param){
		var opts = $.data(target[0], "treegrid").options;
		if(opts.singleSelect)
			return;
		var idField = opts.idField;//这里的idField其实就是API里方法的id参数
		var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选
		var selectNodes = $(target).treegrid('getSelections');//获取当前选中项
		for(var i=0;i<selectNodes.length;i++){
			if(selectNodes[i][idField]==param.id)
				status = true;
		}
		//级联选择父节点
		selectParent(target[0],param.id,idField,status);
		selectChildren(target[0],param.id,idField,param.deepCascade,status);
//		/
//		 * 级联选择父节点
//		 * @param {Object} target
//		 * @param {Object} id 节点ID
//		 * @param {Object} status 节点状态，true:勾选，false:未勾选
//		 * @return {TypeName} 
//		 */
		function selectParent(target,id,idField,status){
			var parent = $(target).treegrid('getParent',id);
			if(parent){
				var parentId = parent[idField];
				if(status)
					$(target).treegrid('select',parentId);
				else
					$(target).treegrid('unselect',parentId);
				selectParent(target,parentId,idField,status);
			}
		}
//		/
//		 * 级联选择子节点
//		 * @param {Object} target
//		 * @param {Object} id 节点ID
//		 * @param {Object} deepCascade 是否深度级联
//		 * @param {Object} status 节点状态，true:勾选，false:未勾选
//		 * @return {TypeName} 
//		 */
		function selectChildren(target,id,idField,deepCascade,status){
			//深度级联时先展开节点
			if(!status&&deepCascade)
				$(target).treegrid('expand',id);
			//根据ID获取下层孩子节点
			var children = $(target).treegrid('getChildren',id);
			for(var i=0;i<children.length;i++){
				var childId = children[i][idField];
				if(status)
					$(target).treegrid('select',childId);
				else
					$(target).treegrid('unselect',childId);
				selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点
			}
		}
	}
});


//扩展树的方法，用于角色资源分配时，不全选子节点，可以勾选父节点(未能实现)
$.extend($.fn.tree.methods,{
	
	cascadCheckOwn: function(target,param){
		checkedParentinfo(target, param.id, param.isChecked);
		checkChildreninfo(target, param.id, param.isChecked, param.deepCascade);
		
		//操作父节点
		function checkedParentinfo(target,currendId,isChecked){
			//当前选中的节点
			var currNode=$(target).tree('find',currendId);
			//当前选中的节点的父节点
			var parentNode=$(target).tree("getParent",currNode.target);
			
			/*if(isChecked && null == parentNode){
				 $(target).tree("expand",currNode.target);// 展开父节点下的子节点
			}*/

			//操作的是子节点
			if(parentNode){
				if(isChecked){
					$(target).tree("check",parentNode.target);
				}else{
					var hasChecked=false; // 子节点中是否有被选中的
					//获取所有已经选中的节点
					var nodes= $(target).tree("getChecked");
					//当前节点的父节点的所有子节点
					var  childNodes=$(target).tree("getChildren",parentNode.target);
					//判断当前节点的兄弟节点是否有被选中的
					for(var i=0;i<childNodes.length;i++){
						for(var j=0;j<nodes.length;j++){
							if(nodes[j].id == childNodes[i].id){
								hasChecked=true;
								break;
							}
						}
						if(hasChecked){
							break;
						}
					}
					if(!hasChecked){
						 $(target).tree("uncheck",parentNode.target);
					}
				}
				checkedParentinfo(target,parentNode.id,isChecked);
			}
		};
		
		//操作子节点
		function checkChildreninfo(target,currendId,isChecked,deepCascade){
			//当前节点
			var currNode=$(target).tree("find",currendId);
			if(isChecked && deepCascade){
				 $(target).tree("expand",currNode.target);
			} 
			var childNodes=$(target).tree("getChildren",currNode.target);
			for(var i=0;i<childNodes.length;i++){
				if(isChecked){
					var hasChecked=false; // 子节点中是否有被选中的
					//获取所有已经选中的节点
					/*var nodes= $(target).tree("getChecked");
					for(var i=0;i<nodes.length;i++){
						for(var j=0;j<){
							
						}
					}*/
					
					$(target).tree("check",childNodes[i].target);
				}else{
					$(target).tree("uncheck",childNodes[i].target);
				}	 
				checkChildreninfo(target,childNodes[i].id,isChecked,deepCascade);	 
			}
		}
	}
});


