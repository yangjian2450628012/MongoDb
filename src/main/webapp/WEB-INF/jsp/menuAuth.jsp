<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyui/css/jqueryZtree.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jqueryZtree.js"></script>
<script type="text/javascript">
var setting = {
		data: {
			simpleData: {
				enable: true
			}
	}
};
var data = <%=request.getAttribute("data") %>;
if(data.secondMenu !="" && typeof eval("("+data.secondMenu+")") =="object")
{
var zNodeAll = eval("("+data.authmenu+")"); //string --> json
	for (var i = 0,_d = eval("("+data.secondMenu+")"); i < _d.length; i++) {
		if(_d[i]["child"] != "" && typeof _d[i]["child"] =="object"){
			for (var j = 0,_f = _d[i]["child"]; j < _f.length; j++) {
				zNodeAll.push(_f[j]);
			}
			_d[i]["child"] = "";
		}
		zNodeAll.push(_d[i]);
	}
$.fn.zTree.init($("#treeAll"), setting, zNodeAll);
}	


var _data = <%=request.getAttribute("_data") %>;
var zNodes = Array();
$("#treeM").attr("userid",_data._id);
if(_data.authmenu !="" && typeof eval("("+_data.authmenu+")") =="object")
{
	zNodes= eval("("+_data.authmenu+")");
	if(_data.secondMenu !="" && typeof eval("("+_data.secondMenu+")") =="object" )
	{
		for (var i = 0,_d = eval("("+_data.secondMenu+")"); i < _d.length; i++) {
			if(_d[i]["child"] != "" && typeof _d[i]["child"] =="object" ){
				for (var j = 0,_f = _d[i]["child"]; j < _f.length; j++) {
					zNodes.push(_f[j]);
				}
				_d[i]["child"] = "";
			}
			zNodes.push(_d[i]);
		}
	}	
}	
$.fn.zTree.init($("#treeM"), setting, zNodes);
zNodes = null;
$('#authAll').bind('click', function(){    
	var treeObj = $.fn.zTree.getZTreeObj("treeAll");
	var nodes = treeObj.getSelectedNodes();
	//获取节点
	if(nodes.length > 0)
	{
		var treeCh = $.fn.zTree.getZTreeObj("treeM");
		for ( var d in nodes) {
			if(treeCh.getNodeByParam("id", nodes[d].id) == null){//判断该节点是否已存在
				if(nodes[d].pId == null){ //是根节点全部添加到用户权限
					//判断当前节点得Index
					var _index = treeObj.getNodeIndex(nodes[d]);
					//获取右边的节点序号
					var _nodech = treeCh.getNodes();
					if (_nodech.length > 0) {
						var i = nodes[d].getPreNode()!=null ? treeCh.getNodeIndex(treeCh.getNodeByParam("name", nodes[d].getPreNode().name)):null;
						if(i !=null && i != -1){
							treeCh.addNodes(null,i+1,nodes);
							return;
						}
						var __i =nodes[d].getNextNode()!=null ? treeCh.getNodeIndex(treeCh.getNodeByParam("name", nodes[d].getNextNode().name)):null;
						if(__i !=null && __i != -1){
							treeCh.addNodes(null,__i-1,nodes);
							return;
						}
						
						var _b = false;
						for ( var _ch in _nodech) {
							var _i = treeCh.getNodeIndex(_nodech[_ch]);
							if(_i > _index){
								treeCh.addNodes(null,_i-1,nodes);
								_b = true;
								break;
							}
						}
						if(!_b){
							treeCh.addNodes(null,nodes,true); //增加 1 个根节点
						}
					}else{
						treeCh.addNodes(null,nodes,true); //增加 1 个根节点
					}
				}else { //不是节点,添加到对应节点上
					var nodeP = treeCh.getNodeByParam("id", nodes[d].pId) //用当前选中的pId查找有没有父节点
					if(nodeP !=null ){ //已经有父节点
						treeCh.addNodes(nodeP,treeObj.getNodeIndex(nodes[d]),{name:nodes[d].name,id:nodes[d].id,pId:nodes[d].pId,imgpath:nodes[d].imgpath}); //增加 节点
					}else{//还没有父节点，自动创建父节点
						var nodeallP = treeObj.getNodeByParam("id", nodes[d].pId); //用当前选中的pId查找父节点
						var _nodeallP = treeObj.getNodeByParam("id", nodeallP.pId); //用当前选中的pId查找父节点
						if(_nodeallP != null){
							treeCh.addNodes(null,treeObj.getNodeIndex(_nodeallP),{name:_nodeallP.name,id:_nodeallP.id,pId:_nodeallP.pId,imgpath:_nodeallP.imgpath}); 
							treeCh.addNodes(treeCh.getNodeByParam("id", _nodeallP.id),treeObj.getNodeIndex(nodeallP),{name:nodeallP.name,id:nodeallP.id,pId:nodeallP.pId,imgpath:nodeallP.imgpath}); 
							treeCh.addNodes(treeCh.getNodeByParam("id", nodeallP.id),treeObj.getNodeIndex(nodes[d]),{name:nodes[d].name,id:nodes[d].id,pId:nodes[d].pId,imgpath:nodes[d].imgpath}); 
						}else{
							treeCh.addNodes(null,treeObj.getNodeIndex(nodeallP),{name:nodeallP.name,id:nodeallP.id,pId:nodeallP.pId,imgpath:nodeallP.imgpath});
							treeCh.addNodes(treeCh.getNodeByParam("id", nodeallP.id),treeObj.getNodeIndex(nodes[d]),{name:nodes[d].name,id:nodes[d].id,pId:nodes[d].pId,imgpath:nodes[d].imgpath}); 
						}
					}
				}
			}
		}
	}
});
/* $('#authAll').bind('click', function(){ 
	//重新构建数组
	var treeObj = $.fn.zTree.getZTreeObj("treeAll");
	var nodes = treeObj.getSelectedNodes();
	if(nodes.length > 0)
	{
		var treeCh = $.fn.zTree.getZTreeObj("treeM");
		var nodesCh = treeCh.getNodes() != null ? treeCh.transformToArray(treeCh.getNodes()) : Array();
		for(var d in nodes){//拼接array
			if(treeCh.getNodeByParam("id", nodes[d].id) == null){
				nodesCh.push(nodes[d]);
			}
			if(nodes[d].children != undefined){
				var _node = nodes[d].children;
				for ( var _d in _node) {
					if(treeCh.getNodeByParam("id", _node[_d].id) == null){
						nodesCh.push(_node[_d]);
					}
					if(_node[_d].children != undefined){
						var __node = _node[_d].children;
						for ( var __d in __node) {
							if(treeCh.getNodeByParam("id", __node[__d].id) == null){
								nodesCh.push(__node[__d]);
							}
						}
					}
				}
			}
		}
		treeCh.addNodes(null, nodesCh);
	}
}); */


$('#authUser').bind('click', function(){    
	var treeObj = $.fn.zTree.getZTreeObj("treeM");
	var nodes = treeObj.getSelectedNodes();
	for (var i=0, l=nodes.length; i < l; i++) {
		treeObj.removeNode(nodes[i]);
	}
}); 

</script>
<div id="cc" class="easyui-layout" data-options="border:false,fit:true">   
    <div data-options="region:'west',split:true,collapsible:false" style="width:50%;">
	    <div class="panel-header" style="background: #eee;">
	    	<div class="panel-title" style="color:#444;font-weight: normal;">全部菜单权限<%if(!"admin".equals(request.getAttribute("username"))){%><a id="authAll" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-right_arrow',iconAlign:'right'" style="float: right;margin-top: -5px;">添加</a><%} %>  
	    	</div>
	    </div>
	    <div>
		   <ul id="treeAll" class="ztree" style="padding: 5px 5px;"></ul>
		</div>
    </div>   
    <div data-options="region:'center'">
    	<div class="panel-header" style="background: #eee;">
	    	<div class="panel-title" style="color:#444;font-weight: normal;">拥有权限<%if(!"admin".equals(request.getAttribute("username"))){%><a id="authUser" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-left_arrow'" style="float: right;margin-top: -5px;">移除</a><%} %>  
	    	</div>
	    </div>
	    <div>
		   <ul id="treeM" class="ztree" userid="" style="padding: 5px 5px;"></ul>
		</div>
    </div>   
</div>  