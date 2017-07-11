<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>VIP推送平台</title>
		<link rel="stylesheet" href="../css/common/layui.css">
		<link rel="stylesheet" href="../css/css/zTreeStyle/zTreeStyle.css"/>
		<link rel="stylesheet" href="../css/common/bootstrap.min.css"/>
		<link rel="stylesheet" href="../css/common/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="../css/common/validationEngine.jquery.css" />
		<link rel="stylesheet" href="../css/common/bootstrap-table.min.css"/>
		<link rel="stylesheet" href="../css/common/font-awesome.min.css"/>
		<link rel="stylesheet" href="../css/common/common.css" />
		<link rel="stylesheet" href="../css/page/page.css" />
		<script type="text/javascript" src="../js/common/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="../js/common/layui.js"></script>
		<script type="text/javascript" src="../js/common/tree.js"></script>
		<script type="text/javascript" src="../js/common/bootstrap.min.js" ></script>
		<script type="text/javascript" src="../js/common/bootstrap-table.min.js" ></script>
		<script type="text/javascript" src="../js/common/bootstrap-table-zh-CN.min.js" ></script>
		<script type="text/javascript" src="../js/common/bootstrap-datetimepicker.js" ></script>
		<script type="text/javascript" src="../js/common/bootstrap-datetimepicker.zh-CN.js" ></script>
		<script type="text/javascript" src="../js/common/jquery.ztree.core-3.5.js" ></script>
		<script type="text/javascript" src="../js/common/jquery.ztree.excheck-3.5.js" ></script>
		<script type="text/javascript" src="../js/common/jquery.ztree.exedit-3.5.js" ></script>
		<script type="text/javascript" src="../js/jQuery-Validation-Engine/jquery.validationEngine.js" ></script>
		<script type="text/javascript" src="../js/jQuery-Validation-Engine/jquery.validationEngine-zh_CN.js" ></script>
		<script type="text/javascript" src="../js/common/common.js" charset="UTF-8" ></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 PaddIngMargin">
					<div class="col-xs-2 PaddIngMargin">
						<div class="Top_Ico">
							<img class="Top_IcoX" src="../images/common/executeControl.png" alt="logo" />
						</div>
					</div>
					<div class="col-xs-10 PaddIngMargin common_menu">
						<!--一级菜单-->
						<div class="col-xs-12 text-center common_nav PaddIngMargin" id="commonNav">
							<div class="rowsCenter" id="firstLevelMenu">
								
							</div>
							<div class="rightOut">
									<span class="cursor rightOutIco" data-toggle="dropdown"></span>
									<span class="cursor" data-toggle="dropdown">设置</span>
									<ul class="dropdown-menu" role="menu">
								      <!-- 	<li class="geRenInformationBlock">
								           <span class="geRenXinXi"></span>
								           <a href="JavaScript:;">个人信息</a>
								           <div class="geRenInformation">
								           		<div class="InfoTopBak text-right">
								           			<img class="InfoImg" alt="头像" src="../images/common/InfoImg.jpg" />
								           			<p><i id="">张小三</i>(<i id="">0026</i>)</p>
								           			<div class="inforRightPng"></div>
								           		</div>
								           		<div class="InfoButtomContent">
								           			<div class="geRenLianXiFS">
								           				<div class="col-xs-4 colorCCC">姓名</div>
								           				<div class="col-xs-8 color000">张三</div>
								           				<div class="col-xs-4 colorCCC">性别</div>
								           				<div class="col-xs-8 color000">男</div>
								           				<div class="col-xs-4 colorCCC">电话</div>
								           				<div class="col-xs-8 color000">15816595929</div>
								           			</div>
								           			<div class="geRenLianXiFS" style="border-bottom:none;">
								           				<div class="col-xs-4 colorCCC dengLuCiShu">上次登录时间</div>
								           				<div class="col-xs-8 color000 dengLuCiShu1">2017-3-15</div>
								           				<div class="col-xs-4 colorCCC dengLuCiShu">登录次数总计</div>
								           				<div class="col-xs-8 color000 dengLuCiShu1">6次</div>
								           			</div>
								           		</div>
								           </div>
								        </li>  -->
								        <li data-toggle="modal" data-target=".revisePassword">
								        	<span class="sheZhiZhongXin"></span>
								            <a href="JavaScript:;">修改密码</a>
								        </li>
								        <li data-toggle="modal" data-target=".withdraw">
								        	<span class="zhuXiaoDengLu"></span>
								            <a href="JavaScript:;">注销登录</a>
								        </li>
								        <li data-toggle="modal" data-target=".dropOut">
								        	<span class="tuiChu"></span>
								            <a href="JavaScript:;">退出</a>
								        </li>
								    </ul>
							</div>
						</div>
					</div>
				</div>
			</div>
				<div class="col-xs-12 PaddIngMargin">
					<div class="col-xs-2 contentLeftB PaddIngMargin" id="contentLeft">
						<div class="row munLeft_LineHeight" >
							<!--<div class="leftHeaName text-left">系统管理</div>-->
							<div id="leftNav">
								
								
							</div>
								
						</div>
					</div>
					<div class="col-xs-10" id="contentRight">
					</div>
					<div class="text-center copyright">Copyright&copy; 2016-2030 BOOMHOPE.COM.ALL Right Reserved 博宏信息技术有限公司 版权所有</div>
				</div>
					
			</div>
<div class="modal fade dropOut" id="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content Mymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="myModalLabel">
											            	信息提示
											            </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true">
											            </span>
				</div>
			</div>
			<div class="modal-body text-center" style="height:140px;padding-top: 60px;">
				<span class=" glyphicon glyphicon-question-sign"></span>
				<span class="fontColor">您确定要退出吗?</span>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary modalBtn" data-dismiss="modal">取消
									            </button>
				<button type="button" class="btn btn-primary modalBtn" id="outPMS">
									              		确定
									            </button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<div class="modal fade  withdraw" id="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content Mymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="myModalLabel">
											            	信息提示
											            </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true">
											            </span>
				</div>
			</div>
			<div class="modal-body text-center" style="height:140px;padding-top: 60px;">
				<span class=" glyphicon glyphicon-question-sign"></span>
				<span class="fontColor">您确定要注销吗?</span>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary modalBtn" data-dismiss="modal">取消
									            </button>
				<button type="button" class="btn btn-primary modalBtn" id="withdraw">
									              		确定
									            </button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade  revisePassword" id="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content Mymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="myModalLabel">修改密码</span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"></span>
				</div>
			</div>
			<div class="modal-body text-center" style="height:140px;padding-top:8px;position: relative;">
				<form class="row" id="addInfor1" >
					<div class="col-xs-12"><span class="textWidth"><span class="item">*</span>原密码:</span><input type="text" class="validate[required]  form-control inputName" id="oldpwd" /></div>
					<div class="col-xs-12 polling"><span class="textWidth"><span class="item">*</span>新密码:</span><input type="password" class="validate[required] form-control inputName" id="macModelw" /></div>
					<div class="col-xs-12"><span class="textWidth"><span class="item">*</span>确认密码:</span><input type="password" class="validate[required] form-control inputName" id="macModelwX" /></div>
					<p class="disaccord disaccordDis">您两次输入的密码不一致</p>
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary modalBtn" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary modalBtn" id="revisePassword">确定</button>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
(function firstLevelMenu(){
	$.ajax({
         url:"<%=basePath%>sys/getLogRolePrivilege",
         cache:false,
         type:"post",
         datatype:'json',
         data:"",
         beforeSend:function(){},
         success:function(data){ 
        	 var da = {id:data.treeList[0].id}
        	 leftMenu(da,0,"<%=basePath%>sys/getLogRolePrivilegeSon");
        	 for(var i=0;i<data.treeList.length;i++){
     			$("#firstLevelMenu").append("<div><p class='userManagementIco'></p>"+data.treeList[i].name+"</div>");
     			if(data.treeList[i].id == 2){
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":"-33px"
         			});
     			}else if (data.treeList[i].id == 3){
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":"-66px"
         			});
     			}else if (data.treeList[i].id == 4){
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":"-99px"
         			});
     			}else if (data.treeList[i].id == 5){
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":"-132px"
         			});
     			}else if (data.treeList[i].id == 6){
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":"-165px"
         			});
     			}else{
     				$("#firstLevelMenu div").eq(i).children("p").css({
         				"background-position-x":i*-33+"px"
         			});
     			}
     			
     		}
     		$("#firstLevelMenu div").eq(0).addClass("activea");
     		/*导航点击事件*/
     		$(".rowsCenter div").click(function(){
     			$(this).parent().find("div").removeClass("activea");
     			$(this).addClass("activea");
     			$("#LiftMenuContent").html("");
     			var nty = data.treeList[$(this).index()].id;
     			var data1 = {id:nty}
     		    leftMenu(data1,$(this).index(),"<%=basePath%>sys/getLogRolePrivilegeSon");
     			clearInterval(inTimer);
     		});
         },
         complete:function(){},
         error:function(){}
     })
}());
function leftMenu(data,index,path){
	$.ajax({
        url:path,
        cache:false,
        type:"post",
        datatype:'json',
        data:data,
        beforeSend:function(){},
        success:function(data){ 
       	for(var i=0;i<data.treeList.length;i++){
    		$("#LiftMenuContent").append("<li ref="+data.treeList[i].menuPath+"><span></span>"+data.treeList[i].name+"</li>");
    		if(index == 0){
    			if(i==0){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"1px",
    				});
    			}else{
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":i*-19+"px",
    				});
    			}
    		}else if(index == 1){
    			$("#LiftMenuContent li").eq(i).find("span").css({
    				"background-position-y":index*-40+"px",
    			});
    		}else if(index == 2){
    			if(i==0){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-60px",
    				});
    			}else{
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-80px",
    				});
    			}
    		}else if(index == 3){
    			if(i>=1 && i<=2){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":-100-i*20+"px",
    				});
    			}else if(i==3){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-158px",
    				});
    			}else{
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-100px",
    				});
    			}
    		}else if(index == 4){
    			if(i==0){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-177px",
    				});
    			}else if(i==1){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-195px",
    				});
    			}else if(i==2){
    				$("#LiftMenuContent li").eq(i).find("span").css({
    					"background-position-y":"-217px",
    				});
    			}
    			
    		}
    	}	 
    	busGoto(data.treeList[0].menuPath,"contentRight");//默认加载页面
    	$("#LiftMenuContent li").eq(0).addClass("liActive");
    	$("#LiftMenuContent li").eq(0).find("span").css({
    		"background-position-x":"-25px",
    	});
    	/*左侧nav事件*/
    	$("#LiftMenuContent li").click(function(){
    		$(this).parent().find("li").removeClass("liActive");
    		$(this).addClass("liActive");
    		$(this).parent().find("span").css({
    			"background-position-x":"0px",
    		});
    		$(this).find("span").css({
    			"background-position-x":"-25px",
    		});
    		clearInterval(inTimer);
    		if($(this).attr("ref") == "undefined"){
    			alert("网络错误");
    		}else{
    			busGoto($(this).attr("ref"),"contentRight");
    		}
    		console.log($(this).attr("ref"));
    	});
        },
        complete:function(){},
        error:function(){}
    })
}
	//个人信息框显示
	$(".geRenInformationBlock").mouseover(function(){
		$(".geRenInformation").show();
	}).mouseout(function(){
		$(".geRenInformation").hide();
	});
	//退出
	$("#outPMS").click(function(){
		window.location.href="login.jsp";
	});
	//注销
	$("#withdraw").click(function(){
		var data = {loginUser:$("#loginInfor").html()}
		$.ajax({
	         url:"<%=basePath%>loginOut",
	         cache:false,
	         type:"post",
	         datatype:'json',
	         data:data,
	         beforeSend:function(){},
	         success:function(data){ 
	        	 if(data.success == true){
           		  $("#addInformation,.dropOut,#addDelete").modal("hide");
           			location.href = "login.jsp";
	        	 }		 
	        	
	         },
	         complete:function(){},
	         error:function(){}
	     })
	});
	$("#macModelw,#macModelwX").click(function(){
		$(".disaccordDis").hide();
	});
	//修改密码
	$("#revisePassword").click(function(){
		var a=GetRequest();
		var index_1=a['pwd'];
		console.info(index_1);
		if($("#macModelw").val() != $("#macModelwX").val()){
			$(".disaccordDis").show();
			return;
		}
		var data = {loginUser:$("#loginInfor").html(),newpwd:$("#macModelwX").val(),pwd:$("#oldpwd").val()}
		$.ajax({
	         url:"<%=basePath%>/modifyPwd",
	         cache:false,
	         type:"post",
	         datatype:'json',
	         data:data,
	         beforeSend:function(){},
	         success:function(data){ 
	        	 if(data.success == true){
           		  $(".Mymodal,.dropOut,#addDelete").modal("hide");
           			publicPopups("ubus","","修改成功");
           			window.location.href="login.jsp";
	        	 }		 
	        	 else{
           		  $(".Mymodal,.dropOut,#addDelete").modal("hide");
           			publicPopups("ubus","","原密码错误，修改失败");
//            			window.location.href="login.jsp";
	        	 }		 
	        	
	         },
	         complete:function(){},
	         error:function(){}
	     })
	});
	//个人信息
	$("#").click(function(){
		
	});
</script>	
</html>
