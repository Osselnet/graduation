package ru.topjava.graduation.data;

import ru.topjava.graduation.model.User;

public class UserTestData {
    public static final User ADMIN = new User(100000, "admin", "admin");
    public static final User USER = new User(100001, "user", "password");
    public static final User USER_2 = new User(100002, "user2", "password");
}
