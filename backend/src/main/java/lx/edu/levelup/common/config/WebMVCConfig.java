package lx.edu.levelup.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//주형임시작업
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//주형임시작업

@Configuration
@EnableWebMvc
@ComponentScan("lx.edu.levelup")
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Bean
	public InternalResourceViewResolver jspResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	//주형 임시작업
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해
                .allowedOrigins("http://localhost:5173")  // Vue 개발서버 주소 입력
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 필요한 HTTP 메서드 명시
                .allowCredentials(true);
	}
    //주형 임시작업

}

