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
import info.wonlee.assessment.accela.repo.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Service
public class PersonService {
    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    // for add & edit
    public Person save(Person person) {
        return personRepo.save(person);
    }

    public List<Person> saveAll(List<Person> personList) {
        return personRepo.saveAll(personList);
    }

    public void delete(Long id) {
        Person person = personRepo.getOne(id);
        personRepo.delete(person);
    }

    public long count() {
        return personRepo.count();
    }

    public List<Person> list() {
        return personRepo.findAll();
    }
}
