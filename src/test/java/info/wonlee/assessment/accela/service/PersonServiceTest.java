package info.wonlee.assessment.accela.service;

import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @BeforeEach
    public void test_setup() {
        String firstName = "Won";
        String lastName = "Lee";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        personService.save(person);
    }

    @AfterEach
    public void test_cleanup() {
        personService.list().forEach(it -> personService.delete(it.getId()));
    }

    @Test
    @DisplayName("Person Duplicate Name Test")
    public void test_person_save_fails_on_duplicate_name() {
        String firstName = "Won";
        String lastName = "Lee";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        final CommandResult<Person> result = personService.save(person);

        Assertions.assertNotNull(result.getErrorMessage());
    }

    @Test
    @DisplayName("Second Person Save Test")
    public void test_second_person_save() {
        String firstName = "Don";
        String lastName = "Lee";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        final CommandResult<Person> result = personService.save(person);

        Assertions.assertNull(result.getErrorMessage());
        Assertions.assertEquals("Don", result.getResult().getFirstName());
        Assertions.assertEquals(2, personService.count());
    }

    @Test
    @DisplayName("Edit Person Test")
    public void test_edit_person() {
        final List<Person> list = personService.list();

        Assertions.assertEquals(1, list.size());

        Person person = list.get(0);
        person.setFirstName("Bon");
        final CommandResult<Person> result = personService.save(person);

        Assertions.assertNull(result.getErrorMessage());
        Assertions.assertNull(result.getErrorMessage());
        Assertions.assertEquals("Bon", result.getResult().getFirstName());
    }
}
