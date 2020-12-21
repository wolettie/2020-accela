package info.wonlee.assessment.accela.service;

import info.wonlee.assessment.accela.model.Person;
import info.wonlee.assessment.accela.repo.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@ExtendWith(MockitoExtension.class)
public class PersonServiceUnitTest {

    @Mock
    PersonRepo personRepo;

    @InjectMocks
    PersonService personService;

    @Test
    @DisplayName("XML export")
    public void test_xml_exporting() {
        String firstName = "Won";
        String lastName = "Lee";

        Person person = new Person();
        person.setId(1L);
        person.setFirstName(firstName);
        person.setLastName(lastName);

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personRepo.findAll()).thenReturn(personList);

        final String xml = personService.exportXml();
        Assertions.assertNotNull(xml);

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream resourceStream = classLoader.getResourceAsStream("xml/wonlee-list.xml")) {
            if (resourceStream != null) {
                List<String> xmlLines = new BufferedReader(new InputStreamReader(resourceStream)).lines().collect(Collectors.toList());
                String expectedXml = String.join("\n", xmlLines);
                // watch out for IDE auto format on XML file, xStream indent by 2 spaces but IDE may do it with 4 spaces
                Assertions.assertEquals(expectedXml, xml);
            } else {
                Assertions.fail("resource not found");
            }
        } catch (IOException e) {
            Assertions.fail("failed to read resources");
        }
    }
}
