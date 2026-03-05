package com.imarkov.parking.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Pricing pricing;
    private int capacity;
    private Booking booking;

    public static class Pricing {
        private Map<String, Double> euroCategory;
        private String currency;

        public Map<String, Double> getEuroCategory() { return euroCategory; }
        public void setEuroCategory(Map<String, Double> euroCategory) { this.euroCategory = euroCategory; }

        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
    }

    public static class Booking {
        private int validity;

        public int getValidity() { return validity; }
        public void setValidity(int validity) { this.validity = validity; }
    }

    public Pricing getPricing() { return pricing; }
    public void setPricing(Pricing pricing) { this.pricing = pricing; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
}
