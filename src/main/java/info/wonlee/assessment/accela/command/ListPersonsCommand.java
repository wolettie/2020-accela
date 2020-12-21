package info.wonlee.assessment.accela.command;

import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.service.PersonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: wonlee
 * Date: 21/12/2020
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ListPersonsCommand implements ConsoleCommand {
    private final PersonService personService;

    public ListPersonsCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        List<Person> personList = personService.list();
        if (personList.size() < 1) {
            System.out.println("There is no person in database");
            return;
        }

        System.out.println("ID\t{First Name}, {Last Name}");
        for (Person person: personList) {
            System.out.printf("%d\t%s, %s\n", person.getId(), person.getFirstName(), person.getLastName());
        }
    }
}
