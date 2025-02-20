<%-- 
    Document   : EditBook
    Created on : Nov 10, 2015, 5:36:58 PM
    Author     : piyavit
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
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
    
    String bookid5digit = request.getParameter("BookID5Digit");
  
    
    stmt = conn.createStatement();
    rs = stmt.executeQuery("select * from engbook where BookID5Digit=" + bookid5digit);

  if (rs != null)		 
      rs.next();		 

        int bookid = rs.getInt("ID");
        String bookId5digit = rs.getString("BookId5Digit");
        String bookId6digit = rs.getString("BookId6Digit");
        String booktitle = rs.getString("BookTitle");
        String bookauthor = rs.getString("BookAuthor");
%>


<form method="post" action="ModifyBook.jsp">
<center>

    <table border="2" width="500" cols=3 bgcolor=#dddd00>
    <tr>
        <td bgcolor="#ee0000" border="0" colspan=3><p align="center">
            <font color=yellow size=+2"><b> Modify a Book Record </b></p></td>
    </tr>
    <tr> 
        <td align=right><b>BookID:</b></td>
        <td><input type="text" name="bookid"  value="<%= bookid %>"></td>
    </tr>
    <tr>
         <td align=right><b>BookID5Digit:</b></td>
        <td><input type="text" name="bookid5digit" width="30" value="<%= bookid5digit %>"></td>
    </tr>
    <tr>
         <td align=right><b>BookTitle:</b></td>
        <td><input type="text" name="booktitle" width="30" value="<%= booktitle %>"></td>
    </tr>
    <tr>
        <td align=right><b>BookAuthor:</b></td><td>
        <input type="text" name="bookauthor" width="30" value="<%= bookauthor %>"></td>
    </tr>


<tr>
     <td colspan="2"> 
         <center>
            <input type="submit" value="Change Now">
         </center>
     </td>
</tr>
<%
  stmt.close();			
  conn.close();		    
%>
</table>
</center>
</form>
<a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
