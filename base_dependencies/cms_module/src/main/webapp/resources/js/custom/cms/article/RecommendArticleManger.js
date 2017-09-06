//推荐文章组件
var RecommendArticleManager = function(setting) {
	var state = 'initing';  //文章管理器状态 initing, inited
	var articleList = []; // 表格中选中的文章
	var checkboxMap = {}; //表格id与checkbox的映射
	
	if(setting && setting.articleList){
		articleList = setting.articleList;
	}
	var tagDoms = [ $('#recommandArticleTagA'), $('#recommandArticleTagB') ];
	// 添加推荐文章
	var addRecommend = function(article) {
		if (!article)
			return;
		articleList.push(article);
		setArticleListInTags(articleList);
	};
	// 删除推荐文章
	var removeRecommend = function(article) {
		
		if (!article)
			return;
		var index = -1;
		articleList.forEach(function(tmp, i) {
			if (tmp.id == article.id) {
				index = i;
				return;
			}
		});
		articleList.splice(index, 1);
		setArticleListInTags(articleList);
	};
	// 把推荐文章设置在标签上
	var setArticleListInTags = function(articleList) {
		for(p in checkboxMap){
			checkboxMap[p].attr('checked', false);
		}
		tagDoms.forEach(function(tagDom) {
			tagDom.importTags('');
			articleList.forEach(function(article) {
				if (tagDom.tagExist(article.title)) {
					return
				}
				if(checkboxMap[article.id]){
					checkboxMap[article.id].attr('checked', true);
				}
				
				tagDom.addTag(article.title);
			});
		});
	};
	// 标签上删除推荐文章的处理器
	var onTagsRemovedHandler = function(tag) {
		var article = null;
		articleList.forEach(function(tmp) {
			if (tmp.title == tag) {
				article = tmp;
				return;
			}
		});
		removeRecommend(article);
	}
	// 表格中check的change处理器
	var checkboxChangeHandler = function(e, article) {
		var isChecked = e.target.checked;
		if (isChecked) {
			addRecommend(article);
		} else {
			removeRecommend(article);
		}
	};

	var oTable = null; // 文章表格
	var loadDataTable = function() {
		var aoColumns = [ {
			"sTitle" : "标题",
			"mData" : "title"
		}, {
			"sTitle" : "副标题",
			"mData" : "subTitle"
		}, {
			"sTitle" : "作者",
			"mData" : "author"
		}, {
			"sTitle" : "文章状态",
			"mData" : "articleState"
		} ];

		aoColumns.unshift({
			"sTitle" : "选择",
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false,
			"sWidth" : "3%",
			"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
				var isChecked = false;
				articleList.forEach(function(article) {
					if (article.id == oData.id) {
						isChecked = true;
					}
				});
				var checkboxDom = $('<input>', {
					"type" : "checkbox",
					"checked" : isChecked,
					"class" : "checkboxes",
					"change" : function(e) {
						checkboxChangeHandler(e, oData);
					}
				});
				
				checkboxMap[oData.id] = checkboxDom;
				$(nTd).append(checkboxDom);
			}
		});

		oTable = $('#storeDataTable').DataTable({
			"aoColumns" : aoColumns,
			"fnServerParams": function (aoData) {
                aoData.push({"name": "articleState", "value": 3});
            },
			"sAjaxSource" : "article/service/findByPage",
			"sAjaxDataProp" : "queryResult",
			"bFilter" : true,
			"bInfo" : true,
			"bJQueryUI" : true,
			"bLengthChange" : true,
			"bPaginate" : true,
			"bProcessing" : true,
			"bSort" : false,
			"bStateSave" : false,
			"bAutoWidth" : true,
			"iTabIndex" : 1,
			"sServerMethod" : "POST",
			"bServerSide" : true,
			"aLengthMenu" : [ [ 5, 10 ], [ 5, 10 ] ],
			"iDisplayLength" : 5,
			"oLanguage" : {
				"sProcessing" : "努力加载中...",
				"sLengthMenu" : "显示 _MENU_ 条记录",
				"sInfoEmpty" : "搜索结果为0条记录",
				"sInfoFiltered" : "(从 _MAX_ 条记录中过滤出)",
				"sZeroRecords" : "没有您要搜索的内容",
				"sSearch" : "搜索(标题或作者)：",
				"sInfo" : "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			},
			"fnPreDrawCallback":function(){
				
				if(state == 'initing'){
					return;
				}
				checkboxMap = {};
				state = 'initing'
			},
			"fnInitComplete":function(){
				state = 'inited'; 
			}
		});
	}
	this.init = function() {
		tagDoms.forEach(function(tagDom) {
			tagDom.tagsInput({
				width : '82%',
				defaultText : '',
				interactive : false,
				onRemoveTag : onTagsRemovedHandler
			});
		});
		
		var articleId = $('#articleId').val();
		if(articleId){
			$.getJSON('recommendArticle/service/findByArticleId?articleId=' + articleId, function(json){
				if(json.status != '00'){
					alret('获取推荐文章失败,原因是' + josn.msg);
				}
				
				recommendArticles = json.data;
				recommendArticles = recommendArticles.map(function(recommendArticle){
					return {
						id : recommendArticle.articleId,
						title : recommendArticle.articleTitle,
						subTitle : recommendArticle.articleStitle,
						tag : recommendArticle.articleLabel,
						author : recommendArticle.articleAuthor,
						publishTime : recommendArticle.publishTime,
						summary : recommendArticle.articleAbstract,
						clickCount : recommendArticle.clickCount,
						url : recommendArticle.articleUrl,
						createTime : recommendArticle.createTime,
						coverImage : recommendArticle.coverImage
					};
				});
				articleList = recommendArticles;
				setArticleListInTags(articleList);
				loadDataTable();
			});
		}else{
			loadDataTable();
		}
		
		
		
	};
	this.getRecommendArticleList = function() {
		var recommendArticles = [];
		articleList.forEach(function(article) {
			var coverImage = article.coverImage;
			recommendArticles.push({
				articleId : article.id,
				articleTitle : article.title,
				articleStitle : article.subTitle,
				articleLabel : article.tag,
				articleAuthor : article.author,
				publishTime : article.publishTime,
				articleAbstract : article.summary,
				clickCount : article.clickCount,
				articleUrl : article.url,
				createTime : article.createTime,
				coverImage : coverImage
			});
		});
		return recommendArticles;
	}
}