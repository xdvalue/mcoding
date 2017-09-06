//全局获取分类信息
var dt_TypeId = buijs_geturl('typeid');
console.log('分类ID：'+ dt_TypeId)
var newsId = buijs_geturl('newsId');
$(document).ready(function() {
	$("#dt_header").load('plus_header.html');
	$("#dt_footer").load('plus_footer.html');
});
function changeDateTimeFormat(updatetime) {
    var date = new Date(updatetime);
    //获取年
    var year = date.getFullYear();
    //获取月
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    //获取日
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
        .getDate();
    //获取时
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date
        .getHours();
    //获取分
    var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
        .getMinutes();
    //获取秒
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
        .getSeconds();

    var sTime = year + "-" + month + "-" + currentDate ;

    return sTime;

}
function changeDateTimeFormats(updatetime) {
    var date = new Date(updatetime);
    //获取年
    var year = date.getFullYear();
    //获取月
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    //获取日
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
        .getDate();
    //获取时
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date
        .getHours();
    //获取分
    var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
        .getMinutes();
    //获取秒
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
        .getSeconds();

    var sTime = year + "年" + month + "月" + currentDate + "日" ;

    return sTime;
}
function GetQueryString(name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");		    
    var r = window.location.search.substr(1).match(reg);  
    if (r!=null) return (r[2]); 
    return null; 
}