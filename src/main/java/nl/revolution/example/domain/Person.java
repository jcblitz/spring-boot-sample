package nl.revolution.example.domain;

public class Person {
    private String firstName, lastName;

    public String getFirstName() {
        return firstName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return new StringBuilder(getFirstName()).append(" ").append(getLastName()).toString();
    }
}
