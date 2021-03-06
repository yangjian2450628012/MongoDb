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
_theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/"+_theme+"/easyui.css\" type=\"text/css\" media=\"screen\"/>"):document.write("<link rel=\"stylesheet\" href=\"<%=path %>/easyui/css/gray/easyui.css\" type=\"text/css\" media=\"screen\"/>");_theme=null;
</script>
<link href="<%=path %>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">var contextPath="<%=path%>";</script>
<title>应用引擎</title>
</head>
<body class="easyui-layout">   
    <div data-options="region:'west',title:'数据模型信息',border:false,split:true" style="width:210px;">
    	<div style="background-color: #F4F4F4;border-width: 0 0 1px 0;border-style: solid;border-color: #dddddd;">
			<a class="easyui-linkbutton" data-options="plain:true" onclick="appEngine.editTables('add')">增加表</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a class="easyui-linkbutton" data-options="plain:true" onclick="appEngine.editTables('modify')">修改表</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a class="easyui-linkbutton" data-options="plain:true" onclick="appEngine.editTables('del')">删除表</a>
		</div>
    	<ul class="easyui-tree" id="dataMoudleTree" data-options="url:'<%=path %>/manager/queryMenu',animate:true,onClick:function(node){appEngine.onClickTree(node)},onAfterEdit:function(node){appEngine.onAfterTree(node)}"></ul>
    </div>   
    <div data-options="region:'center',title:'实体信息',border:false">
    	<form id="entityInfo" style="height:12%">
			<table>
				<tbody>
					<tr style="color:#444;">
						<td>&nbsp;业务分类</td>
						<td>
							<!-- <select class="easyui-combotree" id="subclass" style="width:180px;" data-options="readonly:true,onShowPanel:function(){appEngine.loadBusClass(this)},onSelect:function(node){appEngine.selectData(node,'#subclass')}"></select>   -->
							<input class="easyui-textbox" id="subclass" data-options="readonly:true"/>
							<input type="hidden" id="aem_entityId"/> 
							<input type="hidden" id="package_id"/> 
							<input type="hidden" id="package_name"/>
						</td>
						<td>&nbsp;实体名</td>
						<td><input class="easyui-textbox" id="entityName"/> </td>
						<td>&nbsp;备注</td>
						<td><input class="easyui-textbox" id="remark"/></td>
					</tr>
					<tr>	
						<td>&nbsp;已生成</td>
						<td><select class="easyui-combobox" id="create" data-options="editable:false,panelHeight:100" style="width:180px;">   
						    <option value="0">否</option> 
						    <option value="1">是</option>   
						    <option value="2">导入</option>
						</select>  </td>
						<td>&nbsp;生成时间</td>
						<td><input class="easyui-textbox" id="createtime" data-options="readonly:true,disabled:true" /> </td>
						<td colspan="2" align="right">
							<a class="easyui-linkbutton" onclick="appEngine.runEntityResult('save')">保存</a>
							<a class="easyui-linkbutton" onclick="appEngine.runEntityResult('reset')">重置</a>
							<a class="easyui-linkbutton" onclick="appEngine.runEntityResult('createEntity')">生成实体代码</a>
							<a class="easyui-linkbutton" onclick="appEngine.runEntityResult('fromDataSchema')">从数据表导入</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="height:88%;padding: 0 5px;">
			<table style="width: 100%;height:100%;" id="datagrid-entity" class="easyui-datagrid" data-options="onLoadSuccess:function(data){appEngine.loadSuccessData(data)},pagination:true,fitColumns:true,
			fit:true,striped:true,singleSelect:true,toolbar:'#entityTool',onClickCell:appEngine.onClickCell">   
				<thead>   
					<tr>   
						<th data-options="field:'COLUMN_NAME',width:40,align:'center',editor:'text'">属性名称</th>   
						<th data-options="field:'TYPE_NAME',width:65,align:'center',editor:{type:'combobox',options:{data:appEngine.data_type,panelHeight:160}}">数据类型</th>   
						<th data-options="field:'PRIMARYKEYS',width:50,align:'center',editor:{type:'combobox',options:{data:[{'value':'YES','text':'YES'},{'value':'NO','text':'NO'}],panelHeight:80}}">主键标记</th>   
						<th data-options="field:'IS_NULLABLE',width:50,align:'center',editor:{type:'combobox',options:{data:[{'value':'YES','text':'YES'},{'value':'NO','text':'NO'}],panelHeight:80}}">允许为空</th>   
						<th data-options="field:'IS_AUTOINCREMENT',width:50,align:'center',editor:{type:'combobox',options:{data:[{'value':'YES','text':'YES'},{'value':'NO','text':'NO'}],panelHeight:80}}">自动递增</th>   
						<th data-options="field:'COLUMN_SIZE',width:50,align:'center',editor:'numberbox'">属性长度</th>   
						<th data-options="field:'COLUMN_DEF',width:50,align:'center',editor:'text'">默认值</th>   
						<th data-options="field:'informName',width:50,align:'center'">是否字典转换</th>   
						<th data-options="field:'ORDINAL_POSITION',width:30,align:'center'">顺序</th>   
						<th data-options="field:'REMARKS',width:80,align:'center',editor:'text'">备注</th>   
					</tr>   
				</thead>   
			</table>
		</div>	
		
		<div id="entityTool">
			<span><font>实体属性列表：</font></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appEngine.editEntity('add')">添加实体属性</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true" onclick="appEngine.editEntity('del')">删除实体属性</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-tjmb',plain:true" onclick="appEngine.editEntity('maintain')">维护主从表关系</a>
			<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
			<span><font color="#0000FF">规则:属性列表中选择了自动递增，那该属性默认值必须为空。</font></span>
		</div>
							
    </div>   
</body>  
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/easyui/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/appEngine.js"></script> 
<script type="text/javascript">
$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});
appEngine.endIndex = undefined;
appEngine.data_type = [{"value":"TINYINT","text":"TINYINT","type":"java.lang.Integer"},{"value":"SMALLINT","text":"SMALLINT","type":"java.lang.Integer"},{"value":"MEDIUMINT","text":"MEDIUMINT","type":"java.lang.Integer"},{"value":"INT","text":"INT","type":"java.lang.Integer"},
	             {"value":"INTEGER","text":"INTEGER","type":"java.lang.Integer"},{"value":"BIGINT","text":"BIGINT","type":"BigInteger"},{"value":"BIT","text":"BIT","type":"java.lang.Boolean"},{"value":"REAL","text":"REAL","type":"java.lang.String"},
	             {"value":"DOUBLE","text":"DOUBLE","type":"java.lang.Double"},{"value":"FLOAT","text":"FLOAT","type":"java.lang.Float"},{"value":"DECIMAL","text":"DECIMAL","type":"BigDecimal"},{"value":"NUMERIC","text":"NUMERIC","type":"java.math.BigDecimal"},
	             {"value":"CHAR","text":"CHAR","type":"java.lang.String"},{"value":"VARCHAR","text":"VARCHAR","type":"java.lang.String"},{"value":"DATE","text":"DATE","type":"java.sql.Date"},{"value":"TIME","text":"TIME","type":"java.sql.Time"},
	             {"value":"YEAR","text":"YEAR","type":"java.sql.Date"},{"value":"TIMESTAMP","text":"TIMESTAMP","type":"java.sql.Timestamp"},{"value":"DATETIME","text":"DATETIME","type":"java.sql.Timestamp"},{"value":"TINYBLOB","text":"TINYBLOB","type":"java.lang.byte[]"},
	             {"value":"BLOB","text":"BLOB","type":"byte[]"},{"value":"MEDIUMBLOB","text":"MEDIUMBLOB","type":"java.sql.Timestamp"},{"value":"LONGBLOB","text":"LONGBLOB","type":"java.sql.Timestamp"},{"value":"TINYTEXT","text":"TINYTEXT","type":"java.lang.byte[]"},
	             {"value":"TEXT","text":"TEXT","type":"java.lang.byte[]"},{"value":"MEDIUMTEXT","text":"MEDIUMTEXT","type":"java.sql.Timestamp"},{"value":"LONGTEXT","text":"LONGTEXT","type":"java.sql.Timestamp"},{"value":"ENUM","text":"ENUM","type":"java.lang.byte[]"},
	             {"value":"SET","text":"SET","type":"java.lang.byte[]"},{"value":"BINARY","text":"BINARY","type":"java.sql.Timestamp"},{"value":"varbinary","text":"VARBINARY","type":"java.sql.Timestamp"},{"value":"VARBINARY","text":"VARBINARY","type":"java.lang.byte[]"},
	             {"value":"VARBINARY","text":"VARBINARY","type":"java.lang.byte[]"},{"value":"VARBINARY","text":"VARBINARY","type":"java.sql.Timestamp"},{"value":"POINT","text":"POINT","type":"java.sql.Timestamp"},{"value":"LINESTRING","text":"LINESTRING","type":"java.lang.byte[]"},
	             {"value":"POLYGON","text":"POLYGON","type":"java.lang.byte[]"},{"value":"GEOMETRY","text":"GEOMETRY","type":"java.sql.Timestamp"},{"value":"MULTIPOINT","text":"MULTIPOINT","type":"java.sql.Timestamp"},{"value":"MULTILINESTRING","text":"MULTILINESTRING","type":"java.lang.byte[]"},
	             {"value":"MULTIPOLYGON","text":"MULTIPOLYGON","type":"java.lang.byte[]"},{"value":"GEOMETRYCOLLECTION","text":"GEOMETRYCOLLECTION","type":"java.sql.Timestamp"},{"value":"MULTIPOINT","text":"MULTIPOINT","type":"java.sql.Timestamp"},{"value":"MULTILINESTRING","text":"MULTILINESTRING","type":"java.lang.byte[]"}];
</script>
</html>