<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>

<title>Borrowings Panel</title>

</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Borrowings Management</h2>
				<fieldset>
					<legend>Create New Borrowing</legend>
					<form action="createBorrowing" method="post">
						<br> <label>Copy :</label> <select name="copySelected"
							class="chosen-select" required>
							<c:forEach var="copy" items="${ copies }">
								<c:if test="${ copy.isAvailable == true }">
									<option value="${ copy.id }">${ copy }</option>
								</c:if>
							</c:forEach>
						</select> <label>Subscriber :</label> <select name="subSelected"
							class="chosen-select" required>
							<c:forEach var="sub" items="${ subs }">
								<option value="${ sub.id }">${ sub }</option>
							</c:forEach>
						</select> <br> <br> 
						<label style="">Borrowing date : </label> <input type="text" class="datepicker" size="7" name="borrowDate" placeholder="Borrowing date" value='<c:out value="${ today }" />' required /> 
						<label>Expected return date : </label> <input type="text" name="expectedDate" size="7" placeholder="Expected return date" class="datepicker" value='<c:out value="${ expected }" />' required />
						<span data-toggle="tooltip" data-placement="right" title="The expected return date is set to +2 weeks by default">
							<img src="https://cdn3.iconfinder.com/data/icons/pure-lines-blue-grey/100/sqi2016_bg1-05-20.png">
						</span>
						<br><br>
						<input type="submit" value="Create" /> <input type="reset" value="Reset " /><br /><br />
						<c:if test="${ not empty dateError }">
							<div class="alert alert-danger" role="alert">
								<strong>Oops !</strong> ${ dateError }
							</div>
						</c:if>
					</form>
				</fieldset>
			</div>
		</div>
		<table id="borTable">
			<thead>
				<tr>
					<th>Bor. n°</th>
					<th>Copy</th>
					<th>Bor. date</th>
					<th>Expected return</th>
					<th>Return date</th>
					<th>Borrower</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bor" items="${ bors }">
					<tr>
						<td width="8%"><c:out value="${ bor.id }" /></td>
						<td width="32%"><b><c:out value="${ bor.copy }" /></b></td>
						<td width="10%"><c:out value="${ bor.borDate }" /></td>
						<td width="10%"><c:out value="${ bor.borDateExpected }" /></td>
						<td width="10%">
							<c:choose>
								<c:when test="${ bor.borDateReturn == null }">
									<span style="color:red;">Not returned</span>
								</c:when>
								<c:when test="${ bor.borDateReturn != null }">
									<c:out value="${ bor.borDateReturn }" />
								</c:when>
							</c:choose>
						</td>
						<td width="15%">
	 						<a 	role="button" 
	 							data-html="true" 
	 							data-toggle="popover" 
	 							title="<b><c:out value="${ bor.sub.lastname }" /> <c:out value="${ bor.sub.firstname }" /></b>" 
								data-content="
								<b>Subscriber ID:</b> <c:out value="${ bor.sub.id }" /> <br /> 
								<b>Adress : </b> <c:out value="${ bor.sub.street }" /><br/><c:out value="${ bor.sub.zipcode }" /> <c:out value="${ bor.sub.city }" /><br />
								<b>Total borrowings :</b> <c:out value="${ fn:length(bor.sub.borrowings) }" /><br /> (<c:out value="${ fn:length(bor.sub.currentBorrowings)  }" /> active)"
	 						>
								<c:out value="${ bor.sub.lastname }" /> <c:out value="${ bor.sub.firstname }" />
							</a>
						</td>
						<td width="15%" style="text-align: center">
						<a href="<c:url value="/editBorForm?id=${ bor.id }" />"><button
									class="btnEdit"><img alt="Edit" src="img/edit.png"></button></a> 
							<c:if test="${ bor.borDateReturn != null }">
								<a href="<c:url value="/deleteBor?id=${ bor.id }" />"><button
										class="btnDel"><img alt="Delete" src="img/del.png"></button></a>
							</c:if> 
							<c:if test="${ bor.borDateReturn == null }">
							<span data-toggle="tooltip" data-placement="top" title="Return copy">
								<a href="<c:url value="/returnCopy?id=${ bor.id }" />"><button class="btnReturn"><img alt="Return" src="img/return.png"></button></a>
							</span>
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