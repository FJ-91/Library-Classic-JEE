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
				<h2 class="display-3">Details of the <c:out value="${ cat }" /> catalogue</h2>
				<h4><i>Contains <b><c:out value="${ fn:length(cat.books) }" /></b> book(s)</i></h4>
			</div>
		</div>
		<table id="catDetails">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>Title</th>
					<th>Author</th>
					<th>Total copies</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${cat.books}">
					<tr>
						<td width="15%"><c:out value="${ book.ISBN }" /></td>
						<td width="35%"><c:out value="${ book.title }" /></td>
						<td width="35%"><c:out value="${ book.author }" /></td>
						<td width="15%"><c:out value="${ fn:length(book.copies) }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>