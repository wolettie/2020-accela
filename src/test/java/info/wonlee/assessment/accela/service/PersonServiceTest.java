package info.wonlee.assessment.accela.service;
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

        final Person save = personService.save(person);

        assert save.getId() > 0L;
        assert save.getFirstName().equals(firstName);
        assert save.getLastName().equals(lastName);
    }
}
