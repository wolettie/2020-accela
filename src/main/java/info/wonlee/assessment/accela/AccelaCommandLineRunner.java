package info.wonlee.assessment.accela;

import info.wonlee.assessment.accela.command.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Slf4j
@Component
// CommandLineRunner will be auto run on startup, test don't have any standard input hence unable to run if using it
// Skipping CommandLineRunner during the test
@Profile("!test")
public class AccelaCommandLineRunner implements CommandLineRunner {
    private final ApplicationContext applicationContext;

    public AccelaCommandLineRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("-- Available Command --");
            System.out.println("1, Add Person");
            System.out.println("2, Edit Person");
            System.out.println("3, Delete Person");
            System.out.println("4, Count Number of Persons");
            System.out.println("5, List Persons");
            System.out.println("6, Export Data in XML");
            System.out.println("7, Load Data");
            System.out.println("-- Exist Command --");
            System.out.println("0, Exit");
            System.out.print("Enter Command Number: ");
            String line = ConsoleInput.waitInput();
            keepGoing = identifyCommand(line);
        }

        log.warn("application to be shutdown");
    }

    private boolean identifyCommand(String line) {
        ConsoleCommand command = null;
        /*
            Using applicationContext.getBean rather than autowiring.
            Just to show how to get prototype bean in multi user environment.
            Not necessary for this particular CLI application.
         */
        switch (line) {
            case "0":
                return false;
            case "1": // add person
                command = applicationContext.getBean(AddPersonCommand.class);
                break;
            case "2": // edit person
                command = applicationContext.getBean(EditPersonCommand.class);
                break;
            case "3": // delete person
                command = applicationContext.getBean(DeletePersonCommand.class);
                break;
            case "4": // count persons
                command = applicationContext.getBean(CountPersonsCommand.class);
                break;
            case "5": // list persons
                command = applicationContext.getBean(ListPersonsCommand.class);
                break;
            case "6": // data export
                command = applicationContext.getBean(ExportPersonsCommand.class);
                break;
            case "7": // data loading
                command = applicationContext.getBean(ImportPersonCommand.class);
                break;
            default:
                System.out.println("invalid command number");
                log.warn("invalid command number");
        }
        if (command != null) {
            command.execute();
        }
        System.out.println("Command Process is completed");
        return true;
    }
}
