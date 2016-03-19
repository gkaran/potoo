package gk.potoo.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class StaticResourcesConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/lib/**")
            .addResourceLocations("classpath:/static/lib/");

        registry.addResourceHandler("/app/**")
            .addResourceLocations("classpath:/static/app/");

        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/index.html")
            .resourceChain(true)
            .addResolver(new MyPathResourceResolver());
    }

}
