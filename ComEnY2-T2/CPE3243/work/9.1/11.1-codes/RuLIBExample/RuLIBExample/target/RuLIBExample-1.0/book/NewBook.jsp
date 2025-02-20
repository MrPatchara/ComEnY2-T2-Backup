<%-- 
    Document   : NewBook
    Created on : Nov 10, 2015, 1:27:16 PM
    Author     : piyavit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="post" action="AddBook.jsp">
        <center>
        <table border="2" width="500" cols=3 bgcolor=#dddd00>
           <tr>
                <td bgcolor="#000099" border="0" colspan=3><p align="center">
                    <font color=yellow size=+2"><b> Add New Book </b></p>
                </td>
            </tr>
            <tr>
                    <td align="right" ><b> BookID5Digit : </b></td>
                    <td ><input type="text" value="" name="bkid5digit" width="30"></td>
            </tr>
             <tr>
                    <td align="right" ><b> BookID6Digit : </b></td>
                    <td ><input type="text" value="" name="bkid6digit" width="30"></td>
            </tr>
            <tr>
                    <td align="right" ><b> BookTitle : </b></td>
                    <td ><input type="text" value="" name="bktitle" width="30"></td>
            </tr>
            <tr>
                    <td align="right" ><b> BookAuthor : </b></td>
                    <td ><input type="text" value="" name="bkauthor" width="30"></td>
            </tr>
            <tr>
                    <td colspan="2"> 
                        <center>            
                        <input type="Submit" value="Add Book" name="Submit">
                        </center>
                    </td>
            </tr>
        </table>
        </center>
        </form>     
        <a href="menu.jsp"><font color="red"><b>Go back to book menu control</b></font></a>
    </body>
</html>
