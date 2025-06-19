package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(long id) {
        Optional<Person> personOptional = personRepository.findById(id);

        return personOptional.orElse(null);
    }

    //CLEANUP: should this be roleType?
    public List<Person> getPersonByRole(Role role) {
        return personRepository.findByRole(role);
    }


    public Person createPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }

    public Person updatePerson(long id, Person updatedPerson) {
        Optional<Person> personToUpdateOptional = personRepository.findById(id);

        if (personToUpdateOptional.isPresent()) {
            Person personToUpdate = personToUpdateOptional.get();

            //personToUpdate.setUsername(updatedPerson.getUsername());;

            return personRepository.save(personToUpdate);
        }

        return null;
    }

    public void deletePersonById(long id) {
        personRepository.deleteById(id);
    }


}
