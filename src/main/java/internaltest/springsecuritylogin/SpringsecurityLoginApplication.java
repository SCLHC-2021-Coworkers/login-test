package internaltest.springsecuritylogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Locale;

@SpringBootApplication
public class SpringsecurityLoginApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		Locale.setDefault(Locale.KOREAN);
		SpringApplication.run(SpringsecurityLoginApplication.class, args);
		System.out.println("Server Started");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringsecurityLoginApplication.class);
	}

}
