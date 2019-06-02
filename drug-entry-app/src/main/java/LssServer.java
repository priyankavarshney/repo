
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@PropertySource("classpath:application.properties")
@SpringBootApplication
@ComponentScan("com.lss")

public class LssServer
{
	@Value("${server.port}")
	
public static void main(String args[])
{
	SpringApplication.run(LssServer.class,args);
}
	@Bean
	public Docket productApi()
	{
	return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.lss")).paths(PathSelectors.ant("/api/*"))
	         .build().apiInfo(apiEndPointsInfo());
	}
	
	private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
            .description("Product Management REST API")
            .contact(new Contact("Ramesh Fadatare", "www.javaguides.net", "ramesh24fadatare@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
		
		
		         
		         /*return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/**")).build().apiInfo
				(new ApiInfo("Products APIs","APIs to enter the drugs in LSS","v1","","priyankavarshney","",""));
				}*/
	}

	