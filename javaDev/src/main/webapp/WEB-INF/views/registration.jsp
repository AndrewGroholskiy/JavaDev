<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Registration form</h1>
<div class="row">
<form:form class="form-horizontal" method="POST" modelAttribute="user">

	<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="Name">Name</label>  
  			<div class="col-md-5">
  				<form:input id="name" path="name" type="text" placeholder="username" class="form-control input-md" required=""/>
  			</div>
  			
  			<div class="col-md-3">
  				<label for="name" style="color:red"><form:errors path="name"></form:errors></label>		
  			</div>
		</div>
	</div>	
	
	<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="last_name">Last name</label>  
  			<div class="col-md-5">
  				<form:input id="last_name" path="lastName" type="text" placeholder="last name" class="form-control input-md" required=""/>
  			</div>
  			
  			<div class="col-md-3">
  				<label for="last_name" style="color:red"><form:errors path="lastName"></form:errors></label>		
  			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="phone">phone number</label>  
  			<div class="col-md-5">
  				<form:input id="phone" path="phoneNamber" type="number" placeholder="phone number" class="form-control input-md" required=""/>
  			</div>
  			
  			  <div class="col-md-3">
  				<label for="phone" style="color:red"><form:errors path="phoneNamber"></form:errors></label>		
  			</div>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="addres">Home addres</label>  
  			<div class="col-md-5">
  				<form:input id="addres" path="addres" type="text" placeholder="home addres" class="form-control input-md" required=""/>
  			</div>
  			
  			  <div class="col-md-3">
  				<label for="addres" style="color:red"><form:errors path="addres"></form:errors></label>		
  			</div>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="email">Email</label>  
  			<div class="col-md-5">
  				<form:input id="email" path="mail" type="email" placeholder="email" class="form-control input-md" required="" />
  			</div>
  			<div class="col-md-3">
  					<label for="email" style="color:red"><form:errors path="mail"></form:errors></label>		
  			</div>
		</div>
	</div>
	
		<div class="form-group">
		<div class="col-md-10 ">
	  		<label class="col-md-2 control-label" for="password">Password</label>  
  			<div class="col-md-5">
  				<form:input id="password" path="password" type="password" placeholder="password" class="form-control input-md" required=""/>
  			</div>
  			
  			  <div class="col-md-3">
  				<label for="password" style="color:red"><form:errors path="password"></form:errors></label>		
  			</div>
		</div>
	</div>
	
	<div class="form-group">
	<label class="col-md-2 control-label" for="submit">confirm and check out</label>  
		<div class="col-md-5">
			<button type="submit" class="btn btn-primary"  id="submit" class="form-control">confirm</button>
		</div>
	</div>
</form:form>	
</div>
<script>
</script>

</body>
</html>