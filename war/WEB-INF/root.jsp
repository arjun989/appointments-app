<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<style>
	.boxed1 {
		border: 3px solid red;
	}
</style>
</head>


<body>

	<br />
	<c:choose>
		<c:when test="${user != null}">
			<p style="text-align: center">
				Welcome ${user.email} <br />
			</p>
			<p style="text-align: center">
				You can signout <a href="${logout_url}">here</a><br />
			</p>
			<p style="text-align: center">
				<a href="${addAppointment}">ADD APPOINTMENT</a><br />
			</p>
			<br />
			<br />
			<br />
			<span id="container">&nbsp;</span>

			<%
				ArrayList<String> arr = (ArrayList<String>) request.getAttribute("appointments");
						if (arr != null) {
							out.write("<div class=\"boxed1\" align=\"center\">");
							out.write("<h2 align=\"center\" >~Your Current Appointments ~</h2><br/>");
							for (int i = 0; i < arr.size(); i++) {
								
								out.write("<lable>" + arr.get(i) + "</lable>");
								out.write("<input type=\"button\" name=\"" + (i + "edt") + "\" value=\"Edit\" \\>");
								out.write("<input type=\"button\" name=\"" + (i + "dlt") + "\" value=\"Delete\" \\>");
								out.write("<br/><br/>");
								
							}
							out.write("</div>");
						}
			%>

		</c:when>
		<c:otherwise>
			<div align="center">
				<h2>~Welcome~</h2>
				<br /> <br /> <a href="${login_url}">Sign in or register</a>
			</div>
			<br />
			<br />

		</c:otherwise>
	</c:choose>
</body>
</html>