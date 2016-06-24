<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>
<head>
<title>xxx后台管理平台</title>
<meta charset="UTF-8">
<link   type="text/css" href="${ctx}/css/skin.css" rel="stylesheet" >
<script type="text/javascript" src="${ctx}/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">

function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE");
    var version = parseFloat(arVersion[1]);
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j];
          var imgName = img.src.toUpperCase();
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : "";
             var imgClass = (img.className) ? "class='" + img.className + "' " : "";
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' ";
             var imgStyle = "display:inline-block;" + img.style.cssText ;
             if (img.align == "left") imgStyle = "float:left;" + imgStyle;
             if (img.align == "right") imgStyle = "float:right;" + imgStyle;
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle;
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" ;
             img.outerHTML = strNewHTML;
             j = j-1;
          }
       }
    }
}
//window.attachEvent("onload", correctPNG);

$(function(){
	init();
});


//表单提交
function doSubmit(){
	var loginName = $("#loginName").val();
	var password = $("#password").val();
	var code = $("#code").val();
	if(loginName == null || loginName == ""){
		$.messager.alert('提示','请输入用户名','info');
		return false;
	}
	if(password == null || password == ""){
		$.messager.alert('提示','请输入密码','info');
		return false;
	}
	 if(code == null || code == ""){
		$.messager.alert('提示','请输入验证码','info');
		return;
	} 
	document.forms[0].submit();
}

function doReset(){
	 $("#loginName").val('');
	 $("#password").val('');
	 $("#code").val('');
}


//生成验证码
function changecode(obj){
	obj.src = "${ctx}/validcode.action?t="+Math.random();
}

function init(){
	changecode(document.getElementById("codeimg"));
}

document.onkeydown=keyLogin; 
	function keyLogin(e){
			var e=e||event;  
   	if (e.keyCode==13) {   //回车键的键值为13
   		doSubmit(); 
			} 
}

</script>
</head>
<body ><!-- onload="init()" -->
<div class="login_top_bg"></div>
<div class="login_main login_bg">
    <div class="left login_bg2">
       <div class="left-info">
         <img src="${ctx}/images/logo.png" width="279" height="68">
         <img src="${ctx}/images/logo_y.png" width="100px">
       </div>
    </div>

    <div class="right">
    
    <form name="myform" action="${ctx}/sysUserLogin/SysUserLogin.do" method="post">
      <ul class="right-info">
        <li class="login_txt_bt">登录管理后台</li>
        <li>
        	<span class="login_txt">用户名：&nbsp;&nbsp;</span>
        	<input id="loginName" name="loginName" class="editbox4" value="" size="20">
        </li>
        <li>
        	<span class="login_txt">密&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;</span>
        	 <input class="editbox4" type="password" size="20" id="password" name="password">
        	<img src="${ctx}/images/luck.gif" width="19" height="18">
        </li>
        
         <li>
        	<span class="login_txt">验证码：&nbsp;&nbsp;</span>
			<input id="code" name="code" class="editbox4"  maxlength="4" style="width: 90px;ime-mode:disabled;text-align:left; font-size:14px;font-variant: small-caps"/>
			<img border=0  onclick="changecode(this)"  title="点击切换验证码" style="vertical-align:middle;cursor: pointer;" alt="加载" id="codeimg" 
			src="${ctx}/images/codeload.gif" >
		</li> 
        <li>
        	<input name="Submit" type="button" class="button" id="Submit" value="登 录" onclick ="return doSubmit()">
        	<input name="cs" type="button" class="button" id="cs" value="取 消" onClick="doReset()">
        	<input type="hidden" id="key" name="key" value="lognginName,password,code" />
        </li>
      </ul>
    </form>
    
    </div>
</div>
<div class="login-buttom-bg">
      <div class="login-buttom-txt">Copyright &copy; 2009-2010</div>
    </div>
	<c:if test="${not empty errorMessage}">
		<script>
			alert('${errorMessage}');
		</script>
	</c:if>
</body>
</html>