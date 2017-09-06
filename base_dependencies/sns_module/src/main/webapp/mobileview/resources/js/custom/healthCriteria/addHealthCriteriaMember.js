//检测结果相关
var health_addHealthCriteriaMemberList = function () {
    //初始化页面相关按钮事件
    var initEvent = function () {
        //$("#allSearch").hide();
        $("#singleAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
           if(!callMyvalidate()){
            	return;
            }
            setHealthCriteriaId();
            //setTotalScore();
            if(!setProduct()){
            	return;
            };
            setAnalysis();
            var requestURL = "addHealthCriteriaMember.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addHealthCriteriaMember.html";
                tips = "修改失败!";
            }
            var param = submitForm.serialize();
            $.ajax({
                type: "POST",
                url: requestURL,
                data: param,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#backListPage").click();
                    }else{
                        bootbox.alert(tips);
                    }
                }
            });
        });

        $("#nextAdd").on("click", function(){
            var submitForm = $("#submitForm");
            if(!submitForm.valid()){
                return;
            }
            $.ajax({
                type: "POST",
                url: "addHealthCriteriaMember.html",
                data: submitForm.serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                       bootbox.alert("增加成功 !");
                        //清除页面数据
                       $("#submitForm").find('input:type="text":').val('');
                    }else{
                        bootbox.alert("增加失败!");
                    }
                }
            });
        });

        var options = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	membername: {
                    required: true
                },
                mobilephone: {
                    required: true
                },
                level: {
                	number:true,
                	required: true
                },
                determine: {
                	required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
            }
        }

        //验证表单
       $("#submitForm").validate(options);
        
	/*   $("#onehealthcriteriaid").on("change", function(){
		   if($("#onehealthcriteriaid option:selected").val()==''){
			   $("#weight").val("");
			   $("#result").empty();
			   $("#result").append("<option value=''>请选择检测结果</option>");
			   $("#onescore").val("");
			   alert("请选择检测指标");
			   return;
		   }
		   $.ajax({
               type : "post",
               dataType : "json",
               url : "queryResultByHealthcriteriaid.html",
               data : {healthcriteriaid : $("#onehealthcriteriaid option:selected").val()},
               success : function(data){
            	   $("#weight").val(data.data.weight);
               }
           });
		   $("#result").empty();
		   $.ajax({
               type : "post",
               dataType : "json",
               url : "queryResultByTestitem.html",
               data : {testitem : $("#onehealthcriteriaid option:selected").text()},
               success : function(data){
            	    $("#result").append("<option value=''>请选择检测结果</option>");  
               	$.each(data, function(index,value){
               		$("#result").append("<option value='"+value.id+"'>"+value.result+"</option>");  
                   });
               }
           });
       });*/
	   
	   /*$("#result").on("change", function(){
		   if($("#result option:selected").val()==''){
			   $("#onescore").val("");
			   alert("请选择检测结果");
			   return;
		   }
		   $.ajax({
               type : "post",
               dataType : "json",
               url : "queryResultByHealthcriteriaid.html",
               data : {healthcriteriaid : $("#result option:selected").val()},
               success : function(data){
            	   $("#onescore").val(data.data.score);
               }
           });
       });*/
    };
    
   _addAutoDiv();
   
   /* $("#deleteAutoDiv").on("click",function(){
    	$("#autoTestItem").remove();
		$("#autoTestResult").remove();
    });*/
    
    return {
        init: function () {
        	initData();
            initEvent();
            queryKeyword();
        },
    };
}();

/**动态添加检测指标*/
function _addAutoDiv(){
    var k=1;
    $("#addAutoDiv").on("click",function(){
    	k=$("select[name='onehealthcriteriaid']").length+1;
    	var div="<div id='autoTestItem"+k+"'>";
    	div=div+"<div class='form-group'>"
    	div=div+"<label class='col-md-3 control-label'>检测指标</label>";
    	div=div+"<div class='col-md-3' style='width:300px;'>";
    	div=div+"<div class='input-group input-daterange'>";
    	div=div+"<select class='form-control input-medium' name='onehealthcriteriaid' id='onehealthcriteriaid"+k+"' onchange='setWeightAndResult("+k+");'>";
    	div=div+"<option value=''>请选择检测指标</option>";
    	div=div+"</select>";
    	div=div+"<span class='input-group-addon'>权重</span><input id='weight"+k+"' name='weight' type='text' class='form-control input-small' value=''>";
    	div=div+"</div></div></div></div>";
    	$("#autoDiv").append(div);
    	div="<div id='autoTestResult"+k+"'>";
    	div=div+"<div class='form-group'>";
    	div=div+"<label class='col-md-3 control-label'></label>";
    	div=div+"<div class='col-md-3' style='width:300px;'>";
    	div=div+"<div class='input-group input-daterange'>";
    	div=div+"<select class='form-control input-medium' name='result' id='result"+k+"' onchange='setOneScore("+k+");'><option value=''>请选择检测结果</option></select>";
    	div=div+"<span class='input-group-addon'>分值</span><input id='onescore"+k+"' name='onescore' type='text' class='form-control input-small' value=''>";
    	//div=div+"<span class='input-group-addon'><button id='deleteAutoDiv"+k+"' type='button' onclick='_aaa("+k+")'>删除指标</button></span>";
    	div=div+"</div></div></div></div>"
    	$("#autoDiv").append(div);
    	setTestItem(k);
    	k++;
    });
}
function _aaa(id){
	$("#autoTestItem"+id).remove();
	$("#autoTestResult"+id).remove();
}
/**初始化动态添加的检测项目select*/
function setTestItem(id){//k
	$.ajax({
        type : "post",
        dataType : "json",
        url : "queryHealthCriteriaList.html",
        success : function(data){
        	$.each(data, function(index,value){
        		$("#onehealthcriteriaid"+id).append("<option value='"+value.id+"'>"+value.testitem+"</option>");
            });
        }
    });
}
function setWeightAndResult(id){//k
	if($("#onehealthcriteriaid"+id+" option:selected").val()==''){
		   $("#weight"+id).val("");
		   $("#result"+id).empty();
		   $("#result"+id).append("<option value=''>请选择检测结果</option>");
		   $("#onescore"+id).val("");
		   alert("请选择检测指标");
		   return;
	   }
	$.ajax({
        type : "post",
        dataType : "json",
        url : "queryResultByHealthcriteriaid.html",
        data : {healthcriteriaid : $("#onehealthcriteriaid"+id+" option:selected").val()},
        success : function(data){
     	   $("#weight"+id).val(data.data.weight);
        }
    });
	 $("#result"+id).empty();
	   $.ajax({
         type : "post",
         dataType : "json",
         url : "queryResultByTestitem.html",
         data : {testitem : $("#onehealthcriteriaid"+id+" option:selected").text()},
         success : function(data){
        	 $("#result"+id).append("<option value=''>请选择检测结果</option>");
         	$.each(data, function(index,value){
         		$("#result"+id).append("<option value='"+value.id+"'>"+value.result+"</option>");
             });
         }
     });
}
function setOneScore(id){//k
	if($("#result"+id+" option:selected").val()==''){
		   $("#onescore"+id).val("");
		   alert("请选择检测结果");
		   return;
	   }
	$.ajax({
        type : "post",
        dataType : "json",
        url : "queryResultByHealthcriteriaid.html",
        data : {healthcriteriaid : $("#result"+id+" option:selected").val()},
        success : function(data){
     	   $("#onescore"+id).val(data.data.score);
     	   initSelectedProduct();
     	  setTotalScore();
     	 setAnalysis();
        }
    });
}
/**设置检测项目的id*/
function setHealthCriteriaId(){
	var healthcriteriaid="";
	$("select[name='result'] option:selected").each(function(){
		healthcriteriaid = healthcriteriaid + $(this).val() + ","
	});
	if(healthcriteriaid!=""){
		healthcriteriaid=healthcriteriaid.substring(0,healthcriteriaid.length-1);
	}
	$("#healthcriteriaid").val(healthcriteriaid);
}
/**计算总分*/
function setTotalScore(){
	var totalScore=0;
	$("input[name='onescore']").each(function(){
		if($(this).val()!=""){
			totalScore=parseInt(totalScore)+parseInt($(this).val());
		}
	});
	$("#score").val(totalScore);
}
/**初始化页面*/
function initData(){
	if($("#id").val() != '' && $("#scoreId").val()==0){
		initData1();
	}else if($("#id").val() != ''){
		initData2();
	}else{
		initData1();
	}
}
/**添加时初始化检测指标选项*/
function initData1() {
	$.ajax({
		type : "post",
    	dataType : "json",
    	url : "queryHealthCriteriaList.html",
    	async:false,
    	success : function(data){
    		for(var m=1;m<17;m++){
    			loadDiv(m);
    			$.each(data, function(index,value){
    				$("#onehealthcriteriaid"+m).append("<option value='"+value.id+"'>"+value.testitem+"</option>");
    			});
    			setOptionSelected(m);
    			setWeightAndResult(m);
    		}
    	}
	});
}

/**编辑时初始化检测指标*/
function initData2() {
	//setTestItem(m);
	$.ajax({
        type : "post",
        dataType : "json",
        url : "queryHealthCriteriaList.html",
        async:false,
        success : function(data){
        	var healthcriteriaids = $("#healthcriteriaid").val();
        	$.ajax({
                type : "post",
                dataType : "json",
                url : "queryHealthCriteriaByIds.html",
                async:false,
                data : {ids : healthcriteriaids},
                success : function(data1){
                	var m = 1;
                	$.each(data1, function(index1,value1){
                		m=$("select[name='onehealthcriteriaid']").length+1;
                		loadDiv(m);
                		$.each(data, function(index,value){
                    		$("#onehealthcriteriaid"+m).append("<option value='"+value.id+"'>"+value.testitem+"</option>");
                        });
                		setDefaultOption(m,value1);
                		//setWeightAndResult(m);
            			$.ajax({
            		        type : "post",
            		        dataType : "json",
            		        url : "queryResultByHealthcriteriaid.html",
            		        async:false,
            		        data : {healthcriteriaid : $("#onehealthcriteriaid"+m+" option:selected").val()},
            		        success : function(data){
            		     	  $("#weight"+m).val(data.data.weight);
            		     	  $("#result"+m).empty();
            				   $.ajax({
            			         type : "post",
            			         dataType : "json",
            			         url : "queryResultByTestitem.html",
            			         async:false,
            			         data : {testitem : $("#onehealthcriteriaid"+m+" option:selected").text()},
            			         success : function(data){//
            			        	$("#result"+m).append("<option value=''>请选择检测指标</option>");
            			         	$.each(data, function(index,value){
            			         		$("#result"+m).append("<option value='"+value.id+"'>"+value.result+"</option>");  
            			             });
            			         	var bbb_v = document.getElementById("result"+m);
                    				if( bbb_v.options.length >= 1)
                    				{
                    					for(var i=0; i<bbb_v.options.length; i++){
                    					    if(bbb_v.options[i].value  == value1.id){
                    					    	bbb_v.options[i].selected = "selected";
                    					        break;
                    					    }  
                    					} 
                    				}
            			         	setOneScore(m)
            			         	m++;
            			         }
            			     });
            		        }
            		    });
            			//setOneScore(m)
                    });
                }
            });
        }
    });
	
}
/**设置产品*/
function setProduct() {
	var str = [];
	$('#healthProductTable' + ' tbody tr .checkboxes:checked').each(function() {
		this.checked = !this.checked;
		str += $(this).val() + ",";
	});
	if (str == "") {
		alert("请至少选择一个产品");
		return false;
	} else {
		str = str.substring(0, str.length - 1);
	}
	$("#productid").val(str);
	return true;
}

/**编辑时带出选中产品的信息*/
function initSelectedProduct(){
	var productids=$("#productid").val().split(",");
	//console.info("checkboxes length:"+$('#healthProductTable'+' tbody tr .checkboxes').length);
	$('#healthProductTable'+' tbody tr .checkboxes').each(function() {
		for(var i=0;i<productids.length;i++){
			if($(this).val()==productids[i]){
				this.checked="checked";
			}
		}
	});
}

/**校验*/
function callMyvalidate(){
	var _ccc=document.getElementsByName("onescore");
	if(_ccc.length==0){
		alert("至少填写一个检测项目相关信息");
		return false;
	}
	for(var i=0;i<_ccc.length;i++){
		if(_ccc[i].value==""){
			alert("请填写检测项目相关信息");
			return false;
		}
	}
	var detailedreport = $("#detailedreport").val();
	if(detailedreport==""){
		alert("请务必上传检测报告");
		return false;
	}
	var mobilephone = $("#mobilephone").val();
	if(mobilephone=="" || mobilephone.trim().length!=11){
		alert("请输入11位手机号码")
		return false;
	}
	return true;
}

/**添加时页面写死指标*/
function setOptionSelected(id){
	if(id==1){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "疲劳"){
			    	aaa_v.options[i].selected = "selected";
			    	//aaa_v.options[i].value = value.id;
			        break;
			    }
			} 
		}
	}
	if(id==2){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "免疫系统"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==3){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "脑机能"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==4){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "睡眠"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==5){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "消化系统"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==6){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "运动系统"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==7){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "脂代谢"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==8){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:维生素A"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==9){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:维生素B"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==10){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:维生素C"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==11){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:维生素D"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==12){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:维生素E"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==13){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:锌(Zn)"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==14){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:钙(Ca)"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==15){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:硒(Se)"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
	if(id==16){
		var aaa_v = document.getElementById("onehealthcriteriaid"+id);
		if( aaa_v.options.length >= 1)
		{
			for(var i=0; i<aaa_v.options.length; i++){
				if(aaa_v.options[i].text  == "营养:铁(Fe)"){
					aaa_v.options[i].selected = "selected";
					break;
				}
			} 
		}
	}
}
/**编辑时设置选中项目*/
function setDefaultOption(m,value1){
	var aaa_v = document.getElementById("onehealthcriteriaid"+m);
	if( aaa_v.options.length >= 1)
	{
		for(var i=0; i<aaa_v.options.length; i++){
		    if(aaa_v.options[i].text  == value1.testitem){
		    	aaa_v.options[i].selected = "selected";
		    	aaa_v.options[i].value = value1.id;
		        break;
		    }  
		} 
	}
}
/**设定页面上显示的指标*/
function loadDiv(m){
	var div="<div id='autoTestItem"+m+"'>";
	div=div+"<div class='form-group'>";
	div=div+"<label class='col-md-3 control-label'>检测指标</label>";
	div=div+"<div class='col-md-3' style='width:300px;'>";
	div=div+"<div class='input-group input-daterange'>";
	div=div+"<select class='form-control input-medium' name='onehealthcriteriaid' id='onehealthcriteriaid"+m+"' onchange='setWeightAndResult("+m+");' disabled='disabled'>";
	div=div+"<option value=''>请选择检测指标</option>";
	div=div+"</select>";
	div=div+"<span class='input-group-addon'>权重</span><input id='weight"+m+"' name='weight' type='text' class='form-control input-small' value='' disabled='disabled'>";
	div=div+"</div></div></div>";
	$("#autoDiv").append(div);
	div="<div id='autoTestResult"+m+"'>";
	div=div+"<div class='form-group'>";
	div=div+"<label class='col-md-3 control-label'></label>";
	div=div+"<div class='col-md-3' style='width:300px;'>";
	div=div+"<div class='input-group input-daterange'>";
	div=div+"<select class='form-control input-medium' name='result' id='result"+m+"' onchange='setOneScore("+m+");'>";
	/*div=div+"<option value=''>请选择检测指标</option>";*/
	div=div+"</select>";
	div=div+"<span class='input-group-addon'>分值</span><input id='onescore"+m+"' name='onescore' type='text' class='form-control input-small' value='' disabled='disabled'>";
	//div=div+"<span class='input-group-addon'><button id='deleteAutoDiv"+m+"' type='button' onclick='_aaa("+m+")'>删除指标</button></span>";
	div=div+"</div></div></div>"
	$("#autoDiv").append(div);
}

function setAnalysis(){
	var score = $("#score").val();
	if(parseInt(score)<60){
		$("#level").val("4");
		$("#determine").val("吓傻了！");
		$("#description").val("天天困成狗，头晕又手抖，想吃没胃口，疲劳赶不走？身体都在投诉你了！赶紧调整生活方式，找回健康的自己吧！");
	}else if(parseInt(score)>=60 && parseInt(score)<75){
		$("#level").val("3");
		$("#determine").val("弱爆了！");
		$("#description").val("你用着最新潮的手机，可你对健康的认识还停留在上个世纪。快看看潜藏在身体的问题，update自己的健康吧！");
	}else if(parseInt(score)>=75 && parseInt(score)<85){
		$("#level").val("2");
		$("#determine").val("OK的！");
		$("#description").val("看来你对健康还是有点概念，不过有些问题可能还没发现。为健康，问题不论大小，越早解决越好！");
	}else{
		$("#level").val("1");
		$("#determine").val("棒棒的！");
		$("#description").val("三餐饮食有规矩，作息时间有规律，锻炼身体经常去，健康生活请继续！改善一些小问题，你将变得更好哦！");
	}
}

var queryKeyword=function(){
	$.ajax({
        type : "post",
        dataType : "json",
        async: false,
        url : "manager/getKeywordContentByPage.html",
        data: {	
        		pageNo:"1",
        		pageSize:"500"
        },
        success : function(data){
        	//添加页
        	var result = "<option value=''></option>";
            $.each(data.queryResult, function(index, value){
            	result += "<option value="+value+">"+value+"</option>";
            });
            
            $("#healthMark1Id").empty().append(result).change();
            $("#healthMark2Id").empty().append(result).change();
            $("#healthMark3Id").empty().append(result).change();
            $("#healthMark4Id").empty().append(result).change();
            
            var healthMark1Val = $("#hHealthMark1Id").val();
            var healthMark2Val = $("#hHealthMark2Id").val();
            var healthMark3Val = $("#hHealthMark3Id").val();
            var healthMark4Val = $("#hHealthMark4Id").val();
            
            $("#healthMark1Id option[value="+healthMark1Val+"]").attr("selected","selected").change();
            $("#healthMark2Id option[value="+healthMark2Val+"]").attr("selected","selected").change();
            $("#healthMark3Id option[value="+healthMark3Val+"]").attr("selected","selected").change();
            $("#healthMark4Id option[value="+healthMark4Val+"]").attr("selected","selected").change();
    	}
        	
    });
}
