package ibf2021.assessment.csf.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry cors){
        cors.addMapping("/api/**");
    }    
}