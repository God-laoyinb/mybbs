<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
	<head>
            
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0">

                <title>欢迎注册</title>
                <!-- Style Sheet-->
                <link rel="stylesheet" href="style.css"/>
                <link rel='stylesheet' id='bootstrap-css-css'  href='${pageContext.request.contextPath}/static/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='responsive-css-css'  href='${pageContext.request.contextPath}/static/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='pretty-photo-css-css'  href='${pageContext.request.contextPath}/static/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
                <link rel='stylesheet' id='main-css-css'  href='${pageContext.request.contextPath}/static/css/main5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='custom-css-css'  href='${pageContext.request.contextPath}/static/css/custom5152.html?ver=1.0' type='text/css' media='all' />


                <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
                <!--[if lt IE 9]>
                <script src="js/html5.js"></script></script>
                <![endif]-->

        </head>

        <body>
                <!-- Start of Header -->
                <div class="header-wrapper">
                        <header>
                                <div class="container">
                                        <!-- Start of Main Navigation -->
                                        <nav class="main-nav">
                                                <div class="menu-top-menu-container">
                                                        <ul id="menu-top-menu" class="clearfix">
                                                                <li><a onclick="history.back()">返回</a></li>
                                                                <li><a href="${pageContext.request.contextPath}/Index/index">首页</a></li>
                                                                <li><a href="${pageContext.request.contextPath}/Login/login">登录</a></li> 
                                                                <li><a href="#">${message}</a></li>                                                               
                                                        </ul>
                                                </div>
                                        </nav>
                                        <!-- End of Main Navigation -->
                                </div>
                        </header>
                </div>
                <!-- End of Header -->

                <!-- Start of Search Wrapper -->
                <div class="search-area-wrapper" >
                        <div class="search-area container" style="height: 800px;">
                                <h3 class="search-header">用户注册</h3>
                                <p class="search-tag-line">感谢您的支持，请遵循规范进行注册，祝您愉快!</p>
									
                                <form id="register" style="width: 400px;height: 400px;margin: 0 auto;"  method="post" action="${pageContext.request.contextPath}/Regist/registform" autocomplete="off" >
                                        <input type="text"  id="userNameId" name="username" placeholder="用户名" style="display:block;margin: 0 18px;width: 80%;height: 9%;"/>
                                         <input type="password"  id="passwordId" name="password" placeholder="密码"  style="display:block;margin: 20px 18px ;width: 80%;height: 9%;"/>
                                         <input type="password"  id="passwordId2" name="password2" placeholder="确认密码"  style="display:block;margin: 20px 18px ;width: 80%;height: 9%;"/>
                                        <input  id="submitId" type="button" value="注册"  style="display:block;margin: 20px 120px ;width: 30%;height: 11%;background-color:#353B65;border: 0;font-size: 30px;color: white;"/>
                          
                                </form>
                        </div>
                </div>
                <a href="#top" id="scroll-top"></a>
                <!-- script -->
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery-1.9.1.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.easing.1.3.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/prettyphoto/jquery.prettyPhoto.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jflickrfeed.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.liveSearch.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.form.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/jquery.validate.min.js'></script>
                <script type='text/javascript' src='${pageContext.request.contextPath}/static/js/custom.js'></script>
                <script src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
				<script type="text/javascript">
				$(function(){	
					registershow();
				});
				function registershow(){
					$("#submitId").click(function(){
						var valid = true;
						var username = $("#userNameId").val();
						var password = $("#passwordId").val();
						var password2 = $("#passwordId2").val();
						if(!validNotEmpty(username,"请输入用户名")){
							valid =false;
							return;
						}
						else{
							if(!validCheckdName(username,"不能包含特殊字符")){
								valid = false;
								return;
							}
						}
						if(!validNotEmpty(password,"请输入密码")){
							valid=false;
							return;
						}
						else{
							if(!validLength(password,"密码长度必须在6~20位之间")){
								valid=false;
								return;
							}
						}
						if(!validNotEmpty(password2,"请输入确认密码")){
							valid=false;
							return;
						}
						else{
							if(!validequals(password,password2,"密码不一致")){
								valid=false;
								return ;
							}
						}
						if(valid==true){
							//layer.msg("注册成功，返回登录页面");
							registervalid();
		
						}
					});
				}
				function registervalid(){
					var username = $("#userNameId").val();
					var password = $("#passwordId").val();
					//alert(username+password);
					$.ajax({
						url:"${pageContext.request.contextPath}/Regist/registform",
						type:"post",
						data:{"userName":username,"password":password},
						dataType:"json",
						async:false,
						success:function(data){
							if(data.res==1){
								var input = document.getElementById("submitId");
								input.disabled="disabled";
								layer.msg('注册成功，前往注册页面',{icon:1,time:2000});
	                            location.replace("${pageContext.request.contextPath}/Login/login");//跳转到login页面
							}
							if(data.res==0){
								$("#userNameId").val('');
								$("#passwordId").val('');
								$("#passwordId2").val('');
								layer.msg('用户已存在',{icon:0,time:3000});					
							}
							if(data.res==2){
								layer.msg('注册失败',{icon:0,time:2000});
							}
						},
						error:function(){
							alert('error');
						}
						
					});	
				}
				function validNotEmpty(str,mes){
					if(str==''||str.trim()==""){
						layer.msg(mes);
						return false;
					}
					else{
						return true;
					}
				}
				function validCheckdName(str,mes){
					var regu = "^[a-zA-Z\u4e00-\u9fa5]+$"; 
					var re = new RegExp(regu); 
					if(re.test(str)){
						//layer.msg(str);
						return true;
					}
					else{
						layer.msg(mes);
						return false;
					}
				}
				function validLength(str,mes){
					
					if(str.length<6||str.length>20){
						layer.msg(mes);
						return false;
					}
					else{
						return true;
					}
				}
				function validequals(str,str2,mes){
					if(str!==str2){
						layer.msg(mes);
						return false;
					}
					else{
						return true;
					}
				}
				</script>
        </body>
</html>
