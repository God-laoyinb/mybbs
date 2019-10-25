<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" />
<link href="${pageContext.request.contextPath}/page/admin/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form enctype="multipart/form-data" action="" method="post" class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>发帖人：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${userName}" placeholder="" id="userName" name="" autocomplete="off" disabled="disabled">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>版块：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<select id="blockSelect" class="select">
						<option value="0">请选择版块</option>
							<c:forEach items="${blockList}" var="block" varStatus="status">
								<option value="${status.count}">${block.blockName}</option>
							</c:forEach>
					</select>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>主题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${title}" placeholder="" id="title" name="" autocomplete="off">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>热门：</label>
			<div class="formControls col-xs-8 col-sm-9" id="hot">
				<input type="radio"  name="hot" value="1"> 是
				<input type="radio" name="hot" value="0" checked="checked"> 否
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">发帖时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${postTime}" placeholder="" name="" autocomplete="off" disabled="disabled">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">内容：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="content" name="" cols="" rows="" class="textarea" style="width:710px ;height:350px"  placeholder="说点什么...最少输入10个字符">${content}</textarea>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="save();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script id="editor" type="text/plain" style="width: 750px; height: 350px; margin: 0px auto;"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $("#blockSelect").val("${blockValue}");
	});
function save(){
	var userName = $("#userName").val();
	var title = $("#title").val();
	var blockName = $("#blockSelect option:selected").text();
	var content = $("#content").val();
	var hot = $('input[name="hot"]:checked').val();
	if(!validate()){
		
	} else {
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/PostManage/updatePost",
			dataType:"json",
			async:false,
			data:{"postId":"${postId}","userName":userName,"title":title,"blockName":blockName,"content":content,"hot":hot},
			success:function(data){
				if(data.res==1){
					parent.location.reload();
				} else {
					
				}
			},
			error:function(){
				alert("error");	
			}
		});
	}
}
function validate(){//验证输入的准确性
	var res = true;
	var userName = $("#userName").val();
	var title = $("#title").val();
	var blockName = $("#blockSelect option:selected").text();
	var content = $("#content").val();

	if(content==''){
		layer.msg('请输入内容',{icon:0,time:2000});
		res = false;
	}
	if(title==0){
		layer.msg('请选择主题',{icon:0,time:2000});
		res = false;
	}
	if(blockName==0){
		layer.msg('请选择版块',{icon:0,time:2000});
		res = false;
	}
	if(userName==''){
		layer.msg('请输入发帖人',{icon:0,time:2000});
		res = false;
	}
	return res;
}

</script>
</body>
</html>