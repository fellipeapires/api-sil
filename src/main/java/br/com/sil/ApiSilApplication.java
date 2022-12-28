package br.com.sil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import br.com.sil.config.ApiProperty;

@SpringBootApplication//(exclude = { UserDetailsServiceAutoConfiguration.class })
@EnableConfigurationProperties(ApiProperty.class)
public class ApiSilApplication extends SpringBootServletInitializer {
	
	private static ApplicationContext APPLICATION_CONTEXT;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiSilApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ApiSilApplication.class, args);
	}
	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}
}
