<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row contentHeader">
	<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">角色名称: </span><input type="text" class="form-control inputName" id="roleNameADDA" placeholder="角色名称">
		</div>
		<div class="col-xs-9 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="chaxun">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addInformation">添加</button>
			<!--<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addEditor">编辑</button>-->
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addDelete" id="delectInfor">删除</button>
			<!--<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addMun">菜单配置</button>-->
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
						<span class="textWidth"><span class="item">*</span>角色名称:</span><input type="text" class="validate[required]  form-control inputName" id="roleName" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>角色描述:</span><input type="text" class="validate[required] form-control inputName" id="roleDesc" />
					</div>
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop  ClearZhiMoRen" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="addInforMaTYU">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
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
						<span class="textWidth"><span class="item">*</span>角色名称:</span><input type="text" class="validate[required]  form-control inputName" id="roleName1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>角色描述:</span><input type="text" class="validate[required] form-control inputName" id="roleDesc1" />
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
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!--设置权限-->
<div class="modal fade addMun" id="" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal" style="height: 320px; top: 77px;">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 设置权限 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">

				<div class="pupInfor">
					<div class="modal-body text-center ztree" id="rolePermission" style="width: 290px; height: 170px; overflow: auto;"></div>
				</div>
			</div>
			<div class="modal-footer modalButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="saveRolePermission">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<div class="col-xs-12 PaddIngMargin">
	<table id="TableList" data-side-pagination="server" style="height: auto; max-height: 500px;" data-height="500" data-method="post" data-response-handler="filterInfor" data-query-params="offetFunction" data-unique-id="roleCode" data-striped="true" data-url="<%=basePath%>sys/queryRoles" data-pagination="true" data-click-to-select="true" data-single-select="false">
		<thead>
			<tr>
				<th data-field="swact" data-checkbox="true" data-align="center"></th>
				<th data-field="roleName" data-align="center">角色名称</th>
				<th data-field="roleDesc" data-align="center">角色描述</th>
				<th data-field="caozuo" data-align="center" data-formatter="usernameFormatter">操作</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript" src="../js/common/select.js"></script>
<script>
var userId = "";
$('#TableList').bootstrapTable();
function usernameFormatter(value,row,index){
	return '<a class="aMarGIn" onclick="start(\''+row.roleCode+'\')" data-toggle="modal" data-target=".addEditor">编辑</a><a class="aMarGIn" onclick="start1(\''+row.roleCode+'\')" data-toggle="modal" data-target=".addMun">设置权限</a>';
}
function start(d){//获取当前选中行数据
	  console.log(d);
	var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 $("#roleName1").val(row.roleName);
	 $("#roleDesc1").val(row.roleDesc);
	 userId = row.roleCode;  
}
//获取权限所有信息
function start1(d){
	userId = d;
	 $.ajax({
	        url:"<%=basePath%>sys/getRolePrivilege",
	        cache:false,
	        type:"post",
	        datatype:'json',
	        data: {roleCode:d},
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
	        	tree = $.fn.zTree.init($("#rolePermission"), setting, data.treeList);
	        }
	});
}
//保存用户权限
$("#saveRolePermission").click(function(){
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
	  var data = {id:per,checked:checked,name:name,parentId:parentId,roleCode:userId};
	  console.log(data);
	  addInfor(data,6,"<%=basePath%>sys/addRolePrivilege");
});
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
//编辑信息
$("#bianjiInfor").on("click",function(){
	 $("#addInfor1").submit();
});
//删除信息
$("#shebeiDelect").click(function(){
	 var option = $('#TableList').bootstrapTable("getAllSelections");
		var idList = [];
		$.each(option,function(i){
			idList.push(option[i].roleCode);
		});
		var data = {roleCode:""+idList};
		console.log(data);
	  addInfor(data,3,"<%=basePath%>mac/deleteSysRole");
		
		
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
       		 $("#addInformation,.addEditor,#addDelete,.addMun").modal("hide");
       		 Clear();
       		if(num ==1){
       			publicPopups("ubus","","添加成功");
       		}else if(num == 2){
       			publicPopups("ubus","","编辑成功");
       		}else if(num == 3){
       			publicPopups("ubus","","删除成功");
       		}else if(num == 6){
       			publicPopups("ubus","","保存成功");
       		}
       		
       	 }else{
       			
       		 $("#addInformation,.addEditor,#addDelete").modal("hide");
    		 $('#TableList').bootstrapTable("refresh",{silent:true});//刷新表格
    		 publicPopups("ubus","",data.errmsg);
    	 }
       	 $('#TableList').bootstrapTable("refresh",{silent:true});//刷新表格
        },
        complete:function(){},
        error:function(){}
    })
}
//清空值默认值
function Clear(){
$("#roleName,#roleDesc").val("");
 $('form').validationEngine('hideAll');
}
$(".ClearZhiMoRen,.data_hiding").click(function(){
 Clear();
});
//按条件查询
$("#chaxun").click(function(){
	  var data = {roleName:$("#roleNameADDA").val()};
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
					 var data = {roleName:$("#roleName").val(),roleDesc:$("#roleDesc").val(),};
					 addInfor(data,1,"<%=basePath%>sys/addRole");
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
					
					var data = {roleName:$("#roleName1").val(),roleDesc:$("#roleDesc1").val(),roleCode:userId};
					 console.log(data);
					 addInfor(data,2,"<%=basePath%>mac/updateRole");
				}
			}
		});
	}()); 
</script>
