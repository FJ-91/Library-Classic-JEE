<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Edit Catalogue</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Edit Catalogue</h2>
				<fieldset>
					<form action="<c:url value="/editCat?id=${ catToEdit.id }" />"
						method="post">
						<label>Catalogue : </label> <input type="text" name="catname" placeholder="Catalogue name" required size="35" value='<c:out value="${ catToEdit.name }" />' />
						<input type="submit" value="Save" /> 
						<input type="reset" value="Reset " />
						<c:if test="${ not empty dupCatError }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ dupCatError }
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