<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<script src="/resources/js/js.js"></script>
   
    <!-- Navigation -->
    
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="/">iShop.ua</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" style="float:right;">
                              
                <table>
				<tr>
					<td>
						<a>${authUser.login}</a>		
					</td>
					<td>
						 <security:authorize access="isAuthenticated()">
							<form:form action="/logout" method="post" class="navbar-form navbar-right">
								<button type="submit" class="btn btn-default">Logout</button>
							</form:form>
						</security:authorize>
				
						<security:authorize access="!isAuthenticated()">
							<form:form action="/login" method="get"	class="navbar-form navbar-right">
								<button type="submit" class="btn btn-default">Login</button>
							</form:form>	
						</security:authorize>
					</td>
					<td></td>
					</tr>					
			</table>
            </div>

            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<div class="row">
	<div class="col-md-3" style="float:right;">
		<p id="choppingCart"><a href="/cart" id="cart">корзина</a>: <span id="cartLenght">0 </span>товар(ів), сума: <span id ="cartPrice">0</span> </p>
	</div>
</div>
	
<script>
$(function(){
	var array = [];
	var totalPrice = 0;
	var totalQuantity = 0;
	
	
	if(sessionStorage.getItem("cartList") != null){
		array = JSON.parse(sessionStorage.getItem("cartList"));
		for (var i = 0; i < array.length; i++) {
			totalQuantity	= parseInt(totalQuantity)+ parseInt(array[i].quantity);
		}
		$('#cartLenght').text(totalQuantity);

		for (var i = 0; i < array.length; i++) {
			totalPrice	+= (parseInt(array[i].price) * parseInt(array[i].quantity));
		}
		$('#cartPrice').text(totalPrice);
	}else{
		
	$('#cartLenght').text('0 ');
	$('#cartPrice').text('0 ');
}
	if(window.location.pathname !="/admin/item" && !window.location.pathname.includes("/admin/adminProductPage/")){
		if(sessionStorage.getItem("pagination") != null){
			sessionStorage.removeItem("pagination");
		}
	}
		
});
</script>	
	