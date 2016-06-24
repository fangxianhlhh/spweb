<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/jquery-2.0.3.min.js"></script>

<script type="text/javascript">
(function($){
 $.extend({
		liu:function(tre){
			return 'liu='+tre ;
		}
	});
})(jQuery);
</script>

<script type="text/javascript">
(function($){
	$.fn.extend({
		zhang:function(){
			return 'zhang--';
		}
	});
	
	$.fn.xie=function(t){
	   return 'xie---'+t;	
	};
	
	$.fn.hilight = function(options) {    
		 var defaults = {    
		   foreground: 'red',    
		   background: 'yellow'    
		 };    
		  // Extend our default options with those provided.    
		 var opts = $.extend(defaults, options);    
		  // Our plugin implementation code goes here.    
	}; 
	
	
	$.fn.extend({
	    color:function(val)
	    {
	          if(val==undefined){
	         
	               return $(this).css("color");
	            }else{
	    
	              return $(this).css("color",val);
	            }
	    }
	});
	
	$.fn.extend({        
        
	     alertWhileClick:function(){        
	       
	         $(this).click(function(){        
	       
	              alert($(this).val());        
	          });        
	        
	      }        
	        
	});        
	
		
})(jQuery);


</script>

<script type="text/javascript">
$(function(){
	/* var yu= $.liu('111');
	alert("yu="+yu); */
	
	/*  var yu1= $("").zhang();
	 alert("yu1="+yu1); */
	 
	 $("#tryu").alertWhileClick();
	 
	//var s=5;
	//var t=$('').xie(s);
	// 我们的插件可以这样被调用：  
	//$('#tyui').hilight({    
	//  foreground: 'blue'    
	//}); 
	
});

function changecol(){
	alert('123');
	$(this).color("red");//对jquery对象进行颜色设置 
	$("#test1").css('background-color','red');
};
</script>

</head>
<body>
 this id one
 <input type="button" value="测试" onclick="changecol();">
 <div id="test1" style="width: 200px; height: 200px;" >
       werwhafhia
 </div>
 <div id="tyui">thisafdafjoalladfnj</div>
 
 <input id="tryu"  value="tewes1213">

</body>
</html>