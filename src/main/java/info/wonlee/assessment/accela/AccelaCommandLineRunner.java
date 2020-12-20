package info.wonlee.assessment.accela;
/***************************************************************
 * Copyright (c) 2020 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 ***************************************************************/

import info.wonlee.assessment.accela.repo.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Slf4j
@Component
@Profile("!test")
public class AccelaCommandLineRunner implements CommandLineRunner {
    private final PersonRepo personRepo;

    public AccelaCommandLineRunner(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            boolean keepGoing = true;
            while (keepGoing) {
                System.out.print("Enter Command Number: ");
                String line = scanner.nextLine();
                keepGoing = identifyCommand(line);
            }
        } catch (Exception e) {
            log.error("unhandled exception, closing application", e);
        } finally {
            scanner.close();
        }

        log.warn("application to be shutdown");
    }

    private boolean identifyCommand(String line) {
        switch (line) {
            case "0":
                return false;
            case "1": // add person
                break;
            case "2": // edit person
                break;
            case "3": // delete person
                break;
            case "4": // count persons
                break;
            case "5": // list persons
                break;
            case "6": // data loading
                break;
            default:
                System.out.println("invalid command number");
                log.warn("invalid command number");
        }
        System.out.println("Command Process is completed");
        return true;
    }
}
