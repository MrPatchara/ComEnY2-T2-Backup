/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

/**
 *
 * @author piyavit
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.Stateless;

@Stateless
public class BookSessionBean implements BookSessionBeanLocal 
{
    List<String> contents;
    String BookId5Digit;

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;
    private String query = null;;

    @Override
    public void initialize(String bookid5digit) 
    {
        if (bookid5digit != null) 
        {
            BookId5Digit = bookid5digit;
            try 
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rulib", "root", "engineer");
            }
            catch(Exception e) 
            {
                System.err.println("Sorry failed to connect to the Database." + e.getMessage());
            }
        }
        contents = new ArrayList<String>();
    }

    @Override
    public void addBook(String bookid5digit) 
    {
        try 
        {
            stmt = conn.createStatement();
            //query = "INSERT INTO engbook VALUES('" + customerName + "','" + title + "')";
            //stmt.executeUpdate(query);
        }
        catch(Exception e) 
        {
            System.err.println("Sorry failed to insert values from the database table. " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(String bookid5digit) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBook(String bookid5digit) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}

