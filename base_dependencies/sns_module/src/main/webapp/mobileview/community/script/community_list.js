var _typeName = GetQueryString("typeName");
var _id = GetQueryString("id");
var _pageNo= 1;
var moduleId= GetQueryString("id");
var urlJson;
var tips = "";  //精品贴提示图标

$(document).ready(function(){  
   if (_typeName == "perfectText") {
       $("#classfiyType").text("精品帖");
       tips = '<span class="pefctTextTitleRed bui_mr_3">精</span>';
   }else if (_typeName == "discussed") {
        $("#classfiyType").text("评论过的帖");
   }else if (_typeName == "my") {
       $("#classfiyType").text("我的帖子"); 
   }else  {
        $("#classfiyType").text(_typeName);
   }
   // getCommunityList();
   contentList();
});

/*列表帖子点击事件->帖子详情页*/
function contentPostListFunc (id,title,name) {
    window.sessionStorage.setItem('article_ID',id);
    buijs_showloading('black_f72');
    $.ajax({
        type: 'get',
        url: 'ajax_articleDetail.html',
        complete: function (){
            buijs_closeloading();
        },
        success: function (data){
            $('.bui_wrap').hide();
            wechatSharePublic("健乐多微社区",title, 'http://v.gymmaxer.com/sns_module/mobileview/community/article_detail.html?articleId='+id, false, '', 'mobileview/community/images/logoShare.jpg');
            $('body').append(data);
        },
        error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        }
    })
}
/*获得帖子列表  by xiaohui*/
function getCommunityList() {
    if (_typeName == "perfectText") {
        urlJson= "snsPost/front/querySpecialPostByPage?pageNo="+_pageNo+"&pageSize=10&typeFlag=0";
   }else if (_typeName == "discussed") {
        urlJson= "snsPost/front/findCommentedPostByPage?pageNo="+_pageNo+"&pageSize=10";
   }else  if (_typeName == "my") {
        urlJson= "snsPost/front/findPostByCurrentMember?pageSize=10&pageNo="+_pageNo;
   }else {
        urlJson= "snsPost/front/findPostByPage?pageNo="+_pageNo+"&pageSize=10&moduleId="+ (_id || 0);
   }
    $.ajax({
        type: "get",
        url: _jsonUrl+ urlJson,
        async: true,
        contentType:'application/json',
        dataType: 'json',
        error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        },
        success: function(data) {
            if ( _pageNo >  data.data.pageCount) {
                $("#communityList_tips").html('<i class="fa fa-check"></i> 已经没有了<br />健乐多社区版权所有');
                return
            } else {
                if(_pageNo == 1 && data.data.endRowNo < 10){
                   $("#communityList_tips").html('<i class="fa fa-check"></i> 已经没有了<br />健乐多社区版权所有'); 
                }else{
                    $("#communityList_tips").removeClass('active');
                }
                var nowTime= (new Date()).getTime();
                var _list='';
                
                $.map(data.data.queryResult, function(data,index) {
                    var _listImg='';
                    var levelPict;
//                    data.member.levelList && data.member.levelList.map(function(pram){
//                        if(pram.moduleType == "100"){
//                            levelPict= pram.grade;
//                        }
//                    });
                    if(data.member.memberExtInfoMap && data.member.memberExtInfoMap.levelList && data.member.memberExtInfoMap.levelList.mt100){
    					levelPict = data.member.memberExtInfoMap.levelList.mt100.grade;
    				}
                    if(data.postImgs[0]){
                       _listImg='<div class="bui_mt_8" data-bui_img="" style="width:100%;height:128px;">'+'<img src="'+ _jsonUrl+(data.postImgs[0] && data.postImgs[0].imgUrl) +'">'+'</div>' 
                    }
                    _list +='<li>'+
                            '<a href="javascript:contentPostListFunc('+data.id+',\''+data.title+'\')" class="bui_block bui_plr_12 bui_pt_12">'+
                            '    <p class="oneSpace s_cl1" style="font-size:16px;">'+(tips+ (data.title.length >22 ? (data.title.substring(0,18)+"..."): data.title) )+'</p>'+
                            '    <div>'+_listImg+'</div>'+
                            '</a>'+
                            '<div class="bui_media bui_vm bui_mb_12 bui_mt_8 bui_pr_12 bui_plr_12" style="color: #666;">'+
                            '   <div class="bui_media_l bui_ts_14 bui_vm "><span>'+ data.memberName +'</span>&nbsp;</span><div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(levelPict || 0)+'</span></div> - <span>'+ $.dateDiff(data.createTime,nowTime) +'</span></div>'+
                            '    <div class="bui_media_b"></div>'+
                            '    <div class="bui_media_r bui_ts_14">'+
                            '        <div class="bui_media bui_vm    ">'+
                            '            <div class="bui_media_l">'+
                            '                <i class="fa fa-commenting-o fa-fw"></i> <span>'+ (data.snsPostExtInfo.commentNum || 0) +'</span>'+
                            '            </div>'+
                            '            <div class="bui_media_l">'+
                            '                <i class="fa fa-thumbs-o-up a-fw"></i> <span>'+ (data.snsPostExtInfo.likeNum || 0) +'</span>'+
                            '            </div>'+
                            '        </div>'+
                            '    </div>'+
                            '</div>'+
                            '<hr style="border-color:#d7d7d7;">'+
                            '</li>'
                });
                $("#communityList").append(_list).find('[data-bui_img]').buijs_img();
                //检测滚动到底部
                $(".bui_mo_b").checkScrollB(function() {
                    if (!$("#communityList_tips").hasClass('active')) {
                        $("#communityList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
                        _pageNo++;
                        setTimeout(function() {
                            getCommunityList();
                        }, 300);
                    }
                });
            }
                
        }
    })
}

function contentList () {
    var _htmlList= '';
    if (_typeName == "perfectText") {
        urlJson= "snsPost/front/querySpecialPostByPage?pageNo="+_pageNo+"&pageSize=10&typeFlag=0";
   }else if (_typeName == "discussed") {
        urlJson= "snsPost/front/findCommentedPostByPage?pageNo="+_pageNo+"&pageSize=10";
   }else  if (_typeName == "my") {
        urlJson= "snsPost/front/findPostByCurrentMember?pageSize=10&pageNo="+_pageNo;
   }else {
        urlJson= "snsPost/front/findPostByPage?pageNo="+_pageNo+"&pageSize=10&moduleId="+ (_id || 0);
   }
    $.ajax({
        type: 'get',
        url: _jsonUrl+ urlJson,
        dataType: 'json',
        contentType: 'application/json',
        error: function (data,reo){
            buijs_alert({
                content: "网络超时,请刷新重试。("+data.status+":"+reo+")"
            })
        },
        success: function(data) {
            var nowTime= (new Date()).getTime();
            var _htmlList= '';
            if (data.status != "00" ) {
                return buijs_alert({
                    content:data.msg
                })
            }
            if ( _pageNo >  data.data.pageCount) {
                $("#communityList_tips").html('<i class="fa fa-check"></i> 已经加载完成<br />健乐多社区版权所有');
                return
            }else {
                $("#communityList_tips").removeClass('active');

                $.map(data.data.queryResult,function(item){
                    var tips = ""; //精品贴提示图标
                    var imgList= '';
                    var levelPict;
                    var showImgRule=[];
//                    item.member && item.member.levelList && item.member.levelList.map(function(pram){
//                        if(pram.moduleType == "100"){
//                            levelPict= pram.grade;
//                        }
//                    });
                    if(item.member.memberExtInfoMap && item.member.memberExtInfoMap.levelList && item.member.memberExtInfoMap.levelList.mt100){
                    	levelPict = item.member.memberExtInfoMap.levelList.mt100.grade;
                    }

                    if (item.postImgs.length>0) {
                        $.map(item.postImgs,function(imgurlA,indexK){
                            if(indexK>1) return;
                            showImgRule.push(_jsonUrl + imgurlA.imgUrl)
                        })
                    }
                    if (item.typeFlag == 3) {
                        tips = '<span class="pefctTextTitleRed bui_mr_3">精</span>';
                    }
                     $.map(item.postImgs,function(obj,index){
                        if(index>1) return;
                        if ((index==0)) {
                            imgList+="<li onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'><div style='width:100%; height: 100px; border:1px solid #e7e7e7;' data-bui_img=''><img src='"+_jsonUrl+obj.imgUrl+"' /></div></li>"
                        }else {
                            imgList+="<li onclick='showIMGFUN("+JSON.stringify(showImgRule)+","+index+")'><div style='width:100%; height: 100px;border:1px solid #e7e7e7;' data-bui_img=''><img src='"+_jsonUrl+obj.imgUrl+"' /></div></li>"
                        }
                     });
                    _htmlList+='<div class="bui_bgc_white bui_mt_12 bui_plr_12 bui_ptb_12" onclick="contentPostListFunc('+item.id+',\''+item.title+'\',\''+item.memberName+'\')">'
                                +   '<div class="bui_media">'
                                +       '<div class="bui_media_l">'
                                +           '<div class="contentPostList_li_face">'
                                +               '<img src="'+item.memberImgUrl+'">'
                                +           '</div>'
                                +       '</div>'
                                +       '<div class="bui_media_b">'
                                +           '<div class="bui_ts_16 bui_inline bui_vm"><span>'+item.memberName+'</span>&nbsp;<div class="bui_bgc_red bui_tc_white bui_plr_6 bui_radius bui_inline" style="display: inline-block;"><span class="bui_ts_12 bui_tc_yellow_l24">Lv</span><span class="bui_ts_8">'+(levelPict || 0)+'</span></div></div>'
                                +           '<div class="bui_ts_10 bui_tc_gray">'+$.dateDiff(item.createTime,nowTime)+'</div>'
                                +       '</div>'
                                +   '</div>'
                                +   '<div class="bui_ptm_6 bui_ts_15">'+tips+item.title+'</div>'
                                +   '<div class="bui_ts_13 bui_tc_gray">'+(item.content.length >80 ? (item.content.replace(/<[^>]+>/g,"").substring(0,66)+"..."): item.content)+'</div>'
                                +   '<div class="contentPostList_li_Pict bui_avg_sm_2 bui_row_p_6">'+imgList+'</div>'
                                +   '<p class="bui_ts_13 bui_tc_gray">'+'<i class="fa fa-eye"></i> '+(item.snsPostExtInfo.viewNum || "0")+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-commenting-o"></i> '+(item.snsPostExtInfo.commentNum || "0")+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-o-up"></i> '+(item.snsPostExtInfo.likeNum || "0")+'</div>'
                                +'</p>'
                });
                $("#communityList").append(_htmlList);
                $("#communityList").find('[data-bui_img]').buijs_img();
                //检测滚动到底部
                $(".bui_mo_b").checkScrollB(function() {
                    if (!$("#communityList_tips").hasClass('active')) {
                        $("#communityList_tips").addClass('active').html('<i class="fa fa-circle-o-notch fa-spin"></i> 加载中...<br />健乐多社区版权所有');
                        _pageNo++;
                        setTimeout(function() {
                            contentList();
                        }, 300);
                    }
                });

            }
        }
    });
}