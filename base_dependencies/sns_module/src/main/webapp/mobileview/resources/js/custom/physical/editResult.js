//产品积分列表相关操作
var Physical_EditResultManager = function () {

    //初始化页面相关按钮事件
    var initEvent = function () {
        $("#btn_edit_result").on("click", function(){
        	editResult();
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
function editResult() {
	var postData = JSON.stringify({
		id: parseInt($('#resultId').val()),
		score: parseFloat($('#score').val())
	});
	$.ajax({
        url: 'physicalUpdateResult',
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data : postData,
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