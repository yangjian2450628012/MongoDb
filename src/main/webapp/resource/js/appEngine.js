var appEngine = {
	loadBusClass : function(dom){
		$(dom).combotree('loadData', $("#dataMoudleTree").tree("getRoots"));
	},
	selectData : function(node,dom){
		if(node.children != undefined && typeof node.children == "object")$(dom).combotree("clear");
	},
	onClickTree : function(node){
		if(node.ylzd != "en")return;
		if(node.text.indexOf("<span") !=-1)$("#subclass").textbox("setValue",node.text.substr(0,node.text.indexOf("<span")));
		else $("#subclass").textbox("setValue",node.text);
		$("#package_id").val(node.id);
		$("#package_name").val(node.packageN);
		$("#datagrid-entity").datagrid("options").url = contextPath+"/manager/queryEntity?random="+Math.random();
		$("#datagrid-entity").datagrid('load',{
			id : node.id,
			tableName : ''
		});
	}
	,loadSuccessData : function(data){
		if(data.rows.length > 0 && data.rows[0].name != undefined ){
			var entity = data.rows[0];
			$("#entityName").textbox("setValue",entity.name);
			$("#remark").textbox("setValue",entity.comm);
			$("#create").combobox("setValue",entity.create_flag);
			$("#createtime").textbox("setValue",entity.createtime);
			$("#datagrid-entity").datagrid("deleteRow",0);
			$("#aem_entityId").val(entity.id);
			var rows = $("#datagrid-entity").datagrid("getRows");
			for(var i=0;i<rows.length;i++){
				if(rows[i].DATA_TYPE == '-4'){
					$("#datagrid-entity").datagrid("getRows")[i].SOURCE_DATA_TYPE = appEngine.data_type[20].type;
				}else if(rows[i].DATA_TYPE == '-5'){
					
				}else{
					$("#datagrid-entity").datagrid("getRows")[i].SOURCE_DATA_TYPE = appEngine.data_type[parseInt(rows[i].DATA_TYPE)].type;
				}
			}
			return;
		}
		if(data.rows[0].status !=undefined && data.rows[0].status == "false"){
			$("#datagrid-entity").datagrid("deleteRow",0); //清空列表
			$("#entityName").textbox("clear");
			$("#remark").textbox("clear");
			$("#create").combobox("clear");
			$("#createtime").textbox("clear");
			$("#aem_entityId").val("");
			return;
		}
	}
	,editTables : function(type){
		var node = $('#dataMoudleTree').tree('getSelected');
		if(!node) return; 
		if(type == "add")
		{
			if(node.ylzd == "mn"){
				var uuid = appEngine.getNewUUID(6);
				$('#dataMoudleTree').tree('append',{
					parent: node.target,
					data : [{id: uuid,text: '',pid:node.id,ylzd:'1',url:'',ylzd:'en',packageN:node.packageN}]
				});
				//开启编辑
				var node = $('#dataMoudleTree').tree('find', uuid);
				$('#dataMoudleTree').tree('beginEdit',node.target);
				$(node.target).find(".tree-editor").width('100px');
			}
		}else if(type == "modify"){
			if(node.ylzd == "en"){
				if(node.text.indexOf("<span") !=-1)node.text = node.text.substr(0,node.text.indexOf("<span"));
				$('#dataMoudleTree').tree('beginEdit',node.target);
				$(node.target).find(".tree-editor").width('100px');
			}
		}else if(type == "del"){
			if(node.ylzd == "en"){
				var data = node;
				data.type = "del";
				data.children = "";
				data.text = "del";
				appEngine.onAfterTree(data); //调用数据操作公共方法
			}
		}
	},
	onAfterTree : function(node){
		if(node.text == "" || node.text == undefined || node.text == null)
		{
			$('#dataMoudleTree').tree('remove',node.target);
			return;
		}
		var data = undefined;
		if(typeof node.children == "object" && node.children == null){ //修改
			data = new Object();
			data.type = 'modify';
			data.id = node.id;
			data.text = node.text;
		}else{ //新增 && 删除
			data = node;data.target = "";
		}
		$.messager.progress({title : "温馨提示",msg : "请稍后，正在处理......"});
		//保存数据
		$.ajax({
			   type: "POST",
			   url: contextPath+"/manager/editDataTable?random="+Math.random(),
			   dataType : "JSON",
			   data: {data:JSON.stringify(data)},
			   success: function(result){
				   $.messager.progress("close");
				   if(result.status){
					   $.messager.show({title:'温馨提示',msg:result.msg,timeout:1000,showType:'show'});
					   //刷新tree
					   $("#dataMoudleTree").tree("reload");
				   }else{
					   $.messager.alert("温馨提示", result.msg,"info");
				   }
			   },error : function(msg){
				   $.messager.progress("close");
				   $.messager.alert("温馨提示", result.msg,"info");
			   }
		});
	},
	getNewUUID : function(length){
		var s = [];
	    var h = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    for (var i = 0; i < length; i++) {
	        s[i] = h.substr(Math.floor(Math.random() * 0x10), 1);
	    }
	    return s.join("");
	},endEditing:function(index, field){
		if (appEngine.endIndex == undefined){return true}
		if ($('#datagrid-entity').datagrid('validateRow', appEngine.endIndex)){
			//验证数据
			$('#datagrid-entity').datagrid('endEdit', appEngine.endIndex);
			appEngine.endIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	onClickCell : function(index, field){
		if (appEngine.endEditing(index, field)){
			$('#datagrid-entity').datagrid('selectRow', index)
					.datagrid('editCell', {index:index,field:field});
			appEngine.endIndex = index;
		}
	},
	editEntity : function(type){
		if(type == 'add'){
			var counts = $('#datagrid-entity').datagrid('getRows').length;
			$('#datagrid-entity').datagrid('appendRow',{ORDINAL_POSITION: counts + 1});
		}else if(type == "del"){
			var row = $('#datagrid-entity').datagrid('getSelected');
			var index = $('#datagrid-entity').datagrid('getRowIndex',row);
			$('#datagrid-entity').datagrid('deleteRow',index); //动态删除
		}else if(type == 'maintain'){
			
		}
	},
//	type_nameSelect : function(record){
//		if(appEngine.endIndex == undefined)return;
//		$('#datagrid-entity').datagrid("getRows")[appEngine.endIndex].SOURCE_DATA_TYPE = record.type;
//	},
	runEntityResult : function(type){
		if(type == 'reset'){ //重置
			$('#entityName').textbox('clear');
			$('#remark').textbox('clear');
			$('#create').combobox('clear');
			$('#createtime').textbox('clear');
			$('#datagrid-entity').datagrid('loadData',{rows:{},total:0});
		}else if(type == 'fromDataSchema'){
			if($("#package_id").val() == '') return;
			$.messager.confirm('从数据库表生成实体属性？','<span>数据库表名:<input class="easyui-textbox" id="tableName" style="width:120px"/></span>',function(r){    
			    if (r){    
			       var tableName = $('#tableName').val();
			       if(tableName == '')return;
			       $('#entityName').textbox('setValue',tableName);
			       $('#datagrid-entity').datagrid('load',{
						tableName : tableName,
						id : ''
					});
			    }    
			});  
		}
		
		if($("#package_id").val() == "") return;
		appEngine.endEditing();//结束所有编辑行
		var data = $('#datagrid-entity').datagrid("getRows");
		
		var tableInfo = new Object(); //实体信息对象
		tableInfo.subclass = $("#subclass").textbox("getValue");
		tableInfo.entityName = $("#entityName").textbox("getValue");
		tableInfo.remark = $("#remark").textbox("getValue");
		tableInfo.entityName = $("#entityName").textbox("getValue");
		tableInfo.create = $("#create").combobox("getValue");
		tableInfo.createtime = $("#createtime").textbox("getValue");
		tableInfo.aem_entityId = $("#aem_entityId").val();
		tableInfo.package_id = $("#package_id").val();
		
		if(type == "save" ){
			$.messager.progress({title : "温馨提示",msg : "请稍后，正在处理......"});
			$.post(contextPath+"/manager/createEntityTable?random="+Math.random(), { data : JSON.stringify(data),tableInfo : JSON.stringify(tableInfo)},
				function(result){
				$.messager.progress("close");
				if(result.status){
					$.messager.show({title:'温馨提示',msg:result.msg,timeout:1000,showType:'show'});
					$('#datagrid-entity').datagrid("reload");
				}else{
					$.messager.alert("温馨提示", result.msg,"info");
				}
			}	  
			, "json");
		}else if(type == "createEntity"){
			tableInfo.packageN = $("#package_name").val();
			$.messager.progress({title : "温馨提示",msg : "请稍后，正在处理......"});
			$.post(contextPath+"/manager/createDataMoudle?random="+Math.random(), { data : JSON.stringify(data),tableInfo : JSON.stringify(tableInfo)},
					function(result){
					$.messager.progress("close");
					if(result.status){
						$.messager.show({title:'温馨提示',msg:result.msg,timeout:1000,showType:'show'});
						$('#datagrid-entity').datagrid("reload");
					}else{
						$.messager.alert("温馨提示", result.msg,"info");
					}
				}	  
				, "json");
		}
	}
};