package interop;

import main.interop.Person;
import main.interop.PersonRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public PersonRegistry getRegistryNotAnnotated() {
        return registry;
    }

    @Nullable
    public PersonRegistry getNullableRegistry() {
        return registry;
    }

    @NotNull
    public PersonRegistry getNotNullRegistry() {
        return registry;
    }
}
