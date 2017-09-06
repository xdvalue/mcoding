var _widthBox='';
var _width='';
var moduleId;
var i=0;
var pictUrlArray=[]; 
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
    isCanvasSupported();
    document.getElementById("sbt").addEventListener('click', subImgHandler, false);
});

function isCanvasSupported() {
    var elem = document.createElement("canvas");
    return !!(elem.getContext && elem.getContext("2d"))
}
/*html5+canvas进行移动端手机照片上传    by xiaohui*/
function changeImg(){
    if(i<9){
        if (!isCanvasSupported) {
            return
        }
        $.ajaxFileUpload({
            url:_jsonUrl+ "/attachment/front/upload",//处理图片脚本
            secureuri :false,
            fileElementId :'photo',//file控件id
            dataType : 'json',
            success :function(data){
               pictUrlArray.push({imgUrl: data.data.fileUrl});
            }
        });
        compress(event, function (base64Img, _ori) {
            //解决ios手机上传竖拍照片时会逆时针旋转90度问题
            switch (_ori){
                case "1":
                    $("#showImg"+i)[0].style.transform="rotate(0deg)";
                    $("#showImg"+i)[0].style.webkitTransform="rotate(0deg)";
                    break;
                case "3":
                    $("#showImg"+i)[0].style.transform="rotate(180deg)";
                    $("#showImg"+i)[0].style.webkitTransform="rotate(180deg)";
                    break;
                case "6":
                    $("#showImg"+i)[0].style.transform="rotate(90deg)";
                    $("#showImg"+i)[0].style.webkitTransform="rotate(90deg)";
                    break;
                case "8":
                    $("#showImg"+i)[0].style.transform="rotate(270deg)";
                    $("#showImg"+i)[0].style.webkitTransform="rotate(270deg)";
                    break;
                default:
                    $("#showImg"+i)[0].style.transform="rotate(0deg)";
                    $("#showImg"+i)[0].style.webkitTransform="rotate(0deg)";
                    break;
            }
            $("#showImg"+i)[0].src = base64Img;

            $("#showImg"+i).attr("ori", _ori);
            $("#addImgBox input").remove();
            i++;
            if(i < 9){
                $("#addImg").append('<li class=" bui_p_6" id="addImgBox" style="height:'+_widthBox+'px; position: relative;">'+
                                '<img src="images/picture.jpg" id="showImg'+i+'" style="width:'+_width+'px; height:'+_width+'px;" />'+
                                '<input type="file" name="file" id="photo" class="selectImg" style="width:'+_width+'px; height:'+_width+'px;" onchange="changeImg()" accept="image/*"/>'+
                                '</li>');
                
            }else{
                $("#addImg").append('');
            }
        });
    }
    
}
function compress(event, callback) {
    var file = event.currentTarget.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        var image = new Image();
        image.onload = function () {
            var square = 400;
            var canvas = document.createElement("canvas");
            canvas.width = square;
            canvas.height = square;
            var context = canvas.getContext("2d");
            context.clearRect(0, 0, square, square);
            var imageWidth;
            var imageHeight;
            var offsetX = 0;
            var offsetY = 0;
            if (this.width > this.height) {
                imageWidth = Math.round(square * this.width / this.height);
                imageHeight = square;
                offsetX = -Math.round((imageWidth - square) / 2)
            } else {
                imageHeight = Math.round(square * this.height / this.width);
                imageWidth = square;
                offsetY = -Math.round((imageHeight - square) / 2)
            }
            context.drawImage(this, offsetX, offsetY, imageWidth, imageHeight);
            var data = canvas.toDataURL('image/jpeg', 0.8);
            var _ori
            EXIF.getData(this, function(){
                _ori = JSON.stringify(EXIF.getAllTags(this).Orientation);
            });
            //console.log(_ori);
            callback(data, _ori);
        }
        image.src = e.target.result;
    };
    reader.readAsDataURL(file);
};

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


