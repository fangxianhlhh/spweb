<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 
    JSTL 1.0 的声明是：  http://java.sun.com/jstl/core 
    JSTL1.1 的声明是：http://java.sun.com/jsp/jstl/core 
 -->

<%
	String path = request.getContextPath()+ "/";
	String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path;
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="webroot" value="<%=basePath%>"/>


  