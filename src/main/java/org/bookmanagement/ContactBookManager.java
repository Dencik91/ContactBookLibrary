package org.bookmanagement;

public interface ContactBookManager {
    void addContact(String name, String number);
    void removeContact(String name);
    void searchContact(String name);
    void viewContact();
}
