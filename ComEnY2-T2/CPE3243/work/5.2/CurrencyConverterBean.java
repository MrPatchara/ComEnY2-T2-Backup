package ejb;

import jakarta.ejb.Stateless;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Stateless
public class CurrencyConverterBean {
    private final BigDecimal USD = new BigDecimal("0.0324803");
    private final BigDecimal THB = new BigDecimal("30.787800");

    public BigDecimal convert(String from, String to, BigDecimal amount) 
	{
        if(from.equals(to)) 
	{
            return amount;
        }
        else 
	{
            BigDecimal toRate = findRate(to);
            BigDecimal result = toRate.multiply(amount);
            return result.setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public BigDecimal findRate(String to) 
    {
        BigDecimal returnValue = null;
        if(to.equals("THB")) 
		{
            returnValue = THB;
        }
        if(to.equals("USD")) 
		{
            returnValue = USD;
        }
        return returnValue;
    } 
}