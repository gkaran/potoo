package gk.potoo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyPathResourceResolverTest {

    @Mock
    Resource location;

    private static final String RESOURCE_PATH = "/foo/bar";
    private MyPathResourceResolver resourceResolver = new MyPathResourceResolver();

    @Test
    public void testResourceDoesNotExist() throws Exception {
        when(location.exists()).thenReturn(false);
        assertEquals(resourceResolver.getResource(RESOURCE_PATH, location), null);
    }

    @Test
    public void testResourceIsNotReadable() throws Exception {
        when(location.exists()).thenReturn(true);
        when(location.isReadable()).thenReturn(false);
        assertEquals(resourceResolver.getResource(RESOURCE_PATH, location), null);
    }

    @Test
    public void testResourceExistsAndIsReadable() throws Exception {
        when(location.exists()).thenReturn(true);
        when(location.isReadable()).thenReturn(true);
        assertEquals(resourceResolver.getResource(RESOURCE_PATH, location), location);

    }
}