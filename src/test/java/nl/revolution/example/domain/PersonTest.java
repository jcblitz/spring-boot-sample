package nl.revolution.example.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void getFirstName() {
    }

    @Test
    public void getLastName() {
    }

    @Test
    public void getFullName() {
        Person person = new Person("Jared", "Blitzstein");
        assertEquals("Blitzstein Jared", person.getFullName());
    }
}