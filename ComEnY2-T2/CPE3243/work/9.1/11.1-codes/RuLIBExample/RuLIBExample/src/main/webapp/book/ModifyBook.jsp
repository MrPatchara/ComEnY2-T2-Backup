<%-- 
    Document   : ModifyBook
    Created on : Nov 10, 2015, 5:48:05 PM
    Author     : piyavit
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor=cyan>
<body bgcolor=cyan>
<%
  String bookid = request.getParameter("bookid");
  int BookID = Integer.parseInt(bookid);
  String bookid5digit = request.getParameter("bookid5digit");
  String bookid6digit = request.getParameter("bookid6digit");
  String booktitle = request.getParameter("booktitle");
  String bookauthor = request.getParameter("bookauthor");
  
  Connection conn=null;
  Statement stmt=null;
  ResultSet rs=null;
  try 
  {
        Class.forName("com.mysql.jdbc.Driver");
  }
  catch(Exception e)
  {
        out.write("Error!!!!!!" + e);
  }
  try 
  {
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rulib?useSSL=false","root","engineer");
        out.write("Connected!");       
  }
  catch(SQLException e) 
  {
        System.out.println("Error!!!!!!" + e);
  } 
  
  
  //Class.forName("com.mysql.jdbc.Driver").newInstance();
  //conn = DriverManager.getConnection("jdbc:mysql://localhost/rulib", "root", "engineer");
  stmt = conn.createStatement();
 
    
  //String sql = "UPDATE engbook SET BookId5Digit='" + bookid5digit+ "', BookId6Digit='" + bookid6digit + "', BookTitle='" + booktitle + "', BookAuthor='" +bookauthor + "where Id='"+ BookID;
   
  int updateRow = stmt.executeUpdate("update engbook set BookId5Digit='" + 
  bookid5digit+ "',BookId6Digit='" + bookid6digit + "',BookTitle='" + booktitle + "',BookAuthor='" +
  bookauthor + "'WHERE Id="+ BookID  );

  if (updateRow == 1)			
  {
%>


<center>

<table border="2" width="350" bgcolor=#FF7700>
<br>
<tr>

<td bgcolor="#000099" border="0"><p align="center">
<font color=yellow size=+2"><b>The Book Record Has Been Modified.</b></p></td>
</tr>
   
 <tr><td align=center><a href="ListBook.jsp">See all Books</a></td></tr>

 </table>
 </center>
 <br>

 <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>

<%   
    }
    else			
    {
%>
     <h1>Sorry, modification has failed.</h1>
     
     
     <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
<%
  }
  stmt.close();				
  conn.close();				
%>
    </body>
</html>
