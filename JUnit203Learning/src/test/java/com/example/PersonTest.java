package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person testPerson;

    @BeforeEach
    public void setup() {
        testPerson = new Person("Hannah", "Nelson", LocalDate.parse("1996-01-01"));
    }

    @Test
    public void testGetFullName() {
        String expectedFullName = "Hannah Nelson";
        assertEquals(expectedFullName, testPerson.getFullName());
    }

    @Test
    public void testGetAge() {
//        Person testPerson = new Person("Hannah", "Nelson", LocalDate.parse("1996-01-01"));
        long expectedAge = ChronoUnit.YEARS.between(LocalDate.parse("1996-01-01"), LocalDate.now());
        assertEquals(expectedAge, testPerson.getAge());
    }

}