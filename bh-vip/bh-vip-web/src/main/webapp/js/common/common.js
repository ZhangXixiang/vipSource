
$(function(){

	$("#contentLeft").css({
		"height":$(window).height()-80+"px",
		"overflow-y":"auto"
	});
	busGoto("nav.jsp","leftNav");
});
var inTimer = ""//人脸识别定时器
	/*计算左侧菜单栏高度*/
function busGoto(fileName,id)
	{
		$.get(fileName,{},function(res){
			$("#"+id).html(res);
		});
	}

	/*公共校验输入框是否为空*/
function volid(id){
	$('#'+id).validationEngine('attach', {
		relative: true,
		overflownDIV:"#divOverflown",
		promptPosition:"bottomLeft",
		maxErrorsPerField:1,
		onValidationComplete:function(form,status){
			
		}
	});
	
}
/*公共弹窗*/
function publicPopups(id,icon,msg){
	var html = "<div class='maskLayer'></div><div class='publicPopups'><div class='popups'><span class='popupsFont'>提示信息</span><span class='icon-remove'></span></div><div class='popupsInfor'><p><span class='informationTiShi "+icon+"' id=''></span><span id='erroyInformation'>"+msg+"</span></p></div><div class='popusButton text-center'><button type='button' class='btn btn-primary btnWidth' id='"+id+"'>确定</button><button type='button' style='display:none' class='btn btn-warning btnWidth' id='abrogate'>取消</button></div></div>";
	$("body").append(html);
	$("#"+id).click(function(){
		$(".maskLayer,.publicPopups").hide();
		$(".maskLayer,.publicPopups").empty();
	});
	$("#abrogate").click(function(){
		$(".maskLayer,.publicPopups").hide();
		$(".maskLayer,.publicPopups").empty();
	});
}
/*公共弹窗*/
function publicPopups2(id,icon,msg){
	var html = "<div class='maskLayer'></div><div class='publicPopups'><div class='popups'><span class='popupsFont'>提示信息</span><span class='icon-remove'></span></div><div class='popupsInfor'><p><span class='informationTiShi "+icon+"' id=''></span><span id='erroyInformation'>"+msg+"</span></p></div><div class='popusButton text-center'><button type='button' class='btn btn-primary btnWidth' id='"+id+"'>确定</button><button type='button' style='display:none' class='btn btn-warning btnWidth' id='abrogate'>取消</button></div></div>";
	$("body").append(html);
	$("#"+id).click(function(){
		$(".maskLayer,.publicPopups").hide();
		$(".maskLayer,.publicPopups").empty();
	});
	$("#abrogate").click(function(){
		$(".maskLayer,.publicPopups").hide();
		$(".maskLayer,.publicPopups").empty();
	});
}
//接收参数函数封装
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}