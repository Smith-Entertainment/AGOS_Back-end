package AGOS.AGOS;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
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
