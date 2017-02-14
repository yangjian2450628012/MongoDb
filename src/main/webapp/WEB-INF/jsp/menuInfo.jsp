<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理信息</title>
<script type="text/javascript">
var _theme,reg=new RegExp("(^| )theme=([^;]*)(;|$)");
if(_theme=document.cookie.match(reg))_theme=unescape(_theme[2]);
_theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/"+_theme+"/easyui.css\" type=\"text/css\" media=\"screen\"/>"):document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/gray/easyui.css\" type=\"text/css\" media=\"screen\"/>");_theme=null;
</script>
<link href="<%=path %>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">var contextPath="<%=path%>";</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:40px;">
	<form id="menuform">
		<table>
			<tbody>
				<tr style="color:#444;">
					<td style="width:50px;">&nbsp;主菜单</td>
					<td><input class="easyui-textbox" name="username" data-options="iconCls:'icon-man'"/> </td>
					<td>&nbsp;二级菜单</td>
					<td><input class="easyui-textbox" name="name" data-options="iconCls:'icon-man'"/> </td>
					<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="javascript:userinfo.search()">查询</a></td>
					<td><a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="javascript:userinfo.clear()">清除</a></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>  
<div data-options="region:'center',border:false" style="width:100%;">
	<table id="menu-datagrid" style="width: 100%;height:100%;"></table>
</div>	 
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/easyui/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/menuinfo.js"></script>
</body>
</html>