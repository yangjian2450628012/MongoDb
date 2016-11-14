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
				columns :[[{field : 'username',title : '用户名',width : userinfo.cWidth*0.13,align : 'left',halign : 'center'},
				           {field : 'TBBMMC',title : '用户权限',width : userinfo.cWidth*0.13,align : 'left',halign : 'center'},
							{field : 'SSBK_TNAME',title : '所属功能',width : userinfo.cWidth*0.07,align : 'left',halign : 'center'},
							{field : 'SSNF',title : '所属机构',width : userinfo.cWidth*0.05,align : 'left',halign : 'center'}] ],
				toolbar : toolbar,nowrap : false,rownumbers : true,striped : true,border : false,fit : true,fitColumns : false,
				singleSelect : false,checkOnSelect : true,selectOnCheck : true,pagination : true,pageNumber : 1,pageList : [20,50,100],
				pageSize : 20,pagePosition:"bottom",loadMsg : "正在加载，请稍后......",url:"",queryParams : {},
				onLoadError:function(){
					$.messager.alert("温馨提示", "数据加载失败,请稍后重试!","warning");
				}
			});
		}
};

//加载JS完执行userinfo.init
userinfo.init();

