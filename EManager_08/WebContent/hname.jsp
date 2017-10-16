<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		#main {
			width: 700px;
			height: 600px;
		}
		
		#d1 {
			width: 200px;
			height: 300px;
			display: inline-block;
			margin-left: 20px;
		}
		
		img {
			width: 195px;
			height: 150px;
		}
		
		.box{
            width: 198px;
            height: 320px;
            border: 1px solid #e0e0e0;
            text-align: center;
        }
        .box h4{line-height:32px; font-size:14px; color:#f30;font-weight:500}
        .box h4 span{font-size:20px}
        .u-flyer{display: block;width: 50px;height: 50px;border-radius: 50px;position: fixed;z-index: 9999;}
        .m-sidebar{
            position: fixed;
            top:0;
            right: 0;
            background: #000000;
            z-index: 2000;
            width: 35px;
            height: 100%;
            font-size: 12px;
            color: #fff;
        }
        .cart{color: #fff;text-align:center;line-height: 20px;padding: 200px 0 0 0px;margin: 200px 0}
        .cart span{display:block;width:20px;margin:0 auto;}
		
		
	</style>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/jquery.fly.min.js"></script> 
	<script type="text/javascript">
		function topPage() {
			window.self.location = "showbyname?pageNo=${pm.topPageNo}&name=${name }";
			
		}
		
		function previousPage() {
			window.self.location = "showbyname?pageNo=${pm.previousPageNo}&name=${name }";
		}
		
		function nextPage() {
			window.self.location = "showbyname?pageNo=${pm.nextPageNo}&name=${name }";
		}
		
		function bottomPage() {
			window.self.location = "showbyname?pageNo=${pm.bottonPageNo}&name=${name }";
		}
	
		function buy(obj){
			var nextnode = obj.nextElementSibling;
			var prenode = obj.previousElementSibling;
			//alert(nextnode.value);
			//alert(prenode.value)
			$(function(){
				$.ajax({
					   type: "POST",		//请求类型
					   url: "additem",	//请求到哪
					   data: "pid="+nextnode.value+"&num="+prenode.value,	//参数
					   success: function(msg){
					   }
					});
			});
		}
		
		function show(){
			var ipt =  document.getElementById("name");
			window.self.location = "showbyname?name=" + ipt.value;
		}
	</script>
	<body>
		<div>
			<input style="margin-left: 35%;margin-bottom: 25px;" type="text" name="name" id="name" value="" /><button onclick="show()" >查询</button>
		</div>
		<div id="main">
			<c:forEach  items="${pm.list }" var="list">
				<div id="d1" class="box">
							<img src="upload/${list.pro_img_src }" />
							<h4>${list.pro_name }</h4>
							<h5>${list.pro_price }</h5>
							数量:<input type="text" id="num" name="num" style="width: 40px;" />
							<a style="margin-left: 60px;" onclick="buy(this)"  class="addcar"  >购买</a>
							<input type="hidden" value="${list.pid }" />
				</div>
			</c:forEach>
		</div>
		<div style="margin-left:120px" >
				共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showbyname?pageNo=' + this.value +'&name=${name }'}">
				<!-- if(isNaN(this.value)){location='page?pageNo=this.value'}}this.disabled='disabled'   window.self.location = "showcate?pageNo=";-->
			  	<c:forEach begin="1" end="${pm.totalPageNo }" step="1" var="i">
				  	<c:choose>
				  		<c:when test="${i == pm.pageNo}">
				  		<option value="${i }" selected="selected">${i }</option> 
				  		</c:when>
				  		<c:otherwise>
				  		<option value="${i }" >${i }</option> 
				  		</c:otherwise>
				  	</c:choose>
			  	</c:forEach>
						</select>页
		</div>
		<div class="m-sidebar">
				    <div class="cart">
				        <i id="end"></i>
				        <span>购物车</span>
				    </div>
		</div>
	</body>
	<script>
    $(function () {
        var offset=$('#end').offset();

        $(window).resize(site);
        function site() {
            offset=$('#end').offset();
        }
            $('.box').on('click','a',function () {
                var addcar=$(this);
                var img=addcar.parent().find('img').attr('src');
                var flyer=$('<img class="u-flyer" src="'+img+'">');
                console.log(offset)
                flyer.fly({
                    start:{
                        left:event.pageX,
                        top:event.pageY
                    },
                    end:{
                        left:offset.left,
                        top:offset.top,
                        width:0,
                        height:0
                    }

                })
            })
    })
	</script>
</html>
 