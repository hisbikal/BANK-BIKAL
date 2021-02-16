package bni.webservices;

import bni.webservices.controllers.NasabahControllers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class WebServicesApplication {
        
        @GetMapping("/")
        public String home(){
            return "redirect:api/nasabah";
        }
        
	public static void main(String[] args) {
		SpringApplication.run(WebServicesApplication.class, args);
	}

}
