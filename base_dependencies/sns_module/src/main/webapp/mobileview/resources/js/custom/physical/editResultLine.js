//产品积分列表相关操作
var Physical_EditResultLineManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $('#btn_edit_result_line').on('click', function(){
        	editResultLine();
        });
        $('#investigationkeywordid').change(function(){
        	$('#keyword').val($("#investigationkeywordid").find("option:selected").text());
        	$('#score').val($("#investigationkeywordid").find("option:selected").attr("data-score"));
        	$('#weight').val($("#investigationkeywordid").find("option:selected").attr("data-weight"));
        });
        $("#btn_enlarge").on("click", function(){
	        imgEnlarge();
	    });
		$("#btn_shrink").on("click",function(){
	        imgShrink();
	    });
    };

    return {
        init: function () {
            initEvent();
        },
    };
}();
function editResultLine() {
	var postData = JSON.stringify({
		id: parseInt($('#resultLineId').val()),
		keyword: $('#keyword').val(),
		keywordrecognize: $('#keywordrecognize').val(),
		resultdesc: $('#resultdesc').val(),
		resultvalue: parseFloat($('#resultvalue').val()),
		resultunit: $('#resultunit').val(),
		normalrangemin: parseFloat($('#normalrangemin').val()),
		normalrangemax: parseFloat($('#normalrangemax').val()),
		isanomaly: parseInt($('input[name="isanomaly"]:checked').val()),
		isseriousanomaly: parseInt($('input[name="isseriousanomaly"]:checked').val()),
		investigationkeywordid: parseInt($('#investigationkeywordid').val()),
		score: parseFloat($('#score').val()),
		weight: parseFloat($('#weight').val()),
		ext1: parseInt($('#ext1').val())
	});
	$.ajax({
        url: 'physicalUpdateResultLine',
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: postData,
        success:function(data){
        	if (null == data) {
        		bootbox.alert('服务器无响应');
        	} else {
        		bootbox.alert(data.msg);
            	if (data.code == 0) {
            		$("#btn_back_list").click();
            	}
        	}
        },
        error:function(){
        	bootbox.alert("网络异常!");
        }
	});
}
function imgEnlarge() {
	$('#img_physical_result').css("width", '100%');
	$('#img_physical_result').css("height", '100%');
}
function imgShrink() {
	$('#img_physical_result').css("width", '50%');
	$('#img_physical_result').css("height", '50%');
}