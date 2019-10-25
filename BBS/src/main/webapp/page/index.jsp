<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>
<link rel="shortcut icon" href="images/favicon.png" />
<link rel='stylesheet' id='bootstrap-css-css'  href='${pageContext.request.contextPath}/static/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css-css'  href='${pageContext.request.contextPath}/static/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='pretty-photo-css-css'  href='${pageContext.request.contextPath}/static/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
<link rel='stylesheet' id='main-css-css'  href='${pageContext.request.contextPath}/static/css/main5152.css?ver=1.0' type='text/css' media='all' />
</head>
<body>
	<div class="header-wrapper">
		<header>
  		<div class="container">
             <nav class="main-nav">
	              <div class="menu-top-menu-container">
	                 <ul id="menu-top-menu" class="clearfix">
	                     <li><a href="${pageContext.request.contextPath}/PersonalCenter/personalCenter">个人中心</a></li>
	                     <li class="current-menu-item"><a href="#">首页</a></li>
	                     <li><a onclick="posting()">发帖</a></li>
	                     <li><a href="${pageContext.request.contextPath}/BlockList/blockList">版块</a></li>
	                     <li><a href="${pageContext.request.contextPath}/PostList/postList">帖子</a></li>
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
    <div class="search-area-wrapper">
	    <div class="search-area container">
	        <h3 class="search-header">搜索帖子</h3>
	        <p class="search-tag-line">查找你所感兴趣的帖子!</p>
	       <form id="search-form" class="search-form clearfix"  action="${pageContext.request.contextPath}/PostList/postList?page=1&&pagesize=10" autocomplete="off">
	            <input class="search-term required" type="text" id="keywordid" name="keyword" placeholder="输入关键字" title="请输入关键字" />
	            <input id="search" class="search-btn" type="submit" value="全站搜索" />
	         </form>
	    </div>
    </div>
 	<div class="page-container">
         <div class="container">
	         <div class="row">
                 <div class="span8 page-content">
	                 <div class="row separator">
	                     <section class="span4 articles-list">
	                             <h3>热门帖子</h3>
	                             <ul class="articles" id="hotPost">
	                                 
	                             </ul>
	                     </section>
                         <section class="span4 articles-list">
                                 <h3>最新帖子</h3>
                                 <ul class="articles" id="lastPost">
	                                 
                                 </ul>
                         </section>
	                 </div>
                 </div>
                 <aside class="span4 page-sidebar">
	                 <section class="widget">
	                     <div class="support-widget">
	                         <h3 class="title">用户名</h3>
	                         <p class="intro">Need more support? If you did not found an answer, contact us for further help.</p>
	                     </div>
	                 </section>    
				 </aside>
	         </div>
         </div>
	</div>
	      <a href="#top" id="scroll-top"></a>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery-1.9.1.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.easing.1.3.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/prettyphoto/jquery.prettyPhoto.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jflickrfeed.js'></script>
		  <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.liveSearch.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.form.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.validate.min.js'></script>
	      <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/custom.js'></script>
		  <script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
	</body>
	<script type="text/javascript">
		function getPost(type,num){
			$.ajax({
				url:"${pageContext.request.contextPath}/Index/getPost",
				type:"get",
				data:{"num":num,"type":type},
				dataType:"json",
				async:false,
				success:function(postList){
					var target;
					if(type==1){
						target = "#hotPost";
					}
					if(type==2){
						target = "#lastPost";
					}
					$.each(postList,function(index,post){
						$(target).append(
							'<li class="article-entry standard">'+
	                             '<h4><a onclick=postDetail("'+post.pId+'")>'+post.title+'</a></h4>'+
	                             '<span class="article-meta">'+post.postTime+'<a href="" title="">&nbsp;&nbsp;作者:'+post.user.userName+'</a></span>'+
	                         '</li>'		
						);
					});
				},
				error:function(){
					alert('error');
				}
			});
		}
		function postDetail(postId){ //跳转到帖子详情页面
			window.location.href="${pageContext.request.contextPath}/PostDetail/postDetail?postId="+postId;	
		}		
		function posting(){ //发帖
			var url = "${pageContext.request.contextPath}/Posting/posting";
			//layer_show('发帖',url,'500','500');
			
			layer.open({
				  type: 2,
				  title: '发帖',
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1000px', '600px'],
				  offset: 'c', //右下角弹出
				 
				  anim: 2,
				  content: ['${pageContext.request.contextPath}/Posting/posting', 'yes'], //iframe的url，no代表不显示滚动条
				
				});
		}
		$(document).ready(function(){
			validate();
			getPost(1,5);
			getPost(2,5);
			
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