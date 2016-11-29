<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="login_page">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<link href="//cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/layer/2.4/skin/layer.min.css" rel="stylesheet">
<link rel="stylesheet" href="resource/css/style.css"/>
<title>后台数据管理</title>
</head>
<body>
	<div class="login_box">
		<form action="check" method="post" onreset="this.reset()" onsubmit="return validate_form()" id="login_form">
			<div class="top_b">欢迎登陆后台数据管理</div>
			<div class="cnt_b">
				<div class="formRow">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-user"></i> </span>
						<input type="text" id="username" placeholder="用户名" />
					</div>
				</div>
				<div class="formRow">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-lock"></i> </span>
						<input type="password" id="password" placeholder="密码"/>
					</div>
				</div>

				<div class="formRow">
					<img class="controls" src="" id="checkCodeImg" style="cursor: pointer;border: 1px #ccc solid;"
						onclick="this.src=this.src+'?'+Math.random();"
						title="看不清？点击更换" />
				</div>

				<div class="formRow">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-check"></i> </span>
						<input type="text" id="validation" name="validation" maxlength="4" placeholder="验证码"/>
					</div>
				</div>
				<div class="formRow clearfix">
					<label class="checkbox">
						<input type="checkbox" onclick="choosebox()" checked="checked" id="rememberMe" name="rememberMe" value="1" /> 下次自动登陆
					</label>
				</div>
			</div>
			<div class="btm_b clearfix">
				<div style="float: right">
					<button class="btn btn-inverse" type="submit">登陆</button>
					<button class="btn btn-inverse" type="reset">重置</button>
				</div>
				<span class="link_reg">后台数据管理</span>
			</div>
			<input type="hidden" name="_value" id="_value"/>
		</form>
	</div>
<script src="//cdn.bootcss.com/jquery/1.8.0/jquery.min.js"type="text/javascript"></script>
<script src="//cdn.bootcss.com/layer/2.4/layer.min.js" type="text/javascript"></script>
<script src="resource/js/cdvc.js" type="text/javascript"></script>
<script>
		$(document).ready(function() {
			//* boxes animation
			form_wrapper = $('.login_box');
			function boxHeight() {
				form_wrapper.animate({marginTop : (-(form_wrapper.height() / 2) - 24)}, 400);
				};
			form_wrapper.css({marginTop : (-(form_wrapper.height() / 2) - 24)});
			$('.linkform a,.link_reg a').on('click',function(e) {
							var target = $(this).attr('href'), target_height = $(target).actual('height');
								$(form_wrapper).css({'height' : form_wrapper.height()});
									$(form_wrapper.find('form:visible')).fadeOut(400,function() {
										form_wrapper.stop().animate({height : target_height,
																	marginTop : (-(target_height / 2) - 24)
													},500,function() {
														$(target).fadeIn(400);
			$('.links_btm .linkform').toggle();
			$(form_wrapper).css({'height' : ''});
				});
						});
				e.preventDefault();
				});
			document.getElementById("username").focus();
		});		
		
		function validate_form(){
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var validation = document.getElementById("validation");
			if(username.value == ""){
				layer.msg('嗯哼,您的用户名呢？', {icon: 5,time: 1500}, function(){
					username.focus();
				}); 
				return false;
			}
			if(password.value == ""){
				layer.msg('嗯哼,您的密码呢？', {icon: 5,time: 1500}, function(){
					password.focus();
				}); 
				return false;
			}
			if(validation.value == ""){
				layer.msg('嗯哼,您的验证码呢？', {icon: 5,time: 1500}, function(){
					validation.focus();
				});
				return false;
			}
			document.getElementById("_value").value = Base64.encode(username.value+":"+password.value);
		}
		function choosebox(){
			if(document.getElementById("rememberMe").checked==true){
				document.getElementById("rememberMe").value="1";
			}else{
				document.getElementById("rememberMe").value="0";
			}
		}
	</script>	
</body>
</html>