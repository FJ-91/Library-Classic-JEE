<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Authors</title>

</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="container">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h2 class="display-3">Catalogues Management</h2>
				<fieldset>
					<legend>Add New Catalogue</legend>
					<form action="addCat" method="post">
						<input type="text" name="name"
							placeholder="Ex: Dramatic, Narrative..." size="40" required /> <input
							type="submit" value="Add" /> <input type="reset" value="Reset " /><br><br>
							<c:if test="${ not empty dupCatError }">
								<div class="alert alert-danger" role="alert">
									<strong>Oops !</strong> ${ dupCatError }
								</div>
							</c:if>
					</form>
				</fieldset>
			</div>
		</div>
		<div style="width: 60%; margin: 0 auto;">
		<table id="catTable">
			<thead>
				<tr>
					<th>Name</th>
					<th>Number of books</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cat" items="${catalogues}">
					<tr>
						<td width="45%"><a href="<c:url value="/catDetails?id=${ cat.id }" />"><c:out value="${ cat.name }" /></a></td>
						<td width="30%"><c:out value="${ fn:length(cat.books) }" /></td>
						<td width="25%" style="text-align: center;">
							<c:if test="${ cat.checkBookAvailable != 0 }">
								<a href="<c:url value="/editCatForm?id=${ cat.id }" />"><button class="btnEdit" ><img alt="Edit" src="img/edit.png"></button></a>
								<button type="button" class="btn btn-xs btn-secondary" data-toggle="tooltip" data-placement="top" title="You can't delete this catalogue because copies linked to it are either currently borrowed or sent for repair. You will be able to delete it as soon as the copies are returned or marked as repaired.">
									<img src="https://cdn2.iconfinder.com/data/icons/50-material-design-round-corner-style/44/Info-24.png">
								</button>
							</c:if>
							
							<c:if test="${ cat.checkBookAvailable == 0 }">
								<a href="<c:url value="/editCatForm?id=${ cat.id }" />"><button class="btnEdit"><img alt="Edit" src="img/edit.png"></button></a>
								<a href="<c:url value="/deleteCat?id=${ cat.id }" />"><button class="btnDel"><img alt="Delete" src="img/del.png"></button></a>
							</c:if>
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