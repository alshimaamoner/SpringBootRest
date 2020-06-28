package com.example.springMicroService.service;

import com.example.springMicroService.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserService {
    private static int counter=0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "shimaa", new Date()));
        users.add(new User(2, "doaa", new Date()));
        users.add(new User(3, "alyaa", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public void addUser(User user) {
        if (user.getId()==0) {
            ++counter;
            user.setId(counter);
        }
        users.add(user);

    }

    public User deleteUserById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }

        }
        return null;
}
}
