package info.wonlee.assessment.accela.command;

import info.wonlee.assessment.accela.ConsoleInput;
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
public class DeletePersonCommand implements ConsoleCommand {
    private final PersonService personService;

    public DeletePersonCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        System.out.print("Enter Person ID to Delete: ");
        String idString = ConsoleInput.waitInput();

        long id = 0L;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            System.out.printf("%s is not number\n", idString);
            return;
        }

        Optional<Person> personOpt = personService.getPerson(id);
        if (personOpt.isEmpty()) {
            System.out.printf("Person with ID %d not found\n", id);
            return;
        }

        Person person = personOpt.get();
        personService.delete(id);
        System.out.printf("Person ID: %d, Name: %s %s is deleted from DB\n",
                person.getId(), person.getFirstName(), person.getLastName());
    }
}
