// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

	/**
	 * json日期格式转换
	 * @param cellval
	 * @returns {String}
	 */
	function changeDateFormat(dateTime) {
		var date = new Date(dateTime);
		//获取年
		var year = date.getFullYear();
		//获取月
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
				: date.getMonth() + 1;
		//获取日
		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
				.getDate();
		//获取时
		var hours = date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours();
		//获取分
		var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes();
		//获取秒
		var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds();

		 var sTime =  year + "年" + month + "月" + currentDate + "日 " + hours + ":" + minute + ":" + seconds;
		return sTime;
	}
	
	(function($) {
	    $.extend({
	        myTime: {
	            /**
	             * 当前时间戳
	             * @return <int>        unix时间戳(秒)  
	             */
	            CurTime: function(){
	                return Date.parse(new Date())/1000;
	            },
	            /**              
	             * 日期 转换为 Unix时间戳
	             * @param <string> 2014-01-01 20:20:20  日期格式              
	             * @return <int>        unix时间戳(秒)              
	             */
	            DateToUnix: function(string) {
	                var f = string.split(' ', 2);
	                var d = (f[0] ? f[0] : '').split('-', 3);
	                var t = (f[1] ? f[1] : '').split(':', 3);
	                return (new Date(
	                        parseInt(d[0], 10) || null,
	                        (parseInt(d[1], 10) || 1) - 1,
	                        parseInt(d[2], 10) || null,
	                        parseInt(t[0], 10) || null,
	                        parseInt(t[1], 10) || null,
	                        parseInt(t[2], 10) || null
	                        )).getTime() / 1000;
	            },
	            /**              
	             * 时间戳转换日期              
	             * @param <int> unixTime    待时间戳(秒)              
	             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
	             * @param <int>  timeZone   时区              
	             */
	            UnixToDate: function(unixTime, isFull, timeZone) {
	                if (typeof (timeZone) == 'number')
	                {
	                    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
	                }
	                var time = new Date(unixTime * 1000);
	                var ymdhis = "";
	                ymdhis += time.getUTCFullYear() + "-";
	                ymdhis += (time.getUTCMonth()+1) + "-";
	                ymdhis += time.getUTCDate();
	                if (isFull === true)
	                {
	                    ymdhis += " " + time.getUTCHours() + ":";
	                    ymdhis += time.getUTCMinutes() + ":";
	                    ymdhis += time.getUTCSeconds();
	                }
	                return ymdhis;
	            }
	        }
	    });
	})(jQuery);
