package se.supportix.camelreboot;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        
        ProducerTemplate template = context.createProducerTemplate();
        
        RouteBuilder route1 = new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("direct:simple").setBody().simple("${body} med ID ${exchangeId}").to("log:se.supportix?level=INFO&showAll=true");
				
				from("direct:bean").setBody().method(MyBeanExpression.class).to("log:se.supportix?level=INFO&showAll=true");
				
				from("direct:header").setHeader("camelFileName").header("${exchangeId}").to("file:outbox");
				
			}
		};
        
		context.addRoutes(route1);
		
		context.start();

		template.sendBody("direct:simple","IT-HUSET"); //Note: InOnly MEP
		
		template.sendBody("direct:header","IT-HUSET på fil!"); //Note: InOnly MEP
		
		Object response = template.requestBody("direct:bean", "Hej");  
		logger.info(response.toString());  //Note InOut MEP!
		
        context.stop();
    }
    
    public static class MyBeanExpression {
    	
    	public String fixMessage(Exchange exchange) {
    		logger.info("In my expression! Message: " + exchange.getIn().getBody());
    		return "Was in fixMessage";
    		
    	}
    	
    }
    
}
