package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.User;

public abstract class ControllerBase {
    public void init(){}
    public void init(Friend friend) {}
    public void init(Group group) {}
}
