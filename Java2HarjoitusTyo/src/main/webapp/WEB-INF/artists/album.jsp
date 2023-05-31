<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><c:out value="${ album }" /></title>
<link rel="stylesheet"
	href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<script src="scripts/app.js"></script>
</head>
<body>
	<h1>
		<c:out value="${ album }" />
	</h1>
	<button onclick="removeAlbum()">Remove album</button>
	<!-- Piilotettu arvo muuttujaksi JS:lle, tarvitaan redirectiä varten poiston yhteydessä -->
	<input type="hidden" id="artistId" value ="${artistId }"/>
	<hr>

	<table>
		<thead>
			<tr>
				<th>Tracks</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ tracks }" var="track">
				<tr>
					<td><c:out value="${track}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>