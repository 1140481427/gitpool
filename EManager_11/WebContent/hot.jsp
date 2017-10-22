<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/bootstrap.css" />
	</head>
	<style>
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
			window.self.location = "showhot?pageNo=${pm.topPageNo}";
			
		}
		
		function previousPage() {
			window.self.location = "showhot?pageNo=${pm.previousPageNo}";
		}
		
		function nextPage() {
			window.self.location = "showhot?pageNo=${pm.nextPageNo}";
		}
		
		function bottomPage() {
			window.self.location = "showhot?pageNo=${pm.bottonPageNo}";
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
		<div class="container-fluid">
		
		<div>
			<input style="margin-left: 35%;margin-bottom: 10px;margin-top: 5px;"  type="text" name="name" id="name" value="" /><button onclick="show()" >查询</button>
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
		<div style="margin-left:40%" >
		
		<nav aria-label="Page navigation">
				  <ul class="pagination">
				    <li>
				      <a href="#" onclick="previousPage()" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				   <c:forEach begin="1" end="${pm.totalPageNo }" step="1" var="i">
				    <c:choose>
				    		<c:when test="${pm.pageNo == i }">
				    			<li class="active"><a href="showhot?pageNo=${i }">${i }</a></li>	
				    		</c:when>
				    		<c:otherwise>
				    			<li><a href="showhot?pageNo=${i }">${i }</a></li>	
				    		</c:otherwise>
				    	</c:choose>
				   </c:forEach> 
				    <li>
				      <a href="#" onclick="nextPage()" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
				
				<%-- 共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showhot?pageNo=' + this.value }">
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
						</select>页 --%>
		</div>
		
		<div class="m-sidebar">
				    <div class="cart">
				        <i id="end"></i>
				        <span>购物车</span>
				    </div>
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
 