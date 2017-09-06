<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<LINK href="resources/css/wechat/base2fde18.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechat/layout_head2880f5.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechat/lib2f613f.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechat/index2b2fad.css" rel="stylesheet" type="text/css">
<LINK href="resources/css/wechat/tooltip218878.css" rel="stylesheet"　type="text/css">
<LINK href="resources/css/wechat/richvideo2b638f.css" rel="stylesheet"　type="text/css">
<LINK href="resources/css/wechat/mywechat.css" rel="stylesheet"　type="text/css">

<input type="hidden" id="originId" value="${account.originId }" />
<div class="main_bd">
	<div class="menu_initial_box js_startMenuBox" style="display: none">
		<p class="tips_global">你尚未添加任何菜单</p>
		<a class="btn btn_primary btn_add js_openMenu"
			href="javascript:void(0);"><i class="icon14_common add_white"></i>添加菜单</a>
	</div>
	<div class="menu_setting_box js_menuBox dn" style="display: block;">
		<div
			class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
			id="menustatus_2">
			<i class="icon icon_msg_small info"></i>
			<p class="title">菜单编辑中</p>
			<p class="desc">
				菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div
			class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
			id="menustatus_1">
			<i class="icon icon icon_msg_small success"></i>
			<p class="title">菜单使用中</p>
			<p class="desc">
				可在手机查看菜单内容。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div
			class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
			id="menustatus_3">
			<i class="icon icon_msg_small waiting"></i>
			<p class="title">菜单已发布</p>
			<p class="desc">
				<span class="js_waitHour"></span>小时后可在手机查看菜单内容。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div
			class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
			id="menustatus_8">
			<i class="icon icon_msg_small waiting"></i>
			<p class="title">菜单已发布/个性化菜单使用中</p>
			<p class="desc">
				<span class="js_waitHour"></span>小时后可在手机查看菜单内容。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div
			class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
			id="menustatus_9">
			<i class="icon icon_msg_small info"></i>
			<p class="title">菜单编辑中/个性化菜单使用中</p>
			<p class="desc">
				菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div
			class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
			id="menustatus_10">
			<i class="icon icon icon_msg_small success"></i>
			<p class="title">菜单使用中/个性化菜单使用中</p>
			<p class="desc">
				可在手机查看菜单内容，当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
					href="/cgi-bin/plugindetails?t=service/profile&amp;pluginid=10007&amp;action=intro&amp;token=926966060&amp;lang=zh_CN"
					class="js_closeMenu">点击这里</a>
			</p>
		</div>
		<div class="page_msg mini plugin_update_tips js_authorized"
			style="display: none; margin: -20px 30px 0 30px">
			<div class="inner group">
				<span class="msg_icon_wrp"><i class="icon_msg_mini info"></i></span>
				<div class="msg_content">
					<p>
						你已授权给<span class="js_auth_name"></span>帮助你运营公众号，点击管理<a
							href="/cgi-bin/component_unauthorize?action=list&amp;t=service/auth_plugins&amp;token=926966060&amp;lang=zh_CN">已授权的第三方平台</a>
					</p>
				</div>
			</div>
		</div>
		<div class="menu_setting_area js_editBox">
			<div class="menu_preview_area" >
				<div class="mobile_menu_preview">
					<div class="mobile_hd tc">${account.name }</div>
					<div class="mobile_bd">
						<ul class="pre_menu_list grid_line ui-sortable ui-sortable-disabled" id="menuList">
							
						</ul>
					</div>
				</div>
				<!-- <div class="sort_btn_wrp">
					<a id="orderBt" class="btn btn_default" href="javascript:void(0);"
						style="display: inline-block;">菜单排序</a> <span id="orderDis"
						class="dn btn btn_disabled" style="display: none;">菜单排序</span> <a
						id="finishBt" href="javascript:void(0);"
						class="dn btn btn_default">完成</a>
				</div> -->
			</div>
			<div class="menu_form_area">
				<div id="js_none" class="menu_initial_tips tips_global"
					style="display: none;">点击左侧菜单进行编辑操作</div>
				<div id="js_rightBox" class="portable_editor to_left" style="display: block;">
					
					<span class="editor_arrow_wrp"> <i
								class="editor_arrow editor_arrow_out"></i> <i
						class="editor_arrow editor_arrow_in"></i>
					</span>
				</div>
			</div>
		</div>
		<div class="tool_bar tc js_editBox">
			<span id="pubBt" class="btn btn_input btn_primary"><button>保存并发布</button></span>
			<!-- <a href="javascript:void(0);" class="btn btn_default" id="viewBt">预览</a> -->
		</div>
	</div>
</div>

<div id="keywordWin"></div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}resources/js/custom/wechat/wxMsgAutoReply/autoReplyWin.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/menus/ButtonView.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/menus/SubButtonView.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/menus/ButtonInfoView.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/wechat/menus/main.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript">
//WechatMenuMng.init();
MainMng.init();
</script>
