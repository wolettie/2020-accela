package info.wonlee.assessment.accela.command;
/***************************************************************
 * Copyright (c) 2020 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 ***************************************************************/

import info.wonlee.assessment.accela.ConsoleInput;
import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.service.PersonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddPersonCommand implements ConsoleCommand {
    private final PersonService personService;

    public AddPersonCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        System.out.print("Enter first name: ");
        String firstName = ConsoleInput.waitInput();
        System.out.print("Enter last name: ");
        String lastName = ConsoleInput.waitInput();;
        if (firstName == null || firstName.isBlank()) {
            System.out.println("first name is blank, aborting command");
            return; // invalid input
        }
        if (lastName == null || lastName.isBlank()) {
            return; // invalid input
        }

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        final CommandResult<Person> result = personService.save(person);
        if (result.getErrorMessage() == null) {
            Person saved = result.getResult();
            System.out.printf("person %s %s is added\n", saved.getFirstName(), saved.getLastName());
        } else {
            System.out.println("failed to save person, " + result.getErrorMessage());
        }
    }
}
