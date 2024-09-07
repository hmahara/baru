package eu.iamhelmi.baru.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import eu.iamhelmi.baru.config.BaruUtility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileDetectedProcessor implements Processor{

	
	String uriBase = "http://localhost:5657";
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Test property: {}", BaruUtility.getUriBase());
		if (BaruUtility.getUriBase() != null) {
			uriBase = BaruUtility.getUriBase();
		}
		log.info("Processor is processing {}", exchange.getMessage().getBody(String.class));
		GenericFile v =  (GenericFile) exchange.getProperty("CamelFileExchangeFile");
		log.info("Object: {}", v.getFileName());
		RestClient restClient = RestClient.create();
		ResponseEntity<Void> response = restClient.post()
				  .uri(uriBase + "/upload?fileName="+v.getFileName())
				  .contentType(MediaType.TEXT_PLAIN)
				  .body(exchange.getMessage().getBody(String.class))
				  .retrieve()
				  .toBodilessEntity();
		
	}

}
