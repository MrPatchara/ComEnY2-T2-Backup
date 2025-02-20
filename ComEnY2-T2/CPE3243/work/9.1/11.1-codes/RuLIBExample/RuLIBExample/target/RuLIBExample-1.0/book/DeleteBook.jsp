<%-- 
    Document   : DeleteBook
    Created on : Nov 10, 2015, 1:15:42 PM
    Author     : piyavit
--%>
<%@page import="java.sql.SQLException"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
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

        <table border="1" width=90% cols=7 bgcolor=#eeff00>
            <tr>
                <td bgcolor="#000099" border="0" colspan=7><p align="center">
                <font color=white size=+2"><b> Delete Books </b></p></td>
            </tr>
            <tr> 
                <td><b>ID</b></td>
                <td><b>BookId5Digit</b></td>
                <td><b>BookId6Digit</b></td>
                <td><b>BookTitle</b></td>
                <td><b>BookAuthor</b></td>
                <td><b>Delete?</d></td>
            </tr>
<% 
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
    
    stmt = conn.createStatement();
    rs = stmt.executeQuery("Select * From engbook");

    if (rs != null)				
    {
      while (rs.next())		
      {    
        int bookid = rs.getInt("ID");
	String bookid5digit = rs.getString("Bookid5Digit");
        String bookid6digit = rs.getString("Bookid6Digit");
        String booktitle = rs.getString("BookTitle");
        String bookauthor = rs.getString("BookAuthor");
      
%>
<tr>
      <td><%= bookid %></td>
      <td><%= bookid5digit %></td>
      <td><%= bookid6digit %> </td>
      <td><%= booktitle %></td>
      <td><%= bookauthor %></td>
      <td><a href='RemoveBook.jsp?BookID5Digit=<%= bookid5digit %>'>delete</a></td>
</tr>
<%
       }
   }  
    stmt.close();		
    conn.close();		    
%>
</table>
</center>
<p>
<a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
