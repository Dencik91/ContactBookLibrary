package org.bookmanagement;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ContactBookManagerImpl implements ContactBookManager {
    protected Map<String, String> contacts = new HashMap<>();
    private Logger logger;

    public void addContact(String name, String number) {
        contacts.put(name, number);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void viewContact() {
        for(Map.Entry<String, String> contact : contacts.entrySet()) {
            logger.info("************* List of Contacts ***************");
            logger.info("Name: {}, Phone: {}", contact.getKey(), contact.getValue());
        }
    }
}
