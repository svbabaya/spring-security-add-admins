package ru.maxima.springboottest.ProjectSpringBoot1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.springboottest.ProjectSpringBoot1.models.Person;
import ru.maxima.springboottest.ProjectSpringBoot1.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {

        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
//        List<Person> byEmail = peopleRepository.findByEmail("mail3@mail.ru");
//        byEmail.forEach(System.out::println);
//        List<Person> alex = peopleRepository.findByName("Alex");
//        alex.forEach(System.out::println);
//        List<Person> n = peopleRepository.findByNameStartingWith("N");
//        n.forEach(System.out::println);
//        List<Person> viktor = peopleRepository.findByNameOrEmail("Viktor", "mail5@mail.ru");
//        viktor.forEach(System.out::println);
//        List<Person> alex1 = peopleRepository.findByNameOrEmail("Alex", "test@maillll,ru");
//        alex1.forEach(System.out::println);
        return peopleRepository.findAll();
    }

    public List<Person> findRoleUser() {
        return peopleRepository.findByRole("ROLE_USER");
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {

        peopleRepository.deleteById(id);
    }
}
