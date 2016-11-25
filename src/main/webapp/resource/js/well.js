/*
 * well v1.0
 */
var well = {
		init : function(){
			/*下拉框更换皮肤*/
			$("#theme-id").combobox({    
				data: [{name:'默认',value:'default'},
				       {name:'灰色',value:'gray'},
				       {name:"Metro",value:"metro"},
				       {name:"黑色",value:"black"},
				       {name:"Bootstrap",value:"bootstrap"},
				       {name:"布色",value:"material"}],    
			    valueField:'value',    
			    textField:'name',
			    width:120,
			    height:20,
			    editable:false,
			    panelHeight:130,
			    onChange:function(){
			    	well._change();
			    }
			});  
			/*去掉accordion-one 更改皮肤的光标*/
			$("#accordion-one").prev().css("cursor","default");
			$("#accordion-one").prev().find(".panel-title").css("margin-top","-2px");
			/* 初始化tab页*/
			$("#_tabs").tabs({
				fit : true,
				border : false
			});
			/* 选择工作日程触发事件*/
			$('#job-calendar').calendar({
				onSelect: function(date){
					alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				}
			});
			
			/*拼接树形菜单*/
			var zNodes =[{name:"系统模块",open:true,
			     		children: [{name:"用户管理", "_name":"用户管理","url":contextPath+"/manager/usermanager",target:"my-ifream用户管理"}
				]},{name:"菜单模块",open:true,
						children: [{name:"菜单管理","_name":"菜单管理","url":contextPath+"/manager/menumanager",target:"my-ifream菜单管理"}]
				}];

			/*给树形节点增加触发事件*/
			var setting = {
					treeId: "",
					treeObj: null,
				 	callback: {
						onClick: well.getTree
					}
			};

			$.fn.zTree.init($("#authManager"), setting, zNodes);
			
			/*Easyui树形结构*/
			$('#_tree').tree({
				onClick: function(node){
					if(node.url)
					{
						if($("#_tabs").tabs("exists",node.text))
						{
							$("#_tabs").tabs("select",node.text);
						}
						else
						{
							$('#_tabs').tabs('add',{    
							    title: node.text,  //要使纵向没有滚动条,可以将iFrame的height更改为99%
							    content: '<iframe scrolling="no" name="my-ifream" frameborder="0"  src="'+node.url+'" style="width:100%;height:99%;"></iframe>',    
							    closable:true,    
							    tools:[{    
							        iconCls:'icon-mini-refresh',    
							        handler:function(){    
							            //刷新页面
							        	var tab = $('#_tabs').tabs('getSelected');
							        	$('#_tabs').tabs('update', {
							        		tab: tab,
							        		options: {
							        			title: node.text,
							        			content: '<iframe scrolling="no" name="my-ifream" frameborder="0"  src="'+node.url+'" style="width:100%;height:99%;"></iframe>',    
							        		}
							        	});
							        	tab.panel('refresh');
							        }    
							    }]    
							}); 
						}
					}	
				}
			});

			/*主菜单选中效果*/
			$(".titleMenu-div a").click(function(){
				if($(this).attr("class") == "active")
				return;
				$(".titleMenu-div a").each(function(_i){
					if($(this).attr("class")=="active")
					{
						$(this).attr("class","");
						return; //取消选中class,并终止循环
					}	
				});
				$(this).attr("class","active");
				
				/*调用继承jQuery的函数*/
				$(".menu-div").menuCom({
					text : $.trim($(this).text()),
					callback : function(s){
						//修改面板的标题
						$("#accordion-two").panel("setTitle",s.text);
						document.getElementById("menusecond-div").innerHTML = s._h;
						$(".show-child a:first-child").on("click",function(event){
							var menu = $(".menu-div");
							//二级菜单的子菜单滑动效果实现
			                var currentlink=$(event.currentTarget);
			                if (currentlink.parent().find('ul.active').size()==1)
			                {
			                    currentlink.parent().find('ul.active').slideUp('medium',function(){
			                    	menu.find('ul li ul.active').parent().find("span").css("background","url('easyui/css/"+well.easyuiTheme+"/images/layout_arrows.png') no-repeat 0 -16px");
			                        currentlink.parent().find('ul.active').removeClass('active');
			                    });
			                }
			                else if (menu.find('ul li ul.active').size()==0)
			                {
			                	well.__show(currentlink);
			                }
			                else
			                {
			                    menu.find('ul li ul.active').slideUp('medium',function(){
			                    	menu.find('ul li ul.active').parent().find("span").css("background","url('easyui/css/"+well.easyuiTheme+"/images/layout_arrows.png') no-repeat 0 -16px");
			                        menu.find('ul li ul').removeClass('active');
			                        well.__show(currentlink);
			                    });
			                }
			            
						});
						//选中accordion-two
						$("#accord").accordion("select",s.text);
					}
				});
			});

			/*主菜单的二级菜单选中效果*/
			$(".menu-div").on("click","a",function(event){
				if($(this).parent().parent().parent().attr("class") == "show-child")
				{
					$(this).parent().parent().parent().find("a")[0].attr("class","active");
					return;
				}	
				if($(this).attr("class") == "active")
					return;
				$(".menu-div a").each(function(_i){
					if($(this).attr("class")=="active")
					{
						$(this).attr("class","");
						return;
					}	
				});
				$(this).attr("class","active");
			});
			/*二级菜单的子菜单选中效果*/
			$(".show-child a:first-child").on("click",function(event){
				var menu = $(".menu-div");
				//二级菜单的子菜单滑动效果实现
				var currentlink=$(event.currentTarget);
			    if (currentlink.parent().find('ul.active').size()==1)
			    {
			        currentlink.parent().find('ul.active').slideUp('medium',function(){
			        	menu.find('ul li ul.active').parent().find("span").css("background","url('easyui/css/"+well.easyuiTheme+"/images/layout_arrows.png') no-repeat 0 -16px");
			        	currentlink.parent().find('ul.active').removeClass('active');
			        });
			    }
			    else if (menu.find('ul li ul.active').size()==0)
			    {
			    	well.__show(currentlink);
			    }
			    else
			    {
			        menu.find('ul li ul.active').slideUp('medium',function(){
			        	menu.find('ul li ul.active').parent().find("span").css("background","url('easyui/css/"+well.easyuiTheme+"/images/layout_arrows.png') no-repeat 0 -16px");
			        	menu.find('ul li ul').removeClass('active');
			        	well.__show(currentlink);
			        });
			    }
			});
			
			/*一分钟调用一次更新时间*/
			setInterval("well.show_cur_times()",1000);
		},
		_change : function(){
			/*更改框架皮肤*/
			/*var theme = $("select[name=theme]").val();*/
			var theme = $("#theme-id").combobox("getValue");
			var href = $("#easyuiTheme").attr("href").replace($("#easyuiTheme").attr("href").split("/")[4], theme);
		    $("#easyuiTheme").attr("href", href);
		    document.cookie = "theme="+$("#easyuiTheme").attr("href").split("/")[4];
		    //更改二级菜单的折叠图标
		    $(".menu-div li.show-child").each(function(){
		    	var _h = $(this).find("span")[0];
		    	$(_h).css("background",$(_h).css("background").replace(well.easyuiTheme,theme));
			});
		    //更改二级菜单字体颜色
		    if(theme == "black")
		    {
		    	$(".menu-div ul li a div").each(function(){
		    		$(this).css("color","#fff");
		    	});
		    }else{
		    	$(".menu-div ul li a div").each(function(){
		    		$(this).css("color","");
		    	});
		    }
		    well.easyuiTheme = theme;
		},
		goChange : function(t,i){
			alert(t+i);
		},
		show_cur_times : function(){
			var _d = new Date();
			//判断是十二点,更新日期
			var _h = _d.getHours(),_m = _d.getMinutes();
			if(_h == 0 && _m == 0 && _d.getSeconds() == 0)
			{
				var week = "星期天";
				switch (_d.getDay()) {
				case 1:
					week = "星期一";
					break;
				case 2:
					week = "星期二";
					break;
				case 3:
					week = "星期三";
					break;	
				case 4:
					week = "星期四";
					break;	
				case 5:
					week = "星期五";
					break;	
				case 6:
					week = "星期六";
					break;	
				}
				$(".menuright-div span").last().text(_d.getFullYear()+"年"+_d.getMonth()+"月"+_d.getDate()+"日 "+week);
			}
			if(_m>=0 && _m<=9)
			{
				_m = "0"+_m;
			}	
			$(".menuright-div span:first").text(_h+":"+_m);
		},
		__show : function(currentlink){
			currentlink.parent().find('ul').addClass('active');
		    currentlink.find(".accordion-img").css("background","url('easyui/css/"+well.easyuiTheme+"/images/layout_arrows.png') no-repeat -16px 0");
		    currentlink.parent().find('ul').slideDown('medium');
		},
		easyuiTheme : "default",
		getTree:function(event,treeId,treeNode){
			event=null,treeId=null;
			var _b;if(_b=treeNode._name==null)return;
			if(!_b){
				if($("#_tabs").tabs("exists",treeNode._name))
				{
					$("#_tabs").tabs("select",treeNode._name);
				}
				else
				{
					$('#_tabs').tabs('add',{    
					    title: treeNode._name,  //要使纵向没有滚动条,可以将iFrame的height更改为99%
					    content: '<iframe scrolling="no" name="my-ifream'+treeNode._name+'" frameborder="0"  src="'+treeNode.url+'" style="width:100%;height:99%;"></iframe>',    
					    closable:true,    
					    tools:[{    
					        iconCls:'icon-mini-refresh',    
					        handler:function(){
					        	//刷新页面
					        	if($(this).parent().parent(".tabs-selected")[0]==undefined)return;
					        	var tab = $('#_tabs').tabs('getSelected');
					        	$('#_tabs').tabs('update', {
					        		tab: tab,
					        		options: {
					        			title: treeNode._name,
					        			content: '<iframe scrolling="no" name="my-ifream'+treeNode._name+'" frameborder="0"  src="'+treeNode.url+'" style="width:100%;height:99%;"></iframe>',    
					        		}
					        	});
					        	tab.panel('refresh');
					        }    
					    }]    
					}); 
				}
			}
		}
};

jQuery(function(){
	//初始化
	well.init();
});
