 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/includes.jsp"%>  
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Basic TreeGrid - jQuery EasyUI Demo</title>
        <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.4.3/themes/icon.css">
        <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/jquery.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    </head>
    <body>
        <h2>Basic TreeGrid</h2>
        <p>TreeGrid allows you to expand or collapse group rows.</p>
        <div style="margin:20px 0;"></div>
        <table title="Folder Browser" class="easyui-treegrid" style="width:700px;height:250px"
                data-options="
                 url: '${ctx}/js/jquery-easyui-1.4.3/test1.json',
                 method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'name'
            ">
        <thead>
            <tr>
                <th data-options="field:'name'" width="220">Name</th>
                <th data-options="field:'size'" width="100" align="right">Size</th>
                <th data-options="field:'date'" width="150">Modified Date</th>
            </tr>
        </thead>
    </table>
 
</body>
</html>