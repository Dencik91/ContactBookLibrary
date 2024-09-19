package libs;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactBookManageTest {
    private ContactBookManage contactBookManage;

    @BeforeClass
    public void setUp() {
        contactBookManage = new ContactBookManage();
    }

    @Test
    public void testAddContact() {
        contactBookManage.addContact("John", 1234567890L);
        assertEquals(contactBookManage.contacts, Map.of("John", 1234567890L),
                "Contact was not added");
    }

    @Test
    public void testAddContactNegative() {
        boolean isExceptionThrown = false;
        try {
            contactBookManage.addContact(1234567890L, "John");
        } catch (Exception e) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown, "Exception was not thrown, Name must be String and Phone must be Long");
    }

    @Test
    public void testAddContactLength() {
        contactBookManage.addContact("Valera", 1234567890L);
        assertEquals(contactBookManage.contacts.size(), 2);
    }
}
