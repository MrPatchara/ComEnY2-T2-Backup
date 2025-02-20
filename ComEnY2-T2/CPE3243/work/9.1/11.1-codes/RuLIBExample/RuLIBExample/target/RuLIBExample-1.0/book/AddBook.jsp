<%-- 
    Document   : AddBook
    Created on : Nov 10, 2015, 1:15:32 PM
    Author     : piyavit
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
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
        try 
        {
            //Class c = Class.forName("com.mysql.jdbc.Driver"); 
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            out.write("Error!!!!!!" + e);
        }
        try {
             conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rulib?useSSL=false","root","engineer");
             out.write("Connected!");       
        }
        catch(SQLException e) {
            System.out.println("Error!!!!!!" + e);
        }        
 
        
        //AddBook.jsp 
        String Mybkid5digit = request.getParameter("bkid5digit");
        String Mybkid6digit = request.getParameter("bkid6digit");
        String Mybktitle = request.getParameter("bktitle");
        String Mybkauthor = request.getParameter("bkauthor");
       
        stmt = conn.createStatement();
  

        String sql = "INSERT INTO engbook (BookId5Digit,BookId6Digit,BookTitle,"+
				"BookAuthor) VALUES('" + Mybkid5digit + "','" +
				Mybkid6digit + "','" + Mybktitle + "','" + Mybkauthor +  "')";
				
	 
        int insertRow = stmt.executeUpdate(sql);

        if (insertRow == 1)			
        {									
    %>
    <h2>Successful Adding New Book</h2>

    <b>Thank you for adding new Book.<b><br><br>
    <li><a href="ListBook.jsp">See all Books</a></li>
    <br><br>
    <a href="menu.jsp"><font color="red"><b>Go back to menu control</b></font></a>

    <%   
        }
        else		
        {
    %>
     <h1>Sorry, addition has failed.</h1>
     <br>
     <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    <%
        }
        stmt.close();			
        conn.close();		    		
     %>
    </body>
</html>
