//微信菜单
var wechat_menu = function () {
	
	setKeywordText();
	
	$("input[name='hello']").bind("click",function(){
		if($(this).attr("id")=="tiaozhuan_radio"){
			$("#xiaoxi").removeClass("selected");
			$("#tiaozhuan").addClass("selected");
			$("#url").show();
			$("#keyword").hide();
		}else{
			$("#xiaoxi").addClass("selected");
			$("#tiaozhuan").removeClass("selected");
			$("#url").hide();
			$("#keyword").show();
		}
	});
	
	$("#menuname_text").blur(function(){
		var menuname = $("#menuname_text").val();
		if(menuname.trim()==""){
			$("#menuname_error2").show();
			return;
		}
		if(!checkNameLength()){
			$("#menuname_error1").show();
			return;
		}
		//设置选中的菜单的名称
		$("li[name='menu_']").each(function(){//如果是一级菜单
			 if($(this).hasClass("current selected")){
				 //alert($(this).attr("id"));//auto_menuname
				 //$(this).html(menuname);
				 $("#auto_menuname"+$(this).attr("id").substring(5,$(this).attr("id").length)).text(menuname);
			 }
		});
		$("li[name='submenu_']").each(function(){//如果是二级菜单
			 if($(this).hasClass("current selected")){
				 $(this).text(menuname);
			 }
		});
	});
	$("#menuname_text").focus(function(){
		$("#menuname_error1").hide();
		$("#menuname_error2").hide();
	});
	
	/**删除按钮*/
	$("#jsDelBt").on("click",function(){
		if(confirm("确定删除吗?")){
			deleteMenu();
		}
	});
	
	/**保存按钮*/
	$("#pubBt").on("click",function(){
		if(!callMyvalidate()){
        	return;
        }
		saveMenu();
	});
	
    return {
        init: function () {
           initMenu();
        },
    };
}();

/**初始化底部菜单栏*/
function initMenu(){
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	if(data.data==null){
                	addMenuPlus();
            		return;
            	}
            	//console.info(JSON.stringify(data.data.buttons));
            	var menus = data.data.buttons;
            	for(var i=0;i<menus.length;i++){
            		var menuli = "<li class='jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3' name='menu_' id='menu_"+i+"'>";
            		menuli=menuli+"<a href='javascript:void(0);' class='pre_menu_link'>";
            		menuli=menuli+"<i class='icon_menu_dot js_icon_menu_dot dn' style='display: none;'></i>";
            		menuli=menuli+"<i class='icon20_common sort_gray'></i>";
            		menuli=menuli+"<span class='js_l1Title' name='auto_menuname' id='auto_menuname"+i+"' onclick='selectThisMenu("+i+")'>"+menus[i].name+"</span>";
            		menuli=menuli+"</li>";
            		$("#menuList").append(menuli);
            		if(i==0){//设为默认选中
                		$("#menu_"+i).addClass("current selected");
                		addSubmenuPlus(i);
                		setFirstmenuRight(i);
                		initSubMenu(0);
                	}
            	}
            	if(menus.length<3){
            		addMenuPlus();
            	}
            }else{
            	alert("获取菜单失败");
            }
        }
    });
	$("#level").val("1");//一级菜单
	$("#current_firstmenu").val(0);
}

/**选中该菜单项*/
function selectThisMenu(i){
	var flag = false;
	$("li[name='menu_']").each(function(){
		if($(this).hasClass("current selected")){
			if("menu_"+i==$(this).attr("id")){//点了自己
				flag = true;
			}
			 $(this).removeClass("current selected");
		 }
	});
	$("#menu_"+i).addClass("current selected");
	
	if(flag){
		return;
	}
	$("div[name='submenu_div']").each(function(){
		//if($(this).attr("id")!="submenu_div"+i){
			$(this).remove();
		//}
	});
	
	$("#current_firstmenu").val(i);//当前一级菜单
	
	//显示子菜单项
	initSubMenu(i);
	//在第i个菜单上面加一个加号按钮
	addSubmenuPlus(i);
	
	setFirstmenuRight(i);
	
	$("#level").val("1");//解决点击了二级菜单再点一级菜单名称不能修改的bug
}
/**选中该子菜单*/
function selectSubmenu(i,j){
	$("li[name='menu_']").each(function(){
		if($(this).hasClass("current selected")){
			$(this).removeClass("current selected");
		 }
	});
	$("li[name='submenu_']").each(function(){
		if($(this).hasClass("current selected")){
			$(this).removeClass("current selected");
		}
	});
	$("#"+i+"submenu_"+j).addClass("current selected");
	$("#level").val("2");//二级菜单
	setSecondmenuRight(i,j);
}

/**点击添加菜单的加号*/
function addMenu(){
	$("#addMenu_li").remove();//移除这个加号
	var i = parseInt($("li[name='menu_']").length);
	var menuli = "<li class='jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3' name='menu_' id='menu_"+i+"'>";
	menuli=menuli+"<a href='javascript:void(0);' class='pre_menu_link'>";
	menuli=menuli+"<i class='icon_menu_dot js_icon_menu_dot dn' style='display: none;'></i>";
	menuli=menuli+"<i class='icon20_common sort_gray'></i>";
	menuli=menuli+"<span class='js_l1Title' name='auto_menuname' id='auto_menuname"+i+"' onclick='selectThisMenu("+i+")'>"+"菜单名称"+"</span>";
	menuli=menuli+"</li>";
	$("#menuList").append(menuli);//添加一个菜单项
	selectThisMenu(i);//选中该菜单
	
	$("#menuname_text").val("菜单名称");
	$("#level").val("1");//一级菜单
	$("#current_firstmenu").val(i);//当前一级菜单
	if(i<2){
		addMenuPlus();
	}
}

/**点击添加子菜单的加号*/
function addSubmenu(i){
	if($("#submenu_ul"+i).find("li[name='submenu_']").length>4){//最多5个二级菜单
		alert("一个一级菜单上最多能创建5个二级菜单");
		return;
	}
	$("li[name='menu_']").each(function(){//去掉一级菜单的选中状态
		 if($(this).hasClass("current selected")){
			 $(this).removeClass("current selected");
		 }
	});
	//1，在第i个菜单项上显示一个子菜单项
	var j = $("li[name='submenu_']").length;
	var li = "<li class='jslevel2' name='submenu_' id='"+i+"submenu_"+j+"' onclick='selectSubmenu("+i+","+j+")'><a href='javascript:void(0);' class='jsSubView' draggable='false'>" +
	"<span class='sub_pre_menu_inner js_sub_pre_menu_inner'><i class='icon20_common sort_gray'></i>" +
			"<span class='js_l2Title'>子菜单名称</span></span></a></li>";
	$("#submenu_ul"+i).append(li);
	selectSubmenu(i,j);

	$("#menuname_text").val("子菜单名称");
	$("#level").val("2");//二级菜单
}

/**请求后台添加菜单*/
function saveMenu(){
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	var level = $("#level").val();//1:一级菜单，2：二级菜单
    			var current_firstmenu = $("#current_firstmenu").val();//当前一级菜单索引
            	if(data.data==null){//之前没有菜单
            		if(level=="1"){
            			var menulist = [];
            			var object = {};
                    	object.name = $("#menuname_text").val();
                    	object.type = $("input[name='hello']:checked").val();
                    	if($("input[name='hello']:checked").val()=="click"){
                    		object.key = $("#keywordText").val();
                    	}else{
                    		object.url = $("#urlText").val();
                    	}
                    	menulist.push(object);
            		}
            		var param = JSON.stringify(menulist);
                	postDataToController(param);
            		return;
            	}
            	var menulist = data.data.buttons;
            	if(level=="1"){
            		if(menulist.length==$("li[name='menu_']").length){//修改一级菜单
            			if(menulist[current_firstmenu].subButtons.length>0){
            				/*alert("该一级菜单下有二级菜单不能修改");
            				return;*/
            				var object = menulist[current_firstmenu];
            				object.name = $("#menuname_text").val();
                        	object.type = $("input[name='hello']:checked").val();
                        	object.subButtons = menulist[current_firstmenu].subButtons;
                        	if($("input[name='hello']:checked").val()=="click"){
                        		object.key = $("#keywordText").val();
                        	}else{
                        		object.url = $("#urlText").val();
                        	}
                        	menulist[current_firstmenu] = object;
            			}else{
            				var object = menulist[current_firstmenu];
            				object.name = $("#menuname_text").val();
                        	object.type = $("input[name='hello']:checked").val();
                        	if($("input[name='hello']:checked").val()=="click"){
                        		object.key = $("#keywordText").val();
                        	}else{
                        		object.url = $("#urlText").val();
                        	}
                        	menulist[current_firstmenu] = object;
            			}
            		}else{//新增一级菜单
            			var object = {};
                    	object.name = $("#menuname_text").val();
                    	object.type = $("input[name='hello']:checked").val();
                    	if($("input[name='hello']:checked").val()=="click"){
                    		object.key = $("#keywordText").val();
                    	}else{
                    		object.url = $("#urlText").val();
                    	}
                    	menulist.push(object);
            		}
            	}else if(level=="2"){
            		if(menulist.length==$("li[name='menu_']").length){//在原有的一级菜单中操作二级菜单
            			var j = 0;
            			$("li[name='submenu_']").each(function(){
            				if($(this).hasClass("current selected")){
            					j =  $(this).attr("id").substring($(this).attr("id").length-1,$(this).attr("id").length);//二级菜单索引
            				}
            			});
            			var firstmenu = menulist[current_firstmenu];
            			var secondmenu = firstmenu.subButtons;
            			if(secondmenu.length>=parseInt(j)+1){//后台存在该二级菜单，修改
            				var object = secondmenu[j];
            				object.name = $("#menuname_text").val();
                        	object.type = $("input[name='hello']:checked").val();
                        	if($("input[name='hello']:checked").val()=="click"){
                        		object.key = $("#keywordText").val();
                        	}else{
                        		object.url = $("#urlText").val();
                        	}
                        	secondmenu[j] = object;
                        	firstmenu.subButtons = secondmenu;
                        	menulist[current_firstmenu] = firstmenu;
            			}else{//新增
                			var object = {};
                        	object.name = $("#menuname_text").val();
                        	object.type = $("input[name='hello']:checked").val();
                        	if($("input[name='hello']:checked").val()=="click"){
                        		object.key = $("#keywordText").val();
                        	}else{
                        		object.url = $("#urlText").val();
                        	}
                        	secondmenu.push(object);
                        	firstmenu.subButtons=secondmenu;
                        	menulist[current_firstmenu]=firstmenu;
            			}
            			
            		}else{//在新建的一级菜单中操作二级菜单，只有新增
            			var firstmenu = {};
                    	var secondmenu = [];
                    	var object = {};
                    	object.name = $("#menuname_text").val();
                    	object.type = $("input[name='hello']:checked").val();
                    	if($("input[name='hello']:checked").val()=="click"){
                    		object.key = $("#keywordText").val();
                    	}else{
                    		object.url = $("#urlText").val();
                    	}
                    	secondmenu.push(object);
                    	firstmenu.name = $("#auto_menuname"+current_firstmenu).text();
                    	firstmenu.subButtons=secondmenu;
                    	menulist.push(firstmenu);
            		}
            	}
            	var param = JSON.stringify(menulist);
            	//var param = JSON.parse(JSON.stringify(menulist));
            	//var param = "{"+JSON.stringify(menulist)+"}";
            	//var param = JSON.stringify(menulist).substring(1,JSON.stringify(menulist).length-1);
            	postDataToController(param);
            }else{
            	alert("获取菜单失败");
            }
        }
    });
}
function postDataToController(param){
	console.info("============")
	console.info(param)
	$.ajax({
        type : "post",
        dataType : "json",
        url : "WechatAPI/menuCreate.html",
        //data : {name : $("#menuname_text").val(),type : $("input[name='hello']:checked").val(),url : $("#urlText").val(),level : $("#level").val(),current_firstmenu : $("#current_firstmenu").val(i)},
        data : {menujson: param},
        success : function(data) {
            if(data.code==0){
            	alert("操作成功");
            }else{
            	alert("操作失败"+data.msg);
            }
        }
    });
}

/**删除*/
function deleteMenu(){
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	var level = $("#level").val();//1:删除一级菜单，2：删除二级菜单
            	var current_firstmenu = $("#current_firstmenu").val();//要被删除的一级菜单的下标或者要被删除的二级菜单依附的一级菜单
            	if(data.data==null){//后台么有菜单哦
            		$("#menu_"+current_firstmenu).remove();
        			selectThisMenu(parseInt(current_firstmenu)-1);
            		return;
            	}
            	var menulist = data.data.buttons;
            	if(level=="1"){
            		if(menulist.length>=parseInt(current_firstmenu)+1){//需调用后台删除
            			menulist.splice(current_firstmenu,1);
            			var param = JSON.stringify(menulist);
            			deleteData(param);
            		}
            		$("#menu_"+current_firstmenu).remove();
        			selectThisMenu(parseInt(current_firstmenu)-1);
        			addMenuPlus();
            	}else if(level=="2"){
            		var removeparent = false;
            		if(parseInt(current_firstmenu)+1<=menulist.length){//后台存在该一级菜单
            			var j = 0;
            			$("li[name='submenu_']").each(function(){
            				if($(this).hasClass("current selected")){
            					j =  $(this).attr("id").substring($(this).attr("id").length-1,$(this).attr("id").length);//要被删除的二级菜单
            				}
            			});
            			var menulisti = menulist[current_firstmenu];
            			var secondmenu = menulisti.subButtons;
            			if(secondmenu.length>=parseInt(j)+1){//后台存在该二级菜单
            				if(secondmenu.length>1){//删除后后台还有其他子菜单
            					secondmenu.splice(j,1);
                    			menulisti.subButtons = secondmenu;
                    			menulist[current_firstmenu] = menulisti;
                			}else{//删除后么有其他子菜单，直接删除一级菜单
                				menulist.splice(current_firstmenu,1);
                				removeparent = true;
                			}
                			var param = JSON.stringify(menulist);
                			deleteData(param);
            			}
            		}
            		//在页面移除
            		if(removeparent){
            			$("#menu_"+current_firstmenu).remove();
            			addMenuPlus();
            		}else{
            			$("#"+current_firstmenu+"submenu_"+j).remove();
            		}
            	}
            }else{
            	alert("获取菜单失败");
            }
        }
    });
}
function deleteData(param){
	$.ajax({
        type : "post",
        dataType : "json",
        url : "WechatAPI/menuDelete.html",
        async:false,
        data : {menujson: param},
        success : function(data) {
            if(data.code==0){
            	alert("删除成功");
            }else{
            	
            }
        }
    });
}

/**初始化二级菜单*/
function initSubMenu(i){
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	if(data.data!=null){//后台没有菜单，不执行任何动作
            		var menus = data.data.buttons;
            		if(menus.length>=parseInt(i)+1){//后台有这个菜单,显示子菜单
            			var submenus = menus[i].subButtons;
            			for(var j=0;j<submenus.length;j++){
            				var li = "<li class='jslevel2' name='submenu_' id='"+i+"submenu_"+j+"' onclick='selectSubmenu("+i+","+j+")'><a href='javascript:void(0);' class='jsSubView' draggable='false'>" +
            				"<span class='sub_pre_menu_inner js_sub_pre_menu_inner'><i class='icon20_common sort_gray'></i>" +
            						"<span class='js_l2Title'>"+submenus[j].name+"</span></span></a></li>";
            				$("#submenu_ul"+i).append(li);
            			}
            		}
            	}
            }else{
            	alert("获取子菜单失败");
            }
        }
    });
}

/**一级菜单加号*/
function addMenuPlus(){
	var addmenuli = "<li class='js_addMenuBox pre_menu_item grid_item no_extra size1of3' name='addMenu_li' id='addMenu_li' onclick='addMenu()'>" +
	"<a href='javascript:void(0);' class='pre_menu_link js_addL1Btn' title='添加菜单'>" +
	"<i class='icon16_common add_gray'></i></a></li>";
	$("#menuList").append(addmenuli);
}
/**二级菜单加号*/
function addSubmenuPlus(i){
	var submenu = "<div name='submenu_div' id='submenu_div"+i+"'>";
	submenu=submenu+"<div class='sub_pre_menu_box js_l2TitleBox' style=''>";
	submenu=submenu+"<ul class='sub_pre_menu_list' name='submenu_ul' id='submenu_ul"+i+"'>";
	submenu=submenu+"<li class='js_addMenuBox'><a href='javascript:void(0);' class='jsSubView js_addL2Btn' title='添加子菜单' onclick='addSubmenu("+i+")'>" +
			"<span class='sub_pre_menu_inner js_sub_pre_menu_inner'>" +
			"<i class='icon16_common add_gray'></i></span></a></li>";
	submenu=submenu+"</ul>";
	submenu=submenu+"<i class='arrow arrow_out'></i> <i class='arrow arrow_in'></i>";
	submenu=submenu+"</div></div>";
	$("#menu_"+i).append(submenu);
}

/**校验*/
function callMyvalidate(){
	var needvalidate = true;
	if($("#menuname_text").val().trim()==""){
		alert("请输入菜单名称");
		return false;
	}
	if(!checkNameLength()){
		alert("菜单名称太长")
		return false;
	}
	var level = $("#level").val();//1:一级菜单，2：二级菜单
	if(level==1){
		var current_firstmenu = $("#current_firstmenu").val();//当前一级菜单索引
		/*console.info("level="+level+"current_firstmenu="+current_firstmenu);
		console.info($("#submenu_ul"+current_firstmenu+" li").length);*/
		var sublength = $("#submenu_ul"+current_firstmenu+" li").length;
		if(sublength>1){
			needvalidate = false;
		}
	}
	if(needvalidate){
		if($("input[name='hello']:checked").val()=="click"){
			if($("#keywordText").val().trim()==""){
				alert("请输入关键字");
				return false;
			}
		}else{
			if($("#urlText").val().trim()==""){
				alert("请输入url");
				return false;
			}
		}
	}
	return true;
}
function checkNameLength(){
	var name = $("#menuname_text").val();
	var textlength = name.length;
	var length = 0;
	var re = /[^u4e00-u9fa5]/; 
	for(var i=0;i<name.length;i++){
		var char = name[i];
		if(re.test(char)){
			length = length + 2;
		}else{
			length = length + 1;
		}
	}
	if($("#level").val()==1 && length>8){
		return false;
	}else if($("#level").val()==2 && length>16){
		return false;
	}
	return true;
}

/**设置页面右边的值*/
function setFirstmenuRight(i){
	$("#menuname_text").val($("#menu_"+i).text());
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	if(data.data==null){
            		return;
            	}
            	var menulist = data.data.buttons;
            	if(parseInt(i)+1<=menulist.length){//后台存在该菜单
            		var type = menulist[i].type;
            		if(type=="view"){
            			showUrl();
            			$("#urlText").val(menulist[i].url);
            		}else if(type=="click"){
            			showKeyword();
            			//$("#keywordText").val(menulist[i].key);
            			//$("#keywordText").find("option[value='2']").attr("selected",true);
            			setKeywordText(menulist[i].key);
            		}else{
            			showKeyword();
            			//$("#keywordText").val("");
            			$("#urlText").val("");
            		}
            	}
            }
        }
    });
}
function setSecondmenuRight(i,j){
	$("#menuname_text").val($("#"+i+"submenu_"+j).text());
	$.ajax({
        type : "get",
        dataType : "json",
        url : "WechatAPI/menuGet.html",
        success : function(data) {
            if(data.code==0){
            	if(data.data==null){
            		return;
            	}
            	var menulist = data.data.buttons;
            	if(parseInt(i)+1<=menulist.length){//后台存在该一级菜单
            		var menulisti = menulist[i];
        			var secondmenu = menulisti.subButtons;
        			if(secondmenu.length>=parseInt(j)+1){//后台存在该二级菜单
            			var type = secondmenu[j].type;
            			if(type=="view"){
                			showUrl();
                			$("#urlText").val(secondmenu[j].url);
                		}else if(type=="click"){
                			showKeyword();
                			//alert(secondmenu[j].key)
                			//$("#keywordText").val(secondmenu[j].key);
                			setKeywordText(secondmenu[j].key)
                		}else{
                			showKeyword();
                			$("#keywordText").val("");
                			$("#urlText").val("");
                		}
            		}
            	}
            }
        }
    });
}
function showKeyword(){
	$("#xiaoxi").addClass("selected");
	$("#xiaoxi_radio").attr("checked","checked");
	$("#tiaozhuan").removeClass("selected");
	$("#url").hide();
	$("#keyword").show();
}
function showUrl(){
	$("#xiaoxi").removeClass("selected");
	$("#tiaozhuan").addClass("selected");
	$("#tiaozhuan_radio").attr("checked","checked");
	$("#url").show();
	$("#keyword").hide();
}

function setKeywordText(keyword){
	$.ajax({
        type : "post",
        dataType : "json",
        async: false,
        url : "wechat/queryWechatReplyData.html",
        data: {iDisplayLength:"all"},
        success : function(data){
        	var result = "";
        	//添加页
        	result += "<option value=''>请选择关键字</option>";
            $.each(data.queryResult, function(index, value){
            	if(keyword == value.keyword){
            		result += "<option value="+value.keyword+" selected>"+value.keyword+"</option>";
            	}else{
            		result += "<option value="+value.keyword+">"+value.keyword+"</option>";
            	}
            });
            //console.log(result);
            $("#keywordText").empty().append(result).change();
    	}
        	
    });
}