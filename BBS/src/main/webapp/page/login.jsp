<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link href=" ${pageContext.request.contextPath}/static/lib/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/static/lib//h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href=" ${pageContext.request.contextPath}/static/lib/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<link href="${pageContext.request.contextPath}/static/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<title>登录</title>
</head>
<body>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="userName" name="" type="text" placeholder="账户" autocomplete="off" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="" type="password" autocomplete="off" placeholder="密码" class="input-text size-L">
        </div>
      </div>
    
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          	<input type="radio" name="statu" id="student" value="1" checked="checked">用户
          	<input type="radio" name="statu" id="admin" value="2">管理员
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input id="login" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"/>
          <input id="regist" type="button" class="btn btn-success radius size-L" value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;"/>
          <input id="cancel" type="button" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;"/>
        </div>
      </div>
    </form>
  </div>
</div>
 
</body>
<script type="text/javascript">
	$(document).ready(function(){
		login();
		regist();
		cancel();
	});
	function login(){ //登录
		$("#login").click(function(){
			if(!validate()){
				layer.msg('请输入正确的账户和密码',{icon:0,time:2000});
				return false;
			}
			var userName = $("#userName").val();
			var password = $("#password").val();
			var status = $('input:radio:checked').val();
			var url;
			if(1==status){ //普通用户
				$.ajax({
					url:"${pageContext.request.contextPath}/Login/validate",
					type:"post",
					data:{"userName":userName,"password":password,"status":status},
					dataType:"json",
					async:false,
					success:function(data){
						if(data.res==1){
							layer.msg('登录成功',{icon:1,time:2000});
                            location.replace("${pageContext.request.contextPath}/${prePage}");//跳转到index页面
						}
						if(data.res==0){
							layer.msg('用户不存在',{icon:0,time:2000});					
						}
						if(data.res==2){
							layer.msg('密码错误',{icon:0,time:2000});
						}
					},
					error:function(){
						alert('usererror');
					}
				});
			} else { //管理员
				$.ajax({
					url:"${pageContext.request.contextPath}/Login/adminValidate",
					type:"post",
					data:{"userName":userName,"password":password,"status":status},
					dataType:"json",
					async:false,
					success:function(data){
						if(data.res==1){
							layer.msg('登录成功',{icon:1,time:2000});
                            location.replace("${pageContext.request.contextPath}/AdminIndex/adminIndex");//跳转到index页面
						}
						if(data.res==0){
							layer.msg('管理员不存在',{icon:0,time:2000});					
						}
						if(data.res==2){
							layer.msg('密码错误',{icon:0,time:2000});
						}
					},
					error:function(){
						alert('error');
					}
				});
			}
				
				
				
			
		});
	}
			
	function regist(){
		$("#regist").click(function(){
			//alert("bbbbb");
			layer.msg("前往注册页面");
			location.replace("${pageContext.request.contextPath}/Regist/regist");//跳转到regist页面
		});
				
	}
	function validate(){ //输入正确性验证
		var res = true;
		var adminName = $("#adminName").val();
		var password = $("#password").val();
		if(adminName==''){
			res = false;
		}
		if(password==''){
			res = false;
		}
		return res;
	}
	function cancel(){
		$("#cancel").click(function(){
			window.location.href="${pageContext.request.contextPath}/${prePage}";	
		});
	}
</script>
</html>