<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Subscriber details</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h3 class="display-3">Details of the subscriber: <br /> <c:out value="${ sub }" /></h3>
				<h4><i>Has currently <b><c:out value="${ fn:length(sub.currentBorrowings) }" /></b> active borrowings and has borrowed a total of <b><c:out value="${ fn:length(sub.borrowings) }" /></b> copies <c:if test="${ fn:length(sub.currentBorrowings) == 5 }">(Limit of 5 active borrowings reached)</c:if></i>
					
				</h4>
			</div>
		</div>
		<table id="subDetails">
			<thead>
				<tr>
					<th width="5%">Bor. n°</th>
					<th width="35%">Copy</th>
					<th width="15%">Bor. date</th>
					<th width="15%">Expected return</th>
					<th width="15%">Return date</th>
					<th width="15%">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bor" items="${ sub.borrowings }">
					<tr>
						<td width="5%"><c:out value="${ bor.id }" /></td>
						<td width="35%"><c:out value="${ bor.copy }" /></td>
						<td width="15%"><c:out value="${ bor.borDate }" /></td>
						<td width="15%"><c:out value="${ bor.borDateExpected }" /></td>
						<td width="15%"><c:out value="${ bor.borDateReturn }" /></td>
						<td width="15%">
						<c:if test="${ bor.borDateReturn == null }">
							<span style="color: red">Not returned yet</span>
						</c:if>
						<c:if test="${ bor.borDateReturn != null }">
							<span style="color: green">Returned</span>
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