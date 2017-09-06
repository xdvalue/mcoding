/**
 * 菜单信息展示组件
 */
var ButtonInfoView = (function(){
	
	var keywordWin = $('#keywordWin').AutoReplyWin({
		onchange: function(selectKey){
			$('#urlText').val(selectKey);
		}
	});
	
	var buttonData = null;
	
	var deleteHandler = function(){
	};
	
	var getSelectedButton = function(){
		
		if(!buttonData){
			buttonData = {};
		}
		buttonData.type = $('.frm_radio_label.selected').attr('data-type');
		
		var value = $('#urlText').val();
		if(buttonData.type == 'view'){
			buttonData.url = value;
			buttonData.key = '';
		}else if(buttonData.type == 'click'){
			buttonData.url = '';
			buttonData.key = value;
		}else if(buttonData.type == 'scancode_push'){
			buttonData.key = 'menu_scancode_push';
		}
		
		buttonData.name = $('#buttonValueIpt').val();
		return buttonData;
	}
	
	var reset = function(isSub, buttonData){
		
		var buttonName = buttonData && buttonData.name ? buttonData.name : '';
		$('#buttonInfoViewTitel').text(buttonName);
		$('#buttonValueIpt').val(buttonName);
		
		var isParent = !isSub && buttonData && buttonData.subButtons && buttonData.subButtons.length > 0;
		
		var isFull = isSub ? false : buttonData==null? false : buttonData.isFull;
		$('.viewContent').show();
		$('#buttonInfoViewTip').hide();
		if(isParent){
			var text = isFull ? '已为添加了5个子菜单，无法设置其他内容。' : '已添加子菜单，仅可设置菜单名称。';
			$('#buttonInfoViewTip').text(text);
			$('#buttonInfoViewTip').show();
			$('.viewContent').hide();
		}
		var type = buttonData == null ? 'click' : buttonData.type;
		changeViewType(type);
		
		var value = buttonData == null ? '' : type == 'view' ? buttonData.url :buttonData.key ;
		$('#urlText').val(value);
	};
	
	var loadData = function(settings){
		var defaultSetting = { //选中的菜单信息
			selected : {
				isSub:false,   //是否是二级菜单
				button:null,   //选中的菜单内容
				subButton:null //如果选中是一级菜单，这里存下联的二级菜单
			},
			onDelete:deleteHandler //删除子菜单按钮的响应事件
		};
		$.extend(defaultSetting, settings);
		
		if(settings.selected.isSub){
			buttonData = settings.selected.subButton;
		}else{
			buttonData = settings.selected.button;
		}
		reset(settings.selected.isSub, buttonData);
		
		deleteHandler = settings.onDelete;
	};
	
	var getHtml = function(){
		var html = 
		'<div class="editor_inner">'+
		'    <div class="global_mod float_layout menu_form_hd js_second_title_bar">'+
		'        <h4 class="global_info" id="buttonInfoViewTitel">子菜单名字</h4>'+
		'        <div class="global_extra"> <a href="javascript:void(0);" id="jsDelBt">删除子菜单</a> </div>'+
		'    </div>'+
		'    <div class="menu_form_bd" id="view">'+
		'       <div id="buttonInfoViewTip" style="display: none;" class="msg_sender_tips tips_global">已为“嗨翻全场”添加了5个子菜单，无法设置其他内容。</div>'+
		'       <div class="frm_control_group js_setNameBox">'+
		'           <label for="" class="frm_label"> <strong class="title js_menuTitle">子菜单名称</strong> </label>'+
		'           <div class="frm_controls"> <span class="frm_input_box with_counter counter_in append"><input type="text" class="frm_input js_menu_name" id="buttonValueIpt"></span>'+
		'                <p class="frm_msg fail js_titleEorTips dn">字数超过上限</p>'+
		'                <p class="frm_msg fail js_titlenoTips dn" style="display: none;">请输入菜单名称</p>'+
		'                <p class="frm_tips js_titleNolTips">字数不超过8个汉字或16个字母</p>'+
		'            </div>'+
		'        </div>'+
		'        <div class="frm_control_group viewContent" style="display: block;">'+
		'            <label for="" class="frm_label"> <strong class="title js_menuContent">子菜单内容</strong> </label>'+
		'            <div class="frm_controls frm_vertical_pt">'+
		'                <label class="frm_radio_label" data-editing="0" data-type="click">'+
		'                    <i class="icon_radio"></i> <span class="lbl_content">发送消息</span>'+
		'                </label>'+
		'                <label class="frm_radio_label" data-editing="1" data-type="view">'+
		'                    <i class="icon_radio"></i> <span class="lbl_content">跳转网页</span>'+
		'                </label>'+
		'                <label class="frm_radio_label" data-editing="0" data-type="scancode_push">'+
		'                    <i class="icon_radio"></i> <span class="lbl_content">调起扫一扫</span>'+
		'                </label>'+		
		'            </div>'+
		'        </div>'+
		'        <div class="menu_content_container viewContent" style="display: block;">'+
		'            <div class="menu_content url jsMain" id="viewContentUrl" style="display: block;">'+
		'                <form action="" id="urlForm" onsubmit="return false;">'+
		'                    <p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>'+
		'                    <div class="frm_control_group">'+
		'                        <label for="" class="frm_label">页面地址</label>'+
		'                        <div class="frm_controls"> '+
		'                            <span class="frm_input_box"><input type="text" class="frm_input" id="urlText" name="urlText"/></span>'+
		'                            <p class="profile_link_msg_global menu_url mini_tips warn dn js_warn"> 请勿添加其他公众号的主页链接 </p>'+
		'                            <p class="frm_tips" id="js_urlTitle" style="display: none;"> 来自<span class="js_name"></span><span style="display:none;"> -《<span class="js_title"></span>》</span>'+
		'                            </p>'+'<span id="keywordBtn" class="btn btn_primary"><button>选择关键词</button></span>'+
		'                        </div>'+ 
		'                    </div>'+
		'                </form>'+
		'            </div>'+
		'            <div id="js_errTips" style="display:none;" class="msg_sender_msg mini_tips warn"></div>'+
		'        </div>'+
		'    </div>'+
		'</div>';
		
		return html;
	};
	
	var changeViewType = function(viewType){
		$('.frm_radio_label').removeClass('selected');
		$('#urlForm .frm_input_box').show();
		$('#keywordBtn').hide();
		if(viewType == 'view'){
			$('#viewContentUrl .menu_content_tips').text('订阅者点击该子菜单会跳到以下链接');
			$('#viewContentUrl .frm_label').text('页面地址');
		}else if(viewType == 'click'){
			$('#viewContentUrl .menu_content_tips').text('订阅者点击该子菜单，服务器收到以下key');
			$('#viewContentUrl .frm_label').text('关键字key');
			$('#keywordBtn').show();
		}else if(viewType == 'scancode_push'){
			$('#viewContentUrl .menu_content_tips').text('用户点击按钮后，微信客户端将调起扫一扫工具');
			$('#viewContentUrl .frm_label').text('调起扫一扫');
			$('#urlForm .frm_input_box').hide();
		}
		$('.frm_radio_label[data-type="'+viewType+'"]').addClass('selected');
	}
	
	$('#js_rightBox').empty();
	$('#js_rightBox').prepend(getHtml());
	
	$('.frm_radio_label').click(function(){
		var type = $(this).attr('data-type');
		changeViewType(type);
		if(buttonData==null){
			buttonData = {};
		}
		buttonData.type = type;
	});
	
	$('#jsDelBt').click(function(){
		bootbox.confirm("确认删除该菜单以及下级的子菜单吗?", function(result) {
			if (!result) {
				return;
			}
			deleteHandler(getSelectedButton());
		});
		
	});
	
	$('#keywordBtn').click(function(){
		keywordWin.show($('#urlText').val());
	});
	
	return {
		loadData: loadData,
		getSelectedButton : getSelectedButton
	};
})();
