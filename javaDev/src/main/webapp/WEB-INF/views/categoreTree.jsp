<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


		<c:forEach items="${category}" var="category" >
		
 <ul style="list-style: none;">
		<li>
		<c:choose>
    				<c:when test="${errorsDelete eq category.id}">
        				${category.name}
        				
        				<a  href="/admin/category/delete/${category.id}<custom:allParams/>">delete</a>
						<a  href="/admin/category/update/${category.id}<custom:allParams/>">update</a>
        				 <span style="color:red">(can't delete, has child or string propertys)</span>
   					 </c:when>
  				  <c:otherwise>
        				${category.name}
        				<a  href="/admin/category/delete/${category.id}<custom:allParams/>">delete</a>
						<a  href="/admin/category/update/${category.id}<custom:allParams/>">update</a>
  				  </c:otherwise>
				</c:choose>
	
					<c:set var="category" value="${category.childs}" scope="request"/>
		    		<jsp:include page="categoreTree.jsp"/>
		</li>
</ul>
		</c:forEach>
	