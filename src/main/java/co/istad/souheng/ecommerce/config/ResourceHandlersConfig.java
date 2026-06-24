package co.istad.souheng.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {
    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.client-path}")
    private String clientPathh;


    //if (addResourseLocation classpath:/static/ ) use classpath resource , folder that stored image must be in project
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientPathh + "/**")
                .addResourceLocations("file:"+storageLocation );
    }
}
