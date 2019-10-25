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
</head>
<body>
	<div class="header-wrapper">
		<header>
			<div class="container">
                <nav class="main-nav">
	                <div class="menu-top-menu-container">
	                    <ul id="menu-top-menu" class="clearfix">
	                        <li><a href="${pageContext.request.contextPath}/Index/index">首页</a></li>
	                        <li><a href="${pageContext.request.contextPath}/BlockList/blockList">版块</a></li>
	                        <li class=""><a href="">帖子</a></li>
	                        <li><a href="${pageContext.request.contextPath}/Login/login">登录</a></li>
	                    </ul>
	                </div>
                </nav>
	        </div>
	   </header>
	</div>
    
    <div class="page-container">
	    <div class="container">
	        <div class="row">
	            <div class="span8 main-listing" id="postContent"><!-- postContent 放帖子的容器 -->
	            </div>
	        </div>
	    </div>
	     <div id="myPage" align="center" class="demo"></div> 
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
<script>
	$(document).ready(function(){
		showPost(1,10);
		searchPost("${keyword}");
	});    		
	function postDetail(postId){ //进入帖子详情
		window.location.href="${pageContext.request.contextPath}/PostDetail/postDetail?postId="+postId;
	};
	function showPost(page,pageSize){ //显示帖子
		var blockName = "${blockName}";
		
		if(''!=blockName){
			$.ajax({
				url:"${pageContext.request.contextPath}/PostList/getPostByPaging",
				type:"get",
				dataType:"json",
				async:false,
				data:{"blockName":blockName,"page":page,"pageSize":pageSize},
				success:function(paging){
					$("#postContent").html("");
					$.each(paging.list,function(index,post){
						$("#postContent").append(
								'<article class=" type-post format-image hentry clearfix">'+
			                    '<header class="clearfix">'+
			                    	'<h3 class="post-title">'+
			                    		'<a onclick=postDetail("'+post.pId+'")>'+post.title+'</a>'+
			                        '</h3>'+
			                        '<div class="post-meta clearfix">'+
			                        	'<span class="category">'+post.user.userName+'</span>'+
			                        	'<span class="date">'+post.postTime+'</span>'+
				                    	'<span class="comments"><a href="#" title="Comment on Using Images">'+post.replyNum+'回复</a></span>'+
			                        '</div>'+
			                    '</header>'+
			                    '<p>'+post.content+'</p>'+
			                    '<a href="#" title="Using Images"><img width="770" height="501" src="${pageContext.request.contextPath}/static/images/temp/living-room-770x501.jpg" class="attachment-std-thumbnail wp-post-image" alt="Living room"></a>'+
			                '</article>'
							);	
					});
					sPageInit(paging.totalCount,paging.currentPage,paging.pageSize); //填充分页条信息
				},
				error:function(){
					alert('error');
				}
			});
		}
	
	};
	function sPageInit(totalCount,currentPage,pageSize){ //分页初始化
		$("#myPage").sPage({ //对分页条进行数据填充
	  	    page:currentPage,//当前页码，必填   
	        total:totalCount,//数据总条数，必填    
	        pageSize:pageSize,//每页显示多少条数据，默认10条
	        showTotal:true,//是否显示总条数，默认关闭：false
	        totalTxt:'共'+totalCount+'条',//数据总条数文字描述，{total}为占位符，默认"共{total}条" //可以不要
	        showSkip:true,//是否显示跳页，默认关闭：false
	        showPN:true,//是否显示上下翻页，默认开启：true
	        prevPage:"上一页",//上翻页文字描述，默认“上一页”
	        nextPage:"下一页",//下翻页文字描述，默认“下一页”
	        backFun:function(page){
	        	showPost(page,pageSize);
	        }
		});
	};
	function getCommentCount(postId){ //获取每条帖子评论数
		$.ajax({
			
			
		});
	}
	function searchPost(keyword){ //搜索帖子
		$("#search").click(function(){ // 搜索按钮被点击
			if(''!="${keyword}"){
				keyword = "${keyword}";
			}
			if(''==$("#keyword").val()){
				layer.msg('请输入关键字',{icon:0,time:2000});
			} else {
				
			}
		});
	}
	function validateLogin(){ //验证登录状态
		
	}
	function showImage(){ //如果帖子中有图片，则显示图片
		
	}
</script>
</html>
