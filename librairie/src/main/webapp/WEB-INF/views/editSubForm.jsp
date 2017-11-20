<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Edit Subscriber</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Edit Subscriber</h2>
				<fieldset>
					<form action="<c:url value="/editSub?id=${ subToEdit.id }" />"
						method="post">
						<label>Lastname : </label> <input type="text" name="lastname" placeholder="Lastname" required size="35" value='<c:out value="${ subToEdit.lastname }" />' />
						<br><br>
						<label>Firstname : </label> <input type="text" name="firstname" placeholder="Firstname" required size="35" value='<c:out value="${ subToEdit.firstname }" />' />
						<br><br>
						<label>Street adress : </label><input type="text" name="street" placeholder="Street adress"  required size="50" value='<c:out value="${ subToEdit.street }" />' />
						<br><br> 
						<label>Postal code : </label><input type="text" name="zip" placeholder="Postal code"  required size="10" value='<c:out value="${ subToEdit.zipcode }" />' />
						<br><br>
						<label>City : </label><input type="text" name="city" placeholder="City"  required size="50" value='<c:out value="${ subToEdit.city }" />' />
						<br><br>
						<input type="submit" value="Save" /> <input type="reset" value="Reset " />
					</form>
				</fieldset>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
	
</body>

</html>