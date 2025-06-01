package io.glebtanaka.currency_converter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurrencyType", namespace = "http://glebtanaka.io/currency-converter")
public class CurrencyType {
    
    @XmlValue
    private String value;
    
    public CurrencyType() {
    }
    
    public CurrencyType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value;
    }
}