package gk.potoo.documents;

import org.junit.Test;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;

import static org.junit.Assert.*;

public class AccountTest {

    private final Account account = new Account();
    private final static String EMAIL = "test@test.com";
    private final static String PASSWORD = "password";
    private static final String FULL_NAME = "full name";
    private static final String USER_NAME = "username";
    private static final String[] ROLES = new String[]{"ROLE_USER"};

    @Test
    public void testSetPassword() throws Exception {
        account.setPassword(PASSWORD);
        assertEquals(account.getPassword(), PASSWORD);
    }

    @Test
    public void testEncodePassword() throws Exception {
        assertTrue(Account.PASSWORD_ENCODER.matches(PASSWORD, account.encodePassword(PASSWORD)));
    }

    @Test
    public void testSetId() throws Exception {
        account.setId(TestDocumentHelper.ID);
        assertEquals(account.getId(), TestDocumentHelper.ID);
    }

    @Test
    public void testEmail() throws Exception {
        account.setEmail(EMAIL);
        assertEquals(account.getEmail(), EMAIL);
    }

    @Test
    public void testSetFullName() throws Exception {
        account.setFullName(FULL_NAME);
        assertEquals(account.getFullName(), FULL_NAME);
    }

    @Test
    public void testSetUsername() throws Exception {
        account.setUsername(USER_NAME);
        assertEquals(account.getUsername(), USER_NAME);
    }

    @Test
    public void testSetRoles() throws Exception {
        account.setRoles(ROLES);
        assertArrayEquals(account.getRoles(), ROLES);
    }

    @Test
    public void testSetVersion() throws Exception {
        account.setVersion(TestDocumentHelper.VERSION);
        assertEquals(account.getVersion(), TestDocumentHelper.VERSION);
    }

    @Test
    public void testSetCreatedDate() throws Exception {
        account.setCreatedDate(TestDocumentHelper.DATETIME);
        assertEquals(account.getCreatedDate(), TestDocumentHelper.DATETIME);
    }

    @Test
    public void testSetLastModifiedDate() throws Exception {
        account.setLastModifiedDate(TestDocumentHelper.DATETIME);
        assertEquals(account.getLastModifiedDate(), TestDocumentHelper.DATETIME);
    }
}