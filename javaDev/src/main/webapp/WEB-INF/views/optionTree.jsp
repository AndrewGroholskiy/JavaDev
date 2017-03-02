<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

						<c:forEach items="${category}" var="category">
							<c:choose>
							<c:when test="${category.id eq form.category.id}">
								<option value="${category.id}" selected="selected">${category.name}</option>
							</c:when>	
							<c:otherwise>
								<option value="${category.id}">${category.name}</option>
							</c:otherwise>
							</c:choose>
							
						</c:forEach>
