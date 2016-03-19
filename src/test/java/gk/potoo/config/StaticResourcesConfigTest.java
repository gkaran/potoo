package gk.potoo.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StaticResourcesConfigTest {

    @Mock
    ResourceHandlerRegistry registry;

    @Mock
    ResourceHandlerRegistration resourceHandlerRegistration;

    @Mock
    ResourceChainRegistration resourceChainRegistration;

    @Before
    public void setUp() throws Exception {
        when(registry.addResourceHandler(anyString())).thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.addResourceLocations(anyString())).thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.resourceChain(anyBoolean())).thenReturn(resourceChainRegistration);
    }

    @Test
    public void testAddResourceHandlers() throws Exception {
        new StaticResourcesConfig().addResourceHandlers(registry);

        verify(registry).addResourceHandler("/static/**");
        verify(resourceHandlerRegistration).addResourceLocations("classpath:/static/");

        verify(registry).addResourceHandler("/lib/**");
        verify(resourceHandlerRegistration).addResourceLocations("classpath:/static/lib/");


        verify(registry).addResourceHandler("/app/**");
        verify(resourceHandlerRegistration).addResourceLocations("classpath:/static/app/");

        verify(registry).addResourceHandler("/**");
        verify(resourceHandlerRegistration).addResourceLocations("classpath:/static/index.html");
        verify(resourceHandlerRegistration).resourceChain(true);
        verify(resourceChainRegistration).addResolver(any(MyPathResourceResolver.class));
    }
}