<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="row">
 	<div class="col-xs-12" style="padding-left:0;padding-right:0;">
		<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-2 PaddIngMargin">
			<span class="disBlock">证件号: </span><input type="text" class="form-control inputName" id="creditIdQry" placeholder="身份证号" style="width:130px;">
		</div>
		<div class="col-xs-2 PaddIngMargin">
			<span class="disBlock">人员名称: </span><input type="text" class="form-control inputName" id="userNameQry" placeholder="人员名称"  style="width:100px;">
		</div><!-- 
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">所属营业厅: </span><input type="text" class="form-control inputName" id="unitNameQry" placeholder="所属营业厅">
		</div> -->
			<div class="col-xs-3 PaddIngMargin">
				<span class="textWidth" style="width: auto; max-width: 85px;"><span class="item"></span>营业厅名称:</span> <input type="text" class="validate[required] form-control inputName" id="unitNameSearch" readonly="readonly" style="width: auto; max-width: 106px;" /> <input type="hidden" id="unitCodeSearch"> <span class="btn btn-primary selectParentUnit">选择</span>
			</div>
			<div class="col-xs-4 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="query">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="reset">重置</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addBussinessinfo">添加</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addDelete" id="delectInfor">删除</button>
		</div>
	</div>
</div>
		<!-- 表格数据  -->
		<div class="col-xs-12 PaddIngMargin">
			<table id="TableList" data-side-pagination="server" data-method="post" data-query-params="offetFunctionChoise" data-unique-id="userCode" data-striped="true" data-url="<%=basePath%>bussinessUser/qryBussinessUserList" style="height:auto; max-height:420px;" data-pagination="true" data-height="500" data-click-to-select="true" data-single-select="false">
				<thead>
					<tr>
					<th  data-width="5%" data-field="swact" data-checkbox="true" data-align="center"></th>
					<th data-field="userCode" data-align="center">人员代码</th>
					<th data-field="userName" data-align="center">人员名称</th>
					<th data-field="loginName" data-align="center">用户名</th>
					<th data-field="creditId" data-align="center">身份证号</th>
			 		<th data-field="phone" data-align="center">电话号码</th>
			 		<th data-field="telephone" data-align="center">手机号码</th>
			 		<th data-field="unitName" data-align="center">所属营业厅</th>
			 		<th data-field="creater" data-align="center" >创建者</th>
			 		<th data-field="createDate" data-align="center">创建日期</th>
			 		<!-- <th data-field="status" data-align="center" >状态</th> -->
			 		<th data-field="status" data-align="center" data-formatter="enableDisable">状态</th>
			 		<th data-field="caozuo" data-align="center" data-formatter="enableDisableOperate">操作</th>
				</tr>
				</thead>
		    </table>
		</div>
	</div>	

	<!--添加弹窗-->
	<div class="modal fade" id="addBussinessinfo" tabindex="-1" role="dialog" aria-labelledby="addBussinessUser" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content addBussinessUser" style="top:60px;">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addBussinessUser"> 添加员工信息 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor" >
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>人员代码:</span>
						<input type="text" class="validate[required]  form-control inputName"  id="userCodeAdd"/>
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>人员名称:</span>
						<input type="text" class="validate[required] form-control inputName"  id="userNameAdd"  />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>用户名:</span>
						<input type="text" class="validate[required] form-control inputName"  id="loginNameAdd" />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>身份证号:</span>
						<input type="text" class="validate[required] form-control inputName"  id="creditIdAdd" />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>电话号码</span>
						<input type="text" class="validate[required] form-control inputName"  id="phoneAdd"/>
					</div>
					
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>手机号码:</span>
						<input type="text" class="validate[required] form-control inputName"  id="telephoneAdd" />
					</div>
					<!-- <div class="col-xs-6 polling text-right">
						<span class="textWidth"><span class="item"></span>营业厅编号:</span>
						<input type="text" class="validate[required] form-control inputName"  id="unitCodeAdd" />
					</div> -->
					<!-- 选择所属营业厅 readonly="readonly"-->
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>营业厅名称:</span>
						<input type="text" class="validate[required] form-control inputName" id="unitName" readonly="readonly" style="width:auto;max-width:106px;"/>
						<input type="hidden" id="unitCode">
						<span class="btn btn-primary selectParentUnit">选择</span>
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
<div class="modal fade addEditor" id="editBussiness" tabindex="-1" role="dialog" aria-labelledby="editBussiness" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content addBussinessUser" style="top:60px;">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addBussinessUser"> 编辑员工信息 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor1">
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>人员代码:</span> 
						<input type="text" class="validate[required] form-control inputName" id="userCodeEdit" readonly="readonly" />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>人员名称:</span> 
						<input type="text" class="validate[required] form-control inputName" id="userNameEdit" />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>用户名:</span> 
						<input type="text" class="validate[required] form-control inputName" id="loginNameEdit"  />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>身份证号:</span> 
						<input type="text" class="validate[required] form-control inputName"  id="creditIdEdit"/>
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>电话号码:</span>
						<input type="text" class="validate[required] form-control inputName" id="phoneEdit" />
					</div>
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>手机号:</span>
						<input type="text" class="validate[required] form-control inputName" id="telephoneEdit" />
					</div>
					<!-- 选择所属营业厅 readonly="readonly"-->
					<div class="col-xs-6 polling text-right">
						<span class="textWidth" style="width:auto;max-width:85px;"><span class="item">*</span>营业厅名称:</span>
						<input type="text" class="validate[required] form-control inputName" id="unitNameEdit" readonly="readonly" style="width:auto;max-width:106px;"/>
						<input type="hidden" id="unitCodeEdit">
						<span class="btn btn-primary selectParentUnit">选择</span>
					</div> 
					</form>
					</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop ClearZhiMoRen" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop"  id="bianjiInfor">确定</button>
			</div>
		</div>
	</div>
</div>
<!--删除-->
<div class="modal fade" id="addDelete" tabindex="-1" role="dialog" aria-labelledby="deleteBussinessUser" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content delecMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="deleteBussinessUser"> 删除员工信息 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<p class="pupInfor"><span class="glyphicon glyphicon-question-sign icoIfor"></span>您确定要删除吗？</p>
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
<!-- 选择上级机构 -->
<div class="modal fade" id="selectParentUnitBox" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content addMymodal" style='top:60px;'>
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left">选择营业厅</span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"></span>
				</div>
			</div>
			<div class="modal-body text-center">
				<div class="row">
					<div class="col-xs-8 polling">
						<span class="textWidth"><span class="item">*</span>营业厅名称:</span>
						<input type="text" class="validate[required] form-control inputName" id="selectUnitNameInput"/>
						<button class="btn btn-primary" id="parentUnitSearch">查询</button>
					</div>
				</div>
				<table id="parentUnitList" data-side-pagination="server" data-method="post" data-query-params="offetFunction" data-unique-id="unitCode" data-striped="true" data-url="<%=basePath%>/findBusinessManagentList" data-pagination="true" data-height="220" data-click-to-select="true" data-single-select="true">
				 	<thead>
						<tr>
							<th data-width="5%" data-field="swact" data-checkbox="true" data-align="center"></th>
							<th data-field="unitCode" data-align="center">营业厅编号</th>
							<th data-field="unitName" data-align="center">营业厅名称</th>
					 		<th data-field="status" data-align="center" data-formatter="usernameFormatter1">营业厅状态</th>
						</tr>
					</thead>
		        </table>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop" id="addParentUnit"> 确定 </button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//选择上级机构按钮事件(添加、编辑界面公用的)
$(".selectParentUnit").on("click",function(event){
	$("#selectParentUnitBox").modal("show");
	$("#parentUnitList").bootstrapTable("refresh",{silent:true});
	//$("#selectParentUnitBox .bootstrap-table .fixed-table-container").css({"height":"160px;"})
})
// 选择上级机构弹框查询按钮事件
	 $("#parentUnitSearch").on("click",function(event){
		var data = {unitName:$("#selectUnitNameInput").val()};
		$('#parentUnitList').bootstrapTable("refresh",{silent:true,query:data});
	 })
	 // 选择上级机构确定按钮事件
	 $("#addParentUnit").on("click",function(event){
		 var result = $('#parentUnitList').bootstrapTable("getAllSelections");
		 if(result.length){
			 $("#unitCode,#unitCodeEdit,#unitCodeSearch").val(result[0].unitCode);
			 $("#unitName,#unitNameEdit,#unitNameSearch").val(result[0].unitName);
			 $("#selectParentUnitBox").modal("hide");
		 }
	 })
</script>
<script>
	var userId = "";
	 $('#TableList').bootstrapTable();
	 $('#parentUnitList').bootstrapTable(); // 初始化上级机构表格
	 //添加下拉框默认值
	 selectInformation(2,"<%=basePath%>findBusinessManagentList");//所属机构  
	 function selectInformation(num,path){
		 $.ajax({
             url:path,
             cache:false,
             type:"post",
             datatype:'json',
             data:"",
             beforeSend:function(){},
             success:function(data){
            	 if(num == 1){
            		 //console.log(data);
            		 $("#macModel").html(data[0].macModel);
                	 for(var i=0;i<data.length;i++){
                		 $(".selectRquipment").append("<li value="+data[i].macBrand+">"+data[i].macModel+"</li>")
                	 }
            	 }else{
            		 //console.log(data);
            		 $("#unitName").html(data[0]);
                	 for(var i=0;i<data.length;i++){
                		 $(".selectRquipmentB").append("<li>"+data[i]+"</li>")
                	 }
            	 }
            	 $(".select-content li").click(function(){
            	        $(this).parent().siblings(".select-header").removeClass("select-arrow");
            	        $(this).parent().siblings(".select-header").html($(this).html()).end().slideUp("fast");
            	        $(this).parent().siblings(".select-header").attr("value",$(this).attr("value"));
            	        /* $("#macBrand").val($(this).attr("value"));
            	        $("#macBrand2").val($(this).attr("value")); */
            	 });
            	 $(".select_UlLi ul li").click(function(){//添加、编辑联动
         	        $("#macBrand").val($(this).attr("value"));
         	        $("#macBrand2").val($(this).attr("value"));
         	 	 }); 
             },
             complete:function(){},
             error:function(){}
         })
	 };
	 $("#reset").click(function(){
		 	ClearQry();
		 });
	//清空值默认值
	 function ClearQry(){
 	 	$("#creditIdQry,#userNameQry,#unitCodeSearch,#unitNameSearch").val("");
	 }
	$("#choiseBtn").click(function(){
		$("#TableListChoise").bootstrapTable(tableListChoiseInit());
	});
	function tableListChoiseInit(){ // 修改选择营业厅表格
		return {
			onLoadSuccess:function(data){
				$("#choise .fixed-table-container").css({'border-bottom':'none'});
			}
		}
	}
	//  选择营业厅确定按钮事件
	$('#choiseButton').on('click',function(event){
		var result = $("#TableListChoise").bootstrapTable('getSelections');
		if(result.length){
			$("#unitCodeAdd").val(result[0].unitCode);
			$("#unitNameAdd").val(result[0].unitName);
			$("#unitCodeEdit").val(result[0].unitCode);
// 			$("#unitCodeEdit").val(result[0].unitName);
			$('#choise').modal('hide');
		}else{
			publicPopups("ubus","","请选择一条记录!");
		}
	})
	 function enableDisable(value,row,index){
		 var start = "";
		 if(row.status == "0"){
			 start = "禁用";
		 }else if(row.status == "1"){
			 start = "启用";
		 }
		 return start;
	 }
	 function enableDisableOperate(value,row,index){
		 var start = "";
		 if(row.status == "1"){
			 start = "禁用";
		 }else if(row.status == "0"){
			 start = "启用";
		 }
	 	return "<a  onclick='start1(\""+row.userCode+"\")'>"+start+"</a><a class='aMarGIn' onclick='start(\""+row.userCode+"\")' data-toggle='modal' data-target='.addEditor'>编辑</a><a class='aMarGIn'";
	 }
	 function start1(d){
		 var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
		 var data = {userCode:row.userCode,status:row.status};
		 var vcA = "";
		 if(row.status == "1"){
			 vcA = "禁用";
		 }else if(row.status == "0"){
			 vcA = "启用";
		 }
		 publicPopups("idsCvIoc","","您确定要"+vcA+"吗?");
		 $("#idsCvIoc").click(function(){
			 addInfor(data,4,"<%=basePath%>bussinessUser/enableBanBussinessUser");
		 });
	 }
	 function start(d){//获取当前选中行数据，回显操作；
	 	var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 	 $("#userCodeEdit").val(row.userCode);
	 	 $("#userNameEdit").val(row.userName);
	 	 $("#loginNameEdit").val(row.loginName);
	 	 $("#creditIdEdit").val(row.creditId);
	 	 $("#phoneEdit").val(row.phone);
		 //$("#unitName2").html(row.unitName);
	 	 $("#telephoneEdit").val(row.telephone);
	 	 $("#unitCodeEdit").val(row.unitCode);
	 	 $("#unitNameEdit").val(row.unitName);
// 	 	$("#unitCodeEdit").val(row.unitName);
	 	userId = d;
	 }
	<%--  function start1(d,satusType){ 
		var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
		var macStauts = row.macStauts;
		var data = {userCode:row.userCode,macStauts:satusType};
		 addInfor(data,5,"<%=basePath%>bussinessUser/editBussinessUser")
	 } --%>
	 function offetFunction(params){
		 var data = {
					pageSize:this.pageSize,
					pageNumber:this.pageNumber,
					status:$("#status").attr("value"),
					searchText:"",
					sortName:"",
					sortOrder:"" 
			}
			 //console.log(params);
		 	return data;
	 }
	 function offetFunctionChoise(params){
		 var data = {
					pageSize:this.pageSize,
					pageNumber:this.pageNumber,
					/* status:$("#status").attr("value"), */
					searchText:"",
					sortName:"",
					sortOrder:"" 
			}
			 //console.log(params);
		 	return data;
	 }
	 //添加
	 $("#addInforMaTYU").on("click",function(){
		$("#addInfor").submit();
	 });
	// 选择上级机构按钮事件
	  /* $(".selectParentUnit").on("click",function(event){
		$("#selectParentUnitBox").modal("show");
		$("#parentUnitList").bootstrapTable("refresh",{silent:true});
	 }) */ 
	// 选择上级机构弹框查询按钮事件
	  /* $("#parentUnitSearch").on("click",function(event){
		var data = {unitName:$("#selectUnitNameInput").val()};
		$('#parentUnitList').bootstrapTable("refresh",{silent:true,query:data});
	 })  */ 
	 // 选择上级机构确定按钮事件
	 /*  $("#addParentUnit").on("click",function(event){
		 var result = $('#parentUnitList').bootstrapTable("getAllSelections");
		 if(result.length){
			 $("#unitCode,#unitCode2").val(result[0].unitCode);
			 $("#unitName,#unitName2").val(result[0].unitName);
			 $("#selectParentUnitBox").modal("hide");
			 $("#selectUnitNameInput").val("");
		 }
	 })  */
	 // 清除查询上级机构条件
	 /*  $("#selectParentUnitBox [data-dismiss='modal']").on("click",function(event){
		 $("#selectUnitNameInput").val("");
	 }) */ 
	 // 所有取消按钮清空表单数据
	 $(".addEditor [data-dismiss='modal'],#addBussinessinfo [data-dismiss='modal']").on("click",function(event){
		 $(".addEditor input,.addEditor select,.addEditor textarea").val("");
		 $("#addBussinessinfo input,#addBussinessinfo select,#addBussinessinfo textarea").val("");
	 })
	 //编辑信息
	 $("#bianjiInfor").on("click",function(){
		 $("#addInfor1").submit();
	 });
	 //删除设备信息
	 function shebeiDelect(){
		 var option = $('#TableList').bootstrapTable("getAllSelections");
			var idList = [];
			$.each(option,function(i){
				idList.push(option[i].id);
			});
			var data = {macId:idList};
			addInfor(data,3,"<%=basePath%>deploy/deleteMac")
	 }
	 
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
            	 //console.log(data);
            	 if(data.success == true){
            		 $("#addBussinessinfo,#editBussiness,#addDelete").modal("hide");
            		 Clear();
            		 if(num ==1){
            			publicPopups("ubus","","添加成功");
            		}else if(num == 2){
            			publicPopups("ubus","","编辑成功");
            		}else if(num == 3){
            			publicPopups("ubus","","删除成功");
            		}else if(num == 4){
            			publicPopups("ubus","","修改状态成功");
            		}else if(num == 5){
            			publicPopups("ubus","","修改成功");
            		}
            	 }else{
            		 $("#addBussinessinfo,#editBussiness,#addDelete").modal("hide");
            		 publicPopups("ubus","",data.errmsg);
            	 }
            	 $('#TableList').bootstrapTable("refresh",{silent:true});//刷新表格
             },
             complete:function(){
            	 $('#addInfor input').val('');
            	 $('#addInfor1 input').val('');
             },
             error:function(){}
         })
	 }
	//按条件查询
	 $("#query").click(function(){
		  var data = {
				  	creditId:$("#creditIdQry").val(),
				  	userName:$("#userNameQry").val(),
					unitCode:$("#unitCodeSearch").val(),
		 };
		 $('#TableList').bootstrapTable("refresh",{silent:true,query:data});
	 });
// 		删除
	  $("#delectInfor").click(function(){
		 $(this).attr("data-target","");
		 var option = $('#TableList').bootstrapTable("getAllSelections");
		 if(option == ""){
			 publicPopups("ubus","","请选择想您要删除的数据");
		 }else{
			 $(this).attr("data-target","#addDelete");
		 }
	 });  
	 
	 //删除人员信息
		$("#shebeiDelect").click(function(){
			 var option = $('#TableList').bootstrapTable("getAllSelections");
				var idList = [];
				$.each(option,function(i){
					idList.push(option[i].userCode);
				});
				var data = {userCode:""+idList};
				//console.log(data);
				addInfor(data,3,"<%=basePath%>/bussinessUser/deleteBussinessUser");
		});
		//清空值默认值
		 function Clear(){
			 $("#userCode,#macBrand,#factoryCode,#ipAddress,#port,#userName,#password","#unitCode","#unitName").val("");
			  $('form').validationEngine('hideAll');
		 }
		 $(".ClearZhiMoRen,.data_hiding").click(function(){
			  Clear();
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
							 var data = {
									 userCode:$("#userCodeAdd").val(),
									 userName:$("#userNameAdd").val(),
									 loginName:$("#loginNameAdd").val(),
									 creditId:$("#creditIdAdd").val(),
									 phone:$("#phoneAdd").val(),
									 telephone:$("#telephoneAdd").val(),
									 unitName:$("#unitNameAdd").val(),
									 
									 unitCode:$("#unitCode").val()
							};
							addInfor(data,1,"<%=basePath%>bussinessUser/insertSelective");
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
							 var data = {
									 userCode:$("#userCodeEdit").val(),
									 userName:$("#userNameEdit").val(),
									 loginName:$("#loginNameEdit").val(),
									 creditId:$("#creditIdEdit").val(),
									 phone:$("#phoneEdit").val(),
									 telephone:$("#telephoneEdit").val(),
									 unitCode:$("#unitCodeEdit").val()
									 /* unitName:$("#unitCodeEdit").val() */
									 
							};
							addInfor(data,2,"<%=basePath%>bussinessUser/editBussinessUser");
						}
					}
				});
			}()); 
</script>
<script type="text/javascript" src="../js/common/select.js" ></script>
