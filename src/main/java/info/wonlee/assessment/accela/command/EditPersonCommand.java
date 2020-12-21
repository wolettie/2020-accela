package info.wonlee.assessment.accela.command;

import info.wonlee.assessment.accela.ConsoleInput;
import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.service.PersonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * User: wonlee
 * Date: 21/12/2020
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EditPersonCommand implements ConsoleCommand {
    private final PersonService personService;

    public EditPersonCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        System.out.print("Enter Person ID to Edit: ");
        String idString = ConsoleInput.waitInput();

        long id = 0L;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            System.out.printf("%s is not number\n", idString);
        }

        Optional<Person> personOpt = personService.getPerson(id);
        if (personOpt.isEmpty()) {
            System.out.printf("Person with ID %d not found\n", id);
            return;
        }

        final Person person = personOpt.get();
        System.out.printf("First Name [%s]: ", person.getFirstName());
        String firstName = ConsoleInput.waitInput();
        System.out.printf("Last Name [%s]: ", person.getLastName());
        String lastName = ConsoleInput.waitInput();

        if (firstName.isBlank()) {
            firstName = person.getFirstName();
            System.out.println("first name is blank, won't be changed");
        }
        if (lastName.isBlank()) {
            lastName = person.getLastName();
            System.out.println("first name is blank, won't be changed");
        }

        person.setFirstName(firstName);
        person.setLastName(lastName);

        final CommandResult<Person> result = personService.save(person);
        if (result.getErrorMessage() == null) {
            Person saved = result.getResult();
            System.out.printf("updated, ID: %d, First Name: %s, Last Name: %s\n",
                    saved.getId(), saved.getFirstName(), saved.getLastName());
        } else {
            System.out.println("failed to update person, " + result.getErrorMessage());
        }
    }
}
