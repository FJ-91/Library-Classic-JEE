<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>


<title>Books Panel</title>
</head>

</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Books Management</h2>
				<fieldset>
					<legend>Add New Book</legend>
					<form action="addBook" method="post">
						<input type="text" name="isbn" placeholder="Number (ISBN)"
							size="20" required /> <input type="text" name="title"
							placeholder="Title" size="35" required /> <label>Author
							:</label> <select name="author" required class="chosen-select">
							<c:forEach var="author" items="${ authors }">
								<option value="${ author.id }">${ author }</option>
							</c:forEach>
						</select> <label>Catalogue :</label> <select name="catalogue" required
							class="chosen-select" id="comboCat">
							<c:forEach var="cat" items="${ catalogues }">
								<option value="${ cat.id }">${ cat }</option>
							</c:forEach>
						</select><br /> <br /> <input type="submit" value="Add" /> <input
							type="reset" value="Reset " /><br /> <br />
						<c:if test="${ not empty duplicateMessage }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ duplicateMessage }
							</div>
						</c:if>

					</form>
				</fieldset>
			</div>
		</div>
		<table id="bookTable">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>Title</th>
					<th>Author</th>
					<th>Catalogue</th>
					<th>Total copies</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${ books }">
					<tr>
						<td width="14%"><c:out value="${ book.ISBN }" /></td>
						<td width="30%"><c:out value="${ book.title }" /></td>
						<td width="20%"><c:out value="${ book.author }" /></td>
						<td width="10%"><c:out value="${ book.cat }" /></td>
						<td width="11%"><c:out value="${ fn:length(book.copies) }" /></td>
						<td width="15%" style="text-align: center;">
							<c:if test="${ book.copiesUnavailable == 0}">
								<a href="<c:url value="/editBookForm?id=${ book.ISBN }" />"><button
									class="btnEdit"><img alt="Edit" src="img/edit.png"></button></a>
								<a href="<c:url value="/deleteBook?id=${ book.ISBN }" />"><button
									class="btnDel"><img alt="Delete" src="img/del.png"></button></a>
							</c:if>
							<c:if test="${ book.copiesUnavailable > 0}">
								<a href="<c:url value="/editBookForm?id=${ book.ISBN }" />"><button class="btnEdit"><img alt="Edit" src="img/edit.png"></button></a>
								<button type="button" class="btn btn-xs btn-secondary" data-toggle="tooltip" data-placement="top" title="You can't delete this book because copies linked to it are either currently borrowed or sent for repair. You will be able to delete it as soon as the copies are returned or marked as repaired.">
									<img src="https://cdn2.iconfinder.com/data/icons/50-material-design-round-corner-style/44/Info-24.png">
								</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>