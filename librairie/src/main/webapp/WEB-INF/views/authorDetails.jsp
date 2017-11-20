<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Catalogue details</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Details of the author <c:out value="${ author.lastname }" /> <c:out value="${ author.firstname }" /> born on <c:out value="${ dob }" /> </h2>
				<h4><i><b><c:out value="${ fn:length(cat.books) }" /></b> books linked</i></h4>
			</div>
		</div>
		<div style="width: 70%; margin: 0 auto;">
			<table id="authorDetails">
				<thead>
					<tr>
						<th>ISBN</th>
						<th>Title</th>
						<th>Total copies</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${author.booksWritten}">
						<tr>
							<td width="20%"><c:out value="${ book.ISBN }" /></td>
							<td width="60%"><c:out value="${ book.title }" /></td>
							<td width="20%"><c:out value="${ fn:length(book.copies) }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>