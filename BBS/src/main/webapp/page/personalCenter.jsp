<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>列表</title>
	<link rel="shortcut icon" href="images/favicon.png" />
	    <link rel='stylesheet' id='bootstrap-css-css'  href='${pageContext.request.contextPath}/static/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
	    <link rel='stylesheet' id='responsive-css-css'  href='${pageContext.request.contextPath}/static/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
	    <link rel='stylesheet' id='pretty-photo-css-css'  href='${pageContext.request.contextPath}/static/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
	    <link rel='stylesheet' id='main-css-css'  href='${pageContext.request.contextPath}/static/css/main5152.css?ver=1.0' type='text/css' media='all' />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery.sPage.css">
<style type="text/css">
	.button1{
	width:100px;
	height:40px;
	background-color:rgb(25,95,251) ;
	border: 0;
	color:white;
	font-size: 20px;
	}
	.p1{
		width:1100px;
		background-color: red;
		margin: 0 auto;
		margin-top: 30px;
		text-align: center;
		
		
	}
	.pp2{
		width:300px;
		height:600px;	
		background-color: rgb(233,252,992);
		float: left;
	}
	.pp2-1{
		margin-left:10px;
		width:700px;
		height:600px;
		
		float: left;
		border-top:1px solid black;
		border-left:1px solid black;
		border-right:1px solid black;
		border-bottom:1px solid black;
	}
	.pp3{
		width:150px;
		height:150px;
		float: left;
			
		
	}
	.pp3-1{
		width:150px;
		height:150px;
		float: left;

	}
	.pp4{
	width:700px;
	height: 90px;
	}
	.pp4-1{
	width:700px;
	height: 60px;
	text-align: left;
	}
	.pp4-2{
		width:700px;
		height: 450px;
	}
</style>
</head>

<body>

	<div class="header-wrapper" style="display: block;">
		<header>
			<div class="container">
                <nav class="main-nav">
	                <div class="menu-top-menu-container">
	                    <ul id="menu-top-menu" class="clearfix">
	                        <li><a href="${pageContext.request.contextPath}/Index/index">首页</a></li>
	                        <li><a href="${pageContext.request.contextPath}/BlockList/blockList">版块</a></li>
	                        <li class=""><a href="">帖子</a></li>
	                        <li id="Liid"><a href="${pageContext.request.contextPath}/Login/login" id="loginid">登录</a><b>/</b>
	                     <a href="${pageContext.request.contextPath}/Regist/regist" id="registid">注册</a></li>
	                     <li><a href="javascript:void(0);" id="Unameid" Style="font-size: 30px">${user.userName}</a></li>
	                     <li><a href="${pageContext.request.contextPath}/Login/userlogout" id="logoutid">:[退出]</a></li>
	                    </ul>
	                </div>
                </nav>
	        </div>
	   </header>
	</div>
    <div class="search-area-wrapper" id="NotLoginDiv" style="display: none;">
   		 <div class="search-area container">
    		<center>
    		<h1>你还未登录</h1>
    		<h2>是否登录？</h2>
    		<a href="${pageContext.request.contextPath}/Login/login"><input type="button" class="button1" style="width:80px;" value="登录"/></a>
    		<a href="${pageContext.request.contextPath}/Regist/regist"><input type="button" class="button1" style="width:80px;background-color: green;" value="注册"/></a>
    		</center>
    	</div>
    </div>
    
   <div class="p1" style="display: none" id="LoginDiv">
   		<div class="pp2">
   			<div>
   			<h5 >个人信息</h5><hr size="2" align="center" width="270">
   			<div class="pp3">
   				<img alt="点击选择" src="${pageContext.request.contextPath}/static/images/faq-minus.png" style="width:140px;height:140px; border-radius:50%;">
   			</div>
   			<div class="pp3-1">
   				<h3 id="hname">用户名</h3>
   				<h6 style="color: blue;">粉丝：<a href="#" style="color: blue;">0</a></h6>
   				<h6 style="color: blue;">关注：<a href="#" style="color: blue;">0</a></h6>
   			</div>
   			</div >
   				<hr size="2" align="center" width="270">
   				<div style="text-align: right;margin-right: 50px;font-size: 20px;margin-top: 10px;">
   					<div style="margin-top: 10px;"><a href="${pageContext.request.contextPath}/Index/index" style="color: rgb(64,225,255);">首页</a><br/></div>
   					<div id="mypost" style="margin-top: 20px;"><a href="#" style="color: rgb(64,225,255);">我的帖子</a><br/></div>
   					<div id="myreply" style="margin-top: 20px;"><a href="#" style="color: rgb(64,225,255);">回复我的</a><br/></div>
   					<div id="myreplyme" style="margin-top: 20px;"><a href="#" style="color: rgb(64,225,255);">@提到我</a><br/></div>
   					<div id="mylove" style="margin-top: 20px;"><a href="#" style="color: rgb(64,225,255);">我的收藏</a><br/></div>
   					<div id="myapply" style="margin-top: 20px;"><a href="#" style="color: rgb(64,225,255);">好友申请</a><br/></div>
   				</div>
   		</div>
   		<div class="pp2-1">
			<div class="pp4"> 
			<img alt="点击选择" src="${pageContext.request.contextPath}/static/images/1.gif" style="width:660px;height:70px;margin-top: 10px;align-self: inherit;">
			</div>	
			<div class="pp4-1"> 
			<input id="mybutton" type="button" style="width:200px;height:50px;margin-left: 20px;font-size: 40px;background-color: rgb(244,244,244);border-bottom: none;" value="请选择" disabled="disabled">	
			<hr size="1" style="margin-top: 0px;">
			</div>
			<div id="viewdiv" class="pp4-2" style="display: none">
				<iframe src="${pageContext.request.contextPath}/page/mypost.jsp" width="100%" height="100%" frameborder="0"></iframe>
			</div>
			<div id="viewdivreply" class="pp4-2" style="display: none">
				<h5>暂无回复的消息</h5>
			</div>
			<div id="viewdivreplyme" class="pp4-2" style="display: none">
				<h5>暂时还没有人@我，快去关注更多人吧~</h5>
			</div>
			<div id="viewdivlove" class="pp4-2" style="display: none">
				<h5>暂时收藏，快去收藏喜欢的东西吧~</h5>
			</div>
			<div id="viewdivapply" class="pp4-2" style="display: none">
				<h5>还没有人添加你哦，快去认识更多人吧~</h5>
			</div>
			   		
   		</div>
   </div>
  
   
   
	<a href="#top" id="scroll-top"></a>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery-1.9.1.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.easing.1.34e44.js?ver=1.3'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/prettyphoto/jquery.prettyPhotoaeb9.js?ver=3.1.4'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.liveSearchd5f7.js?ver=2.0'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.formd471.js?ver=3.18'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.validate.minfc6b.js?ver=1.10.0'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/custom5152.js?ver=1.0'></script>
	<script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.sPage.js"></script>
</body>
<script type="text/javascript">

$(document).ready(function(){

	$("#Unameid").hover(function(){
		
	});
	$("#myapply").click(function(){
		$("#mybutton").val("我的收藏");
		$("#viewdivreplyme").css("display","none");
		$("#viewdiv").css("display","none");
		 $("#viewdivreply").css("display","none");
		 $("#viewdivlove").css("display","none");
		 $("#viewdivapply").css("display","block");
	});
	$("#mylove").click(function(){
		$("#mybutton").val("我的收藏");
		$("#viewdivreplyme").css("display","none");
		$("#viewdiv").css("display","none");
		 $("#viewdivreply").css("display","none");
		 $("#viewdivlove").css("display","block");
		 $("#viewdivapply").css("display","none");
	});
	$("#myreplyme").click(function(){
		$("#mybutton").val("@提到我");
		$("#viewdivreplyme").css("display","block");
		$("#viewdiv").css("display","none");
		 $("#viewdivreply").css("display","none");
		 $("#viewdivlove").css("display","none");
		 $("#viewdivapply").css("display","none");
	});
	$("#myreply").click(function(){
		$("#mybutton").val("回复我的");
		 $("#viewdiv").css("display","none");
		 $("#viewdivreply").css("display","block");
		 $("#viewdivreplyme").css("display","none");
		 $("#viewdivlove").css("display","none");
		 $("#viewdivapply").css("display","none");
	});
	$("#mypost").click(function(){
		$("#mybutton").val("我的帖子");
		 $("#viewdiv").css("display","block");
		 $("#viewdivreply").css("display","none");
		 $("#viewdivreplyme").css("display","none");
		 $("#viewdivlove").css("display","none");
		 $("#viewdivapply").css("display","none");
	});
	validate();
	
	
});
function validate(){
	$("#Liid").hide();
	$("#Unameid").hide();
	$("#logoutid").hide();
	$.ajax({
		url:"${pageContext.request.contextPath}/Login/validateLogin",
		type:"post",
		dataType:"json",
		async:false,
		success:function(date){
			
		    	if(date.res==0){
		    		$("#Liid").show();
		    		 $("#NotLoginDiv").css("display","block");
		    		 $("#LoginDiv").css("display","none");
		    	}
		    	
		    	if(date.res==1){
		    		$("#Unameid").show();
					$("#logoutid").show();
					$("#NotLoginDiv").css("display","none");
					$("#LoginDiv").css("display","block");
					$("#hname").html("${user.userName}");
		    	}
		    	
		    },
		    error:function(){
				alert('error');
			}
		});
}
	
</script>
</html>
