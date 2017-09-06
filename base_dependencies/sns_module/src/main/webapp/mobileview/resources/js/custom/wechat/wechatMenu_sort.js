//微信菜单排序
var firstmenu_order=[];//排序后的顺序
var firstmenu_val=[];
var secondmenu_order0=[];//排序后的顺序
var secondmenu_order1=[];//排序后的顺序
var secondmenu_order2=[];//排序后的顺序
var secondmenu_val=[];
var wechat_menusort = function() {
	//保存排序
	$("#confirmBut").on("click",function(){
		$.ajax({
			type : "get",
			dataType : "json",
			url : "WechatAPI/menuGet.html",
			success : function(data) {
				if (data.code == 0) {
					if (data.data == null) {
						return;
					}
					// 处理获取到的数据
					var befor_menu = data.data;
					//var after_menu = {};
					var after_menu = [];
					var buttons = [];
					var order_second=secondmenu_order0;//二级菜单顺序
					var button = befor_menu.buttons[0];//第一个一级菜单
					var subButtons = [];
					for(var i=0;i<order_second.length;i++){
						var index = order_second[i];
						subButtons[i]=button.subButtons[index];
					}
					befor_menu.buttons[0].subButtons=subButtons;
					
					if(secondmenu_order1!=""){
						order_second=secondmenu_order1;//二级菜单顺序
						button = befor_menu.buttons[1];//第三个一级菜单
						subButtons = [];
						for(var i=0;i<order_second.length;i++){
							var index = order_second[i];
							subButtons[i]=button.subButtons[index];
						}
						befor_menu.buttons[1].subButtons=subButtons;	
					}
					
					if(secondmenu_order2!=""){
						order_second=secondmenu_order2;//二级菜单顺序
						button = befor_menu.buttons[2];//第三个一级菜单
						subButtons = [];
						for(var i=0;i<order_second.length;i++){
							var index = order_second[i];
							subButtons[i]=button.subButtons[index];
						}
						befor_menu.buttons[2].subButtons=subButtons;	
					}
					
					var order=firstmenu_order;//一级菜单顺序
					for(var i=0;i<order.length;i++){
						var index = order[i];
						buttons[i]=befor_menu.buttons[index];
					}
					after_menu.buttons=buttons;
					after_menu.push(buttons);
					//console.info(after_menu);
					var param = JSON.stringify(after_menu);
					menuSort(param);
				} else {
					alert("操作失败");
				}
			}
		});
	});
	
	//改变一级菜单顺序
	$("#firstMenuText").on("change",function(){
		firstmenu_val = $("#firstMenuText").val();
		if(firstmenu_val==null){
			firstmenu_order=[];
		}
		if(firstmenu_val.length<firstmenu_order.length){
			firstmenu_order=firstmenu_val;
		}else if(firstmenu_val.length>firstmenu_order.length){//找出没有的那个
			for(var i=0;i<firstmenu_val.length;i++){
				var flag = false;
				for(var j=0;j<firstmenu_order.length;j++){
					if(firstmenu_val[i]==firstmenu_order[j]){
						flag = true;
						continue;
					}
				}
				if(!flag){
					var a = firstmenu_val[i];
					var length = firstmenu_val.length;
					firstmenu_order.push(firstmenu_val[i]);
					break;
				}
			}
		}
	});
	//改变第一个一级菜单下的二级菜单
	$("#secondMenuText0").on("change",function(){
		secondmenu_val = $("#secondMenuText0").val();
		if(secondmenu_val==null){
			secondmenu_order0=[];
		}
		if(secondmenu_val.length<secondmenu_order0.length){
			secondmenu_order0=secondmenu_val;
		}else if(secondmenu_val.length>secondmenu_order0.length){//找出没有的那个
			for(var i=0;i<secondmenu_val.length;i++){
				var flag = false;
				for(var j=0;j<secondmenu_order0.length;j++){
					if(secondmenu_val[i]==secondmenu_order0[j]){
						flag = true;
						continue;
					}
				}
				if(!flag){
					secondmenu_order0.push(secondmenu_val[i]);
					break;
				}
			}
		}
	});
	$("#secondMenuText1").on("change",function(){
		secondmenu_val = $("#secondMenuText1").val();
		if(secondmenu_val==null){
			secondmenu_order1=[];
		}
		if(secondmenu_val.length<secondmenu_order1.length){
			secondmenu_order1=secondmenu_val;
		}else if(secondmenu_val.length>secondmenu_order1.length){//找出没有的那个
			for(var i=0;i<secondmenu_val.length;i++){
				var flag = false;
				for(var j=0;j<secondmenu_order1.length;j++){
					if(secondmenu_val[i]==secondmenu_order1[j]){
						flag = true;
						continue;
					}
				}
				if(!flag){
					secondmenu_order1.push(secondmenu_val[i]);
					break;
				}
			}
		}
	});
	$("#secondMenuText2").on("change",function(){
		secondmenu_val = $("#secondMenuText2").val();
		if(secondmenu_val==null){
			secondmenu_order2=[];
		}
		if(secondmenu_val.length<secondmenu_order2.length){
			secondmenu_order2=secondmenu_val;
		}else if(secondmenu_val.length>secondmenu_order2.length){//找出没有的那个
			for(var i=0;i<secondmenu_val.length;i++){
				var flag = false;
				for(var j=0;j<secondmenu_order2.length;j++){
					if(secondmenu_val[i]==secondmenu_order2[j]){
						flag = true;
						continue;
					}
				}
				if(!flag){
					secondmenu_order2.push(secondmenu_val[i]);
					break;
				}
			}
		}
	});
	
	return {
		init : function() {

		},
	};
}();

function showsortdiv() {
	$.ajax({
		type : "get",
		dataType : "json",
		url : "WechatAPI/menuGet.html",
		success : function(data) {
			if (data.code == 0) {
				if (data.data == null) {
					return;
				}
				// 处理获取到的数据
				setFirstMenuText(data.data);
			} else {
				alert("获取菜单失败");
			}
		}
	});
}

function setFirstMenuText(data) {
	var firstmenunum = data.buttons.length;
	var result = "";
	result += "<option value=''>请选择菜单顺序</option>";
	for ( var i = 0; i < firstmenunum; i++) {
		result += "<option value=" + i + " selected>" + data.buttons[i].name
				+ "</option>";
	}
	$("#firstMenuText").empty().append(result).change();
	firstmenu_order=$("#firstMenuText").val();
	setSecondMenuText(data);
}

/*
 * function setSecondMenuText(data){ $("#secondMenuSelectDiv").show();
 * //console.info(data); var firstmenunum = data.buttons.length; var label = "";
 * var secondmenuselect = ""; var result = ""; for(var i=0;i<firstmenunum;i++){
 * var button = data.buttons[i]; //console.info(button); var subButtons =
 * button.subButtons; //console.info(subButtons); var secondmenunum =
 * subButtons.length; if(secondmenunum>0){//第i个一级菜单有二级菜单拼一个select label = "<label
 * class='control-label col-md-3'>"+button.name+"</label>"; secondmenuselect = "<select
 * class='form-control input-large select2me' name='secondMenuText'
 * id='secondMenuText"+i+"' multiple='multiple'>"; result = "<option
 * value=''>请选择子菜单顺序</option>"; for(var j=0;j<secondmenunum;j++){ result += "<option
 * value='"+i+"_"+j+"' selected>"+subButtons[j].name+"</option>"; }
 * secondmenuselect+=result; secondmenuselect+="</select>";
 * $("#secondMenuText"+i).empty().append(result).change(); }
 * $("#secondMenuSelectDiv").append(label);
 * $("#secondMenuSelectDiv").append(secondmenuselect); } }
 */

function setSecondMenuText(data) {
	var firstmenunum = data.buttons.length;
	var result = "";
	for ( var i = 0; i < firstmenunum; i++) {
		$("#second" + i).show();
		var button = data.buttons[i];
		$("#sencondMenuName" + i).html(button.name);
		var subButtons = button.subButtons;
		var secondmenunum = subButtons.length;
		if (secondmenunum > 0) {
			result = "<option value=''>请选择子菜单顺序</option>";
			for ( var j = 0; j < secondmenunum; j++) {
				result += "<option value='" + j + "' selected>"
						+ subButtons[j].name + "</option>";
			}
			$("#secondMenuText" + i).empty().append(result).change();
			if(i==0){
				secondmenu_order0 = $("#secondMenuText0").val();
			}else if(i==1){
				secondmenu_order1 = $("#secondMenuText1").val();
			}else if(i==2){
				secondmenu_order2 = $("#secondMenuText2").val();
			}
			
		}
	}
}

function menuSort(param){
	//console.info("============")
	//console.info(param)
	$.ajax({
        type : "post",
        dataType : "json",
        url : "WechatAPI/menuSort.html",
        data : {menujson: param},
        success : function(data) {
            if(data.code==0){
            	 bootbox.alert("操作成功");
                 $("#confirmWin2").modal("hide");
                 //wechat_menu.fnReloadAjax();
            }else{
            	alert("操作失败"+data.msg);
            }
        }
    });
}