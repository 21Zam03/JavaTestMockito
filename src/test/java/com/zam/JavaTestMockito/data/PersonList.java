package com.zam.JavaTestMockito.data;

import com.zam.JavaTestMockito.entities.PersonEntity;

import java.util.List;

public class PersonList {

   public static List<PersonEntity> getPersonList() {
      return List.of(
              new PersonEntity(1, "Jose", "Zambrano"),
              new PersonEntity(2, "Ludmila", "Dominguez"),
              new PersonEntity(3, "Yessenia", "Marquina"),
              new PersonEntity(4, "Daniela", "Arevalo")
      );
   }

}
