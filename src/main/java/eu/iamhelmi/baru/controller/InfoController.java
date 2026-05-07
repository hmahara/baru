package eu.iamhelmi.baru.controller;



import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.iamhelmi.baru.model.Person;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping( "/api/v1")
public class InfoController {
	public static final String BASE_API = "/api/v1";
	
	@Value("${app.dump.file:/storage/dump}")
	private String dump;
	
	@GetMapping ("/info")
    public ResponseEntity<String> getInfo(){
		
		// Jackson 3 - Immutable builder pattern
		JsonMapper mapper = JsonMapper.builder()
		    .enable(SerializationFeature.INDENT_OUTPUT)
		    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		    .build();  // Configuration locked after build()
		
        return new ResponseEntity<String> ("info",HttpStatus.OK);
    }
	
	@GetMapping ("/people")
    public ResponseEntity<List<Person>> getPeople() {
		
		// Jackson 3 - Immutable builder pattern
		JsonMapper mapper = JsonMapper.builder()
		    .enable(SerializationFeature.INDENT_OUTPUT)
		    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		    .build();  // Configuration locked after build()
		
		List<Person> resp = new ArrayList<Person>();
		Person u1 = Person.builder().memberSince("2020-11-30").email("helmi@abc.com").fullname("Helmi Mahara").active(true).build();
		resp.add(u1);
		
		Person u2 = Person.builder().memberSince("2021-11-30").email("firas@abc.com").fullname("Firas Mahara").active(true).build();
		resp.add(u2);
		
		
        return new ResponseEntity<List<Person>> (resp,HttpStatus.OK);
    }
	
	@PostMapping(path= "/file", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addEmployee(@RequestParam String fileName, @RequestBody String s) throws Exception 
	{       
		log.info(s);
		log.info("REST Body will be stored to folder {} filename: {}", dump, fileName);
		//String folder = "/storage/dump";
		FileUtils.write(new File(dump+File.separatorChar+fileName), s, Charset.defaultCharset());

    return new ResponseEntity<String> ("info",HttpStatus.OK);
	}

}
