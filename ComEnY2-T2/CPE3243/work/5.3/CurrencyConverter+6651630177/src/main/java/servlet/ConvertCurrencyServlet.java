package servlet;

import ejb.CurrencyConverterBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ConvertCurrencyServlet", urlPatterns={"/ConvertCurrencyServlet"})
public class ConvertCurrencyServlet extends HttpServlet {
    @EJB
    CurrencyConverterBean converterBean = new CurrencyConverterBean();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String amount = request.getParameter("amount");
            if (amount != null && amount.length() > 0) 
            {
                BigDecimal d = new BigDecimal("100");
                BigDecimal convertedAmount = converterBean.convert(request.getParameter("From"), request.getParameter("To"), d);
                //BigDecimal convertedAmount = converterBean.convert("USD", "THB", d);
                out.println("<html>");
                out.println("<head><title>Converted Currency</title></head>");
                out.println("<body>");
                out.println(amount + " " + request.getParameter("From") + " = ");
                out.println(convertedAmount + " " + request.getParameter("To"));
                out.println("<br/>Click <a href='index.jsp'>here</a> to go back");
                out.println("</body>");
                out.println("</html>");
            //}
        }
    } 
}