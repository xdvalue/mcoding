$(function(){

	getMyInfo();
	getMemberSet();

	$("#myChose").bind('click',function (){
		changeMember();
	})
});

var myChose = false;
var hg_memberInfo;

/*获取会员配置信息*/
function getMemberSet() {
	$.ajax({
		type: 'get',
		url: _jsonUrl+ 'snsMemberSettingValue/front/findCurrentMemberSetting',
		dataType: 'json',
		success: function(data) {
			if (data.data.tpl_msg_is_enable == "1") {
				myChose = true;
				$("#myChose").css({'padding-left': '24px','background-color': 'green'});
			}
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

/*修改会员信息推送设置*/
function changeMember() {
	$.ajax({
		type: 'get',
		url: _jsonUrl + 'snsMemberSettingValue/front/changeMemberSetting?code=tpl_msg_is_enable&value='+(myChose ? "0": "1"),
		dataType: 'json',
		contentType: 'application/json',
		success: function (data){
			if (data.status == "00") {
				if (myChose == true) {
					myChose = false;
					$("#myChose").css({'padding-left': '1px','background-color': 'gray'});
				}else {
					myChose = true;
					$("#myChose").css({'padding-left': '24px','background-color': 'green'});
				}
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

/*获取会员信息*/
function getMyInfo () {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/member/front/findCurrentMember",
		dataType: 'json',
		success: function(data) {
			var levelPict;
			hg_memberInfo= data.data;
			hg_memberInfo.levelList.map(function(item){
				if(item.moduleType == "100"){
					levelPict= item.grade;
				}
			});
			$("#hg_myPanel").html('<div class="bui_ta_c bui_tc_white">' +
				'<div class="bui_row_p_32 bui_bgc_red">' +
				'	<li class="bui_inline bui_mt_24">' +
				'		<div>' +
				'			<div class="bui_round bui_bds_4 bui_bdc_white" data-bui_img="" style="width:96px;height:96px;"><img src="' + hg_memberInfo.wxMember.wxHeadimgurl + '"/></div>' +
				'			<h5 class="bui_ta_c" style="max-width: 95px">' + (hg_memberInfo.name || "未知用户") + '</h5> ' +
				'			<div class="bui_ts_14 bui_inline bui_vm"><span>会员等级&nbsp;</span><div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(levelPict || 0)+'</span></div></div>' +
				'		</div>' +
				'	</li>' +
				'</div>' +
				'<div class="bui_bgc_black_f32 bui_mt_24">' +
				'</div>').find('[data-bui_img]').buijs_img();
		},
		error: function (data,reo){
			buijs_alert({
				content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
			})
		}
	})
}

