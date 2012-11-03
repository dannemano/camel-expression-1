package se.supportix.camelreboot;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.supportix.camelreboot.beans.FixerBean;
import se.supportix.camelreboot.messages.ABeanMessage;

/**
 * Hello world!
 * 
 */
public class App2 {
	private static final Logger logger = LoggerFactory.getLogger(App2.class);

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		RouteBuilder route1 = new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("file:inbox?delay=2000").setHeader("camelFileName",simple("IT-HUSET-${header.camelFileName}")).to("file:outbox");

			}
		};

		context.addRoutes(route1);

		context.start();

		System.in.read();

		context.stop();
	}
}
