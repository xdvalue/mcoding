//初始化标签组件
var TagManger = function(option) {
	var elementId = option.elementId;
	var selectedTags = [];
	
	if(option && option.selectTags){
		selectedTags = option.selectTags;
	}
	var tagDom = $('#' + elementId);
	
	var addTag = function(tag){
		if(!isTagExist(selectedTags, tag)){
			selectedTags.push(tag);
		}
		loadTags(selectedTags);
	};
	
	var removeTag = function(tag){
		var index = -1;
		selectedTags.forEach(function(tmp, i){
			if(tmp.mark == tag.mark){
				index = i;
			}
		});
		
		if(index > -1){
			selectedTags.splice(index, 1);
		}
		loadTags(selectedTags);
	};
	
	
	var checkboxChangeHandler = function(tag) {
		if(isTagExist(selectedTags, tag)){
			removeTag(tag);
		}else{
			addTag(tag);
		}
	};
	
	var loadTags = function(tags){
		tagDom.importTags('');
		tags.forEach(function(tag) {
			tagDom.addTag(tag.mark);
		});
	}
	
	var isTagExist = function(tags, tag){
		var index = -1;
		tags.forEach(function(tmp, i){
			if(tmp && tag && tag.mark == tmp.mark){
				index = i;
			}
		});
		
		return index > -1;
	}
	
	this.getTags = function(){
		var tags = selectedTags.map(function(tag){
			var tmp = {};
			if(tag.mark) tmp.lableMark = tag.mark;
			if(tag.id) tmp.lableId = tag.id;
			
			return tmp;
		});
		return tags;
	}

	this.init = function() {
		tagDom.tagsInput({
			width : '82%',
			defaultText : '输入标签，回车确认',
			onAddTag : function(mark){
				
				var tag = { mark:mark };
				if(!isTagExist(selectedTags, tag)){
					addTag(tag);
				}
			},
			onRemoveTag:function(mark){
				var tag = { mark:mark };
				if(isTagExist(selectedTags, tag)){
					removeTag(tag);
				}
			}
		});
		
		loadTags(selectedTags);
		
		$('#'+ elementId +'_tag').on('focus', function() {
			$('#commonTagFormGroup').css("display", "block");
		});
		$.getJSON("label/service/findByPage", function(data){
			var commonTags = data.queryResult;
			commonTags.forEach(function(tag) {
				var buttonDom = $('<button>', {
					text : tag.mark,
					"class" : 'btn green',
					type : 'button',
					click : function() {
						checkboxChangeHandler(tag);
					}
				});
				$('#commonTag').append(buttonDom);
			});
		});
	};
}