package info.wonlee.assessment.accela.repo;


import info.wonlee.assessment.accela.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

public interface PersonRepo extends JpaRepository<Person, Long> {
}
