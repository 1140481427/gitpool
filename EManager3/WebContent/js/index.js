function _onload() {
	
	var ones = ["商品管理","订单管理","会员管理","系统设置"];

	var zh_t = [{"name":"商品列表","src":"PageModelShowPro"},{"name":"商品类别维护","src":"showcate"},{"name":"商品规格属性","src":"showrule"}];
	var aq_t = [{"name":"订单列表","src":"showorder"},{"name":"未支付","src":"hello1.html"},{"name":"已支付","src":"hello1.html"}];
	var cd_t = [{"name":"会员列表","src":"member_mag.jsp"}];
	var gr_t = [{"name":"管理员信息","src":"hello1.html"},{"name":"修改密码","src":"hello2.html"}];
	
	var twos = [zh_t,aq_t,cd_t,gr_t];
//	alert(twos[0][1]);
	/*生成一级菜单*/
	for(var i=0; i<ones.length; i++){
		var main = document.getElementById("_left_main");
		var div_main = document.createElement("div");
		div_main.setAttribute("class","_safety_main");
		var pull_down = document.createElement("div");
		pull_down.setAttribute("id","d" + i);
		pull_down.setAttribute("class","_sf_img");
		pull_down.setAttribute("onclick","change(this)");
		var right_text = document.createElement("div");
		right_text.setAttribute("class","_sf_txt");
		var right_text_text = document.createTextNode(ones[i]);
		right_text.appendChild(right_text_text);
		main.appendChild(div_main);
		div_main.appendChild(pull_down);
		div_main.appendChild(right_text);
		
		var height = twos[i].length*30; 
		var div_down = document.createElement("div");
		div_down.setAttribute("id","d" + i + "a");
		div_down.setAttribute("style","height:" + height + "px;overflow: hidden;transition: height 1s;");
		
		var div_down_main = document.createElement("div");
		div_down_main.setAttribute("class","pull_zh");
		var div_down_ul = document.createElement("ul");
		div_down_ul.setAttribute("id","d" + i + "b");
		for(var j=0; j<twos[i].length;j++){
			var div_down_li = document.createElement("li");
			div_down_li.setAttribute("id","d" + i + "c");
			div_down_li.setAttribute("onclick","openChild(this)");
			div_down_li.setAttribute("title",twos[i][j].src);
			var div_down_li_text = document.createTextNode(twos[i][j].name);
			div_down_li.appendChild(div_down_li_text);
			div_down_ul.appendChild(div_down_li);
		}
		div_down_main.appendChild(div_down_ul);
		div_down.appendChild(div_down_main);
		main.appendChild(div_down);
		
	}
}

/*隐藏显示li*/
function change(obj){
	//alert(1);
	var id = obj.id + "a";
	var idb = obj.id + "b"; 
	//alert(id);
	var main = document.getElementById(id);
	var _ul = document.getElementById(idb).getElementsByTagName("li").length;
//	alert(_ul);
	var height =  _ul*30 + "px";
	if(main.style.height == height){
		//alert(1);
		main.setAttribute("style","height: 0px;overflow: hidden;transition: height 1s;");
	}else if(main.style.height == "0px"){
		//alert(2);
		main.setAttribute("style","height: " + height + ";overflow: hidden;transition: height 1s;");
	}
}

var count = 0;
/*新建子页*/
function openChild(obj){
	count++;
	var text = obj.innerHTML;
	//alert(obj.title);
	var _top = document.getElementById("top");
	var div_main = document.createElement("div");
	var div_main_left = document.createElement("div");
	var div_main_text = document.createTextNode(text);
	div_main_left.setAttribute("onclick","handover(this)");
	div_main_left.setAttribute("style","display: flex;");
	div_main_left.setAttribute("title",obj.title);
	div_main_left.appendChild(div_main_text);
	div_main.appendChild(div_main_left);
	div_main.setAttribute("class","_div_main");
	var div_close = document.createElement("div");
	div_close.setAttribute("id",count);
	div_close.setAttribute("class","_tu");
	div_close.setAttribute("onclick","closeChild(this)");
	div_main.appendChild(div_close);
	_top.appendChild(div_main);
	
	var iframeHtml = document.getElementById("iframeHtml");
	iframeHtml.src = obj.title;
}

/*切换页面*/
function handover(obj){
	var iframeHtml = document.getElementById("iframeHtml");
	iframeHtml.src = obj.title;
}



function closeChild(obj){
	var _top = document.getElementById("top");
	var id = obj.id;
	var child = document.getElementById(id);
	_top.removeChild(child.parentNode); 
}


/*隐藏菜单*/
function concealLeft(){
	var _left_main = document.getElementById("_left_main");
	_left_main.setAttribute("style","display: none;");
	var _top = document.getElementById("top");
	var _botton = document.getElementById("botton");
	_top.setAttribute("style","width: 1519px;height: 40px;background-color: #666666;display: flex;");
	_botton.setAttribute("style","width: 1519px;height: 643px;background-color: whitesmoke;display: flex;");
}

/*显示菜单*/
function showp(){
//	alert(1);
	var _left_main = document.getElementById("_left_main");
	_left_main.setAttribute("style","background-color: #666666;height: height: 683px;width: 134px;");
	var _top = document.getElementById("top");
	var _botton = document.getElementById("botton");
	_top.setAttribute("style","width: 1385px;height: 40px;background-color: #666666;display: flex;");
	_botton.setAttribute("style","width: 1385px;height: 643px;background-color: whitesmoke;display: flex;");
}
