<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Εγγραφή Γραμματείας</title>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</head>
<body>
<p><h1><u>Εγγραφή Γραμματείας</u></h1></p>
	<form method="post" action="/javaergasia/NewSec">
		<input type="hidden" name="requestType" value="Insert" />
		<table>
			<tr>
				<td><h3>Αριθμός Μητρώου Γραμματείας:</h3></td>
				<td><input type="text" name="mitrwo" /></td>
				
			</tr>

			<tr>
				<td><h3>Κωδικός:</h3></td>
				<td><input type="password" name="password" /></td>
				
			</tr>
				<td></td>
				<td><input type="submit" value="Εγγραφή" style="width:150px; height:60px; "/></td>
				
			</tr>
		</table>
	</form>
	<br>
	<form method="post" action="/javaergasia/Show.jsp">
		<input type="hidden" name="requestType" value="Insert" />
		<td><input type="submit" value=" Επιστροφή" /></td>
		</form>

</body>
</html>