package com.zam.JavaTestMockito.services;

import com.zam.JavaTestMockito.payload.PersonRequest;
import com.zam.JavaTestMockito.payload.PersonResponse;
import com.zam.JavaTestMockito.payload.PersonUpdateRequest;

import java.util.List;

public interface PersonService {

    public List<PersonResponse> getAllPersons();
    public PersonResponse createPerson(PersonRequest personRequest);
    public PersonResponse getPerson(Integer personId);
    public PersonResponse updatePerson(PersonUpdateRequest personUpdateRequest);
    public void deletePerson(Integer personId);

}
