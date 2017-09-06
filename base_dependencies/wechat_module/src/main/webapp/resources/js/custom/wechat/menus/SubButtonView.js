
var SubButtonView = function(settings){
	var defaultSetting = {
		parent : null, 
		subButton: null,
		onClick:null
	};
	
	$.extend(defaultSetting, settings);
	
	var self = this;
	this.id = settings.subButton !=null ? settings.subButton.id : settings.parent.id + '_-1';
	this.id = 'subMenu_menu_' + this.id;
	
    this.init = function(){
    	$('#sub_pre_menu_list_' + settings.parent.id).append(getHtml());
    	$('#'+self.id).click(function(){
    		settings.onClick(settings.subButton);
    	});
	}
	
	var getHtml = function(){
		var content = settings.subButton == null ? '<i class="icon14_menu_add"></i>' : '<i class="icon20_common sort_gray"></i>'+'<span class="js_l2Title">'+settings.subButton.name+'</span>';
		
		return '<li id="'+self.id+'" class="jslevel2">'+
                   '<a href="javascript:void(0);" class="jsSubView" draggable="false">'+
                       '<span class="sub_pre_menu_inner js_sub_pre_menu_inner">'+
                           content+
                       '</span>'+
                   '</a>'+
               '</li>';
	};
}