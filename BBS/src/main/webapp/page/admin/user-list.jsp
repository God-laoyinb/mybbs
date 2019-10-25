<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" onclick="member_add('添加用户','    ${pageContext.request.contextPath}/UserManage/user-add','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span>  </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				        <th width="80">ID</th>
						<th width="100">用户名</th>
						
						<th width="160">密码</th>
						<th width="50">状态</th>
						<th width="50">选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr class="text-c">
					<td><input type="checkbox" value="1" name=""></td>
					<td>${user.uId}</td>
					<td class="userName">${user.userName}</td>
					
					<td>${user.password}</td>
					
					<td class="td-status"><span class="label label-success radius">${user.state}</span></td>
					<td class="td-manage"> <a title="编辑" href="javascript:;" onclick="member_edit('编辑','  ${pageContext.request.contextPath}/UserManage/user-update?userId=${user.uId}&password=${user.password}&userName=${user.userName}','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,${user.uId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript">
$(function(){
	 transStatus();
});
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,userId){
	//layer.confirm('确认要删除吗？',function(index){
		
		if(confirm('您确定要删除吗?')){

				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/UserManage/delete",
					dataType:"json",
					data:{"userId":userId},
					success: function(data){
						//$(obj).parents("tr").remove();
						layer.msg('已删除!',{icon:1,time:1000});
						location.reload();
					},
					error:function(data) {
						//console.log(data.msg);
						alert('error');
					}
				});
		}
		
		
		
			
	//});
}
function transStatus(){ //把状态的数字改为文字
	var ele = $(".td-status");
	$.each(ele,function(index,value){
		if($(this).children().text()=="1") {	
			var tmp = $(this).children();
			$(this).prepend('<span class="label label-success radius">已启用</span>');
			tmp.remove();
			$(this).next().prepend('<a onClick="user_stop(this,id)" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
				 
				
			} else {
		
				//$(this).children().text('已停用');
				var tmp = $(this).children();
				$(this).prepend('<span class="label label-default radius">已停用</span>');
				tmp.remove();
				$(this).next().prepend('<a onClick="user_start(this,id)" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
			}
	});
}
/*user-停用*/
function user_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		var userName = $(obj).parents("tr").find(".userName").text();
		$.ajax({
			url:"${pageContext.request.contextPath}/UserManage/stopUser",
			type:"post",
			
			dataType:"json",
			async:false,
			data:{"userName":userName},
			success:function(data){
				if(data.res==1){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已停用</span>');
					$(obj).remove();
					layer.msg('已停用!',{icon: 5,time:1000}); 
				} else {
					layer.msg('操作失败!',{icon: 5,time:1000}); 
				}
			},
			error:function(){
				alert('error');
			}
		});
		
	});
}

/*user-启用*/
function user_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		var userName = $(obj).parents("tr").find(".userName").text();
		$.ajax({
			url:"${pageContext.request.contextPath}/UserManage/startUser",
			type:"post",
			
			dataType:"json",
			data:{"userName":userName},
			success:function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!', {icon: 6,time:1000});
			},
			error:function(){
				alert('error');
			}
		});
		
		
	});
}
</script> 
</body>
</html>