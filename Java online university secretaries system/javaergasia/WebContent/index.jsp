<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Δικτυακή Εφαρμογή Πανεπιστημίου</title>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
<% 
      
   HttpSession newsession = request.getSession();
   if (newsession != null) 
   {
       newsession.invalidate();
       
   }
   	
 %>
</head>
<body>
<p ><H1 ><u>Λειτουργίες σελίδας</u></H1> </p>
	<br />
	<br>	
	<br />
	<ul>
	<li style="color:gold" ><a href="/javaergasia/insert.jsp"><H3>Εισοδος Γραμματείας</H3></a></li>
	<li style="color:gold" ><a href="/javaergasia/insertfoit.jsp"><H3>Εισοδος Φοιτητή</H3></a></li>
	<li style="color:gold" ><a href="/javaergasia/insertKath.jsp"><H3>Εισοδος Καθηγητή</H3></a></li>
	</ul>
	<br />
</body>
</html> 