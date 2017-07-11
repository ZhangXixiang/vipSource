<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row contentHeader">
	<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">证件号: </span><input type="text" class="form-control inputName" id="zhengjian" placeholder="证件号">
		</div>
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">客户名称: </span><input type="text" class="form-control inputName" id="xingming" placeholder="姓名">
		</div>
		<div class="col-xs-6 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="chaxun">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="#addInformation">添加</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" id="delectInfor">删除</button>
		</div>
	</div>
</div>

<!--添加弹窗-->
<div class="modal fade" id="addInformation" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left" id="addMyModal"> 添加客户 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove data_hiding" data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-left">
				<form class="row" id="addInfor">
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>客户号:</span><input type="text" class="validate[required]  form-control inputName" id="custNo1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">客户姓名:</span><input type="text" class="form-control inputName" id="custName1" />
					</div>
					<!-- <div class="col-xs-6 polling"><span class="textWidth"><span class="item">*</span>性别:</span><input type="text" class="validate[required] form-control inputName" id="macModel" /></div> -->
					<div class="col-xs-6 polling">
						<span class="textWidth">性别: </span> <select name="custSex" id="custSex1" class="form-control inputName select-header">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">证件类型:</span> <select name="custLevel" id="creditType1" class="form-control inputName select-header">
							<option value="0">身份证</option>
							<option value="1">居住证</option>
							<option value="2">签证</option>
							<option value="3">护照</option>
							<option value="4">户口本</option>
							<option value="5">军人证</option>
							<option value="6">团员证</option>
							<option value="7">党员证</option>
							<option value="8">港澳通行证等</option>
						</select>
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">证件号:</span><input type="text" class="form-control inputName" id="creditNo1" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">客户级别: </span> <select name="custLevel" id="custLevel1" class="form-control inputName select-header">
							<option value="1">普卡客户</option>
							<option value="2">白金卡客户</option>
							<option value="3">金卡客户</option>
							<option value="4">至尊卡客户</option>
							<option value="5">钻石卡客户</option>
						</select>
					</div>
					<div class="col-xs-6 polling">
						<span class='textWidth'>出生日期：</span>
						<input class="form-control inputName"  type="date" id="custBir1">
					</div>
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop ClearZhiMoRen" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop " id="addInforMaTYU">保存</button>
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
					<span class="modal-title text-left" id="addMyModal"> 编辑客户 </span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="modal-body text-center">
				<form class="row" id="addInfor1">
					<div class="col-xs-6 polling">
						<span class="textWidth"><span class="item">*</span>客户号:</span><input type="text" readonly="readonly" class="validate[required]  form-control inputName" id="custNo2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">客户姓名:</span><input type="text" class="form-control inputName" id="custName2" />
					</div>
					<!-- <div class="col-xs-6 polling"><span class="textWidth"><span class="item">*</span>性别:</span><input type="text" class="validate[required] form-control inputName" id="macModel" /></div> -->
					<div class="col-xs-6 polling">
						<span class="textWidth">性别: </span> <select name="custSex" id="custSex2" class="form-control inputName select-header">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
						<div class="col-xs-6 polling">
						<span class="textWidth">证件类型:</span> <select name="custLevel" id="creditType2" class="form-control inputName select-header">
							<option value="0">身份证</option>
							<option value="1">居住证</option>
							<option value="2">签证</option>
							<option value="3">护照</option>
							<option value="4">户口本</option>
							<option value="5">军人证</option>
							<option value="6">团员证</option>
							<option value="7">党员证</option>
							<option value="8">港澳通行证等</option>
						</select>
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">证件号:</span><input type="text" class="form-control inputName" id="creditNo2" />
					</div>
					<div class="col-xs-6 polling">
						<span class="textWidth">客户级别: </span> <select name="custLevel" id="custLevel2" class="form-control inputName select-header">
							<option value="1">普卡客户</option>
							<option value="2">白金卡客户</option>
							<option value="3">金卡客户</option>
							<option value="4">至尊卡客户</option>
							<option value="5">钻石卡客户</option>
						</select>
					</div>
					<div class="col-xs-6 polling">
						<span class='textWidth'>出生日期：</span>
						<input class="form-control inputName"  type="date" id="custBir2">
					</div>
					<!-- <div class="col-xs-12 polling"><span class="col-xs-2 PaddIngMargin">证件号:</span><p class="col-xs-10 PaddIngMargin"><textarea type="text" class="form-control PaddIngMargin" id="remark1" style="margin-left:-9px;width:506px;height:72px;resize:none;"></textarea></p></div> -->
				</form>
			</div>
			<div class="modal-footer modalHeaderButtom">
				<button type="button" class="btn btn-primary BtnTop" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary BtnTop" id="bianjiInfor">保存</button>
			</div>
		</div>
	</div>
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
	<table id="TableList" style="height: auto; max-height: 500px;" data-height="500" data-side-pagination="server" data-method="post" data-query-params="offetFunction" data-unique-id="vipCusId" data-striped="true" data-url="<%=basePath%>/findVipCustomerList" data-pagination="true" data-click-to-select="true" data-single-select="false">
		<thead>
			<tr>
				<th data-width="5%" data-field="swact" data-checkbox="true" data-align="center"></th>
				<th data-field="vipCusId" data-visible="false" data-align="center"></th>
				<th data-width="15%" data-field="custNo" data-align="center">客户号</th>
				<th data-width="15%" data-field="custName" data-align="center">客户姓名</th>
				<!-- <th data-field="imgId" data-align="center">图片</th> -->
				<th data-width="5%" data-field="custSex" data-align="center" data-formatter="usernameFormatter1">性别</th>
				<th data-width="5%" data-field="creditType" data-align="center" data-formatter="usernameFormatter3">证件类型</th>
				<th data-width="20%" data-field="creditNo" data-align="center">证件号</th>
				<th data-width="10%" data-field="custBir" data-align="center">出生日期</th>
				<th data-width="10%" data-field="custLevel" data-align="center" data-formatter="usernameFormatter2">客户级别</th>
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
	 	return '<a class="aMarGIn" onclick="start('+row.vipCusId+')" data-toggle="modal" data-target=".addEditor">编辑</a>';
	 }
	 function start(d){//获取当前选中行数据
		console.log("hahah,hahaha");
	 	var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 	console.log(row);
		 $("#custName2").val(row.custName);
		 $("#custSex2").val(row.custSex);
	 	 $("#custNo2").val(row.custNo); 
	 	 $("#creditType2").val(row.creditType); 
	 	 $("#creditNo2").val(row.creditNo); 
	 	 $("#custLevel2").val(row.custLevel); 
	 	 $("#custBir2").val(row.custBir); // 反显日期
	 	userId = row.vipCusId;
	 }
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
	 //编辑
	 $("#bianjiInfor").on("click",function(){
		 $("#addInfor1").submit();
	 });
	 function addInforMationAce(){
		 var data = {creditNo:$("#creditNo1").val(),custName:$("#custName1").val(),custLevel:$("#custLevel1").val(),
		 custNo:$("#custNo1").val(),custSex:$("#custSex1").val(),creditType:$("#creditType1").val(),custBir:$("#custBir1").val(),};
		 addInfor(data,1,"<%=basePath%>/addVipCustomer");
	 }
	 function usernameFormatter1(value,row,index){
		 var start = "";
		 if(row.custSex == "0"){
			 start = "男";
		 }else if(row.custSex == "1"){
			 start = "女";
		 }
		 return start;
	 }
	 function usernameFormatter2(value,row,index){
		 var start = "";
		 if(row.custLevel == "1"){
			 start = "普通卡客户";
		 }else if(row.custLevel == "2"){
			 start = "白金卡客户";
		 }else if(row.custLevel == "3"){
			 start = "金卡客户";
		 }else if(row.custLevel == "4"){
			 start = "至尊卡客户";
		 }else if(row.custLevel == "5"){
			 start = "钻石卡客户";
		 }
		 return start;
	 }
	 function usernameFormatter3(value,row,index){
		 var start = "";
		 if(row.creditType == "0"){
			 start = "身份证";
		 }else if(row.creditType == "1"){
			 start = "居住证";
		 }else if(row.creditType == "2"){
			 start = "签证";
		 }else if(row.creditType == "3"){
			 start = "护照";
		 }else if(row.creditType == "4"){
			 start = "户口本";
		 }else if(row.creditType == "5"){
			 start = "军人证";
		 }else if(row.creditType == "6"){
			 start = "团员证";
		 }else if(row.creditType == "7"){
			 start = "党员证";
		 }else if(row.creditType == "8"){
			 start = "港澳通行证等";
		 }
		 return start
	 }
	 function modifyParm(){
		 var data = {
				 custName   : $("#unitCodeModify").val(),
				 custNo   : $("#unitNameModify").val(),
				 custSex    : $("#addressModify").val(),
				 unitCode   : unitCode
				    };
		 addInfor(data,2,"<%=basePath%>/updateVipCustomer");
	 }
	 //删除设备信息
	$("#shebeiDelect").click(function(){
		 var option = $('#TableList').bootstrapTable("getAllSelections");
			var idList = [];
			$.each(option,function(i){
				idList.push(option[i].custNo);
			});
			var data = {custNo:""+idList};
			console.log(data);
		  addInfor(data,3,"<%=basePath%>/deleteCustName");
			
			
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
	$("#custName1,#custNo1,#custSex1,#creditType1,#creditNo1,#custLevel1,#custBir1").val("");
	$('form').validationEngine('hideAll');
}
$(".ClearZhiMoRen,.data_hiding").click(function(){
	Clear();
});
	//按条件查询
	 $("#chaxun").click(function(){
		 
		  var data = {custName:$("#xingming").val(),creditNo:$("#zhengjian").val()};
		  
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
						 var data = {custName:$("#custName2").val(),custNo:$("#custNo2").val(),custSex:$("#custSex2").val(),
								 creditType : $("#creditType2").val(),creditNo: $("#creditNo2").val(),custLevel: $("#custLevel2").val(),
								 custBir: $("#custBir2").val(),vipCusId:userId};
						 console.log(data);
						 addInfor(data,2,"<%=basePath%>/updateVipCustomer");
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
