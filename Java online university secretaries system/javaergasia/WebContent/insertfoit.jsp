<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Είσοδος στο σύστημα</title>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</head>
<body>
	<p><h2><u>Είσοδος στο σύστημα</u></h2></p>
	<form method="post" action="/javaergasia/StudServlet">
		<input type="hidden" name="requestType" value="Insert" />
		<table>
			<tr>
				<td><h3>Αριθμός Μητρώου:</h3></td>
				<td><input type="text" name="mitrwo" /></td>
				
			</tr>
			<tr>
				<td><h3>Κωδικος:</h3></td>
				<td><input type="password" name="password" /></td>
				
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Σύνδεση" /></td>
				
			</tr>

		</table>
	</form>
<form method="post" action="/javaergasia/index.jsp">
		<input type="hidden" name="requestType" value="Insert" />
		<td><input type="submit" value=" Επιστροφή" /></td>
		</form>
</body>
</html>