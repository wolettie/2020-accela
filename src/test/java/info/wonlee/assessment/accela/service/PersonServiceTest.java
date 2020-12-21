package info.wonlee.assessment.accela.service;

import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void test_person_save() {
        String firstName = "Won";
        String lastName = "Lee";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        final CommandResult<Person> result = personService.save(person);

        assert result.getErrorMessage() == null;

        Person saved = result.getResult();
        assert saved.getId() > 0L;
        assert saved.getFirstName().equals(firstName);
        assert saved.getLastName().equals(lastName);
    }
}
