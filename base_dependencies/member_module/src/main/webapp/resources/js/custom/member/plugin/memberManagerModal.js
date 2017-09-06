(function($) {
	$.fn.memberManager = function(options){
		var defaultOptions = {
			singleSelect : true,
			onChange : function(){},
			selectedIds : [],
			isModal : true,
		};
		
		options = $.extend({}, defaultOptions, options);  
		
		var firstElement = null;
		this.each(function() {    
		    if(firstElement){
		    	return firstElement;
		    }    
		    
		    firstElement = $(this);
		});    
		
		fnCreateTableDiv(firstElement, options.isModal);
	}
	
	var fnCreateTableDiv = function(parent, isDisplay){
		var modal = $('<div class="modal fade" role="dialog" aria-hidden="true">');
		$(parent).append(modal);
	}
})(jQuery);