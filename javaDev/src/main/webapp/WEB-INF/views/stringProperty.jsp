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
								<li class="active"><a href="/admin/stringProperty">String Property</a><span
										class="sr-only">(current)</span></li>
								<li><a href="/admin/stringValue">String value</a></li>
								<li><a href="/admin/digitValue">Digit value</a></li>
								<li><a href="/admin/digitProperty">Digit property</a></li>
								<li><a href="/admin/item">Item</a></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>
<div class="row-fluid">
	<div class="col-md-12 text-center">
		<h2>String property</h2>
	</div>
</div>

<div class="row-fluid">
		<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			
			<form:form action="/admin/stringProperty" class="form-inline" method="get" modelAttribute="filter" acceptCharset="UTF-8">
				<custom:hiddenInputs excludeParams="serch,_categorys,categorys" />
		
				<div class="form-group">
					<form:input path="serch" placeholder="serch" class="form-control"/>
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
	<form:form action="/admin/stringProperty" method="post"  modelAttribute="form">
				<form:hidden path="id"/>
				<custom:hiddenInputs excludeParams="id,name,stringProperty"/>
				
		<div class="form-group">
			<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:input path="name" placeholder="Enter name of property" class="form-control" />
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red;"><form:errors path="name"></form:errors></label>			
			</div>
			</div>	
			</div>
				<div class="row">
			<div class="col-md-12">
			<div class="col-md-6">
				<form:select path="category"  class="form-control" id="sel1" >
						 <option value="" disabled selected>Select category</option>
						<jsp:include page="optionTree.jsp"/>
					</form:select>
			</div>
			<div class="col-md-6">
					<label for="sel1" style="color:red;"><form:errors path="category"></form:errors></label>								
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
			<div class="col-md-6 col-xs-6"><h4>String property</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Delete</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Update</h4></div>
			<c:forEach items="${page.content}" var="stringProperty">
				<div class="col-md-6 col-xs-6">
				
				<c:choose>
    				<c:when test="${errorsDelete eq stringProperty.id}">
        				${stringProperty.name} <span style="color:red">(can't delete, has value)</span>
   					 </c:when>
  				  <c:otherwise>
        				${stringProperty.name}
  				  </c:otherwise>
				</c:choose>
				
				</div>
				<div class="col-md-2 col-xs-2">
					<a  href="/admin/stringProperty/delete/${stringProperty.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-2 col-xs-2">
					<a href="/admin/stringProperty/update/${stringProperty.id}<custom:allParams/>">update</a>
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