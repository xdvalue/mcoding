// JavaScript Document
//resize
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
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.add(l);
			$.data(this, d, {
				w: l.width(),
				h: l.height()
			});
			if (a.length === 1) {
				g()
			}
		},
		teardown: function() {
			if (!e[f] && this[k]) {
				return false
			}
			var l = $(this);
			a = a.not(l);
			l.removeData(d);
			if (!a.length) {
				clearTimeout(i)
			}
		},
		add: function(l) {
			if (!e[f] && this[k]) {
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
			if ($.isFunction(l)) {
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
				if (m !== o.w || l !== o.h) {
					n.trigger(j, [o.w = m, o.h = l])
				}
			});
			g()
		}, e[b])

	}
})(jQuery, this);

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
			if (direction == 'left' && _ex - _sx >= range && callback) {
				callback.call($(this));
			};
			//右滑
			if (direction == 'right' && _sx - _ex >= range && callback) {
				callback.call($(this));
			};
			//上滑
			if (direction == 'up' && _sy - _ey >= range && callback) {
				callback.call($(this));
			};
			//下滑
			if (direction == 'down' && _ey - _sy >= range && callback) {
				callback.call($(this));
			};
		}
	})
};

//图片处理
$.fn.buijs_img = function(options) {

	$(this).each(function() {
		var _box = $(this);
		var _img = _box.children('img');
		//使用标签
		if (_box.attr('data-bui_img') != undefined) {
			var _data_center, _data_lazy, _data_alt, _data_hover
			_data_center = _box.data('bui_img').center || 'cut';
			_data_alt = _box.data('bui_img').alt || false;
			_data_hover = _box.data('bui_img').hover || false;
		}
		//不使用标签
		if (_box.attr('data-bui_img') == undefined) {
			_box.css({
				'display': 'block',
				'overflow': 'hidden'
			});
			var defaults = {
				center: false,
				alt: false,
				hover: false
			};
		}
		var sets = $.extend(defaults, options);
		var _center = _data_center || sets.center
		var _alt = _data_alt || sets.alt
		var _hover = _data_hover || sets.hover

		//使用lazyload
		if (_img.attr('data-src') && !_img.attr('src')) {
			buijs_img_lazy(_box, _img, _center);
		};
		if (!_img.attr('data-src') && _img.attr('src')) {
			buijs_img_c(_box, _img, _center);
			_img.load(function() {
				buijs_img_c(_box, _img, _center);
			})

		};


		//窗口尺寸
		$(window).resize(function() {
			buijs_img_c(_box, _img, _center);
		});
		//鼠标经过效果
		if (_hover != false) {
			buijs_img_hover(_box, _img, _hover);
		};
		//显示alt
		if (_alt != false) {
			buijs_img_alt(_box, _img, _alt);
		};

	});

	//lazyload
	function buijs_img_lazy(_box, _img, _center) {
		//使用lazy
		_img.lazyload({
			threshold: 0,
			failure_limit: 100,
			data_attribute: "src",
			placeholder: "data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==",
			effect: "show",
			skip_invisible: true,
			load: function() {
				buijs_img_c(_box, _img, _center);
			}
		});
	};
	//center开启
	function buijs_img_c(_box, _img, _center) {
		var _box_w = _box.width(),
			_box_h = _box.height(),
			_box_p = _box_w / _box_h,
			_img_w = _img.width(),
			_img_h = _img.height(),
			_img_p = _img_w / _img_h;
		//裁剪
		if (_center == 'cut') {
			//水平裁剪
			if (_img_p >= _box_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h + 'px'
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
			};
			//垂直裁剪
			if (_img_p < _box_p) {
				_img.css({
					'width': _box_w + 'px',
					'height': 'auto',
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
			};
		};
		//匹配
		if (_center == 'inside') {
			//水平居中
			if (_box_p >= _img_p) {
				_img.css({
					'width': 'auto',
					'height': _box_h
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px',
					'margin-left': [_box_w - _img_w] / 2
				});
			};
			//垂直居中
			if (_box_p < _img_p) {
				_img.css({
					'width': _box_w,
					'height': 'auto'
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
			};
		}
		if (_center == 'imgShow') {
			//水平居中
			if (_box_p >= _img_p) {
				_img.css({
					'width': '100%'
				});
				_img_w = _img.width();
				_img.css({
					'margin-top': '0px'
				});
			};
			//垂直居中
			if (_box_p < _img_p) {
				_img.css({
					'width': _box_w,
					'height': 'auto'
				});
				_img_h = _img.height();
				_img.css({
					'margin-top': [_box_h - _img_h] / 2,
					'margin-left': '0px'
				});
			};
		}
		//显示图片;
		_img.css({
			'opacity': '1'
		});
	};

	//hover开启
	function buijs_img_hover(_box, _img, _hover) {
		_img.css({
			'transition': 'transform 0.3s',
			'-webkit-transition': 'transform 0.3s',
			'-moz-transition': 'transform 0.3s'

		});


		if (_hover == 'out') {
			_img.css('transform', 'scale(1)');
			//PC
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css('transform', 'scale(1.1)');
				}, function() {
					_img.css('transform', 'scale(1)');
				});
				_box.parents('a').hover(function() {
					_img.css('transform', 'scale(1.1)');
				}, function() {
					_img.css('transform', 'scale(1)');
				});
			};
			//移动
			if ($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css('transform', 'scale(1.1)');
					},
					'touchend': function() {
						_img.css('transform', 'scale(1)');
					}
				});
			};

		};
		if (_hover == 'in') {
			_img.css('transform', 'scale(1.1)');
			//PC
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.hover(function() {
					_img.css('transform', 'scale(1)');
				}, function() {
					_img.css('transform', 'scale(1.1)');
				});
				_box.parents('a').hover(function() {
					_img.css('transform', 'scale(1)');
				}, function() {
					_img.css('transform', 'scale(1.1)');
				});
			};
			//移动
			if ($('body').hasClass('bui_body_sm')) {
				_box.on({
					'touchstart': function() {
						_img.css('transform', 'scale(1)');
					},
					'touchend': function() {
						_img.css('transform', 'scale(1.1)');
					}
				});
			};
		};

	};
	//显示alt
	function buijs_img_alt(_box, _img, _alt) {
		var _alttext = _img.attr('alt');
		if (_alttext != '' && _alttext != undefined) {
			_box.append("<div class='bui_img_alt'><p>" + _alttext + "</p></div>");
			_box.css({
				'position': 'relative'
			});
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
			if (_alt == 'bottom') {
				_box.children('.bui_img_alt').css({
					'top': 'auto',
					'bottom': '0px',
					'left': '0px',
					'transform': 'translateY(100%)'
				});
				//PC
				if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
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
				if ($('body').hasClass('bui_body_sm')) {
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
			if (_alt == 'top') {
				_box.children('.bui_img_alt').css({
					'top': '0px',
					'bottom': 'auto',
					'left': '0px',
					'transform': 'translateY(-100%)'
				});
				//PC
				if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
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
				if ($('body').hasClass('bui_body_sm')) {
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
		if (_box.attr('data-bui_fixed') != undefined) {
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
			if (_top != 'false' && _bottom == 'false') {
				//顶部
				if (_win_t < _box_t - _top) {
					_item.css({
						'position': 'static',
						'z-index': _zindex,
						'width': 'auto',
						'top': 'auto',
						'left': 'auto'
					});
				};
				//滚动
				if (_win_t >= _box_t - _top) {
					_item.css({
						'position': 'fixed',
						'z-index': _zindex,
						'width': _box_w + 'px',
						'top': _top + 'px',
						'left': _box_l + 'px'
					});
				};
				//到达底部
				if (_topstop != 'false') {

					if (_win_t > _box_t + _box_h - _item_h - _top - _topstop) {
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
			if (_top == 'false' && _bottom != 'false') {
				//到达顶部
				if (_win_t - _bottom < _box_t + _item_h - _win_h) {
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
				if (_win_t - _bottom >= _box_t + _item_h - _win_h) {
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
				if (_win_t >= _box_t + _box_h - _win_h) {
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
			if (_scroll != 'false') {
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

/*锚链接动画*/
$.fn.buijs_anchor = function(options) {
	$(this).each(function(e) {
		var _t = $(this);
		var _url = _t.attr('href');
		var _data_top = _t.data('bui_anchor').top || 0
		_t.on({
			'click': function(e) {
				e.preventDefault();
				var _target = $(_url).offset().top || '0';

				$('html,body').animate({
					scrollTop: _target - _data_top + 'px'
				});
			}
		})
	});
};

/*操作变换*/
$.fn.buijs_mask = function() {
	$(this).each(function() {
		var _t = $(this);
		var _i = _t.children('li');
		var _data_event = _t.data('bui_mask').event || 'hover';
		var _data_show = _t.data('bui_mask').show || '0';
		var _data_back = _t.data('bui_mask').back || 'true';
		var _event = _data_event;
		var _show = _data_show;
		var _back = _data_back;

		//初始化
		buijs_mask_defautl();

		//操作
		if (_event == 'click') {
			_i.on({
				'click': function() {
					buijs_mask_active($(this));
				}
			});
		}
		if (_event == 'hover') {
			_i.each(function() {
				var _i = $(this);
				_i.hoverDelay({
					hoverEvent: function() {
						buijs_mask_active(_i);
					}
				});
				if (_back == 'true') {
					_t.hoverDelay({
						outEvent: function() {
							buijs_mask_defautl();
						}
					})
				};
			});
		}
		if (_event == 'tap') {
			_i.on({
				'touchend': function() {
					buijs_mask_active($(this));
				}
			});
		}

		//初始化
		function buijs_mask_defautl() {
			_i.children('.bui_mask_show').show();
			_i.children('.bui_mask_hide').hide();
			_i.eq(_show).children('.bui_mask_hide').show();
			_i.eq(_show).children('.bui_mask_show').hide();
		};

		//活动
		function buijs_mask_active(_i) {
			var _s = _i.children('.bui_mask_show');
			var _h = _i.children('.bui_mask_hide');
			var _os = _i.siblings('li').children('.bui_mask_show');
			var _oh = _i.siblings('li').children('.bui_mask_hide');
			_s.hide();
			_h.show(0, function() {
				$(this).find($('[data-bui_img]')).buijs_img();
			});
			_oh.hide();
			_os.show(0, function() {
				$(this).find($('[data-bui_img]')).buijs_img();
			});
		};
	});
};

/*模拟弹窗*/

function buijs_modal(options) {
	var buijs_modal_on
	var defaults = {
		positions: 'auto',
		title: '提示',
		content: '<div class="bui_m_24 bui_ta_c bui_fas_24 bui_fac_lgray"><i class="fa fa-circle-o-notch fa-spin"></i></div>',
		ajaxload: null,
		footer: '',
		width: '320px',
		height: 'auto',
		opened: function() {},
		closed: function() {}
	};
	var setting = $.extend(defaults, options);
	if (setting.positions == 'auto') {
		if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
			setting.positions = 'center'
		}
		if ($('body').hasClass('bui_body_sm')) {
			setting.positions = 'top'
		}
	};
	buijs_modal_on = true;
	buijs_modal_content();

	$(".bui_modal").show(0, function() {
		buijs_modal_resize();
		$(this).addClass('active');
		setTimeout(function() {
			$(".bui_modal_b").buijs_scrollbar();
		}, 300);
		window.location.hash = window.location.hash + '#buijs_modal';
		window.addEventListener('hashchange', function() {
			var _t = window.location.hash;
			if (_t.indexOf('#buijs_modal') <= -1) {
				buijs_modal_close();
			};
		});
	});
	//适应窗口
	$(window).resize(function() {
		if (buijs_modal_on == true) {
			buijs_modal_resize();
		}
		return false;
	});
	//关闭模拟弹窗
	$('.bui_modal_mask,.bui_modal_close').click(function() {
		window.history.go(-1);
	});
	//插入内容
	function buijs_modal_content() {
		//判断遮罩层是否存在
		if ($(".bui_modal_mask").length == 0) {
			$("body").append('<div class="bui_modal_mask bui_bgc_black_f72"></div>');
			$('.bui_modal_mask').fadeIn(100);
		}
		//判断模拟弹窗是否存在
		if ($(".bui_modal").length == 0) {
			$('body').append(
				'<div id="fadeModal" class="bui_modal ' + setting.positions + '" style="width:' + setting.width + ';">' +
				'<div class="bui_modal_h bui_ta_c"><div class="bui_p_12"><span>' + setting.title + '</span><a href="javascript:;" class="bui_btn_24 bui_fr bui_fac_black bui_modal_close"><i class="fa fa-close"></i></a></div><hr/></div>' +
				'<div class="bui_modal_b bui_p_12">' + setting.content + '</div>' +
				'<div class="bui_modal_f">' + setting.footer + '</div>' +
				'</div>');
		} else {
			$(".bui_modal").attr('class', 'bui_modal ' + setting.positions).width(setting.width);
			$(".bui_modal_h span").html(setting.title);
			$(".bui_modal_f").html(setting.footer);
		};
		//内容为字符串
		if (setting.content != null) {
			$(".bui_modal_b").html(setting.content);
		};
		//内容为ajax
		if (setting.ajaxload != null) {
			$('.bui_modal').attr('bui_modal_url', setting.ajaxload);
			$.ajax({
				type: "get",
				url: setting.ajaxload,
				async: true,
				success: function(data) {
					$('.bui_modal_b').html(data);
					return false;
				},
				error: function(data) {
					$('.bui_modal_b').html('<div class="bui_ta_c bui_tc_o24 bui_ptb_32"><h1 class="bui_btn_48 bui_btnsq bui_ts_32 bui_bgc_black bui_round">!</h1><p class="bui_mt_12">连接错误，请检测您的网络设置</p></div>');
					$('.bui_modal_f').html('');
					return false;
				}
			});
		};
	};
	//重置尺寸位置
	function buijs_modal_resize() {
		var _box = $(".bui_modal");
		var _h = _box.children('.bui_modal_h');
		var _b = _box.children('.bui_modal_b');
		var _f = _box.children('.bui_modal_f');
		if (_box.hasClass('center')) {
			if (_box.height() > $(window).height()) {
				_box.height("90%");
				_b.css({
					'height': _box.height() - _h.height() - _f.height(),
					'overflow': 'hidden'
				});
			}
			_box.css({
				'left': [
					[$(window).width() - _box.width()] / 2
				] + 'px',
				'top': [
					[$(window).height() - _box.height()] / 2
				] + 'px'
			});
		};
		if (_box.hasClass('bottom')) {
			_box.css({
				'width': '100%',
				'left': '0px',
				'bottom': '0px',
			});
		};
		if (_box.hasClass('top')) {
			_box.css({
				'width': '100%',
				'left': '0px',
				'top': '0px',
			});
		};
		if (_box.hasClass('left') || _box.hasClass('right')) {
			if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
				_box.width(setting.width);
			}
			if ($('body').hasClass('bui_body_sm')) {
				_box.width('100%');
			}
			_box.height('100%');
			_b.css({
				'height': _box.height() - _h.height() - _f.height(),
				'overflow': 'hidden'
			});
			if (_box.hasClass('left')) {
				_box.css({
					'left': '0px',
					'top': '0px',
				});
			};
			if (_box.hasClass('right')) {
				_box.css({
					'right': '0px',
					'top': '0px',
				});
			};
		};

	};
	//关闭弹窗
	function buijs_modal_close() {
		buijs_modal_on = false;
		$('.bui_modal').removeClass('active');
		$('.bui_modal_mask').fadeOut(300, function() {
			$('.bui_modal,.bui_modal_mask').remove();
			setting.closed();
		});
		window.removeEventListener('hashchange');
	};
}(jQuery);


//关闭弹窗
function buijs_modal_close(callback) {
	buijs_modal_on = false;
	$('.bui_modal').removeClass('active');
	$('.bui_modal_mask').fadeOut(300, function() {
		$('.bui_modal,.bui_modal_mask').remove();
		if (callback) {
			callback();
		}
	});
};

/*警告框*/
function buijs_alert(options) {
	var defaults = {
		positions: 'center',
		timeout: 2000,
		content: '<i class="fa fa-circle-o-notch fa-spin"></i>',
	};
	var setting = $.extend(defaults, options);
	$('body').append('<div class="bui_alert bui_bgc_black_f64 bui_radius">' +
		'<div class="bui_p_12 bui_ta_c bui_fas_24 bui_tc_white bui_fac_white">' + setting.content +
		'</div>' +
		'</div>');

	var _alert = $(".bui_alert");
	if (setting.positions == 'center') {
		_alert.css({
			'top': '50%',
			'left': '50%',
			'right': '',
			'bottom': '',
			'margin-top': -[_alert.height() / 2],
			'margin-left': -[_alert.width() / 2],
		});
	}
	if (setting.positions == 'top') {
		_alert.css({
			'top': '32px',
			'left': '32px',
			'right': '32px',
			'bottom': ''
		});
	}
	if (setting.positions == 'bottom') {
		_alert.css({
			'top': '',
			'left': '32px',
			'right': '32px',
			'bottom': '32px'
		});
	}
	_alert.addClass('active');
	if (setting.timeout != null && setting.timeout != 0) {
		setTimeout(function() {
			_alert.removeClass('active');
			setTimeout(function() {
				_alert.remove()
			}, 300)
		}, setting.timeout);
	}
};

//公用标签 新
$.fn.buijs_tip = function(options) {
	var _t = $(this);
	var defaults = {
		width: '240px',
		events: 'hover',
		positions: 'bottom',
		content: '<p class="bui_p_12 bui_ta_c"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_tc_o24"></i></p>'
	};
	var setting = $.extend(defaults, options);
	var _width = setting.width;
	var _events = setting.events;
	var _positions = setting.positions;
	var _content = setting.content;

	//判断操作监听
	if (_events == 'hover') {
		_t.hoverDelay({
			hoverEvent: function() {
				buijs_tip_open(_t);
				_t.addClass('bui_tip_on');
				$(".bui_tip").hover(function() {
					$(".bui_tip").addClass('bui_tip_on');
				}, function() {
					buijs_tip_close();
				});
			},
			outEvent: function() {
				if (!$(".bui_tip").hasClass('bui_tip_on')) {
					buijs_tip_close();
				};
			}
		});

	};
	//标签弹出
	function buijs_tip_open(_t) {
		_positions = setting.positions;
		if ($(".bui_tip").length == 0) {
			$('body').append('<div class="bui_tip bui_bgc_white bui_panel" style="width:' + _width + ';"><div class="bui_tip_inner">' + _content + '<div class="bui_tip_arrow"></div></div></div>');
		} else {
			buijs_tip_close();
			$('body').append('<div class="bui_tip bui_bgc_white bui_panel" style="width:' + _width + ';"><div class="bui_tip_inner">' + _content + '<div class="bui_tip_arrow"></div></div></div>');

		};
		var _tip = $(".bui_tip");
		var _set_t, _set_l, _tip_t, _tip_l;
		_set_t = _t.offset().top + _t.height() - $(window).scrollTop();
		_set_l = (_t.offset().left + (_t.width() / 2)) - (_tip.width() / 2);
		if (_positions == 'bottom') {
			if (_set_t + _tip.height() >= $(window).height()) {
				_positions = 'top';
			} else {
				_positions = 'bottom';
			};
		};
		if (_positions == 'top') {
			if (_set_t - _t.height() - _tip.height() <= $(window).scrollTop()) {
				_positions = 'bottom';
			} else {
				_positions = 'top';
			};
		};
		_tip.addClass(_positions);
		//底部显示
		if (_tip.hasClass('bottom')) {
			//超出窗口右侧
			if ($(window).width() < _tip.width() + _set_l) {
				_tip_t = _set_t;
				_tip_l = $(window).width() - _tip.width() - 12
			}
			//超出窗口左侧
			else if (_set_l < 0) {
				_tip_t = _set_t;
				_tip_l = 12
			}
			//窗口内
			else {
				_tip_t = _set_t;
				_tip_l = _set_l
			}
			//箭头位置
			_tip.find(".bui_tip_arrow").css({
				'top': '-4px',
				'left': _t.offset().left - _tip_l + (_t.width() / 2) - 4 + 'px',
				'border-width': '1px 0 0 1px'
			});
		};
		//顶部显示
		if (_tip.hasClass('top')) {
			_tip_t = _set_t - _t.height() - _tip.height();
			//超出窗口右侧
			if ($(window).width() < _tip.width() + _set_l) {
				_tip_l = $(window).width() - _tip.width() - 12
			}
			//超出窗口左侧
			else if (_set_l < 0) {
				_tip_l = 12
			}
			//窗口内
			else {

				_tip_l = _set_l
			}
			_tip.find(".bui_tip_arrow").css({
				'bottom': '-4px',
				'left': _t.offset().left - _tip_l + (_t.width() / 2) - 4 + 'px',
				'border-width': '0 1px 1px 0'
			});
		};

		//加载位置
		_tip.css({
			'top': _tip_t + 'px',
			'left': _tip_l + 'px'
		});
		_tip.addClass('active');
		//图片
		$(".bui_tip [data-bui_img]").buijs_img();
	};
	//标签关闭
	function buijs_tip_close() {
		$(".bui_tip").remove()
	};
};

//公共用tab
$.fn.buijs_tab = function() {
	$(this).each(function() {
		var _nav = $(this);
		var _box = $('#' + _nav.data('bui_tab').target);
		var _data_event = _nav.data('bui_tab').event || 'click';

		var _event = _data_event;

		//初始化
		if (_nav.attr('buijs_tab_default') == null)
			buijs_tab_default(_nav, _box);

		//操作
		if (_event == 'click') {
			_nav.children('*').click(function() {
				var _t = $(this);
				buijs_tab_active(_t, _box);
			});
		};
		if (_event == 'hover') {
			_nav.children('*').each(function() {
				var _t = $(this);
				_t.hoverDelay({
					hoverEvent: function() {
						buijs_tab_active(_t, _box);
					}
				});
			});
		};

		//初始化
		function buijs_tab_default(_nav, _box) {
			//初始化
			_nav.children('*:first').addClass('active').siblings('*').removeClass('active');
			_box.children('*').hide();
			_box.children('*:first').show();
		};
		//活动
		function buijs_tab_active(_t, _box) {
			var _index = _t.index();
			_t.addClass('active').siblings('*').removeClass('active');
			_box.children('*:eq(' + _index + ')').show(0, function() {
				$(this).find('[data-bui_img]').buijs_img();
			}).siblings('*').hide();

		};
	});
};
/*共用tab*/
function buijs_tab() {
	$('[data-bui_tab]').each(function(e) {
		var _T = $(this);
		var _Target = _T.data('bui_tab').target;
		var _B = $('#' + _Target)
		var _Event = _T.data('bui_tab').event;
		_T.children().first().addClass('active');
		_B.children().first().addClass('active');
		if (_Event == 'hover') {
			_T.children().on({
				'hover': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if (_Event == 'click') {
			_T.children().on({
				'click': function() {
					var _Index = $(this).index();
					$(this).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).addClass('active').siblings().removeClass('active');
					_B.children().eq(_Index).find('img').AutoCenter();
				}
			});
		};
		if (_Event == 'touch') {
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

/*判断窗口宽度*/
function bui_auto_w() {
	var _WW = $(window).width();

	if (_WW >= 1440) {
		$('body').addClass('bui_body_lg').removeClass('bui_body_md bui_body_sm');
	}
	if (_WW < 1440 && _WW >= 641) {
		$('body').addClass('bui_body_md').removeClass('bui_body_lg bui_body_sm');
	}
	if (_WW <= 640) {
		$('body').addClass('bui_body_sm').removeClass('bui_body_lg bui_body_md');
	}
};
//模拟滚动条
$.fn.buijs_scrollbar = function(options) {
	$(this).each(function() {
		var _t = $(this);
		if (_t.children('.bui_scrollbar_box').length == 0) {
			_t.wrapInner('<div class="bui_scrollbar_box" style="position:relative;width:100%;height:100%"></div>');
		}
		var _box = _t.children('.bui_scrollbar_box');
		if (_box.children('.bui_scrollbar_inner').length == 0) {
			_box.wrapInner('<div class="bui_scrollbar_inner"></div>');
		}
		var _inner = _box.children('.bui_scrollbar_inner');
		if (_box.children('.bui_scrollbar_bar').length == 0) {
			_box.append('<div class="bui_bgc_black_f12 bui_scrollbar_bar" style="cursor:pointer;display:none;width:4px; height:100%; position:absolute;top:0;right:0"></div>');
		}
		var _bar = _box.children('.bui_scrollbar_bar');
		_box.css({
			'overflow': 'hidden',
			'position': 'relative',
		});
		buijs_scrollbar_resize(_t, _box, _inner, _bar);
		buijs_scrollbar_event(_t, _box, _inner, _bar);
		_box.resize(function() {
			setTimeout(function() {
				buijs_scrollbar_resize(_t, _box, _inner, _bar);
				return false;
			}, 400)
		});
		_inner.resize(function(data) {
			setTimeout(function(data) {
				buijs_scrollbar_resize(_t, _box, _inner, _bar);
				return false;
			}, 400)
		});
	});

	function buijs_scrollbar_resize(_t, _box, _inner, _bar) {
		if (_box.height() < _inner.height()) {
			_bar.css({
				'height': (_box.height() / _inner.height()) * 100 + '%',
				'display': 'block'
			});
			if (_bar.position().top + _bar.height() > _box.height()) {
				_box.scrollTop(_inner.height() - _box.height());
			};
			return false;
		} else if (_box.height() >= _inner.height()) {
			_bar.hide();
			return false;
		};
	};

	function buijs_scrollbar_event(_t, _box, _inner, _bar) {
		var _st = -_inner.position().top;
		_box.unbind('mousewheel');
		_box.unbind('scroll');
		var _mst, _startY, _endY
		_box.bind({
			'mousewheel': function(e) {
				e.preventDefault();
				if (event.wheelDelta < 0) {
					_box.scrollTop(_st + 48);

				} else {
					_box.scrollTop(_st - 48);
				}
			},
			'DOMMouseScroll': function(e) {
				e.preventDefault();
				if (e.originalEvent.detail > 0) {
					_box.scrollTop(_st + 48);

				} else {
					_box.scrollTop(_st - 48);
				}
			},
			'scroll': function(e) {
				_st = -_inner.position().top;
				_bar.css({
					'top': _st + _st * [_box.height() / _inner.height()]
				});
			},
			'touchstart': function(e) {
				_mst = _st;
				_startY = e.originalEvent.touches[0].pageY;
			},
			'touchmove': function(e) {
				e.preventDefault();
				_endY = e.originalEvent.touches[0].pageY
				_box.scrollTop(_mst + _startY - _endY);
			}
		});
	};
};
//幻灯片
$.fn.buijs_swiper = function(options) {
	var _i = 0
	$(this).each(function() {
		var _t = $(this);
		if (_t.attr('data-bui_swiper')) {
			var _data_nav = _t.data('bui_swiper').nav;
			var _data_index = _t.data('bui_swiper').index;
			var _data_no = _t.data('bui_swiper').no;
			var _data_btn = _t.data('bui_swiper').btn;
			var _data_play = _t.data('bui_swiper').play;
			var _data_speed = _t.data('bui_swiper').speed;
			var _data_loop = _t.data('bui_swiper').loop;
			var _data_view = _t.data('bui_swiper').view;
			var _data_effect = _t.data('bui_swiper').effect;
			var _data_plr = _t.data('bui_swiper').plr;
		} else {
			_t.addClass('bui_swiper')
		};
		var defaults = {
			nav: 'true',
			index: false,
			no: 0,
			btn: 'true',
			play: 0,
			speed: 300,
			loop: true,
			view: 1,
			effect: 'slide',
			plr: 0
		};
		var sets = $.extend(defaults, options);

		var _nav = _data_nav || sets.nav;
		var _index = _data_index || sets.index;
		var _no = _data_no || sets.no;
		var _btn = _data_btn || sets.btn;
		var _play = _data_play || sets.play;
		var _speed = _data_speed || sets.speed;
		var _loop = _data_loop || sets.loop;
		var _view = _data_view || sets.view;
		var _effect = _data_effect || sets.effect;
		var _plr = _data_plr || sets.plr;


		if (_nav == 'true') {
			_t.append('<div class="nav" id="navno' + _i++ + '"></div>');
			var _navno = '#' + _t.find('.nav').attr('id');
		}
		if (_btn == 'true') {
			_t.append('<a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_prev bui_hide_sm"><i class="fa fa-angle-left"></i></a><a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_next bui_hide_sm"><i class="fa fa-angle-right"></i></a>');
		};

		var mySwiper = _t.swiper({
			wrapperClass: 'box',
			slideClass: 'item',
			initialSlide: _no,
			pagination: _navno,
			bulletClass: 'nav_item',
			bulletActiveClass: 'active',
			direction: 'horizontal',
			loop: _loop,
			slidesPerView: _view,
			spaceBetween: _plr,
			setWrapperSize: true,
			loopAdditionalSlides: 0,
			grabCursor: true,
			autoplay: parseInt(_play),
			speed: parseInt(_speed),
			autoplayDisableOnInteraction: false,
			effect: _effect,
			onInit: function(swiper) {
				_t.find('[data-bui_img]').buijs_img();
				//显示页码
				if (_index == true) {
					_t.append('<div class="index bui_p_12 bui_ta_r bui_tc_white bui_tsd_1" style="position:absolute;width:100%;bottom:0;" id="indexno' + _i++ + '"><span class="bui_ts_16"></span>/<span class="bui_ts_12"></span></div>');
					_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
					_t.find('.index .bui_ts_12').html(swiper.slides.length);
				};
			},
			onSlideChangeEnd: function(swiper) {
				//显示页码
				if (_index == true) {
					_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
				};
			},
		});
		_t.children('.swiperbtn_prev').click(function() {
			mySwiper.slidePrev();
		});
		_t.children('.swiperbtn_next').click(function() {
			mySwiper.slideNext();
		});

	});
};

//图片预览  by Gzr
$.fn.bui_swiperShowImg = function(options) {
	var _i = 0
	$(this).each(function() {
		var _t = $(this);
		if (_t.attr('data-bui_swiper')) {
			var _data_nav = _t.data('bui_swiper').nav;
			var _data_index = _t.data('bui_swiper').index;
			var _data_no = _t.data('bui_swiper').no;
			var _data_btn = _t.data('bui_swiper').btn;
			var _data_play = _t.data('bui_swiper').play;
			var _data_speed = _t.data('bui_swiper').speed;
			var _data_loop = _t.data('bui_swiper').loop;
			var _data_view = _t.data('bui_swiper').view;
			var _data_effect = _t.data('bui_swiper').effect;
			var _data_plr = _t.data('bui_swiper').plr;
		} else {
			_t.addClass('bui_swiper')
		};
		var defaults = {
			nav: 'true',
			index: false,
			no: 0,
			btn: 'true',
			play: 0,
			speed: 300,
			loop: true,
			view: 1,
			effect: 'slide',
			plr: 0
		};
		var sets = $.extend(defaults, options);

		var _nav = _data_nav || sets.nav;
		var _index = _data_index || sets.index;
		var _no = _data_no || sets.no;
		var _btn = _data_btn || sets.btn;
		var _play = _data_play || sets.play;
		var _speed = _data_speed || sets.speed;
		var _loop = _data_loop || sets.loop;
		var _view = _data_view || sets.view;
		var _effect = _data_effect || sets.effect;
		var _plr = _data_plr || sets.plr;


		if (_nav == 'true') {
			_t.append('<div class="nav" id="navno' + _i++ + '"></div>');
			var _navno = '#' + _t.find('.nav').attr('id');
		}
		if (_btn == 'true') {
			_t.append('<a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_prev bui_hide_sm"><i class="fa fa-angle-left"></i></a><a href="javascript:;" class="bui_btn_48 bui_btnsq swiperbtn_next bui_hide_sm"><i class="fa fa-angle-right"></i></a>');
		};

		var mySwiper = _t.swiper({
			wrapperClass: 'box',
			slideClass: 'item',
			initialSlide: _no,
			pagination: _navno,
			bulletClass: 'nav_item',
			bulletActiveClass: 'active',
			direction: 'horizontal',
			loop: _loop,
			slidesPerView: _view,
			spaceBetween: _plr,
			setWrapperSize: true,
			loopAdditionalSlides: 0,
			grabCursor: true,
			autoplay: parseInt(_play),
			speed: parseInt(_speed),
			autoplayDisableOnInteraction: false,
			effect: _effect,
			onInit: function(swiper) {
				_t.find('[data-bui_img]').buijs_img();
				//显示页码
				if (_index == true) {
					_t.append('<div class="index bui_p_12 bui_ta_r bui_tc_white bui_tsd_1" style="position:absolute;width:100%;bottom:0;" id="indexno' + _i++ + '"><span class="bui_ts_16"></span>/<span class="bui_ts_12"></span></div>');
					_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
					_t.find('.index .bui_ts_12').html(swiper.slides.length);
				};
			},
			onSlideChangeEnd: function(swiper) {
				//显示页码
				if (_index == true) {
					_t.find('.index .bui_ts_16').html(swiper.activeIndex + 1);
				};
			},
		});
		_t.children('.swiperbtn_prev').click(function() {
			mySwiper.slidePrev();
		});
		_t.children('.swiperbtn_next').click(function() {
			mySwiper.slideNext();
		});

	});
	$('.bui_swiper .item').css('overflow-y','scroll')
};

//处理url参数
function buijs_geturl(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
};

$.fn.buijs_date = function(options) {
	$(this).map(function() {
		var _t = $(this);
		if (_t.attr('data-bui_date')) {
			var _data_max = _t.data('bui_date').max;
			var _data_min = _t.data('bui_date').min;
			var _data_start = _t.data('bui_date').start;
			var _data_today = _t.data('bui_date').today;
			var _data_positions = _t.data('bui_date').positions;
			var _data_format = _t.data('bui_date').format;
		};
		var defaults = {
			max: 4,
			min: 2,
			start: 2,
			today: false,
			positions: '',
			format: "yyyy-mm-dd",
		}
		var setting = $.extend(defaults, options);
		var _max = _data_max || setting.max;
		var _min = _data_min || setting.min;
		var _start = _data_start || setting.start;
		var _today = _data_today || setting.today;
		var _positions = _data_positions || setting.positions;
		var _format = _data_format || setting.format;
		//开启插件
		_t.datetimepicker({
			maxView: _max,
			minView: _min,
			startView: _start,
			autoclose: true,
			language: 'cn',
			fontAwesome: true,
			todayBtn: _today,
			format: _format
		}).on({
			'show': function() {
				if (_positions) {
					$(".bui_date:last").after('<div class="bui_data_mask bui_bgc_black_f72" style="position:fixed;width:100%;height:100%;left:0;top:0;z-index:' + [$(".bui_date").css("z-index") - 1] + ';"></div>');
					$(".bui_date").addClass('active');
				}
			},
			'hide': function() {
				$(".bui_date").removeClass('active');
				$(".bui_data_mask").remove();
			}
		});
		//处理样式
		$(".datetimepicker").addClass('bui_date ' + _positions);
		$(".fa-arrow-left").removeClass('fa-arrow-left').addClass('fa-angle-left');
		$(".fa-arrow-right").removeClass('fa-arrow-right').addClass('fa-angle-right');

	});

};

//开启loading
function buijs_showloading(bgc,content) {
	if (!bgc) {
		$("body").append('<div id="bui_loading" class="bui_ta_c bui_round" style="background-color:rgba(0,0,0,0.72);position:absolute;width:48px;height:48px;top: 50%;left:50%;margin: -24px 0 0 -24px;z-index: 100000;"><i class="fa fa-circle-o-notch fa-spin bui_fas_24 bui_fac_white bui_p_12"></i></div>');
	} else {
		$("body").append('<div id="bui_loading" class="bui_ta_c bui_bgc_' + bgc + ' bui_inline bui_vm" style="position:fixed;width:100%;height:100%;top:0;left:0;z-index: 100000;"><i style="width:0;height:100%"></i><i class="fa fa-circle-o-notch fa-spin bui_fas_32 bui_fac_white bui_p_12"></i><p class="bui_tc_white">'+(content || "")+'</p></div>');
	};
	$("#bui_loading").bind({
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

//关闭loading
function buijs_closeloading() {
	$("#bui_loading").remove();
};

//表单相关_判断文本框位数
function buijs_formcheck_length(str, max, min) {
	min = min || 0;
	if (!max) {
		return false;
	}
	return str.length <= max && str.length >= min;
};
//表单相关_判断是否手机
function buijs_formcheck_mobile(_v) {
	var re = /1[3-8]+\d{9}/;
	return re.test(_v) && (_v.length == 11);
};

//表单相关_判断是否邮箱
function buijs_formcheck_email(_email) {
	if (_email.search(/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[A-Za-z0-9]+$/) != -1) {
		return true;
	}
	return false
};

//表单相关_判断是否数字
function buijs_formcheck_number(str) {
	if (str == "") {
		return false;
	}
	if (!/^[0-9]*$/.test(str)) {
		return false;
	}
	return true;
};

//表单滚动 by 永远的小白哥
function buijs_ipt_scroll() {

	$(".bui_mo_b input,.bui_mo_b textarea,.bui_mo_b select").focus(function() {
		var _box = $(this).parents('.bui_mo_b');
		var _hh = $(this).parents('.bui_mo').find('.bui_mo_h').height();
		var _tt = $(this).offset().top;
		_box.animate({
			'scrollTop': [_box.scrollTop() + _tt - _hh - 12]
		});
	});
};

//右侧滑动弹出框 by 永远的小白哥
function buijs_side_show(options) {
	var defaults = {
		setid: 'bui_sidePanel' + $(".bui_side_panel").length, //设置缺省id
		side: 'r', //左右弹出 r右侧,l左侧
		bgc: 'white_d12', //弹出框背景颜色
		barbgc: 'red', //页头颜色
		margin: 'ml_48', //边距
		title: '标题', //标题
		barbtn: '', //页头右侧html
		footer: false, //页脚html，不为false时自动会在bui_mo_b加上bui_mo_b_f占位
		content: '<p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p>', //默认内容html
		load: false, //ajax页面路径，此项不为false时，content失效
		bgmove: false, //原板块位移
		showAfter: function() {} //弹出后callback
	};
	var setting = $.extend(defaults, options);
	var bartc;

	//添加锚链接
	if (window.location.hash.indexOf('#' + setting.setid) <= -1) {
		window.location.hash = window.location.hash + '#' + setting.setid;
	}

	//判断导航颜色
	if (setting.barbgc.indexOf('white') == 0) {
		bartc = 'bui_tc_black bui_fac_black bui_atc_black';
	} else {
		bartc = 'bui_tc_white bui_fac_white bui_atc_white';
	};
	//判断是否已经存在侧边栏
	if ($(".bui_side_mask").length == 0) {
		$('body').append('<div class="bui_side_mask bui_bgc_black_f64 bui_side_close" style="position:fixed;top:0;bottom:0;left:0;right:0;z-index:8;"></div>');
	};
	$(".bui_wrap").append('<div class="bui_side_panel bui_' + setting.margin + ' bui_mo bui_mo_' + setting.side + ' bui_bgc_' + setting.bgc + ' bui_bsd_24" style="z-index:9" id="' + setting.setid + '">' +
		'<div class="bui_mo_h bui_media bui_vm bui_bgc_' + setting.barbgc + ' ' + bartc + '">' +
		'<div class="bui_media_l bui_p_0"><a href="javascript:;" class="bui_btn_48 bui_btnsq bui_side_close"><i class="fa fa-angle-left bui_fas_24"></i></a></div>' +
		'<div class="bui_media_b">' + setting.title + '</div>' +
		'<div class="bui_media_r">' + setting.barbtn + '</div>' +
		'</div>' +
		'<div class="bui_mo_b bui_mo_b_h bui_mo_b_f"><p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p></div>' +
		'<div class="bui_mo_f">' + setting.footer + '</div>' +
		'</div>');
	//顶部bar
	if (setting.title == false) {
		$("#" + setting.setid + " .bui_mo_b").removeClass('bui_mo_b_h');
		$("#" + setting.setid + " .bui_mo_h").remove();
	};
	//底部按钮
	if (setting.footer == false) {
		$("#" + setting.setid + " .bui_mo_b").removeClass('bui_mo_b_f');
		$("#" + setting.setid + " .bui_mo_f").remove();
	};
	setTimeout(function() {
		$("#" + setting.setid).addClass("active");
		if (setting.bgmove == true) {
			var _rSide
			if (setting.side == 'r') {
				_rSide = 'l'
			} else {
				_rSide = 'r'
			}
			$("#" + setting.setid).siblings('.bui_mo').addClass('bui_mo_' + _rSide + ' bui_' + setting.margin);
		}
	}, 5);
	//判断是否ajaxloading
	setTimeout(function() {
		if (setting.load != false) {
			$.ajax({
				type: "get",
				url: setting.load,
				async: true,
				cache: false,
				success: function(data) {
					$("#" + setting.setid + " .bui_mo_b").html(data);
					setting.showAfter();
				}
			});
		} else {
			$("#" + setting.setid + " .bui_mo_b").html(setting.content);
			setting.showAfter();
		};
	}, 5);

	//关闭滑动框
	$('.bui_side_close').unbind('click').bind({
		'click': function() {
			window.history.go(-1);
		}
	});
	$(".bui_side_panel:last-child").buijs_tap('left', 100, function() {
		window.history.go(-1);
	});
	window.addEventListener('hashchange', function() {
		var _t = window.location.hash;
		if (_t.indexOf('#' + setting.setid) <= -1 && $("#" + setting.setid).length != 0) {
			buijs_side_close(setting);
		};
	});
};

function buijs_side_close(setting) {
	$("#" + setting.setid).removeClass('active');
	if (setting.bgmove == true) {
		$("#" + setting.setid).siblings('.bui_mo').removeClass('bui_mo_l bui_mo_r bui_ml_48 bui_mr_48');
	};
	setTimeout(function() {
		$("#" + setting.setid).remove();
		if ($(".bui_side_panel.active").length == 0) {
			$(".bui_side_mask").remove();
		};
	}, 300);
	window.removeEventListener('hashchange');
};

//右侧滑动弹出框筛选 by 永远的小白哥
var bui_side_search = '<div class="bui_p_12"><input type="text" placeholder="Search" name="bui_side_search" value="" class="bui_ipt_48 bui_radius bui_bgc_white bui_block" /></div>';

function buijs_side_search(target, placeholder) {
	if (placeholder) {
		$("[name=bui_side_search]").attr('placeholder', placeholder);
	}
	$("[name=bui_side_search]").keyup(function() {
		var _val = $(this).val().toLowerCase();
		var _item = target.find('.item');
		if (_val == '' || _val == null) {
			_item.show();
		} else {
			_item.map(function() {
				if ($(this).html().toLowerCase().indexOf(_val) >= 0) {
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
	$(this).wrapInner('<div id="buijs_InnerBox"></div>');
	var _InnerBox = $("#buijs_InnerBox");
	_box.scroll(function() {
		scroll();
	});

	function scroll() {
		var _boxH = _box.height();
		var _boxS = _box.scrollTop();
		var _boxC = _InnerBox.height();
		if (_boxS >= (_boxC - _boxH)) {
			callback();
		};
	};
};

$(document).ready(function() {
	/*判断窗口宽度*/
	bui_auto_w();
	/*共用tab*/
	$('[data-bui_tab]').buijs_tab();
	//新图片处理
	$('[data-bui_img]').buijs_img();
	//滚动固定
	$('[data-bui_fixed]').buijs_fixed();
	//幻灯片 低版本IE不执行
	if (!+[1, ]) {} else {
		$('[data-bui_swiper]').buijs_swiper();
	};
	//日期插件
	$("[data-bui_date]").buijs_date();
	//锚链接
	$('[data-bui_anchor]').buijs_anchor();
	//锚链接
	$('[data-bui_mask]').buijs_mask();
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


	/*按钮相关*/
	$('[class*=bui_btn_],[class*=bui_btng_] > *').each(function() {
		var _T = $(this);
		_T.on({
			'mousedown': function() {
				_T.addClass('active')
			},
			'mouseup': function() {
				_T.removeClass('active')
			}
		})
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
		if (_WT >= _TT - 24 && _WT < _TB) {
			$('[data-scroll-link="' + _Name + '"]').parents('li').addClass('Active');
		} else {
			$('[data-scroll-link="' + _Name + '"]').parents('li').removeClass('Active');
		}
	});
});
$('body,html').resize(function() {
	/*判断窗口宽度*/
	bui_auto_w();
});