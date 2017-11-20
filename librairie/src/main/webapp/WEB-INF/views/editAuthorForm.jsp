<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Edit Author</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Edit Author</h2>
				<fieldset>
					<form
						action="<c:url value="/editAuthor?id=${ authorToEdit.id }" />"
						method="post">
						<label>Lastname : </label><input type="text" name="lastname"
							placeholder="Lastname" required size="35"
							value='<c:out value="${ authorToEdit.lastname }" />' /><br>
						<br> <label>Firstname : </label><input type="text"
							name="firstname" placeholder="Firstname" required size="35"
							value='<c:out value="${ authorToEdit.firstname }" />' /><br>
						<br> <label>Date of birth : </label><input type="text"
							name="dob" placeholder="Date of birth (yyyy-mm-dd)" class="datepicker" required
							size="10" value='<c:out value="${ authorToEdit.dob }" />' /><br>
						<br> <input type="submit" value="Save" /> <input
							type="reset" value="Reset " />
					</form>
				</fieldset>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>

</html>