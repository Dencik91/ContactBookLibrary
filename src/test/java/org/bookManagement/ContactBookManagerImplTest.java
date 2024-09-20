package org.bookManagement;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactBookManagerImplTest {
    @Mock
    private Logger mockLogger;
    @InjectMocks
    private ContactBookManagerImpl contactBookManagerImpl;

    @BeforeClass
    public void setUp() {
        mockLogger = Mockito.mock(Logger.class);
        contactBookManagerImpl = new ContactBookManagerImpl(mockLogger);
    }

    @Test
    public void testAddContact() {
        contactBookManagerImpl.addContact("John", "1234567890");
        assertEquals(contactBookManagerImpl.getContacts(), Map.of("John", "1234567890"),
                "Contact was not added");
    }

    @Test
    public void testAddContactNegative() {
        boolean isExceptionThrown = false;
        String errorMessage = "";
        try {
            contactBookManagerImpl.addContact("John1", "34AD");
        } catch (Exception e) {
            isExceptionThrown = true;
            errorMessage = e.getMessage();
        }
        assertTrue(isExceptionThrown, errorMessage);
    }

    @Test
    public void testAddContactLength() {
        contactBookManagerImpl.addContact("Valera", "1234567890");
        assertEquals(contactBookManagerImpl.getContacts().size(), 2);
    }

    @Test
    public void testRemoveContact() {
        contactBookManagerImpl.removeContact("John");
        assertEquals(contactBookManagerImpl.getContacts(), Map.of("Valera", "1234567890"),
                "Contact was not removed");
    }

    @Test
    public void testRemoveContactNegative() {
        contactBookManagerImpl.addContact("Valera", "1234567890");
        contactBookManagerImpl.removeContact("John");
        assertEquals(contactBookManagerImpl.getContacts(), Map.of("Valera", "1234567890"),
                "Incorrect contact was removed, actual contacts: " + contactBookManagerImpl.getContacts());
    }

    @Test
    public void testRemoveContactLength() {
        contactBookManagerImpl.removeContact("Valera");
        assertEquals(contactBookManagerImpl.getContacts().size(), 0);
    }

    @Test
    public void testSearchContact() {
        contactBookManagerImpl.addContact("John", "1234567890");
        contactBookManagerImpl.searchContact("John");
        Mockito.verify(mockLogger).info("Contact found: Name: {}, Phone: {}", "John", "1234567890");
    }

    @Test
    public void testSearchContactNegative() {
        contactBookManagerImpl.addContact("John", "1234567890");
        contactBookManagerImpl.searchContact("Johny");
        Mockito.verify(mockLogger).info("Contact: {} not found", "Johny");
    }

    @Test
    public void testSearchContactEmpty() {
        contactBookManagerImpl.removeContact("John");
        contactBookManagerImpl.searchContact("John");
        Mockito.verify(mockLogger).info("Contact: {} not found", "John");
    }

    @Test
    public void testViewContact() {
        contactBookManagerImpl.addContact("John", "1234567890");
        contactBookManagerImpl.addContact("Valera", "1234567890");
        contactBookManagerImpl.viewContact();
        Mockito.verify(mockLogger, Mockito.times(2)).info("************* List of Contacts ***************");
        Mockito.verify(mockLogger).info("Name: {}, Phone: {}", "John", "1234567890");
        Mockito.verify(mockLogger).info("Name: {}, Phone: {}", "Valera", "1234567890");
    }

    @Test
    public void testViewContactEmpty() {
        contactBookManagerImpl.removeContact("John");
        contactBookManagerImpl.removeContact("Valera");
        contactBookManagerImpl.viewContact();
        Mockito.verify(mockLogger).info("No contacts available.");
    }

}
