jQuery(function(){
	//去掉accordion-one 更改皮肤的光标
	$("#accordion-one").prev().css("cursor","default");
	$("#accordion-two").prev().css("cursor","default");
	$("#accordion-two").show();
//	var nn = document.getElementById("_weather");
//	console.log(document.getElementById("_weather").contentWindow.document.getElementByClassName("box"));
});
$("#_tabs").tabs({
		fit : true,
		border : false
});
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
/**
 * 更换框架皮肤
 */
var _change = function(){
	var href = $("#easyuiTheme").attr("href").replace($("#easyuiTheme").attr("href").split("/")[4], $("select[name=theme]").val());
    $("#easyuiTheme").attr("href", href);
    document.cookie="theme="+$("#easyuiTheme").attr("href").split("/")[4];
};

/**
 * 选择工作日程触发事件
 */
$('#job-calendar').calendar({
	onSelect: function(date){
		alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});
/**
 * 
 */
var goChange = function(t,i){
	alert(t+i);
};
/**
 * 主菜单选中效果
 */
$(".titleMenu-div a").click(function(){
	$(".titleMenu-div a").each(function(_i){
		if($(this).attr("class")=="active")
		{
			$(this).attr("class","");
			return;
		}	
	});
	$(this).attr("class","active");
});

/**
 * 主菜单的二级菜单选中效果
 */
$(".menu-div a").click(function(){
	$(".menu-div a").each(function(_i){
		if($(this).attr("class")=="active")
		{
			$(this).attr("class","");
			return;
		}	
	});
	$(this).attr("class","active");
});
/**
 * 更新日期时间
 */
var show_cur_times = function(){
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
		$(".right-div span").last().text(_d.getFullYear()+"年"+_d.getMonth()+"月"+_d.getDate()+"日 "+week);
	}
	if(_m>=0 && _m<=9)
	{
		_m = "0"+_m;
	}	
	$(".right-div span:first").text(_h+":"+_m);
};
/**
 * 一分钟调用一次更新时间
 */
setInterval("show_cur_times()",1000);