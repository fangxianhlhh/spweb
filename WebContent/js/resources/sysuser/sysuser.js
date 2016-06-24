var I_sysUser={
		validTypes : ["notNull"],	
		//角色列表展示
		init:function(){
			var height = $('.indexcenter').height();
			//var classId = 'role';
			var urljson = '../sysRole/getSysRoleList.do';
			$('#datagrid_sysRole').datagrid({	});
		}
		
}