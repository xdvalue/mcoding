//模拟数据
var demoData = new Object();
//模拟数据_导航
demoData.topNav = [{
	"id": "1",
	"icon": "fa-home",
	"name": "首页",
	"channelName": "首页",
	"url": "index.html"
}, {
	"id": "2",
	"icon": "fa-gamepad",
	"name": "活动",
	"channelName": "活动",
	"url": "activity_list.html?typeid=1"
}, {
	"id": "3",
	"icon": "fa-globe",
	"name": "资讯",
	"channelName": "资讯",
	"url": "news_list.html?typeid=2"
}, {
	"id": "4",
	"icon": "fa-laptop",
	"name": "业务",
	"channelName": "业务",
	"url": "object_list.html?typeid=3"
}, {
	"id": "5",
	"icon": "fa-mortar-board",
	"name": "智库",
	"channelName": "智库",
	"url": "idea_list.html?typeid=4"
}, {
	"id": "6",
	"icon": "fa-line-chart",
	"name": "投资案例",
	"channelName": "投资案例",
	"url": "case_list.html?typeid=5"
}, {
	"id": "7",
	"icon": "fa-group",
	"name": "合作机构",
	"channelName": "合作机构",
	"url": "operation_list.html?typeid=10"
}];
//模拟数据_banner
demoData.banner = [{
	"id": "1",
	"url": "images/index_banner_001.jpg",
	"title": "80小时全程直播：小编带你逛CES",
	"infos": "一年一度的CES国际消费电子展又来了！作为全球科技圈的“开年大菜”，CES已经成为新一年科技产品和技术发展的风向标。网易科技联合网易汽车正在为大家直播。"
}, {
	"id": "2",
	"url": "images/index_banner_002.jpg",
	"title": "CES变车展 谁将主宰汽车未来？",
	"infos": "可以肯定是，在自动驾驶、新能源和车联网这三大趋势的影响下，汽车行业即将迎来一轮颠覆和洗牌，汽车厂商已经到了不变革就会死的关键时刻。"
}, {
	"id": "3",
	"url": "images/index_banner_003.jpg",
	"title": "华为Mate 8全球发布 荣耀正式进入美国",
	"infos": "华为此次发布的Mate 8国际版在国内于去年11月底亮相，据余承东透露，一个多月的销量已超过百万台，速度为Mate 7发布初期的10倍，这其中高配置版本销量更好。"
}, {
	"id": "4",
	"url": "images/index_banner_004.jpg",
	"title": "扳倒特斯拉? 乐视概念车发布",
	"infos": "该车并非出自与FF相同的VPA平台，而是款全新产品，设计出自宝马i3、i8概念车设计者Richard Kim之手。"
}, {
	"id": "5",
	"url": "images/index_banner_005.jpg",
	"title": "NVIDIA发布PX2车载电脑模块",
	"infos": "Drive PX2是去年推出的Drive PX的迭代产品，拥有12个CPU核心，采用Pascal GPU架构使得性能提升到8Teraflor。"
}];
//模拟数据_文章
demoData.article = [{
	"id": "1",
	"title": "体验设计，从相对中寻找绝对",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "四处看看，很容易就会看到移动设备几乎成为了人们第五大附属品。结果是组织中的智能手机和平板电脑产生了大量的",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img5.cache.netease.com/3g/2015/5/20/201505200822034f665.jpg"
}, {
	"id": "2",
	"title": "董事长发红包钓鱼：谁抢罚500",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "“前不久被员工拉进了群里，于是这次就用这个‘新颖’的方法来了个测试。”——董事长罗先生说，公司属于特殊行业，关系到千家万户安全，容不得一点马虎。上班抢红包的还是公司中层，怎么带员工？“抢还是不抢？”4日上午10点左右，杨海（化名）所在的微信",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img3.cache.netease.com/3g/2015/12/31/201512310755494c312.jpg"
}, {
	"id": "3",
	"title": "360好搜份额超35% 齐向东：如被骗 我全赔",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "1月6日消息，奇虎360旗下搜索产品，360好搜举行更名一周年用户见面会，并且发布了基于大数据分析产生的2015年度热搜榜单。360总裁齐向东透露，截止2015年底，好搜在PC端的市场份额已达到35%，每日搜索请求超过",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img6.cache.netease.com/3g/2015/12/3/2015120309231420877.jpg"
}, {
	"id": "4",
	"title": "去哪儿回应航司封杀：开展自查 启动新监管",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "针对近日连续收到多家航空公司暂停旗舰店的情况，去哪儿网发布声明，称目前已展开自查，并启动新一轮监管升级的举措。去哪儿网称，目前已向平台上全部代理人下发公告，要求代理人严格按照航司规范进行机票销售以及提供退改签服务。",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img5.cache.netease.com/3g/2016/1/6/2016010615454976f5d.jpg"
}, {
	"id": "5",
	"title": "三万元罚款管得住P2P平台？",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "郭峰/北京大学国家发展研究院博士后在之前的评论中，笔者认为《网络借贷信息中介机构业务活动管理暂行办法（征求意见稿）》（下称《监管办法》），对P2P平台的监管可能会造成几个后果：要么监管过严，大部分P2P平台被清退；要么监管流于形式，大部分平",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img4.cache.netease.com/3g/2016/1/6/20160106170120e1ed8.png"
}, {
	"id": "6",
	"title": "搜狗地图或在CES2016发布新技术 手表可控制汽车",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "每年一度的国际消费类电子产品展览会CES将于美国东部时间2016年1月6日在拉斯维加斯国际会展中心拉开帷幕，此次有关汽车的新技术、产品将成为“重头戏”。消息人士称，作为苹果和谷歌重要的手表地图合作方，搜狗地图或将在本届展会上联合博世等汽车零",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img4.cache.netease.com/3g/2016/1/2/20160102123112058d4.jpg"
}, {
	"id": "7",
	"title": "银客网宣布平台累计成交额破50亿元",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "1月6日消息，银客网在日前的两周年纪念日上宣布，其平台累计成交额突破50亿元。该公司称， 2015年，其平台累计成交额40.88亿元。截止目前，银客网注册用户近68万人次，累计为投资人创造理财收益2.5亿元，为14.9万家中小企",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img3.cache.netease.com/tech/2016/1/6/20160106134854a4909.jpg"
}, {
	"id": "8",
	"title": "艺龙任命周荣为首席运营官 ",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "1月6日消息，据美通社报道，艺龙当日宣布，任命周荣为首席运营官（COO），任命即日起生效。从2015年7月起，周荣一直担任该公司首席战略官。周荣有着超过10年的中国在线旅游业务经验。在加入艺龙前，他曾在安永会计师事务所担任高级审",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img1.cache.netease.com/tech/2016/1/6/201601061101344d4bd.jpg"
}, {
	"id": "9",
	"title": "逃房租将被降芝麻信用分",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "芝麻信用评分再度更新。此次芝麻评分更新主要体现在信用租房领域，首次对有不良记录的用户进行负面信息披露。公测以来，芝麻信用已经陆续接入租车、酒店、住宿等行业的商家，而用户使用这些服务的信息也会被反馈到芝麻评分当",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img3.cache.netease.com/tech/2016/1/6/201601061055252a9a2.jpg"
}, {
	"id": "10",
	"title": "奇虎360私有化获招行34亿美元债务融资",
	"shortTitle": '每日一讲 八卦转型+',
	"infos": "作为最大规模的中概股私有化交易，奇虎360的私有化进程备受瞩目，今日，网易财经从招商银行获悉，招银和另外两家股份制商业银行牵头为这笔私有化交易提供34亿美元的债务融资。据公开信息显示，自奇虎360宣布与公司董事",
	"author": "商存海",
	"time": "2016-01-01",
	"click": "4500",
	"url": "article_detail.html",
	"imgUrl": "http://img1.cache.netease.com/tech/2016/1/6/20160106110520a1b38.jpg"
}];
//模拟数据_tag
demoData.tag = [{
	"id": "1",
	"tag": "创新",
}, {
	"id": "2",
	"tag": "DT时代",
}, {
	"id": "3",
	"tag": "今日亮点",
}, {
	"id": "4",
	"tag": "每日一讲",
}, {
	"id": "5",
	"tag": "晨起观变",
}, {
	"id": "6",
	"tag": "IT转型",
}, {
	"id": "7",
	"tag": "信息化",
}, {
	"id": "8",
	"tag": "数据科技",
}, {
	"id": "9",
	"tag": "最新"
}];
//模拟数据_专家
demoData.user = [{
	"id": "	1	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_001.jpg	"
}, {
	"id": "	2	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_002.jpg	"
}, {
	"id": "	3	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_003.jpg	"
}, {
	"id": "	4	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_004.jpg	"
}, {
	"id": "	5	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_005.jpg	"
}, {
	"id": "	6	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_006.jpg	"
}, {
	"id": "	7	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_007.jpg	"
}, {
	"id": "	8	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_008.jpg	"
}, {
	"id": "	9	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_009.jpg	"
}, {
	"id": "	10	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_010.jpg	"
}, {
	"id": "	11	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_011.jpg	"
}, {
	"id": "	12	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_012.jpg	"
}, {
	"id": "	13	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_013.jpg	"
}, {
	"id": "	14	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_014.jpg	"
}, {
	"id": "	15	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_015.jpg	"
}, {
	"id": "	16	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_016.jpg	"
}, {
	"id": "	17	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_017.jpg	"
}, {
	"id": "	18	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_018.jpg	"
}, {
	"id": "	19	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_019.jpg	"
}, {
	"id": "	20	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_020.jpg	"
}, {
	"id": "	21	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_021.jpg	"
}, {
	"id": "	22	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_022.jpg	"
}, {
	"id": "	23	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_023.jpg	"
}, {
	"id": "	24	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_024.jpg	"
}, {
	"id": "	25	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_025.jpg	"
}, {
	"id": "	26	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_026.jpg	"
}, {
	"id": "	27	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_027.jpg	"
}, {
	"id": "	28	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_028.jpg	"
}, {
	"id": "	29	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_029.jpg	"
}, {
	"id": "	30	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_030.jpg	"
}, {
	"id": "	31	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_031.jpg	"
}, {
	"id": "	32	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_032.jpg	"
}, {
	"id": "	33	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_033.jpg	"
}, {
	"id": "	34	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_034.jpg	"
}, {
	"id": "	35	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_035.jpg	"
}, {
	"id": "	36	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_036.jpg	"
}, {
	"id": "	37	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_037.jpg	"
}, {
	"id": "	38	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_038.jpg	"
}, {
	"id": "	39	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_039.jpg	"
}, {
	"id": "	40	",
	"name": "	王重阳	",
	"infos": "	DT总裁兼常务总监	",
	"imgUrl": "	pic/face_040.jpg	"
}];
//模拟数据_投资案例
demoData.case = [{
	"id": "1",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_001.png"
},{
	"id": "2",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_002.png"
},{
	"id": "3",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_003.png"
},{
	"id": "4",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_004.png"
},{
	"id": "5",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_005.png"
},{
	"id": "6",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_006.png"
},{
	"id": "7",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_007.png"
},{
	"id": "8",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_008.png"
},{
	"id": "9",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_009.png"
},{
	"id": "10",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_010.png"
},{
	"id": "11",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_011.png"
},{
	"id": "12",
	"name": "万达实业",
	"infos": "大连实德足球俱乐部，简称实德，是一家位于辽宁省大连市的足球队，成立于2000年1月9日（前身大连万达足球俱乐部始建于1994年），是中国顶级联赛四大传统豪门之一（其他传统豪门为山东鲁能、上海申花、北京国安）。",
	"imgUrl": "pic/logo_012.png"
}];