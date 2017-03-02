<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>

.navbar .navbar-nav {
	display: inline-block;
	float: none;
	vertical-align: top;
}
.navbar .navbar-collapse {
	text-align: center;
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
								<li  ><a href="/admin/category">Category</a></li>
								<li><a href="/admin/stringProperty">String Prperty</a></li>
								<li><a href="/admin/stringValue">String value</a></li>
								<li class = "active"><a href="/admin/digitValue">Digiit value</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/digitProperty">Digit property</a></li>
								<li><a href="/admin/item">Item</a></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>


<div class="row-fluid">
	<div class="col-md-12 text-center">
		<h2>Digit value</h2>
	</div>
</div>

<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		
			
			<form:form action="/admin/digitValue" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="min,max"/>
				<div class="col-md-12 col-xs-12">
				<div class="form-group">
				<div class="row-fluid">
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
					
				</div>
				</div>
					
			</form:form>	

		
		</div>

		<div class="col-md-7 col-xs-12">
	<form:form action="/admin/digitValue" method="post" modelAttribute="digitValueForm">
		
		
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="id,value,digitProperty"/>
				
				
		<div class="form-group">
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:input path="value" placeholder="Enter value of property" class="form-control" />
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red;"><form:errors path="value"></form:errors></label>			
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:select path="digitProperty"  class="form-control" id="digitProperty" >
						 <option value="" disabled selected>Select property</option>
						
							<c:forEach items="${digitProperty}" var="digitProperty">
						<c:choose>
							<c:when test="${digitProperty.id eq digitValueForm.digitProperty.id}">
								<option value="${digitProperty.id}" selected="selected">
									${digitProperty.name}
								</option>
							</c:when>	
							<c:otherwise>		
							<option value="${digitProperty.id}">
								${digitProperty.name}
							</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</form:select>
			</div>
			<div class="col-md-6">
					<label for="digitProperty" style="color:red;"><form:errors path="digitProperty"></form:errors></label>								
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
			<div class="col-md-6 col-xs-6"><h4>Digit value</h4></div>
			<div class="col-md-6 col-xs-6"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="digitValue">
				
				<div class="col-md-6 col-xs-6">${digitValue.value}</div>
				<div class="col-md-6 col-xs-6">
					<a href="/admin/digitValue/update/${digitValue.id}<custom:allParams/>">update</a>
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