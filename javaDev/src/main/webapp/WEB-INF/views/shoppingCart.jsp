<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<style>
#container{
margin-top:70px;
}

.table>tbody>tr>td, .table>tfoot>tr>td{
    vertical-align: middle;
}
@media screen and (max-width: 600px) {
    table#cart tbody td .form-control{
		width:20%;
		display: inline !important;
	}
	.actions .btn{
		width:36%;
		margin:1.5em 0;
	}
	
	.actions .btn-info{
		float:left;
	}
	.actions .btn-danger{
		float:right;
	}
	
	table#cart thead { display: none; }
	table#cart tbody td { display: block; padding: .6rem; min-width:320px;}
	table#cart tbody tr td:first-child { background: #333; color: #fff; }
	table#cart tbody td:before {
		content: attr(data-th); font-weight: bold;
		display: inline-block; width: 8rem;
	}
	
	
	
	table#cart tfoot td{display:block; }
	table#cart tfoot td .btn{display:block;}
	
}
</style>
<div class="container" id = "container">
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody id="tbody">
						
					</tbody>
					<tfoot>
						<tr class="visible-xs">
							<td class="text-center"><strong>Total  $<span id="totalCartPriceHidden"></span></strong></td>
						</tr>
						<tr>
							<td><a  id ="continueShopping" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Total $<span id="totalCartPrice"></span></strong></td>
							<td>
								<a href="/checkout" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a>
							</td>
						</tr>
					</tfoot>
				</table>
</div>


<script>
$(function() {
	var array = JSON.parse(sessionStorage.getItem("cartList"));
	console.log(array)
	var tbody = $('#tbody');
	var totalCartPrice = 0;
	

	function cartList(){
	if(array !=null){
	for (var i = 0; i < array.length; i++) {
		totalCartPrice += array[i].price * array[i].quantity;
	
	var tr = $("<tr>",{
		id:"item_"+array[i].id
	});
	var td1 = $("<td>")
	var divRow = $("<div>",{
		class:"row"
	})
	var div1 = $("<div>",{
		class:"col-sm-2 hidden-xs"
	})
	var img = $("<img>",{
		src:array[i].img,
		class:"img-thumbnail"
	})
	
	div1.append(img);
	
	var div2 = $("<div>",{
		class:"col-sm-10"
	})
	var number = i + 1;
	var h4 = $("<h4>",{
		class:"nomargin",
		text:"Product " + number,
	})
	var p = $("<p>",{
		text:array[i].description
	})
	
	div2.append(h4,p);
	divRow.append(div1,div2);
	td1.append(divRow);
	//price
	var td2 = $("<td>",{
		text:array[i].price
	})
	
	//Quantity
	var td3 = $("<td>");
	var input = $("<input>", {
		type:"number",
		class:"form-control text-center",
		min:"1",
		value:array[i].quantity
	})
	
	td3.append(input);
	
	//Subtotal
	var td4 = $("<td>",{
		class:"text-center",
		text:array[i].quantity * array[i].price
	})
	
	//buttons
	var td5 = $("<td>",{
		class:"actions"
	})
	//update
	var button1 = $("<button>",{
		class:"btn btn-info btn-sm update",
		name:"item_"+array[i].id
	})
	var i1 = $("<i>",{
		name:"item_"+array[i].id,
		class:"fa fa-refresh update"
	})
	
	button1.append(i1);
	
	//delete
	var button2 = $("<button>",{
		class:"btn btn-danger btn-sm delete",
		id:"item_"+array[i].id
	})
	var i2 = $("<i>",{
		id:"item_"+array[i].id,
		class:"fa fa-trash-o delete"
	})
	button2.append(i2);
	td5.append(button1,button2)
	tr.append(td1,td2,td3,td4,td5);
	tbody.append(tr);
	}
	}
	}
	cartList();
	
	$('#totalCartPrice').text(totalCartPrice);
	$('#totalCartPriceHidden').text(totalCartPrice);
	
	
	$(".update").click(function(event){
		event.stopPropagation();
		var id = event.target.attributes.name.value.substring(event.target.attributes.name.value.lastIndexOf("_")+1);
		console.log(id);
	//	console.log($("#" +event.target.name + " "+ "td:eq(2) input").val());
	var quantity = $("#" +event.target.attributes.name.value + " "+ "td:eq(2) input").val()
		$("#" +event.target.attributes.name.value + " "+ "td:eq(3)").text($("#" +event.target.attributes.name.value + " "+ "td:eq(1)").text() * quantity);
	totalCartPrice = 0;
		for (var i = 0; i < array.length; i++) {
			if(array[i].id == id){
				array[i].quantity = quantity;
			}
			totalCartPrice += array[i].price * array[i].quantity;
		}
		
		sessionStorage.setItem("cartList",JSON.stringify(array));
		$('#totalCartPrice').text(totalCartPrice);
		$('#totalCartPriceHidden').text(totalCartPrice);
	})
	
	
	$(".delete").click(function(event){
		event.stopPropagation();
		var id = event.target.id.substring(event.target.id.lastIndexOf("_")+1);
		
		$("#" + event.target.id).empty();
		
		for (var i = 0; i < array.length; i++) {
			if(array[i].id == id){
				array.splice(i, 1);
			}
		}
		totalCartPrice = 0;
		for (var i = 0; i < array.length; i++) {
			if(array[i].id == id){
				array[i].quantity = quantity;
			}
			totalCartPrice += array[i].price * array[i].quantity;
		}
		
		sessionStorage.setItem("cartList",JSON.stringify(array));
		$('#totalCartPrice').text(totalCartPrice);
		$('#totalCartPriceHidden').text(totalCartPrice);
	})
	
	$("#continueShopping").click(function(event){
		 window.history.back()
	})
});
</script>