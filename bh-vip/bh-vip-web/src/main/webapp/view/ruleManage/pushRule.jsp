<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="modal-body text-center">
	<form class="row" id="addInfor">
		<div class="col-xs-12 polling">
			<div class="col-xs-6 text-right">
				<span class="textWidth"><span class="item">*</span>阈值(%):</span> <input type="number" min='0' class="validate[required]  form-control inputName" id="threshold" />
			</div>
			<div class="col-xs-6 text-left">
				<!-- 				<button type="button" class="btn btn-primary  pushRuleBtn" id="">确定</button> -->
			</div>
		</div>
		<div class="col-xs-12 polling">
			<div class="col-xs-6 text-right">
				<span class="textWidth" style="width: 95px; height: 34px; line-height: 34px;"><span class="item">*</span>时间间隔(秒):</span> <input type="number" min='0' class="validate[required] form-control inputName" id="timeInterval" />
			</div>
			<div class="col-xs-6 text-left">
				<!-- 				<button type="button" class="btn btn-primary  pushRuleBtn" id="">确定</button> -->
			</div>
		</div>
		<div class="col-xs-12 polling">
			<div class="col-xs-6 text-right">
				<span class="textWidth" style="width: 140px; height: 34px; line-height: 34px;"> <span class="item">*</span>日志保留时长（天）:
				</span> <input type="number" min='0' class="validate[required] form-control inputName" id="logSaveTime" />
			</div>
			<div class="col-xs-6 text-left">
				<button type="button" class="btn btn-primary  pushRuleBtn" id="">确定</button>
			</div>
		</div>
	</form>
</div>
<!-- <div class="modal-footer modalHeaderButtom"></div> -->
<script>
ajaxFunc();
function ajaxFunc(){
	$.ajax({
		url:"<%=basePath%>vip/queryPushRule",
		type:"post",
		data:"",
		dataType:"json",
		success:function(data){
			if(data.success){
				$('#threshold').val(data.rows[0].threshold);
				$('#timeInterval').val(data.rows[0].timeInterval);
				$('#logSaveTime').val(data.rows[0].logSaveTime);
			}
		},
		error:function(){},
		complete:function(){}
	})
}
			
			//做添加之前的前一步骤
			 $(".pushRuleBtn").on("click",function(){
				 $("#addInfor").submit();
			 });
			

			//添加/编辑设备公共方法封装,为了在页面上显示添加成功和添加失败
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
			            		 ajaxFunc();
			            		 $("#addInformation,.addEditor,#addDelete").modal("hide");
			            		 Clear();
			            		 if(num ==1){
			            			publicPopups("ubus","","更新成功");
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
			
				//校验添加的条件，自动执行的代码
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
					}()); 
				
				//添加的前端代码，提取用户输入的信息传到Controller
				 function addInforMationAce(){
					 var data = {threshold:$("#threshold").val(),
							 timeInterval:$("#timeInterval").val(),
							 logSaveTime:$("#logSaveTime").val(),
					 };
					 addInfor(data,1,"<%=basePath%>vip/updatePushRule");
	}
</script>