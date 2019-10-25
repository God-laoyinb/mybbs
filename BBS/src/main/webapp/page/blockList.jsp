<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>版块</title>
<link rel="shortcut icon" href="images/favicon.png" />
<link rel='stylesheet' id='bootstrap-css-css'  href='${pageContext.request.contextPath}/static/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css-css'  href='${pageContext.request.contextPath}/static/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='pretty-photo-css-css'  href='${pageContext.request.contextPath}/static/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
<link rel='stylesheet' id='main-css-css'  href='${pageContext.request.contextPath}/static/css/main5152.css?ver=1.0' type='text/css' media='all' />
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery-1.9.1.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.easing.1.3.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/prettyphoto/jquery.prettyPhoto.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jflickrfeed.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.form.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.validate.min.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/static/js/custom.js'></script>	
</head>
<body>
	<div class="header-wrapper">
		<header>
			<div class="container">                                        
				<nav class="main-nav">
			        <div class="menu-top-menu-container">
		                <ul id="menu-top-menu" class="clearfix">
	                        <li><a href="${pageContext.request.contextPath}/Index/index">首页</a></li>
	                        <li><a href="">版块</a></li>
	                        <li class="current-menu-item"><a href="${pageContext.request.contextPath}/PostList/postList">帖子</a></li>
	                        <li id="Liid"><a href="${pageContext.request.contextPath}/Login/login" id="loginid">登录</a>/
	                     <a href="${pageContext.request.contextPath}/Regist/regist" id="registid">注册</a></li>
	                     <li><a href="javascript:void(0);" id="Unameid" Style="font-size: 30px">${user.userName}</a></li>
	                     <li><a href="${pageContext.request.contextPath}/Login/userlogout" id="logoutid">:[退出]</a></li>
		                </ul>
			        </div>
				</nav>
			</div>
		</header>
	</div>
    <div class="page-container">
		<div class="container">
        	<div class="row">
            	<div class="span8 page-content">
	                <div class="row home-listing-area">
	                    <div class="span8">
                        	<h2>版块列表</h2>
	                    </div>
	                </div>
	                <div class="row separator">
	                <c:forEach items="${blockList}" var="block">
	                    <section class="span4 articles-list">
	                        <h3><a onclick="getPostByBlock('${block.blockName}')">${block.blockName}</a><span></span></h3>
	                        <ul class="articles">
	                            <li class="article-entry image">
	                                <h4><a href="single.html">帖子标题</a></h4>
	                                <span class="article-meta">发帖时间</span>
	                            </li>
	                        </ul>
	                    </section>
	                </c:forEach>
                   </div> 
                   </div>
				</div>
			</div>
		</div>
    <a href="#top" id="scroll-top"></a>
    </body>
    <script>
    	$(document).ready(function(){
    		validate();
    	});
    	function like(){ //点赞
  			  		
    	}
    	function postDetail(postId){ //跳转到帖子详情
    		window.location.href="${pageContext.request.contextPath}/PostDetail/postDetail?postId="+postId;
    	};
    	function getPostByBlock(blockName){ //通过版块名称去查询帖子列表并跳转到帖子列表页面
    		window.location.href="${pageContext.request.contextPath}/PostList/postList?blockName="+blockName;
    	}
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
    			    	}
    			    	
    			    	if(date.res==1){
    			    		$("#Unameid").show();
    						$("#logoutid").show();
    			    	}
    			    	
    			    },
    			    error:function(){
    					alert('error');
    				}
    			});
    	}
    </script>
</html>