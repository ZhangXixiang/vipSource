<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row">
 	<div class="col-xs-10" style="padding-left:0;padding-right:0;">
		<div class="col-xs-12 polling PaddIngMargin">
		<div class="col-xs-3 PaddIngMargin">
			<span class="disBlock">所属营业厅: </span><input type="text" class="form-control inputName" id="unitCodeQry" placeholder="所属营业厅">
		</div>
		<div class="col-xs-6 PaddIngMargin">
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="query">查询</button>
			<button type="button" class="btn btn-primary btn_primary" data-toggle="modal" data-target="" id="reset">重置</button>
		</div>
	</div>
</div>
		<!-- 表格数据  -->
		<div class="col-xs-12 PaddIngMargin">
			<table id="TableList" data-side-pagination="server" data-method="post" data-query-params="offetFunction" data-unique-id="macId" data-striped="true" data-url="<%=basePath%>mac/queryMacInfo" style="height:auto; max-height:420px;" data-pagination="true" data-height="500" data-click-to-select="true" data-single-select="false">
				<thead>
					<tr>
					<th  data-width="5%" data-field="swact" data-checkbox="true" data-align="center"></th>
					<th data-field="macId" data-align="center">MAC地址</th>
					<th data-field="ipAddress" data-align="center">设备IP</th>
					<th data-field="port" data-align="center">设备端口</th>
					<th data-field="unitCode" data-align="center">所属营业厅</th>
			 		<th data-field="creater" data-align="center">创建者</th>
			 		<th data-field="createDate" data-align="center">创建日期</th>
			 		<th data-field="status" data-align="center" data-formatter="translate">状态</th>
				</tr>
				</thead>
		    </table>
		</div>
	</div>

<script>
	$('#TableList').bootstrapTable();
	//条件查询
	$("#query").click(function(){
		var data = {
				unitCode:$("#unitCodeQry").val()
		};
		$('#TableList').bootstrapTable("refresh",{silent:true,query:data});
	});

	function translate(value,row,index){
		 var start = "";
		 if(row.status == "1"){
			 start = "在线";
		 }else if(row.status == "0"){
			 start = "离线";
		 }
		 return start;
	 }
	 function offetFunction(params){
		 var data = {
					pageSize:this.pageSize,
					pageNumber:this.pageNumber,
					unitCodeQry:$("#unitCodeQry").attr("value"),
					searchText:"",
					sortName:"",
					sortOrder:"" 
			}
			 //console.log(params);
		 	return data;
	 }
	 
	//清空值默认值
	 function Clear(){
// 	 	$("#unitCodeQry,#custNo1,#custSex1").val("");
	 	$("#unitCodeQry").val("");
	 }
	 $("#reset").click(function(){
	 	Clear();
	 });
	 
</script>
<script type="text/javascript" src="../js/common/select.js" ></script>
