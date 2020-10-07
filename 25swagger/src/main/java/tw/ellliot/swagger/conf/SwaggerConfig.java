package tw.ellliot.swagger.conf;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author elliot
 */
@Configuration
public class SwaggerConfig {
	@Bean
	public ApiInfo createApiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger3 UI Demo")
				.description("Author: Elliot")
				.contact(new Contact("Elliot", "http://www.elliot.tw", "elliot.chen@elliot.tw"))
				.version("1.0")
				.build();
	}

	@Bean
	public Docket createRestApi(ApiInfo apiInfo) {
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();
	}
}
