/** 
 *  Created by fanx by 2015-3-24
**/
(function(window,undefined){
	
	// Mrmj Object start
	function Mrmj(){};
	Mrmj.prototype = {
		CONST: {
			MOBILE_NUM_LENGTH : 11,  // 手机号长度
			VERIFICATION_CODE_LENGTH : 6  // 验证码长度
		},
		init: function(){
			//console.log('mrmj init');

			mrmj.addLoaderMessageHTML();
        }, // End init
        addLoaderMessageHTML: function(){
        	//console.log('addLoaderMessageHTML'); 

        	var tempStr = '';

			tempStr += '<div id="id_loader_page" class="cls_hidden">';
			tempStr += '	<div class="cls_translucent_layer"></div>';
			tempStr += ' 	<div id="id_loader" class="cls_loader">';
			tempStr += '  		<div>';
			tempStr += '  			<img src="http://v.merryplus.com/resources/images/loadOverlay.gif" />';
			tempStr += '  			<span>数据加载中...</span>';
			tempStr += '  		</div>';
			tempStr += '  	</div>';
			tempStr += '  	<div id="id_pop_message" class="cls_pop_tips cls_hidden">';
			tempStr += '  		<div>';
			tempStr += '  			<div id="id_pop_tips">出错啦!</div>';
			tempStr += '  			<div id="id_pop_tips_btn">确认</div>';
			tempStr += ' 		</div>';
			tempStr += '  	</div>';  	  	
			tempStr += '</div>';
			
			$('body').append(tempStr);
        	mrmj.registerMessageButton();
        }, // End addLoaderMessageHTML
        registerMessageButton: function (){
            //console.log('registerMessageButton'); 
            
            $('#id_pop_tips_btn').on('click',function(e){  
				//console.log('id_pop_tips_btn click');
				$('#id_loader_page').addClass('cls_hidden');
				$('#id_pop_message').addClass('cls_hidden');				 
			});	    
        }, // End registerMessageButton
        showMessage: function(message){
        	//console.log('showMessage message=='+message);

            $('#id_pop_tips').html(message);
            $('#id_loader_page').removeClass('cls_hidden');
            $('#id_pop_message').removeClass('cls_hidden');                       
        }, // End showMessage
        hideLoader: function(){
        	//console.log('hideLoader');
    	
			$('#id_loader_page').addClass('cls_hidden');
            $('#id_loader').addClass('cls_hidden');	           
        },  // End hideLoader
        showLoader: function(){
        	//console.log('showLoader');
        	
        	$('#id_loader_page').removeClass('cls_hidden');
			$('#id_loader').removeClass('cls_hidden');	
        },  // End showLoader
		// 根据name,获得URL里对应key的参数值.						
		getQueryString: function(name) {
			var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)','i');
			var r = window.location.search.substr(1).match(reg);
			
			if (r!=null){
				return (r[2]); 
			} 
			
			return null;
		}, // End getQueryString
		checkInputEmpty: function(id){
			//console.log('checkInputEmpty id=='+id); 

			if($('#'+id).val().trim() === ''){
				return true;
			}
			
			return false;
		},  // End checkInputEmpty
		isCheckboxSelected: function(id){
			//console.log('isCheckboxSelected id=='+id); 

			if($('#'+id).prop('checked')){
				return true;
			}
			
			return false;
		},  // End isCheckboxSelected
		checkNumber: function(str){
			//console.log('checkNumber str=='+str);
			
			var tempArray = [],
				i = 0,
				len = 0,
				tempValue = 0,
				isValid = true;

			tempArray = str.split('');
			//console.log('tempArray=='+tempArray);

			for(i=0,len=tempArray.length; i<len;i++){
				tempValue = parseInt(tempArray[i], 10);
				if (isNaN(tempValue)) {
					isValid = false;
					break;
				};
			}

			//console.log('checkNumber isValid=='+isValid);
			return isValid;
		}, // End checkNumber
		// 产生年份下拉框数据
		generateYearSelectOptionData: function(id,endYear){
            //console.log('generateYearSelectOptionData'); 
            
            var tempStr = '', endYear = endYear || 1900, i=new Date().getFullYear();
            var year = $("#year").val();
			for(; i>=endYear; i--){
				if(i == year){
					tempStr += '<option value ="'+i+'" selected="selected">'+i+'</option>';
				}else{
					tempStr += '<option value ="'+i+'">'+i+'</option>';
				}
            }

            $('#'+id).html(tempStr);
        }, // End generateYearSelectOptionData
		// 产生1到12月份下拉框数据
		generateMonthSelectOptionData: function(id){
            //console.log('generateMonthSelectOptionData'); 
            
            var tempStr = '', i = 1;
            var month = $("#month").val();
			for(; i<=12; i++){
				if(i == month){
					tempStr += '<option value ="'+i+'" selected="selected">'+i+'</option>';
				}else{
					tempStr += '<option value ="'+i+'">'+i+'</option>';
				}
            }

            $('#'+id).html(tempStr);
        }, // End generateMonthSelectOptionData
        // 产生1到12月份下拉框数据
		generateDaysSelectOptionData: function(id){
            //console.log('generateDaysSelectOptionData'); 
            
            var tempStr = '', i = 1;
            var day = $("#day").val();
			for(; i<=31; i++){
				if(i == day){
					tempStr += '<option value ="'+i+'" selected="selected">'+i+'</option>';
				}else{
					tempStr += '<option value ="'+i+'">'+i+'</option>';
				}
            }

            $('#'+id).html(tempStr);
        } // End generateDaysSelectOptionData
	};
	// Mrmj Object end

	
	if (typeof window.mrmj === 'undefined') {
		window.mrmj = new Mrmj();	 // new a Mrmj Class and register to window.mrmj
	}
	
})(window);
