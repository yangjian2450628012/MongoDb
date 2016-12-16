<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台数据管理首页</title>
<!-- 默认皮肤 -->
<script type="text/javascript">
	var _theme,reg=new RegExp("(^| )theme=([^;]*)(;|$)");
	if(_theme=document.cookie.match(reg))_theme=unescape(_theme[2]);
	_theme != undefined ?document.write("<link rel=\"stylesheet\" href=\"<%=path%>/easyui/css/"+_theme+"/easyui.css\" id=\"easyuiTheme\" type=\"text/css\" />"):document.write("<link rel=\"stylesheet\" href=\"<%=path%>/easyui/css/default/easyui.css\" id=\"easyuiTheme\" type=\"text/css\" />");_theme=null;reg=null;
</script>
<script type="text/javascript">var contextPath="<%=request.getContextPath()%>"</script>
<link href="<%=path%>/easyui/css/icon.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/resource/css/well.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/easyui/css/jqueryZtree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">window.onload = function(){var objLoading = document.getElementById("divprogressbar");if (objLoading != null){document.body.removeChild(objLoading);objLoading=null;}};</script>
</head>

<body class="easyui-layout">
<div id="divprogressbar" style="position: absolute;width: 100%; height: 100%; left: 0px; top: 0px; background-color: #ffffff; filter: alpha (opacity = 100 ); z-index: 50000">  
   <div style="text-align: center;padding-top: 200px">  
      <table align="center" border="1" width="37%" cellspacing="0" cellpadding="4" style="border-collapse: collapse;" bgcolor="#fff" height="87"><!-- #FFFFEC -->  
         <tr>  
            <td bgcolor="#95B8E7" style="font-size: 12px;" height="24" align="center">友情提示, 数据正在载入中...</td>  
         </tr>  
         <tr>  
            <td style="font-size: 12px; line-height: 200%" align="center">网页正在载入数据中.请耐心等待...  
             <marquee style="border: 1px solid #E0ECFF" direction="right" width="300" scrollamount="5" scrolldelay="10" bgcolor="#ECF2FF"> <!--  --> 
                <table cellspacing="1" cellpadding="0">  
                   <tr height=8>  
                      <td bgcolor=#3399FF width=8></td>  
                      <td></td>  
                      <td bgcolor=#3399FF width=8></td>  
                      <td></td>  
                      <td bgcolor=#3399FF width=8></td>  
                      <td></td>  
                      <td bgcolor=#3399FF width=8></td>  
                      <td></td>  
                   </tr>  
                </table>  
              </marquee>  
            </td>  
          </tr>  
       </table>  
    </div>  
</div>


<div id="logo" data-options="region:'north',split:true" style="height:70px;background-image:url('<%=path%>/resource/images/banner.jpg')">
		<div class="logo" style="background-image: url(<%=path%>/resource/images/logo4.png)"></div>
		<div class="titleMenu-div" id="authMenu_div">
			<!-- 主菜单区域 -->
 		</div>
 		<div style="" class="menuright-div">
			<!-- <iframe id="_weather" src="http://i.tianqi.com/index.php?c=code&id=99" width="160" height="36" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe> -->
			
			<table>
				<tbody>
					<tr>
						<td></td>
						<td>
							<span class="currtime"><%=new SimpleDateFormat("H:mm").format(new Date()) %></span>
						</td>
					</tr>
					<tr>
						<td>
							<a title="首页定义" href="javascript:void();"><img src="resource/images/personset.gif" /></a>
							<a title="刷新" href="javascript:void();"><img src="resource/images/refresh.gif" ></a>
							<a title="帮助中心" href="javascript:void();"><img src="resource/images/help.gif"></a>
							<a title="注销" href="logout"><img src="resource/images/logout.gif"></a>
						</td>
						<td>
							<span class="currdate"><%=new SimpleDateFormat("yyyy年MM月dd日 E").format(new Date()) %></span>
						</td>
					</tr>
				</tbody>
			</table>
 		</div>
	</div>   
    <div id="west-div"  data-options="region:'center'">
    	<div id="_tabs">
			<div title="我的桌面" iconCls="icon-house" style=" display:block;padding: 5px;">
				<!-- 欢迎来到后台管理系统！ -->
				<div class="easyui-layout" data-options="border:false,fit:true">
					<div data-options="region:'north'" style="height:32px;">
						<div style="float:right;margin:4px 5px 0 0">
							<input class="easyui-searchbox" prompt="Please input value" style="width:300px">
						</div>
						<div style="padding:2px 5px;">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">发启流程</a>
							<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
							<a href="#" class="easyui-splitbutton" data-options="iconCls:'icon-tip'">代办事项</a>
							<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
							<a href="#" class="easyui-menubutton" data-options="iconCls:'icon-tip'">待审批</a>
							<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save',toggle:true">Save</a>
							<span class="datagrid-btn-separator" style="vertical-align: middle; display:inline-block;float:none"></span>
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">Print</a>
						</div>
					</div> 
					<div region="center" border="false" >
						
						<div id="mydeksot" style="position:relative">
							<div style="width:30%;">
								<div title="个人信息" iconCls="icon-man" collapsible="true" closable="true" style="text-align:center;height:200px;">
									
							    </div>
							    <div title="待办任务" collapsible="true" closable="true" iconCls="icon-myTask_yb" style="height:200px;">
							   		
							    </div>
							</div>
							<div style="width:40%;">
								<div title="通知公告" closable="true" collapsible="true" iconCls="icon-speaker" style="height:200px;">
									
								</div>
								<div title="待办流程" collapsible="true" closable="true" iconCls="icon-handleTask_db" style="height:200px;">
							   		
							    </div>
							</div>
							<div style="width:30%;">
								<div title="工作日程" collapsible="true" closable="true" iconCls="icon-calendar" style="height:200px;">
									<div class="easyui-calendar" id="job-calendar" data-options="fit:true,border:false"></div>
								</div>
								<div title="待办会议" closable="true" collapsible="true" iconCls="icon-metting" style="height:200px;text-align:center;">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>  
    <div data-options="region:'west',title:'功能导航',split:true" style="width:200px;"><!-- iconCls="icon-menu" -->
		<div class="easyui-accordion" data-options="border:false,fit:true" id="accord">   
		    <div title="更换皮肤:<input id='theme-id' />" data-options="collapsible:false" id="accordion-one">
			</div>
			<div title="个人" id="accordion-two" data-options="selected:true"><!-- data-options="collapsible:false" -->
				<%-- <ul class="easyui-tree" id="_tree" data-options="url:'<%= path%>/tree'"></ul> --%>
				<div class="menu-div" id="menusecond-div">
					
				</div>
			</div>
			
			 <div title="消息菜单">   
			 	
			</div>   
			<%if(request.getAttribute("username")!=null && "admin".equals(request.getAttribute("username"))){ %>
			<div title="权限管理">   
				<ul id = "authManager" class="ztree"></ul>  
			</div>
			<%} %>
		</div>  
	</div> 
	
<script type="text/javascript">
<%if(request.getAttribute("authmenus")!=null){ %>
var _h = "<ul>";
for (var i=0,authmenu=<%=request.getAttribute("authmenus") %>;i < authmenu.length;i++) {
	if(i == 0){
		_h +="<li><a class='active'><div class='first-div'>";
		_h +="<img src='"+authmenu[i].imgpath+"' style='border: 0px; width: 35px; height: 35px; margin-top: 1px;' /><div style='margin: 0px;'>";
		_h +="<span>"+authmenu[i].name+"</span></div></div></a></li>";
	}else{
		_h +="<li><a><div class='first-div'>";
		_h +="<img src='"+authmenu[i].imgpath+"' style='border: 0px; width: 35px; height: 35px; margin-top: 1px;' /> <div style='margin: 0px;'>";
		_h +="<span>"+authmenu[i].name+"</span></div></div></a></li>";
	}
}
_h +="</ul>";
document.getElementById("authMenu_div").innerHTML = _h;
_h = null;
<%}%>

</script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jqueryZtree.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.portal.js"></script> 
<script type="text/javascript" src="<%=path %>/resource/js/well.js"></script>
<script type="text/javascript"> 
var _da = {"个人":Array(),"通知":Array(),"流程":Array(),"文档":Array(),"报告":Array(),"项目":Array(),"资产":Array(),"人事":Array(),"客户":Array(),"采购":Array()};
<%if(request.getAttribute("secondMenu")!=null){ %>
var _sH = "<ul>";
for (var i=0,secondMenu=<%=request.getAttribute("secondMenu") %>;i < secondMenu.length;i++) {
	if(secondMenu[i].pId == "1"){//组装html
		if(secondMenu[i].child != undefined && typeof secondMenu[i].child == "object"){
			_sH += "<li class=\"show-child\"><a><div>";
			_sH += "<img alt='' src="+secondMenu[i].imgpath+" />"+secondMenu[i].name+"<span class=\"accordion-img\"></span>";
			_sH += "</div></a><ul style=\"display: none;\">";
			var _c = secondMenu[i].child;
			for ( var ch in _c) {
				_sH += "<li class=\"child-no-display\"><a><div>";
				_sH += "<img alt='' src="+_c[ch].imgpath+" />"+_c[ch].name+"</div></a></li>";
			}
			_sH += "</ul>";
			_sH += "</li>";
		}else{
			_sH += "<li><a><div><img alt='' src="+secondMenu[i].imgpath+" />"+secondMenu[i].name+"</div></a></li>";
		}
		_da.个人.push(secondMenu[i]);
	}else{
		if(secondMenu[i].pId == "2"){
			_da.通知.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "3"){
			_da.流程.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "4"){
			_da.文档.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "5"){
			_da.报告.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "6"){
			_da.项目.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "7"){
			_da.资产.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "8"){
			_da.人事.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "9"){
			_da.客户.push(secondMenu[i]);
		}else if(secondMenu[i].pId == "10"){
			_da.采购.push(secondMenu[i]);
		}
	}
}
_sH += "</ul>";
document.getElementById("menusecond-div").innerHTML = _sH;
_sH = null;
<%}%>
</script>
<script type="text/javascript" src="<%=path %>/resource/js/menu.js"></script>
</body>
</html>