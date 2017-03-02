<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
 <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="/resources/css/shop-homepage.css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
		
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
						window.location.replace("categorys/"+id);
					};
				}
			});
		});
	});
</script>	


<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
<a href="/admin">Admin panel</a>
</security:authorize>
    
   <div class="row">
   	<div class="col-md-4 col-md-offset-9" style="padding-bottom:10px">
   		<form:form action="/" class="form-inline" method="get" modelAttribute="search">
				<div class="form-group">
					<form:input path="serch" placeholder="serch" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">шукати</button>
				</div>
			</form:form>
   	</div>
   </div>
   
    <div class="container">
        <div class="row">
            <div class="col-md-3" id="categoryBar">
                <p class="lead">iShop.ua</p>
                <div class="list-group" id="listCategory">
                          
                </div>
            </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="/resources/css/1.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image"  src="/resources/css/2.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="/resources/css/3.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
    
                   <div class="row"  id="row">
                    <div class="col-md-9 col-md-offset-3">
                    <div class="col-md-12"> 
                    	<p>${pageEmpty}</p>
                    </div>
					<c:forEach items="${page.content}" var="item">
						 <div class="col-sm-4 col-lg-4 col-md-4 " id="item_${item.id}">
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
                <div class="row">
                	<div class="col-md-9 col-md-offset-3 text-center">
		    			<c:choose>
							<c:when test="${page.getTotalPages() > 1}">                           								
			        			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
							</c:when>	
						</c:choose>  
                	</div>
                </div>