<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var _theme,reg=new RegExp("(^| )theme=([^;]*)(;|$)");
if(_theme=document.cookie.match(reg))_theme=unescape(_theme[2]);
_theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/"+_theme+"/easyui.css\" type=\"text/css\" media=\"screen\"/>"):document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/default/easyui.css\" type=\"text/css\" media=\"screen\"/>");_theme=null;
</script>
<link href="<%=path %>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">var contextPath="<%=path%>";</script>
<title>应用引擎</title>
</head>
<body class="easyui-layout">   
    <div data-options="region:'west',title:'数据模型信息',border:false,split:true" style="width:200px;">
    	<ul class="easyui-tree" id="dataMoudleTree" data-options="url:'<%=path %>/manager/queryMenu',animate:true"></ul>
    </div>   
    <div data-options="region:'center',title:'实体信息',border:false">
    	<form id="entityInfo" style="height:11%">
			<table>
				<tbody>
					<tr style="color:#444;">
						<td>&nbsp;业务分类</td>
						<td>
							<select class="easyui-combotree" name="subClass" id="subclass" style="width:180px;" data-options="onShowPanel:function(){appEngine.loadBusClass(this)},onSelect:function(node){appEngine.selectData(node,'#subclass')}"></select>  
						</td>
						<td>&nbsp;实体名</td>
						<td><input class="easyui-textbox" name="entityName"/> </td>
						<td>&nbsp;备注</td>
						<td><input class="easyui-textbox" name="remark"/></td>
					</tr>
					<tr>	
						<td>&nbsp;已生成</td>
						<td><select class="easyui-combobox" name="create" data-options="editable:false" style="width:180px;">   
							<option value=""></option>
						    <option value="yes">是</option>   
						    <option value="no">否</option> 
						    <option value="export">导入</option>
						</select>  </td>
						<td>&nbsp;生成时间</td>
						<td><input class="easyui-textbox" name="job" data-options="readonly:true" /> </td>
						<td colspan="3" align="right">
							<a class="easyui-linkbutton" >保存</a>
							<a class="easyui-linkbutton" >重置</a>
							<a class="easyui-linkbutton" >生成实体代码</a>
							<a class="easyui-linkbutton" >导出Table.xml</a>
							<a class="easyui-linkbutton" >从Table.xml导入</a>
							<a class="easyui-linkbutton" >从数据表导入</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="height:89%;padding: 0 5px;">
			<table style="width: 100%;height:100%;" class="easyui-datagrid" data-options="url:'<%=path %>/json/inform.json',pagination:true,fitColumns:true,fit:true,striped:true,singleSelect:true,toolbar:'#entityTool'">   
				<thead>   
					<tr>   
						<th data-options="field:'name',width:40,align:'center'">属性名称</th>   
						<th data-options="field:'type',width:40,align:'center'">数据类型</th>   
						<th data-options="field:'key',width:50,align:'center'">主键标记</th>   
						<th data-options="field:'isempty',width:50,align:'center'">允许为空</th>   
						<th data-options="field:'lucency',width:50,align:'center'">透明标记</th>   
						<th data-options="field:'prolength',width:50,align:'center'">属性长度</th>   
						<th data-options="field:'creater',width:50,align:'center'">默认值</th>   
						<th data-options="field:'informName',width:50,align:'center'">是否字典转换</th>   
						<th data-options="field:'createtime',width:30,align:'center'">顺序</th>   
						<th data-options="field:'createtime',width:80,align:'center'">备注</th>   
					</tr>   
				</thead>   
			</table>
		</div>	
		
		<div id="entityTool">
			<span><font>实体属性列表：</font></span>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加实体属性</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true">删除实体属性</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tjmb',plain:true">维护主从表关系</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<span><font color="#0000FF">规则:属性列表中必须存在一个名称为"ID"的主键约束。</font></span>
		</div>
							
    </div>   
</body>  
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/easyui/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/appEngine.js"></script> 
</html>