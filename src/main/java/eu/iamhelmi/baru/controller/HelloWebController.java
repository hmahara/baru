package eu.iamhelmi.baru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.iamhelmi.baru.config.BaruUtility;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloWebController {

	@GetMapping({"/", "/hello"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        log.info("A {}", BaruUtility.getUriBase());
        return "hello";
    }
}
