package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.excilys.computerdatabase.core.model.User;

/**
 * User Service for Crud
 * 
 * @author excilys
 *
 */
public interface UserDBService extends UserDetailsService {

	/**
	 * Find users
	 * 
	 * @return List of users
	 */
	public List<User> find();

	/**
	 * Persist a user
	 * 
	 * @param user to be persisted
	 */
	public void save(User user);

	/**
	 * Delete a user
	 * 
	 * @param user to be deleted
	 */
	public void delete(User user);

	/**
	 * Find user by name
	 * 
	 * @param userName User name
	 * @return User with userName
	 */
	public User findByUserName(String userName);

}
