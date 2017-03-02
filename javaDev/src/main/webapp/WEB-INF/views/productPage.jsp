<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/css/shop-homepage.css" rel="stylesheet">	


<div class="row">
	<div class="col-md-4">
		<div class="img-thumbnail" >
			<img id = "image" src="/images/item/${image}" width="250px">
		</div>
	</div>
	<div class="col-md-6">
		<p><span id = "shortDescription">${brend} ${model}</span> </p>
		<p>Ціна - <span id = "price">${price}</span></p>
		<input hidden="hidden" value="${itemId}" id="itemId">
		<p>
			кількість: <input class="form-control text-center" type="number" id ="quantity" style="width:50px;" value="1" min="1">
		</p>
		<p id = "buttonBuy"> <a class="btn btn-primary" id="buy">add to cart</a></p>
	</div>
	<div class="col-md-12">
			<h3>Характеристики</h3>
		<c:forEach items="${listValue}" var="value">
		<div class="row">
			<div class="col-md-2">
				${value.name}
			</div>
			<div class="col-md-2">
				${value.value}
			</div>
		</div>
		</c:forEach>
	</div>
</div>
<script>
$(function() {
	var array = [];
	var totalPrice = 0;
	var totalQuantity = 0;
	if(sessionStorage.getItem("cartList") != null){
		array = JSON.parse(sessionStorage.getItem("cartList"));
		for (var i = 0; i < array.length; i++) {
			totalQuantity	+=parseInt(array[i].quantity);
		}
		$('#cartLenght').empty();
		$('#cartLenght').text(totalQuantity);

		for (var i = 0; i < array.length; i++) {
			totalPrice	+= (parseInt(array[i].price) * parseInt(array[i].quantity));
		}
		$('#cartPrice').text(totalPrice);
	}else{
		
	$('#cartLenght').text('0 ');
	$('#cartPrice').text('0 ');
	
	}
	function cart(id, quantity, price, description, img){
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.img = img;
	}
	
	function checkCart(){
		for (var i = 0; i < array.length; i++) {
			if($('#itemId').val() == array[i].id){
				var greenButton = $('<a>',{
					class:"btn btn-primary",
					style:"background-color:green",
					text:"товар в кошику"
				})
				
				$('#buttonBuy').empty();
				$('#buttonBuy').append(greenButton);
			}
		}
	}
	
	checkCart();
	
	$('#buy').click(function(){
	var id = $('#itemId').val();
	var quantity = parseInt($('#quantity').val());
	var price = parseInt($('#price').text());
	var description = $("#shortDescription").text();
	var img = $("#image").attr("src");

	
    var cart1 = new cart(id,quantity, price, description,img);
	totalPrice =totalPrice +cart1.price*cart1.quantity;
	totalQuantity = totalQuantity + cart1.quantity;
    array.push(cart1);
    sessionStorage.setItem("cartList",JSON.stringify(array));
    $('#cartLenght').text(totalQuantity);
    $('#cartPrice').text(totalPrice);
    checkCart();
    });
	
});
</script>




