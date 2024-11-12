package com.zam.JavaTestMockito.services;

import com.zam.JavaTestMockito.data.PersonList;
import com.zam.JavaTestMockito.entities.PersonEntity;
import com.zam.JavaTestMockito.payload.PersonResponse;
import com.zam.JavaTestMockito.repositories.PersonRepository;
import com.zam.JavaTestMockito.services.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPersons() {
        List<PersonEntity> mockPersons = PersonList.getPersonList();

        when(personRepository.findAll()).thenReturn(mockPersons);

        List<PersonResponse> result = personServiceImpl.getAllPersons();

        assertEquals(mockPersons.size(), result.size());
        assertEquals(1, result.get(0).getPersonId());
        assertEquals("Jose", result.get(0).getFirstName());
        assertEquals("Zambrano", result.get(0).getLastName());

        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testGetPerson() {
        //Configuracion del mock para devolver un PersonEntity cuando existe el ID
        PersonEntity mockPerson = new PersonEntity(1, "Jose", "Zambrano");
        when(personRepository.findById(1)).thenReturn(Optional.of(mockPerson));

        //Llamada al metodo real del servicio
        PersonResponse result = personServiceImpl.getPerson(1);

        //Verificaciones
        assertEquals(mockPerson.getPersonId(), result.getPersonId());
        assertEquals("Jose", result.getFirstName());
        assertEquals("Zambrano", result.getLastName());

        //Verificar que se llamo a personRepository.findById(1) exactamente una vez
        verify(personRepository, times(1)).findById(1);
    }

}
