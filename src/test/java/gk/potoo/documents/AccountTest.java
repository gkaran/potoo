package gk.potoo.documents;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    private final Account account = new Account();
    private final static String PASSWORD = "password";
    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    public static final String USER_NAME = "username";
    public static final String[] ROLES = new String[]{"ROLE_USER"};

    @Test
    public void testSetPassword() throws Exception {
        account.setPassword(PASSWORD);
        assertTrue(Account.PASSWORD_ENCODER.matches(PASSWORD, account.getPassword()));
    }

    @Test
    public void testSetId() throws Exception {
        account.setId(TestDocumentHelper.ID);
        assertEquals(account.getId(), TestDocumentHelper.ID);
    }

    @Test
    public void testSetFirstName() throws Exception {
        account.setFirstName(FIRST_NAME);
        assertEquals(account.getFirstName(), FIRST_NAME);
    }

    @Test
    public void testSetLastName() throws Exception {
        account.setLastName(LAST_NAME);
        assertEquals(account.getLastName(), LAST_NAME);
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