package gk.potoo.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class StaticResourcesConfig extends WebMvcConfigurerAdapter {

    private ResourceProperties resourceProperties = new ResourceProperties();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Integer cachePeriod = resourceProperties.getCachePeriod();

        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .setCachePeriod(cachePeriod);

        registry.addResourceHandler("/lib/**")
            .addResourceLocations("classpath:/static/lib/")
            .setCachePeriod(cachePeriod);

        registry.addResourceHandler("/app/**")
            .addResourceLocations("classpath:/static/app/")
            .setCachePeriod(cachePeriod);

        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/index.html")
            .setCachePeriod(cachePeriod).resourceChain(true)
            .addResolver(new PathResourceResolver() {
                @Override
                protected Resource getResource(String resourcePath, Resource location) throws IOException {
                    return location.exists() && location.isReadable() ? location : null;
                }
            });
    }

}
