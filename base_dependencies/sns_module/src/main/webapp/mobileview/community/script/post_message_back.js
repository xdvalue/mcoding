var _widthBox='';
var _width='';
var moduleId;
var pictUrlArray=[];
var i=0;
var localId=''; 
$(document).ready(function(){
    _widthBox = document.all.addBox.offsetWidth;
    _width=_widthBox-12;

    $("#addBox").css({
        'height': _widthBox + 'px'
    });
    $("#picture").css({
        'width': _width + 'px',
        'height': _width + 'px'
    });
    $("#addButton").css({
        'width': _width + 'px',
        'height': _width + 'px'
    });
    $("#photo").css({
        'width': _width + 'px',
        'height': _width + 'px'
    });
    document.getElementById("sbt").addEventListener('click', subImgHandler, false);
});
//by xiaohui
var serverId;
var count=9;
function changeImg() {
    pict3('#pictureShow0', function() {
    });
}
//微信sdk打开照相机  by xiaohui
function pict3(dom, callbackF) {
    //dom = 例如：'#img'   ————》img选择 作显示图片
    //callbackF = 拍照后调用的function
    wx.chooseImage({
        count: count, // 默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['camera', 'album'], // 可以指定来源是相册还是相机，默认二者都有
        success: function(res) {
            var _list='';
            $.map(res.localIds, function(localId) {
                _list +='<li class="bui_p_6" id="addImgBox" style="height:'+_widthBox+'px;position: relative;">'+
                                                '<div class="bui_radius" id="picture" style="width:'+_width+'px; height:'+_width+'px;" data-bui_img="" >'+
                                                    '<img src="'+ localId +'" id="pictureShow0" >'+
                                                '</div>'+
                                            '</li>';
            }); 
            console.log(res);
            if (callbackF) callbackF(res.localIds[0]);
            count=count-res.localIds.length;
                //res.localIds 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            if(count >= 0){
                $("#addBox").before(_list).find('[data-bui_img]').buijs_img();
                $('#addImg').find('[data-bui_img]').buijs_img();
            } 
            if(count == 0){
                $("#addBox").remove();    
            }
            for (var i = 0; i < res.localIds.length; i++) {
                wx.uploadImage({
                    localId: res.localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
                    isShowProgressTips: 1, // 默认为1，显示进度提示
                    success: function(arm) {
                        serverId = arm.serverId; // 返回图片的服务器端ID
                        pictUrlArray.push({wxImgId: arm.serverId});  
                    }
                }); 
            };
        }
    });
}

/**
 * 发表帖子按钮事件
 */
function subImgHandler(event){
    var _title = $("#title").val();
    var _mesContent = $("#mesContent").val();
    if (!$.trim(_title)) {
        buijs_alert({
            content: "请设立标题"
        });
        return
    }
    // else if (!moduleId) {
    //     buijs_alert({
    //         content: "请选择分类"
    //     });
    //     return
    // }
    else if (!$.trim(_mesContent)) {
        buijs_alert({
            content: "请填写帖子类容"
        });
        return
    }
    $.ajax({
        type: "post",
        url: _jsonUrl+ "snsPost/front/post",
        dataType: 'json',
        contentType:"application/json",
        data:JSON.stringify({
            title: _title,
            moduleId: 1,
            content: _mesContent,
            postImgs: pictUrlArray
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
        }
    })
};