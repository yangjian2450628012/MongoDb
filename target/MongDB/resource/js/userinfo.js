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
			var toolbar=[{iconCls: 'icon-add',handler: function(){onMkhz();},text : '新增'},'-',
			             {iconCls: 'icon-edit',handler: function(){onXtyx();},text : '修改'},'-',
			             {iconCls: 'icon-cancel',handler: function(){onFzgshz();},text : '删除'}];
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
			_datagridUrl:contextPath+"/manager/getUserManagerDatas"
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
				url:contextPath+"/manager/toMenuAuth?_id="+value,
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
					$.post(contextPath+"/manager/saveAuth", { "authmenu": JSON.stringify(authmenu),"secondMenu":JSON.stringify(secondMenu),"_id": $("#treeM").attr("userid")},
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
		}
};
//加载JS完执行userinfo.init
userinfo.init();