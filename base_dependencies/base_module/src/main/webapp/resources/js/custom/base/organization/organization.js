//集团架构的增删改管理
var ORG_OrganizationManager = function(){
    //初始化相关事件
    var initEvent = function(){
        //新建架构主管事件
        $("#addNewOrgLeader").click(function(){
            var leaderDiv = $("#addOrgLeader");
            if(leaderDiv.is(":visible")){
                leaderDiv.hide();
            }else{
                leaderDiv.show();
            }
        });

        //选择省份事件
        $("#regionProvId").on("change", function(){
            var regionId = $(this).val();
            if(regionId==''){
                return;
            }
            $.ajax({
                type : "post",
                dataType : "json",
                url : "queryRegionByParentId",
                data : {parentRegionId : regionId},
                success : function(data){
                    var result = "";
                    result += "<option value=''>请选择城市</option>";
                    $.each(data, function(index, value){
                        var editRegionCityId = $("#editRegionCityId").val();
                        if(editRegionCityId == value.id){
                            result += "<option value=\"" + value.id + "\" selected=\"selected\">" + value.name + "</option>";

                        } else {
                            result += "<option value=\"" + value.id + "\">" + value.name + "</option>";
                        }
                    });
                    $("#regionCityId").empty().append(result).change();
                    $("#regionAreaId").empty();
                }
            })
        });

        //选择城市事件
        $("#regionCityId").on("change", function(){
            var regionId = $(this).val();
            if(regionId==''){
                return;
            }
            $.ajax({
                type : "post",
                dataType : "json",
                url : "queryRegionByParentId",
                data : {parentRegionId : regionId},
                success : function(data){
                    var result = "";
                    result += "<option value=''>请选择区域</option>";
                    $.each(data, function(index, value){
                        var editRegionAreaId = $("#editRegionAreaId").val();
                        if(editRegionAreaId == value.id){
                            result += "<option value=\"" + value.id + "\" selected=\"selected\">" + value.name + "</option>";
                        } else {
                            result += "<option value=\"" + value.id + "\">" + value.name + "</option>";
                        }
                    });
                    $("#regionAreaId").empty().append(result);
                }
            })
        });

        //选择架构级别类型事件
        $("#levelType").on("change", function(){
            var levelType = $(this).val();
            if(levelType==''){
                return;
            }
            $.ajax({
                type : "post",
                dataType : "json",
                url : "queryLevelByType.html",
                data : {levelType : levelType},
                success : function(data){
                    var result = "";
                    result += "<option value=''>请选择职位</option>";
                    $.each(data, function(index, value){
                        if(levelId == value.levelId){
                            result += "<option value=\"" + value.levelId + "\" selected=\"selected\">" + value.levelName + "</option>";
                        } else {
                            result += "<option value=\"" + value.levelId + "\">" + value.levelName + "</option>";
                        }
                        $("#levelId").empty().append(result);
                    });
                }
            });
        });

        //保存架构
        $("#addOrgBut").on("click", function(){
            var orgForm = $("#orgForm");
            if(!orgForm.valid()){
                return;
            }
            var requestUrl = "addOrg.html";
            //判断是新增还是修改
            if($("#editOrgId").val() != ""){
                requestUrl = "modifyOrg.html";
            }

            $.ajax({
                type : "post",
                dataType : "json",
                url : requestUrl,
                data : orgForm.serialize(),
                success : function(data){
                    if(data.code==0){
                        $("#backListPage").click();
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
                orgName: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                },
                parentOrgName: {
                    minlength: 2,
                    maxlength: 24,
                    required: true
                },
                regionProvId: {
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

        //验证增加角色表单
        $("#orgForm").validate(options);

        $("#regionProvId").change();
    };

    return {
        init : function (){
            initEvent();
        }
    };
}();
