<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><title>Currency Converter</title></head>
    <body>
        <h1>Currency Converter</h1>
        <hr>
        <p>Enter an amount to convert:</p>
        <form method="post" action="ConvertCurrencyServlet">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td> Convert: <br />
                        <input type="text" name="amount" value="1" size="10" tabindex="1" />
                        <div>Enter an amount</div>
                    </td>
                </tr>
                <tr>
                    <td> From this currency:<br />
                        <select name="From" size="3" tabindex="2">
                            <option value="USD">America (United States), Dollar (USD)</option>
                            <option value="THB">Thai, Baht (THB)</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td> To this currency:<br />
                        <select name="To" size="3" tabindex="3">
                            <option value="USD">America (United States), Dollar (USD)</option>
                             <option value="THB">Thai, Baht (THB)</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input name="cmdSubmit" type="submit" value="Submit" alt="Convert" tabindex="4" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
