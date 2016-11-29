var menuinfo = {
		cWidth : $(window).width(),
		cHeight : $(window).height(),
		init : function(){
			$.ajaxSetup({
				cache : false
			});
			if(!$.support.leadingWhitespace){
				menuinfo.cWidth = document.body.clientWidth;
				menuinfo.cHeight = document.body.clientHeight;
			}
			//初始化完成后执行_datagrid
			menuinfo._datagrid();
		},
		_datagrid : function(){
			var toolbar=[{iconCls: 'icon-add',handler: function(){onMkhz();},text : '新增'},'-',
			             {iconCls: 'icon-edit',handler: function(){onXtyx();},text : '修改'},'-',
			             {iconCls: 'icon-cancel',handler: function(){onFzgshz();},text : '删除'}];
			$("#menu-datagrid").datagrid({
				columns :[[{field : "_id",checkbox : true,width : menuinfo.cWidth*0.01},
				        {field : 'username',title : '主菜单',width : menuinfo.cWidth*0.13,halign : 'center'},
						{field : 'createtime',title : '二级菜单',width : menuinfo.cWidth*0.15,halign : 'center'}]],
				toolbar : toolbar,nowrap : false,rownumbers : true,striped : true,border : false,fit : true,fitColumns : false,
				singleSelect : false,checkOnSelect : true,selectOnCheck : true,pagination : true,pageNumber : 1,pageList : [10,20,30],
				pageSize : 10,pagePosition:"bottom",loadMsg : "正在加载，请稍后......",url:menuinfo.url._datagridUrl,
				onLoadError:function(){$.messager.alert("温馨提示", "数据加载失败,请稍后重试!","warning");}
			});
		},
		clear: function(){
			//$("#userform")[0].reset();
			$("#menuform").form("clear");
		},
		url:{
			_datagridUrl:contextPath+"/manager/getMenuManagerDatas"
		},
		search:function(){
			$('#user-datagrid').datagrid('reload',{
				"username" : $("input[name=username]").val(),
				"dept" : $("#dept").combobox("getValue"), 
				"job" : $("input[name=job]").val(),
				"name" : $("input[name=name]").val()
			});
		}
};
//加载JS完执行menuinfo.init
menuinfo.init();