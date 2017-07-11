<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row contentHeader">
	<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">营业厅代码: </span><input type="text" class="form-control inputName" id="unitCode" placeholder="营业厅代码">
		</div>
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">营业厅名称: </span><input type="text" class="form-control inputName" id="unitName" placeholder="营业厅名称">
		</div>
		<div class="col-xs-6 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="chaxun">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addInformation">添加</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" id="delectInfor">删除</button>
			<div style="display:inline-block;">
				<form method="post" id="form2">
					<input type="button" id="exportExcel" name="exportExcel" value="Excel模板" class="btn btn-primary btn_primary" onclick="checkDate(event);"/>
				</form>
			</div>
			<button type="button" class="btn btn-primary btn_primary"  data-toggle="modal"  data-target='#upLoadFile'  id="upLoadFileBtn">上传文件</button>	
		</div>
	</div>
</div>

<!--上传文件-->
<div class="modal fade" id="upLoadFile" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog" style='top: calc(50% - 284px);'>
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 上传文件 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" > </span>
				</div>
			</div>
			<div class="modal-body text-left">
				<form class="form-inline clearfix " method="POST" enctype="multipart/form-data" id="form1" action="<%=basePath%>/business/importExcel">  
			              <label class="fl">上传文件: </label>  
			              <input type="text" class="form-control" id="upfileList" name="upfile" placeholder='选择文件点击此处'  readonly='readonly'/>    
			              <input type="file" class="form-control" id="upfile" name="upfile"  style='display:none;'>     
				</form> 
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop ClearZhiMoRen" data-dismiss="modal"  id='upLoadFileBtnCancel'>取消</button>
				<button type="button" class="btn btn-primary BtnTop " id="upLoadFileBtnSubmit">上传</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!--添加营业厅-->
<div class="modal fade" id="addInformation" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 添加 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-left">
				<form class="row" id="addInfor">
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>营业厅代码:</span><input type="text" class="validate[required]  form-control inputName" id="unitCode1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>营业厅名称:</span><input type="text" class="validate[required]  form-control inputName" id="unitName1" />
					</div>
					<!-- <div class="col-xs-6 polling"><span class="textWidth"><span class="item">*</span>性别:</span><input type="text" class="validate[required] form-control inputName" id="macModel" /></div> -->
					<div class="col-xs-6 polling">
						<span class="textWidth">联系人:</span><input type="text" class="form-control inputName" id="manager1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">手机号码:</span><input type="text" class="form-control inputName" id="phone1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">电话号码:</span><input type="text" class="form-control inputName" id="unitTel1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">地址:</span><input type="text" class="form-control inputName" id="address1" />
					</div>
					<!--  <div class="col-xs-12 polling"><span class="col-xs-2 PaddIngMargin">证件号:</span><p class="col-xs-10 PaddIngMargin"><textarea type="text" id="remark" class="form-control PaddIngMargin" style="margin-left:-9px;width:506px;height:72px;resize:none;"></textarea></p></div>-->
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop ClearZhiMoRen" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop " id="addInforMaTYU">保存</button>
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
					<span class="modal-title text-left" id="addMyModal"> 编辑 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor1">
				<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>营业厅代码:</span><input type="text" readonly="readonly" class="validate[required]  form-control inputName" id="unitCode2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>营业厅名称:</span><input type="text" class="validate[required]  form-control inputName" id="unitName2" />
					</div>
					<!-- <div class="col-xs-6 polling"><span class="textWidth"><span class="item">*</span>性别:</span><input type="text" class="validate[required] form-control inputName" id="macModel" /></div> -->
					<div class="col-xs-6 polling">
						<span class="textWidth">联系人:</span><input type="text" class="form-control inputName" id="manager2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">手机号码:</span><input type="text" class="form-control inputName" id="phone2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">电话号码:</span><input type="text" class="form-control inputName" id="unitTel2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">地址:</span><input type="text" class="form-control inputName" id="address2" />
					</div>
					<!-- <div class="col-xs-12 polling"><span class="col-xs-2 PaddIngMargin">证件号:</span><p class="col-xs-10 PaddIngMargin"><textarea type="text" class="form-control PaddIngMargin" id="remark1" style="margin-left:-9px;width:506px;height:72px;resize:none;"></textarea></p></div> -->
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="bianjiInfor">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!--删除-->
<div class="modal fade " id="addDelete" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 删除客户 </span>
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
<!--图片管理-->
<div class="modal fade addMun" id="" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content delecMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 图片管理 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<!--<p class="pupInfor"><span class="glyphicon glyphicon-question-sign icoIfor"></span>您确定要删除吗？</p>-->
				<div class="pupInfor" id="munAdd" style="height: 10px; border: 1px solid #CCCCCC;"></div>
			</div>
			<div class="modal-footer modalButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<div class="col-xs-12 PaddIngMargin">
	<table id="TableList" style="height: auto; max-height: 500px;" data-height="500" data-side-pagination="server" data-method="post" data-query-params="offetFunction" data-unique-id="unitCode" data-striped="true" data-url="<%=basePath%>/findBusinessManagentList" data-pagination="true" data-click-to-select="true" data-single-select="false">
		<thead>
			<tr>
				<th data-field="swact" data-checkbox="true" data-align="center"></th>
				<th data-field="unitCode" data-align="center">营业厅代码</th>
				<th data-field="unitName" data-align="center">营业厅名称</th>
				<!-- <th data-field="imgId" data-align="center">图片</th> -->
				<th data-field="manager" data-align="center">联系人</th>
				<th data-field="phone" data-align="center">手机号码</th>
				<th data-field="unitTel" data-align="center">电话号码</th>
				<th data-field="address" data-align="center">地址</th>
				<th  data-field="creater" data-align="center" >创建者</th>
				<th  data-field="createDate" data-align="center">创建时间</th>
				<th  data-field="status" data-align="center" data-formatter="usernameFormatter1">状态</th>
				<th data-field="caozuo" data-align="center" data-formatter="usernameFormatter">操作</th>
				<th data-field="status" data-align="center" data-visible="false"></th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript" src="../js/common/select.js"></script>
<script>
// 上传文件功能
$('#upfileList').on('click',function(event){
	event.stopPropagation();
	$('#upfile').click();
});
$('#upfile').on('change',function(event){
	event.stopPropagation();
	var val = $(event.target).val().substr($(event.target).val().lastIndexOf('\\') + 1);
	$('#upfileList').val(val);
})
// $('#upLoadFileBtnSubmit').on('click',function(event){
// 	event.stopPropagation();
// 	var val = $('#upfileList').val();
// 	if(val){
// 		$('#form1').submit();
// 	}
// });
// 清空Excel上传相关文件及文本框
//清空值默认值
function ClearExcel(){
	$("#upfileList").val("");
	$("#upfile").val("");
	$('form1').validationEngine('hideAll');
}

// 为了接收返回值给页面做些合适的提示，我们使用ajax的方式提交表单；
$('#upLoadFileBtnSubmit').on('click',function(event){
	event.stopPropagation();
	var val = $('#upfileList').val();
	var formdata = new FormData();
	if(val){
		// $('#form1').submit();
		var input2 = $('#upfile');
		formdata.append("file" , input2[0].files[0]);
		$.ajax({
			url:'<%=basePath%>/business/importExcel',
						cache : false,
						type : "post",
						datatype : 'json',
						contentType : false,
						processData : false,
						data : formdata,
						beforeSend : function() {
						},
						success : function(data) {
							console.log(data);
							if (data.success == true) {
								$("#upLoadFile").modal("hide");
								ClearExcel();
								publicPopups("ubus", "", "添加成功");
							} else {
								$("#upLoadFile").modal("hide");
								publicPopups("ubus", "", data.errmsg);
							}
							$('#TableList').bootstrapTable("refresh", {
								silent : true
							});//刷新表格
							//             	debugger;
						},
						complete : function() {
						}
					})
				}
			});

	$('#upLoadFileBtnCancel').on('click', function(event) {
		event.stopPropagation();
		$('#upLoadFile').modal('hide');
		$('#form1 input').val('');
	})
	//excel导出
	function checkDate(event) {
		event.preventDefault();
		$('#form2')[0].action = "<%=basePath%>business/exportExcel";
   		$('#form2').submit();	
}

var userId = "";
	 $('#TableList').bootstrapTable();
	 function usernameFormatter(value,row,index){
		 var start = "";
		 if(row.status == "1"){
			 start = "禁用";
		 }else if(row.status == "0"){
			 start = "启用"; 
		 }
		 	return "<a  onclick='start1(\""+row.unitCode+"\")'>"+start+"</a><a class='aMarGIn' onclick='start(\""+row.unitCode+"\")' data-toggle='modal' data-target='.addEditor'>编辑</a>";
	 }
	 function usernameFormatter1(value,row,index){
	var start = "";
		if (row.status == "1") {
			start = "启用";
		} else if (row.status == "0") {
			start = "禁用";
		}
		return start;
	}
	function start(d) {//获取当前选中行数据
		console.log("hahah,hahaha");
		var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
		console.log(row);
		$("#unitCode2").val(row.unitCode);
		$("#unitName2").val(row.unitName);
		$("#manager2").val(row.manager);
		$("#phone2").val(row.phone);
		$("#unitTel2").val(row.unitTel);
		$("#address2").val(row.address);
	}
	function offetFunction(params) {
		var data = {
			pageSize : this.pageSize,
			pageNumber : this.pageNumber,
			searchText : "",
			sortName : "",
			sortOrder : ""
		}
		console.log(params);
		return data;
	}
	//添加
	$("#addInforMaTYU").on("click", function() {
		$("#addInfor").submit();
	});
	//编辑
	$("#bianjiInfor").on("click", function() {
		$("#addInfor1").submit();
	});
	function addInforMationAce() {
		var data = {
			unitCode : $("#unitCode1").val(),
			unitName : $("#unitName1").val(),
			manager : $("#manager1").val(),
			phone : $("#phone1").val(),
			unitTel : $("#unitTel1").val(),
			address : $("#address1").val()
		};
		addInfor(data, 1, "<%=basePath%>/addBusinessManagent");
	 }
	 function start1(d){
		 var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
		 var data = {unitCode:row.unitCode,status:row.status};
		 var vcA = "";
		 if(row.status == "1"){
			 vcA = "禁用";
		 }else if(row.status== "0"){
			 vcA = "启用";
		 }
		 publicPopups("idsCvIoc","","您确定要"+vcA+"吗?");
		 $("#idsCvIoc").click(function(){
			 addInfor(data,4,"<%=basePath%>/updateBusinessManagent");
		 });
	 }
	 //删除设备信息
	$("#shebeiDelect").click(function(){
		 var option = $('#TableList').bootstrapTable("getAllSelections");
			var idList = [];
			$.each(option,function(i){
				idList.push(option[i].unitCode);
			});
			var data = {unitCode:""+idList};
			console.log(data);
		  addInfor(data,3,"<%=basePath%>/deleteBusinessManagent");
			
			
	});
	 $("#delectInfor").click(function(){
		 $(this).attr("data-target","");
		 var option = $('#TableList').bootstrapTable("getAllSelections");
		/*  data-target="#addDelete" */
		 if(option == ""){
			 publicPopups("ubus","","请选择您想要删除的数据");
		 }else{
			 $(this).attr("data-target","#addDelete");
		 }
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
            		 $("#addInformation,.addEditor,#addDelete").modal("hide");
            		 Clear();
            		 if(num ==1){
            			publicPopups("ubus","","添加成功");
            		}else if(num == 2){
            			publicPopups("ubus","","编辑成功");
            		}else if(num == 3){
            			publicPopups("ubus","","删除成功");
            		}
            	 }else{
            		 $("#addInformation,.addEditor,#addDelete").modal("hide");
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
	$("#unitCode1,#unitName1,#manager1,#phone1,#unitTel1,#address1").val("");
	$('form').validationEngine('hideAll');
}
$(".ClearZhiMoRen,.data_hiding").click(function(){
	Clear();
});
	//按条件查询
	 $("#chaxun").click(function(){
		 
		 var data = {unitCode:$("#unitCode").val(),unitName:$("#unitName").val()};
		  
		 console.log(data);
		 $('#TableList').bootstrapTable("refresh",{silent:true,query:data});
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
						addInforMationAce();
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
						var data = {unitCode:$("#unitCode2").val(),unitName:$("#unitName2").val(),manager:$("#manager2").val(),
								 phone:$("#phone2").val(),unitTel:$("#unitTel2").val(),address:$("#address2").val()};
						 console.log(data);
						 addInfor(data,2,"<%=basePath%>/updateBusinessManagent");
					}
				}
			});
		}()); 
	//添加日期控件
	 $('.form_datetime').datetimepicker({
	 	  language: 'zh-CN',//显示中文
	 	  format: 'yyyy-mm-dd',//显示格式
	 	  minView: "month",//设置只显示到月份
	 	  autoclose: true,//选中自动关闭
	 	  todayBtn: true,//显示今日按钮
	       pickDate: true,  
	       pickTime: true,  
	       hourStep: 1,  
	       minuteStep: 15,  
	       secondStep: 30,  
	       inputMask: true , 
	 	  pickerPosition:"bottom-left"
	 });
</script>
