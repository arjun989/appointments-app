<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Appointment</title>

</head>

<body>
	<br />
	<c:choose>

		<c:when test="${ loggedin !=null }">
			<!-- 			<h2 align="center">This page is for Adding appointments</h2><br/>-->
			<div align="center" action="/">
				<form method="post">
					<br /> <br />
					<h3>Edit Appointment</h3>
					<br /> 
					<input type="text" name="name" value=<%=request.getAttribute("name")%> /><br /> 
					<input type="text" name="date" value=<%=request.getAttribute("date")%> /><br />
					<input type="text" name="time" value=<%=request.getAttribute("time")%> /><br />
					<input name="Update" type="submit" />
				</form>
				<br /> <br />
			</div>
			<br />
			<br />
		</c:when>

		<c:otherwise>
			<div align="center">
				<h2>~You are not Logged In~</h2>
				<br /> <br /> <a href="${login_url}">Sign in or register</a>
			</div>
			<br />
			<br />
		</c:otherwise>

	</c:choose>
</body>
</html>