package org.bookManagement;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ContactBookManagerImpl implements ContactBookManager {
    private Map<String, String> contacts = new HashMap<>();
    private Logger logger;

    public ContactBookManagerImpl(Logger logger) {
        this.logger = logger; // Inject logger
    }

    public ContactBookManagerImpl() {
        this(LoggerFactory.getLogger(ContactBookManagerImpl.class)); // use default logger
    }

    public boolean isNameValid(String name) {
        return name.matches("^[A-Za-z\\-]+$");
    }

    public boolean isNumberValid(String number) {
        return number.matches("^[0-9+\\-.]+$");
    }

    public void addContact(String name, String number) {
        if (!isNameValid(name) || !isNumberValid(number)) {
            throw new IllegalArgumentException(
                    "Name must be composed from letters and hyphens, " +
                            "Number must be composed from digits, plus signs, and hyphens");
        }
        contacts.put(name, number);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void searchContact(String name) {
        if (contacts.containsKey(name)) {
            logger.info("Contact found: Name: {}, Phone: {}", name, contacts.get(name));
        } else {
            logger.info("Contact: {} not found", name);
        }
    }

    public void viewContact() {
        if (contacts.isEmpty()) {
            logger.info("No contacts available.");
        } else {
            for (Map.Entry<String, String> contact : contacts.entrySet()) {
                logger.info("************* List of Contacts ***************");
                logger.info("Name: {}, Phone: {}", contact.getKey(), contact.getValue());
            }
        }
    }
}
