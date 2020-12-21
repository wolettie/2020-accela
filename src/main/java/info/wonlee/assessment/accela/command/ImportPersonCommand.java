package info.wonlee.assessment.accela.command;

import info.wonlee.assessment.accela.ConsoleInput;
import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.service.PersonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * User: wonlee
 * Date: 21/12/2020
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ImportPersonCommand implements ConsoleCommand {
    private final PersonService personService;

    public ImportPersonCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        System.out.print("Enter path to XML: ");
        String path = ConsoleInput.waitInput();

        String xmlString = null;
        try {
            xmlString = Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.printf("failed to read file from path %s\n", path);
            return;
        }

        final CommandResult<Person> parseResult = personService.fromXml(xmlString);
        if (parseResult.getErrorMessage() != null) {
            System.out.printf("error: %s\n", parseResult.getErrorMessage());
            return;
        }

        Person person = parseResult.getResult();
        if (person.getId() == null) {
            System.out.printf("Are you sure to create person %s %s\n", person.getFirstName(), person.getLastName());
        } else {
            System.out.printf("Are you sure to update person ID:%d to %s %s\n", person.getId(), person.getFirstName(), person.getLastName());
        }

        System.out.print("[Y/n]");
        String confirmation = ConsoleInput.waitInput();

        if (!confirmation.equalsIgnoreCase("y")) {
            System.out.println("aborting");
            return;
        }

        final CommandResult<Person> saveResult = personService.save(person);
        System.out.printf("%s to %s person %s %s\n",
                (saveResult.getErrorMessage() == null) ? "successful" : "failed",
                (person.getId() == null) ? "create" : "update",
                (person.getId() == null) ? person.getFirstName() + " " + person.getLastName() : "ID: " + person.getId(),
                (saveResult.getErrorMessage() == null) ? "" : ": " + saveResult.getErrorMessage()
        );

    }
}
