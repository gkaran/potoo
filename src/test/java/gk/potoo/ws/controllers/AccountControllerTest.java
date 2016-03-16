package gk.potoo.ws.controllers;

import gk.potoo.PotooApplication;
import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import gk.potoo.util.JSONSerializerUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PotooApplication.class)
public class AccountControllerTest {

    public static final String USERNAME = "John Doe";
    public static final String PASSWORD = "123123";

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(accountController).build();
        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
            post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONSerializerUtil.convertObjectToJsonBytes(new Account(USERNAME, PASSWORD)))
        )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.username").value(USERNAME))
            .andExpect(jsonPath("$.roles").value("ROLE_USER"))
            .andExpect(jsonPath("$.id").isEmpty())
            .andExpect(jsonPath("$.firstName").isEmpty())
            .andExpect(jsonPath("$.lastName").isEmpty())
            .andExpect(jsonPath("$.version").isEmpty())
            .andExpect(jsonPath("$.createdDate").isEmpty())
            .andExpect(jsonPath("$.lastModifiedDate").isEmpty())
            .andExpect(jsonPath("$.password").doesNotExist());
    }
}