<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10"/>
    <script type="text/javascript" src="../js/common/jquery-1.11.3.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="../css/common/common.css" />
</head>
<style>
	html,body{height: 100%;}
	.login{width: 100%;height: 100%;position: relative;}
	.loginCentent{width: 100%;height: 100%;position: absolute;top: 0;left: 0;background: url(../images/common/index.jpg) no-repeat;background-size:100% 100%;}
	.boHongLogo{width:150px;height:30px;position: absolute;top: 6%;left: 20%;background: url(../images/common/executeControl.png) no-repeat;background-size:100% 100%;}
	.loginCenten{width:368px;height:442px;position: absolute;left: 50%;top: 50%;margin:-184px 0 0 -221px; background-size: 100% 100%;border-radius: 10px;}
	.loginCententA{position: relative;}
	.loginImg{width:300px;height:76px;margin: 0 auto;background: url(../images/common/vipImg.png) no-repeat;background-size:100% 100%;}
	.marginNEw{margin-top:40px;width:300px;margin: 0 auto;}
	.loginUserName,.loginPassword{width:100%;position: relative;line-height:34px;border: 1px solid #3d4b5e;border-radius: 2px;background: #1d8bd8;}
	.loginUserName input,.loginPassword input{border:none;display:inline-block;line-height: 30px;width: 269px;
		height: 34px;padding-left: 4px;box-sizing: border-box;font-size:16px;position: absolute;right:0;}
	.marginNEw span{display:inline-block;margin:5px 3px 3px 3px;vertical-align: top;}
	.userNameIco{background: url(../images/common/userName.png) no-repeat;width: 20px;height: 20px;background-size: 100% 100%;}
	.loginUserName{margin-top:70px;}
	.loginPassword{margin-top:28px;}
	.userPassword{background: url(../images/common/password.png) no-repeat;background-size: 100% 100%;}
	.loginBtn{width: 100%;margin-top: 30px;height: 34px;background: #1d8bd8;border-radius: 4px;border: none;color: #fff;margin-left: 4px;line-height: 34px;font-size: 16px;cursor: pointer;}
	.loginBanQuan{position:absolute;bottom: 6px;font-size:13px;height:30px;text-align:center;width: 100%;}
	.marginNEw .userNameNull{position:absolute;color:red;font-size:14px;display:none;left:86px;top:183px;}
	.marginNEw .passwordNull{position:absolute;color:red;font-size:14px;display:none;left:86px;top:248px;}
</style>
<body>
    <div class="login">
    	<div class="loginCentent">
    		<p class="boHongLogo"></p>
    	</div>
    	<div class="loginCenten">
    		<div class="loginCententA">
    			<p class="loginImg"></p>
    			<div class="marginNEw">
    				<div class="loginUserName">
    					<span class="userNameIco"></span>
        				<input type="text" id="userNameVAL" onkeypress="if (event.keyCode == 13) keydown()" />
    				</div>
    				<div class="loginPassword">
    					<span class="userNameIco userPassword"></span>
        				<input type="password" id="passwordVALUE" onkeypress="if (event.keyCode == 13) keydown()" />
    				</div>
    				<button class="loginBtn" onclick="keydown()" >登录</button>
    				<p class="userNameNull">用户名不能为空</p>
        			<p class="passwordNull"></p>
    			</div>
    		</div>
    	</div>
    	<div class="loginBanQuan text-center">Copyright&copy; 2016-2030 BOOMHOPE.COM.ALL Right Reserved 博宏信息技术有限公司 版权所有</div>
    </div>
    <script>
       function keydown(){
    	$(".userNameNull,.passwordNull").hide();
    	if($("#userNameVAL").val() == ""){
    		$(".userNameNull").show();
    			return;
        } if($("#passwordVALUE").val()==""){
        	$(".passwordNull").show().html("密码不能为空");
        	return;
        }else{ 
            	var loginName = $("#userNameVAL").val();// 账号
        		var passWord = $("#passwordVALUE").val();// 密码
        		var data={loginName:loginName,passWord:passWord}
        		console.log(data);
                $.ajax({
                    url:"<%=basePath%>login",
                    cache:false,
                    type:"post",
                    datatype:'json',
                    data:data,
                    beforeSend:function(){},
                    success:function(data){ 
                    	console.log(data);
                    	if(data.success== true){
                           // busGoto("");
                            location.href = "main.jsp";
        				}else{
        					$(".passwordNull").show().html(data.errmsg);
                            }
                    },
                    complete:function(){},
                    error:function(){}
                })
   			 } 
            
        };
        function busGoto(fileName)
		{
			$.get(fileName,{},function(res){
				$("body").html(res);
			});
		};
    </script>
</body>
</html>