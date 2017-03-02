<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row-fluid">
				<nav class="navbar navbar-default" style="margin-top: 80px;">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/category">Category</a></li>
								<li><a href="/admin/stringProperty">String Property</a></li>
								<li><a href="/admin/stringValue">String value</a></li>
								<li><a href="/admin/digitValue">Digit value</a></li>
								<li><a href="/admin/digitProperty">Digit property</a></li>
								<li><a href="/admin/item">Item</a></li>
								<li class="active"><a href="/admin/filter">Filter</a><span
										class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>
<div class="row-fluid">
	<div class="col-md-12 text-center">
		<h2>Filter</h2>
	</div>
</div>
<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			
			<form:form action="/admin/filter" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="serch,categorys"/>
		
				<div class="form-group">
					<form:input path="serch" placeholder="serch" class="form-control" />
				</div>
				<div class="form-group">
					<h4>Category</h4>
				</div>
				<div class="form-group">
					<div class="checkbox">
						<form:checkboxes items="${category}" path="categorys" itemLabel="name" itemValue="id" cssClass="hh"/>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>	

		
		</div>
		</div>
		<div class="col-md-7 col-xs-12">
				
		<div class="form-group">
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<select id="category" class="form-control">
					<option value="0">
						category
					</option>
				</select>
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red;"><form:errors path="name"></form:errors></label>			
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<select id="property" class="form-control">
					<option value="0">
						property
					</option>
				</select>
			</div>
			<div class="col-md-6">
				<label for="sel1" style="color:red;"><form:errors path="category"></form:errors></label> 					
			</div>
			
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<button type="button" class="btn btn-primary" id="save">save</button>
				
			</div>			
			</div>	
			</div>
					
				
		</div>
		
	
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-4 col-xs-4"><h4>String property</h4></div>
			<div class="col-md-4 col-xs-4"><h4>Delete</h4></div>
			<div class="col-md-4 col-xs-4"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="filter">
				
				<div class="col-md-4 col-xs-4">${filter.name}</div>
				<div class="col-md-4 col-xs-4">
					<a  href="/admin/stringProperty/delete/${filter.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-4 col-xs-4">
					<a href="/admin/stringProperty/update/${filter.id}<custom:allParams/>">update</a>
				</div>
				
			</c:forEach>
			</div>
			</div>
		
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		

	</div>				
<script>
$(function(){

	$.ajax({
		url:'/admin/filter/category',
		type:'GET',
		success:function(categores){
			var select = $('#category');
			for (var i = 0; i < categores.length; i++) {
				var option = $('<option>',{
					value:categores[i].id,
					text:categores[i].name
				})
				select.append(option);
			}
			
		}
	});
	
	$('#category').change(function(){

		var categoryId = $(this).val();
		$('#property').empty();
		$.ajax({
			url:'/admin/filter/stringAndDigitProperty/' + categoryId,
			type:'GET',
			success:function(property){
				var select = $('#property');
				for (var i = 0; i < property.length; i++) {
					var option = $('<option>',{
						value:property[i].id,
						text:property[i].name
					});
					select.append(option);
				}
			}
		})
		
		$.ajax({
			url:'/item/digitProperty/' + categoryId,
			type:'GET',
			success:function(digitProperty){
				var select = $('#digitProperty');
				for (var i = 0; i < digitProperty.length; i++) {
					var option = $('<option>',{
						value:digitProperty[i].id,
						text:digitProperty[i].name
					});
					select.append(option);
				}
				
			}
		});
		
	});
	

		$('#save').click(function(){
		var categoryId = $('#category').val();
		var propertyName = $('#property option:selected').text();
		console.log("CategoryId = "+ categoryId + " "+ "property = " + propertyName);
		
		var json = JSON.stringify({
			categoryId:categoryId,
			name:propertyName
		})
		
		if(categoryId !=0){
			$.ajax({
				url:'/admin/filter/',
				type:'PUT',
				data:json,
				contentType:"application/json",
				success:function(){
					console.log('done');
				}
			})
		}
	})
	
})
</script>

</body>
</html>