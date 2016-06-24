/*
 * SUMMARY : function obj$(id) 根据id得到对象 function val$(id) 根据id得到对象的值 function
 * trim(str) 删除左边和右边空格 function ltrim(str) 删除左边空格 function rtrim (str) 删除右边空格
 * function isEmpty(str) 字串是否有值 function equals(str1, str2) 比较两字符串是否相等 function
 * equalsIgnoreCase(str1, str2) 忽略大小写比较两个字符串是否相等 function isChinese(str) 判断是否中文
 * function isEmail(strEmail) 是否电子邮件 function isImg(str)
 * 是否是一个图片格式的文件jpg|jpeg|swf|gif function isInteger(str) 是否是一个整数 function
 * isFloat是否是一个浮点数 function isPost(str) 是否邮编(1位至6位) function isMobile(str)
 * 是否是手机号 function isPhone(str) 是否是电话号码必须包含区号,可以含有分机号 function isQQ(str)
 * 是否合法的QQ号码 function isIP(str) 是否是合法的IP function isDate(str)
 * 是否日期类型(例:2005-12-12) function isIdCardNo(idNumber) 是否是合法的身份证号 function
 * isEmpty(str) 是否为空 function Back() 返回上一页 function getToday() 获取系统当前时间 function
 * isDigit(theNum) 是否是数字 function isInt(theStr) 是否是整型数字 function isBetween (val,
 * lo ,hi) 是否在两个数字之间 function isDateArea(str1,str2) 是否第一个日期小于等于第二个日期
 * name:ID forname:显示信息divID msg错误显示信息 function msgisnull(name,forname,msg)
 * 该方法用来检验值是否为空,并将错误信息显示在div中 function msgisDigit(name,forname,msg)
 * 该方法用来验证是否数字,并将错误信息显示在div中 function msgisDate(name,forname,msg)
 * 该方法用来验证是否日期,并将错误信息显示在div中 function msgisIP(name,forname,msg)
 * 该方法用来验证是否IP地址,并将错误信息显示在div中
 */

//	function modify(url,id,resultPage,pageHtml) { 
//	    window.location=url+"&resultPage="+resultPage+"&id=" + encodeURI(id)+"&page="+pageHtml;
//	}

/**
 * 判断两个日期大小
 * @param date1 日期1 值需为yyyy-MM-dd形式
 * @param date2 日期2 值需为yyyy-MM-dd形式
 * @return 日期1>日期2 false;日期1<=日期2 true
 */
function befordate(date1,date2){
    var b=date1.replace(/-/g, "/");//2010-04-29  2010/04/29
    var e=date2.replace(/-/g, "/");
    var dt1=new Date(Date.parse(b));
    var dt2=new Date(Date.parse(e));
             
 	if(dt1>dt2){//比较日期
 		return false;
 	}else{
 		return true;
 	}
   }

/**
 * 页面重定向
 * @param url 地址
 * @param proName 参数名
 * @param proName 参数值
 * @return
 */
	function forwordByProperty(url,proName,proValue) {
		var ddd=url+"?"+proName+"=" + proValue;
	    window.location=ddd;
	}
	
/**
 * 编辑打开页面的url
 * @param url 地址
 * @param proName 参数名
 * @param proName 参数值
 * @return
 */
	function editOpenUrl(url,proName,proValue) { 
	    var myurl=url+"&"+proName+"=" + proValue;
	    return myurl;
	}

/**
 * 提供页面统一编码转码规范
 * @param proName 参数值
 * @return
 */
	function OurEncode(proValue) { 
	    var v=encodeURI(proValue);
	    return v;
	}


/**
 * 获取下拉框被选中项的文本
 * @param obj
 * @return
 */
function getSelectText(obj){
 return obj.options[obj.selectedIndex].text;	
}


/**
 * 该方法用来验证是否是金额,并将错误信息显示在div中
 */
function isMoney(name,forname,msg) 
{ 
	var tempMoney = val$(name);
	var patrn=/^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	if (!patrn.exec(tempMoney)) {
		obj$(forname).innerHTML="<font color='red'>"+msg+"</font>";
		obj$(name).focus();
		return false 
	}else{
		 obj$(forname).innerHTML="";
		 return true 
	}
}

/**
 * 该方法用来验证是否数字,并将错误信息显示在div中
 */
function msgisDigit(name,forname,msg){
  var tempDigit = val$(name);
 if(tempDigit!=null&&tempDigit.length>0&&!isDigit(tempDigit)){
 obj$(forname).innerHTML="<font color='red'>"+msg+"</font>";
 obj$(name).focus();
 obj$(name).value="";
 return false;
 }
 else{
 obj$(forname).innerHTML="";
 }
}

/**
 * 该方法用来验证是否浮点数,并将错误信息显示在div中
 */
function msgisFloat(name, msg){
  var tempDigit = val$(name);
 if(isFloat(val$(name))==false){
 obj$("msg").innerHTML="<font color='red'>"+msg+"</font>";
 obj$(name).focus();
 obj$(name).value="";
 return false;
 }
 else{
 obj$("msg").innerHTML="";
 return true;
 }
}

/**
 * 该方法用来验证是否整数,并将错误信息显示在div中
 */
function msgisInteger(name,forname,msg){
  var tempDigit = val$(name);
 if(isInteger(val$(name))==false){
 obj$(forname).innerHTML="<font color='red'>"+msg+"</font>";
 obj$(name).focus();
 // obj$(name).value="";
 return false;
 }
 else{
 obj$(forname).innerHTML="";
 return true;
 }
}

/*
将错误信息显示在div中
*/
function getFocusAndMsg(name ,msg, setValEmpFlag){
	 obj$('msg').innerHTML="<font color='red'>"+msg+"</font>";
 	 if(setValEmpFlag==1){
	 	 obj$(name).value="";
 	 }
}

/*
将错误信息消失
*/
function getEmptyMsg(){
	 obj$('msg').innerHTML="";
}

// 复选框全选/全不选
// 根据传入的checkbox的选中状态设置所有checkbox的选中状态
// 调用时：<input id="chk_SelectALL" type="checkbox" onclick="selectAll(this)" />全选
function selectAll(obj)
{
    var allInput = document.getElementsByTagName("input");
  
    // alert(allInput.length);
    var loopTime = allInput.length;
    for(i = 0;i < loopTime;i++)
    {
        // alert(allInput[i].type);
        if(allInput[i].type == "checkbox")
        {
            allInput[i].checked = obj.checked;
          
        }
    }
}
function selectAllByColor(obj,name)
{
    var allInput = document.getElementsByName(name);
    var trs = document.getElementsByTagName('tr');
    // alert(allInput.length);
    var loopTime = allInput.length;
    for(i = 0;i < loopTime;i++)
    {
            allInput[i].checked = obj.checked;
            trs[i+2].className = (allInput[i].checked==true)?'click':'';
        
    }
}
function selectNotEnable(obj)
{
    var allInput = document.getElementsByTagName("input");
    // alert(allInput.length);
    var loopTime = allInput.length;
    for(i = 0;i < loopTime;i++)
    {
        // alert(allInput[i].type);
        if(allInput[i].type == "checkbox")
        {
        	if(allInput[i].disabled!=true){
            allInput[i].checked = obj.checked;
            }
        }
    }
}

// 选择复选框进行删除，修改，查看明细
// 表单名称固定为list,checkbox名称固定为ids
// 查看明细调用 onDetail(你的查看明细action地址)
// 删除信息调用onDel(strActionUrl) strActionUrl设置你的表单action提交地址
// 修改信息调用onModify(你的查看明细action地址)
function onDetail(strActionUrl){
	var iSelRow=0;
    var id=0;
    var obj=document.getElementsByName("ids");
    for (var i = 0; i < obj.length; i++) {
      if (obj[i].checked) {
        iSelRow ++;
        }
    }
    if (iSelRow < 1 ) {
      alert("请选择一条记录！");
      return;
      }
    window.location=strActionUrl+"?id=" + id ;
  }
function onDelRow(strActionUrl){
	 if(confirm("确定要删除选中的记录吗？")) {
    window.location=strActionUrl ;
    }
  }
function onAdd(strActionUrl){
	window.location=strActionUrl;
}

  function onDel(strActionUrl){
    var iSelRow=0;
    var id = "";
    var obj=document.getElementsByName("ids");
    for (var i = 0; i < obj.length; i++) {
      if (obj[i].checked) {
        iSelRow ++;
        id += obj[i].value + ",";
        }
    }
    id = id.substring(0, id.length - 1);
    if (iSelRow < 1 ) {
      alert("请选择一条记录！");
      return;
      }
      if(confirm("确定要删除选中的记录吗？")) {
    	  forwordByProperty(strActionUrl,"ids",id);
      }
  }
  
   function onPhysicalDel(strActionUrl){
    var iSelRow=0;
    var id = "";
    var obj=document.getElementsByName("ids");
    for (var i = 0; i < obj.length; i++) {
      if (obj[i].checked) {
        iSelRow ++;
        id += obj[i].value + ",";
        }
    }
    id = id.substring(0, id.length - 1);
    if (iSelRow < 1 ) {
      alert("请选择一条记录！");
      return;
      }
      if(confirm("确定要删除选中的记录吗？")) {
    	  window.location=strActionUrl+"&ids=" + id;
      }
  }
  	
  	/*
  	自定义删除信息
  	*/
    function onDelete(strActionUrl, delMsg){
    var iSelRow=0;
    var id = "";
    var obj=document.getElementsByName("ids");
    for (var i = 0; i < obj.length; i++) {
      if (obj[i].checked) {
        iSelRow ++;
        id += obj[i].value + ",";
        }
    }
    id = id.substring(0, id.length - 1);
    if (iSelRow < 1 ) {
      alert("请选择一条记录！");
      return;
      }
      if(confirm(delMsg)) {
    	  forwordByProperty(strActionUrl,"ids",id);
      }
  }
  
   function onModify(strActionUrl){
	   var iSelRow = 0;
	    var id = 0;
	    var obj = document.getElementsByName("ids");
	    for (var i = 0; i < obj.length; i++) {
	      if (obj[i].checked) {
	        iSelRow ++;
	        id = obj[i].value;
	        }
	    }
	    if (iSelRow == 0 ) {
	      alert("请选择一条您要修改的记录！");
	      return;
	    } else if (iSelRow > 1 ) {
		      alert("对不起，您只能选择一条记录进行修改！");
		      return;
		}
	
	window.location=strActionUrl+"&ids=" + id;
	
  }
	function onView(strActionUrl){
		   var iSelRow = 0;
		    var id = 0;
		    var obj = document.getElementsByName("ids");
		    for (var i = 0; i < obj.length; i++) {
		      if (obj[i].checked) {
		        iSelRow ++;
		        id = obj[i].value;
		        }
		    }
		    if (iSelRow == 0 ) {
		      alert("请选择一条您要查看的记录！");
		      return;
		    } else if (iSelRow > 1 ) {
			      alert("对不起，您只能选择一条记录进行查看！");
			      return;
			}
		
		window.location=strActionUrl+"&ids=" + id;
	  }

	function onChoose(strActionUrl){
		   var iSelRow = 0;
		    var id = 0;
		    var obj = document.getElementsByName("ids");
		    for (var i = 0; i < obj.length; i++) {
		      if (obj[i].checked) {
		        iSelRow ++;
		        id = obj[i].value;
		        }
		    }
		    if (iSelRow == 0 ) {
		      alert("请选择一条您要选择的记录！");
		      return;
		    } else if (iSelRow > 1 ) {
			      alert("对不起，您只能选择一条记录进行操作！");
			      return;
			}
		
		window.location=strActionUrl+"?ids=" + id;
	  }

	/**
	 * 根据对象的id得到对象 id:对象的id
	 */
	function obj$(id)
	{
	    return document.getElementById(id);
	}

	/**
	 * 根据对象的id得到对象的值 id:对象的id
	 */
	function val$(id)
	{
	    var obj = document.getElementById(id);
	    if(obj !== null)
	    {
	        return obj.value;
	    }
	    return null;
	}
	/**
	 * 根据对象的name得到对象 name:对象的name
	 */
	function name$(name)
	{
	    return document.getElementsByName(name);
	}

	/**
	 * 去掉字符串的前后空格 str:将要除去空格的字符串
	 */
	function trim(str)
	{
	    return str.replace(/(^\s*)|(\s*$)/g, ''); 
	}

	/**
	 * 去掉字符串前的空格 str:将要除去空格的字符串
	 */
	function ltrim(str)
	{
	    return str.replace(/^\s*/g,'');
	}

	/**
	 * 去掉字符串后的空格 str:将要除去空格的字符串
	 */
	function rtrim(str)
	{
	    return str.replace(/\s*$/,'');
	}

	/**
	 * 字符串是否有值 str:要检测的字符串
	 */
	function isEmpty(str)
	{
	    return (str == null || "" == str) ? true : false;
	}

	/**
	 * 比较两个字符串是否相等 str1:要比较的字符串1 str2:要比较的字符串2
	 */
	function equals(str1, str2)
	{
	    if(str1 == str2)
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 忽略大小写比较两个字符串是否相等 str1:要比较的字符串1 str2:要比较的字符串2
	 */
	function equalsIgnoreCase(str1, str2)
	{
	    if(str1.toUpperCase() == str2.toUpperCase())
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是中文 str:要检测的字符串
	 */
	function isChinese(str)
	{
	   var str = str.replace(/(^\s*)|(\s*$)/g,'');
	   if (!(/^[\u4E00-\uFA29]*$/.test(str) 
	           && (!/^[\uE7C7-\uE7F3]*$/.test(str))))
	   {
	      return false;
	   }
	   return true;
	}

	/**
	 * 是否是Email str:要检测的字符串
	 */
	function isEmail(str)
	{
	    if(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str))
	    {
	        return true
	    }
	    return false;
	}

	/**
	 * 是否是图片格式文件 str:要测试的文件名
	 */
	function isImg(str)
	{
	    var objReg = new RegExp("[.]+(jpg|jpeg|swf|gif)$", "gi");
	    if(objReg.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是一个整数 str:要检测的字符串
	 */
	function isInteger(str)
	{
	    if(/^-?\d+$/.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是一个浮点数 str:要检测的字符串
	 */
	function isFloat(str)
	{
	    if(/^(-?\d+)(\.\d+)?$/.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是邮编 str:要检测的字符串
	 */
	function isPost(str)
	{
	    if(/^\d{6}$/.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是手机号码 str:要检测的字符串
	 */
	function isMobile(str)
	{
	    if(/^1[23456789]\d{9}/.test(str))
	      {
	          return true;
	      }
	    return false;
	}

	/**
	 * 是否是电话号码 str:要检测的字符串 电话号码必须有区号,可以有分机号
	 */
	function isPhone(str)
	{
	    if(/^(0[1-9]\d{1,2}-)\d{7,8}(-\d{1,8})?/.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是合法的QQ号码 str:要检测的字符串
	 */
	function isQQ(str){
	    if(/^\d{5,9}$/.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是合法的IP str:要检测的字符串
	 */
	function isIP(str){
	    var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	    if(reg.test(str))
	    {
	        return true;
	    }
	    return false;
	}

	/**
	 * 是否是一合法日期 str:要检测的字符串
	 */
	function isDate(str)
	{
	    var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
	    if(reg.test(str))
	    {
	        return true;
	    }
	    return false;    
	}

	/**
	 * 是否是身份证 str:要检测的字符串
	 */
	function isIdCardNo(idNumber) 
	{
	    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
	    var varArray = new Array();
	    var lngProduct = 0;
	    var intCheckDigit;

	    if ((idNumber.length != 15) && (idNumber.length != 18))
	    {
	        return false;
	    }    
	    for(i=0;i<idNumber.length;i++)
	    {
	        varArray[i] = idNumber.charAt(i);
	        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17))
	        {
	            return false;
	        }
	        else if (i < 17)
	        {
	            varArray[i] = varArray[i]*factorArr[i];
	        }
	    }
	    if (idNumber.length == 18)
	    {
	        var date8 = idNumber.substring(6,14);
	        if (checkDate(date8) == false)
	        {
	            return false;
	        }        
	        for(i=0;i<17;i++)
	        {
	            lngProduct = lngProduct + varArray[i];
	        }        
	        intCheckDigit = 12 - lngProduct % 11;
	        switch (intCheckDigit)
	        {
	            case 10:
	                intCheckDigit = 'X';
	                break;
	            case 11:
	                intCheckDigit = 0;
	                break;
	            case 12:
	                intCheckDigit = 1;
	                break;
	        }        
	        if (varArray[17].toUpperCase() != intCheckDigit)
	        {
	            return false;
	        }
	    } 
	    else
	    {       
	        var date6 = idNumber.substring(6,12);
	        if (checkDate(date6) == false)
	        {
	            return false;
	        }
	    }
	    return true;
	} 
	
	/**
	 * 是否是身份证2 num:要检测的字符串
	 */
	function isIdCard(num) 
	{ 


	var len = num.length, re; 
	var subnum=num.substring(0,17);
	if (len == 18&&(isNaN(subnum))) {
    return false;
    }else if(len == 15&&(isNaN(num))) {
    return false;

	}
	
	
	if (len == 15) 
	re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/); 
	else if (len == 18) 
	re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/); 
	else {return false;} 
	var a = num.match(re); 
	if (a != null) 
	{ 
	if (len==15) 
	{ 
	var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
	var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
	} 
	else 
	{ 
	var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
	var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
	} 
	if (!B) {return false;} 
	} 
	return true; 
	} 

/**
 * 是否是数字
 */
function isDigit(theNum)
{
	// if (isEmpty(theNum)) return false;
	var theMask = "0123456789";
	for(i=0;i<theNum.length;i++)
	{
		tmpn = theNum.substring(i,i+1);
		if (theMask.indexOf(tmpn) == -1) return false;
	}
	return true;
}
/*
输入的字符是否由26个英文大写字母或数字组成的字符串
*/
function isDigitOrLetter(theChar, name, forname, msg){
	    if(/^[A-Z0-9]+$/.test(theChar))
	    {
	        obj$(forname).innerHTML="";
	        return true;
	    }else{
	    	obj$(forname).innerHTML="<font color='red'>"+msg+"</font>";
 			obj$(name).focus();
 			obj$(name).value="";
	 		return false; 
	    }
 		}

/**
 * 是否是整数
 */
function isInt(theStr)
{
	for (var i=0 ;i < theStr.length;i++)
	{
		if (isDigit(theStr.substring(i,i+1)) == false)
		{
			return false;
			break;
		}
	}
	return true ;
}

/**
 * 判断是否在两个数之间
 */
function isBetween (val, lo ,hi)
{
if ((val < lo) || (val > hi))
{
	return false;
}
else
{
	return true;
}
}
/**
 * 返回上一页
 */
function Back()
{
history.back();
}

/** **********************日期函数******************************* */

/**
 * 取出系统当前日期
 */
function getToday()
{
  var d = new Date();
  var sDate = d.getYear()*10000+(d.getMonth()+1)*100+d.getDate();

  return sDate.toString();
}

/**
 * 取出给定日期后的第几日
 */
function getAfterDay(sBeginDay,inDay)
{
  var sReturn = "" ;
  var d = new Date(getYear(sBeginDay),getMonth(sBeginDay),getDay(sBeginDay));
  sReturn = (d.getYear()*10000+d.getMonth()*100+d.getDate()+parseFloat(inDay)).toString();

  return sReturn ;
}

/**
 * 取出年
 */
function getYear(sDate)
{
  return sDate.substring(0,4);
}

/**
 * 取出月
 */
function getMonth(sDate)
{
  return sDate.substring(4,6);
}

/**
 * 取出日
 */
function getDay(sDate)
{
  return sDate.substring(6,8);
}


function isDateArea(str1,str2){
	
	if(str1!="" && str2!=""){
		
        if(str1<=str2){
        return true;
        }else{
        	alert("结束日期不能小于起始日期！");
        	return false;
        }
        }else{
        	
        	return true;
        }
}

// ===============================
// 作用：选中节点对象
// 参数：nobj node对象
// cobj checkbox对象
// ===============================
var aNodes=document.getElementsByName("ids");
var trs = document.getElementsByTagName('tr');
function checkNode(id,pid,_hc,checked) {
	// 1、递归选父节点对象（无论是叶节点还是中间节点）
	// 判断同级中有无被选中的，如果有选中的就不可以反选
	
	if(!isHaveBNode(id,pid)){
		if(checked){
			// 选中就一直选到根节点
		// checkPNodeRecursion(pid,checked);
		}else{
		// 去掉选中仅将其父节点去掉选中s
			checkPNode(pid,checked);
		}
	}	
	
	// 2、如果是中间结点，具有儿子，递归选子节点对象
	if(_hc)	
	
	checkSNodeRecursion(id,checked);
	
}

// ===============================
// 作用：判断同级中有无被选中的
// 参数：id 节点id
// pid 节点的父节点id
// ===============================
function isHaveBNode(id,pid) {	
	var isChecked = false;
	for (var n=0; n<aNodes.length; n++) {
		// 不是节点自身、具有同父节点兄弟节点
		
		if (aNodes[n].pid!=0&&aNodes[n].id!=id&&aNodes[n].pid == pid) {			
			if(eval("obj$("+ aNodes[n].id + ").checked"))
				isChecked = true;		
		}
		
	}
	return isChecked;
}
// ===============================
// 作用：递归选中父节点对象
// 参数：pid 节点的父节点id
// ischecked 是否被选中
// ===============================
function checkPNodeRecursion(pid,ischecked) {	
	for (var n=0; n<aNodes.length; n++) {
		if (aNodes[n].pid!=0&&aNodes[n].id == pid) {	
			eval("obj$("+ aNodes[n].id + ").checked =" + ischecked);
			checkPNodeRecursion(aNodes[n].pid,ischecked);
			break;
		}
	}
}

// ===============================
// 作用：递归选中子节点对象
// 参数：id 节点id
// ischecked 是否被选中
// ===============================
function checkSNodeRecursion(id,ischecked) {	

	for (var n=0; n<aNodes.length; n++) {
		if (aNodes[n].pid!=0&&aNodes[n].pid == id) {	
		// alert("document.all."+ aNodes[n].id + ".checked =" + ischecked);
			eval("obj$("+ aNodes[n].id + ").checked =" + ischecked);
			if(ischecked==true){
			trs[n+2].className = (aNodes[n].id.checked!=true)?'click':'';
			}else{
				// trs[n+2].className =
				// (trs[n+2].className!='click')?'click':'';
				trs[n+2].className = (aNodes[n].id.checked==true)?'click':'';
			}	
			if(aNodes[n]._hc!="false"){
					checkSNodeRecursion(aNodes[n].id,ischecked);	
				}	
		}
	}
}

// ===============================
// 作用：仅选中父节点对象
// 参数：pid 节点的父节点id
// ischecked 是否被选中
// ===============================
function checkPNode(pid,ischecked) {	
	for (var n=0; n<aNodes.length; n++) {
		if (aNodes[n].pid!=0&&aNodes[n].id == pid) {			
			eval("obj$("+ aNodes[n].id + ").checked =" + ischecked);
			trs[n+2].className = (aNodes[n].id.checked==true)?'click':'';
			break;
		}
	}
}


function selectline(row){
	
	var users=document.getElementsByName("ids");
	if(users[row-2].disabled!=true){
	trs[row].className = (users[row-2].checked!=true)?'click':'';
	// 选中该行
	users[row-2].checked=(users[row-2].checked!=true)?true:false;
	checkNode(users[row-2].id,users[row-2].pid,users[row-2]._hc,users[row-2].checked);
	}
}
function selectline2(row){
	var users=document.getElementsByName("ids");
	if(users[row].disabled!=true){
	// 选中该行
	users[row].checked=(users[row].checked==true)?false:true;
	checkNode(users[row].id,users[row].pid,users[row]._hc,users[row].checked);
	}
}

// 提取查找字符串前面所有的字符
function getFront(mainStr,searchStr){
  foundOffset=mainStr.indexOf(searchStr);
  if(foundOffset==-1){
     return null;
  }
  return mainStr.substring(0,foundOffset);
}

// 提取查找字符串后面的所有字符
function getEnd(mainStr,searchStr){
  foundOffset=mainStr.indexOf(searchStr);
  if(foundOffset==-1){
     return null;
  }
  return mainStr.substring(foundOffset+searchStr.length,mainStr.length);
}

// 在字符串 searchStr 前面插入字符串 insertStr
function insertString(mainStr,searchStr,insertStr){
  var front=getFront(mainStr,searchStr);
  var end=getEnd(mainStr,searchStr);
  if(front!=null && end!=null){
     return front+insertStr+searchStr+end;
  }
  return null;
}

// 删除字符串 deleteStr
function deleteString(mainStr,deleteStr){
  return replaceString(mainStr,deleteStr,"");
}

// 将字符串 searchStr 修改为 replaceStr
function replaceString(mainStr,searchStr,replaceStr){
  var front=getFront(mainStr,searchStr);
  var end=getEnd(mainStr,searchStr);
  if(front!=null && end!=null){
     return front+replaceStr+end;
  }
  return null;
}
// 删除重复子字符串返回无重复字符串
function dropcf(strtemp){
var str=strtemp;
var strArr=str.split(",");

// 排序
strArr.sort();
var result=new Array();
var tempStr="";
for(var i in strArr)
{
   if(strArr[i] != tempStr)
   {
        result.push(strArr[i]);
        tempStr=strArr[i];
   }
   else
   {
        continue;
   }
}
return (result.join(","));
}


// 验证是否包含特殊字符
function strangecode(code) {
	var strangCode = "~`#%^&\'\"<>";  
  	var temp;  
  	for(var i=0; i<code.length; i++) {  
		temp = code.substring(i,i+1);  
 		if(strangCode.indexOf(temp) != -1) {  
 			alert("文本框中含有非法字符[ "   +   temp   +   " ]！");  
   			return  false;  
		}  
	}  
 	return true;  
 }

// 验证是否生效日期大于失效日期，是：返回false;
function dateorder(startTime,endTime){
var d1 = new Date(startTime.replace(/\-/g, "\/"));

var d2 = new Date(endTime.replace(/\-/g, "\/"));        

if(d1 > d2) {

	// alert("开始日期必须小于或等于结束日期！");

        return false;

}else{
     return true;
} 
}

// 验证是否生效日期大于等于失效日期，是：返回false;
function dateNotBefore(startTime,endTime){
	var d1 = new Date(startTime.replace(/\-/g, "\/"));
	var d2 = new Date(endTime.replace(/\-/g, "\/"));        
	if(d1 >= d2) {
		return false;
	}else{
		return true;
	} 
}


// 弹出框
function showDialog(url, _left, _top, _width, _height, _modal, _resizable, _status, _scroll) {
		var result;
	  	var iTop = (window.screen.availHeight- 30 - _height) / 2;
      	var iLeft = (window.screen.availWidth- 10 - _width) / 2;
		
		if(_left == -1 || _top == -1) {
			_left = iLeft;
			_top = iTop;
		}
		
		var ieFeatures = "dialogWidth=" + _width + "px;" + 
			"dialogHeight=" + _height + "px;" + 
			"dialogLeft=" + _left + "px;" + 
			"dialogTop=" + _top + "px;" + 
			"resizable=" + _resizable + ";" +
			"status=" + _status + ";" + 
			"scroll=" + _scroll; 
		var otherFeatures = "width=" + _width + "," + 
			"height=" +  _height + "," + 
			"left=" + _left + "," + 
			"top=" + _top + "," + 
			"resizable=" + _resizable + "," + 
			"status=" + _status + "," + 
			"scrollable=" + _scroll;
			
		if(_modal == 'yes') {
			window.openSonModel = true;
		} else {
			window.openSonModel = false;
		}
			
		if(document.all) {	// ie
			if(_modal == 'yes') {
				result = window.showModalDialog(url, window, ieFeatures);
			} else {
				result = window.showModelessDialog(url, window, ieFeatures);
			}
		} else { // other
			otherFeatures += ',modal=' +  _modal;
			result = window.open(url, '_blank', otherFeatures);
			windowArray.push(result);
		}
		
		return result;
	}


/* 创建option */
function createOption(id, text, value) {
var p = document.getElementById(id);
option = document.createElement("option");
option.setAttribute("value", value);
txtNode = document.createTextNode(text);
option.appendChild(txtNode);
p.appendChild(option);
}
/* 创建option */
function createOption1(id, text, value, selectValue) {
var p = document.getElementById(id);
option = document.createElement("option");
option.setAttribute("value", value);
if(value==selectValue) {
	option.setAttribute("selected", true);
}
txtNode = document.createTextNode(text);
option.appendChild(txtNode);
p.appendChild(option);
}
function createOption2(id, text, value, selectValue) {
	var p = document.getElementById(id);
	option = document.createElement("option");
	option.setAttribute("value", value);
	if(value.substring(0,6)==selectValue) {
		option.setAttribute("selected", true);
	}
	txtNode = document.createTextNode(text);
	option.appendChild(txtNode);
	p.appendChild(option);
	}
/* 清空select */
function clearSelect(id) {
var p = document.getElementById(id);
for (i = p.length - 1; i >= 1; i--) {
	p.options[i] = null;
}
p.options.length = 1;
}	
/* 清空select */
function clearSelect2(id) {
var p = document.getElementById(id);
for (i = p.length - 1; i >= 0; i--) {
	p.options[i] = null;
}
p.options.length = 0;
}	

// 搜索功能
function lightSearchTd(textFieldId,tableId){
	var textValue=document.getElementById(textFieldId).value;
	if(textValue!=null&&textValue!=""){
		var tds=document.getElementById(tableId).all.tags("td");
		for(var a=3;a<tds.length;a++){
			var tdObj=tds[a];
			if(tdObj.innerText.indexOf(textValue)!=-1){
				tdObj.style.background='#FFFF99';
			}else{
				tdObj.style.background='#FFFFFF';
			}
		}
	}
}

/**
 * 如果字符串str不够长度len则补temp
 * @param str
 * @param len
 * @param temp
 * @return
 */
function getStr(str,len,temp){
	var rstr="";
	var strlen=str.length;
	var l = strlen-len;
		if(l<0){
			for(var a=0;a<-l;a++){
				rstr+=temp;
			}
		}
		return rstr+str;
	}

function getTime(num) {
    var ms = num%1000;
    num /= 1000;
    var hour = Math.floor(num/3600);
    var minute = Math.floor(num/60) % 60;
    var secend = Math.floor(num%60);
    var time = "";
    
    if(ms != 0) {
    	if(ms < 10) {
    		ms = "00" + ms;
    	} else if(ms < 100) {
    		ms = "0" + ms;
    	}
    } else {
    	ms = "000";
    }
    ms = "<font color=blue>" + ms + "</font>";
    
    if(secend != 0) {
    	if(secend < 10) {
    		secend = "0" + secend;
    	}
    	secend = "<font color=blue>" + secend + "</font>";
    } else {
    	secend = "00";
    	if(hour != 0 || minute != 0) {
    		secend = "<font color=blue>" + secend + "</font>";
    	}
    }
    
    if(minute != 0) {
    	if(minute < 10) {
    		minute = "0" + minute;
    	}
    	minute = "<font color=blue>" + minute + "</font>";
    } else {
    	minute = "00";
    	if(hour != 0) {
    		minute = "<font color=blue>" + minute + "</font>";
    	}
    }
    
    if(hour != 0) {
    	if(hour < 10) {
    		hour = "0" + hour;
    	}
    	hour = "<font color=blue>" + hour + "</font>";
    } else {
    	hour = "00";
    }
    
    return hour + "：" + minute + "：" + secend + "：" + ms;
}

//校验传入的文本框文本是否为空
function checkNull(obj,vMc){
var v;
v=obj.value;
v=v.trim();

if (v.length==0)
{
	alert(vMc+"输入值为空！");
	return false;
}
obj.value=v;
return true;
}

/*
日期加减: 
*/
function addByTransDate(dateParameter, num) {   
    var translateDate = "", dateString = "", monthString = "", dayString = "";   
    translateDate = dateParameter.replace("-", "/").replace("-", "/");;   
  
    var newDate = new Date(translateDate);   
    newDate = newDate.valueOf();   
    newDate = newDate + num * 24 * 60 * 60 * 1000;   
    newDate = new Date(newDate);   
  
    //如果月份长度少于2，则前加 0 补位   
    if ((newDate.getMonth() + 1).toString().length == 1) {   
        monthString = 0 + "" + (newDate.getMonth() + 1).toString();   
        // alert(translateDate);   
    } else {   
        monthString = (newDate.getMonth() + 1).toString();   
        // alert(translateDate);   
    }   
    //如果天数长度少于2，则前加 0 补位   
    if (newDate.getDate().toString().length == 1) {   
        dayString = 0 + "" + newDate.getDate().toString();   
    } else {   
        dayString = newDate.getDate().toString();   
    }   
    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;   
    return dateString;   
}   
/*
日期加减: 
*/ 
function reduceByTransDate(dateParameter, num) {   
    var translateDate = "", dateString = "", monthString = "", dayString = "";   
    translateDate = dateParameter.replace("-", "/").replace("-", "/");;   
  
    var newDate = new Date(translateDate);   
    newDate = newDate.valueOf();   
    newDate = newDate - num * 24 * 60 * 60 * 1000;   
    newDate = new Date(newDate);   
    //如果月份长度少于2，则前加 0 补位   
    if ((newDate.getMonth() + 1).toString().length == 1) {   
        monthString = 0 + "" + (newDate.getMonth() + 1).toString();   
        // alert(translateDate);   
    } else {   
        monthString = (newDate.getMonth() + 1).toString();   
        // alert(translateDate);   
    }   
    //如果天数长度少于2，则前加 0 补位   
    if (newDate.getDate().toString().length == 1) {   
        dayString = 0 + "" + newDate.getDate().toString();   
    } else {   
        dayString = newDate.getDate().toString();   
    }   
    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;   
    return dateString;   
}

// 创建select.option
function createSelOption(id, text, value) {
			var p = document.getElementById(id);
			option = document.createElement("option");
			option.setAttribute("value", value);
			txtNode = document.createTextNode(text);
			option.appendChild(txtNode);
			p.appendChild(option);
			}
/*
将数字金额转换成大写的中文汉字
*/			
function numToMoney(Num)
{
    for(i=Num.length-1;i>=0;i--)
    {
    Num = Num.replace(",","")//替换tomoney()中的“,”
    Num = Num.replace(" ","")//替换tomoney()中的空格
    Num = Num.replace("￥","")//替换掉可能出现的￥字符
    }
    if(isNaN(Num)) 
    {
    //验证输入的字符是否为数字
    alert("请检查小写金额是否正确");
    return;
    }
    //---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
    part = String(Num).split(".");
    newchar = ""; 
    //小数点前进行转化
    for(i=part[0].length-1;i>=0;i--)
    {
     if(part[0].length > 10){ alert("位数过大，无法计算");return "";}//若数量超过拾亿单位，提示
    tmpnewchar = ""
    perchar = part[0].charAt(i);
    switch(perchar){
    case "0": tmpnewchar="零" + tmpnewchar ;break;
    case "1": tmpnewchar="壹" + tmpnewchar ;break;
    case "2": tmpnewchar="贰" + tmpnewchar ;break;
    case "3": tmpnewchar="叁" + tmpnewchar ;break;
    case "4": tmpnewchar="肆" + tmpnewchar ;break;
    case "5": tmpnewchar="伍" + tmpnewchar ;break;
    case "6": tmpnewchar="陆" + tmpnewchar ;break;
    case "7": tmpnewchar="柒" + tmpnewchar ;break;
    case "8": tmpnewchar="捌" + tmpnewchar ;break;
    case "9": tmpnewchar="玖" + tmpnewchar ;break;
     }
     switch(part[0].length-i-1)
   {
    case 0: tmpnewchar = tmpnewchar +"元" ;break;
    case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break; 
    case 4: tmpnewchar= tmpnewchar +"万" ;break;
    case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
    case 8: tmpnewchar= tmpnewchar +"亿" ;break;
    case 9: tmpnewchar= tmpnewchar +"拾" ;break;
     }
     newchar = tmpnewchar + newchar;
    }
    //小数点之后进行转化
    if(Num.indexOf(".")!=-1)
    {
     if(part[1].length > 2)
     {
     alert("提示: 系统当前货币精确到'分', 小数点之后只能保留两位数字, 其余系统将自动截取!");
     part[1] = part[1].substr(0,2)
    }
     for(i=0;i<part[1].length;i++)
     {
     tmpnewchar = ""
     perchar = part[1].charAt(i)
     switch(perchar){
     case "0": tmpnewchar="零" + tmpnewchar ;break;
     case "1": tmpnewchar="壹" + tmpnewchar ;break;
     case "2": tmpnewchar="贰" + tmpnewchar ;break;
     case "3": tmpnewchar="叁" + tmpnewchar ;break;
     case "4": tmpnewchar="肆" + tmpnewchar ;break;
     case "5": tmpnewchar="伍" + tmpnewchar ;break;
     case "6": tmpnewchar="陆" + tmpnewchar ;break;
     case "7": tmpnewchar="柒" + tmpnewchar ;break;
     case "8": tmpnewchar="捌" + tmpnewchar ;break;
     case "9": tmpnewchar="玖" + tmpnewchar ;break;
    }
    if(i==0)tmpnewchar =tmpnewchar + "角";
    if(i==1)tmpnewchar = tmpnewchar + "分";
    newchar = newchar + tmpnewchar;
     }
    }
    //替换所有无用汉字
    while(newchar.search("零零") != -1)
      newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("零亿", "亿");
    newchar = newchar.replace("亿万", "亿");
    newchar = newchar.replace("零万", "万"); 
    newchar = newchar.replace("零元", "");
    // newchar = newchar.replace("零元", "元");
    newchar = newchar.replace("零角", "");
    newchar = newchar.replace("零分", "");
    if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
     newchar = newchar
    return newchar;
}

// 验证是否包含某一指定字符
function isExistSomeCode(name,forname,msg) {
	var strangCode = "-";  
  	var temp;  
  	code = val$(name);
  	for(var i=0; i<code.length; i++) {  
		temp = code.substring(i,i+1);  
 		if(strangCode.indexOf(temp) != -1) {  
 			obj$(forname).innerHTML="<font color='red'>"+msg+"</font>";
   			return  false;  
		}  
	}  
 	return true;  
 }

// 记录修改
 function selectUniqueCheckBox(e)
 {
 	var b=0;
 	var d;
 	var c=document.getElementsByName(e);
 	for(var a=0;a<c.length;a++){
 	if(c[a].checked)
 	{b++;d=c[a].value}}if(b==0){alert("请选择一条您要操作的记录！");
 	selectAll(e);
 	return false
 	}
 	else{
 		if(b>1)
 	{
	 	alert("对不起，您只能选择一条记录进行操作！");
	 	selectAll(e);
	 	return false
 	}
 	else{
 		return true
 		}
 	}
 	}
	/**
	 * 获取日期格式
	 * @method getDateString
	 * @param date 日期对象
	 * @return 日期格式
	 */
	function getDateString(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
	}
	function getTimeString(date){
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mm = date.getMinutes();
		var ss = date.getSeconds();
		return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d)
		+' '+(h < 10 ? '0' + h : h) + ':' + (mm < 10 ? '0' + mm : mm)+ ':' + (ss < 10 ? '0' + ss : ss);
	}
	/**
	 * 将数组转化成json
	 * @param arr
	 * @returns
	 */
	function arrayToJSON(arr){
		if(arr!=null&&arr.length>0&&arr instanceof Array){
			var result = "[";
			for(var i=0;i<arr.length;i++){
				result += "\""+arr[i]+"\",";
			}
			result = result.substring(0,result.length-1);
			result += "]";
			return result;
		}else{
			return null;
		}
	}
	/**
	 * trim，去左右空格
	 */
	String.prototype.trim = function () {
		return this.replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	 }
	 /**
	  * 将数字金额转化成大写的人民币
	  * @param numberValue
	  * @returns {String}
	  */
	function atoc(numberValue){
			if(numberValue == "" || numberValue == null) return;
		   	numberValue = parseFloat(numberValue).toFixed(2);  
		    var numberValue=new String(Math.round(numberValue*100)); // 数字金额  
		    var chineseValue=""; // 转换后的汉字金额  
		    var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
		    var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
		    var len=numberValue.length; // numberValue 的字符串长度  
		    var Ch1; // 数字的汉语读法  
		    var Ch2; // 数字位的汉字读法  
		    var nZero=0; // 用来计算连续的零值的个数  
		    var String3; // 指定位置的数值  
		    if(len>15){  
		    alert("超出计算范围");  
		    return "";  
		    }  
		    if (numberValue==0){  
		    chineseValue = "零元整";  
		    return chineseValue;  
		}  
		  
		    String2 = String2.substr(String2.length-len, len); // 取出对应位数的STRING2的值  
		    for(var i=0; i<len; i++){  
		    String3 = parseInt(numberValue.substr(i, 1),10); // 取出需转换的某一位的值  
		    if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){  
		    if ( String3 == 0 ){  
		    Ch1 = "";  
		    Ch2 = "";  
		    nZero = nZero + 1;  
		    }  
		        else if ( String3 != 0 && nZero != 0 ){  
		    Ch1 = "零" + String1.substr(String3, 1);  
		    Ch2 = String2.substr(i, 1);  
		    nZero = 0;  
		}  
		else{  
		    Ch1 = String1.substr(String3, 1);  
		    Ch2 = String2.substr(i, 1);  
		    nZero = 0;  
		}  
		}  
		else{ // 该位是万亿，亿，万，元位等关键位  
		    if( String3 != 0 && nZero != 0 ){  
		    Ch1 = "零" + String1.substr(String3, 1);  
		    Ch2 = String2.substr(i, 1);  
		    nZero = 0;  
		}  
		else if ( String3 != 0 && nZero == 0 ){  
		    Ch1 = String1.substr(String3, 1);  
		    Ch2 = String2.substr(i, 1);  
		    nZero = 0;  
		}  
		else if( String3 == 0 && nZero >= 3 ){  
		    Ch1 = "";  
		    Ch2 = "";  
		    nZero = nZero + 1;  
		}  
		else{  
		    Ch1 = "";  
		    Ch2 = String2.substr(i, 1);  
		    nZero = nZero + 1;  
		}  
		if( i == (len - 11) || i == (len - 3)){ // 如果该位是亿位或元位，则必须写上  
		    Ch2 = String2.substr(i, 1);  
		}  
		}  
		    chineseValue = chineseValue + Ch1 + Ch2;  
		}  
		  
		if ( String3 == 0 ){ // 最后一位（分）为0时，加上“整”  
		    chineseValue = chineseValue + "整";  
		}  
		  
		return chineseValue;  
		}
