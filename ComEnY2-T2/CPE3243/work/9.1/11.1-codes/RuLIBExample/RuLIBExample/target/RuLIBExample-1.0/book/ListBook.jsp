<%-- 
    Document   : ListBook
    Created on : Nov 10, 2015, 1:16:21 PM
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
        <center>
        <table border="2" width=100%  bgcolor=#eeff00 cols="9">
        <br>
        <tr>
            <td bgcolor="#000099" colspan="9" border="0"><p align="center">
            <font color=yellow size=+2"><b>RU Engineering Books</b></p></td>
        </tr>
        <tr> 
            <td><b>BookId5Digit</b></td>
            <td><b>BookId6Digit</b></td>
            <td><b>BookLanguage</b></td>
            <td><b>BookAuthor</b></td>
            
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
            rs = stmt.executeQuery("select * from engbook order by BookID5Digit asc");  

            if (rs != null)				
            {
                while (rs.next())		
                {
                    String MyBookId5Digit = rs.getString("BookId5Digit");
                    String MyBookId6Digit = rs.getString("BookId5Digit");
                    String MyBookTitle = rs.getString("BookTitle");
                    String MyBookAuthor = rs.getString("BookAuthor");
                    
%>
<tr>
	 
      <td><%= MyBookId5Digit %></td>
      <td><%= MyBookId6Digit %></td>
      <td><%= MyBookTitle %></td>
      <td><%= MyBookAuthor %></td>
      
</tr>
<%
                } 
            }  

            stmt.close();		
            conn.close();		    
%>
        </table>
        </center>
        <br>

        <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
