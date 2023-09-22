package AGOS.agos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")

public class ObrasJavaApplication {


	public static void main(String[] args) {
		SpringApplication.run(ObrasJavaApplication.class, args);
	}



}
