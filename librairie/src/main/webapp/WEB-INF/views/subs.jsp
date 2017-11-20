<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>

<title>Subscribers</title>

</head>

<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Subscribers Management</h2>
				<fieldset>
					<legend>Add New Subscriber</legend>
					<form action="addSub" method="post">
						<input type="text" name="lastname" placeholder="Lastname"
							size="30" required /> <input type="text" name="firstname"
							placeholder="Firstname" size="30" required /><br> <br>
						<input type="text" name="street" placeholder="Street adress"
							size="40" required /> <input type="number" name="zipcode"
							placeholder="Postal code" size="10" required /> <input
							type="text" name="city" placeholder="City" size="40" required /><br>
						<br> <input type="submit" value="Add" /> <input type="reset"
							value="Reset " />
					</form>
				</fieldset>
			</div>
		</div>
		<table id="subTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>Full Name</th>
					<th>Adress</th>
					<th>Total borrowings</th>
					<th>Current borrowings</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sub" items="${subs}">
					<tr>
						<td width="5%"><c:out value="${ sub.id }" /></td>
						<td width="15%"><a href="<c:url value="/subDetails?id=${ sub.id }" />"><c:out
									value="${ sub.lastname }" /> <c:out value="${ sub.firstname }" /></a></td>
						<td width="35%"><c:out value="${ sub.street }" />, <c:out
								value="${ sub.zipcode }" /> <c:out value="${ sub.city }" /></td>
						<td width="15%"><c:out value="${ fn:length(sub.borrowings) }" /></td>
						<td width="15%"><c:out value="${ fn:length(sub.currentBorrowings) }" /></td>
						<td width="15%" style="text-align: center;">
						<c:if test="${ fn:length(sub.currentBorrowings) == 0}">
							<a href="<c:url value="/editSubForm?id=${ sub.id }" />">
								<button class="btnEdit"><img alt="Edit" src="img/edit.png"></button>
							</a>
							<a href="<c:url value="/deleteSub?id=${ sub.id }" />">
								<button class="btnDel"><img alt="Delete" src="img/del.png"></button>
							</a>
						</c:if>
						<c:if test="${ fn:length(sub.currentBorrowings) > 0}">
							<a href="<c:url value="/editSubForm?id=${ sub.id }" />">
								<button class="btnEdit" ><img alt="Edit" src="img/edit.png"></button>
							</a>
							<button type="button" class="btn btn-xs btn-secondary" data-toggle="tooltip" data-placement="top" 
							title="You can't delete this subscriber because he has at least 1 active borrowing. You will be able to remove him as soon as he returns the copies.">
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