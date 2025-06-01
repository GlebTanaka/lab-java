# Spring Boot SOAP Currency Converter

This is a simple SOAP web service for currency conversion built with Spring Boot.

## Features

- Convert between different currencies
- Get a list of supported currencies
- WSDL generation

## Prerequisites

- Java 21
- Maven

## Building and Running

1. Clone the repository
2. Build the project:
   ```
   mvn clean install
   ```
3. Run the application:
   ```
   mvn spring-boot:run
   ```
   
The application will start on port 8080.

## Accessing the WSDL

Once the application is running, you can access the WSDL at:

```
http://localhost:8080/ws/currencyConverter.wsdl
```

## Supported Currencies

The service supports the following currencies:
- USD (US Dollar)
- EUR (Euro)
- GBP (British Pound)
- JPY (Japanese Yen)
- CAD (Canadian Dollar)
- AUD (Australian Dollar)
- CHF (Swiss Franc)

## Testing the Service

You can test the service using tools like SoapUI, Postman, or by creating a client application.

### Example SOAP Request for Currency Conversion

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:cur="http://glebtanaka.io/currency-converter">
   <soapenv:Header/>
   <soapenv:Body>
      <cur:CurrencyConversionRequest>
         <cur:sourceCurrency>USD</cur:sourceCurrency>
         <cur:targetCurrency>EUR</cur:targetCurrency>
         <cur:amount>100</cur:amount>
      </cur:CurrencyConversionRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Example SOAP Request for Getting Supported Currencies

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:cur="http://glebtanaka.io/currency-converter">
   <soapenv:Header/>
   <soapenv:Body>
      <cur:GetCurrenciesRequest/>
   </soapenv:Body>
</soapenv:Envelope>
```

## Implementation Details

The service is implemented using:
- Spring Boot 3.5.0
- Spring Web Services
- JAXB for XML binding
- XSD for schema definition

The main components are:
1. XSD Schema (`src/main/resources/xsd/currency-converter.xsd`)
2. Web Service Configuration (`WebServiceConfig.java`)
3. Currency Repository (`CurrencyRepository.java`)
4. SOAP Endpoint (`CurrencyEndpoint.java`)