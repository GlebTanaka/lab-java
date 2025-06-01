package io.glebtanaka.currency_converter.repository;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyRepository {
    private static final Map<String, Map<String, BigDecimal>> exchangeRates = new HashMap<>();

    // List of supported currencies
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF");

    @PostConstruct
    public void initData() {
        // Initialize exchange rates (relative to USD)
        // USD to other currencies
        Map<String, BigDecimal> usdRates = new HashMap<>();
        usdRates.put("USD", BigDecimal.ONE);
        usdRates.put("EUR", new BigDecimal("0.93"));
        usdRates.put("GBP", new BigDecimal("0.79"));
        usdRates.put("JPY", new BigDecimal("150.50"));
        usdRates.put("CAD", new BigDecimal("1.36"));
        usdRates.put("AUD", new BigDecimal("1.52"));
        usdRates.put("CHF", new BigDecimal("0.89"));
        exchangeRates.put("USD", usdRates);

        // EUR to other currencies
        Map<String, BigDecimal> eurRates = new HashMap<>();
        eurRates.put("USD", new BigDecimal("1.08"));
        eurRates.put("EUR", BigDecimal.ONE);
        eurRates.put("GBP", new BigDecimal("0.85"));
        eurRates.put("JPY", new BigDecimal("162.50"));
        eurRates.put("CAD", new BigDecimal("1.47"));
        eurRates.put("AUD", new BigDecimal("1.64"));
        eurRates.put("CHF", new BigDecimal("0.96"));
        exchangeRates.put("EUR", eurRates);

        // Add more exchange rates for other currencies
        // For simplicity, we'll calculate them based on USD rates
        for (String sourceCurrency : SUPPORTED_CURRENCIES) {
            if (!exchangeRates.containsKey(sourceCurrency)) {
                Map<String, BigDecimal> rates = new HashMap<>();
                BigDecimal usdToSource = usdRates.get(sourceCurrency);

                for (String targetCurrency : SUPPORTED_CURRENCIES) {
                    if (sourceCurrency.equals(targetCurrency)) {
                        rates.put(targetCurrency, BigDecimal.ONE);
                    } else {
                        BigDecimal usdToTarget = usdRates.get(targetCurrency);
                        // Calculate cross rate: source -> USD -> target
                        BigDecimal rate = usdToTarget.divide(usdToSource, 6, RoundingMode.HALF_UP);
                        rates.put(targetCurrency, rate);
                    }
                }
                exchangeRates.put(sourceCurrency, rates);
            }
        }
    }

    public List<String> getSupportedCurrencies() {
        return SUPPORTED_CURRENCIES;
    }

    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        validateCurrency(sourceCurrency);
        validateCurrency(targetCurrency);

        return exchangeRates.get(sourceCurrency).get(targetCurrency);
    }

    public BigDecimal convertCurrency(String sourceCurrency, String targetCurrency, BigDecimal amount) {
        BigDecimal exchangeRate = getExchangeRate(sourceCurrency, targetCurrency);
        return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }

    private void validateCurrency(String currency) {
        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }
}
