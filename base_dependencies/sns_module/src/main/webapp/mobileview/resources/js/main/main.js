/**
 * 初始化主页的数据
 */
var oTable = null;
var ul = "noticeId";
var userid = $("#userId").val();

var mainList = function() {
    //Ajax提交查询消息
    var initEvent = function() {
    	
    };
    
  //ajax查询订单统计数据
    var loadAnalysisData = function() {
    	$.ajax({
            type : "get",
            dataType : "json",
            url : "getDataAnalysis.html",
            success : function(data){
            	$.each(data, function(n, result) {
            		$("#sum"+n).empty().append(result)
            	});
            }
        });
    };
   
    return {
        init: function() {
            initEvent(); //提示框
            loadAnalysisData();
        },
        loadAnalysisData: loadAnalysisData
    };

} ();

