var _hmt = _hmt || [];
var _domain = 'http://' + window.location.host;
(function() {
  var hm = document.createElement("script");
  console.log(_domain);
  if (_domain.indexOf('merryplus') >= 0) {
	  hm.src = "//hm.baidu.com/hm.js?f9000a631355378e750c2dea3d67b17b";
  }else if(_domain.indexOf('gymmaxer') >= 0){
	  hm.src = "//hm.baidu.com/hm.js?cf99a0401f337cf915c17a4c6779be72";
  }else{
	  hm.src = "//hm.baidu.com/hm.js?3cfdd2375bde82f6b65cc19b744266b0";
  }
  console.log(hm.src);
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
