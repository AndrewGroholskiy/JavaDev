<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<title><tiles:getAsString name="title" /></title>
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
</head>
<body>
<header>
    <tiles:insertAttribute name="header" />
</header>
	<!-- Основной контент страницы -->
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	<div class="footer">
		<!-- футер страницы -->
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
