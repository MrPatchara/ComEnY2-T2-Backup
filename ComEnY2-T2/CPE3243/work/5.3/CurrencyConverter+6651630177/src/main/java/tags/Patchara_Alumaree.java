/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package tags;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.JspFragment;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author patchara
 */
public class Patchara_Alumaree extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            out.println("<div class=\"container\">\n" +
"            <h1>Currency Converter</h1>\n" +
"            <hr>\n" +
"            <p>Enter an amount to convert:</p>\n" +
"            <form method=\"post\" action=\"ConvertCurrencyServlet\">\n" +
"                <table>\n" +
"                    <tr>\n" +
"                        <td>Convert:</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <input type=\"text\" name=\"amount\" value=\"1\" size=\"10\" tabindex=\"1\" />\n" +
"                            <div class=\"info\">Enter an amount</div>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>From this currency:</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <select name=\"From\" size=\"5\" tabindex=\"2\">\n" +
"                                <option value=\"USD\">America (United States), Dollar (USD)</option>\n" +
"                                <option value=\"THB\">Thai, Baht (THB)</option>\n" +
"                                <option value=\"EUR\">Euro (EUR)</option>\n" +
"                                <option value=\"GBP\">British Pound (GBP)</option>\n" +
"                                <option value=\"JPY\">Japanese Yen (JPY)</option>\n" +
"                            </select>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>To this currency:</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <select name=\"To\" size=\"5\" tabindex=\"3\">\n" +
"                                <option value=\"USD\">America (United States), Dollar (USD)</option>\n" +
"                                <option value=\"THB\">Thai, Baht (THB)</option>\n" +
"                                <option value=\"EUR\">Euro (EUR)</option>\n" +
"                                <option value=\"GBP\">British Pound (GBP)</option>\n" +
"                                <option value=\"JPY\">Japanese Yen (JPY)</option>\n" +
"                            </select>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <input name=\"cmdSubmit\" type=\"submit\" value=\"Convert\" tabindex=\"4\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </form>\n" +
"        </div>");
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Tag01 tag", ex);
        }
    }
    
}
