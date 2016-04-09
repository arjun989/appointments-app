<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>Welcome</title>

<style>
.boxed1 {
	border: 3px solid red;
}
</style>
<script type="text/javascript">
	function removeElement(n) {
		var element = document.getElementById(id);
		// A bit of robustness helps...
		if (element && element.parentNode) {
			element.parentNode.removeChild(element);
		}
	}
</script>
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
			<script>
				if (document.getElementById("app")) {
					document.getElementById("app").parentNode
							.removeChild(document.getElementById("app"));
				}
			</script>
			<%
				ArrayList<String> arr = (ArrayList<String>) request.getAttribute("appointments");
						if (arr != null) {
							//out.write("");
							out.write("<div id= \"app\" class=\"boxed1\" align=\"center\">");
							out.write("<form method=\"post\">");
							out.write("<h2   align=\"center\" >~Your Current Appointments ~</h2><br/>");
							for (int i = 0; i < arr.size(); i++) {

								out.write("<lable>" + arr.get(i) + "</lable>");
								out.write("<input type=\"submit\" name=\"" + (i + "edt") + "\" value=\"Edit\" \\>");
								out.write("<input type=\"submit\" name=\"" + (i + "dlt") + "\" value=\"Delete\" \\>");
								out.write("<br/><br/>");

							}
							out.write("</form>");
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