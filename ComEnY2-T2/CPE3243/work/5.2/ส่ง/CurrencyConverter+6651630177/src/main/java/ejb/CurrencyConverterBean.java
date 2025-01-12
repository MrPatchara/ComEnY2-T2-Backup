package ejb;

import jakarta.ejb.Stateless;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Stateless
public class CurrencyConverterBean {
    private final BigDecimal USD = new BigDecimal("1.0");
    private final BigDecimal THB = new BigDecimal("30.787800");
    private final BigDecimal EUR = new BigDecimal("0.883000");
    private final BigDecimal GBP = new BigDecimal("0.736500");
    private final BigDecimal JPY = new BigDecimal("113.500000");

    public BigDecimal convert(String from, String to, BigDecimal amount) {
        if (from.equals(to)) {
            return amount;
        } else {
            BigDecimal fromRate = findRate(from);
            BigDecimal toRate = findRate(to);
            BigDecimal result = toRate.divide(fromRate, 10, RoundingMode.HALF_EVEN).multiply(amount);
            return result.setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public BigDecimal findRate(String currency) {
        switch (currency) {
            case "USD":
                return USD;
            case "THB":
                return THB;
            case "EUR":
                return EUR;
            case "GBP":
                return GBP;
            case "JPY":
                return JPY;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }
}
