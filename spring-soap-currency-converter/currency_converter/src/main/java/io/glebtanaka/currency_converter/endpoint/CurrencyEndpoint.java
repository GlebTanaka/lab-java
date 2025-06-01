package io.glebtanaka.currency_converter.endpoint;

import io.glebtanaka.currency_converter.repository.CurrencyRepository;
import io.glebtanaka.currency_converter.wsdl.CurrencyConversionRequest;
import io.glebtanaka.currency_converter.wsdl.CurrencyConversionResponse;
import io.glebtanaka.currency_converter.wsdl.GetCurrenciesRequest;
import io.glebtanaka.currency_converter.wsdl.GetCurrenciesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;

@Endpoint
public class CurrencyEndpoint {
    private static final String NAMESPACE_URI = "http://glebtanaka.io/currency-converter";

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyEndpoint(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CurrencyConversionRequest")
    @ResponsePayload
    public CurrencyConversionResponse convertCurrency(@RequestPayload CurrencyConversionRequest request) {
        CurrencyConversionResponse response = new CurrencyConversionResponse();

        String sourceCurrency = request.getSourceCurrency();
        String targetCurrency = request.getTargetCurrency();
        BigDecimal amount = request.getAmount();

        BigDecimal exchangeRate = currencyRepository.getExchangeRate(sourceCurrency, targetCurrency);
        BigDecimal convertedAmount = currencyRepository.convertCurrency(sourceCurrency, targetCurrency, amount);

        response.setSourceCurrency(sourceCurrency);
        response.setTargetCurrency(targetCurrency);
        response.setSourceAmount(amount);
        response.setTargetAmount(convertedAmount);
        response.setExchangeRate(exchangeRate);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCurrenciesRequest")
    @ResponsePayload
    public GetCurrenciesResponse getCurrencies(@RequestPayload GetCurrenciesRequest request) {
        GetCurrenciesResponse response = new GetCurrenciesResponse();

        for (String currency : currencyRepository.getSupportedCurrencies()) {
            response.getCurrencies().add(currency);
        }

        return response;
    }
}
