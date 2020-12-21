package info.wonlee.assessment.accela.service;

import info.wonlee.assessment.accela.dto.CommandResult;
import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.repo.PersonRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public CommandResult<Person> save(Person person) {
        try {
            final Person save = personRepo.save(person);
            return CommandResult.<Person>builder().result(save).build();
        } catch (DataIntegrityViolationException e) {
            return CommandResult.<Person>builder().errorMessage("check data entered meets database constraints such as unique key").build();
        }
    }

    public List<Person> saveAll(List<Person> personList) {
        return personRepo.saveAll(personList);
    }

    public void delete(Long id) {
        Optional<Person> person = personRepo.findById(id);
        person.ifPresent(personRepo::delete);
    }

    public long count() {
        return personRepo.count();
    }

    public List<Person> list() {
        return personRepo.findAll();
    }

    public Optional<Person> getPerson(Long id) {
        return personRepo.findById(id);
    }
}
