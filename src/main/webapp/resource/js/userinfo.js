var userinfo = {
		cWidth : $(window).width(),
		cHeight : $(window).height(),
		init : function(){
			$.ajaxSetup({
				cache : false
			});
			if(!$.support.leadingWhitespace){
				userinfo.cWidth = document.body.clientWidth;
				userinfo.cHeight = document.body.clientHeight;
			}
			//初始化完成后执行_datagrid
			userinfo._datagrid();
		},
		_datagrid : function(){
			var toolbar=[{iconCls: 'icon-add',handler: function(){userinfo.onAddModifyUse(1);},text : '新增'},'-',
			             {iconCls: 'icon-edit',handler: function(){userinfo.onAddModifyUse(2);},text : '修改'},'-',
			             {iconCls: 'icon-cancel',handler: function(){userinfo.onDeleteUse();},text : '删除'},'-',
			             {iconCls: 'icon-downloadExcel',handler: function(){userinfo.onImportExcelUse();},text : '导入'},'-',
			             {iconCls: 'icon-downloadExcel',handler: function(){userinfo.onExportExcelUse();},text : '导出'},'-',
			             {iconCls: 'icon-downloadExcel',handler: function(){userinfo.onDownloadExcel();},text : '下载模板'}];
			$("#user-datagrid").datagrid({
				columns :[[{field : "_id",checkbox : true,width : userinfo.cWidth*0.01},
				        {field : 'username',title : '用户名',width : userinfo.cWidth*0.13,halign : 'center'},
						{field : 'createtime',title : '创建时间',width : userinfo.cWidth*0.15,halign : 'center'},
						{field : 'name',title : '员工姓名',width : userinfo.cWidth*0.08,halign : 'center'},
						{field : 'dept',title : '所属部门',width : userinfo.cWidth*0.12,halign : 'center',formatter:function(value){
							return userinfo.managerOrganization(value);
						}},
						{field : 'organization',title : '上级部门',width : userinfo.cWidth*0.12,halign : 'center',formatter:function(value){
							return userinfo.managerOrganization(value);
						}},
						{field : 'job',title : '职位',width : userinfo.cWidth*0.12,halign : 'center'},
						{field : 'auth',title : '菜单权限',width : userinfo.cWidth*0.12,align : 'center',halign : 'center',formatter:function(value,row,index){
							//var _data = {"_id":row._id,"authmenu":row.authmenu,"secondMenu":row.secondMenu};
							return "<a id=\"authLinkButton\" href=\"javascript:userinfo.authManager('"+row._id+"');\" style=\"text-decoration: none;\">查看</a>";
						}},
							/*{field : 'authmenu',title : '拥有主菜单权限',width : userinfo.cWidth*0.13,align : 'left',halign : 'center'}*/] ],
				toolbar : toolbar,nowrap : false,rownumbers : true,striped : true,border : false,fit : true,fitColumns : false,
				singleSelect : false,checkOnSelect : true,selectOnCheck : true,pagination : true,pageNumber : 1,pageList : [10,20,30],
				pageSize : 10,pagePosition:"bottom",loadMsg : "正在加载，请稍后......",url:userinfo.url._datagridUrl,
				onLoadError:function(){$.messager.alert("温馨提示", "数据加载失败,请稍后重试!","warning");}
			});
		},
		clear: function(){
			//$("#userform")[0].reset();
			$("#userform").form("clear");
		},
		url:{
			_datagridUrl:contextPath+"/manager/getUserManagerDatas",
			_authManagerUrl:contextPath+"/manager/toMenuAuth",
			_authManagerSaveUrl:contextPath+"/manager/saveAuth",
			_onAddModifyUseUrl:contextPath+"/manager/toAddModifyUse",
			_onExportExcelUseUrl:contextPath+"/export/exportExcelUse",
			_onImportExcelUseUrl:contextPath+"/import/downloadExcel",
			_importExcelUrl:contextPath+"/import/importExcel"
		},managerOrganization:function(options){
			if(options=="1")return "总经理办公室";
			else if(options=="0")return "管理员";
			else if(options == "1_110")return "人事部门";
			else if(options == "1_111")return "技术部门";
			else if(options == "1_112")return "行政部门";
			else if(options == "1_113")return "业务部门";
			else if(options == "1_114")return "财务部门";
			else if(options == "1_115")return "采购部门";
			else if(options == "1_116")return "后勤部门";
		},authManager:function(value){
			userinfo.openDialog({
				width:450,
				height:500,
				modal:true,
				maximizable:true,
				title:"菜单权限",
				closed:true,
				id:"authManmger",
				url:userinfo.url._authManagerUrl+"?_id="+value,
				save:function(){
					var treeObj = $.fn.zTree.getZTreeObj("treeM");
					var nodes = treeObj.getNodes();
					var authmenu = Array();//组装数据
					var secondMenu = Array();
					if(nodes.length > 0){
						for ( var d in nodes) {
							var data = {};
							data.id = nodes[d].id;
							data.imgpath = nodes[d].imgpath;
							data.name = nodes[d].name;
							data.pId = "0";
							authmenu.push(data);
							if(nodes[d].children != undefined){
								var _node = nodes[d].children;
								for ( var _d in _node) {
									var _data = {};
									_data.id = _node[_d].id;
									_data.imgpath = _node[_d].imgpath;
									_data.name = _node[_d].name;
									_data.pId = _node[_d].pId;
									secondMenu.push(_data);
									if(_node[_d].children != undefined){
										var child = Array();
										var __node = _node[_d].children;
										for ( var __d in __node) {
											var __data = {};
											__data.id = __node[__d].id;
											__data.imgpath = __node[__d].imgpath;
											__data.name = __node[__d].name;
											__data.pId = __node[__d].pId;
											child.push(__data);
										}
										_data.child = child;
									}
								}
							}
						}
					}
					$.messager.progress({title : "温馨提示",msg : "请稍后，正在处理......"});
					$.post(userinfo.url._authManagerSaveUrl, { "authmenu": JSON.stringify(authmenu),"secondMenu":JSON.stringify(secondMenu),"_id": $("#treeM").attr("userid")},
							  function(result){
						$.messager.progress("close");
								if(result.status){
									$.messager.show({title:'温馨提示',msg:result.msg,timeout:1000,showType:'show'});
									$("#authManmger").dialog("close");
								}else{
									$.messager.alert("温馨提示", result.msg,"info");
								}
						}
					,"json");
				}
			});
		},onAddModifyUse:function(type){
			var _id = "";
			if(type==2){var rows = $("#user-datagrid").datagrid("getSelected");if(rows==null)return; _id= rows._id;};
			userinfo.openDialog({ //调用公共openDialog
				width:550,
				height:500,
				modal:true,
				maximizable:true,
				title:type==1?"新增用户":"修改用户",
				closed:true,
				id:"addModifyUse",
				url:userinfo.url._onAddModifyUseUrl+"?_id="+_id,
				save:function(){ //保存数据
					alert("保存数据");
				}
			});
		},openDialog:function(options){
			var dialogId = userinfo.dialogId,_b = false;
			if(dialogId.length != 0)
			{
				for ( var _id in dialogId) {
					$("#"+dialogId[_id]).remove();
				}
				_b = true;
			}
			$(document.body).append("<div id="+options.id+" class='easyui-dialog'></div>");
			$("#"+options.id).dialog({
				width : options.width,
				height : options.height,
				modal : options.modal==null?false:options.modal,
				maximizable : options.maximizable==null?false:options.maximizable,
				title : options.title,
				closed : options.closed==null?false:options.closed,
				href : options.url,
				buttons : [ {text : "确定",iconCls : "icon-save",handler : function() {
					options.save();
					}
				}, {text : "返回",iconCls : "icon-undo",handler : function() {
						$("#"+options.id).dialog("close");
					}
				} ]
			});
			if(!_b)
			{
				userinfo.dialogId.push(options.id);
			}else{
				if($.inArray(options.id,dialogId)==-1)
				{
					userinfo.dialogId.push(options.id);
				}
			}	
			$("#"+options.id).dialog("open");
		},dialogId:[],
		search:function(){
			$('#user-datagrid').datagrid('reload',{
				"username" : $("input[name=username]").val(),
				"dept" : $("#dept").combobox("getValue"), 
				"job" : $("input[name=job]").val(),
				"name" : $("input[name=name]").val()
			});
		},uploadFileForm:function(options){
			var html = "<form id=\"importFileForm\" method=\"post\" enctype=\"multipart/form-data\">";
			html += "<table style=\"margin:5px;height:70px;\">";
			html += "<tr>";
			html += "<td>请选择文件:</td>";
			html += "<td><input class=\"easyui-filebox\" data-options=\"prompt:'点击选择文件...',buttonText:'点击上传',onChange:function(){"+options.fileChangeMethod+";}\" id=\"fileImport\" name=\"fileImport\" style=\"width:260px;\"></td>";
			html += "<td></td></tr>";
			html += "<tr>";
			html += "<td colspan=\"4\"><label id=\"fileName\" isCanUpolad='false' /></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td colspan=\"4\">";
			html += "<label id=\"uploadInfo\" />";
			html += "</td>";
			html += "</tr>";
			html += "</table><div style=\"text-align:center;clear:both;margin:5px;\">";
			html += "<a id=\"uploadFile\" style=\"margin-left:5px;\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-ok'\" onclick=\"javascript:"+options.uploadMethodName+";\" >上传</a>";
			html += "<a class=\"easyui-linkbutton\" style=\"margin-left:5px;\" data-options=\"iconCls:'icon-cancel'\" onclick=\"javascript:$('#"+options.id+"').window('close');\" >关闭</a>";
			html += "</div>"
			html += "</form>";
			return html;
		},importFileClick:function(){ //实现导入功能
			if(document.getElementById('fileName').getAttribute("isCanUpolad") == "false")
			{
				document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:错误，请上传文件!</span>";
				return;
			}
			var formData = new FormData($("#importFileForm")[0]);
			$.ajax({
				url: userinfo.url._importExcelUrl,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                	if(result.success){
                		$.messager.show({title:'温馨提示',msg:result.msg,timeout:1000,showType:'show'});
                		$('#importExcelWin').window('close');
                		$('#user-datagrid').datagrid("reload");
                	}else{
                		document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>错误提示:" + result.msg + "</span>";
                	}
                },
                error: function (result) {
                    document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>错误提示:" + result.msg + "</span>";
                }
			});
		},fileUploadChange:function(){
			var file = document.getElementsByName("fileImport")[0].files[0];
			if(file == null ) {
				document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:错误，请上传文件!</span>";
				return;
			}
			var fileName = file.name;
			var file_typename = fileName.substring(fileName.lastIndexOf('.'),fileName.length);
			if(file_typename == ".xls" || file_typename == ".xlsx"){
				var fileSize = 0;
				//计算文件的大小
				if(file.size > 1024*1024){
					fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;
					if(fileSize > 10 ){ //大于10M，禁止上传
						document.getElementById('fileName').setAttribute("isCanUpolad","false"); 
						document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:错误，文件超过10M，禁止上传!</span>";
						return;
					}
					fileSize = fileSize.toString() + 'MB';
				}else{
					fileSize = Math.round(file.size * 100 / (1024)) / 100 + 'KB';
				}
				document.getElementById('fileName').setAttribute("isCanUpolad","true"); 
				document.getElementById('fileName').innerHTML = "<span style='color:blue;'>文件名: " + file.name + ',大小: ' + fileSize + "</span>";
			}else{
				document.getElementById('fileName').setAttribute("isCanUpolad","false");
	            document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:上传文件应该是.xlsx或.xls后缀,而不应该是" + file_typename + ",请重新选择文件</span>";
			}
		},onImportExcelUse:function(){ //导入excel
			$("#importExcelWin").remove();
			$(document.body).append("<div id='importExcelWin' class='easyui-window'></div>");
			$('#importExcelWin').window({    
			    width:400,    
			    height:160,    
			    modal:true,
			    title:'文件上传',
			    closed:true,
			    content:userinfo.uploadFileForm({id:'importExcelWin',uploadMethodName:'userinfo.importFileClick()',fileChangeMethod:'userinfo.fileUploadChange()'}),
			    minimizable:false,
			    maximizable:false
			});
			 $('#importExcelWin').window('open');
		},onDownloadExcel:function(){ //下载模板，用户导入excel
			var deptData = new Array();
			var organizationData = new Array();
			for (var int = 0,deptOptins = document.getElementById("dept").options; int < deptOptins.length; int++) {
				if(deptOptins[int].innerText == "全部")continue;
				deptData.push(deptOptins[int].innerText);
				organizationData.push(deptOptins[int].innerText);
			}
			var submitForm = document.createElement( "FORM" );
			submitForm.action = userinfo.url._onImportExcelUseUrl;
			submitForm.method = "POST";
			
			userinfo.createNewFormElement( submitForm, "fieldName", userinfo.getColumnsFidleTitle('user-datagrid')[0] );
			userinfo.createNewFormElement( submitForm, "depts", deptData );
			userinfo.createNewFormElement( submitForm, "organizations", organizationData );
			
			document.body.appendChild( submitForm );
			submitForm.submit();
			submitForm.parentNode.removeChild( submitForm );
		},onExportExcelUse:function(){ //导出excel，创建form表单，用post方式导出excel
			var optionsField = userinfo.getColumnsFidleTitle('user-datagrid');
			
			var submitForm = document.createElement( "FORM" );
			submitForm.action = userinfo.url._onExportExcelUseUrl;
			submitForm.method = "POST";
			
			//通过反射调用use查询的服务类
//			userinfo.createNewFormElement( submitForm, "className", "com.xiaoyang.service.impl.EasyuitreeServiceImpl" );
//			userinfo.createNewFormElement( submitForm, "methodName", "queryManagerAll" );
			userinfo.createNewFormElement( submitForm, "username", $("input[name=username]").val() );
			userinfo.createNewFormElement( submitForm, "dept", $("#dept").combobox("getValue") );
			userinfo.createNewFormElement( submitForm, "job", $("input[name=job]").val() );
			userinfo.createNewFormElement( submitForm, "name", $("input[name=name]").val() );
			userinfo.createNewFormElement( submitForm, "fieldName", optionsField[0] );
			userinfo.createNewFormElement( submitForm, "field", optionsField[1] );
			
//			submitForm.appendChild($("input[name=username]")[0]);
//			submitForm.appendChild($("#dept")[0]);
//			submitForm.appendChild($("input[name=job]")[0]);
//			submitForm.appendChild($("input[name=name]")[0]);
			
			document.body.appendChild( submitForm );
			submitForm.submit();
			submitForm.parentNode.removeChild( submitForm );
		},createNewFormElement:function( inputForm, elementName, elementValue){//创建input，装数据用于form提交
			var newElement = document.createElement( "INPUT" );
			newElement.name = elementName;
			//newElement.id = elementName;
			newElement.type = "hidden";
			inputForm.appendChild( newElement );
			newElement.value = elementValue;
			return newElement;
		},getColumnsFidleTitle:function(id){
			var options = $("#"+id).datagrid("options");
			//修改属性
			var fieldName = new Array();
			var field = new Array();
			for (var int = 0; int < options.columns[0].length; int++) {
				fieldName.push(options.columns[0][int].title);
				field.push(options.columns[0][int].field);
			}
			return new Array(fieldName,field);
		}
};
//加载JS完执行userinfo.init
userinfo.init();