
var urlContent = "http://"+window.location.host;

/**
 * kindEditor编辑器动态创建
 * **/
function kindEditorCreate(kindEditorId,fileId){
	//动态创建kindEditor编辑器
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", function() {
		
		KindEditor.basePath = "./resources/js/kindeditor/";//kindeditor的目录路径
		KindEditor.create("#"+kindEditorId, {
            cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
    		uploadJson : "uploadFile.html?fileId="+fileId,
    		fileManagerJson : "fileManager.html?fileId="+fileId,
    		allowFileManager : true,
    		items:[
    	        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
    	        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
    	        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
    	        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
    	        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
    	        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'multiimage',
    	        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
    	        'anchor', 'link', 'unlink', '|', 'about'
    	    ],
    		afterBlur: function(){this.sync();},
			afterUpload : function(url) {
				console.log(urlContent+url);
	        }
		});
	});
}

/**
 * kindEditor图片上传
 * **/
function imageUpload(imageUploadBut,imageUploadName,fileId){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
			var editor = KindEditor.editor({
	            cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
	            uploadJson : "uploadFile.html?fileId="+fileId,
	    		fileManagerJson : "fileManager.html?fileId="+fileId,
	    		allowFileManager : true,
	    		afterBlur: function(){this.sync();}
			});
			
			//图片类型上传
			$('#'+imageUploadBut).click(function() {
				editor.loadPlugin('image', function() {
					editor.plugin.imageDialog({
						imageUrl : "",
						urlType: "",
						clickFn : function(url, title, width, height, border, align) {
							if(url.indexOf("http")>-1){
								$('#'+imageUploadName).val(url);
							}else{
								$('#'+imageUploadName).val(urlContent+url);
							}
							$('#imageShow').attr("src",urlContent+url);
							editor.hideDialog();
						}
					});
				});
			});
			
		})
	);
}

/**
 * kindEditor文件上传
 * **/
function fileUpload(fileUploadBut){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
			var editor = KindEditor.editor({
				cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
	    		uploadJson : "uploadFile.html",
	    		fileManagerJson : "fileManager.html",
				allowFileManager : true
			});
			
			//文件类型上传
			$('#'+fileUploadBut).click(function() {
				editor.loadPlugin('insertfile', function() {
					editor.plugin.fileDialog({
						fileUrl : "",
						clickFn : function(url, title) {
							$('#fileUrl').val(url);
							editor.hideDialog();
						}
					});
				});
			});
		})
	);
}
/**
 * kindEditor文件上传
 * **/
function fileUpload2(fileUploadBut,fileUploadInput){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
		var editor = KindEditor.editor({
			cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
			uploadJson : "uploadFile.html",
			fileManagerJson : "fileManager.html",
			allowFileManager : true
		});
		
		//文件类型上传
		$('#'+fileUploadBut).click(function() {
			editor.loadPlugin('insertfile', function() {
				editor.plugin.fileDialog({
					fileUrl : "",
					clickFn : function(url, title) {
						if(title==url){
							title="未命名文件";
						}
						$('#'+fileUploadInput).val("<a href='"+url+"'>上传了一个文件【"+title+"】，点击下载</a>");
						editor.hideDialog();
					}
				});
			});
		});
	})
	);
}
function fileUpload4(fileUploadBut,fileUploadInput,fileUploadInput1,fileId){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
		var editor = KindEditor.editor({
			cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
			uploadJson : "uploadFile.html?fileId="+fileId,
			fileManagerJson : "fileManager.html?fileId="+fileId,
			allowFileManager : true
		});
		
		//文件类型上传
		$('#'+fileUploadBut).click(function() {
			editor.loadPlugin('insertfile', function() {
				editor.plugin.fileDialog({
					fileUrl : "",
					clickFn : function(url, title) {
						if(title==url){
							title="未命名文件";
						}
						$('#'+fileUploadInput).val(urlContent+url);
						$('#'+fileUploadInput1).html(urlContent+url);
						editor.hideDialog();
					}
				});
			});
		});
	})
	);
}

/**
 * kindEditor文件上传并自动提交
 * **/
function fileUpload3(fileUploadBut,fileUploadInput,clickButton){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
		var editor = KindEditor.editor({
			cssPath : "./resources/js/kindeditor/plugins/code/prettify.css",
			uploadJson : "uploadFile.html",
			fileManagerJson : "fileManager.html",
			allowFileManager : true
		});
		
		//文件类型上传
		$('#'+fileUploadBut).click(function() {
			editor.loadPlugin('insertfile', function() {
				editor.plugin.fileDialog({
					fileUrl : "",
					clickFn : function(url, title) {
						if(title==url){
							title="未命名文件";
						}
						$('#'+fileUploadInput).val("<a href='"+url+"'>上传了一个文件【"+title+"】，点击下载</a>");
						editor.hideDialog();
						$('#'+clickButton).click();
					}
				});
			});
		});
	})
	);
}

function fileUploadButton(fileUploadBut,fileUploadInput){
	//单独调用kindedit上传控件
	$.getScript("./resources/js/kindeditor/kindeditor-all-min.js", (function() {
			var uploadbutton = KindEditor.uploadbutton({
				button : $('#'+fileUploadBut),
				fieldName : 'imgFile',
				url : 'uploadFile.html',
				afterUpload : function(data) {
					if (data.error === 0) {
						alert(data.url);
						//var url = KindEditor.formatUrl(data.url, 'absolute');
						//$('#'+fileUploadInput).val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
	}));
}
