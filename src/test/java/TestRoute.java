import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Rashad
 */
public class TestRoute {
    //override setupRegistry, createRouteBuilder etc needed for tests
    @Before
    public void setUpData() throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);


        camelContext.getRouteDefinitions().get(0).adviceWith(camelContext, new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // intercept sending to sql and do something else
                interceptSendToEndpoint("sql:.SELECT * FROM ARTICLES WHERE CATEGORY='Camel'")
                        .skipSendToOriginalEndpoint()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
//                                if (exchange.in.getHeader(Exchange.INTERCEPTED_ENDPOINT).equals(routeUnderTest.getCountyQuery())) {
//                                    exchange.in.setBody([[NAME:
//                                    'King']]) //mock data
//                                } else if (exchange.in.getHeader(Exchange.INTERCEPTED_ENDPOINT).equals(routeUnderTest.getMayorQuery())) {
//                                    exchange.in.setBody([[MAYOR_NAME:
//                                    'Franklin']])  //mock data
//                                }
                            }
                        });

            }
        });
    }

    @Test
    public void testRoute() {
        //test and asserts etc
    }
}