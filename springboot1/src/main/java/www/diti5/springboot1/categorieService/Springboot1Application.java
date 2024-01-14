package www.diti5.springboot1.categorieService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Springboot1Application 
{

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}

	// @GetMapping("/")
	@RequestMapping("/")
	public String getIndexPage()
	{
		return "index";
	}

}
