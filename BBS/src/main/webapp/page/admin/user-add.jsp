<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<title></title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="userName" name="userName" autocomplete="off">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="password" name="password" autocomplete="off">
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input id="add" class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			
			</div>
		</div>
	</form>
</article>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
</body>
	<script>
		$(document).ready(function(){
			add();
		});
		function add(){ //添加用户
			$("#add").click(function(){
				if(validate()){
					var userName = $("#userName").val();
					var password = $("#password").val();
					
					//var realName = $("#realName").val();
					$.ajax({
						url:"${pageContext.request.contextPath}/UserManage/add",
						type:"post",
						dataType:"json",
						data:{
							"userName":userName,
							"password":password,
							
							
						},
						async:false,
						success:function(data){
							if(data.res==1){
								window.parent.location.reload(); //刷新不了
								parent.layer.msg('添加成功',{icon:1,time:2000});
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭
								
								
							} else {
								layer.msg('用户名已存在',{icon:0,time:2000});
							}
						},
						error:function(){
							alert('error');
						}
					});
				}
			});
		}
		function validate(){ //验证输入的准确性
			var res = true;
			var userName = $("#userName").val();
			var password = $("#password").val();
			
			if(password==''){
				layer.msg('密码不为空',{icon:0,time:2000});
				res = false;
			}
			if(userName==''){
				layer.msg('用户名不为空',{icon:0,time:2000});
				res = false;
			}
			return res;
		}
	</script>
</html>