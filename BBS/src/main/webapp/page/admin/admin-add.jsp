<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Shortcut Icon" href="/favicon.ico" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<title>添加管理员 - 管理员管理 - 后台管理系统 v3.1</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员名：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="" id="adminName" name="adminName" required="required" autocomplete="off" autofocus="autofocus"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" autocomplete="off" value="" placeholder="密码" id="password" name="password" required="required" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" autocomplete="off"  placeholder="确认新密码" id="password2" name="password2" required="required"/>
		</div>
	</div>
	

	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">角色：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="adminRole" size="1">
				
				<option value="1">普通管理员</option>
			</select>
			</span> </div>
	</div>
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input id="submit" class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	 addAdmin();
	
});

function addAdmin(){ //添加管理员
	//只能由超级管理员添加
	$("#submit").click(function(){
		if($("#password").val()!=$("#password2").val()){
			layer.msg('两次密码不同!',{icon: 5,time:1000}); 
		} else {
			var adminName = $("#adminName").val();;
			var password = $("#password2").val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/AdminManage/addAdmin",
				dataType:"json",
				data:{"adminName":adminName,"password":password},
				async:false,
				contentType:"application/json",
				success:function(data){ //添加成功
					if(data.res==1) {
						layer.msg('添加成功!',{icon: 1,time:2000});
						window.parent.location.reload();
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); 
						
						
					} else if(data.res==2) {
						layer.msg('您不是超级管理员，没有添加管理员权限!',{icon: 5,time:1000});
					} else {
						layer.msg('该管理员已存在!',{icon: 5,time:1000});
					}
				},
				error:function(){
					alert('error');
				}
			});
		}
	});
	
}
</script> 
</body>
</html>