<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Edit Borrowing</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Edit Borrowing</h2>
				<fieldset>
					<form action="<c:url value="/editBor?id=${ borToEdit.id }" />" method="post">
						<label>Copy : </label>
						<input type="text" size="80" value='<c:out value="${ borToEdit.sub }" />' disabled />
						<br><br>
						<label>Subscriber : </label>
						<input type="text" size="80" value='<c:out value="${ borToEdit.copy }" />' disabled />
						<br><br> 
						<label>Borrowing date : </label>
						<input type="text" name="borDate" class="datepicker" required size="10" value='<c:out value="${ borToEdit.borDate }" />' />
						<br><br> 
						<label>Expected return date : </label>
						<input type="text" name="expectedDate" class="datepicker" required size="10" value='<c:out value="${ borToEdit.borDateExpected }" />' />
						<br><br>
						<c:if test="${ borToEdit.borDateReturn != null }">
							<label>Return date : </label>
							<input type="text" name="returnDate" class="datepicker" required size="10" value='<c:out value="${ borToEdit.borDateReturn }" />' />
							<br><br>
						</c:if>
						<input type="submit" value="Save" /> <input	type="reset" value="Reset " />
						<br><br>
						<c:if test="${ not empty dateError }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ dateError }
							</div>
						</c:if>
					</form>
				</fieldset>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
	
</body>

</html>