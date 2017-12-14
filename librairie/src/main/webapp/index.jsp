<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<title></title>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>
	<div id="containerHome">
		<h1 style="color: white;">Welcome to my first ever Java EE project !</h1>
	</div>

	<%@ include file="/WEB-INF/views/footer.jsp"%>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>