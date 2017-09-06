var Event_Calendar = function () {
    var myCalenderId = "calendar";
    var myDialogId = "AddEventModal";

    //初始化加载日历数据的参数定义
    var initTableHeaderInfo = function() {
        var options = {
            slotMinutes: 15,
            lang: 'zh-cn',
            currentTimezone: 'Asia/Beijing',
            selectable: true,
            selectHelper: true,
            //select: addEvent,
            //editable: true,
            //eventDrop: dropEvent,
            //eventResize: dropEvent,
            eventClick: updateEvent,
            events: queryUserEvent
        };
        if(App.checkUserOperRight("myCalendar", "addEvent")){
            options.select = addEvent
        }
        if(App.checkUserOperRight("myCalendar", "modifyEvent")){
            options.editable = true,
            options.eventDrop = dropEvent,
            options.eventResize = dropEvent
        }

        return options;
    };

    //初始化日历
    var initCalendar = function () {
        if (!jQuery().fullCalendar) {
            return;
        }
        var cal = $('#' + myCalenderId);
        var h = {};
        if (App.isRTL()) {
            if (cal.parents(".portlet").width() <= 720) {
                cal.addClass("mobile");
                h = {
                    center: '',
                    right: 'agendaDay, agendaWeek, month, today'
                };
            } else {
                cal.removeClass("mobile");
                h = {
                    right: 'title',
                    center: '',
                    left: 'agendaDay, agendaWeek, month, today, prev,next'
                };
            }
        } else {
            if (cal.parents(".portlet").width() <= 720) {
                cal.addClass("mobile");
                h = {
                    left: 'title, prev, next',
                    center: '',
                    right: 'today,month,agendaWeek,agendaDay'
                };
            } else {
                cal.removeClass("mobile");
                h = {
                    left: 'title',
                    center: '',
                    right: 'prev,next,today,month,agendaWeek,agendaDay'
                };
            }
        }
        cal.fullCalendar('destroy');
        var opts = initTableHeaderInfo();
        opts.header = h;
        cal.fullCalendar(opts);
    };

    //增加事件
    var addEvent = function (start, end) {

        var curView = $("#" + myCalenderId).fullCalendar('getView');
        //初始化默认值
        if (curView.name == "month") {
            $("#start").val(start.format("YYYY-MM-DD"));
            $("#end").val(end.format("YYYY-MM-DD"));
        } else {
            $("#start").val(start.format("YYYY-MM-DD"));
            $("#end").val(end.format("YYYY-MM-DD"));
        }
        $("#" + myDialogId).find("h4").text("添加事件");
        $("#deleteEvent").hide();
        $("#modifyEvent").hide();
        $("#addEvent").show();

        bindFormEvent();
        $("#" + myDialogId).modal("show");
    };

    //拖拽事件
    var dropEvent = function (event, delta, revertFunc) {
        if (event.types == "task") {
            revertFunc();
            return;
        }
        var newData = {};
        newData.id = event.id;
        var dateFormat = "YYYY-MM-DD";
        if (event.allDay) {
            dateFormat = "YYYY-MM-DD";
        }
        newData.start = event.start.format(dateFormat);
        if (event.end) {
            newData.end = event.end.format(dateFormat);
        }
        $.ajax({
            type: "post",
            data: newData,
            url: 'modifySummary.html',
            success: function (data) {
                if (data.code == 0) {
                    //$('#calendar').fullCalendar('refetchEvents');
                } else {
                    revertFunc();
                }
            }
        });
    };

    //修改事件
    var updateEvent = function (calEvent, jsEvent, view) {
        bindFormEvent(calEvent);
        $("#" + myDialogId).modal("show");
    };

    //绑定form表单事件
    var bindFormEvent = function (calEvent) {
        if (!jQuery().datetimepicker) {
            return;
        }
        $("#id").val('0');

        //弹出框相关事件
        $("#AddEventModal").on("hidden.bs.modal", function(e){
            $("#title").val('');
            $("#id").val('0');
            $("#start").val('');
            $("#end").val('');
        });

        //初始化滑动条相关事件
        $('.make-switch').on('switch-change', function (e, data) {
            if (data.value) {
                calendarBindEvent("date");
            } else {
                calendarBindEvent("datetime");
            }
        });


        var curView = $("#" + myCalenderId).fullCalendar('getView');
        var dateFormat = "YYYY-MM-DD";
        if (curView.name == "month") {
            $('.make-switch').bootstrapSwitch("setState", true);
            dateFormat = "YYYY-MM-DD";
            calendarBindEvent("date");
        } else {
            $('.make-switch').bootstrapSwitch("setState", false);
            calendarBindEvent("datetime");
        }

        //修改状态
        if (calEvent) {
            $("#id").val(calEvent.id);
            $("#title").val(calEvent.title);
            $("#start").val(calEvent.start.format(dateFormat));
            if (calEvent.end) {
                $("#end").val(calEvent.end.format(dateFormat));
            } else {
                $("#end").val('');
            }
            if (calEvent.allDay == true) {
                $('.make-switch').bootstrapSwitch("setState", true);
            } else {
                $('.make-switch').bootstrapSwitch("setState", false);
            }

            //判断是否具有修改权限
            if(App.checkUserOperRight("myCalendar", "modifyEvent")){
                if (calEvent.types == "task") {
                    $("#" + myDialogId).find("h4").text("查看事件");
                    $("#deleteEvent").hide();
                    $("#addEvent").hide();
                    $("#modifyEvent").hide();
                } else {
                    $("#" + myDialogId).find("h4").text("修改事件");
                    $("#deleteEvent").show();
                    $("#addEvent").hide();
                    $("#modifyEvent").show();
                }
            }else{
                $("#" + myDialogId).find("h4").text("查看事件");
                $("#deleteEvent").hide();
                $("#addEvent").hide();
                $("#modifyEvent").hide();
            }

            if(App.checkUserOperRight("myCalendar", "deleteEvent")){
                $("#deleteEvent").show();
            }
        }
        
        //注册表单验证
        var evenOptions = {
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	summaryText: {
                    maxlength: 120,
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
        };

        //绑定增加按钮事件
        $("#addEvent").unbind('click').bind('click', function () {
        	 $("#eventAddForm").validate(evenOptions);
        	 if(!$("#eventAddForm").valid()){
                 return;
             }
            $.ajax({
                type: "post",
                data: $("#eventAddForm").serialize(),
                url: "addSummary.html",
                success: function (data) {
                    if (data.code == 0) {
                        $('#' + myCalenderId).fullCalendar('refetchEvents');
                        $("#" + myDialogId).modal("hide");
                    }
                }
            });
        });

        //绑定修改按钮事件
        $("#modifyEvent").unbind('click').bind('click', function () {
        	$("#eventAddForm").validate(evenOptions);
        	if(!$("#eventAddForm").valid()){
                return;
            }
            $.ajax({
                type: "post",
                data: $("#eventAddForm").serialize(),
                url: "modifySummary.html",
                success: function (data) {
                    if (data.code == 0) {
                        $('#' + myCalenderId).fullCalendar('refetchEvents');
                        $("#" + myDialogId).modal("hide");
                    }
                }
            });
        });

        //绑定删除按钮事件
        $("#deleteEvent").unbind('click').bind('click', function () {
            $.ajax({
                type: "post",
                data: {id: calEvent.id},
                url: "deleteSummary.html",
                success: function (data) {
                    if (data.code == 0) {
                        $('#' + myCalenderId).fullCalendar('refetchEvents');
                        $("#" + myDialogId).modal("hide");
                    }
                }
            });
        });
    };

    //绑定日历事件
    var calendarBindEvent = function (type) {
        $(".form_datetime").datetimepicker('remove');
        $(".form_datetime").datepicker('remove');
        if (type == "datetime") {
            $(".form_datetime").datetimepicker({
                language: "zh-CN",
                autoclose: true,
                isRTL: App.isRTL(),
                format: "yyyy-mm-dd",
                pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
            });
        } else {
            $(".form_datetime").datepicker({
                language: "zh-CN",
                autoclose: true,
                isRTL: App.isRTL(),
                format: "yyyy-mm-dd",
                pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
            });
        }
    };

    //查询后台事件数据
    var queryUserEvent = function () {
        var cal = $("#" + myCalenderId);
        var moment = cal.fullCalendar('getDate');
        var param = {"crrDate": moment.valueOf()};
        $.ajax({
            type: "POST",
            data: param,
            url: "querySummaryCalendarData.html",
            success: function (data) {
                if (data.code == 0) {
                    cal.fullCalendar('removeEvents');
                    $.each(data.data, function (index, event) {
                        cal.fullCalendar('renderEvent', event, true);
                    });
                }
            }
        });
    };

    return {
        initCalendar: initCalendar
    };

}();