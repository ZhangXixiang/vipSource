<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row contentHeader">
	<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">姓名: </span><input type="text" class="form-control inputName" id="shebiBBJ" placeholder="姓名">
		</div>
		<div class="col-xs-9 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="chaxun">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addInformation">添加</button>
			<!--<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addEditor">编辑</button>-->
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" id="delectInfor">删除</button>
			<button style="padding: 0px; width: 104px;" type="button" class="btn btn-primary btn_primary" data-toggle="modal" id="userRolePriority">设置用户角色</button>
		</div>
	</div>
</div>

<!--添加弹窗-->
<div class="modal fade" id="addInformation" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 添加用户 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor">
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>登录名:</span><input type="text" class="validate[required]  form-control inputName" id="loginName" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>登录密码:</span><input type="text" class="validate[required] form-control inputName" id="passWord" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>姓名:</span><input type="text" class="validate[required] form-control inputName" id="userName" />
					</div>
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop ClearZhiMoRen" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="addInforMaTYU">确定</button>
			</div>
		</div>
	</div>
</div>
<!--编辑弹窗-->
<div class="modal fade addEditor" id="" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 编辑用户 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor1">
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>登录名:</span><input type="text" class="validate[required]  form-control inputName" id="loginName1" readonly="readonly" />
					</div>
					<div class="col-xs-6 polling" style="display: none;">
						<span class="textWidth"><span class="item">*</span>登录密码:</span><input type="text" class="validate[required] form-control inputName" id="passWord1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>姓名:</span><input type="text" class="validate[required] form-control inputName" id="userName1" />
					</div>
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="bianjiInfor">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!--删除-->
<div class="modal fade" id="addDelete" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 删除用户 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<p class="pupInfor">
					<span class="glyphicon glyphicon-question-sign icoIfor"></span>您确定要删除吗？
				</p>
			</div>
			<div class="modal-footer modalButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="shebeiDelect">确定</button>
			</div>
		</div>
	</div>
</div>
<!--重置密码-->
<div class="modal fade addMun" id="" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 重置密码 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form id="YuanMiMaPass" style="margin-top: -18px; margin-bottom: 40px;">
					<div class="pupInfor" id="munAdd">
						<div class="col-xs-12 polling">
							<span class=""><span class="item">*</span>新密码:</span><input type="text" class="validate[required]  form-control inputName" id="yuanPassword" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer modalButtom">
				<button type="button" class="btn btn-primary BtnTop data_hiding" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="YuanMiMa">确定</button>
			</div>
		</div>
	</div>
</div>
<!--重置密码-->
<div class="modal fade " id="addMun" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal" style="height: 320px; top: 77px;">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 设置用户角色 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center ztree" id="roleQuanXian" style="height: 230px; overflow: auto; padding-left: 114px;"></div>
			<div class="modal-footer modalButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="seveQuanXian">确定</button>
			</div>
		</div>
	</div>
</div>
<div class="col-xs-12 PaddIngMargin">
	<!-- data-side-pagination="server" -->
	<table style="height: auto; max-height: 500px;" data-height="500" data-side-pagination="server" data-response-handler="filterInfor" id="TableList" data-method="post" data-query-params="offetFunction" data-unique-id="loginName" data-striped="true" data-url="<%=basePath%>sys/queryUsers" data-pagination="true" data-click-to-select="true" data-single-select="false">
		<thead>
			<tr>
				<th data-field="swact" data-checkbox="true" data-align="center"></th>
				<th data-field="userName" data-align="center">姓名</th>
				<th data-field="token" data-align="center">令牌</th>
				<th data-field="loginName" data-align="center">登录名</th>
				<th data-field="loginStatus" data-align="center" data-formatter="usernameFormatter1">状态</th>
				<th data-field="creater" data-align="center">创建者</th>
				<th data-field="createDate" data-align="center">创建时间</th>
				<th data-field="caozuo" data-align="center" data-formatter="usernameFormatter">操作</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript" src="../js/common/select.js"></script>
<script>
 var userId = "";
 var loginNameBtn = "";
 $('#TableList').bootstrapTable({
	onClickRow:function(row, $element){
		loginNameBtn = row.loginName;
	}
 });
 function usernameFormatter(value,row,index){
	 var start = "";
	 if(row.loginStatus == "1"){
		 start = "禁用";
	 }else if(row.loginStatus == "0"){
		 start = "启用"; 
	 }
 	return "<a  onclick='start1(\""+row.loginName+"\")'>"+start+"</a><a class='aMarGIn' onclick='start(\""+row.loginName+"\")' data-toggle='modal' data-target='.addEditor'>编辑</a><a class='aMarGIn' onclick='start2(\""+row.loginName+"\")' data-toggle='modal'  data-target='.addMun'>重置密码</a>";
 }
 function start(d){//获取当前选中行数据
 	var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
 	 $("#loginName1").val(row.loginName);
 	 $("#passWord1").val(row.passWord);
 	 $("#userName1").val(row.userName);
 	 userId = row.loginName; 
 }
 function usernameFormatter1(value,row,index){
	 var start = "";
	 if(row.loginStatus == "0"){
		 start = "禁用";
	 }else if(row.loginStatus == "1"){
		 start = "启用";
	 }
	 return start;
 }
 function start1(d){
	 var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 var data = {loginName:row.loginName,loginStatus:row.loginStatus};
	 var vcA = "";
	 if(row.loginStatus == "1"){
		 vcA = "禁用";
	 }else if(row.loginStatus == "0"){
		 vcA = "启用";
	 }
	 publicPopups("idsCvIoc","","您确定要"+vcA+"吗?");
	 $("#idsCvIoc").click(function(){
		 addInfor(data,4,"<%=basePath%>sys/updateUserStatus");
	 });
 }
 function start2(d){
	 var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 userId = row.loginName; 
 }
/*  onClickRow */
 function offetFunction(params){
	 var data = {
				pageSize:this.pageSize,
				pageNumber:this.pageNumber,
				searchText:"",
				sortName:"",
				sortOrder:"" 
		}
		 console.log(params);
	 	return data;
 }
 //添加
 $("#addInforMaTYU").on("click",function(){
	$("#addInfor").submit();
 });
 function filterInfor(res){
	 return res;
 }
 //修改密码
 $("#YuanMiMa").click(function(){
	 $("#YuanMiMaPass").submit();
 });
 //编辑信息
 $("#bianjiInfor").on("click",function(){
	 $("#addInfor1").submit();
 });
 //删除设备信息
$("#shebeiDelect").click(function(){
	 var option = $('#TableList').bootstrapTable("getAllSelections");
		var idList = [];
		$.each(option,function(i){
			idList.push(option[i].loginName);
		});
		var data = {loginName:""+idList};
		console.log(data);
	  addInfor(data,3,"<%=basePath%>sys/deleteUser");
		
		
});
 //保存用户角色
 $("#seveQuanXian").click(function(){
	 var  nodes = tree.getCheckedNodes(true);
	 var  per = "";
	 var  checked = "";
	 var  name = "";
	 var  parentId = "";
	  for(var i=0;i<nodes.length;i++){
      	if(i == 0){
      		per +=nodes[i].id;
      		checked += nodes[i].checked;
      		name += nodes[i].name;
      		parentId += nodes[i].parentId;
      	}else if(i != nodes.length){
      		per +=","+nodes[i].id;
      		checked += ","+nodes[i].checked;
      		name += ","+nodes[i].name;
      		parentId += ","+nodes[i].parentId;
      	}else{
      		per +=nodes[i].id;
      	}
      }
	  var data = {id:per,checked:checked,name:name,parentId:parentId,loginName:loginNameBtn};
	  console.log(data);
	  addInfor(data,6,"<%=basePath%>sys/addUserRole");
 });
//添加/编辑设备公共方法封装
 function addInfor(data,num,path){
	 $.ajax({
         url:path,
         cache:false,
         type:"post",
         datatype:'json',
         data:data,
         beforeSend:function(){},
         success:function(data){ 
        	 console.log(data);
        	 if(data.success == true){
        		 $("#addInformation,.addEditor,#addDelete,.addMun,#addMun").modal("hide");
        		 Clear();
        		if(num ==1){
        			publicPopups("ubus","","添加成功");
        		}else if(num == 2){
        			publicPopups("ubus","","编辑成功");
        		}else if(num == 3){
        			publicPopups("ubus","","删除成功");
        		}else if(num == 4){
        			publicPopups("ubus","","修改成功");
        		}else if(num == 5){
        			publicPopups("ubus","","修改成功");
        		}else if(num == 6){
        			publicPopups("ubus","","保存成功");
        		}
        		 $('#TableList').bootstrapTable("refresh",{silent:true});//刷新表格
        	 }else{
        		 $("#addInformation,.addEditor,#addDelete,.addMun,#addMun").modal("hide");
        		 $('#TableList').bootstrapTable("refresh",{silent:true});//刷新表格
        		 publicPopups("ubus","",data.errmsg);
        	 }
         },
         complete:function(){},
         error:function(){}
     })
 }
 //权限按钮显示
 $("#userRolePriority").click(function(){
	 $(this).attr("data-target","");
	 var option = $('#TableList').bootstrapTable("getAllSelections");
	 if(option == ""){
		 publicPopups("ubus","","请选择您要设置的数据");
	 }else if(option.length > 1){
		 publicPopups("ubus","","只能选择一条要设置的数据");
	 }else{
		 $(this).attr("data-target","#addMun");

		 $.ajax({
		        url:"<%=basePath%>sys/GetUserRole",
		        cache:false,
		        type:"post",
		        datatype:'json',
		        data: {loginName:loginNameBtn},
		        beforeSend:function(){},
		        success:function(data){
		        	console.log(data);
		        	var zTree;
		        	var demoIframe;
		        	var setting = {
		        		check: {//点击事件
		        				enable: true
		        		},
		        		view: {
		        			dblClickExpand: true,
		        			showLine: true,
		        			selectedMulti: true
		        		},
		        		data: {
		        			simpleData: {
		        				enable:true,
		        				idKey: "id",
		        				parentIdKey: "parentId",
		        				rootPId: 0
		        			}
		        		},
		        		callback: {
		        			beforeClick: function(treeId, treeNode) {
		        				var zTree = $.fn.zTree.getZTreeObj("tree");
		        			}
		        		}
		        	}; 
		        	tree = $.fn.zTree.init($("#roleQuanXian"), setting, data.treeList);
		        }
		}); 
	 }
 });
//清空值默认值
function Clear(){
 $("#loginName,#passWord,#userName,#yuanPassword").val("");
 $('form').validationEngine('hideAll');
}
$(".ClearZhiMoRen,.data_hiding,#YuanMiMa").click(function(){
  Clear();
});
//按条件查询
 $("#chaxun").click(function(){
	  var data = {userName:$("#shebiBBJ").val()};
	 $('#TableList').bootstrapTable("refresh",{silent:true,query:data});
 });
 //删除弹窗
 $("#delectInfor").click(function(){
		 $(this).attr("data-target","");
		 var option = $('#TableList').bootstrapTable("getAllSelections");
		 if(option == ""){
			 publicPopups("ubus","","请选择想您要删除的数据");
		 }else{
			 $(this).attr("data-target","#addDelete");
		 }
	 });
//校验添加
 (function valid(){
		$('#addInfor').validationEngine('attach', {
			relative: true,
			overflownDIV:"#divOverflown",
			promptPosition:"bottomLeft",
			maxErrorsPerField:1,
			onValidationComplete:function(form,status){
				if(status){
					 var data = {loginName:$("#loginName").val(),passWord:$("#passWord").val(),userName:$("#userName").val()};
					 addInfor(data,1,"<%=basePath%>sys/addUser");
				}
			}
		});
		$('#addInfor1').validationEngine('attach', {
			relative: true,
			overflownDIV:"#divOverflown",
			promptPosition:"bottomLeft",
			maxErrorsPerField:1,
			onValidationComplete:function(form,status){
				if(status){
					 var data = {loginName:$("#loginName1").val(),passWord:$("#passWord1").val(),userName:$("#userName1").val()};
					 console.log(data);
					 addInfor(data,2,"<%=basePath%>sys/updateUser");
				}
			}
		});
		$('#YuanMiMaPass').validationEngine('attach', {
			relative: true,
			overflownDIV:"#divOverflown",
			promptPosition:"bottomLeft",
			maxErrorsPerField:1,
			onValidationComplete:function(form,status){
				if(status){
					 var data = {loginName:userId,passWord:$("#yuanPassword").val()};
					 console.log(data);
					 addInfor(data,5,"<%=basePath%>sys/updateUserPass");
				}
			}
		});
	}()); 
</script>

