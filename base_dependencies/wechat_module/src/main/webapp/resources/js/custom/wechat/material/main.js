
var DataTableList = function () {
	var data = {
		orginId:$('#orginId').val(),
		imgPageNum: $('#imgPageNum').attr('data-page-num') 
	};
    
	var loadImg = function(){
		$.getJSON('wxMaterial/service/findByPage',{
			originId: data.originId,
			type:'image',
			pageNo: data.imgPageNum
		}, function(json){
			if(json.status != '00'){
				bootbox.alert('查询失败:' + json.msg);
				return;
			}
			
			
			
		});
	}
    
    
    
    
 
    return {
        init: function () {
//        	initTableA();
        }
    };
}();

