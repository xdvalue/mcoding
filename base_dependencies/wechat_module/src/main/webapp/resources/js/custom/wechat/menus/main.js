/**
 * 菜单管理，使用数据驱动的方式，界面的刷新，根据加载数据来操作。
 * 保存一份全局数据 data，执行方法loadData(data)，就把数据刷入界面。因此界面的所有操作，都是通过改动数据，然后加载数据来完成。
 * 界面分成三个组件，左侧 一级菜单组件(ButtonView)，二级菜单组件(SubButtonView)，右侧 菜单信息展示组(ButtonInfoView)。
 */

var MainMng = (function(){
	var rootMenu = $('#menuList');
	
	var data = {};
		
	/**初始化方法**/
	var init = function(){
		
		//1、获取数据并加载到界面
		$.getJSON("wechatMenu/service/menuGet?originId="+$('#originId').val(), function(json){
			if(json.status != '00'){
				bootbox.alert("查询菜单数据失败");
			}
			initData(json.data);
			loadData(data);
		});
		
		//2、添加保存的监听事件
		$('#pubBt').click(function(){
			saveHandler();
		});
	};
	
	/**加载数据到界面**/
	var loadData = function(data){
		rootMenu.empty();
		var menus = data.menus;
		if(!menus || menus.buttons.length <=0){
			return;
		}
		//1、加载左侧的一级菜单：生成菜单组件对象，并加载到页面
		$.each(menus.buttons, function(index, button){
			
			button.id = index + '';
			if(button.subButtons){
				button.isFull = button.subButtons.length >=5;
			}else{
				button.isFull = false;
			}
			
			var isSelected = false;
			if(data.selected.button == null && button.name){
				//如果初始化时，没有选中的按钮，就把第一个按钮设置为已选中
				data.selected.button = button;
				isSelected = true;
			}else if(data.selected.button !=null && data.selected.button.id == button.id){
				isSelected = true;
			}
			
			//生成菜单对象，并把对象加载到页面
			var buttonView = new ButtonView({
				parent: rootMenu,
				button: button,
				onClick: clickButtonHandler,
				onSubButtonClick: clickSubButtonHandler,
				isSelected: isSelected
			});
			
			buttonView.init();
		});
		
		//2、加载右侧的菜单内容
		ButtonInfoView.loadData({
			selected:data.selected,
			onDelete:deleteHandler
		});
	};
	
	/**一级菜单的点击处理器**/
	var clickButtonHandler = function(button){
		data.selected.isSub = false;
		data.selected.button = button;
		data.selected.subButton = null;
		loadData(data);
	}
	
	/**二级菜单的点击处理器**/
	var clickSubButtonHandler = function(subButton){
		data.selected.isSub = true;
		data.selected.subButton = subButton;
		loadData(data);
	}
	
	/***保存按钮的处理器*/
	var saveHandler = function(){
		
		var button = ButtonInfoView.getSelectedButton();
		if(!button || !button.name || button.name.length == 0 || !button.type || 
				((!button.key|| button.key.length==0) && (!button.url || button.url.length==0) && data.selected.isSub)
			){
			bootbox.alert("请填写内容");
			return;
		}
		
		if(!data.selected.isSub){
			if(!button.id || button.id == ''){
				data.menus.buttons.push(button);
			}else{
				data.selected.button.type = button.type;
				data.selected.button.key = button.key;
				data.selected.button.url = button.url;
				data.selected.button.name = button.name;
			}
			
		}else{
			if(!button.id || button.id == ''){
				data.selected.button.subButtons.push(button);
			}else{
				data.selected.subButton.type = button.type;
				data.selected.subButton.key = button.key;
				data.selected.subButton.url = button.url;
				data.selected.subButton.name = button.name;
			}
			
		}
		updateDataToServer();
		
	};
	
	/***删除按钮的处理器*/
	var deleteHandler = function(){
		
		if(!data.selected.isSub){
			data.menus.buttons = $.grep( data.menus.buttons, function(button, i){
		        return button.id != data.selected.button.id;
			});
		}else{
			var index = -1;
			$.each(data.selected.button.subButtons, function(i, subButton){
				if(subButton.id == data.selected.subButton.id){
					index = i;
				}
			});
			data.selected.button.subButtons.splice(index, 1);
			if(data.selected.button.subButtons.length==0){
				data.selected.button.type='click';
				data.selected.button.key= 'click';
				delete data.selected.button.subButtons;
			}
		}
		updateDataToServer();
	}
	
	/**更新数据到服务器*/
	var updateDataToServer = function(){
		if(data.menus && data.menus.buttons){
			data.menus.buttons = $.grep( data.menus.buttons, function(button, i){
		        return button.name;
			});
		}
		App.blockUI({target: '.portlet-body form', boxed: true});
		$.ajax("wechatMenu/service/create?originId="+$('#originId').val(),{
			type:'post',
			contentType:'application/json',
			data : JSON.stringify(data.menus),
			dataType:'json',
			success:function(json){
				App.unblockUI('.portlet-body form');
				if(json.status != '00'){
					bootbox.alert(" 操作失败");
					return;
				}
				bootbox.alert(" 操作成功");
				initData(json.data);
				loadData(data);
			},
			error:function(){
				App.unblockUI('.portlet-body form');
				bootbox.alert(" 操作失败");
			}
		});
	}
	
	/**初始化数据**/
	var initData = function(jsonData){
		data = {
			menus :{ //所有的菜单数据
		    	buttons:[]
		    },
			selected : { //选中的菜单
				isSub:false,    //是否子菜单
				button:null,    //菜单内容
				subButton:null  //子菜单内容
			}	
		};
		if(jsonData == null){
			var plusButton = {}; //最后有一个添加按钮 "+"
			data.menus.buttons.push(plusButton);
			return;
		}
		
		//插入按钮数据
		jsonData = jsonData.menu;
		if(jsonData && jsonData.buttons){
			$.each(jsonData.buttons, function(i, button){
				data.menus.buttons.push(button);
			});
		}
		var plusButton = {}; //最后有一个添加按钮 "+"
		data.menus.buttons.push(plusButton);
		
//		data.menus.buttons.reverse();
	}
	
	return {
		init :init
	};
})();


