<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var _theme,reg=new RegExp("(^| )theme=([^;]*)(;|$)");
if(_theme=document.cookie.match(reg))_theme=unescape(_theme[2]);
_theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/"+_theme+"/easyui.css\" type=\"text/css\" media=\"screen\"/>"):document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/gray/easyui.css\" type=\"text/css\" media=\"screen\"/>");_theme=null;
</script>
<link href="<%=path %>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">var contextPath="<%=path%>";</script>
<title>用户管理信息</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" style="height:40px;">
		<form id="userform">
			<table>
				<tbody>
					<tr style="color:#444;">
						<td style="width:50px;">&nbsp;登录名</td>
						<td><input class="easyui-textbox" name="username" data-options="iconCls:'icon-man'"/> </td>
						<td>&nbsp;员工姓名</td>
						<td><input class="easyui-textbox" name="name" data-options="iconCls:'icon-man'"/> </td>
						<td>&nbsp;所属部门</td>
						<td>
						<select class="easyui-combobox" id="dept" name="dept" data-options="editable:false,panelHeight:210" style="width:180px;">   
							<option value="">全部</option>
						    <option value="1">总经理办公室</option>   
						    <option value="1_110">人事部门</option> 
						    <option value="1_111">技术部门</option> 
						    <option value="1_112">行政部门</option> 
						    <option value="1_113">业务部门</option> 
						    <option value="1_114">财务部门</option> 
						    <option value="1_115">采购部门</option> 
						    <option value="1_116">后勤部门</option> 
						    <option value="0">管理员</option> 
						</select>  
						</td>
						<td>&nbsp;职位</td>
						<td><input class="easyui-textbox" name="job" data-options="iconCls:'icon-more'" /> </td>
						<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="javascript:userinfo.search()">查询</a></td>
						<td><a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="javascript:userinfo.clear()">清除</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>  
	
<div data-options="region:'center',border:false" style="width:100%;">
	<table id="user-datagrid" style="width: 100%;height:100%;"></table>
</div>	 
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/easyui/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/userinfo.js"></script>
</body>
</html>