package gk.potoo.ws.controllers;

import gk.potoo.TestContext;
import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import gk.potoo.util.JSONSerializerUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class AccountControllerTest {

    private static final String EMAIL = "test@test.test";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "123123";
    private static final String FULLNAME = "John Doe";

    @Autowired
    private AccountRepository repository;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(repository);
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(accountController).build();
        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc
            .perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONSerializerUtil.convertObjectToJsonBytes(new Account(EMAIL, FULLNAME, PASSWORD))))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.username").value(USERNAME))
            .andExpect(jsonPath("$.email").value(EMAIL))
            .andExpect(jsonPath("$.roles").value("ROLE_USER"))
            .andExpect(jsonPath("$.id").isEmpty())
            .andExpect(jsonPath("$.fullName").value(FULLNAME))
            .andExpect(jsonPath("$.version").isEmpty())
            .andExpect(jsonPath("$.createdDate").isEmpty())
            .andExpect(jsonPath("$.lastModifiedDate").isEmpty())
            .andExpect(jsonPath("$.password").doesNotExist());
    }
}