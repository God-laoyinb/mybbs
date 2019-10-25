<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>详情</title>
	<link rel="shortcut icon" href="images/favicon.png" />
    <link rel='stylesheet' id='bootstrap-css-css'  href='${pageContext.request.contextPath}/static/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='${pageContext.request.contextPath}/static/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='${pageContext.request.contextPath}/static/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='${pageContext.request.contextPath}/static/css/main5152.css?ver=1.0' type='text/css' media='all' /></head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery.sPage.css">
<body>
	<div class="header-wrapper">
		<header>
			<div class="container">
				<nav class="main-nav">
                   <div class="menu-top-menu-container">
	                   <ul id="menu-top-menu" class="clearfix">
	                   		<li><a href="${pageContext.request.contextPath}/${prePage}">返回</a></li>
	                   		<li><a href="${pageContext.request.contextPath}/Index/index">首页</a></li>
							<li><a href="${pageContext.request.contextPath}/BlockList/blockList">版块</a></li>
							<li><a href="${pageContext.request.contextPath}/PostList/postList">帖子</a></li>
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
	<c:set value="${post}" var="post"></c:set>
	<div class="page-container">
		<div class="container">
			<div class="row">
				<div class="span8 page-content">
			        <article class=" type-post format-standard hentry clearfix">
		                <h1 class="post-title"><a href="">${post.title}</a></h1>
		                <div class="post-meta clearfix">
		                	<span class="category">${post.user.userName}</span>
	                        <span class="date">${post.postTime}</span>
	                        <span class="comments"><a id="replyCount" title="Comment on Integrating WordPress with Your Website">0 评论</a></span>
		                </div>
		               <p>${post.content}</p>
			        </article>
			    
			        <section id="comments">
		                <h3 id="comments-title">回复列表</h3>
		                <ol class="commentlist" id="commentList">

		                </ol>
		                 <div id="myPage" align="center" class="demo"></div> 
		                <div id="respond"> <!-- 评论框  -->
	                        <h3>回复</h3>
	                        
                            <div>
                            	<textarea class="span8" name="comment" id="comment" cols="58" rows="10"></textarea>
                            </div>
                            <div>
                                <input onclick=reply() class="btn" name="submit" type="button" id="submit"  value="发表评论">
                            </div>
		                </div>
			        </section>
				</div>
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
	<script>
		$(document).ready(function(){
			validate();
			getReply(1,10);
		});
		function reply(){ //评论
			if (!validateLogin()){
				layer.msg('请先登录',{icon:0,time:2000});
				return false;
			}
			if(validateReply()){
				var postId = "${post.pId}";
				var content = $("#comment").val();
				$.ajax({
					url:"${pageContext.request.contextPath}/PostDetail/addReply",
					type:"get",
					dataType:"json",
					async:false,
					data:{"content":content,"postId":postId},
					success:function(data){
						layer.msg('回复成功',{icon:1,time:2000});
					},
					error:function(){
						alert('error');
					}
				});
				location.reload();
			}
		}
		function showPostDetail(){ //显示帖子详情
			
		}
		function getReply(page,pageSize){ //获取评论
			$.ajax({
				url:"${pageContext.request.contextPath}/PostDetail/getReply",
				type:"get",
				dataType:"json",
				data:{"page":page,"pageSize":pageSize,"postId":"${post.pId}"},
				async:false,
				success:function(paging){
					setReplyCount(paging.totalCount);
					$("#commentList").html('');
					$.each(paging.list,function(index,reply){
						$("#commentList").append(
							'<li class="comment even thread-odd thread-alt depth-1" id="li-comment-4">'+
	                              '<article id="comment-4">'+
	                              '<div class="comment-meta">'+
	                              	'<h5 class="author">'+
	                                '<cite class="fn"><a href="#" rel="external nofollow" class="url">'+reply.user.userName+'</a></cite>'+
	                                	'- <a class="comment-reply-link" href="#">回复</a>'+
	                                '</h5>'+
	                                '<p class="date">'+
                                    	'<time>'+reply.replyTime+'</time>'+
                            		'</p>'+
	                               '</div>'+
	                               '<div class="comment-body">'+
	                               		'<p>'+reply.content+'</p>'+
	                               '</div>'+
	                              '</article>'+
	                      	 '</li>'		
						);
					});
					sPageInit(paging.totalCount,paging.currentPage,paging.pageSize);
				},
				error:function(){
					alert('error');
				}
			});
		}
		function setReplyCount(replyCount){ //放置评论数
			$("#replyCount").text(replyCount+'评论');
		}
		function validateReply(){ //验证输入回复的正确性（判空，判长度）
			var res = true;
			var reply = $("#comment").val();
			if(''==reply){
				layer.msg('回复不为空',{icon:0,time:2000});
				res = false;
			}
			if(50<reply.length){
				layer.msg('长度不超过50',{icon:0,time:2000});
				res = false;
			}
			return res;
		}
		function validateLogin(){ //验证用户登录状态
			var res = true;
			$.ajax({
				url:"${pageContext.request.contextPath}/Login/validateLogin",
				type:"get",
				async:false,
				dataType:"json",
				data:{},
				success:function(data){
					if(0==data.res){
						res = false;	
					}
				},
				error:function(){
					alert('error');
				}
			});
			return res;
		}
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
		        	getReply(page,pageSize);
		        }
			});
		};
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
