package org.acme.kafka.model;



public class Quote {

    public String key;
    public int price;

    /**
     * Default constructor required for Jackson serializer
     */
    public Quote() { }

    public Quote(String key, int price) {
        this.key = key;
        this.price = price;
    }
    
    
    
    @Override
    public String toString() {
        return "Quote{" +
                "key='" + key + '\'' +
                ", price=" + price +
                '}';
    }
}