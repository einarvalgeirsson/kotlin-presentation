package main.interop;

import java.util.List;

public class RegistryBackup {
    private PersonRegistry registry;

    public RegistryBackup() {
        registry = new PersonRegistry();
    }

    void performBackup() {
        List<Person> persons = registry.getPersons();
        // .. backup persons to storage
    }
}
