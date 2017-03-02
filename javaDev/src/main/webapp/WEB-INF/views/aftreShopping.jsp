<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3> Дякуєм за користування нашим сервісом</h3>
<h5> На вашу пошту було відправлено листа.</h5>
<h6>Ця сторінка закриється через <span id="timeOut"> 6 </span> секунд</h6>

<script>
$(function() {
	
	  sessionStorage.removeItem("cartList");
	
		var myTimer = setInterval(function(){
			var element = $('#timeOut') 
			var num = parseInt(element.text());
			if(num > 0){
				element.text(num-1);				
			}else{
				clearInterval(myTimer);		
				window.location = "/";
			}
		} , 1000);
	
})
</script>
</body>
</html>