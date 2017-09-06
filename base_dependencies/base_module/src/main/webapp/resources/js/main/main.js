/**
 * 初始化主页的数据
 */
var oTable = null;
var ul = "noticeId";
var userid = $("#userId").val();

var noticeList = function() {
    //Ajax提交查询消息
    var initEvent = function() {
    	//清空一个通知
    	$(".gritter-item-wrapper").empty();
    	/*//设置cookie，作用于此提示框在此浏览只弹出一次
    	if ($.cookie('intro_show')) {
            return;
        }
        $.cookie('intro_show', 1);*/
        $.ajax({
            type: "post",
            dataType: "json",
            url: "queryByReceiveNotifications.html",
            success: function(data) {
                //设置警告框位置
                $.extend($.gritter.options, {
                    position: 'bottom-right',
                });

                $.each(data,function(index, item) {
                	//定义notificationsType为null
                	var notificationsType = null;
                	//判断通知公告的类型
                	if(item.noticeType == 1){
                		notificationsType = "行政公告";
                	} else if(item.noticeType == 2){
                		notificationsType = "任务公告";
                	} else if(item.noticeType == 3){
                		notificationsType = "项目公告";
                	}
                    setTimeout(function() {
                        var unique_id = $.gritter.add({
                            // 通知的标题
                            title: "<label>" + notificationsType + "<label>",
                            // 通知的文本的链接
                            text:"<a class='noticeAjaxify' href='queryNotificationsType.html?notificationsId= " + item.notificationsId + " '> " + item.noticeTitle + " </a>",
                            /*// 通知的图片在左边
                            image: 'resources/images/manlogo.png',*/
                            // 渐渐淡出
                            sticky: true,
                            // 淡出时间,一般为空,不在这里设置
                            time: '',
                            // 通知的class
                            class_name: 'my-sticky-class'
                        });
                        
                        //当点击通知模板链接便触发这个function清除当前的通知模板
                        $(".noticeAjaxify").click(function(){
                        	$.gritter.remove(unique_id, {
                                fade: true,
                                speed: 'slow'
                            });
                        });
                        
                        // 设置提示框退出的时间
                        setTimeout(function() {
                            $.gritter.remove(unique_id, {
                                fade: true,
                                speed: 'slow'
                            });
                        },
                        300000);
                    });
                    
                    
                });
            }
        });
    };
    
 $("#selectpeople").on("show.bs.modal", function(e){
    	
    });
    $("#selectpeopelName").on("click", function(){
        var peopelName = $("#peopelName").val();
        $.ajax({
            type: "post",
            url: 'selectPeople.html',
            data : {"peopelName" : peopelName},
		    
            success: function (msg) {
            var $tb = $("#peoplelist").find("tbody").empty();	
            if(msg.mobilePhone !=null && msg.email !=null && msg.qq !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+msg.mobilePhone+"</th><th>"+msg.qq+"</th><th>"+msg.email+"</th></tr>");  
            }else if(msg.mobilePhone !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+msg.mobilePhone+"</th><th>"+''+"</th><th>"+''+"</th></tr>");  
            }else if(msg.qq !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+''+"</th><th>"+msg.qq+"</th><th>"+''+"</th></tr>");
            }else if(msg.email !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+''+"</th><th>"+''+"</th><th>"+msg.email+"</th></tr>");  	
            }else if(msg.mobilePhone !=null && msg.email){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+msg.mobilePhone+"</th><th>"+''+"</th><th>"+msg.email+"</th></tr>");
            }else if(msg.email !=null && msg.qq !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+''+"</th><th>"+msg.qq+"</th><th>"+msg.email+"</th></tr>");
            }else if(msg.mobilePhone !=null && msg.qq !=null){
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+msg.mobilePhone+"</th><th>"+''+"</th><th>"+msg.email+"</th></tr>");  
            }else{
            	$tb.append("<tr id='trabcd'><th>"+msg.nickName+"</th><th>"+msg.loginName+"</th><th>"+''+"</th><th>"+''+"</th><th>"+''+"</th></tr>");  		
            }
            }
        });
    });
    
    //ajax提交查询公告
    var loadNoticeData = function() {
        // ajax提交函数查询公告栏
        $.ajax({
            type: "post",
            dataType: "json",
            url: "getNotifications.html",
            data: { 
            	"noticeType": $("#noticeTypes").val(),
            },
            success: function(data) {
                $("#noticeId").empty();
                var result = "";
                /* result += "<li value=''>公告标题</option>"; */
                $.each(data,function(index, value) {
                	
                    if (value.noticeImportance == 0) {
                        $("#noticeId").append("<li><div class='col1'><div class='cont'><div class='cont-col1'><div class='label label-sm label-success'><i class='fa fa-bullhorn'></i></div></div><div class='cont-col2'><div class='desc'><a class='noticeAjaxify' href='queryNoticeDetail.html?noticeId=" + value.noticeId + "'>" + value.noticeTitle + "</a><span class='label label-sm label-success' style='margin-left:12px;'>一般</span><span class='task-bell'><i class='fa fa-bell-o'></i></span></div></div></div><span style='float: right;'> " + changeDateFormat(value.createTime) + " </span></div></li>");
                    } else if (value.noticeImportance == 1) {
                        $("#noticeId").append("<li><div class='col1'><div class='cont'><div class='cont-col1'><div class='label label-sm label-warning'><i class='fa fa-bullhorn'></i></div></div><div class='cont-col2'><div class='desc'><a class='noticeAjaxify' href='queryNoticeDetail.html?noticeId=" + value.noticeId + "'>" + value.noticeTitle + "</a><span class='label label-sm label-warning' style='margin-left:12px;'>重要</span><span class='task-bell'><i class='fa fa-bell-o'></i></span></div></div></div><span style='float: right;'> " + changeDateFormat(value.createTime) + " </span></div></li>");
                    } else if (value.noticeImportance == 2) {
                        $("#noticeId").append("<li><div class='col1'><div class='cont'><div class='cont-col1'><div class='label label-sm label-danger'><i class='fa fa-bullhorn'></i></div></div><div class='cont-col2'><div class='desc'><a class='noticeAjaxify' href='queryNoticeDetail.html?noticeId=" + value.noticeId + "'>" + value.noticeTitle + "</a><span class='label label-sm label-danger' style='margin-left:12px;'>紧急</span><span class='task-bell'><i class='fa fa-bell-o'></i></span></div></div></div><span style='float: right;'> " + changeDateFormat(value.createTime) + " </span></div></li>");
                    }
                });

                $("#noticeId").append(result);
            }
        });
    };
    
  //添加文件共享
    var loadShareFileData = function() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "loadShareFileData.html",
            success: function(data) {
                $("#fileShareList").empty();
                var result = "";
                $.each(data,function(index, value) {
                      $("#fileShareList").append("<li><div class='task-title'><span class='task-title-sp'><i class='fa fa-file'></i><a target='_blank' href='"+value.upFullpath+"'>"+value.upTitle+"</a></span><span class='label label-sm label-success'></span></div>");
                });
                $("#fileShareList").append(result);
            }
        });
    };

    // 添加公告，vilidation表单验证
    var noticeVal = function() {
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-danger', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span',
            // default input error message container
            errorClass: 'help-block',
            // default input error message class
            focusInvalid: false,
            // do not focus the last invalid input
            ignore: "",
            rules: {
                noticeTitle: {
                    minlength: 5,
                    maxlength: 20,
                    required: true
                },
                noticeContent: {
                    required: true
                },
                noticeImportance: {
                    required: true
                }
            },

            invalidHandler: function(event, validator) { // display error
                success1.hide();
                error1.show();
                App.scrollTo(error1, -200);
            },

            highlight: function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
            },

            unhighlight: function(element) { // revert the change done by
                // hightlight
                $(element).closest('.form-group').removeClass('has-error'); // set
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error'); // set
            },

            submitHandler: function(form) {
                success1.show();
                error1.hide();
                onClickNotice();
            }
        });

    };
    
    //添加共享文件，vilidation表单验证
    var shareFileVal = function() {
        var form2 = $('#ShareFileForm');
        var error1 = $('#ShareFileForm .alert-danger', form2);
        var success1 = $('#ShareFileForm .alert-success', form2);

        form2.validate({
            errorElement: 'span',
            // default input error message container
            errorClass: 'help-block',
            // default input error message class
            focusInvalid: false,
            // do not focus the last invalid input
            ignore: "",
            rules: {
            	upTitle: {
                    minlength: 2,
                    required: true
                },
                upFullpath: {
                    required: true
                }
            },

            invalidHandler: function(event, validator) { // display error
                success1.hide();
                error1.show();
                App.scrollTo(error1, -200);
            },

            highlight: function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
            },

            unhighlight: function(element) { // revert the change done by
                // hightlight
                $(element).closest('.form-group').removeClass('has-error'); // set
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error'); // set
            },

            submitHandler: function(form) {
                success1.show();
                error1.hide();
                saveShareFile();
            }
        });

    };

    /**
	 * 集团销售数据统计图
	 */
    var groupnotice = function() {
        $.ajax({
            type: "post",
            url: "groupTaskSellStatement.html",
            success: function(msg) {
                var name = new Array();
                var newtaskMoney = new Array();
                var newrealMoney = new Array();
                for (var i = 0; i < msg.length; i++) {
                    newrealMoney.push(msg[i].realMoney);
                    newtaskMoney.push(msg[i].taskMoney);

                    name.push(msg[i].username);
                }
                $('#groupcontainer').highcharts({
                    chart: {
                        renderTo: 'groupcontainer',
                        type: 'bar'
                    },
                    title: {
                        text: '集团销售统计图'
                    },
                    subtitle: {
                        text: ' 集团销售额排行榜'
                    },
                    xAxis: {
                        categories: name,
                        title: {
                            text: "姓名"
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '销售商品数',
                            align: 'high'
                        },
                        labels: {
                            overflow: 'justify'
                        }
                    },
                    tooltip: {
                        valueSuffix: '元'
                    },
                    plotOptions: {
                        bar: {
                            dataLabels: {
                                enabled: false
                            }
                        }
                    },

                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -40,
                        y: 100,
                        floating: true,
                        borderWidth: 1,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
                        shadow: true
                    },
                    credits: {
                        enabled: false
                    },
                    series: [{
                        name: '计划销售商品数',
                        data: newtaskMoney
                    },
                    {
                        name: '实际销售商品数',
                        data: newrealMoney
                    }]
                });
            }
        });
    };

    return {
        init: function() {
            initEvent(); //提示框
            loadNoticeData();//初始化加载公告查询方法
            loadShareFileData();//初始化加载公告查询方法
            noticeVal(); // 初始化加载表单验证
            shareFileVal();
            /* notice();*/
            groupnotice();
            userMessage(); //初始化留言列表
            noticeUnread();//初始化公告通知未读
            taskUnread();
            messageUnread();//初始化message列表
        },
        noticeUnread : noticeUnread,
        taskUnread : taskUnread
    };

} ();

//留言
var userMessage = function() {
	var list = $('.chats');
    $("#listempty").empty();
    $.ajax({
        type: "POST",
        url: "queryMessageLists.html",
        dataType: "json",
        success: function(data) {
        	var items=data.queryResult;
            $.each(items,
            function(i, n) {
                var tpl = '';
                tpl += '<li class="' + (items[i].senduserid == userid ? 'out': 'in') + '">';
                tpl += '<img class="avatar" alt="" src="resources/images/manlogo.png"/>';
                tpl += '<div class="message">';
                tpl += '<span class="arrow"></span>';
                tpl += '<a class="name"> ' + items[i].nickName + ' </a>&nbsp;';
                tpl += '<span class="datetime">' + changeDateFormat(items[i].createtime) + '</span>';
                tpl += '<span class="body">' + items[i].messagecontent + '</span>';
                tpl += '</div>';
                tpl += '</li>';
                list.append(tpl);
            });
        }
    });
};

//右上角公告通知未读
var noticeUnread = function() {
	var noticeList = $("#noticeUnread");
	$("#noticeUnread").empty();
	$.ajax({
		type: "POST",
        url: "queryByUnreadNotifications.html",
        dataType: "json",
        success: function(data) {
            var items = data;
            $.each(items,
            function(i, n) {
                var notice = '';
            	notice += '<li>';
            	notice += '<a class="noticeAjaxify" href="queryNotificationsType.html?notificationsId= '+ items[i].notificationsId +'" >';
                notice += '<span class="label label-sm label-icon label-danger">';
                notice += '<i class="fa fa-bullhorn"></i>';
            	notice += '</span>';
            	notice += items[i].noticeTitle;
            	notice += '</a>';
            	notice += '</li>';
            	noticeList.append(notice);
            });
        }
	});
};

/*//右上角公告留言未读
var messageUnread = function() {
	var messageList = $("#messageUnread");
	$("#messageUnread").empty();
	$.ajax({
		type: "POST",
        url: "queryByUnreadTask.html",
        dataType: "json",
        success: function(data) {
            var items = data;
            $.each(items,
            function(i, n) {
            	var status = '';
                if(items[i].taskStatus == "init"){
                	status = "<span class='label label-info'>提出需求</label>";
                }else if(items[i].taskStatus == "suspend"){
                	status = "<label class='label label-danger'>暫停或延期</label>";
                }else if(items[i].taskStatus == "run"){
                	status = "<label class='label label-success'>进行中</label>";
                }else if(items[i].taskStatus == "finish"){
                	status = "<label class='label label-warning'>已完成</label>";
                }else if(items[i].taskStatus == "cancel"){
                	status = "<label class='label label-default'>已取消</label>";
                }else if(items[i].taskStatus == "invalid"){
                	status = "<span class='label label-primary'>过期未提交</span>";
                }else if(items[i].taskStatus == "checking"){
                	status = "<span class='label label-primary'>审核中</span>";
                }else{
                	status = "<span class='label label-primary'>未知状态</span>";
                }
            	
                var task = '';
                task += '<li>';
                task += '<a class="ajaxify task-title" href="navigateTaskMessage.html?taskId= '+ items[i].taskId +'" >';
                task += '<span class="task-title-sp">';
                task += '<i class="fa fa-tasks"></i>';
                task += items[i].taskName;
                task += '</span>';
                task += status;
                task += '</a>';
                task += '</li>';
                messageList.append(task);
            });
        }
	});
};*/

//右上角任务通知
var taskUnread = function() {
	var taskList = $("#taskUnread");
	$("#taskUnread").empty();
	$.ajax({
		type: "POST",
        url: "queryByUnreadTask.html",
        dataType: "json",
        success: function(data) {
        	
            var items = data;
            $.each(items,
            function(i, n) {
            	var status = '';
                if(items[i].taskStatus == "init"){
                	status = "<span class='label label-info'>提出需求</label>";
                }else if(items[i].taskStatus == "suspend"){
                	status = "<label class='label label-danger'>暫停或延期</label>";
                }else if(items[i].taskStatus == "run"){
                	status = "<label class='label label-success'>进行中</label>";
                }else if(items[i].taskStatus == "finish"){
                	status = "<label class='label label-warning'>已完成</label>";
                }else if(items[i].taskStatus == "cancel"){
                	status = "<label class='label label-default'>已取消</label>";
                }else if(items[i].taskStatus == "invalid"){
                	status = "<span class='label label-primary'>过期未提交</span>";
                }else if(items[i].taskStatus == "checking"){
                	status = "<span class='label label-primary'>审核中</span>";
                }else{
                	status = "<span class='label label-primary'>未知状态</span>";
                }
            	
                var task = '';
                task += '<li>';
                task += '<a class="ajaxify task-title" href="navigateTaskMessage.html?taskId= '+ items[i].taskId +'" >';
                task += '<span class="task-title-sp">';
                task += '<i class="fa fa-tasks"></i>';
                task += items[i].taskName;
                task += '</span>';
                task += status;
                task += '</a>';
                task += '</li>';
            	taskList.append(task);
            });
        }
	});
};

// ajax提交from表单
function onClickNotice() {
	if(!$('#form_sample_1').valid()){
        return;
    }
    $.post("addNotice.html", $("#form_sample_1").serialize(),
    function(data) {
        if (data != null) {
            alert("保存成功！");
            $("#closeModal").click();
            noticeList.init(); // 刷新一次
            //添加数据成功，右上角会显示数字
            var noticeCountDiv = $("#noticeNumber");
            var noticeCount = parseInt(noticeCountDiv.text())+1; 
            noticeCountDiv.text(noticeCount);
            var unreadNoticeConnt = $("#unread");
            var unreadNotice = parseInt(unreadNoticeConnt.text())+1;
            unreadNoticeConnt.text(unreadNotice);
            
        } else {
            alert("保存失败");
        }
    });
}

//保存共享文件
function saveShareFile() {
	var form = $("#ShareFileForm");
    if(!form.valid()){
        return;
    }
    $.post("addShareFile.html", $("#ShareFileForm").serialize(),
    function(data) {
        if (data.code == "0") {
            $("#closeModal1").click();
            bootbox.alert("增加成功!");
            noticeList.init(); // 刷新一次
        } else {
            alert("保存失败");
        }
    });
}

// ajax提交from表单之前给noticeType赋值
var m_type=null;
function modify_type(){
	m_type=1;
	$("#noticeType").attr("value",m_type);
}



// ajax提交留言表单
function messageSend() {
    var messagecontent = $("#messagecontent").val();
    if (messagecontent.replace(/[ ]/g,"")=='' || messagecontent == null) {
        alert("留言内容不能为空");
        return;
    }
    $.post("addMessage.html", $("#messageForm").serialize(),
    function(result) {
        if (result.code == "0") {
            //userMessage();
            //noticeList.userMessage();
            noticeList.init(); //保存成功初始化列表
        }
    });
    clean("#messageForm"); //清空输入框内容
}

function clean(formname) {
    $(':input', formname).not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
}

