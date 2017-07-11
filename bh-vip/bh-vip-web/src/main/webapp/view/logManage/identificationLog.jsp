<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row contentHeader">
	<div class="col-xs-12 polling PaddIngMargin identificationLog_polling">
		<div class="col-xs-4 PaddIngMargin marginBtm">
			<span class="disBlock col-xs-3 identificationLog_span">日志编号: </span><input type="text" class="form-control inputName identificationLog_input" id="rzbh" placeholder="日志编号">
		</div>
		<div class="col-xs-4 PaddIngMargin marginBtm">
			<span class="disBlock col-xs-3 identificationLog_span">识别用户: </span><input type="text" class="form-control inputName identificationLog_input" id="sbyh" placeholder="识别用户">
		</div>
		<div class="col-xs-4 PaddIngMargin marginBtm">
			<span class="disBlock col-xs-3 identificationLog_span">错误编号: </span>
			<select name="cwbh" id="cwbh" class="form-control inputName identificationLog_input">
				<option value="">请选择</option>
				<option value="0">参数缺失</option>
				<option value="1">营业厅异常</option>
				<option value="2">营业厅员工异常</option>
				<option value="3">未识别人脸</option>
				<option value="4">小于系统阀值</option>
				<option value="5">微信异常</option>
				<option value="6">未发现VIP</option>
				<option value="7">人脸平台异常</option>
			</select>
		</div>
		<div class="col-xs-4 PaddIngMargin marginBtm">
			<span class="disBlock col-xs-3 identificationLog_span">状态: </span>
			<select name="zt" id="zt" class="form-control inputName identificationLog_input">
				<option value="">请选择</option>
				<option value="1">推送成功</option>
				<option value="0">推送失败</option>
			</select>
		</div>
		<div class="col-xs-6 PaddIngMargin marginBtm" style="margin-left:15px;">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="chaxun">查询</button>
		</div>
	</div>
</div>
<!-- 表格数据 -->
<div class="col-xs-12 PaddIngMargin">
	 <table id="TableList" style="height:auto; max-height:500px;" data-height="500" data-side-pagination="server" data-method="post" data-query-params="offetFunction" data-unique-id="faceLogId" data-striped="true" data-url="<%=basePath%>vip/queryVipLog" data-pagination="true" data-click-to-select="true" data-single-select="false">
	 	<thead>
	 		<tr>
		 		<th data-field="faceLogId" data-align="center">日志编号</th>
	 			<th data-field="custName" data-align="center">识别用户</th>
	 			<th data-field="unitName" data-align="center">所属营业厅</th>
	 			<th data-field="sameScore" data-align="center">相似度</th> 
	 	    	<th data-field="errorCode" data-align="center" data-formatter="errorCodeFormatter">错误编号</th>
	 	    	<th data-field="errorDesc" data-align="center">错误信息</th>
	 	    	<th data-field="createDate" data-align="center">创建时间</th>
	 	    	<th data-field="computeTime" data-align="center">处理时长</th>
		 		<th data-field="status" data-align="center" data-formatter="statusFormatter">状态</th>
		 		<th data-field="caozuo" data-align="center" data-formatter="usernameFormatter">操作</th>
		 	</tr>
	 	</thead>
  </table>
</div>
<!--详情弹窗-->
	<div class="modal fade hitDetail" id="hitDetail" tabindex="-1" role="dialog" aria-labelledby="addMyModal" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content addMymodal">
			<div class="row modal-header modalHeaderTop">
				<div class="col-xs-6 text-left modalHeaderTopI">
					<span class="modal-title text-left">命中详情</span>
				</div>
				<div class="col-xs-6 text-right modalHeaderTopI">
					<span type="button" class="glyphicon glyphicon-remove " data-dismiss="modal" aria-hidden="true"></span>
				</div>
			</div>
			<div class="modal-body text-center">
				<div class="row">
					<div class="col-xs-6">
						<img id="sendImg" style="height:285px" class="img-responsive img-rounded" src="../images/common/defaultImg.jpg">
						<p class="text-center text_common">视频头像</p>
					</div>
					<div class="col-xs-6">
						<img id="result" style="height:285px" class="img-responsive img-rounded" src="../images/common/defaultImg.jpg">
						<p class="text-center text_common">命中头像</p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<span class="col-xs-6 hitDetail_inpt_common">处理耗时(毫秒)：</span>
							<div class="col-xs-6 hitDetail_inpt_common">
								<input type="text" class="form-control" readonly="readonly" id = "computeTime">
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<span class="col-xs-6 hitDetail_inpt_common">相似度：</span>
							<div class="col-xs-6 hitDetail_inpt_common">
								<input type="text" class="form-control" readonly="readonly" id = "sameScore">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/common/select.js" ></script>

<script>
// 初始化页面，刷新页面
 $('#TableList').bootstrapTable();
//详情按钮
 function usernameFormatter(value,row,index){
 	return '<a class="aMarGIn" onclick="start(\''+row.faceLogId+'\')" data-toggle="modal" data-target=".hitDetail">详情</a>';
 }
//点击详情，回填数据
 function start(d){//获取当前选中行数据
 	var row = $('#TableList').bootstrapTable('getRowByUniqueId', d);
	 console.log(row);
// 	 $("#computeTime").val(row.computeTime);
// 	 $("#sameScore").val(row.sameScore);
 	var data = {faceLogId:row.faceLogId};
 	addInfor(data,1,"<%=basePath%>vip/vipImg");
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
//         	 console.info(data.list);
        	 if(data.success == false){
     			publicPopups("Ase","",data.errmsg);
     			return;
     	 	}
        	 if(data.success == true){
        		 $("#sendImg").attr("src","data:image/jpeg;base64,"+data.list.sendImg+"");
        		 $("#result").attr("src","data:image/jpeg;base64,"+data.list.result+"");
        		 $("#sameScore").val(data.list.sameScore+"%");
        		 $("#computeTime").val(data.list.computeTime);
        	 }
         },
         complete:function(){},
         error:function(){}
     })
 }
//按条件查询
 $("#chaxun").click(function(){
	  var data = {faceLogId:$("#rzbh").val(),errorCode:$("#cwbh").val(),
			  custNo:$("#sbyh").val(),status:$("#zt").val()
			  };
	 $('#TableList').bootstrapTable("refresh",{silent:true,query:data});
 });
//  翻译设备状态字段,翻译设备状态的字段，即库里查出来的是零，翻译成初始！库里面查出来是1，翻译成启用显示在页面上边。
 function statusFormatter(value,row,index){
		var start = "";
		if(row.status == "0"){
			start = "推送失败";
		}else if(row.status == "1"){
			start = "推送成功";
		}
		return start;
	}
//  翻译错误信息
 function errorCodeFormatter(value,row,index){
		var start = "";
		if(row.errorCode == "0"){
			start = "参数缺失";
		}else if(row.errorCode == "1"){
			start = "营业厅异常";
		}else if(row.errorCode == "2"){
			start = "营业厅员工异常";
		}else if(row.errorCode == "3"){
			start = "未识别人脸";
		}else if(row.errorCode == "4"){
			start = "小于系统阀值";
		}else if(row.errorCode == "5"){
			start = "微信异常";
		}else if(row.errorCode == "6"){
			start = "未发现VIP";
		}else if(row.errorCode == "7"){
			start = "人脸平台异常";
		}
		return start;
	}
/*  onClickRow */
 function offetFunction(params){
	 var data = {
				pageSize:this.pageSize,
				pageNumber:this.pageNumber,
				searchText:"",
				sortName:"",
				sortOrder:"",
				proStauts:$("#sbzt").attr("value")
				
		}
	 	return data;
 }
</script>