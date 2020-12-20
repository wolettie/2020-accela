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

import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.repo.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Slf4j
@Component
public class AccelaCommandLineRunner implements CommandLineRunner {
    private final PersonRepo personRepo;

    public AccelaCommandLineRunner(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Person me = new Person();
        me.setFirstName("Won");
        me.setLastName("Lee");
        personRepo.save(me);
        final List<Person> all = personRepo.findAll();
        log.info("YOYOYO {}", all);

/*
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(line);
*/

    }
}
