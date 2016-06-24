$.extend($.fn.validatebox.defaults.rules, {
	minLength: {
		 validator: function(value, param){
			 return value.length >= param[0];
		 },
		 message: '内容长度不能低于 {0} 个字符'
	},
//	checkExist:{
//		 validator: function(value, param){
//			 window.d = $.ajax({
//				 type:"post",
//				 url:param[0],
//				 data:{"newValue":value,"oldValue":param[1]?param[1]:""},
//				 async:false,
//				 success:function(data){
//					 var flag = true;
//					 if(data.result == true){
//						 alert(1);
//						 flag = false;
//					 }
//					 return flag; 
//				 }
//			 });
//			 var flag = true;
//			 var a=window.d.responseText;
//			 a = JSON.parse(a);			 
//			 if(a.result == true){
//				 flag = false;
//			 } 
//			 return flag;
//		 },
//		 message: '名称已存在.'
//	},
	checkExistTrue: {
		 validator: function(value, param){
			 return false;
		 },
		 message: '{0}已存在'
	},
	checkExistFalse: {
		 validator: function(value, param){
			 return true;
		 },
		 message: '名称可用'
	},
	notNull:{
		 validator: function(value, param){
			 if($.trim(value)==""){
				 return false;
			 }
			 return true;
		 },
		 message: '该字段不能为空'
	},
	noSpecial:{
		 validator: function(value, param){
			 var reg = /^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;'\,.<>?]{0,19}$/;
			 if(!reg.test(value)){
				 return false;
			 }
			 return true;
		 },
		 message: '该字段不能有特殊字符'
	}
});