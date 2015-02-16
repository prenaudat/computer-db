package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.excilys.computerdatabase.core.model.User;


public interface UserDBService extends UserDetailsService{
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

	public User findByUserName(String userName);

}
