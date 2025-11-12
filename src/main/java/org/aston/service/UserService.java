package org.aston.service;

import org.aston.dao.Dao;
import org.aston.dao.UserDao;
import org.aston.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final Dao userDao = new UserDao();

    public void addUser(String name, String email, int age) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserById(Long id) {
        return (User) userDao.get(id).get();
    }

    public boolean updateUser(Long id, String name, String email) {
        User user = (User) userDao.get(id).get();
        if (user == null) return false;

        if (name == null ||
                name.isBlank() ||
                email == null ||
                email.isBlank())
            return false;


        userDao.update(user, );
        return true;
    }

    public boolean deleteUser(Long id) {
        User user = userDao.findById(id);
        if (user == null) return false;
        userDao.delete(id);
        return true;
    }

    public void close() {
        userDao.close();
    }
