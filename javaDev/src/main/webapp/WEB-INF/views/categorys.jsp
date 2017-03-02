<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="/resources/css/shop-homepage.css" rel="stylesheet">	
<style>	
	#filter span{
		display: inline-block;
		width: 100%;
		}
	#filter label{
		vertical-align: super;
		padding-left: 2%;
		}
	#filter span input{
 		width: 1.3em;
    	height: 1.3em;
     	border: 1px solid #a9a9a9;
    	border-radius: .25em;
		}
</style>
	

<div class="container">
        <div class="row">
            <div class="col-md-3" id="categoryBar">
                <p class="lead">Shop Name</p>
                <div class="list-group" id="listCategory">  
                </div>
           
               		<form:form action="/categorys/${categoryId}" class="form-inline" method="GET" modelAttribute="filter"  acceptCharset="UTF-8">
						<custom:hiddenInputs excludeParams="price,min,max,brend, _brend"/>
						<div class="col-md-12 col-xs-12">
							<div class="form-group" id="filterGroup">
								<div class="row-fluid">
									<h4>Ціна</h4>
								
									<div class="col-md-6">
										<form:input path="min" placeholder="min" class="form-control" cssStyle="width:70px;"/>
									</div>			
								
									<div class="col-md-6">
										<form:input path="max" placeholder="max" class="form-control" cssStyle="width:70px;"/>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="col-md-12">
										<button type="submit" class="btn btn-primary">Ok</button>				
									</div>
								</div>
						
								<div class="row-fluid">
									<h4>Бренд</h4>
									
									<div class="col-md-12">
										<div class="checkbox" >
											<form:checkboxes items="${brend}" path="brend" itemLabel="brend" itemValue="brend" />
										</div>
									</div>
								</div>
									<div class="row-fluid">
									<div class="col-md-12">
										<button type="submit" class="btn btn-primary">Ok</button>				
									</div>
								</div>
						</div>
					</div>	
			</form:form>            
          </div>


         <div class="col-md-3 col-md-offset-6">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Сортувати <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="від дешевшого до дорожчого" paramValue="price"/>
						<custom:sort innerHtml="від дорожчого до дешевшого" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="кількість товарів"/>
			</div>
		</div>
	   <div class="col-md-9">
   	<div class="col-md-5 col-md-offset-8" style="padding:10px 30px">
   		<form:form action="/" class="form-inline" method="get" modelAttribute="search">
				<div class="form-group">
					<form:input path="serch" placeholder="serch" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">шукати</button>
				</div>
			</form:form>
   	</div>
   </div>

            <div class="col-md-9">
				<div class="row">
					<div class="col-md-12 text-center">
						<h3>${categoryName}</h3>
					</div>	
				</div>
                <div class="row"  id="row">
					<c:forEach items="${page.content}" var="item">
						 <div class="col-sm-4 col-lg-4 col-md-4" id="item_${item.id}">
                        <div class="thumbnail">
                            <img src="/images/item/${item.path}" alt="" id="image">
                            <div class="caption">
                                <h4 class="pull-right" id="price">${item.price}</h4>
                                
                                <p><a href="/item/productPage/${item.id}" id="shortDescription">${item.brend} ${item.model}</a></p>
                              	<p id="buy_${item.id}" >
									 <a class="btn btn-primary buy"  name="item_${item.id}">buy</a>                          	
                              	</p>
                            <div class="ratings" style="margin-top:40px;">
	                    	<c:choose>
								<c:when test="${item.reviews > 0}">
                                	 <p class="pull-right"> ${item.reviews} reviews</p>                                								
								</c:when>	
							</c:choose>                                  
                            </div>
                            </div>
                        </div>
                    </div>
					</c:forEach>
                </div>
            </div>
            
          <div class="col-md-12 col-xs-12 text-center">
          		<div class="row">
          			<div class="col-md-12">
        				<p>
          					<c:forEach items="${values}" var="value">
          						<span>
          							$ { value }
          						</span>
          					</c:forEach>
          				</p>
          			</div>
          		</div>
          		<c:choose>
					<c:when test="${page.getTotalPages() > 1}">                           								
	        			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
					</c:when>	
				</c:choose>    
			</div>
        </div>
    </div>
<script type="text/javascript">
$(function() {	
	var pathname = 	window.location.pathname;
	var categoryId = pathname.substring(pathname.lastIndexOf("/")+1);
	$.ajax({
		url:'/index/categoryMain',
		type:'GET',
		success:function(categores){
			var div = $('#listCategory');
			
			for (var i = 0; i < categores.length; i++) {
				var a = $('<a>',{
					id:categores[i].id,
					href:'#'+'sub_categoria_' + categores[i].id,
					class:'list-group-item',
					text:categores[i].name
				})
				a.attr('data-toggle', 'collapse');
				div.append(a);
				
				var div1 = $('<div>',{
					class:'collapse list-group-submenu',
					id:'sub_categoria_' + categores[i].id
				})
				div.append(div1);
			}
			
		}
	});
	
	$('#listCategory').click(function(event){
		var id = event.target.id;
		
		$.ajax({
			url:'/index/'+id,
			type:'GET',
			success:function(childs){
				if(childs.length != 0){
				var subCategory = $('#sub_categoria_' + id);
				subCategory.empty();
				for (var i = 0; i < childs.length; i++) {
					var a = $('<a>',{
						id:childs[i].id,
						href:'#'+'sub_categoria_' + childs[i].id,
						class:'list-group-item',
						text:childs[i].name,
						style:'padding-left:' + 35*childs[i].level + 'px;'
					})
					a.attr('data-toggle', 'collapse');
					subCategory.append(a);
					
					var div1 = $('<div>',{
						class:'collapse list-group-submenu',
						id:'sub_categoria_' + childs[i].id
					})
					subCategory.append(div1);
				}
			}else{
			
				window.location.replace(id);

				
			};
			
			}
		});
	});
	
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
	

	checkCart();
	function checkCart(){
		if(sessionStorage.getItem("cartList") != null){
			array = JSON.parse(sessionStorage.getItem("cartList"));
			for (var i = 0; i < array.length; i++) {
				var button = $("#buy_"+array[i].id);
				var greenButton = $('<a>',{
					class:"btn btn-primary",
					style:"background-color:green",
					text:"in cart"
				})
				button.empty();
				button.append(greenButton);
			}
		}
	}
	
	$('.buy').click(function(event){
		console.log(event);
		
		var id = event.target.name.substring(event.target.name.lastIndexOf("_")+1);
		var price = parseInt($("#item_"+ id+" #price").text());
		var quantity = 1;
		var description = $("#item_"+ id+  " #shortDescription").text();
		var img = $("#item_"+ id+ " img").attr("src");
		var inCart = false;
		
		console.log(price);
		for (var i = 0; i < array.length; i++) {
			if(array[i].id == id){
				array[i].quantity = parseInt(array[i].quantity)+1;
				sessionStorage.setItem("cartList",JSON.stringify(array));
				inCart = true;
			}	
		}
		
		if(!inCart){
		var cart1 = new cart(id,quantity, price, description,img);
		totalPrice =totalPrice +cart1.price*cart1.quantity;
		totalQuantity = totalQuantity + cart1.quantity;
	    array.push(cart1);
	    sessionStorage.setItem("cartList",JSON.stringify(array));	
	    
		}else{
			totalQuantity = 0;
			totalPrice = 0;
			
			for (var i = 0; i < array.length; i++) {
				totalQuantity	+=parseInt(array[i].quantity);
				totalPrice += parseInt(array[i].quantity) * parseInt(array[i].price) ; 
			}
		}

		checkCart();
	    $('#cartLenght').text(totalQuantity);
	    $('#cartPrice').text(totalPrice);
	    });
		
});
</script>