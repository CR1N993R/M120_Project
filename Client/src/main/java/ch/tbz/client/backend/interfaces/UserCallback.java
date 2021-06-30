package ch.tbz.client.backend.interfaces;

import ch.tbz.client.backend.data.Person;

import java.util.ArrayList;

public interface UserCallback {
    void sendPersons(Person person);
}
