package org.vaadin.aes.view.core;

import org.vaadin.aes.model.concrete.User;

import java.util.ArrayList;

public class FakeData {
    public static User getUser() {
        return new User(-99l, "Fake Name", "Fake Lastname", "fake", "fake", "fake@fake.com", "10101010101");

    }
}
