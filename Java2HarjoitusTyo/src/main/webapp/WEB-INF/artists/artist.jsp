<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><c:out value="${ artist }"/>'s albums</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<script src="scripts/app.js"></script>
</head>
<body>
	<h1><c:out value="${ artist }"/></h1>
	<button onclick="removeArtist()">Remove artist</button>
	<hr>

	<form method="post">
		<input name="name" type="text" required
			placeholder="type album here..." id="input" autofocus /> <input type="submit"
			value="Add to list"/>
	</form>

	<table>
		<thead>
			<tr>
				<th><c:out value="${ artist }"/>'s albums</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ albums }" var="albumItem">
				<tr>
					<td><a href="http://localhost:8080/album?albumId=${albumItem.getId() }"><c:out value="${albumItem.getTitle() }"></c:out></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>