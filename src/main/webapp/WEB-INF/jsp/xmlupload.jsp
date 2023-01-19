<%@page import="com.vinsys.securitylabs.xxe.Book"%>
<%@page import="java.util.List"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-4">
		<div class="card">
			<div class="card-header">XXE Test</div>
			<div class="card-body">
				<form class="form" method="POST" enctype="multipart/form-data"
					action="/xxe/problem/xmlupload">
					<table class="table table-light">
						<tr>
							<td>File to upload:</td>
							<td><input type="file" class="form form-control" name="file" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Upload"
								class="btn btn-success" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="card-footer">
				<%
				if (request.getAttribute("status") != null) {
				%>
				<div class="alert alert-info"><%=request.getAttribute("status") != null ? request.getAttribute("status") : ""%></div>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("books") != null) {
	%>
	<div class="card">
		<ol>
			<%
			List<Book> books = (List<Book>) request.getAttribute("books");
			for (int i = 0; i < books.size(); i++) {
			%>
			Size :
			<%=books.size()%>
			<p><%=books.get(i).getTitle()%></p>
			<p><%=books.get(i).getAuthor()%></p>
			<p><%=books.get(i).getPublisher()%></p>
			<p><%=books.get(i).getIsbn()%></p>
			<%
			}
			%>
		</ol>
	</div>
	<%
	}
	%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>