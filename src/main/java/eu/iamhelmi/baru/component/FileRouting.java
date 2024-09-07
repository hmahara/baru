package eu.iamhelmi.baru.component;



import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileRouting extends RouteBuilder {
	
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
