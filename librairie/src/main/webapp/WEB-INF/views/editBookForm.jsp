<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Edit Book</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Edit Book</h2>
				<fieldset>
					<form action="<c:url value="/editBook?id=${ bookToEdit.ISBN }" />" method="post">
						<label>Title : </label> <input type="text" name="title" placeholder="Title" required size="35" value='<c:out value="${ bookToEdit.title }" />' />
						<br><br>
						<label>Author : </label> 
						<select name="author" required class="chosen-select">
							<c:forEach var="author" items="${ authors }">
								<option value="${ author.id }" ${ bookToEdit.author.id == author.id ? 'selected' : '' } >${ author }</option>
							</c:forEach>
						</select>
						<br><br>
						<label>Catalogue : </label>
						<select name="cat" required class="chosen-select" id="comboCat">
							<c:forEach var="cat" items="${ catalogues }">
								<option value="${ cat.id }" ${ bookToEdit.cat.id == cat.id ? 'selected' : '' } >${ cat }</option>
							</c:forEach>
						</select>
						<br><br>
						<input type="submit" value="Save" /> <input type="reset" value="Reset " />
						<br><br>
						<c:if test="${ not empty duplicateMessage }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ duplicateMessage }
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