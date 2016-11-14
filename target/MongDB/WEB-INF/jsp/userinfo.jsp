<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 根据cookie 动态加载样式-->
<script type="text/javascript">
	var theme = document.cookie.split(";")[0].split("=")[1];
	theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"easyui/css/"+theme+"/easyui.css\" type=\"text/css\" media=\"screen\"/>"):document.write("<link rel=\"stylesheet\" href=\"easyui/css/default/easyui.css\" type=\"text/css\" media=\"screen\"/>");
</script>
<%-- <link href="<%=path %>/easyui/css/default/easyui.css" rel="stylesheet" type="text/css" id="ch-easyuiTheme"/> --%>
<link href="<%=path %>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<title>用户管理信息</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" style="height:40px;background-color: #eee;">
		<form>
			<table>
				<tbody>
					<tr>
						<td>登录名:</td>
						<td><input class="easyui-textbox" name="username" data-options="iconCls:'icon-man'"/> </td>
						<td>用户权限:</td>
						<td><input class="easyui-textbox" name="username" data-options="iconCls:'icon-lock'"/> </td>
						<td>用户功能:</td>
						<td><input class="easyui-textbox" name="username" data-options="iconCls:'icon-more'" /> </td>
						<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="javascript:search()">查询</a></td>
						<td><a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="javascript:clear()">清除</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>  
	
<div data-options="region:'center',border:false" style="width:100%;">
	<table id="user-datagrid" style="width: 100%;height:100%;" title="用户列表"></table>
</div>	 
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/easyui/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/userinfo.js"></script>
</body>
</html>