<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>

</style>

<body>
	<c:choose>
		<c:when test="${user != null}">
			<p style="text-align: center">
				Welcome ${user.email} <br />
			</p>
			<p style="text-align: center">
				You can signout <a href="${logout_url}">here</a><br />
			</p>

		</c:when>
		<c:otherwise>
			<div align="center">
				<h2>~Welcome~</h2>
				<br />
				<br /> <a href="${login_url}">Sign in or register</a>
			</div>
			<br />
			<br />

		</c:otherwise>
	</c:choose>
</body>
</html>