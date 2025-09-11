package lx.edu.levelup.common.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext web = new AnnotationConfigWebApplicationContext();
		web.register(WebMVCConfig.class);
		
		ServletRegistration.Dynamic sd = servletContext.addServlet("dispatcher", new DispatcherServlet(web));
		sd.setLoadOnStartup(1);
		sd.addMapping("/");
		
	}

}
