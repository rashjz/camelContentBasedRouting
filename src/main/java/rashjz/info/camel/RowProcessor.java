package rashjz.info.camel;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Rashad
 */
public class RowProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        Map<String, Object> row = exchange.getIn().getBody(Map.class);
        System.out.println("Processing " + row);
        Article article = new Article();

        article.setAuthor((String) row.get("AUTHOR"));
        article.setCategory((String) row.get("CATEGORY"));
        article.setName((String) row.get("NAME"));
        article.setTags((String) row.get("TAGS"));
        article.setId((Long) row.get("ID"));

        exchange.getOut().setBody(article);
    }

}