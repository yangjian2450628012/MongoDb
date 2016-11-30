(function($,text){
	$.fn.menuCom = function( options ){
		if(options.text == undefined){
			return null;
		}
		for ( var a in text) {
			if(a == options.text){
				text = text[a];
				break;
			}
		}
		var _h = "<ul>";
		for ( var a in text) {//a 0,1,2,3,4,5,6
			var b = false;
			if(text[a].child instanceof Array)
			{
				_h += "<li class=\"show-child\">";
				b = true;
			}else{
				_h += "<li>";
			}	
			_h += "<a><div><img alt='' src="+text[a].imgpath+">"+text[a].name;
			if(b)
			{
				_h += "<span class=\"accordion-img\"></span>";
			}	
			_h += "</div></a>";
			if(b)
			{
				_h += "<ul style='display:none;'>";
				for ( var v in text[a].child) {
					_h += "<li class='child-no-display'><a><div>";
					_h += "<img alt='' src="+text[a].child[v].imgpath+" />"+text[a].child[v].name;
					_h += "</div></a></li>";
				}
				_h += "</ul>";
			}
		}
		_h +="</li></ul>";
		
		options = $.extend({
			callback : function(s){
				return s;
			}
		},options);
		//把得到的结果回调
		options.callback({_h:_h,text:options.text});
		text = _da;
	}
})(jQuery,_da);