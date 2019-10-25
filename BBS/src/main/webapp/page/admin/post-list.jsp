<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生列表</title>
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/page/admin/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/page/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery.sPage.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.sPage.js"></script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 帖子管理 <span class="c-gray en">&gt;</span> 帖子列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l"> 
 			<a href="javascript:" onclick="deleteByquery()" class="btn btn-danger radius"><i class="icon-trash"></i><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		</span>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
		<select id="blockSelect">
			<option value="0">请选择版块</option>
			<c:forEach items="${blockList}" var="block" varStatus="status">
				<option value="${status.count}">${block.blockName}</option>
			</c:forEach>
		</select>
		
		
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				        <th width="30">ID</th>
						<th width="130">发帖人</th>
						<th width="60">主题</th>
						<th width="70">内容</th>
						<th width="160">发帖时间</th>
						<th width="60">版块</th>
						<th width="60">热门</th>
						<th width="50">选项</th>
			</tr>
		</thead>
		<tbody id="tbody">
			
		</tbody>
		
	</table>
	<div id="myPage" align="center" class="demo"></div>
	</div>
	
</div>

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
	
	blockSelect();
	//classesSelect();
	
	//文档初始化时，调用一遍showStudent方法去填充学生数据
	showPost(1,10,'');
	
});

function showPost(page,pageSize,blockName){ //点击分页按显示学生信息
	$.ajax({
		url:"${pageContext.request.contextPath}/PostManage/getPostByPaging",
		type:"get",
		dataType:"json",
		data:{"page":page,"pageSize":pageSize,"blockName":blockName},
		async:false,
		success:function(paging){
			$("#tbody").html("");
			$.each(paging.list,function(index,post){
				$("#tbody").append(
						'<tr class="text-c">'+
						'<td><input class="checkbox" type="checkbox" name=""></td>'+
						'<td>'+post.pId+'</td>'+
						'<td>'+post.user.userName+'</td>'+
						'<td>'+post.title+'</td>'+
						'<td>'+post.content+'</td>'+
						'<td>'+post.postTime+'</td>'+
						'<td>'+post.block.blockName+'</td>'+
						'<td class="isHot">'+post.isHot+'</td>'+
						'<td><a style="text-decoration:none" class="ml-5" onclick=post_update("'+post.pId+'") title="编辑">'+
						'<i class="Hui-iconfont">&#xe6df;</i></a>'+ 
						'<a style="text-decoration:none" class="ml-5" onclick=post_del(this,'+post.pId+') title="删除">'+
						'<i class="Hui-iconfont">&#xe6e2;</i></a></td>'+
					'</tr>'
						
				);
			});
			if(0==paging.totalCount){
				sPageInit(1,paging.currentPage,paging.pageSize,blockName); //填充分页条信息	
			} else {
				sPageInit(paging.totalCount,paging.currentPage,paging.pageSize,blockName); //填充分页条信息
			}
			
		},
		error:function(){
			alert('error');
		}
	});
	changeHot();
}
function sPageInit(total,page,pageSize,blockName){ //page当前页，total总条数，pageSize页面大小 
	$("#myPage").sPage({ //对分页条进行数据填充
  	    page:page,//当前页码，必填   
        total:total,//数据总条数，必填    
        pageSize:pageSize,//每页显示多少条数据，默认10条
        showTotal:true,//是否显示总条数，默认关闭：false
        totalTxt:'共'+total+'条',//数据总条数文字描述，{total}为占位符，默认"共{total}条" //可以不要
        showSkip:true,//是否显示跳页，默认关闭：false
        showPN:true,//是否显示上下翻页，默认开启：true
        prevPage:"上一页",//上翻页文字描述，默认“上一页”
        nextPage:"下一页",//下翻页文字描述，默认“下一页”
        backFun:function(page){
        	showPost(page,pageSize,blockName);
        }
	});
}

function blockSelect(){ //选择版块 
	$("#blockSelect").change(function(){
		if("0"==$("#blockSelect option:selected").val()){
			//选择第一个选项，无动作
		} else {
			var blockName = $("#blockSelect option:selected").text();
			showPost(1,10,blockName);
		}
	});
}

function deleteByquery(){ //批量删除 
	var ele = $(".checkbox"); //获取所以复选框对象
	var list = [];
	$.each(ele,function(index,value){
		if($(this).is(':checked')){ //如果复选框被选中		
			list[list.length] = $(this).parent().next().text(); //填进数组
		}
	});
	if(0==list.length){
		layer.msg('至少选择一个帖子',{icon:5,time:2000});
	} else {
		//先询问是否删除
		if(confirm('您确定要删除吗?')){		 
			$.ajax({
				url:"${pageContext.request.contextPath}/PostManage/deletePostByQuery",
				type:"get",
				dataType:"json",
				data:{"postIdList":JSON.stringify(list)},//帖子ID列表
				success:function(data){
					if(1==data.res){
						layer.msg('删除成功',{icon:1,time:2000});	
						location.reload();
					}
				},
				error:function(){
					alert('error');
				}
			});
		}
	}
}
function post_update(postId){ //跳转至学生更新页面
	var title = "编辑";
	var url = "${pageContext.request.contextPath}/PostManage/post-update?postId="+postId;
	var h = "700";
	var w = "1000";
	layer_show(title,url,w,h);
}
/*学生-删除*/
function post_del(obj,postId){
		if(confirm('您确定要删除吗?')){
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/PostManage/deletePost",
					dataType:"json",
					data:{"postId":postId},
					async:false,
					success: function(data){
						layer.msg('已删除!',{icon:1,time:1000});
						location.reload();
					},
					error:function(data) {
						//console.log(data.msg);
						alert('error');
					}
				});
		}
		
}

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
function changeHot(){ //将热门数字改为中文
	var ele = $(".isHot");
	$.each(ele,function(index,value){
		if('1'==$(this).text()){
			$(this).text('是');
		} else {
			$(this).text('否');
		}
	});
}
</script> 
</body>
</html>