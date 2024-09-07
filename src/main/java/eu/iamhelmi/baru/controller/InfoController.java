package eu.iamhelmi.baru.controller;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class InfoController {
	
	@GetMapping ("/info")
    public ResponseEntity<String> getInfo(){
		
        return new ResponseEntity<String> ("info",HttpStatus.OK);
    }
	
	@PostMapping(path= "/upload", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addEmployee(@RequestParam String fileName, @RequestBody String s) throws Exception 
	{       
		log.info("fileName: {}", fileName);
		log.info(s);
		String folder = "/storage/dump";
		FileUtils.write(new File(folder+File.separatorChar+fileName), s, Charset.defaultCharset());

    return new ResponseEntity<String> ("info",HttpStatus.OK);
	}

}
