package ch.tbz.client.backend.interfaces;

import ch.tbz.client.backend.data.Person;
import javafx.application.Platform;

import java.util.ArrayList;

public abstract class CallbackWrapper {
    public static void sendMessage(MessageCallback messageCallback, String message){
        Platform.runLater(() -> {
            messageCallback.receiveMessage(message);
        });
    }

    public static void update(UpdateCallback messageCallback){
        Platform.runLater(messageCallback::update);
    }

    public static void sendPersons(UserCallback messageCallback, Person people){
        Platform.runLater(() -> {
            messageCallback.sendPersons(people);
        });
    }
}
