<%-- 
    Document   : DisplayBook
    Created on : Nov 10, 2015, 4:24:50 PM
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
    <body>
    <br>
    <center>
    <table border="2" width="300" cols=2 bgcolor=#dddd00>
        <tr>
        <td bgcolor="#000099" border="0" colspan=2><p align="center">
            <font color=yellow size=+2"><b> Piyavit Book Library Detail </b></p></td>
        </tr>
<%
    Connection conn=null;
    Statement stmt=null;
    try 
    {
        Class c=Class.forName("com.mysql.jdbc.Driver");
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
    try 
    {				
        String Mybookid5digit = request.getParameter("bookid5digit");
        String Mybooktitle = request.getParameter("booktitle");
        
        stmt = conn.createStatement();

  
  ResultSet rs = stmt.executeQuery("Select * From engbook Where BookId5Digit='" + Mybookid5digit +
          "' Or BookTitle='" + Mybooktitle + "'");

 if (rs != null)		
  {
      rs.next();		
      
    
    int bookid = rs.getInt("ID");
    String bookId5digit = rs.getString("BookId5Digit");
    String bookId6digit = rs.getString("BookId6Digit");
    String booktitle = rs.getString("BookTitle");
    String bookauthor = rs.getString("BookAuthor");
   
    
%>

<tr>
           <td><b>Book ID:</b></td> <td><%= bookid %></td>
</tr>
<tr>
           <td><b>BookId5Digit :</b></td><td><%= bookId5digit %> </td>
</tr>
<tr>
           <td><b>BookId6Digit :</b></td><td><%= bookId6digit %> </td>
</tr>
<tr>
           <td><b>BookTitle :</b></td><td><%= booktitle %></td>
</tr>
<tr>
           <td><b>BookAuthor :</b></td><td><%= bookauthor %></td>
</tr>


<%   
   stmt.close();			

  }
  else			
  {
%>
     <h1>Sorry, Not found the Doctor you want.</h1>
     
     
     <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
<%
  }			
    stmt.close();			
    conn.close();				
 }
 catch(Exception e)		
 { 
     out.print("<h2>" + "Sorry, No Books were found." + "</h2><br>"); 
 }
%>

</table>
</center>
<br>

<a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
