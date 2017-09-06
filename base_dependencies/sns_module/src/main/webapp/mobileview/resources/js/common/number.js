function numberFrm (num, column) {
		// num 当前的值
		// column 列信息
//		if (!num)
//			return "$0.00";
//		num = num.toString().replace(/\$|\,/g, '');
//		if (isNaN(num))
//			num = "0.00";
//		sign = (num == (num = Math.abs(num)));
//		num = Math.floor(num * 100 + 0.50000000001);
//		cents = num % 100;
//		num = Math.floor(num / 100).toString();
//		if (cents < 10)
//			cents = "0" + cents;
//		for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
//			num = num.substring(0, num.length - (4 * i + 3)) + ','
//					+ num.substring(num.length - (4 * i + 3));
		return numberFrmNoD(num, column);
};

//所有行都需要带%
function appendPercen(num, column){
	if( num == null )
		return num;
	if( isNaN(num)&&num.indexOf("%")!=-1 ){
		reportNeedFrm(num, column);
	}else{
		num = reportNeedFrm(num + "%", column);
	}
	return num;
}

//只有%所在行需处理小数点后两位的时候，使用此类型
function reportNeedFrm(num, column){
	if( isNaN(num) && num.indexOf("%")!=-1 ){
		var result = numberFrmNoD(num, column);
		return result;
	}else{
		return num;
	}
}

//处理小数点，带%的数先乘1000000，无$符号
function numberFrmNoD (num, column) {
	// num 当前的值
	// column 列信息
	if(null==num)
		return "0.000000";
	var hasPercen = isNaN(num) ? num.indexOf("%")!=-1 : false;
	
	if (!num && !hasPercen)
		return "0.000000";
	
	if( hasPercen ){
		num = num.toString().replace(/%/,'');//去掉%，方便后面数据处理
		num = num * 100;//如果带%的，对数据进行*100
	}else{
		num = num.toString().replace(/\$|\|%,/g, '');
	}
	
	if (isNaN(num)){
		num = "-";
	}
	
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 1000000 + 0.50000000001);
	cents = num % 1000000; // 放大成整数的小数位值
	num = Math.floor(num / 1000000).toString();
    zeroNum = 6; // 最多需要添加的"0"的个数
    { 
    	// 计算需要添加的"0"的个数
    	for(t = cents;;){
	     	if(0== Math.floor(t/10)){
	     		zeroNum--;
	     		break;
	     	}else{
	     		zeroNum--;
	     		t=Math.floor(t/10);
	     	}
    	}
    	// 生成小数位
    	zero = "";
        for(var i=0;i<zeroNum;i++){
        	zero+="0";
        }
        cents = zero + cents;
    }
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	
	var result = ((sign) ? '' : '-') + '' + num + '.' + cents;
	
	if( hasPercen )
		result = result+"%";
	
	return result;
};
