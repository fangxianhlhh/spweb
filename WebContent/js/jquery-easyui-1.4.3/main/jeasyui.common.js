/*****左侧导航与右侧tabs功能----------begin******/
/**
* 左侧导航菜单点击事件
*/
$(function(){
$('.sider li a').click(function(){
var classId = 'index';
var subtitle = $(this).text();
var url = $(this).attr('cmshref');
var rel = $(this).attr('rel');
//左侧直接打开弹窗
if(rel=='dialog'){
	var type = $(this).attr('type');
	//alert(type);
	openDialog(type,url,subtitle);
	return false;
}
//更新内容到右侧的tabs内容区
if(!$('#tabs_'+classId).tabs('exists',subtitle)){
	 $('#tabs_'+classId).tabs('add',{
		title:subtitle,
		content:subtitle,
		closable:true,
		href:url,
		tools:[{
				iconCls:'icon-mini-refresh',
				handler:function(){
					updateTab(classId,url,subtitle);
				}
			}]
	});
	return false;
}else{
	$('#tabs_'+classId).tabs('select',subtitle);
	return false;
}
});
});

/**
* 更新tab功能
* 更新后往往会有JS错误	
*/
function updateTab(classId,url,subtitle){
	$('#tabs_'+classId).tabs('select',subtitle);
	var tab = $('#tabs_'+classId).tabs('getSelected');
	tab.panel('refresh', url);
	
/*	var current_tab = $('#tabs_'+classId).tabs('getSelected');
	$('#tabs_'+classId).tabs('update',{
	     tab:current_tab,
	     options : {
	    	 href : url
	     }
	});*/
	
}

/**
 * 首页修改密码
 * 
 */
function index_changePwd(url){
	$('<div id="index_sysUser_pwd"></div>').dialog({
		href : url+'/spweb/sysusers/sysuser/index_changePwd.jsp',
		width : 320,
		height : 200,
		modal : true,
		title : '修改密码',
		buttons : [ {
			text : '确定',
			iconCls : 'icon-edit',
			handler : function() {
				$('#user_sysUserPwd_form').form('submit', {
					url : url+'/sysUser/indexChangePwd.do',
					success : function(result) {
						try {
							var msg = result == '0'?"原密码不正确！":"操作成功，请重新登录！";
							alert(msg);
						} catch (e) {
							$.messager.alert('提示', "操作失败！");
						}
						if(result == '1'){
							top.location = 'logout.do';
						}
					}
				});
			}
		}, {
			text:'关闭',
			handler:function() {
				$("#index_sysUser_pwd").dialog('destroy');
			}
		}  ],
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function() {
		}
	});
}


/**
 * 首页修改个人信息
 * zongxin
 * 2014-12-08
 */
function index_changeUserInfo(url){
	$('<div id="index_userInfo"></div>').dialog({
		href : url+'/yjq/permission/user/index_changeUserInfo.jsp',
		width : 600,
		height : 400,
		modal : true,
		title : '修改个人信息',
		buttons : [ {
			text : '确定',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $('#index_userInfo').closest('.window-body');
				$('#user_userInfo_form').form('submit', {
					url : url+'/yjq/user/index_changeUserInfo.do',
					success : function(result) {
						try {
							var msg = result == '0'?"操作失败！":"修改成功！";
							alert(msg);
						} catch (e) {
							$.messager.alert('提示', "操作失败！");
						}
						var uname = $('#index_uname').val();
						$('#name_tag').text(uname);
						d.dialog('destroy');
					}
				});
			}
		}, {
			text:'关闭',
			handler:function() {
				$("#index_userInfo").dialog('destroy');
			}
		}  ],
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function() {
		}
	});
}





/*****左侧导航与右侧tabs功能----------end******/

/*****顶部特效----------begin******/
/**
* 更换EasyUI主题的方法
* @param themeName
* 主题名称
*/
changeTheme = function(themeName) {
var $easyuiTheme = $('#easyuiTheme');
var url = $easyuiTheme.attr('href');
var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
$easyuiTheme.attr('href', href);
var $iframe = $('iframe');
if ($iframe.length > 0) {
for ( var i = 0; i < $iframe.length; i++) {
var ifr = $iframe[i];
$(ifr).contents().find('#easyuiTheme').attr('href', href);
}
}
$.cookie('easyuiThemeName', themeName, {
expires : 7
});
};

function logoutFun(b) {
	$.getJSON('/userController/logout.action', function(result) {
		if (b) {
			location.replace('/index.jsp');
		} else {
			$('#sessionInfoDiv').html('');
			$('#user_login_loginDialog').dialog('open');
			$('#layout_east_onlineDatagrid').datagrid('load', {});
		}
	});
}
function userInfoFun() {
	$('<div/>').dialog({
		href : '/userController/userInfo.action',
		width : 490,
		height : 285,
		modal : true,
		title : '用户信息',
		buttons : [ {
			text : '修改密码',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $(this).closest('.window-body');
				$('#user_userInfo_form').form('submit', {
					url : '/userController/modifyCurrentUserPwd.action',
					success : function(result) {
						try {
							var r = $.parseJSON(result);
							if (r.success) {
								d.dialog('destroy');
							}
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						} catch (e) {
							$.messager.alert('提示', result);
						}
					}
				});
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function() {
		}
	});
}
/*****顶部特效--------end******/

/*****通用函数--------begin******/
/*
 *  submitForm  提交表单时执行
 *  classId 为当前表单的id
 */
function submitForm(classId){
    var url = $('#form_'+classId).attr('action');
    alert("表单提交成功！，请修改jeasyui.common.js的submitForm方法！");
	$('#dialog_cms').dialog('close');
    return false;
    $('#form_'+classId).form('submit',{
        url:url,
        onSubmit:function(){
        //$('#dialog').dialog('refresh', '__APP__/Setting/add');
        //alert(url);
        //$('#dialog').dialog('close');
        },
        success:function(msg){
            var data = $.parseJSON(msg);
            //alert(data.msg+'=======dede====');
            //return false;
            formAjax(data,classId);
        }
    });

}

/********退出登录********/
function logOut(){
	top.location = 'logout.do';
}


function formAjax(data,classId){
    //alert(classId);
    //return false;

    if(data.status==1){
        $.messager.alert(data.info,data.info,'error');
    }else if(data.status==2){
        $.messager.show({
            title:data.info,
            msg:data.info,
            timeout:5000,
            showType:'slide'
        });
        $('#treegrid_'+classId).treegrid('reload');
        if(data.isclose=='ok'){
            $('#dialog_'+classId).dialog('close');
            dialogOnClose(classId);
        }
    }
}
/*
 *openDialog 弹出框
 *href 传递控制器的url地址
 *title 弹出窗口的标题
 */
function openDialog(classId,href,title){
    $('#dialog_cms').dialog({
        href:href,
        width:1000,
        height:680,
        resizable:true,
        title:title,
        modal:true,
        resizable:true,
        collapsible:true,
        maximizable:true,
        cache: false,
        onClose:function(){
            dialogOnClose(classId);
        },
        buttons:[{
            text:'保存',
            iconCls:'icon-ok',
            handler:function(){
                submitForm(classId);
            }
        },{
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                dialogOnClose(classId);
            }
        }
        ]
    });
//$('#dialog'+classId).dialog('refresh', href);
}
/*
* 关闭dialog时，销毁dialog代码
*/
function dialogOnClose(classId){
    $('#dialog_cms').dialog('destroy');
    $('body.easyui-layout').append('<div id="dialog_cms"  data-options="iconCls:\'icon-save\'"></div>');
    var frame = $('iframe[src="about:blank"]');//destroy与iframe冲突问题，大概是内存释放的原因
    frame.remove();
}
/*****通用函数--------end******/

/*****news函数--------begin******/
var admin_viewFun = function(id,url,classId){
	href = url+'?id='+id;
	title = '详情';
	$('#datagrid_' + classId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');	//清除选择
	$('<div></div>').dialog({
	href:href,
	width:1115,
	height:500,
	modal : true,
	resizable:true,
	collapsible:true,
	maximizable:true,
	title:title,
    onClose: function() {
        $(this).dialog('destroy');
    },
    onLoad: function() {
        var index = $('#datagrid_' + classId).datagrid('getRowIndex', id);
        var rows = $('#datagrid_' + classId).datagrid('getRows');
        var o = rows[index];
        o.roleIds = stringToList(rows[index].roleIds);
        $('#form').form('load', o);
    }
	});
};

var admin_cancelFun = function(id,hrefcancel){
		var href = hrefcancel;
		var title = '删除信息';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};
function admin_appendFun(classId,href,title,url) {
		$('#datagrid_' + classId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div></div>').dialog({
		href:href,
		width:1000,
		height:680,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#form').form('submit', {
				url : url,
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
					$('#datagrid_' + classId).datagrid('insertRow', {
						index : 0,
						row : r.obj
					});
					d.dialog('destroy');
					}
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});
}
function admin_editFun(id,urlBefor,urlEnd,classId) {
		href = urlBefor+'?id='+id;
		title = '编辑';
		$('#datagrid_' + classId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');	//清除选择
		$('<div></div>').dialog({
		href:href,
		width:1000,
		height:680,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#form').form('submit', {
					url : urlEnd,
					success: function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#datagrid_' + classId).datagrid('updateRow', {
										index: $('#datagrid_' + classId).datagrid('getRowIndex', id),
										row: r.obj
									});
									d.dialog('destroy');
									$.messager.show({
										title : '提示',
										msg : r.msg
									});
								}
							} catch(e) {
								$.messager.alert('提示', result);
							}
					}
				});
			}
		} ],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
            var index = $('#datagrid_' + classId).datagrid('getRowIndex', id);
            var rows = $('#datagrid_' + classId).datagrid('getRows');
            var o = rows[index];
            o.roleIds = stringToList(rows[index].roleIds);
            $('#form').form('load', o);
        }
		});
}
function admin_removeFun(classId,url) {
	var rows = $('#datagrid_' + classId).datagrid('getChecked');
	var ids = [];
	$.each(rows, function (i, n) {
		ids[i] = n.Sid;
	}); 
	
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
			if (r) {
				var flag = true;
				$.ajax({
					url : url,
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(result) {
						if (result.success) {
							$('#datagrid_' + classId).datagrid('load');
							$('#datagrid_' + classId).datagrid('uncheckAll')
									.datagrid('unselectAll').datagrid(
											'clearSelections');
						}
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
					}
				});
			}
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : '请勾选要删除的记录！'
		});
	}
}

function admin_releaseFun(classId, hrefcancel, title, url) {
	var rows = $('#datagrid_' + classId).datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		$.messager.confirm('确认', '您是否要发布当前选中的项目？', function(r) {
			if (r) {
				var flag = true;
				$.ajax({
					url : url,
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(result) {
						if (result.success) {
							$('#datagrid_' + classId).datagrid('load');
							$('#datagrid_' + classId).datagrid('uncheckAll')
									.datagrid('unselectAll').datagrid(
											'clearSelections');
						}
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
					}
				});
			}
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : '请勾选要发布的记录！'
		});
	}
}

function admin_newscat_appendFun(classId,href,title) {
		$('<div></div>').dialog({
		href:href,
		width:600,
		height:400,
		modal : true,
		resizable:true,
		collapsible:true,
		maximizable:true,
		title:title,
		buttons : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				var d = $(this).closest('.window-body');	//上层弹窗
				$('#newsaddform').form('submit', {
				url : 'news/newscatsave.html',
				success : function(result) {
				try {
					var r = $.parseJSON(result);
					if (r.success) {
						/*$('#treegrid_newssort').treegrid('append', {	//此段数据对显示效果有影响
							parent: r.obj.pid,
							data: [r.obj]
						});*/
						d.dialog('destroy');
						$('#treegrid_newssort').treegrid('reload');		//使用动态载入方式比较友好
					}
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}catch (e) {
				$.messager.alert('提示',result);
				}
				}
				});
			}
		} ],
		onClose : function() {
		$(this).dialog('destroy');
		}
		});	
}
function admin_newscat_editFun(id) {
    if (id != undefined) {
        $('#treegrid_newssort').treegrid('select', id);
    }
    var node = $('#treegrid_newssort').treegrid('getSelected');
    $('<div></div>').dialog({
        href: 'news/newssortedit.html',
        width: 600,
        height: 400,
        modal: true,
        title: '编辑分类',
        buttons: [{
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function() {
                var d = $(this).closest('.window-body');
                $('#newscateditform').form('submit', {
                    url: 'news/newscatsave.html',
                    success: function(result) {
                        try {
                            var r = $.parseJSON(result);
                            if (r.success) {
                                $('#treegrid_newssort').treegrid('reload');
                                d.dialog('destroy');
                            }
                        } catch(e) {
                            $.messager.alert('提示', result);
                        }
                    }
                });
            }
        }],
        onClose: function() {
            $(this).dialog('destroy');
        },
        onLoad: function() {
            //$('#newscateditform').form('load', node);	//自动载入节点信息
        }
    });
}

function admin_newscat_cancelFun(id) {
    if (id != undefined) {
        $('#treegrid_newssort').treegrid('select', id);
    }
    var node = $('#treegrid_newssort').treegrid('getSelected');
    if (node) {
        $.messager.confirm('询问', '您确定要删除【' + node.name + '】？',
        function(b) {
            if (b) {
                $.ajax({
                    url: 'news/catdel.html',
                    data: {
                        id: node.id
                    },
                    cache: false,
                    dataType: 'JSON',
                    success: function(r) {
                        if (r.success) {
                            $('#treegrid_newssort').treegrid('remove', r.obj); 	//显示删除效果
                            $('#treegrid_newssort').treegrid('reload');
                        }
                        $.messager.show({
                            msg: r.msg,
                            title: '提示'
                        });
                    }
                });
            }
        });
    }
}
/*****news函数--------end******/

/*****pictures函数--------begin******/
var admin_pics_viewFun = function(id){
		alert("打开前台页面");
};
var admin_pics_editFun = function(id){
		var classId = 'picstab0';
		var hrefadd = 'pictures/add.html';
		var title = '添加图片';
		openDialog(classId,hrefadd,title);
};
var admin_pics_cancelFun = function(id){
		var hrefcancel = 'pictures/del.html';
		var href = hrefcancel;
		var title = '删除信息';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};
/*****pictures函数--------end******/

/*****friendlink函数--------begin******/
var admin_links_viewFun = function(id){
		alert("打开前台页面");
};
var admin_links_editFun = function(id){
		var classId = 'picstab0';
		var hrefadd = 'pictures/add.html';
		var title = '添加链接';
		openDialog(classId,hrefadd,title);
};
var admin_links_cancelFun = function(id){
		var hrefcancel = 'pictures/del.html';
		var href = hrefcancel;
		var title = '删除链接';
		$.messager.confirm(title,href, function(){
			$.ajax({
				url:href,
				type:'post',
				data:{
					id:ids
				},
				dataType:'json',
				success:function(data){
					formAjax(data,classId);
				}
			});
		});//$
};
/*****friendlink函数--------end******/

/**
 * 维修企业工位添加
 */
function station_addFun(classId,href,title,url) {
	$('#datagrid_' + classId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	$('<div></div>').dialog({
	href:href,
	width:400,
	height:300,
	modal : true,
	resizable:true,
	collapsible:true,
	maximizable:true,
	title:title,
	buttons : [ {
		text : '增加',
		iconCls : 'icon-add',
		handler : function() {
			/*var d = $(this).closest('.window-body');	*/ //上层弹窗
			$('#form').form('submit', {
			url : url,
			success : function(result) {
					var r = $.parseJSON(result);
					if (r.success) {
						$('#datagrid_' + classId).datagrid('load');
					} 
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
			}
			});
		}
	} ],
	onClose : function() {
		$(this).dialog('destroy');
	}
	});
}


function company_editFun(id,urlBefor,urlEnd,classId) {
	href = urlBefor+'?Reid='+id;
	title = '编辑';
	$('#datagrid_' + classId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');	//清除选择
	$('<div></div>').dialog({
	href:href,
	width:800,
	height:600,
	modal : true,
	resizable:true,
	collapsible:true,
	maximizable:true,
	title:title,
	buttons : [ {
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			var d = $(this).closest('.window-body');	//上层弹窗
			$('#form').form('submit', {
				url : urlEnd,
				success: function(result) {
						try {
							var r = $.parseJSON(result);
							if (r.success) {
								$('#datagrid_' + classId).datagrid('updateRow', {
									index: $('#datagrid_' + classId).datagrid('getRowIndex', id),
									row: r.obj
								});
								d.dialog('destroy');
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							}
						} catch(e) {
							$.messager.alert('提示', result);
						}
				}
			});
		}
	} ],
    onClose: function() {
        $(this).dialog('destroy');
    }
    /*onLoad: function() {
        var index = $('#datagrid_' + classId).datagrid('getRowIndex', id);
        var rows = $('#datagrid_' + classId).datagrid('getRows');
        var o = rows[index];
        o.roleIds = stringToList(rows[index].roleIds);
        $('#form').form('load', o);
    }*/
	});
}
	//维修记录表单打印-
	function printSheet(print_id,url) {
		
		if($.browser.msie) { //IE
			var winprint = window.open(url);
			
			var date1 = new Date();
			date1.setSeconds(date1.getSeconds() + 5);
			var printContent = winprint.document.getElementById('printContent');
			while (printContent == null) {
				printContent = winprint.document.getElementById('printContent');
				var date2 = new Date();
				if (Date.parse(date1) < Date.parse(date2)) {
					break;
				}
			}
			
			if (winprint.document.getElementById('printContent') == null) {
				$.messager.alert('提示','本次打印可能出现问题！请点击确定，重新操作！');
				winprint.close();
				return;
			}
			
			winprint.document.getElementById('printContent').innerHTML = $("#"+print_id).html();
			winprint.document.close(); // necessary for IE >= 10
			winprint.focus(); // necessary for IE >= 10
			winprint.document.execCommand('print', false, null);
			//winprint.print();
			winprint.close();
		} else {
		
			$("#"+print_id).jqprint();
		}
		 return true;
	}
	
	//检测报告打印
function printSheet1(print_id,url) {
		$("#"+print_id).jqprint();
		 return true;
	}
	
