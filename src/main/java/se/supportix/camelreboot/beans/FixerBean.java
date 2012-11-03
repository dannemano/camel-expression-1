package se.supportix.camelreboot.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.supportix.camelreboot.messages.ABeanMessage;

public class FixerBean {

	private static final Logger logger = LoggerFactory.getLogger(FixerBean.class);
	
	public void setCorrectValues(Object inmessage,Exchange exchange) {
		
		ABeanMessage m1 =  exchange.getIn().getBody(ABeanMessage.class);
		m1.setMessage("Jag var i FixerBean!");
		
	}
	
}
