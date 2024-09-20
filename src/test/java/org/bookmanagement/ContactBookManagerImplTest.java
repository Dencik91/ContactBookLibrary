package org.bookmanagement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactBookManagerImplTest {
    private ContactBookManagerImpl contactBookManagerImpl;

    @BeforeClass
    public void setUp() {
        contactBookManagerImpl = new ContactBookManagerImpl();
    }

    @Test
    public void testAddContact() {
        contactBookManagerImpl.addContact("John", "1234567890");
        assertEquals(contactBookManagerImpl.contacts, Map.of("John", "1234567890"),
                "Contact was not added");
    }

    @Test
    public void testAddContactNegative() {
        boolean isExceptionThrown = false;
        try {
            contactBookManagerImpl.addContact("John", "Doe");
        } catch (Exception e) {
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown, "Exception was not thrown, Name and Number must be String");
    }

    @Test
    public void testAddContactLength() {
        contactBookManagerImpl.addContact("Valera", "1234567890");
        assertEquals(contactBookManagerImpl.contacts.size(), 2);
    }

    @Test
    public void testRemoveContact() {
        contactBookManagerImpl.removeContact("John");
        assertEquals(contactBookManagerImpl.contacts, Map.of("Valera", "1234567890"),
                "Contact was not removed");
    }

    @Test
    public void testRemoveContactNegative() {
        contactBookManagerImpl.removeContact("John");
        assertEquals(contactBookManagerImpl.contacts, Map.of("Valera", "1234567890"),
                "Incorrect contact was removed");
    }

    @Test
    public void testRemoveContactLength() {
        contactBookManagerImpl.removeContact("Valera");
        assertEquals(contactBookManagerImpl.contacts.size(), 0);
    }
}
