package info.wonlee.assessment.accela.command;

import info.wonlee.assessment.accela.service.PersonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: wonlee
 * Date: 21/12/2020
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExportPersonsCommand implements ConsoleCommand {
    private final PersonService personService;

    public ExportPersonsCommand(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute() {
        System.out.println("Exporting Persons in XML");
        String xml = personService.exportXml();

        System.out.println(xml);
    }
}
