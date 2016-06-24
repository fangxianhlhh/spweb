<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/includes.jsp"%>

<style>
.detail_info{ width:100%;border-collapse:collapse;word-break:break-all;} 
.detail_info td{border-right:#E2E2E2 solid 1px;border-bottom:#E2E2E2 solid 1px; padding:5px;}
.td1style{
		padding-top: 5px;
		text-align:left;
		padding-left: 10px;
	}
</style>

<div style="margin: 5 5 0 5;">
	
		<table class="detail_info">
			<tr>
			    <td style="width:10%;text-align:right;">操作用户：</td>
				<td style="width:20%" id="loginName_detail"></td>
				<td style="width:10%;text-align:right;">操作时间：</td>
				<td style="width:20%" id="loginTime_detail"></td>
			</tr>
			<tr>
			  
				
				<td style="width:10%;text-align:right;">操作说明：</td>
				<td colspan=5  style="width:20%" id="actionLogs_detail"></td>
			</tr>		
			
			<tr>
				<td style="width:10%;text-align:right;">描述：</td>
				<td  colspan=5    class="td1style" id="remarkDesc_detail">
				</td>
			</tr>
		</table>
	</div>
	
<script type="text/javascript">
	$(function() {
		$.getJSON(
				"${ctx}/sysActionLog/getSystemLog.do",
				{"actionId":"${param.actionId}"},
				function(data){
					$("#loginName_detail").text(data.loginName);
					$("#actionLogs_detail").text(data.actionLogs);
					$("#loginTime_detail").text(data.loginTime);
					$("#remarkDesc_detail").text(data.remarkDesc);
				}
		); 
	});
</script>

