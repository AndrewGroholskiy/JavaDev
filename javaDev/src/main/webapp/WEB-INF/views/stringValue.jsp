<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="row-fluid">
				<nav class="navbar navbar-default" style="margin-top: 80px;">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin/category">Category</a></li>
								<li><a href="/admin/stringProperty">String Prperty</a></li>
								<li class="active"><a href="/admin/stringValue">String value</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/digitValue">Digiit value</a></li>
								<li><a href="/admin/digitProperty">Digit property</a></li>
								<li><a href="/admin/item">Item</a></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>


<div class="row-fluid">
	<div class="col-md-12 text-center">
		<h2>String value</h2>
	</div>
</div>

<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			
			<form:form action="/admin/stringValue" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="serch"/>
				<div class="form-group">
					<form:input path="serch" placeholder="serch" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>	

		</div>
		</div>

		<div class="col-md-7 col-xs-12">
	<form:form action="/admin/stringValue" method="post" modelAttribute="stringValueForm">
				<form:hidden path="id"/>
				<custom:hiddenInputs excludeParams="id,name,stringProperty"/>
		<div class="form-group">
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:input path="name" placeholder="Enter value of property" class="form-control" />
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red;"><form:errors path="name"></form:errors></label>			
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:select path="stringProperty"  class="form-control" id="stringProperty" >
						 <option value="" disabled selected>Select property</option>
						
							<c:forEach items="${stingPropertys}" var="stringProperty">
						<c:choose>
							<c:when test="${stringProperty.id eq stringValueForm.stringProperty.id}">
								<option value="${stringProperty.id}" selected="selected">
									${stringProperty.name}
								</option>
							</c:when>	
							<c:otherwise>		
							<option value="${stringProperty.id}">
								${stringProperty.name}
							</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</form:select>
			</div>
			<div class="col-md-6">
					<label for="stringProperty" style="color:red;"><form:errors path="stringProperty"></form:errors></label>								
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<button type="submit" class="btn btn-primary">save</button>
			</div>			
			</div>	
			</div>
		</div>
		
	</form:form>
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-8"><h4>String property</h4></div>
			<div class="col-md-4"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="stringValue">
				<div class="col-md-8">${stringValue.name}</div>
				<div class="col-md-4">
					<a href="/admin/stringValue/update/${stringValue.id}<custom:allParams/>">update</a>
				</div>
			</c:forEach>
			</div>
			</div>
			<div class="col-md-12 col-xs-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		<div class="col-md-2 col-xs-12">
			<div class="col-md-6 col-xs-6 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
			</div>
		</div>
	</div>

</body>
</html>