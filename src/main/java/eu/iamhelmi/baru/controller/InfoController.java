package eu.iamhelmi.baru.controller;



import java.io.File;
import java.nio.charset.Charset;

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


import lombok.extern.slf4j.Slf4j;

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
		
        return new ResponseEntity<String> ("info",HttpStatus.OK);
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
