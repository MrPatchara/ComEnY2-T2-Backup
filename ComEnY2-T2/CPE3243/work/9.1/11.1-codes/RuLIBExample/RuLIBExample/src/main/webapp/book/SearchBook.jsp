<%-- 
    Document   : SearchBook
    Created on : Nov 10, 2015, 1:16:58 PM
    Author     : piyavit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor=cyan>
        <form method="post" action="DisplayBook.jsp">
        <center>
        <table border="2" width="350" cols=3 bgcolor=#dddd00>
        <tr>
            <td bgcolor="#000099" border="0" colspan=3><p align="center">
                <font color=yellow size=+2"><b> Search a Book </b></p></td>
        </tr>

        <tr>
            <td align=right><b>Search by BookId5Digit:</b></td>
            <td><input name="bookid5digit" type="text" width="30"></td>
        </tr>
        <tr> 
            <td align=right><b>Search by BookTitle:</b></td>
            <td><input name="booktitle" type="text" width="30"></td>
        </tr>

        <tr>
            <td colspan="2"> 
                <center>
                <input type="submit" value="Search Now">
                </center>
            </td>
        </tr>
    </table>
    </center>
    </form>
    <br>

    <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
