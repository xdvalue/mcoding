/* 
    Created by fanx by 2015-1-14
*/
(function(window,undefined){
	
	var backStack = [];
	
	// Tcbj Object start
	function Tcbj(){};
	Tcbj.prototype = {
        alert: function(message){
            //alert(message);        
            $('#id_pop_tips').html(message);
            $('#id_loader_page').removeClass('cls_hidden');
            $('#id_pop_message').removeClass('cls_hidden');                       
        }, // End alert
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
		getBaseURL: function(){
			var tempStr = window.location.host;
			
			if(tempStr == ''){
				tempStr = 'http://yyxy-test.by-health.com/NutritionistClub';
			}else{
				tempStr = window.location.protocol+'//'+tempStr+'/NutritionistClub';
			}
									
			return tempStr;
		}, // End getBaseURL
		ajax: function(opt, successFn,errorFun,completeFun){
			//console.log('ajax opt');
			//console.log(opt);
			
			var tempData = (opt.data !== undefined)? opt.data:{};
			var tempUrl = opt.url,tempType = opt.type.toUpperCase();
			
			if(tempUrl.match(/^http/i) != 'http'){
				tempUrl = tcbj.getBaseURL()+tempUrl; 
			}
			
			if(tempType === 'GET'){
				tempUrl += '&_random='+new Date().getTime();
			}
			
			if(opt.isShowLoader == undefined || opt.isShowLoader){				
				tcbj.showLoader();			
			}
			
        	$.ajax({
                type: tempType,
                url: tempUrl,
                data: tempData,
                timeout: 30000,
                success: function(data) {
                	
                    if(opt.isShowLoader == undefined || opt.isShowLoader){
                    	tcbj.hideLoader();
                    }
                    
                    if(successFn !== undefined && (typeof successFn === 'function') && data.errorCode !== undefined && data.errorCode === '00'){
                        successFn(data);                                                
                    }else if(successFn !== undefined && (typeof successFn === 'function') && opt.handleError !== undefined && opt.handleError){
                    	successFn(data);                    	
                    }else{
                    	tcbj.alert(data.errorMessage);
                    }
                },
               error: function(XMLHttpRequest, textStatus, errorThrown){
               	   //console.log(XMLHttpRequest);
               	   //console.log(textStatus);
               	   //console.log(errorThrown);
               	   
                   if(opt.isShowLoader == undefined || opt.isShowLoader){
                       tcbj.hideLoader();
                   }
                   
                   if(errorFun !== undefined && (typeof errorFun === 'function')){
                       errorFun();
                   }
               },
               complete: function(XMLHttpRequest, textStatus){
                   if(completeFun !== undefined && (typeof completeFun === 'function')){
                       completeFun();
                   }
               }
            });   // End $.ajax
		}, // End ajax
		// 根据name,获得URL里对应key的参数值.						
		getQueryString: function(name) {
			var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)','i');
			var r = window.location.search.substr(1).match(reg);
			
			if (r!=null){
				return (r[2]); 
			} 
			
			return null;
		} // End getQueryString
	};
	// Tcbj Object end


	// Utility Object start	
	function TcbjUtility(){};
    TcbjUtility.prototype = {
    	getImgSrcFromImgTag: function(imgTag){
    		var tempStr = '';
    		
    		tempStr = imgTag.substring(imgTag.indexOf('src="')+5); 
    		tempStr = tempStr.substring(0,tempStr.indexOf('"')); 
    		
    		return tempStr;
    	}, // End getImgSrcFromImgTag
    	getArrayPercentage: function(array){
    		var tempPercentageArray = [],tempTotal = 0;
			for(var i=0,len=array.length; i<len; i++){
				tempTotal += array[i];
			}
			
			for(var i=0,len=array.length; i<len; i++){
				tempPercentageArray[i] = Math.round(array[i]/tempTotal*100);
			}
			
	        return tempPercentageArray;
    	} // End getArrayPercentage	 	
	};
	// Utility Object end
	
	// Mobile Object start
	function TcbjMobile(){};
    TcbjMobile.prototype = {
    	curPageId: '',
    	init: function(pageId){
    		//console.log('tcbj.mobile.init pageId=='+pageId);
    		
    		if(pageId == undefined || pageId == ''){
    			alert('在TcbjMobile.init方法中缺少参数pageId');
    			return;
    		}
    		   		 
    		tcbj.mobile.curPageId = pageId; 		 
    		$('#'+tcbj.mobile.curPageId).removeClass('cls_hidden');
    		backStack.push(tcbj.mobile.curPageId);  
    		
    		window.addEventListener('hashchange', tcbj.mobile.onHashchange);
			window.location.hash = tcbj.mobile.curPageId;
    		
    		$('#id_pop_tips_btn').on('click',function(e){  
				//console.log('id_pop_tips_btn click');
				$('#id_loader_page').addClass('cls_hidden');
				$('#id_pop_message').addClass('cls_hidden');				 
			});				
    	}, // End init
    	onHashchange: function(event){
			//console.log('================= onHashchange window.location.hash=='+window.location.hash);
			
			var tempStr = window.location.hash.substring(1);
			
			tcbj.mobile.back(tempStr);			    			
		}, // End onHashchange	
		resetBackStack: function(pageId){
			//console.log('resetBackStack pageId=='+pageId);
			
			backStack = [];
			tcbj.mobile.curPageId = pageId;			
    		backStack.push(tcbj.mobile.curPageId); 
    		
			//console.log('backStack==');
			//console.log(backStack);
		}, // End resetBackStack		
    	go: function(pageId){
    		//console.log('go pageId=='+pageId);
    		
    		$('#'+tcbj.mobile.curPageId).addClass('cls_hidden');
    		$('#'+pageId).removeClass('cls_hidden');
					
			tcbj.mobile.curPageId = pageId;			
    		backStack.push(tcbj.mobile.curPageId);  
    		window.location.hash = tcbj.mobile.curPageId; 			
    	}, // End go
		back: function(pageId){
			//console.log('back pageId=='+pageId);
			//console.log(backStack);
			
			var tempLength = backStack.length;
	    	
	    	// pageId has value.
	    	if(pageId != undefined && pageId != '' && tempLength > 0){
	    		var tempNewArray = [], tempIsFound = false;
	    		
	    		for(var i=0; i<tempLength; i++){
	    			tempNewArray[i] = backStack[i];
	    			if(backStack[i] == pageId){
	    				tempIsFound = true;
	    				break;
	    			}
	    		} // End for
	    		
	    		if(tempIsFound){
	    			backStack = tempNewArray;
		    		
		    		$('#'+tcbj.mobile.curPageId).addClass('cls_hidden');
		    		$('#'+pageId).removeClass('cls_hidden');
		    		   		
		    		tcbj.mobile.curPageId = pageId;		 
		    		
		    		window.location.hash = tcbj.mobile.curPageId; 				    		
	    		} // End if
	    		
	    		return;
	    	}
	    		
			// pageId empty.
			if(tempLength > 1){
				var tempBackPage = backStack[tempLength-2];
	    		
	    		$('#'+tcbj.mobile.curPageId).addClass('cls_hidden');
	    		$('#'+tempBackPage).removeClass('cls_hidden');
	    		   		
	    		tcbj.mobile.curPageId = tempBackPage;
	    		backStack.pop();  
	    		
	    		window.location.hash = tcbj.mobile.curPageId;		
			} // End if					
		} // End back
	};
	// Mobile Object end
	
	
	if (typeof window.tcbj === 'undefined') {
		window.tcbj = new Tcbj();	 // new a Tcbj Class and register to window.t
		window.tcbj.util = new TcbjUtility();	 // new a TcbjUtility Class and register to window.tcbj.util
		window.tcbj.mobile = new TcbjMobile();	 // new a TcbjMobile Class and register to window.tcbj.mobile				
	}
	
})(window);
