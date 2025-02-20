/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

import jakarta.ejb.Local;

/**
 *
 * @author piyavit
 */
@Local
public interface BookSessionBeanLocal 
{
    public void initialize(String bookid5digit);
    public void addBook(String bookid5digit);
    public void deleteBook(String bookid5digit);
    public void updateBook(String bookid5digit);    
}
