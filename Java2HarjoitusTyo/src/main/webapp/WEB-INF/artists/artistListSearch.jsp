<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Artist List</title>
<link rel="stylesheet"
	href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<header>
		<form method="post">
			<input name="name" type="text" required
				placeholder="type name to search..." /> <input name="action"
				type="hidden" value="SEARCH"/> <input type="submit" value="search" />
		</form>
	</header>
	<h1>Artists found by searching "<c:out value="${ searchedName }"/>"</h1>
	<a href="/artists"><button>Go back to all artists.</button></a>
	<table>
		<thead>
			<tr>
				<th>Artists</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ artists }" var="artistItem">
				<tr>
					<td><a
						href="http://localhost:8080/artist?artistId=${artistItem.getId() }"><c:out
								value="${artistItem.getName() }"></c:out></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>