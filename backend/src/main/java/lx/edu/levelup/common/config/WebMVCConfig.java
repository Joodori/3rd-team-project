package lx.edu.levelup.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//주형수정
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//주형수정
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lx.edu.levelup.common.interceptor.Interceptor;

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

	@Bean
	public Interceptor loginInterceptor() {
		return new Interceptor();
		
	}
	
/*	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**") // 모든 URL에 적용하되,
                .excludePathPatterns("/login", "/resources/**"); // 로그인 페이지와 리소스 파일은 제외
	}*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

    // 주형 수정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해
                // localhost:5173과 ngrok 주소 모두를 허용하도록 변경
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("*")  // 모든 HTTP 메서드 허용 (GET, POST 등)
                .allowedHeaders("*")  // 모든 헤더 허용
                .allowCredentials(true);  // 쿠키 인증 허용 시 필요
    }
// 주형수정
}

