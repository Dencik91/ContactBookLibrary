package libs;

import java.util.HashMap;
import java.util.Map;

public class ContactBookManage {
    Map<String, Long> contacts = new HashMap<>();

    public void addContact(String name, Long number) {
        contacts.put(name, number);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public void viewContact() {
        for(Map.Entry<String, Long> contact : contacts.entrySet()) {
            System.out.println("************* List of Contacts ***************");
            System.out.println("Name: " + contact.getKey() + ", Phone: " + contact.getValue());
        }
    }
}
