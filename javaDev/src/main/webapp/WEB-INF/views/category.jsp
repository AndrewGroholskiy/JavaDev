<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>
<body>
<div class="row-fluid">
				<nav class="navbar navbar-default" style="margin-top: 80px;">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li class = "active" ><a href="/admin/category">Category</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/stringProperty">String Prperty</a></li>
								<li><a href="/admin/stringValue">String value</a></li>
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
		<h2>Category</h2>
	</div>
</div>

<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			
			<form:form action="/admin/category" class="form-inline" method="get" modelAttribute="filter">
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
	<form:form action="/admin/category" method="post" modelAttribute="categoryForm">
		
		
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="id,name,parent"/>
				
		<div class="form-group">
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:input path="name" placeholder="Enter name of category" class="form-control" />
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red"><form:errors path="name"></form:errors></label>			
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:select path="parent"  class="form-control" id="sel1" >
						 <option value="0">first category</option>
						<c:forEach items="${subCategory}" var="categorys">
							<c:choose>
							<c:when test="${categorys.id eq categoryForm.parent.id}">
								<option value="${categorys.id}" selected="selected">${categorys.name}</option>
							</c:when>	
							<c:otherwise>
								<option value="${categorys.id}">${categorys.name}</option>
							</c:otherwise>
							</c:choose>
							
						</c:forEach>				
					</form:select>
			</div>
			<div class="col-md-6">
					<label for="sel1"><form:errors path="parent"></form:errors></label>								
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
					
			
					<c:set var="category" value="${page.content}" scope="request"/>
		    		<jsp:include page="categoreTree.jsp"/>
		    	
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