<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Authors Management</h2>
				<fieldset>
					<legend>Add New Author</legend>
					<form action="addAuthor" method="post">
						<input type="text" name="lastname" placeholder="Lastname"
							size="35" required /> <input type="text" name="firstname"
							placeholder="Firstname" size="35" required /> <input type="text"
							name="dob" placeholder="Date of birth" size="25"
							class="datepicker" required /> <input type="submit" value="Add" />
						<input type="reset" value="Reset " /><br /> <br />
						<c:if test="${ not empty duplicateAuthor }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ duplicateAuthor }
							</div>
						</c:if>
					</form>
				</fieldset>
			</div>
		</div>
		<div style="width: 70%; margin: 0 auto;">
			<table id="authorsTable">
				<thead>
					<tr>
						<th>Fullname</th>
						<th>Date of birth</th>
						<th>Total books</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="author" items="${authors}">
						<tr>
							<td width="38%"><a href="<c:url value="/authorDetails?id=${ author.id }" />"><c:out value="${ author.lastname }" /> <c:out value="${ author.firstname }" /></a></td>
							<td width="20%"><c:out value="${ author.dob }" /></td>
							<td width="20%"><c:out value="${ fn:length(author.booksWritten) }" /></td>
							<td width="22%" style="text-align: center;">
								<a href="<c:url value="/editAuthorForm?id=${ author.id }" />"><button class="btnEdit"><img alt="Edit" src="img/edit.png"></button></a>
								<a href="<c:url value="/deleteAuthor?id=${ author.id }" />"><button class="btnDel"><img alt="Delete" src="img/del.png"></button></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>

</html>