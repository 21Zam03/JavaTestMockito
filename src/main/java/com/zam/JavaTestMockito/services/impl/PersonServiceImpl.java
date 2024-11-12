package com.zam.JavaTestMockito.services.impl;

import com.zam.JavaTestMockito.entities.PersonEntity;
import com.zam.JavaTestMockito.payload.PersonRequest;
import com.zam.JavaTestMockito.payload.PersonResponse;
import com.zam.JavaTestMockito.payload.PersonUpdateRequest;
import com.zam.JavaTestMockito.repositories.PersonRepository;
import com.zam.JavaTestMockito.services.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonResponse> getAllPersons() {
        List<PersonEntity> personEntities = personRepository.findAll();
        List<PersonResponse> personResponses = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            PersonResponse personResponse = PersonResponse.builder()
                    .personId(personEntity.getPersonId())
                    .firstName(personEntity.getFirstName())
                    .lastName(personEntity.getLastName())
                    .build();
            personResponses.add(personResponse);
        }
        return personResponses;
    }

    @Override
    public PersonResponse createPerson(PersonRequest personRequest) {
        PersonEntity personEntity = PersonEntity.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .build();
        PersonEntity personCreated = personRepository.save(personEntity);
        return PersonResponse.builder()
                .personId(personCreated.getPersonId())
                .firstName(personCreated.getFirstName())
                .lastName(personCreated.getLastName())
                .build();
    }

    @Override
    public PersonResponse getPerson(Integer personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(() -> {
            return new EntityNotFoundException("Entity not found");
        });
        return PersonResponse.builder()
                .personId(personEntity.getPersonId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .build();
    }

    @Override
    public PersonResponse updatePerson(PersonUpdateRequest personUpdateRequest) {
        PersonEntity personEntity = PersonEntity.builder()
                .personId(personUpdateRequest.getPersonId())
                .firstName(personUpdateRequest.getFirstName())
                .lastName(personUpdateRequest.getLastName())
                .build();
        PersonEntity personUpdated = personRepository.save(personEntity);
        return PersonResponse.builder()
                .personId(personUpdated.getPersonId())
                .firstName(personUpdated.getFirstName())
                .lastName(personUpdated.getLastName())
                .build();
    }

    @Override
    public void deletePerson(Integer personId) {
        personRepository.deleteById(personId);
    }
}