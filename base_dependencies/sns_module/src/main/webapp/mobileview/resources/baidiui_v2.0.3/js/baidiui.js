var buijs_getFile = {};
buijs_getFile.css = {};
buijs_getFile.js = {};
buijs_getFile.js.lazyload = 'http://cdn.bootcss.com/jquery_lazyload/1.9.7/jquery.lazyload.min.js';
buijs_getFile.js.swiper = 'https://cdn.bootcss.com/Swiper/3.3.1/js/swiper.jquery.min.js';
buijs_getFile.js.dataTimePicker = 'http://www.baidiui.com/baidiui_v2.0.2/js/baidiui-datetimepicker.min.js'
buijs_getFile.css.bicon = '';
buijs_getFile.css.fontAwesome = 'http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css';
buijs_getFile.js.highlight = 'http://cdn.bootcss.com/highlight.js/9.6.0/highlight.min.js';
buijs_getFile.css.highlight = 'http://cdn.bootcss.com/highlight.js/9.6.0/styles/zenburn.min.css';
// JavaScript Document
//Object resize
(function($, h, c) {
	var a = $([]),
		e = $.resize = $.extend($.resize, {}),
		i, k = "setTimeout",
		j = "resize",
		d = j + "-special-event",
		b = "delay",
		f = "throttleWindow";
	e[b] = 250;
	e[f] = true;
	$.event.special[j] = {
		setup: function() {
			if(!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.add(l);
			$.data(this, d, {
				w: l.width(),
				h: l.height()
			});
			if(a.length === 1) {
				g()
			}
		},
		teardown: function() {
			if(!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.not(l);
			l.removeData(d);
			if(!a.length) {
				clearTimeout(i)
			}
		},
		add: function(l) {
			if(!e[f] && this[k]) {
				return false
			}
			var n;

			function m(s, o, p) {
				var q = $(this),
					r = $.data(this, d);
				r.w = o !== c ? o : q.width();
				r.h = p !== c ? p : q.height();
				n.apply(this, arguments)
			}
			if($.isFunction(l)) {
				n = l;
				return m

			} else {
				n = l.handler;
				l.handler = m
			}
		}
	};

	function g() {

		i = h[k](function() {
			a.each(function() {
				var n = $(this),
					m = n.width(),
					l = n.height(),
					o = $.data(this, d);
				if(m !== o.w || l !== o.h) {
					n.trigger(j, [o.w = m, o.h = l])
				}
			});
			g()
		}, e[b])

	}
})(jQuery, this);

//动态加载JS,CSS
function buijs_loadFile(fileType, Url, callback) {
	var _header = $('head:first');
	if(fileType == 'css') {
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.fileType = 'text/css';
		link.href = Url;
		$("head:first").append(link);
		callback ? callback() : '';
	} else if(fileType == 'js') {
		var script = document.createElement('script');
		script.fileType = 'text/javascript';
		script.src = Url;
		$("head:first").append(script);
		$.ajax({
			type: "get",
			url: Url,
			async: true,
			cache: true,
			dataType: 'script',
			error: function() {

			},
			success: function() {
				callback ? callback() : '';
			}
		});

	};
}

//监听事件_hover延时
$.fn.hoverDelay = function(options) {
	var defaults = {
		hoverDuring: 200,
		outDuring: 200,
		hoverEvent: function() {
			$.noop();
		},
		outEvent: function() {
			$.noop();
		}
	};
	var sets = $.extend(defaults, options || {});
	var hoverTimer, outTimer;
	return $(this).each(function() {
		$(this).hover(function() {
			clearTimeout(outTimer);
			hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
		}, function() {
			clearTimeout(hoverTimer);
			outTimer = setTimeout(sets.outEvent, sets.outDuring);
		});
	});
};
//监听事件_触摸
$.fn.buijs_tap = function(direction, range, callback) {
	var _t = $(this);
	var _sx, _sy, _ex, _ey;
	_t.bind({
		'touchstart': function(e) {
			_sx = e.originalEvent.touches[0].pageX;
			_sy = e.originalEvent.touches[0].pageY;
		},
		'touchmove': function(e) {
			//e.preventDefault();
		},
		'touchend': function(e) {
			_ex = e.originalEvent.changedTouches[0].pageX;
			_ey = e.originalEvent.changedTouches[0].pageY;
			//左滑
			if(direction == 'left' && _ex - _sx >= range && callback) {
				callback.call($(this));
			};
			//右滑
			if(direction == 'right' && _sx - _ex >= range && callback) {
				callback.call($(this));
			};
			//上滑
			if(direction == 'up' && _sy - _ey >= range && callback) {
				callback.call($(this));
			};
			//下滑
			if(direction == 'down' && _ey - _sy >= range && callback) {
				callback.call($(this));
			};
		}
	})
};

//第三方 代码演示 highlight
$.fn.buijs_highlight = function() {
	var _t = $(this);
	buijs_loadFile('css', buijs_getFile.css.highlight, function() {
		buijs_loadFile('js', buijs_getFile.js.highlight, function() {
			_t.each(function(index, data) {
				var changeCode = $(this).html().replace(/</g, '&lt;').replace(/>/g, '&gt;');
				$(this).html(changeCode);
				hljs.highlightBlock(data);
			})
		});
	});

};

//第三方 lazyload
$.fn.buijs_lazyload = function(options) {
	var _t = $(this);
	var defaults = {
		getScript: buijs_getFile.js.lazyload,
		threshold: 0, //距离屏幕下方*像素开始处理
		failure_limit: 100, //监听顺序
		event: 'scroll', //监听触发方式
		attr: "src", //处理前替换src标签 默认前缀data-src
		img: "data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==", //占位图片
		effect: "fadeIn", //显示效果
		skip: true, //是否加载隐藏的图片
		box: undefined,
		after: function() {} //完成后回调
	}
	var sets = $.extend(defaults, options);
	$.ajax({
		type: "get",
		url: sets.getScript,
		async: false,
		cache: true,
		dataType: 'script',
		error: function() {},
		success: function() {
			_t.map(function() {
				$(this).lazyload({
					threshold: sets.threshold, //距离屏幕下方*像素开始处理
					failure_limit: sets.failure_limit,
					event: sets.event, //监听触发方式
					data_attribute: sets.attr, //处理前替换src标签
					placeholder: sets.img, //占位图片
					effect: sets.effect, //显示效果
					skip_invisible: sets.skip, //是否加载隐藏的图片
					container: sets.box, //作用盒子
					load: function() {
						sets.after();
					}
				});
			});
		}
	});
};

//图片自适应
$.fn.buijs_img = function(options) {
	$(this).map(function() {
		var _box = $(this);
		var _img = _box.find('img');
		var labelData = _box.data('bui_img') || {},
			setObj = {},
			defaults = {
				center: 'cut',
				alt: false,
				hover: false,
				lazyload: false,
				before: function() {},
				after: function() {}
			};
		var setting = $.extend(defaults, options);
		setObj.center = labelData.center || _box.attr('buijs_img_center') || setting.center;
		setObj.alt = labelData.alt || _box.attr('buijs_img_alt') || setting.alt;
		setObj.hover = labelData.hover || _box.attr('buijs_img_hover') || setting.hover;
		setObj.lazyload = labelData.lazyload || _box.attr('buijs_img_lazyload') || setting.lazyload;
		setObj.before = labelData.before || _box.attr('buijs_img_before') || setting.before;
		setObj.after = labelData.after || _box.attr('buijs_img_after') || setting.after;
		setObj.before();

		//初始化
		_box.css({
			'position': 'relative',
			'display': 'block',
			'overflow': 'hidden'
		});
		var checkComplete = new Image;
		checkComplete.src = _img.attr('src');
		if(checkComplete.complete) {
			buijs_img_c(_box, _img, setObj);
		} else {
			_img.css({
				'transition': 'none',
				'-webkit-transition': 'none',
				'-moz-transition': 'none',
				'opacity': 1
			});
			//开启lazyload
			if(setObj.lazyload == true) {
				_img.attr('data-src', _img.attr('src')).attr('src', 'data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==').buijs_lazyload({
					after: function() {
						buijs_img_c(_box, _img, setObj);
					}
				});
			} else {
				checkComplete.onload = function() {
					buijs_img_c(_box, _img, setObj);
				};
			};
		}
		//鼠标经过效果
		if(setObj.hover != false) {
			buijs_img_hover(_box, _img, setObj);
		};
		//显示alt
		if(setObj.alt != false) {
			buijs_img_alt(_box, _img, setObj);
		};
		//窗口尺寸
		$(window).resize(function() {
			buijs_img_c(_box, _img, setObj);
		});
	});
	//center开启
	function buijs_img_c(_box, _img, setObj) {
		_img.css({
			'transition': 'opacity 0.3s,transform 0.3s',
			'-webkit-transition': 'opacity 0.3s,-webkit-transform 0.3s',
			'-moz-transition': 'opacity 0.3s,-moz-transform 0.3s'
		});
		var _box_w = _box.width(), //获取盒子宽度
			_box_h = _box.height(), //获取盒子高度
			_box_p = _box_w / _box_h, //获取盒子宽高比
			_img_w = _img.width(), //获取图片宽度
			_img_h = _img.height(), //获取图片高度
			_img_p = _img_w / _img_h, //获取图片宽高比
			_img_show = function() {
				setTimeout(function() {
					_img.css({
						'opacity': 1
					});
				}, 300); //图片显示动作
			}; //显示图片
		//裁剪
		if(setObj.center == 'cut') {
			//水平裁剪
			if(_img_p >= _box_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h + 'px'
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
				_img_show(); //图片显示动作
			};
			//垂直裁剪
			if(_img_p < _box_p) {
				_img.css({
					'width': _box_w + 'px',
					'height': 'auto',
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
				_img_show(); //图片显示动作
			};
		}
		//匹配
		else if(setObj.center == 'inside') {
			//水平居中
			if(_box_p >= _img_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
				_img_show(); //图片显示动作
			};
			//垂直居中
			if(_box_p < _img_p) {
				_img.css({
					'width': _box_w,
					'height': 'auto'
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
				_img_show(); //图片显示动作
			};
		};
	};

	//hover开启
	function buijs_img_hover(_box, _img, setObj) {
		_img.css({
			'transition': 'opacity 0.3s,transform 0.3s',
			'-webkit-transition': 'opacity 0.3s,-webkit-transform 0.3s',
			'-moz-transition': 'opacity 0.3s,-moz-transform 0.3s'
		});
		var zoomBigCss = {
				'transform': 'scale(1.1)',
				'-webkit-transform': 'scale(1.1)',
				'-moz-transform': 'scale(1.1)'
			},
			zoomSmallCss = {
				'transform': 'scale(1)',
				'-webkit-transform': 'scale(1)',
				'-moz-transform': 'scale(1)'
			};

		if(setObj.hover == 'out') {
			_img.css(zoomSmallCss);
			//PC
			if($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css(zoomBigCss);
				}, function() {
					_img.css(zoomSmallCss);
				});
				_box.parents('a').hover(function() {
					_img.css(zoomBigCss);
				}, function() {
					_img.css(zoomSmallCss);
				});
			};
			//移动
			if($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css(zoomBigCss);
					},
					'touchend': function() {
						_img.css(zoomSmallCss);
					}
				});
			};

		};
		if(setObj.hover == 'in') {
			_img.css(zoomBigCss);
			//PC
			if($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css(zoomSmallCss);
				}, function() {
					_img.css(zoomBigCss);
				});
				_box.parents('a').hover(function() {
					_img.css(zoomSmallCss);
				}, function() {
					_img.css(zoomBigCss);
				});
			};
			//移动
			if($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css(zoomSmallCss);
					},
					'touchend': function() {
						_img.css(zoomBigCss);
					}
				});
			};
		};

	};
	//显示alt
	function buijs_img_alt(_box, _img, setObj) {
		var _alttext = _img.attr('alt');
		if(_alttext != '' && _alttext != undefined) {
			_box.append("<div class='bui_img_alt'><p>" + _alttext + "</p></div>");
			_box.children('.bui_img_alt').css({
				'width': '100%',
				'padding': '0.5em 1em',
				'background-color': 'rgba(0,0,0,0.48)',
				'color': '#fff',
				'position': 'absolute',
				'top': '-100%',
				'left': '-100%',
				'transition': 'all 0.3s',
				'-webkit-transition': 'all 0.3s',
				'-moz-transition': 'all 0.3s'
			});
			//下
			if(setObj.alt == 'bottom') {
				_box.children('.bui_img_alt').css({
					'top': 'auto',
					'bottom': '0px',
					'left': '0px',
					'transform': 'translateY(100%)'
				});
				//PC
				if($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
					_box.hover(function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(0)'
						});
					}, function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(100%)'
						});
					});
				};
				//移动
				if($('body').hasClass('bui_body_sm')) {
					_box.on({
						'touchstart': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(0)'
							});
						},
						'touchend': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(100%)'
							});
						}
					});
				};
			};
			//上
			if(setObj.alt == 'top') {
				_box.children('.bui_img_alt').css({
					'top': '0px',
					'bottom': 'auto',
					'left': '0px',
					'transform': 'translateY(-100%)'
				});
				//PC
				if($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
					_box.hover(function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(0)'
						});
					}, function() {
						_box.children('.bui_img_alt').css({
							'transform': 'translateY(-100%)'
						});
					});
				};
				//移动
				if($('body').hasClass('bui_body_sm')) {
					_box.on({
						'touchstart': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(0)'
							});
						},
						'touchend': function() {
							_box.children('.bui_img_alt').css({
								'transform': 'translateY(-100%)'
							});
						}
					});
				};
			};
		};

	};
};

/*滚动*/
$.fn.buijs_fixed = function(options) {

	$(this).each(function() {
		var _box = $(this);
		var _item = _box.find('.bui_fixed_item');

		//使用标签
		if(_box.attr('data-bui_fixed') != undefined) {
			var _data_top = _box.data('bui_fixed').top;
			var _data_topstop = _box.data('bui_fixed').topstop;
			var _data_bottom = _box.data('bui_fixed').bottom;
			var _data_zindex = _box.data('bui_fixed').zindex;
			var _data_scroll = _box.data('bui_fixed').scroll;
		};
		//使用js
		var defaults = {
			top: 'false',
			topstop: 'false',
			bottom: 'false',
			zindex: 1,
			scroll: 'false'
		};
		var sets = $.extend(defaults, options);

		var _top = _data_top || sets.top;
		var _topstop = _data_topstop || sets.topstop;
		var _bottom = _data_bottom || sets.bottom;
		var _zindex = _data_zindex || sets.zindex;
		var _scroll = _data_scroll || sets.scroll;

		//滚动活动
		buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
		$(window).on({
			'scroll': function() {
				buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
			},
			'resize': function() {
				buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll);
			}
		});
		//滚动活动
		function buijs_fixed_function(_top, _topstop, _bottom, _zindex, _scroll) {
			var _win_t = parseFloat($(window).scrollTop());
			var _win_h = parseFloat($(window).height());
			var _box_t = parseFloat(_box.offset().top) + parseFloat(_box.css('padding-top'));
			var _box_l = parseFloat(_box.offset().left) + parseFloat(_box.css('padding-left'));
			var _box_w = parseFloat(_box.width());
			var _item_h = parseFloat(_item.height());
			var _box_h = parseFloat(_box.parent().height()) + parseFloat(_box.parent().css('margin-top')) + parseFloat(_box.parent().css('margin-bottom')) + parseFloat(_box.parent().css('padding-top')) + parseFloat(_box.parent().css('padding-bottom'));

			//顶部固定
			if(_top != 'false' && _bottom == 'false') {
				//顶部
				if(_win_t < _box_t - _top) {
					_item.css({
						'position': 'static',
						'z-index': _zindex,
						'width': 'auto',
						'top': 'auto',
						'left': 'auto'
					});
				};
				//滚动
				if(_win_t >= _box_t - _top) {
					_item.css({
						'position': 'fixed',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'top': _top + 'px',
						'left': _box_l + 'px'
					});
				};
				//到达底部
				if(_topstop != 'false') {

					if(_win_t > _box_t + _box_h - _item_h - _top - _topstop) {
						_item.css({
							'position': 'absolute',
							'z-index': _zindex,
							'width': _box_w,
							'top': _box_t + _box_h - _item_h - _topstop + 'px',
							'left': _box_l + 'px'
						});
					}
				};
			};

			//底部固定
			if(_top == 'false' && _bottom != 'false') {
				//到达顶部
				if(_win_t - _bottom < _box_t + _item_h - _win_h) {
					_item.css({
						'position': 'static',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'top': 'auto',
						'bottom': 'auto',
						'left': _box_l + 'px'
					});
				};
				//滚动中
				if(_win_t - _bottom >= _box_t + _item_h - _win_h) {
					_item.css({
						'position': 'fixed',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'left': _box_l + 'px',
						'top': 'auto',
						'bottom': _bottom + 'px',

					});
				};
				//到达底部
				if(_win_t >= _box_t + _box_h - _win_h) {
					_item.css({
						'position': 'absolute',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'left': _box_l + 'px',
						'top': _box_h + _box_t - _item_h - _bottom + 'px',
						'bottom': 'auto'
					});
				};
			};

			//内部滚动
			if(_scroll != 'false') {
				_item.hoverDelay({
					hoverEvent: function() {
						_item.css({
							'height': _win_h - _top,
							'overflow-y': 'auto'
						});
					},
					outEvent: function() {
						_item.css({
							'overflow-y': 'hidden'
						});
					}
				})

				//				_item.bind({
				//					'DOMMouseScroll': function(e) {
				//						e.preventDefault();
				//						var _tst = _item.scrollTop();
				//						var _ewt = e.originalEvent.detail;
				//						if (_ewt > 0) {
				//							_item.scrollTop(_tst + _ewt);
				//						}
				//						if (_ewt < 0) {
				//
				//							_item.scrollTop(_tst + _ewt);
				//						}
				//					}
				//				});
			};
		};
	});
};

//固定位置
//跟随滚动
$.fn.buijs_stick = function(options) {
	$(this).map(function() {
		var _t = $(this),
			labelData = _t.data('buijs_stick') || {},
			setObj = {};
		var defaults = {
			setid: 'buijs_fixedBox_' + [$(".buijs_stick_wrap").length + 1],
			top: 0,
			innerBox: null,
			scrollbox: false,
			zindex: null
		}
		var setting = $.extend(defaults, options);

		setObj.setid = _t.attr('buijs_stick_setid') || labelData.setid || setting.setid;
		setObj.top = _t.attr('buijs_stick_top') || labelData.top || setting.top;
		setObj.innerBox = _t.attr('buijs_stick_innerBox') || labelData.innerBox || setting.innerBox;
		setObj.scrollbox = _t.attr('buijs_stick_scrollbox') || labelData.scrollbox || setting.scrollbox;
		setObj.zindex = _t.attr('buijs_stick_zindex') || labelData.zindex || setting.zindex;

		//初始化
		var _tw = _t.width() + parseFloat(_t.css('padding-left')) + parseFloat(_t.css('padding-right'));
		var _th = _t.height() + parseFloat(_t.css('padding-top')) + parseFloat(_t.css('padding-bottom'));
		if(!_t.parent().hasClass('buijs_stick_wrap')) {
			_t.attr('id', setObj.setid).wrap('<div class="buijs_stick_wrap" style="width:' + _tw + 'px;height:' + _th + 'px"></div>');
		}

		if(setObj.scrollbox == false) {
			$(window).bind({
				'scroll': function() {
					buijs_stick_action(setObj)
				},
				'resize': function() {
					buijs_stick_action(setObj)
				}
			})
		} else {
			$(setObj.scrollbox).scroll(function() {
				buijs_stick_action(setObj);
			});
		}

		function buijs_stick_action(setObj) {
			setObj.top = parseFloat(setObj.top)
			var _st = $(window).scrollTop() + setObj.top;
			var _t = $("#" + setObj.setid);
			var _stickBox = _t.parent('.buijs_stick_wrap');
			var _preBox = ''
			if(setObj.innerBox) {
				if(setObj.innerBox == 'pre') {
					_preBox = _stickBox.parent();
				} else {
					_preBox = $(setObj.innerBox);
				}
			} else {
				_preBox = $(document);
			};

			var stickVal = {}

			stickVal.tw = _t.width() + parseFloat(_t.css('padding-left')) + parseFloat(_t.css('padding-right'));
			stickVal.th = _t.height() + parseFloat(_t.css('padding-top')) + parseFloat(_t.css('padding-bottom'));
			stickVal.wt = parseFloat(_stickBox.offset().top);
			stickVal.wl = parseFloat(_stickBox.offset().left);
			stickVal.bw = _preBox.width();
			stickVal.bh = _preBox.height();

			//滚动中
			if(_st >= stickVal.wt && _st < (stickVal.bh + stickVal.wt - stickVal.th)) {
				_t.css({
					'position': 'fixed',
					'width': stickVal.tw,
					'top': setObj.top,
					'left': stickVal.wl,
					'z-index': setObj.zindex
				});
				//超过屏幕高度
				if(stickVal.th + setObj.top > $(window).height()) {
					_t.css({
						'height': $(window).height() - setObj.top,
						'overflow-x': 'hidden',
						'overflow-y': 'auto'
					});
				}
			}
			//到达顶部
			else if(_st < stickVal.wt && _st < Number(stickVal.bh + stickVal.wt - stickVal.th)) {
				_t.css({
					'position': 'static',
					'width': stickVal.tw,
					'height': '',
					'top': '',
					'left': '',
					'overflow': 'hidden'
				})
			}
			//到达底部
			else {
				_t.css({
					'position': 'absolute',
					'width': stickVal.tw,
					'height': '',
					'top': stickVal.bh + stickVal.wt - stickVal.th,
					'left': stickVal.wl
				})
			}
		}
	});
};

/*锚链接动画*/
$.fn.buijs_anchor = function(options) {
	$(this).each(function() {
		var _t = $(this),
			labelData = _t.data('buijs_anchor') || {},
			setObj = {},
			defaults = {
				top: 0
			};
		var setting = $.extend(defaults, options);

		setObj.top = _t.attr('buijs_anchor_top') || labelData.top || setting.top;
		setObj.href = _t.attr('buijs_anchor_href') || labelData.href || setting.href;

		//初始化
		_t.css('cursor', 'pointer')

		_t.unbind().bind('click', function(e) {
			e.preventDefault();
			$("[data-buijs_anchor]").removeClass('bui_tc_red')
			_t.addClass('bui_tc_red');
			var _target = $('[name="' + setObj.href + '"]').offset().top || '0';

			$('html,body').animate({
				scrollTop: _target - setObj.top + 'px'
			});
		});
	});
};

/*模拟弹窗*/

var buijs_modal = function(options) {
	var buijs_modal = {
		//插入模态对话框html
		content: function(setObj) {
			//插入遮罩层
			if($(".bui_modal_mask").length == 0) {
				$("body").append('<div class="bui_modal_mask bui_bgc_black_f48 ' + setObj.effect + '" style="z-index:10002;" buijs_modal_close=""></div>');
			};
			setTimeout(function() {
				$('.bui_modal_mask').addClass('active').buijs_disable('scroll');
			}, 0);
			//处理层级
			var zindex = $(".bui_modal").length == 0 ? [Number($(".bui_modal_mask:first").css('z-index')) + 1] : [Number($(".bui_modal:first").css('z-index')) + 1];

			//页头页脚处理
			var headerHtml = '';
			if(!setObj.header) {
				setObj.headerClass = '';
				headerHtml = '';
			} else if(setObj.header == 'default') {
				var closeLabel = setObj.isclose == true ? '<a href="javascript:;" class="bui_btn bui_btn_24 bui_btn_sq bui_bds_0 bui_m_8" buijs_modal_close="' + setObj.setid + '"><i class="fa fa-close"></i></a>' : '<div class="bui_btn_32 bui_btnsq bui_m_8"></div>';
				headerHtml = '<div class="bui_media bui_vm bui_ta_c">' +
					'	<div class="bui_media_l">' +
					'		<div class="bui_btn_32 bui_btn_sq bui_m_8"></div>' +
					'	</div>' +
					'	<div class="bui_media_b">' +
					'		<p>' + setObj.title + '</p>' +
					'	</div>' +
					'	<div class="bui_media_r">' + closeLabel + '</div>' +
					'</div>';
			} else {
				headerHtml = setObj.header;
			};
			footerHtml = '';
			if(!setObj.footer) {
				setObj.footerClass = '';
				footerHtml = '';
			} else if(setObj.footer == 'ask') {
				footerHtml = '<div class="bui_ta_r bui_ts_12 bui_p_12">' +
					'<button class="bui_btn bui_btn_24 bui_bgc_white bui_ml_8" buijs_modal_false>取消</button>' +
					'<button class="bui_btn bui_btn_24 bui_bgc_white bui_ml_8" buijs_modal_true>确定</button>' +
					'</div>';
			} else {
				footerHtml = setObj.footer;
			}

			//添加模态对话框
			$('.bui_modal_mask').after(
				'<div id="' + setObj.setid + '" class="bui_modal ' + setObj.effect + ' ' + setObj.positions + ' ' + setObj.boxClass + '" style="width:' + setObj.width + ';height:' + setObj.height + ';z-index:' + zindex + ';">' +
				'<div class="bui_modal_h ' + setObj.headerClass + '">' + headerHtml + '</div>' +
				'<div class="bui_modal_b" style="overflow-x:hidden;overflow-y:auto;">' + setObj.content + '</div>' +
				'<div class="bui_modal_f ' + setObj.footerClass + '">' + footerHtml + '</div>' +
				'</div>');
			//开启
			setTimeout(function() {
				$("#" + setObj.setid).addClass('active');
				setObj.height == 'auto' || setObj.height.indexOf('px') > -1 ? setObj.height = $("#" + setObj.setid).height() + 'px' : setObj.height = setObj.height;
				//html
				if(!setObj.ajaxload) {
					buijs_modal.resize(setObj);
					setObj.showAfter();
					return false;
				}
				//ajax
				else {
					$("#" + setObj.setid).attr('bui_modal_url', setObj.ajaxload);
					setTimeout(function() {
						$.ajax({
							type: "get",
							url: setObj.ajaxload,
							async: true,
							success: function(data) {
								$("#" + setObj.setid + " .bui_modal_b").html(data);
								buijs_modal.resize(setObj);
								setObj.showAfter();
								return false;
							}
						});
					}, 5);
				};
			}, 0);

			//关闭操作
			if(setObj.isclose == true) {
				setTimeout(function() {
					$("[buijs_modal_close]").bind('click', function() {
						var _tid = $(this).attr('buijs_modal_close');
						buijs_modal_close(_tid);
					});
				}, 300);
			};

			//选择操作
			$("#" + setObj.setid + " [buijs_modal_true]").bind('click', function() {
				setObj.trueAfter(setObj.setid);
			});
			$("#" + setObj.setid + " [buijs_modal_false]").bind('click', function() {
				setObj.falseAfter(setObj.setid);
			});
		},
		//resize
		resize: function(setObj) {
			var _box = $("#" + setObj.setid);
			var _h = _box.children('.bui_modal_h');
			var _b = _box.children('.bui_modal_b');
			var _f = _box.children('.bui_modal_f');

			//宽度超出window处理
			var isFullWidth = function() {
				if(setObj.width.indexOf('px') > -1) {
					parseInt(setObj.width) >= $(window).width() ? _box.width('100%') : _box.width(setObj.width);
				} else {
					_box.width(setObj.width);
				}
			};
			//高度超出window处理
			var isFullHeight = function() {
				if(setObj.height.indexOf('px') > -1) {
					parseInt(setObj.height) >= $(window).height() ? _box.height('100%') : _box.height(setObj.height);
				} else {
					_box.height(setObj.height);
				}
				isBHeight(); //处理高度
			};
			//bui_modal_b高度处理
			var isBHeight = function() {
				_b.height([_box.height() - _h.height() - _f.height()]);
			};
			if(setObj.positions == 'center') {
				isFullWidth(); //宽度超出window处理
				isFullHeight(); //高度超出window处理
				_box.css({
					'left': [$(window).width() - _box.width()] / 2 + 'px',
					'top': [$(window).height() - _box.height()] / 2 + 'px'
				});

			} else if(setObj.positions == 'top' || setObj.positions == 'bottom') {
				isFullHeight(); //高度超出window处理
				_box.css({
					'width': '100%',
					'left': '0px'
				});
				if(setObj.positions == 'bottom') {
					_box.css({
						'top': '',
						'bottom': '0px'
					});
				};
				if(setObj.positions == 'top') {
					_box.css({
						'top': '0px',
						'bottom': ''
					});
				};
			} else if(setObj.positions == 'left' || setObj.positions == 'right') {
				isFullWidth(); //宽度超出window处理
				_box.css({
					'height': '100%',
					'top': '0px',
				});
				if(setObj.positions == 'left') {
					_box.css({
						'left': '0px',
						'right': ''
					});
				};
				if(setObj.positions == 'right') {
					_box.css({
						'left': '',
						'right': '0px'
					});
				};
				isBHeight(); //处理高度
			};
		}
	};

	var defaults = {
		setid: 'bui_modal_' + ($('.bui_modal').length + 1), //默认设置弹窗ID
		header: 'default', //是否开启顶部
		footer: false, //是否开启底部
		boxClass: 'bui_bgc_white bui_shadow_24 bui_tc_black', //对话框样式类
		headerClass: 'bui_bds_1_b', //对话框页头样式类名
		footerClass: 'bui_bds_1_t', //对话框页脚样式类名
		positions: 'auto', //默认显示方向
		effect: 'zoomOut', //动画效果
		title: '提示', //默认标题
		content: '<div class="bui_m_24 bui_ta_c bui_ts_24_i"><i class="fa fa-circle-o-notch fa-spin bui_tc_white_d48_i"></i></div>', //默认内容
		ajaxload: false, //是否开启ajax
		width: '320px', //默认宽度
		height: 'auto', //默认高度
		isclose: true, //是否可以关闭
		showAfter: function() {}, //开启回调
		closeAfter: function() {}, //关闭回调
		trueAfter: function(id) {
			buijs_modal_close(id);
		}, //true回调
		falseAfter: function(id) {
			buijs_modal_close(id);
		}, //true回调
	};
	var setObj = $.extend(defaults, options);
	if(setObj.positions == 'auto') {
		$('body').hasClass('bui_body_xs') ? setObj.positions = 'top' : setObj.positions = 'center'
	};
	buijs_modal.content(setObj); //插入模态对话框html
	//适应窗口
	$(window).bind('resize', function() {
		console.log('1')
		if($(".bui_modal").length > 0) {
			buijs_modal.resize(setObj);
		}
	});
};
//模态对话框关闭
function buijs_modal_close(id) {
	var closeBox = id ? $('#' + id) : $('.bui_modal');
	closeBox.removeClass('active');
	setTimeout(function() {
		closeBox.remove();
		if($(".bui_modal").length <= 0) {
			$('.bui_modal_mask').removeClass('active');
			setTimeout(function() {
				$('.bui_modal_mask').remove();
			}, 300);
			//停止监听resize
			$(window).unbind('resize');
		}
	}, 300);
};

/*警告框*/
var buijs_alert_length = 0;

function buijs_alert(options) {
	buijs_alert_length = buijs_alert_length + 1;
	var defaults = {
		setid: 'buijs_alert_' + buijs_alert_length,
		positions: 'center_center',
		bgc: 'bui_bgc_black_f72',
		timeout: 3000,
		content: '<i class="fa fa-circle-o-notch fa-spin"></i>',
		boxeffect: true,
		effect: 'zoomOut',
		zindex: $(".bui_modal").length > 0 ? $(".bui_modal:first").css('z-index') : 10000
	};
	var setting = $.extend(defaults, options);
	var style = setting.bgc.indexOf('white') > -1 ? 'bui_tc_black bui_bds_1' : 'bui_tc_white bui_bds_0';

	//展示方向
	if($(".bui_alert_box." + setting.positions).length == 0) {
		$("body").append('<div class="bui_alert_box bui_row_p_6 ' + setting.boxeffect + ' ' + setting.positions + '" style="z-index:' + [setting.zindex + 1] + '"></div>');
	}
	var itemHtml = '<li class="bui_alert_item bui_ta_c ' + setting.effect + '" id="' + setting.setid + '">' +
		'<div class="bui_p_12 ' + setting.bgc + ' ' + style + '">' +
		'<div class="bui_plr_24">' + setting.content + '</div>' +
		'</div>' +
		'</li>';
	if(setting.positions.indexOf('_center') > -1 || setting.positions.indexOf('_top') > -1) {
		$('.bui_alert_box.' + setting.positions).append(itemHtml);
	} else if(setting.positions.indexOf('_bottom') > -1) {
		$('.bui_alert_box.' + setting.positions).prepend(itemHtml);
	}

	buijs_alert_resize(setting);
	setTimeout(function() {
		$(".bui_alert_item").addClass('active');
	}, 0)

	$("li#" + setting.setid).click(function() {
		buijs_alert_close(setting)
	});

	if(setting.timeout != null && setting.timeout != 0) {
		setTimeout(function() {
			buijs_alert_close(setting);
		}, setting.timeout);
		return false;
	};
};

function buijs_alert_resize(setting) {
	if($(".bui_alert_box.center_top").length > 0) {
		$(".bui_alert_box.center_top").css({
			'top': '24px',
			'left': '50%',
			'right': '',
			'bottom': '',
			'margin-left': -[$(".bui_alert_box.center_top").width() / 2]
		})
	};
	if($(".bui_alert_box.center_center").length > 0) {
		$(".bui_alert_box.center_center").css({
			'top': '50%',
			'left': '50%',
			'right': '',
			'bottom': '',
			'margin-top': -[$(".bui_alert_box.center_center").height() / 2],
			'margin-left': -[$(".bui_alert_box.center_center").width() / 2]
		})
	};
	if($(".bui_alert_box.center_bottom").length > 0) {
		$(".bui_alert_box.center_bottom").css({
			'top': '',
			'left': '50%',
			'right': '',
			'bottom': '24px',
			'margin-left': -[$(".bui_alert_box.center_bottom").width() / 2]
		})
	};
	if($(".bui_alert_box.left_top").length > 0) {
		$(".bui_alert_box.left_top").css({
			'top': '24px',
			'left': '24px',
			'right': '',
			'bottom': '',
			'margin-left': '0px'
		})
	};
	if($(".bui_alert_box.left_center").length > 0) {
		$(".bui_alert_box.left_center").css({
			'top': '50%',
			'left': '24px',
			'right': '',
			'bottom': '',
			'margin-top': -[$(".bui_alert_box.left_center").height() / 2],
			'margin-left': '0px'
		})
	};
	if($(".bui_alert_box.left_bottom").length > 0) {
		$(".bui_alert_box.left_bottom").css({
			'top': '',
			'left': '24px',
			'right': '',
			'bottom': '24px',
			'margin-left': '0px'
		})
	};
	if($(".bui_alert_box.right_top").length > 0) {
		$(".bui_alert_box.right_top").css({
			'top': '24px',
			'left': '',
			'right': '24px',
			'bottom': '',
			'margin-left': '0px'
		})
	};
	if($(".bui_alert_box.right_center").length > 0) {
		$(".bui_alert_box.right_center").css({
			'top': '50%',
			'left': '',
			'right': '24px',
			'bottom': '',
			'margin-top': -[$(".bui_alert_box.right_center").height() / 2],
			'margin-left': '0px'
		})
	};
	if($(".bui_alert_box.right_bottom").length > 0) {
		$(".bui_alert_box.right_bottom").css({
			'top': '',
			'left': '',
			'right': '24px',
			'bottom': '24px',
			'margin-left': '0px'
		})
	};
}

function buijs_alert_close(setting) {
	var box = setting.setid ? $("#" + setting.setid) : $(".bui_alert_item");
	box.removeClass('active');
	setTimeout(function() {
		box.remove();
		setTimeout(function() {
			buijs_alert_resize(setting);
			if($(".bui_alert_box." + setting.positions + " .bui_alert_item").length <= 0) {
				$(".bui_alert_box." + setting.positions).remove();
			}
		}, 0)

	}, 300)
	return false;
};

//公用标签 新
$.fn.buijs_tip = function(options) {
	var buijs_tip = {
		//位置调整
		positions: function(setObj, itemObj) {
			//显示位置
			if(setObj.positions == 'top') {
				itemObj.item.css({
					'top': -itemObj.item.height() + 'px',
					'bottom': 'none',
					'left': [itemObj.box.width() - itemObj.item.width()] / 2 + 'px',
					'right': 'none'
				});
				itemObj.arrow.css({
					'top': 'none',
					'bottom': -setObj.arrow / 2 + 'px',
					'left': itemObj.contentBox.width() / 2 - setObj.arrow / 2 + 'px',
					'right': 'none',
					'border-width': 0
				})
			} else if(setObj.positions == 'bottom') {
				itemObj.item.css({
					'top': itemObj.box.height() + 'px',
					'bottom': 'none',
					'left': [itemObj.box.width() - itemObj.item.width()] / 2 + 'px',
					'right': 'none',
				});
				itemObj.arrow.css({
					'top': -setObj.arrow / 2 + 'px',
					'bottom': 'none',
					'left': itemObj.contentBox.width() / 2 - setObj.arrow / 2 + 'px',
					'right': 'none',
				})
			} else if(setObj.positions == 'left') {
				itemObj.item.css({
					'top': [itemObj.box.height() - itemObj.item.height()] / 2 + 'px',
					'bottom': 'none',
					'left': -itemObj.item.width() + 'px',
					'right': 'none'
				});
				itemObj.arrow.css({
					'top': itemObj.contentBox.height() / 2 - setObj.arrow / 2 + 'px',
					'bottom': 'none',
					'left': 'none',
					'right': -setObj.arrow / 2 + 'px'
				})
			} else if(setObj.positions == 'right') {
				itemObj.item.css({
					'top': [itemObj.box.height() - itemObj.item.height()] / 2 + 'px',
					'bottom': 'none',
					'left': itemObj.box.width() + 'px',
					'right': 'none'
				});
				itemObj.arrow.css({
					'top': itemObj.contentBox.height() / 2 - setObj.arrow / 2 + 'px',
					'bottom': 'none',
					'left': -setObj.arrow / 2 + 'px',
					'right': 'none'
				})
			};
		},
		//开启
		open: function(setObj, itemObj, callback) {
			itemObj.box.css('z-index', '1');
			itemObj.item.show(0, function() {
				buijs_tip.positions(setObj, itemObj);
				itemObj.item.css({
					'opacity': 1
				})
				setTimeout(function() {
					callback ? callback() : '';
					$("html").unbind().bind('click', function() {
						buijs_tip.close(setObj, itemObj);
					});
				}, 300)
			});

		},
		//关闭
		close: function(setObj, itemObj, callback) {
			if(!itemObj) {
				var itemObj = {};
				itemObj.box = $(".buijs_tip_wrap");
				itemObj.item = $(".buijs_tip_item");
			}
			itemObj.box.css('z-index', '0');
			itemObj.item.css({
				'opacity': '0'
			});
			setTimeout(function() {
				itemObj.item.hide(0);
				if($(".buijs_tip_item:visible").length == 0) {
					$("html").unbind();
				}
				callback ? callback() : '';
			}, 300);
		}
	}

	$(this).map(function(index, data) {
		var _t = $(this);
		var labelData = _t.data('buijs_tip') || {},
			setting = {},
			setObj = {};
		var defaults = {
			setid: 'buiId_tip_' + $("[id*=buiId_tip_]").length,
			width: '150%',
			events: 'click',
			arrow: 8,
			positions: 'top',
			bgc: 'bui_bgc_black_l48',
			content: '<p class="bui_p_12 bui_ta_c"><i class="fa fa-circle-o-notch fa-spin bui_ts_24_i bui_tc_white_d48"></i></p>'
		};

		setObj = $.extend(defaults, options, labelData, {
			setid: _t.attr('buijs_tip_setid'),
			width: _t.attr('buijs_tip_width'),
			events: _t.attr('buijs_tip_events'),
			arrow: _t.attr('buijs_tip_arrow'),
			positions: _t.attr('buijs_tip_positions'),
			bgc: _t.attr('buijs_tip_bgc'),
			content: _t.attr('buijs_tip_content'),
		});
		//初始化
		if(_t.parent('.buijs_tip_wrap').length == 0) {
			_t.wrap('<div class="buijs_tip_wrap" id="' + setObj.setid + '" style="position:relative;display:inline-block;"></div>');
		};

		//插入tip内容
		if($("#" + setObj.setid + " .buijs_tip_item").length < 1) {
			setObj.content = $("[buijs_tip_content=" + setObj.setid + "]").html() || setObj.content;
			$("#" + setObj.setid).append('<div class="buijs_tip_item" style="display:none;">' +
				'<div class="buijs_tip_item_content_box ' + setObj.bgc + '">' +
				'	<div class="buijs_tip_item_arrow ' + setObj.bgc + '"></div>' +
				'	<div class="buijs_tip_item_content">' + setObj.content + '</div>' +
				'</div>' +
				'</div>');

			var itemObj = {}
			itemObj.box = $("#" + setObj.setid);
			itemObj.item = itemObj.box.find('.buijs_tip_item');
			itemObj.contentBox = itemObj.item.find('.buijs_tip_item_content_box');
			itemObj.arrow = itemObj.contentBox.find('.buijs_tip_item_arrow');
			itemObj.content = itemObj.contentBox.find('.buijs_tip_item_content');
			//初始化
			itemObj.item.css({
				'position': 'absolute',
				'min-width': setObj.width,
				'opacity': 0,
				'transition': 'all 0.3s',
				'-webkit-transition': 'all 0.3s',
				'-moz-transition': 'all 0.3s'
			})
			itemObj.contentBox.css({
				'position': 'relative'
			});
			itemObj.arrow.css({
				'position': 'absolute',
				'width': setObj.arrow + 'px',
				'height': setObj.arrow + 'px',
				'transform': 'rotate(45deg)'
			});
			itemObj.content.css({
				'position': 'relative',
			})

			//样式处理
			if(setObj.bgc.indexOf('bui_bgc_white') != -1) {
				itemObj.item.addClass('bui_bds_1');
				if(setObj.positions == 'top') {
					itemObj.arrow.addClass('bui_bds_1_r bui_bds_1_b')
				} else if(setObj.positions == 'bottom') {
					itemObj.arrow.addClass('bui_bds_1_t bui_bds_1_l')
				} else if(setObj.positions == 'left') {
					itemObj.arrow.addClass('bui_bds_1_t bui_bds_1_r')
				} else if(setObj.positions == 'right') {
					itemObj.arrow.addClass('bui_bds_1_b bui_bds_1_l')
				}
			} else {
				itemObj.item.addClass('bui_tc_white')
			}

			//操作监控
			if(setObj.events == 'click') {
				itemObj.box.unbind().bind({
					'click': function() {
						if(itemObj.item.css('display') == 'none') {
							buijs_tip.open(setObj, itemObj);
						} else {
							buijs_tip.close(setObj, itemObj);
						}
					}
				});
			} else if(setObj.events == 'hover') {
				itemObj.box.unbind().bind({
					'mouseover': function() {
						if(itemObj.item.css('display') == 'none') {
							if($(".buijs_tip_item:visible").length == 0) {
								buijs_tip.open(setObj, itemObj);
							} else {
								buijs_tip.close(null, null, function() {
									buijs_tip.open(setObj, itemObj);
								});
							};
						};
					},
					'mouseleave': function() {
						buijs_tip.close(setObj, itemObj);
					}
				});
			};
		};

	});
};

//公共用tab
$.fn.buijs_tab = function(options) {
	$(this).map(function() {
		var _t = $(this),
			_item = _t.children(),
			labelData = _t.data('bui_tab') || _t.data('buijs_tab') || {},
			setting = {},
			setObj = {};
		defaults = {
			setid: '',
			index: 0,
			activeClass: 'bui_tc_red',
			events: 'click',
			effect: 'default',
			style: 'default',
			speed: 300,
			actionAfter: function() {}
		}
		setting = $.extend(defaults, options, labelData);
		setObj.setid = _t.attr('buijs_tab_setid') || setting.setid;
		setObj.index = _t.attr('buijs_tab_index') || setting.index;
		setObj.activeClass = _t.attr('buijs_tab_activeClass') || setting.activeClass;
		setObj.events = _t.attr('buijs_tab_events') || setting.events;
		setObj.effect = _t.attr('buijs_tab_effect') || setting.effect;
		setObj.speed = _t.attr('buijs_tab_speed') || setting.speed;
		setObj.style = _t.attr('buijs_tab_style') || setting.style;
		setObj.actionAfter = _t.attr('buijs_tab_actionAfter') || setting.actionAfter;

		var _box = $("[buijs_tab_box=" + setObj.setid + "]");

		//样式处理
		if(setObj.style == 'default') {
			_t.css('box-shadow', 'inset 0 -1px 0 #e7e7e7');
			_box.addClass('bui_bds_1_l bui_bds_1_r bui_bds_1_b bui_bgc_white');
			setObj.activeClass = 'bui_bds_1_t bui_bds_1_l bui_bds_1_r bui_bgc_white';
		}

		//初始化
		_item.css('cursor', 'pointer');
		buijs_tab_actve(setObj.index)

		if(setObj.events == 'click') {
			_item.bind('click', function() {
				buijs_tab_actve($(this).index());
			});
		} else if(setObj.events == 'hover') {
			_item.bind('mouseover', function() {
				buijs_tab_actve($(this).index());
			});
		};

		function buijs_tab_actve(index) {
			_item.eq(index).addClass('active ' + setObj.activeClass).siblings().removeClass('active ' + setObj.activeClass);
			_box.map(function() {
				var _activeBox = $(this).children().eq(index);
				var _otherBox = _activeBox.siblings();
				//开启效果
				if(setObj.effect.indexOf('slide') != -1 || setObj.effect.indexOf('fade') != -1 || setObj.effect.indexOf('zoom') != -1) {
					$(this).css({
						'position': 'relative',
						'overflow': 'hidden',
						'transition': 'height ' + setObj.speed + 'ms',
						'-webkit-transition': 'height ' + setObj.speed + 'ms',
						'-moz-transition': 'height ' + setObj.speed + 'ms'
					});
					$(this).children().css({
						'position': 'absolute',
						'width': '100%',
						'top': 0,
						'left': 0,
						'transition': 'all ' + setObj.speed + 'ms',
						'-webkit-transition': 'all ' + setObj.speed + 'ms',
						'-moz-transition': 'all ' + setObj.speed + 'ms'
					});
					//效果滑动
					if(setObj.effect.indexOf('slide') != -1) {
						var direction = setObj.effect == 'slide' ? 'translateX' : setObj.effect == 'slidev' ? 'translateY' : '';
						_activeBox.addClass('active').removeClass('prev').removeClass('next');
						_activeBox.prevAll().addClass('prev').removeClass('next').removeClass('active');
						_activeBox.nextAll().addClass('next').removeClass('prev').removeClass('active');
						$(this).children('.active').css({
							'transform': direction + '(0)',
							'-webkit-transform': direction + '(0)',
							'-moz-transform': direction + '(0)'
						});
						$(this).children('.prev').css({
							'transform': direction + '(-100%)',
							'-webkit-transform': direction + '(-100%)',
							'-moz-transform': direction + '(-100%)'
						});
						$(this).children('.next').css({
							'transform': direction + '(100%)',
							'-webkit-transform': direction + '(100%)',
							'-moz-transform': direction + '(100%)'
						});

					}
					//渐变效果
					else if(setObj.effect == 'fade') {
						_activeBox.addClass('show').removeClass('hidden');
						_otherBox.addClass('hidden').removeClass('show');
						$(this).children('.show').css({
							'opacity': '1'
						});
						$(this).children('.hidden').css({
							'opacity': '0'
						});
					}
					//缩放
					else if(setObj.effect.indexOf('zoom') != -1) {
						_activeBox.addClass('show').removeClass('hidden');
						_otherBox.addClass('hidden').removeClass('show');

						if(setObj.effect == 'zoomin') {
							$(this).children('.show').css({
								'opacity': '1',
								'transform': 'scale(1)'
							});
							$(this).children('.hidden').css({
								'opacity': '0',
								'transform': 'scale(1.2)'
							});
						} else if(setObj.effect == 'zoomout') {
							$(this).children('.show').css({
								'opacity': '1',
								'transform': 'scale(1)'
							});
							$(this).children('.hidden').css({
								'opacity': '0',
								'transform': 'scale(0.8)'
							});
						}

					};
					var _this = $(this)
					setTimeout(function() {
						_this.css('height', _activeBox.height());
					}, 0);

				}
				//效果默认
				else {
					_activeBox.show();
					_otherBox.hide();
				}
				setObj.actionAfter(setObj);
			});

		};
	});

};
/*共用tab*/
function buijs_tab() {
	$('[data-bui_tab]').each(function(e) {
		var _T = $(this);
		var _Target = _T.data('bui_tab').target;
		var _B = $('#' + _Target)
		var _Event = _T.data('bui_tab').event || 'click';
		_T.children().first().addClass('active');
		_B.children().first().addClass('active');
		if(_Event == 'hover') {
			_T.children().on({
				'hover': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if(_Event == 'click') {
			_T.children().on({
				'click': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if(_Event == 'touch') {
			_T.children().on({
				'touchstart': function(e) {
					$(this).addClass('hover');
					e.preventDefault();
				},
				'touchend': function(e) {
					var _Index = $(this).index();
					$(this).removeClass('hover');
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
					e.preventDefault();
				}
			});
		};
	});
};

//折叠手风琴
$.fn.buijs_accordion = function(options) {
	var buijs_accordion = {
		action: function(setObj) {
			var i = $("#" + setObj.setid + " .item.active");
			var iTitle = i.children('.item_title');
			var iContent = i.children('.item_content');
			var o = i.length != 0 ? i.siblings('.item') : $("#" + setObj.setid + " .item");
			var oTitle = o.children('.item_title');
			var oContent = o.children('.item_content');
			iContent.slideDown();
			oContent.slideUp();
		}
	};

	$(this).map(function() {
		var _t = $(this),
			labelData = _t.data('buijs_accordion') || {},
			setting = {},
			setObj = {};
		defaults = {
			setid: 'buijs_accordion_' + $(this).length,
			isclose: true,
			activeTitleStyle: 'bui_tc_red'
		}
		setting = $.extend(defaults, options);
		setObj.setid = _t.attr('buijs_accordion_setid') || labelData.setid || setting.setid;
		setObj.isclose = _t.attr('buijs_accordion_isclose') || labelData.isclose || setting.isclose;
		setObj.activeTitleStyle = _t.attr('buijs_accordion_activeTitleStyle') || labelData.activeTitleStyle || setting.activeTitleStyle; +

		//初始化
		_t.attr('id', setObj.setid);
		_t.find('.item_content').hide();
		_t.find('.item_title').css({
			'cursor': 'pointer',
		});
		buijs_accordion.action(setObj);

		_t.find('.item').map(function(index, ele) {
			var i = $(this);
			var o = i.siblings('.item');
			var pi = i.parents('.item');
			var po = pi.siblings('.item');
			i.children('.item_title').unbind().bind('click', function() {
				i.toggleClass('active');
				pi.addClass('active');
				if(setObj.isclose == true) {
					o.removeClass('active');
					po.removeClass('active');
				};
				buijs_accordion.action(setObj); //执行
			})
		});
	});
};

/*判断窗口宽度*/
function buijs_auto_w() {
	var _WW = $(window).width();

	if(_WW >= 1200) {
		$('body').addClass('bui_body_lg').removeClass('bui_body_md bui_body_sm bui_body_xs');
	} else if(_WW < 1200 && _WW >= 992) {
		$('body').addClass('bui_body_md').removeClass('bui_body_lg bui_body_sm bui_body_xs');
	} else if(_WW < 992 && _WW >= 768) {
		$('body').addClass('bui_body_sm').removeClass('bui_body_lg bui_body_md bui_body_xs');
	} else if(_WW < 768) {
		$('body').addClass('bui_body_xs').removeClass('bui_body_lg bui_body_md bui_body_sm');
	}
};
//模拟滚动条
$.fn.buijs_scrollbar = function(options) {
	$(this).map(function(index, data) {
		var _t = $(this),
			labelData = _t.data('buijs_scrollbar') || {},
			defaults = {
				setid: 'buijs_scrollbar_' + index,
				positions: 'right',
				width: 4,
				style: 'bui_bgc_black_f12 bui_round',
				stylebg: '',
				mousewheel: true,
				touchwrap: false,
				touchbar: true,
				showAfter: function() {}
			},
			setting = $.extend(defaults, options),
			setObj = {};
		setObj.setid = labelData.setid || _t.attr('buijs_scrollbar_setid') || setting.setid;
		setObj.positions = labelData.positions || _t.attr('buijs_scrollbar_positions') || setting.positions;
		setObj.width = labelData.width || _t.attr('buijs_scrollbar_width') || setting.width;
		setObj.style = labelData.style || _t.attr('buijs_scrollbar_style') || setting.style;
		setObj.stylebg = labelData.stylebg || _t.attr('buijs_scrollbar_stylebg') || setting.stylebg;
		setObj.mousewheel = labelData.mousewheel || _t.attr('buijs_scrollbar_mousewheel') || setting.mousewheel;
		setObj.touchwrap = labelData.touchwrap || _t.attr('buijs_scrollbar_touchwrap') || setting.touchwrap;
		setObj.touchbar = labelData.touchbar || _t.attr('buijs_scrollbar_touchbar') || setting.touchbar;
		setObj.showAfter = labelData.showAfter || _t.attr('buijs_scrollbar_showAfter') || setting.showAfter;

		if(_t.children('.buijs_scrollbar_box').length == 0) {
			_t.wrapInner('<div class="buijs_scrollbar_box" id="' + setObj.setid + '" style="position:relative;overflow:hidden;width:100%;height:100%;"></div>');
			$("#" + setObj.setid).wrapInner('<div class="buijs_scrollbar_wrap" style="position:relative;overflow:hidden;width:100%;height:100%;-webkit-overflow-scrolling:touch;"></div>');
			$("#" + setObj.setid + ' .buijs_scrollbar_wrap').wrapInner('<div class="buijs_scrollbar_inner"></div>');
			$("#" + setObj.setid).append('<div class="buijs_scrollbar_bar ' + setObj.style + '" style="cursor:pointer;display:none;width:' + setObj.width + 'px; height:100%; position:absolute;top:0;' + setObj.positions + ':0;z-index:1;"></div>' +
				'<div class="buijs_scrollbar_barbg ' + setObj.stylebg + '" style="width:' + setObj.width + 'px;height:100%;position:absolute;top:0;' + setObj.positions + ':0;bottom:0;z-index:0;"></div>');
		}

		buijs_scrollbar_resize(setObj);

		_t.find('.buijs_scrollbar_box,buijs_scrollbar_wrap,.buijs_scrollbar_inner,.buijs_scrollbar_bar').resize(function() {
			setTimeout(function() {
				buijs_scrollbar_resize(setObj);
				return false;
			}, 400)
		});
	});

	function buijs_scrollbar_resize(setObj) {
		var _box = $("#" + setObj.setid),
			_bar = _box.find('.buijs_scrollbar_bar'),
			_wrap = _box.find('.buijs_scrollbar_wrap'),
			_inner = _box.find('.buijs_scrollbar_inner');

		if(_wrap.height() < _inner.height()) {
			_bar.css({
				'height': (_wrap.height() / _inner.height()) * 100 + '%',
				'display': 'block'
			});
			if(_bar.position().top + _bar.height() > _wrap.height()) {
				_wrap.scrollTop(_inner.height() - _wrap.height());
			};
			buijs_scrollbar_event(setObj);
			setObj.showAfter();
			return false;
		} else if(_wrap.height() >= _inner.height()) {
			_bar.hide();
			setObj.showAfter();
			return false;
		};

	};

	function buijs_scrollbar_event(setObj) {
		var _box = $("#" + setObj.setid),
			_bar = _box.find('.buijs_scrollbar_bar'),
			_wrap = _box.find('.buijs_scrollbar_wrap'),
			_inner = _box.find('.buijs_scrollbar_inner'),
			_st = -_inner.position().top;
		_wrap.unbind();
		_wrap.bind('scroll', function() {
			console.log('1')
			_st = -_inner.position().top;
			_bar.css({
				'top': _st * [_wrap.height() / _inner.height()]
			});
		});

		//开启鼠标滚轮
		if(setObj.mousewheel && setObj.mousewheel != "false") {
			var _mst, _startY, _endY
			_wrap.bind({
				'mousewheel': function(e) {
					e.preventDefault();
					if(event.wheelDelta < 0) {
						_wrap.scrollTop(_st + 48);

					} else {
						_wrap.scrollTop(_st - 48);
					}
				},
				'DOMMouseScroll': function(e) {
					e.preventDefault();
					if(e.originalEvent.detail > 0) {
						_wrap.scrollTop(_st + 48);

					} else {
						_wrap.scrollTop(_st - 48);
					}
				},
				'touchstart': function(e) {
					e.preventDefault();
					_mst = _st;
					_startY = e.originalEvent.touches[0].pageY;
				},
				'touchmove': function(e) {
					e.preventDefault();
					_endY = e.originalEvent.touches[0].pageY
					_wrap.scrollTop(_mst + _startY - _endY);
				}
			});
		};

		//开启拖动滚动条
		if(setObj.touchbar && setObj.touchbar != "false") {
			_bar.bind({
				'mousedown': function(e) {
					e.preventDefault();
					_wrap.css('cursor', 'pointer');
					var mouseY_s = e.pageY,
						mouseY_m = '';
					var _wrapScrollTop = _wrap.scrollTop();
					$("html").bind({
						'mousemove': function(e) {
							e.preventDefault();
							mouseY_m = e.pageY
							_wrap.scrollTop(_wrapScrollTop + [+mouseY_m - mouseY_s] * [_inner.height() / _wrap.height()]);
						},
						'mouseup': function(e) {
							e.preventDefault();
							_wrap.css('cursor', '');
							$("html").unbind('mousemove');
						}
					});
				}
			});
		};

		//开启拖动窗口
		if(setObj.touchwrap && setObj.touchwrap != "false") {
			_wrap.bind({
				'mousedown': function(e) {
					e.preventDefault();
					_wrap.css('cursor', 'pointer');
					var mouseY_s = e.pageY,
						mouseY_m = '';
					var _wrapScrollTop = _wrap.scrollTop();
					$("html").bind({
						'mousemove': function(e) {
							e.preventDefault();
							mouseY_m = e.pageY
							_wrap.scrollTop(_wrapScrollTop - [+mouseY_m - mouseY_s]);
						},
						'mouseup': function(e) {
							e.preventDefault();
							_wrap.css('cursor', '');
							$("html").unbind('mousemove');
						}
					});
				}
			})
		};
	};
};

//幻灯片
var buijs_swiper = {}; //幻灯片组
$.fn.buijs_swiper = function(options) {
	$(this).map(function(index, data) {
		var _t = $(this);
		var labelData = _t.data('buijs_swiper') || {};
		var defaults = {
			setid: 'buiId_swiper_' + index,
			direction: 'h',
			nav: 'b',
			navSize: 8,
			navBgc: 'black_f48',
			navBgcA: 'red',
			index: false,
			btn: true,
			play: 0,
			speed: 300,
			loop: true,
			avg: 1,
			effect: 'slide',
			plr: 0,
			start: 0,
			lazyload: false
		};
		//合并对象
		var setObj = $.extend(defaults, options, labelData);

		//初始化样式
		_t.css({
			'position': 'relative',
			'width': '100%',
			'margin': '0 auto',
			'overflow': 'hidden',
			'z-index': 0
		});
		_t.children('.box').css({
			'position': 'relative',
			'width': '100%',
			'height': '100%',
			'z-index': 0
		});
		_t.children('.box').children('.item').css({
			'width': '100%',
			'height': '100%',
			'position': 'relative',
			'overflow': 'hidden',
			'float': 'left'
		});
		//设置id
		_t.attr('id', setObj.setid);
		//判断方向
		setObj.direction = setObj.direction == 'v' ? 'vertical' : 'horizontal';
		//判断是否带导航
		if(setObj.nav) {
			if(setObj.nav == 't') {
				var _navCss = 'left:0px;right:0px;top:0px;'
			} else if(setObj.nav == 'b') {
				var _navCss = 'left:0px;right:0px;bottom:0px;'
			}
			_t.append('<div class="buijs_swiper_nav bui_ta_c bui_inline bui_media bui_vm" style="position:absolute;' + _navCss + 'z-index:1;"></div>');
		};
		//判断是否带按钮
		if(setObj.btn) {
			_t.append('<button class="bui_btn bui_btn_48 bui_btn_sq bui_bgc_black_f48 buijs_swiper_btn_prev bui_hide_sm" style="position:absolute;top:50%;left:1rem;margin-top:-24px;"><i class="fa fa-angle-left"></i></button>' +
				'<button class="bui_btn bui_btn_48 bui_btn_sq bui_bgc_black_f48 buijs_swiper_btn_next bui_hide_sm" style="position:absolute;top:50%;right:1rem;margin-top:-24px;"><i class="fa fa-angle-right"></i></button>');
		};
		//判断是否使用lazyload
		if(setObj.lazyload) {
			_t.find('img').map(function() {
				$(this).attr('data-src', $(this).attr('src')).attr('src', '').addClass('swiper-lazy');
			});
		};
		buijs_loadFile('js', buijs_getFile.js.swiper, function() {
			new Swiper(_t, {
				longSwipesRatio: 0.01, //拖动促发距离
				wrapperClass: 'box', //内置盒子className
				slideClass: 'item', //图片item的className
				pagination: '#' + setObj.setid + ' > .buijs_swiper_nav', //导航路径
				bulletClass: 'nav_item', //导航item的className
				bulletActiveClass: 'active', //导航item选中状态的className
				paginationClickable: true, //开启导航nav点击跳转
				slideToClickedSlide: true, //开启点击item图片跳转
				autoHeight: true,
				direction: setObj.direction, //滚动方向
				loop: setObj.loop, //开启循环
				slidesPerView: setObj.avg, //每屏幕显示item数量
				spaceBetween: setObj.plr, //每个item之间的距离 px
				setWrapperSize: true, //优化flexbox布局
				loopAdditionalSlides: 0, //前后复制item,作用于loop无缝循环
				grabCursor: true, //鼠标指针变成手
				autoplay: Number(setObj.play), //自动播放时间
				speed: Number(setObj.speed), //滚动速度
				autoplayDisableOnInteraction: false, //操作时是否停止自动播放
				effect: setObj.effect, //效果
				initialSlide: setObj.start, //起始item数
				lazyLoading: setObj.lazyload, //
				//初始化
				onInit: function(swiper) {
					buijs_swiper[setObj.setid] = swiper
					_t.find("[data-bui_img],[data-buijs_img],[class*=buijs_img]").buijs_img();
					//切换nav效果
					changeNav();
					//定义按钮
					//左右按钮
					_t.find('.buijs_swiper_btn_prev').click(function() {
						swiper.slidePrev(function() {}, 100);
					});
					_t.find('.buijs_swiper_btn_next').click(function() {
						swiper.slideNext(function() {}, 100);
					});
					//显示页码
					if(setObj.index == true) {
						_t.append('<div class="index bui_p_12 bui_ta_r bui_tc_white bui_tsd_1" style="position:absolute;width:100%;bottom:0;" id="indexno' + _i++ + '"><span class="bui_ts_16"></span>/<span class="bui_ts_12"></span></div>');
						_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
						_t.find('.index .bui_ts_12').html(swiper.slides.length);
					};
					//尺寸变化
					_t.resize(function() {
						swiper.onResize()
					});
				},
				//滚动前
				onSlideChangeStart: function(swiper) {
					//切换nav效果
					changeNav();
				},
				//滚动后
				onSlideChangeEnd: function(swiper) {
					//显示页码
					if(setObj.index == true) {
						_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
					};
				}
			});

			//切换nav效果
			function changeNav() {
				$('#' + setObj.setid + '>.buijs_swiper_nav .nav_item').css({
					'width': setObj.navSize + 'px',
					'height': setObj.navSize + 'px',
					'cursor': 'pointer'
				}).addClass('bui_bgc_' + setObj.navBgc + ' bui_round bui_ml_6');
				$('#' + setObj.setid + '>.buijs_swiper_nav .nav_item.active').removeClass('bui_bgc_' + setObj.navBgc + '').addClass('bui_bgc_' + setObj.navBgcA).siblings().removeClass('bui_bgc_' + setObj.navBgcA).addClass('bui_bgc_' + setObj.navBgc + '');

			};
		});
	});
};

//获取url参数
function buijs_geturl(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
};
//设置url参数
function buijs_seturl(arg, arg_val) {
	var pattern = arg + '=([^&]*)';
	var replaceText = arg + '=' + arg_val;
	if(window.location.search.match(pattern)) {
		var tmp = '/(' + arg + '=)([^&]*)/gi';
		tmp = window.location.search.replace(eval(tmp), replaceText);
		return tmp;
	} else {
		if(window.location.search.match('[\?]')) {
			return window.location.search + '&' + replaceText;
		} else {
			return window.location.search + '?' + replaceText;
		}
	};
	return window.location.search + '\n' + arg + '\n' + arg_val;
};

$.fn.buijs_date = function(options) {
	var _t = $(this);
	buijs_loadFile('js', buijs_getFile.js.dataTimePicker, function() {
		_t.map(function() {
			var labelData = $(this).data('buijs_date') || {},
				setting = {},
				setObj = {};
			var defaults = {
				startDate: '',
				endDate: '',
				max: 4,
				min: 2,
				start: 2,
				today: false,
				positions: '',
				format: "yyyy-mm-dd",
			}
			setting = $.extend(defaults, options);
			setObj.startDate = labelData.startDate || setting.startDate;
			setObj.endDate = labelData.endDate || setting.endDate;
			setObj.max = labelData.max || setting.max;
			setObj.min = labelData.min || setting.min;
			setObj.start = labelData.start || setting.start;
			setObj.today = labelData.today || setting.today;
			setObj.positions = labelData.positions || setting.positions;
			setObj.format = labelData.format || setting.format;
			//开启插件
			$(this).datetimepicker({
				startDate: setObj.startDate,
				endDate: setObj.endDate,
				maxView: setObj.max,
				minView: setObj.min,
				startView: setObj.start,
				autoclose: true,
				language: 'cn',
				fontAwesome: true,
				todayBtn: setObj.today,
				format: setObj.format
			}).on({
				'show': function() {
					if(setObj.positions && $('#buijs_mask').length == 0) {
						$(".bui_date:last").after('<div class="bui_bgc_black_f72" id="buijs_mask" style="position:fixed;width:100%;height:100%;left:0;top:0;z-index:' + [$(".bui_date").css("z-index") - 1] + ';"></div>');
						$(".bui_date").addClass('active');
					}
				},
				'hide': function() {
					$(".bui_date").removeClass('active');
					$("#buijs_mask").remove();
				}
			});
			//处理样式
			$(".datetimepicker").addClass('bui_date ' + setObj.positions);
			$(".fa-arrow-left").removeClass('fa-arrow-left').addClass('fa-angle-left');
			$(".fa-arrow-right").removeClass('fa-arrow-right').addClass('fa-angle-right');
		});
	});
};

//开启loading
function buijs_showloading(bgc, msg) {
	var _msg = msg ? '<p class="bui_tc_white bui_plr_24">' + msg + '</p>' : '';
	if($("#buijs_loading").length != 0) {
		$("#buijs_loading").remove();
		insetHtml();
	} else {
		insetHtml();
	};

	function insetHtml() {
		if(!bgc) {
			$("body").append('<div id="buijs_loading" class="bui_ta_c bui_round" style="background-color:rgba(0,0,0,0.72);position:absolute;width:48px;height:48px;top: 50%;left:50%;margin: -24px 0 0 -24px;z-index: 100000;"><i class="fa fa-circle-o-notch fa-spin bui_ts_24_i bui_tc_white_i bui_p_12"></i></div>');
		} else {
			$("body").append('<div id="buijs_loading" class="bui_ta_c ' + bgc + ' bui_inline bui_vm" style="position:fixed;width:100%;height:100%;top:0;left:0;z-index: 100000;"><i style="width:0;height:100%"></i><div><i class="fa fa-circle-o-notch fa-spin bui_ts_32_i bui_tc_white_i bui_p_12"></i>' + _msg + '</div></div>');
		};
		$("#buijs_loading").bind({
			'mousewheel': function(e) {
				e.preventDefault();
			},
			'DOMMouseScroll': function(e) {
				e.preventDefault();
			},
			'touchmove': function(e) {
				e.preventDefault();
			}
		});
	}
};

//关闭loading
function buijs_closeloading() {
	$("#buijs_loading").remove();
};

//mask
function buijs_mask(options) {
	var setObj = {},
		defaults = {
			type: null,
			bgc: 'bui_bgc_black_f72',
			itemStyle: '',
			icon: '',
			content: '',
			timeout: 0
		};
	setObj = $.extend(defaults, options);

	//type
	if(setObj.type == 'loading') {
		setObj.bgc = '';
		setObj.itemStyle = 'bui_bgc_black_f72 bui_radius bui_tc_white bui_p_24';
		setObj.icon = 'fa fa-circle-o-notch fa-spin bui_ts_32_i';
		setObj.content = '数据加载中'
	} else if(setObj.type == 'loadingFull') {
		setObj.bgc = 'bui_bgc_black_f72';
		setObj.itemStyle = 'bui_tc_white bui_p_24';
		setObj.icon = 'fa fa-circle-o-notch fa-spin bui_ts_32_i';
		setObj.content = '数据加载中'
	}
	var bgcStyle = setObj.bgc.indexOf('white') <= -1 ? setObj.bgc + ' bui_tc_white' : setObj.bgc + ' bui_tc_black',
		iconHtml = setObj.icon ? '<i class="' + setObj.icon + '"></i>' : '',
		contentHtml = setObj.content ? '<div class="bui_pt_6 bui_ts_12">' + setObj.content + '</div>' : '';
	if($("#buijs_mask").length == 0) {
		$("body").append('<div id="buijs_mask" class="bui_ta_c bui_inline bui_vm ' + bgcStyle + '" style="position:fixed;width:100%;height:100%;top:0;left:0;z-index: 100000; transition: all 0.3s;-webkit-transition: all 0.3s;-moz-transition: all 0.3s;opacity:0;-moz-opacity:0;-webkit-opacity:0;transform:scale(1.2);-moz-transform:scale(1.2);-webkit-transform:scale(1.2);">' +
			'	<div style="width:0;height:100%"></div>' +
			'		<div class="' + setObj.itemStyle + '">' + iconHtml + contentHtml + '</div>' +
			'	<div style="width:0;height:100%"></div>' +
			'</div>');
		setTimeout(function() {
			$("#buijs_mask").css({
				'opacity': 1,
				'-moz-opacity': 1,
				'-webkit-opacity': 1,
				'transform': 'scale(1)',
				'-moz-transform': 'scale(1)',
				'-webkit-transform': 'scale(1)'
			});
		}, 0);

		//延时关闭
		if(setObj.timeout != 0) {
			setTimeout(function() {
				buijs_mask_close();
			}, setObj.timeout + 300)
		}

		//禁止滚动
		$("#buijs_mask").buijs_disable('scroll,click');
	};

};
buijs_mask_close = function() {
	$("#buijs_mask").css({
		'opacity': 0,
		'-moz-opacity': 0,
		'-webkit-opacity': 0,
		'transform': 'scale(1.2)',
		'-moz-transform': 'scale(1.2)',
		'-webkit-transform': 'scale(1.2)'
	});
	setTimeout(function() {
		$("#buijs_mask").remove();
	}, 300)

};

//禁止滚动
$.fn.buijs_disable = function(event) {
	var _t = $(this);
	if(event) {
		if(event.indexOf('scroll') != -1) {
			_t.bind({
				'mousewheel': function(e) {
					e.preventDefault();
				},
				'DOMMouseScroll': function(e) {
					e.preventDefault();
				},
				'touchmove': function(e) {
					e.preventDefault();
				}
			});
		};
		if(event.indexOf('click') != -1) {
			_t.mousedown(function(e) {
				e.preventDefault();
			})
			_t.mouseup(function(e) {
				e.preventDefault();
			})
			_t.bind({
				'touchstart': function(e) {
					e.preventDefault();
				},
				'touchend': function(e) {
					e.preventDefault();
				}
			})
		};
	};

};

//表单校验
$.fn.buijs_form_check = function(options) {
	$(this).map(function() {
		var form = $(this);
		var item = form.find('[buijs_form_item_type]');
		var defaults = {
			begin: false, //是否立即校验表单
			classerror: 'bui_bdc_red', //表单报错样式
			classsuccess: 'bui_bdc_green', //表单正确样式
			error: function() {
				buijs_alert({
					bgc: 'bui_bgc_red',
					content: 'Check failed'
				});
			},
			success: function() {
				buijs_alert({
					bgc: 'bui_bgc_green',
					content: 'Submit successfully!'
				});
			}
		};
		var labelData = form.data('buijs_form_check') || {};
		var setObj = $.extend(defaults, options, labelData);

		var _check = function(_t) {
			var _input = _t.find('input,select');
			var _tipsbox = _t.find('.buijs_form_item_tips');
			var _error = function(tips) {
				_input.addClass(setObj.classerror).removeClass(setObj.classsuccess);
				_t.addClass('buijs_form_item_error').removeClass('buijs_form_item_success');
				_tipsbox.html('<span class="bui_tc_red">' + tips + '</span>');
			};
			var _success = function(tips) {
				_input.removeClass(setObj.classerror).addClass(setObj.classsuccess);
				_t.addClass('buijs_form_item_success').removeClass('buijs_form_item_error')
				_tipsbox.html('<span class="bui_tc_green">' + tips + '</span>');
			};
			//格式校验-手机
			if(_t.attr('buijs_form_item_type').indexOf('mobile') != -1) {
				_input.attr('maxlength', 11);
				var rule = /1[3-8]+\d{9}/;
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Mobile Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'Mobile invalid';

				if(rule.test(_input.val()) == true && _input.val().length == 11) {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				};
			};
			//格式校验-邮箱
			if(_t.attr('buijs_form_item_type').indexOf('email') != -1) {
				var rule = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[A-Za-z0-9]+$/;
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Email Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'Email invalid';
				if(_input.val().search(rule) != -1) {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				};
			};
			//格式校验-网址
			if(_t.attr('buijs_form_item_type').indexOf('url') != -1) {
				if(_input.val().indexOf('://') != -1) {
					_input.val(_input.val().split('://')[1]);
				};
				var rule = /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/;
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Url Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'Url invalid';
				if(_input.val().search(rule) != -1) {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				};
			};

			//字符校验
			if(_t.attr('buijs_form_item_type').indexOf('number') != -1 || _t.attr('buijs_form_item_type').indexOf('letter') != -1 || _t.attr('buijs_form_item_type').indexOf('symbol') != -1 || _t.attr('buijs_form_item_type').indexOf('cn') != -1) {

				var _number = '',
					_letter = '',
					_symbol = '',
					_cn = '';
				var _tipSuccess = '',
					_tipError = '';
				//校验数字
				if(_t.attr('buijs_form_item_type').indexOf('number') != -1) {
					_number = '0-9\.\-';
					_tipSuccess = _t.attr('buijs_form_item_success') || 'Complete';
					_tipError = _t.attr('buijs_form_item_error') || 'Only input numbers';
				};
				//校验英文字母
				if(_t.attr('buijs_form_item_type').indexOf('letter') != -1) {
					_letter = 'A-Za-z';
					_tipSuccess = _t.attr('buijs_form_item_success') || 'Complete';
					_tipError = _t.attr('buijs_form_item_error') || 'Only input letters';
				};
				//校验符号
				if(_t.attr('buijs_form_item_type').indexOf('symbol') != -1) {
					_symbol = '\-\_\@';
					_tipSuccess = _t.attr('buijs_form_item_success') || 'Complete';
					_tipError = _t.attr('buijs_form_item_error') || 'Only input symbol';
				};
				//校验中文字母
				if(_t.attr('buijs_form_item_type').indexOf('cn') != -1) {
					_cn = '\u4E00-\uFA29';
					_tipSuccess = _t.attr('buijs_form_item_success') || 'Complete';
					_tipError = _t.attr('buijs_form_item_error') || 'Only input Chinese characters';
				};
				var _rule = '^[' + _number + _letter + _symbol + _cn + ']*$';
				var rule = new RegExp(_rule);
				if(rule.test(_input.val()) && _input.val() != '') {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				};
			};

			//校验length
			if(_t.attr('buijs_form_item_type').indexOf('length') != -1) {
				var min = _t.attr('buijs_form_item_length').split(',')[0];
				var max = _t.attr('buijs_form_item_length').split(',')[1];
				_input.attr('maxlength', max);
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'At least ' + max + ' up to ' + min;
				if(_input.val().length >= Number(min) && _input.val().length <= Number(max)) {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				}
			};

			//select校验
			if(_t.attr('buijs_form_item_type').indexOf('select') != -1) {
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'Please choose one of them';
				if(!_input.val() || _input.val() == '' || _input.val() == null || _input.val() == 'null' || _input.val() == false || _input.val() == 'false') {
					_error(_tipError);
				} else {
					_success(_tipSuccess);
				}
			};

			//checkbox校验
			if(_t.attr('buijs_form_item_type').indexOf('checkbox') != -1) {
				var min = _t.attr('buijs_form_item_length').split(',')[0];
				var max = _t.attr('buijs_form_item_length').split(',')[1];
				var _tipSuccess = _t.attr('buijs_form_item_success') || 'Complete',
					_tipError = _t.attr('buijs_form_item_error') || 'At least ' + max + ' up to ' + min;
				if(_t.find('input:checked').length >= Number(min) && _t.find('input:checked').length <= Number(max)) {
					_success(_tipSuccess);
				} else {
					_error(_tipError);
				}
			};
		};

		//初始校验
		if(setObj.begin == true || setObj.begin == 'true') {
			item.map(function() {
				_check($(this))
			});
		};

		//单个校验
		item.map(function() {
			var _t = $(this);
			var _type = _t.attr('buijs_form_item_type');
			if(_type == 'checkbox') {
				_t.find('input[type=checkbox]').on('change', function() {
					_check(_t);
				});
			} else if(_type == 'select') {
				_t.bind('mouseup', function() {
					_check(_t);
				});
			} else {
				_t.bind('input', function() {
					_check(_t);
				});
			}

		});

		//整单校验
		form.bind({
			'submit': function(e) {
				e.preventDefault();
				item.map(function() {
					_check($(this));
				});
				setTimeout(function() {
					if(form.find('.buijs_form_item_error').length >= 1) {
						setObj.error();
					} else {
						setObj.success();
					}
				}, 0)
			},
			'reset': function(e) {
				setTimeout(function() {
					item.map(function() {
						_check($(this))
					});
				}, 0)

			}
		})

	});
};

//表单滚动 by 永远的小白哥
function buijs_ipt_scroll() {
	$(".bui_mo_b input[type=text],.bui_mo_b input[type=tel],.bui_mo_b textarea,.bui_mo_b select").focus(function() {
		var _box = $(this).parents('.bui_mo_b');
		var _hh = $(this).parents('.bui_mo').find('.bui_mo_h').height();
		var _tt = $(this).offset().top;
		_box.animate({
			'scrollTop': [_box.scrollTop() + _tt - _hh - 12]
		});
	});
};

//右侧滑动弹出框筛选 by 永远的小白哥
var bui_side_search = '<div class="bui_p_12"><input type="text" placeholder="Search" name="bui_side_search" value="" class="bui_ipt_48 bui_radius bui_bgc_white bui_block" /></div>';

function buijs_side_search(target, placeholder) {
	if(placeholder) {
		$("[name=bui_side_search]").attr('placeholder', placeholder);
	}
	$("[name=bui_side_search]").keyup(function() {
		var _val = $(this).val().toLowerCase();
		var _item = target.find('.item');
		if(_val == '' || _val == null) {
			_item.show();
		} else {
			_item.map(function() {
				if($(this).html().toLowerCase().indexOf(_val) >= 0) {
					$(this).show();
				} else {
					$(this).hide();
				};
			});
		};
	});
};

//检测滚动到底部   by pangxj
$.fn.buijs_scrollTobottom = function(callback) {
	var _box = $(this);
	if(_box.find('#buijs_InnerBox').length == 0) {
		$(this).wrapInner('<div id="buijs_InnerBox"></div>');
	}
	var _InnerBox = $("#buijs_InnerBox");
	scroll();
	_box.scroll(function() {
		scroll();
	});

	function scroll() {
		var _boxH = _box.height();
		var _boxS = _box.scrollTop();
		var _boxC = _InnerBox.height();
		if(_boxS >= (_boxC - _boxH)) {
			callback();
		};
	};
};
//画布比例
$.fn.buijs_ratio = function(options) {
	var defaults = {
		x: 4,
		y: 3,
		after: function() {}
	}
	var sets = $.extend(defaults, options);
	$(this).map(function() {
		var _w = $(this).width();
		$(this).height(_w * sets.y / sets.x);
		$(this).resize(function() {
			var _w = $(this).width();
			$(this).height(_w * sets.y / sets.x);
		})
		sets.after();
	});
}

//时间戳转换
function buijs_formatTime(dateTime, format) {
	var date = new Date(dateTime);
	var year = date.getFullYear(); //获取年
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1; //获取月
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(); //获取日
	var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours(); //获取时
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes(); //获取分
	var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds(); //获取秒

	if(format == 'time') {
		return year + '-' + month + '-' + currentDate + ' ' + hours + ':' + minute + ':' + seconds;
	} else {
		return year + "-" + month + "-" + currentDate;
	};
};

$(document).ready(function() {
	//加载图标库
	buijs_loadFile('css', buijs_getFile.css.fontAwesome);
	buijs_loadFile('css', buijs_getFile.css.bicon);

	/*判断窗口宽度*/
	buijs_auto_w();
	/*共用tab*/
	$('[data-bui_tab]').buijs_tab();
	//新图片处理
	$('[data-bui_img]').buijs_img();
	//滚动固定
	$('[data-bui_fixed]').buijs_fixed();
	//幻灯片 低版本IE不执行
	if(!+[1, ]) {} else {
		$('[data-buijs_swiper]').buijs_swiper();
	};
	//日期插件
	$("[data-bui_date]").buijs_date();
	//锚链接
	$('[data-buijs_anchor]').buijs_anchor();
	//滑屏
	$('[data-pageswiper]').each(function() {
		var _T = $(this);
		var _Name = _T.data('pageswiper').name;
		var _Url = _T.data('pageswiper').url;
		_T.click(function() {
			$('body').append('<div class="PageSwiper bui_bgc_lgray" id="' + _Name + '"><div class="PageSwiperInset"></div></div>');
			var _Box = $('#' + _Name);
			_Box.find('.PageSwiperInset').load(_Url, function() {

				$('body').css({
					'overflow': 'hidden'
				});
				_Box.addClass('Open');

				$('.PageSwiperHeader .bui_mediaLeft').click(function() {
					$('body').css({
						'overflow': 'auto'
					});
					_Box.removeClass('Open');
				});
			});
		});
	});

	//内容图片处理
	$('.bui_content').each(function() {
		$(this).find('p:has(img)').css({
			'text-align': 'center',
			'text-indent': '0px'
		});
		$(this).find('img').lazyload({
			effect: 'fadeIn',
			failurelimit: 10,
		});
	});

	/*锚链接*/
	$('[data-scroll-link]').click(function() {
		var _Name = $(this).attr('data-scroll-link');
		var _WT = $('[data-scroll-name="' + _Name + '"]').offset().top;
		$('html,body').animate({
			scrollTop: _WT
		});
	});
});

$(window).scroll(function() {
	/*滚动监听*/
	$('[data-scroll-name]').each(function() {
		var _T = $(this);
		var _TT = _T.offset().top;
		var _TB = _TT + _T.height();
		var _WT = $(window).scrollTop();
		var _Name = _T.attr('data-scroll-name');
		if(_WT >= _TT - 24 && _WT < _TB) {
			$('[data-scroll-link="' + _Name + '"]').parents('li').addClass('Active');
		} else {
			$('[data-scroll-link="' + _Name + '"]').parents('li').removeClass('Active');
		}
	});
});
$('body,html').resize(function() {
	/*判断窗口宽度*/
	buijs_auto_w();
});