package org.acme.kafka.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class QuoteDLQ {
	
	 private Long id;
	 
	 private String key;
	 
	 private int price;
	
	 private String header; 
	 
	 private boolean flag = Boolean.FALSE;
	 
	 public QuoteDLQ() { }

	 public QuoteDLQ(String key, int price,String header) {
		 this.key = key;
		 this.price = price;
		 this.header = header;
	 }
	 
	@Id
    @SequenceGenerator(name = "quoteSeq", sequenceName = "quote_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "quoteSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setHeader(String header) {
    	this.header = header;
    }

    public String getHeader() {
    	return this.header;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public void setFlag(boolean flag) {
    	this.flag = flag;
    }
    
    public boolean isFlag() {
    	return this.flag;
    }
    
    @Override
    public String toString() {
        return "QuoteDLQ{" +
                "key='" + key + '\'' +
                ", price=" + price +
                '}';
    }
}
