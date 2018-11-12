package ligacao.ligacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LigacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LigacaoApplication.class, args);
		System.out.println("Ligado");
	}
}
