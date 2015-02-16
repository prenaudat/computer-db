package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.excilys.computerdatabase.core.model.User;


public interface UserDBService {
    /**
     * Find a person.
     */
    public User findById(Integer id);

    /**
     * Find persons.
     */
    public List<User> find();

    /**
     * Saves person.
     */
    public void save(User user);

    /**
     * Deletes person.
     */
    public void delete(User user);

}
