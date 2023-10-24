package org.acme.kafka.processor;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.acme.kafka.model.Quote;
import org.acme.kafka.model.QuoteDLQ;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


import io.smallrye.reactive.messaging.annotations.Blocking;

/**
 * A bean consuming data from the "quote-requests" Kafka topic (mapped to "requests" channel) and giving out a random quote.
 * The result is pushed to the "quotes" Kafka topic.
 */
@ApplicationScoped
public class QuotesProcessor {

	@Inject
    EntityManager em;
	
    private Random random = new Random();

    @Incoming("requests")
    @Outgoing("quotes")
    @Blocking
    public Quote process(String quoteRequest) throws InterruptedException {
        // simulate some hard working task
        Thread.sleep(200);
        
        if(quoteRequest.contains("broken")) { 
        	String msg = "Message request broken";
        	persistDLQ(quoteRequest,msg);
        	throw new IllegalArgumentException(msg);
        }
        
        return new Quote(quoteRequest, random.nextInt(100));
    }
    
    @Transactional
    public void persistDLQ(String quoteRequest, String message) {
    	QuoteDLQ q = new QuoteDLQ(quoteRequest,0,message);
    	em.persist(q);
    }
    
    
	/*
	 * @Incoming("jdbc-dlq-topic")
	 * 
	 * @Outgoing("quotes")
	 * 
	 * @Blocking public Quote processDLQ(Message<String> message) throws Exception {
	 * System.out.println("Message payload  --> " +
	 * message.getPayload().toString());
	 * 
	 * return new Quote(); }
	 */
   
}


