package gm.zona_fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZonaFitApplication {

	public static void main(String[] args) {
		// Esto es lo único que necesitas para levantar el servidor web al cien
		SpringApplication.run(ZonaFitApplication.class, args);
	}
}