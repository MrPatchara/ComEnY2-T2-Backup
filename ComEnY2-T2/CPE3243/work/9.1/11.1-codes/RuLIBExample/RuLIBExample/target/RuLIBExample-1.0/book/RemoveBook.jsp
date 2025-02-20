<%-- 
    Document   : RemoveBook
    Created on : Nov 10, 2015, 4:03:57 PM
    Author     : piyavit
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
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
    <br>
<%
    Connection conn=null;
    Statement stmt=null;
   
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
    String bookid5digit = request.getParameter("BookID5Digit");
    stmt = conn.createStatement();
    int deleteRow = stmt.executeUpdate("Delete From engbook Where BookID5Digit=" + bookid5digit);
    if (deleteRow == 1)     
    {
%>
    <center>
	<table border="2" width="350" bgcolor=#FF7700>
	<tr>
            <td bgcolor="#000099" border="0"><p align="center">
            <font color=yellow size=+2"><b>Book Record has been Deleted</b></p></td>
        </tr>
        <tr>
           <td align=center><a href="ListBook.jsp"> See all Books </a></td></td>
        </tr>
     </table>
     </center>
     <br>
    
     <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>

<%   
    }
    else     
    {
%>
     <h1>Sorry, deletion has failed.</h1>
	 
     <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
<%
  }
    stmt.close();			// àÅÔ¡ãªé¤ÓÊÑè§¢Í§ SQL
	conn.close();				// ÂØµÔ¡ÒÃàª×èÍÁâÂ§¡Ñº°Ò¹¢éÍÁÙÅ
%>
    </body>
</html>
