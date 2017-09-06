var id = buijs_geturl('id');

$(function(){
	getNewsContent();
})

function getNewsContent(){
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsMsgInbox/front/findById?id='+id,
		dataType: 'json',
		success: function (data){
			if (data.status == "00") {
				$("#title").html(data.data.snsMsg.title || "系统消息");
				$("#content").html(data.data.snsMsg.content);
				$("#time").html(changeDateFormat(data.data.snsMsg.createTime))
			}else {
				buijs_alert({
					content: data.msg
				})
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}