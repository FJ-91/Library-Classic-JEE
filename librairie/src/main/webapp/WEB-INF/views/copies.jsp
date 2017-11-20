<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Copies Panel</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Copies Management</h2>
				<fieldset>
					<legend>Add New Copies</legend>
					<form action="addCopies" method="post">
						<label>Book :</label> <select name="bookSelected"
							class="chosen-select" required>
							<c:forEach var="book" items="${ books }">
								<option value="${ book.ISBN }">${ book }</option>
							</c:forEach>
						</select> <input type="number" name="numberOfCopies" size="10" min="1"
							max="100" required /> <input type="submit" value="Add" /> <input
							type="reset" value="Reset " />
					</form>
				</fieldset>
			</div>
		</div>
		<table id="copiesTable">
			<thead>
				<tr>
					<th>Copy ID</th>
					<th>ISBN</th>
					<th>Title</th>
					<th>Author</th>
					<th>Catalogue</th>
					<th>Availability</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="copy" items="${ copies }">
					<tr>
						<td width="8%"><c:out value="${ copy.id }" /></td>
						<td width="1%"><c:out value="${ copy.book.ISBN }" /></td>
						<td width="27%">
							<c:out value="${ copy.book.title }" />
							<span style="margin-left: 5px; float: right;" data-toggle="tooltip" data-placement="left" title="ISBN : <c:out value="${ copy.book.ISBN }" />">
								<img src="https://cdn1.iconfinder.com/data/icons/ui-beast-9/32/ui-37-24.png">
							</span>
						</td>
						<td width="22%">
						<c:if test="${ copy.book.author != null }">
							<c:out value="${ copy.book.author }" />
						</c:if>
						</td>
						<td width="15%"><c:out value="${ copy.book.cat }" /></td>
						<c:choose>
							<c:when	test="${ copy.isAvailable == false && copy.isInReparation == true }">
								<td width="10%" style="color: orange; text-align: center;">Sent for repair</td>
							</c:when>
							<c:when test="${ copy.isAvailable == true }">
								<td width="10%" style="color: green; text-align: center; ">Available</td>
							</c:when>
							<c:when test="${ copy.isAvailable == false }">
								<td width="10%" style="color: red; text-align: center;">
								<span data-toggle="tooltip" data-placement="top" title='Borrowed by : <c:out value="${ copy.currentSub }"></c:out>'>
									Borrowed
								</span>
								</td>
							</c:when>
						</c:choose>
						<td width="17%" style="text-align: center;">
							<c:if test="${ copy.isInReparation == false && copy.isAvailable == true }">
								<a href="<c:url value="/sendRepair?id=${ copy.id }" />"><button
										class="btnRepair"><img alt="Repair" src="img/repair.png"></button></a>
							</c:if>
							<c:if test="${ copy.isAvailable == true }">
								<a href="<c:url value="/deleteCopy?id=${ copy.id }" />"><button
										class="btnDel"><img alt="Delete" src="img/del.png"></button></a>
							</c:if>
							<c:if test="${ copy.isInReparation == true }">
								<a href="<c:url value="/repairCopy?id=${ copy.id }" />"><button style="width:100%"
										class="btnRepaired">Mark as repaired<img style="margin-left: 3px;" alt="Repaired" src="img/ok.png"></button></a>
							</c:if>
							<c:if test="${ copy.isAvailable == false && copy.isInReparation == false }">
							<div style="text-align: center">
								<button type="button" class="btn btn-xs btn-secondary" data-toggle="tooltip" data-placement="top" title="No action allowed here till the copy is returned.">
									<img src="https://cdn2.iconfinder.com/data/icons/50-material-design-round-corner-style/44/Info-24.png">
								</button>
							</div>
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