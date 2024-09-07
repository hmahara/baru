package eu.iamhelmi.baru.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileDetectedProcessor implements Processor{
	String uriBase = "http://localhost:5657";
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Processor is processing {}", exchange.getMessage().getBody(String.class));
		log.info("Processor is processing {}", exchange.getProperties().keySet());
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
