<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<center>
				<table width="90%">
					<tr>
						<td align="center" colspan="2" height="80px">
							&nbsp;
						</td>
					</tr>
				</table>
				<table bgcolor="#F8F8FF" width="90%">
					<tr>
						<td align="center" height="100px">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<img src="<%=basePath %>images/busy.gif">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2" height="60px">
							&nbsp;
						</td>
					</tr>
					<tr >
						<td align="center" colspan="2">
							<font color="FF8C00" style="font-size: 9pt;">&nbsp;<b>提示: 非法操作或没有权限操作此功能, 请登录后操作！</b>
							</font>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2" height="80px">
							&nbsp;
						</td>
					</tr>
				</table>
		</center>
	</body>
</html>
