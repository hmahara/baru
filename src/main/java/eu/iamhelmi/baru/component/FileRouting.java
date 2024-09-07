package eu.iamhelmi.baru.component;



import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileRouting extends RouteBuilder {
	
	@Autowired
	private FileDetectedProcessor proc;
	
	
	@Override
	public void configure() throws Exception {
	from("file:/tmp/sat/in/?recursive=true")
	.log(" Process Started ").log("${body}") 
	.process(new FileDetectedProcessor())
	.to("file:/tmp/sat/out")
	//.to("scp://www.h4world.eu:22/tmp?username=ubuntu")
    ;
		
	}
	
}
