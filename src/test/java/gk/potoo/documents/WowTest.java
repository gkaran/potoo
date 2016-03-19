package gk.potoo.documents;

import org.junit.Test;

import static org.junit.Assert.*;

public class WowTest {

    private static final String TEXT = "This is a wow!";
    private final Wow wow = new Wow();

    @Test
    public void testSetId() throws Exception {
        wow.setId(TestDocumentHelper.ID);
        assertEquals(wow.getId(), TestDocumentHelper.ID);
    }

    @Test
    public void testSetText() throws Exception {
        wow.setText(TEXT);
        assertEquals(wow.getText(), TEXT);
    }

    @Test
    public void testSetCreator() throws Exception {
        wow.setCreator(TestDocumentHelper.CREATOR);
        assertEquals(wow.getCreator(), TestDocumentHelper.CREATOR);
    }

    @Test
    public void testSetVersion() throws Exception {
        wow.setVersion(TestDocumentHelper.VERSION);
        assertEquals(wow.getVersion(), TestDocumentHelper.VERSION);
    }

    @Test
    public void testSetCreatedDate() throws Exception {
        wow.setCreatedDate(TestDocumentHelper.DATETIME);
        assertEquals(wow.getCreatedDate(), TestDocumentHelper.DATETIME);
    }
}