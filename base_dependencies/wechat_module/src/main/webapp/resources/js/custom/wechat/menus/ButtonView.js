/**
 * 一级菜单组件
 */

var ButtonView = function(settings){
	var defaultSetting = {
		parent : null,          //上级组件
		button: {},             //组件内容，包括 type/url/key
		onSubButtonClick: null, //下联的二级菜单
		onClick: null,          //点击菜单的事件
		isSelected: false       //是否被选中，如果是选中，如果有子菜单，就会显示子菜单。
	};
		
	$.extend(defaultSetting, settings);
	
	var self = this;
	var isFull = settings.button.subButtons!=null && settings.button.subButtons.length >=5;
	
	this.id = 'menu_'+ settings.button.id;
	this.init = function(){
		settings.parent.append(getHtml(settings.isSelected));
		
		$('#'+self.id).click(function(){
			
			settings.onClick(settings.button);
		});
		
		if(!settings.button || !settings.button.subButtons){
			return;
		}
		
		var index = 0;
		var subButtonViewList = $.map(settings.button.subButtons, function(subButton){
			subButton.id = settings.button.id + '_' + index;
			index ++ ;
			return new SubButtonView({
				parent:settings.button,
				subButton: subButton,
				onClick: settings.onSubButtonClick
			});
		});
		
		if(!isFull){
			subButtonViewList.push(new SubButtonView({
				parent:settings.button,
				onClick: settings.onSubButtonClick
			}));
		}
		
		$.each(subButtonViewList, function(index, subButtonView){
			subButtonView.init();
		});
		
	}
	
	var getHtml = function(isShow){
		if(!settings.button.name){
			var blankButtonHtml = 
				'<li class="js_addMenuBox pre_menu_item grid_item no_extra" id="menu_'+settings.button.id+'">'+
		            '<a href="javascript:void(0);" class="pre_menu_link js_addL1Btn" title="最多添加3个一级菜单" draggable="false">'+ 
		                '<i class="icon14_menu_add"></i>'+
		            '</a>'+
		        '</li>';
			return blankButtonHtml;
		}
		
		var displayHtml =  isShow ? '' : 'style="display: none;"';
		var subButtonWapperHtml = '' +
			'<div class="sub_pre_menu_box js_l2TitleBox" '+ displayHtml +' >'+
			    '<ul class="sub_pre_menu_list" id="sub_pre_menu_list_'+ settings.button.id +'">'+
		        '</ul>'+
		        '<i class="arrow arrow_out"></i>'+
		        '<i class="arrow arrow_in"></i>'+
	        '</div>';

		var buttonHtml = 
			'<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3" id="menu_'+settings.button.id+'">'+
		        '<a href="javascript:void(0);" class="pre_menu_link" draggable="false">'+
                    '<i class="icon_menu_dot js_icon_menu_dot dn"></i>' + 
                    '<i class="icon20_common sort_gray"></i>' + 
                    '<span class="js_l1Title">'+settings.button.name+'</span>' +
                '</a>' +
                subButtonWapperHtml +
            '</li>';
		
		return buttonHtml;
	};
	
	var getBlankHtml = function(){
		
	}
}