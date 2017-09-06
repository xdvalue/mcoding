var _widthBox='';
var _width='';
var moduleData;
var moduleId;
var ImgIdA= 0; //记录照片位置
var i=0;
var pictUrlArray=[];
var deleteImgArray= [];
var uploadCtrl= true;
$(document).ready(function(){
    _widthBox = document.all.addImgBox.offsetWidth;
    _width=_widthBox-12;

    $("#addImgBox").css({
        'height': _widthBox + 'px'
    });
    $("#showImg0").css({
        'width': _width + 'px',
        'height': _width + 'px'
    });
    $("#photo").css({
        'width': _width + 'px',
        'height': _width + 'px'
    });
    document.getElementById("sbt").addEventListener('click', subImgHandler, false);

    getModels();
});


/*html5+canvas进行移动端手机照片上传    by xiaohui*/
function changeImg(){
    if(i<9){
        if (!uploadCtrl) return;
        uploadCtrl= false;
        $("#showImg"+i).parent("li").append('<div id="loading" class="bui_bgc_white_d48" style="opacity: 0.7;position: absolute;top: 0;left: 0;height: 100%;width: 100%"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_fac_white bui_p_12" style="position: absolute;left: 50%; margin-left: -25px;top: 30px"></i></div>');
        $.ajaxFileUpload({
            url:_jsonUrl+ "/attachment/front/upload",//处理图片脚本
            secureuri :false,
            fileElementId :'photo',//file控件id
            dataType : 'json',
            success :function(data){
               pictUrlArray.push({imgUrl: data.data.fileUrl});
               uploadCtrl= true;
                $("#showImg"+i)[0].src = _jsonUrl+ data.data.fileUrl;
                $("#addImgBox input").remove();
                  $("#loading").remove();
                i++;
                if(i < 9){
                    $("#deleteImg"+(i-1)).show();
                    $("#addImg").append('<li imgId='+i+' class=" bui_p_6" id="addImgBox" style="height:'+_widthBox+'px; position: relative;">'+
                                    '<span  onclick="deleteImg('+i+')" id="deleteImg'+i+'" style="display: none;position: absolute; z-index: 2; font-size: 20px; line-height: 18px;top: 6px; width: 20px; text-align: center;height: 20px;right: 7px;border-radius: 10px;background-color: red;color: white"><i class="fa fa-close"></i></span>' +
                                    '<img src="images/picture.jpg" id="showImg'+i+'" style="width:'+_width+'px; height:'+_width+'px;" />'+
                                    '<input type="file" name="file" id="photo" class="selectImg" style="width:'+_width+'px; height:'+_width+'px;" onchange="changeImg()" accept="image/*"/>'+
                                    '</li>');
                    $("#showImg"+i+"").on('click',function(){
                        
                    })
                }else{
                    $("#addImg").append('');
                }
            },
            error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        }
        });
    }
}

/*删去上传的图片*/
function deleteImg (id) {
    if (!uploadCtrl) return;
    $('[imgId='+id+']').remove();
    deleteImgArray.push(id);
}

function subImgHandler(event){
    var _title = $("#title").val();
    var _mesContent = $("#mesContent").val();
    if (!$.trim(_title)) {
        buijs_alert({
            content: "请设立标题"
        });
        return
    }else if (!$.trim(_mesContent)) {
        buijs_alert({
            content: "请填写帖子内容"
        });
        return
    }else if ($.trim(_mesContent).length<20) {
        buijs_alert({
            content: "帖子内容必须超过20字（不含空格）"
        });
        return;
    }
    var uploadImgArray=[];
    for (var i= 0;i<pictUrlArray.length; i++) {
        var k= true;
        for (var j= 0;j< deleteImgArray.length; j++) {
            if (i == deleteImgArray[j]){
                k= false;
                break;
            }
        }
        if (k) {
            uploadImgArray.push(pictUrlArray[i])
        }
    }
    _mesContent = _mesContent.replace(/\n/g, '<br/>');
    var choiceName = $("#choiceName").val();
    $.map(moduleData,function(item){
        if (choiceName == item.name){
            moduleId = item.id
        }
    })
    $.ajax({
        type: "post",
        url: _jsonUrl+ "snsPost/front/post",
        dataType: 'json',
        contentType:"application/json",
        data:JSON.stringify({
            title: _title,
            moduleId: moduleId || 1,
            moduleName: choiceName || "",
            content: _mesContent,
            postImgs: uploadImgArray
        }),
        success: function(data) {
            if (data.status == '00') {
                buijs_alert({
                    content: '发表成功',
                    time: 2000
                });
                setTimeout(function(){
                    window.location.href='./index.html';
                },1000);
            }else {
                buijs_alert({
                    content: data.msg
                });
            }
        },
        error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        }
    })
};

function getModels(){
    $.ajax({
        type: 'get',
        url: _jsonUrl+ 'snsModule/front/findAllModules',
        dataType: 'json',
        success: function(data) {
            var _html="";
            moduleData = data.data;
            $.map(moduleData,function(item){
                _html+= '<option>'+item.name+'</option>'
            }) 
            $("#choiceName").append(_html);
        },
        error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        }
    });
}

