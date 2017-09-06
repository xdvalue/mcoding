/**
 * 
 */
var WxContentManager = (function(){
	
	var contentArray = [];
	var init = function(){
		
		var contentJsonStr = $('#contentTextArea').val();
		
		if(contentJsonStr){
			contentArray = JSON.parse(contentJsonStr);
		}
		
		load(contentArray);
		
		$('#addContentBtn').click(function(){
			var content = $('#addContentInput').val();
			if(!content || content.match(/^\s*$/g)){
				return;
			} 
			add(content);
			$('#addContentInput').val('');
		});
	};
	
	var load = function(array){
		$('#contentLisContainerDiv').empty();
		$.each(array, function(i, n){
			
			var html = 
			'<div class="form-group">'+
			    '<label class="col-md-3 control-label">已添加</span></label>'+
			    '<div class="col-md-4">'+
			    	'<div class="input-group">'+
				    	'<input type="text" class="form-control input-inline input-medium contentcls" readonly="readonly" value="'+n+'"/>'+ 
					    '<span class="input-group-btn">'+
						    '<button class="btn red" onclick="WxContentManager.removeContent(\''+n+'\');" type="button">删除</button>'+
					    '</span>'+
				    '</div>'+
			    '</div>'+
		    '</div>';
			$('#contentLisContainerDiv').append(html);
		});
		
		$('#contentTextArea').html(JSON.stringify(array));
	};
	
	var indexOf = function(content){
		var iContent = -1;
		$.each(contentArray, function(i,n){
			if(n == content){
				iContent = i;
				return false;
			}
		});
		
		return iContent;
	}
	
	var add = function(content){
		if(indexOf(content) >=0  ) {
			return;
		}
		contentArray.push(content);
		load(contentArray);
	};
	
	var remove = function(content){
		var index =indexOf(content) ; 
		if( index<0 ){
			return;
		}
		contentArray.splice(index,1);
		load(contentArray);
	};
	
	return {
		init:init,
		addContent:add,
		removeContent:remove
	};
})();