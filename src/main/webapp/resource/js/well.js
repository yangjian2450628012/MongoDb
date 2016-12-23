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
			
			/*******************************************考勤操作*********************************************************/
			
			/* 选择工作日程触发事件*/
			$('#job-calendar').calendar({
				onSelect: function(date){
					alert(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
				},formatter:function(date){ //只计算前三个月的
					var b = true;
					var msg = "";
					
					if(date.getDay()==6 || date.getDay()==0) return date.getDate(); //星期六、星期天除外
					
					var options = $('#job-calendar').calendar("options"); 
					var year = options.year; //获取日期里当前年份
					var mothon = options.month; //获取日期里当前月份
					
					var firstDay = new Date(year,mothon-1,1); //获取日期里当月第一天
					var lastDay = new Date(year,mothon,0); //获取日期里最后一天
					
					// 1.当月的1-31
					if(date.getTime() < firstDay.getTime() || date.getTime() > lastDay.getTime()){
						return date.getDate();
					}
					// 2.日期到了当天的17:30才做判断
					var now = new Date();
					now.setHours(00, 00, 00, 00);
					if(now.getTime() == date.getTime()){ //是同一天
						var _time = date;_time.setHours(new Date().getHours(), new Date().getMinutes(), new Date().getSeconds(), 00);
						now.setHours(17,30,00,00);
						if(now.getTime() > _time.getTime()){
							return date.getDate();
						}
					}else{
						if(now.getTime() < date.getTime()){
							return date.getDate();
						}
					}	
					
					for ( var signinDate in well.signIn) {
						var signTime = new Date(well.signIn[signinDate].split(" ")[0]+" 00:00:00");
						if(date.getTime() == signTime.getTime()){ //是同一天判断
							var standardTime = new Date(well.signIn[signinDate].split(" ")[0]+" 09:00:00"); //定义签到标准时间
							var _date = new Date(well.signIn[signinDate].replace(/-/g,"/")); //用户签到时间
							if(_date.getTime() > standardTime.getTime()){ //假如用户签到时间大与定义的标准时间，表示迟到
								msg += "迟到";
								b = false;
							}
							for ( var signoutDate in well.signOut) { //遍历判断签退
								var signTime2 = new Date(well.signOut[signoutDate].split(" ")[0]+" 00:00:00");
								if(date.getTime() == signTime2.getTime()){ //是同一天判断 
									//console.log(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
									var standardTime2 = new Date(well.signOut[signoutDate].split(" ")[0]+" 17:00:00"); //定义签到标准时间
									var _date2 = new Date(well.signOut[signoutDate].replace(/-/g,"/")); //用户签到时间
									if(standardTime2.getTime() > _date2.getTime()){
										if(msg != "")msg += "|早退";
										else msg += "早退";
										b = false;
									}
								}
								//signOut.splice(signoutDate,1); 
								continue;
							}
							//signIn.splice(signinDate,1); //判断完后删除该数组,减少循环次数
							continue;
						}
					}
					if(!b){
						return "<span id='"+date.getTime()+"'>"+date.getDate()+msg+"<script type=\"text/javascript\">$('#"+date.getTime()+"').parent()[0].style.backgroundColor='#bd362f';</script></span>";
					}else{
						return "<span id='"+date.getTime()+"'>"+date.getDate()+"正常<script type=\"text/javascript\">$('#"+date.getTime()+"').parent()[0].style.backgroundColor='rgb(144, 237, 125)';</script></span>";
					}
				} 
			});
			
			//签到上绑定点击事件
			$("#signin-a").bind("click", function(){
				  var now = new Date();
				  var standardTime = new Date();standardTime.setHours(9, 0, 0, 0);
				  if(now.getTime() > standardTime){
					  $.messager.confirm('提示信息','当前已经超过了签到时间,是否继续？',function(r){    
						    if (r){    
						    	well.sendSignData({type:"signin",date:now});
						    }    
						});  
				  }else{
					  well.sendSignData({type:"signin",date:now});
				  }
			});
			//签退上绑定点击事件
			$("#signout-a").bind("click", function(){
				var now = new Date();
				  var standardTime = new Date();standardTime.setHours(17, 0, 0, 0);
				  if(now.getTime() < standardTime){
					  $.messager.confirm('提示信息','当前时间还未到签退时间,是否继续？',function(r){    
						    if (r){    
						    	well.sendSignData({type:"signOut",date:now});
						    }    
						});  
				  }else{
					  well.sendSignData({type:"signOut",date:now});
				  }
			});
			
			/*****************************************************树形菜单操作 ****************************************************************/
			/*拼接树形菜单*/
			var zNodes =[{name:"系统模块",open:true,
			     		children: [{name:"用户管理", "_name":"用户管理","url":contextPath+"/manager/usermanager",target:"my-ifream用户管理"},
			     		           {name:"部门管理", "_name":"部门管理","url":contextPath+"/manager/deptmanager",target:"my-ifream部门管理"}
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
			/*设置桌面*/
			$('#mydeksot').portal({
				border:false,
				fit:false
			});
			//初始化图形化
			 $('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false,
			           // marginLeft: 300,
			            events:{
			            	click: function(e) {
					            	alert(56789);
					            }
			            }
			        },
			        exporting:{enabled:false},
			        credits:{enabled:false},
			        /*legend:{align:"right",itemDistance:15},*/
			        title: {
			            text:'全部流程未完成情况'
			            //align:"right"
			        },
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                },
			                showInLegend: true
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '未完成',
			            data: [
			                ['紧急流程',   45.0],
			                ['重要流程',       26.8],
			                {
			                    name: '普通流程',
			                    y: 12.8,
			                    sliced: true,
			                    selected: true
			                },
			                ['其他待办',    8.5]
			            ]
			        }]
			    });
		},sendSignData:function(options){
			$.messager.progress({title : "温馨提示",msg : "请稍后，正在处理......"});
			$.post(contextPath+"/sign/signinAndOut", { date: options.date ,type: options.type},
					   function(data){
				$.messager.progress("close");
					if(data.success){
						$.messager.show({title:'温馨提示',msg:data.msg,timeout:1000,showType:'show'});
					}     
			});
		},_change : function(){
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
		},showinform:function(index,row){
			var html = "<div style=\"font-size: 20px; width: 100%;\"><div>";
				html+="<span><center>"+row.informName+"</center></span></div>";
				html+="<div><div style=\"overflow: auto; width: 100%; height: 100%;\">";
				html+="<div><p style=\"text-indent:2em\">"+row.content+"</p></div>";	
				html+="<div style=\"text-align: right;margin-right:5px;\">";
				html+="<div>发布人:"+row.creater+"</div><div>发布机构:"+row.dept+"</div>";
				html+="<div>发布时间:"+row.createtime+"</div><div></div></div></div></div></div>";
			$("#showinform").remove();
			$(document.body).append("<div id='showinform' class='easyui-dialog'></div>");
			$('#showinform').dialog({    
			    width:540,    
			    height:300,    
			    modal:true,
			    title:'我的公告',
			    closed:true,
			    //iconCls:"icon-speaker",
			    content:html,
			    minimizable:false,
			    maximizable:true
			});
			 $('#showinform').window('open');
		},editCheckbox:function(){
			return "<img src='"+contextPath+"/resource/images/sms-readed.gif' width='16' height='16' style='margin-top:5px;' />";
		}
};

jQuery(function(){
	//初始化
	well.init();
});
