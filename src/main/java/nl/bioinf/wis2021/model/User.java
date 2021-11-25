package nl.bioinf.wis2021.model;

import java.util.Objects;

public class User {
    private String theFirstName;
    private String lastName;
    private int age;

    private Address address;


    public User(String firstName, String lastName, int age) {
        this.theFirstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = new Address("UnderTheBridge", 0);
    }

    public String getFirstName() {
        return theFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String name() {
        return theFirstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + theFirstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(theFirstName, user.theFirstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theFirstName, lastName, age);
    }
}
