var vm = {},
	vueData = {};

var global = {
	//语言
	language: {
		//获取
		get: function() {
			var language = window.localStorage.getItem('global.language');
			if(!language) {
				global.language.set('cn');
			};
			vm.$set('global.language', language);
		},
		//设置
		set: function(data) {
			window.localStorage.setItem('global.language', data);
			setTimeout(function() {
				global.language.get();
			})
		}
	},
	//vue
	vue: function(options) {
		var defaults = {
			el: 'html',
			data: vueData,
			ready: function() {
				var _t = this;
				_t.$set('global.ready', true);
				buijs.url.get(function(data) {
					_t.$set('url', data)
				})
			},
			created: function() {},
			methods: {
				//字符数控制
				strLength: function(string, length) {
					if(string) {
						var result = '';
						$.map(string.split(''), function(data, index) {
							if(index < length) {
								result += data
							}
						})
						return result;
					} else {
						return 'error'
					}
				},
				//时间转换
				moment: function(date, format, Invalid) {
					var formatData = format ? format : 'YYYY-MM-DD HH:mm:ss';
					return moment(date).format(formatData) != 'Invalid date' ? moment(date).format(formatData) : Invalid || '';
				},
				//语言切换
				language: function(cn, en) {
					if(this.global.language == 'en') {
						return en
					} else {
						return cn
					}

				}
			},
			components: {
				//页头
				'com-header': {
					name: 'com-header',
					props: ['channel'],
					data: {
						return: {
							nav: [],
						}
					},
					ready: function() {
						var _t = this;
						_t.$set('root', _t.$root.$data);
						_t.getNav();
					},
					methods: {
						//获取导航
						getNav: function() {
							var _t = this;
							buiVue.vueGetJson({
								url: workSpace.jsonUrl + '/module/front/findModuleByParentId',
								data: {
									parentId: 0
								},
								success: function(data) {
									_t.$set('nav', data)
								}
							})
						}
					},
					template: '' +
						'<header>' +
						'<div class="bui_bgc_silver_l">' +
						'	<div class="bui_media bui_vm bui_auto_w bui_p_6_tb">' +
						'		<div class="bui_media_b">' +
						'			<p class="bui_p_12">{{root.global.language==\'en\'?\'Welcome to \'+root.config.webName.en:\'欢迎来到\'+root.config.webName.cn}}</p>' +
						'		</div>' +
						'		<!--<div class="bui_media_r">' +
						'			<div class="bui_round bui_bds_1 bui_bdc_blue bui_inline bui_fl bui_at_noline" style="overflow:hidden;">' +
						'				<a href="javascript:global.language.set(\'cn\');" class="bui_fl bui_p_24_lr bui_p_6_tb bui_hover" :class="root.global.language==\'cn\'?\'bui_bgc_blue bui_fc_white\':\'bui_bgc_white bui_fc_blue\'">中文</a>' +
						'				<a href="javascript:global.language.set(\'en\');;" class="bui_fl bui_p_24_lr bui_p_6_tb bui_hover" :class="root.global.language==\'en\'?\'bui_bgc_blue bui_fc_white\':\'bui_bgc_white bui_fc_blue\'">English</a>' +
						'			</div>-->' +
						'		</div>' +
						'	</div>' +
						'</div>' +
						'<div class="bui_bgc_white">' +
						'	<div class="bui_media bui_vm bui_auto_w bui_p_24_tb">' +
						'		<div class="bui_media_b">' +
						'			<a href="index.html" :title="root.global.language==\'en\'?root.config.webName.en:root.config.webName.cn"><img src="{{root.config.logoUrl}}" /></a>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<form class="bui_bds_1 bui_bdc_yellow bui_round bui_p_3">' +
						'				<div class="bui_ipt bui_ipt_32 bui_bds_0 bui_round">' +
						'					<button type="submit" class="bui_ipt_icon_r bui_bgc_yellow bui_fc_white bui_round"><i class="fa fa-search fa-fw"></i></button>' +
						'					<input type="text" size="24" placeholder="{{root.global.language==\'en\'?\'Please input keywords\':\'请输入关键词\'}}" />' +
						'				</div>' +
						'			</form>' +
						'		</div>' +
						'	</div>' +
						'</div>' +
						'<hr class="bui_bdc_silver_l" />' +
						'<div class="bui_bgc_white">' +
						'	<div class="bui_auto_w bui_media bui_vm">' +
						'		<div class="bui_media_b bui_at_noline bui_fc_black_a bui_fc_blue_h bui_inline">' +
						'			<div v-for="data in nav.data | orderBy \'seqNum\'" v-if="data.event!=\'hidden\'" :class="$index==0?\'bui_bds_1_l\':null" class="bui_bds_1_r bui_bdc_silver_l">' +
						'				<a href="{{data.link}}" :target="data.link.indexOf(\'http://\')!=-1?\'_blank\':null" class="bui_block bui_p_24 bui_fc_black_a" :class="channel==data.code?\'active bui_fc_blue\':null" data-btn>{{data.name}}</a>' +
						'			</div>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<div class="bui_media bui_vm">' +
						'				<div class="bui_media_l"><i class="bi bi_phone_in_talk bui_fc_yellow bui_fs_32"></i></div>' +
						'				<div class="bui_media_b">' +
						'					<p>{{root.global.language==\'en\'?\'Hot Line\':\'咨询热线\'}}</p>' +
						'					<p class="bui_fc_yellow bui_fs_16">400-4000-4000</p>' +
						'				</div>' +
						'			</div>' +
						'		</div>' +
						'	</div>' +
						'</div>' +
						'<hr class="bui_bdc_silver_l" />' +
						'<template v-for="data in nav" v-if="channel==data.channel"><img :src="data.banner" class="bui_block"/></template>' +
						'</header>'

				},
				//页脚
				'com-footer': {
					name: 'com-footer',
					props: [],
					data: function() {
						return {}
					},
					ready: function() {
						var _t = this
						_t.$set('root', _t.$root.$data);
						buiVue.vueGetJson({
							url: workSpace.jsonUrlDemo + 'social.json',
							success: function(data) {
								_t.$set('social', data)
							}
						})
					},
					template: '' +
						'<footer class="bui_fc_silver bui_fc_blue_a">' +
						'	<div class="bui_media bui_vb bui_auto_w bui_p_48_tb">' +
						'		<div class="bui_media_b">' +
						'			<p class="bui_fs_16 ">' +
						'				{{root.global.language==\'en\'?\'Guangzhou Nensa Health Technology Co.,Ltd\':\'版权所有 广州灵鲨健康科技有限公司\'}}' +
						'			</p>' +
						'			<p class="bui_m_12_t">CopyRight ©' +
						'				<a href="http://www.nensahealth.com" title="NenSaHealth.Com">NenSaHealth.Com</a>' +
						'			</p>' +
						'		</div>' +
						'		<div class="bui_media_r">' +
						'			<div class="bui_inline bui_row_p_12">' +
						'				<li v-for="data in social">' +
						'					<a href="{{data.link}}" class="bui_btn bui_btn_32 bui_btn_sq bui_round bui_bds_2 bui_bdc_blue bui_bgc_blue_h bui_fc_white_h"><i class="{{data.icon}}"></i></a>' +
						'				</li>' +
						'			</div>' +
						'		</div>' +
						'	</div>' +
						'</footer>'
				},
				//侧边菜单
				'com-sidenav': {
					props: ['bartitle', 'color', 'array', 'activeid', 'page', 'key'],
					data: {
						return: {}
					},
					ready: function() {
						var _t = this;
						_t.$set('root', _t.$root.$data);
						_t.color = _t.color || 'blue'
					},
					methods: {},
					template: '' +
						'<dl class="bui_ta_c bui_at_noline">' +
						'	<dt class="bui_bgc_{{color}} bui_bds_1 bui_bdc_{{color}} bui_fc_white bui_p_12 bui_ta_c bui_radius bui_fs_16">{{bartitle}}</dt>' +
						'	<dd v-if="array.length!=0" class="bui_bgc_silver_l bui_m_12_t bui_radius bui_bds_1 bui_bdc_silver_l" style="overflow: hidden;">' +
						'		<template v-for="data in array">' +
						'			<li class="bui_bgc_white_f" :class="$index!=0?\'bui_bds_1_t bui_bdc_silver_l\':null">' +
						'				<a href="{{page?page:\'\'}}?{{key||\'typeId\'}}={{data.id}}" class="bui_media bui_vm bui_p_12" :class="activeid==data.id?\'bui_bgc_\'+color+\' bui_fc_white\':\'bui_bgc_white_h bui_fc_black\'">' +
						'					<div class="bui_media_b">' +
						'						{{root.global.language==\'en\'?data.subTitle:data.title}}' +
						'					</div>' +
						'					<div class="bui_media_r">' +
						'						<i class="fa fa-angle-right fa-fw"></i>' +
						'					</div>' +
						'				</a>' +
						'			</li>' +
						'		</template>' +
						'	</dd>' +
						'</dl>'
				},
				//报错
				'com-unfind': {
					name: 'com-unfind',
					props: ['class', 'style'],
					template: '' +
						'<div class="bui_ta_c bui_inline bui_vm {{class}}" style="{{style}}">' +
						'	<i style="width:0;height:100%;"></i>' +
						'	<div>' +
						'		<i class="fa fa-unlink bui_fs_48 bui_fc_silver"></i>' +
						'		<p class="bui_fs_24 bui_fc_silver bui_m_12_t">该页面失踪了...</p>' +
						'	</div>' +
						'	<i style="width:0;height:100%;"></i>' +
						'</div>'
				},
				//分页
				'com-page': {
					name: 'com-page',
					props: ['data', 'size', 'action'],
					data: function() {
						return {
							array: {
								prev: [],
								next: []
							}
						}
					},
					ready: function() {
						var _t = this;
						setTimeout(function() {
							_t.changeArray();
						}, 300)
					},
					methods: {
						//计算前后长度
						changeArray: function() {
							var _t = this;
							_t.array = {
								prev: [],
								next: []
							};
							//next
							for(i = _t.data.pageNo; i < [_t.data.pageNo + _t.size + 1]; i++) {
								if(i > _t.data.pageNo && i <= _t.data.pageCount) {
									_t.array.next.push(i);
								}
							}
							//prev
							for(i = _t.data.pageNo - _t.size; i < _t.data.pageNo; i++) {
								if(i > 0) {
									_t.array.prev.push(i);
								}
							}
						},
						//跳转
						goTo: function(pageNo) {
							buijs.url.set({
								pageNo: pageNo
							});
						}
					},
					template: '' +
						'<template v-if="data.pageNo>1">' +
						'	<a href="javascript:;" @click="goTo(1)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d"><i class="bi bi_first_page"></i></a>' +
						'	<a href="javascript:;" @click="goTo(data.pageNo-1)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d"><i class="bi bi_navigate_before"></i></a>' +
						'</template>' +
						'<template v-for="i in array.prev">' +
						'	<a href="javascript:;" @click="goTo(i)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d">{{i}}</a>' +
						'</template>' +
						'<a href="javascript:;" class="bui_btn bui_btn_24 bui_bgc_blue bui_fc_white">{{data.pageNo}}</a>' +
						'<template v-for="i in array.next">' +
						'	<a href="javascript:;" @click="goTo(i)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d">{{i}}</a>' +
						'</template>' +
						'<template v-if="data.pageNo<data.pageCount">' +
						'	<a href="javascript:;" @click="goTo(data.pageNo+1)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d"><i class="bi bi_navigate_next"></i></a>' +
						'	<a href="javascript:;" @click="goTo(data.pageCount)" class="bui_btn bui_btn_24 bui_fc_blue_h bui_fc_silver_d"><i class="bi bi_last_page"></i></a>' +
						'</template>'
				}
			},
			directives: {
				'bluebox': function() {
					var _t = $(this.el);
					_t.addClass('bui_bgc_blue bui_radius bui_p_6');
					_t.wrapInner('<div class="bui_bgc_white bui_radius bui_p_32"></div>')
				}
			}
		};
		var setObj = $.extend(defaults, options);
		vm = new Vue(setObj);
	}
};
$(document).ready(function() {

	global.vue(); //vue
	global.language.get(); //获取当前语言
	//加载网站配置
	vm.$set('config', {
		webName: {
			cn: "灵鲨",
			en: 'NensaHealth'
		},
		logoUrl: 'images/logo.png'
	})
})