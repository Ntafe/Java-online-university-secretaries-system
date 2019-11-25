<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ανάθεση Μαθήματος </title>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</head>
<body>
<p><h1><u>Ανάθεση μαθήματος σε Καθηγητή</u></h1></p>
	<form method="post" action="/javaergasia/AddLesSev">
		<input type="hidden" name="requestType" value="Insert" />
		<table>
			<tr>
				<td><h3>Αριθμός Μητρώου Καθηγητή:</h3></td>
				<td><input type="text" name="mitrwo" /></td>
				
			</tr>
			<tr>
				<td><h3>Αριθμό Μητρώου Μαθήματος: </h3></td>
				<td><input type="text" name="am" /></td>
			</tr>
			<tr>
			<tr>
				<td><h3>Τίτλος Μαθήματος:</h3></td>
				<td><input type="text" name="title" /></td>
				
			</tr>
			<tr>
				<td><h3>Εξάμηνο:</h3></td>
				<td><input type="text" name="sem" /></td>
				
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Ανάθεση" style="width:150px; height:60px; "/></td>
				
			</tr>
		</table>
	</form>
	<br>
<form method="post" action="/javaergasia/Show.jsp">
		<input type="hidden" name="requestType" value="Insert" />
		<td><input type="submit" value=" Επιστροφή" /></td>
		</form>
		<td><h5>*Το μητρώο του καθηγητή πρεπει να προυπάρχει ενω του μαθήματος οχι </h5></td>
		
</body>
</html>