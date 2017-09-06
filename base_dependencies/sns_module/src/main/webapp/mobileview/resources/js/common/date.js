function getDateStr(format){
	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	var h = d.getHours(); 
	var m = d.getMinutes(); 
	var se = d.getSeconds(); 
	format = format.replace("yyyy", vYear);
	format = format.replace("MM", vMon);
	format = format.replace("dd", vDay);
	format = format.replace("hh", h);
	format = format.replace("mm", m);
	format = format.replace("ss", se);
	return format;
};