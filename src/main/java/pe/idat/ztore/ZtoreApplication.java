package pe.idat.ztore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "ztore-api", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "ZTORE API", version = "2.0", description = "Ztore Api"))
public class ZtoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtoreApplication.class, args);
	}

}
