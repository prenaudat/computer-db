package com.excilys.computerdatabase.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.core.model.User;
import com.excilys.computerdatabase.persistence.UserRepository;
import com.excilys.computerdatabase.service.UserDBService;

/**
 * UserDBService for user authentification. Implements UserDBService. /
 * UserDetailsService
 * 
 * @author excilys
 *
 */
@Service
public class UserDBServiceImpl implements UserDBService, UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDBServiceImpl.class);
	// Autowired component
	@Autowired
	UserRepository userRepository;

	private EntityManager entityManager;

	/**
	 * Set new entityManager
	 * 
	 * @param newEm
	 *            entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager newEm) {
		this.entityManager = newEm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.service.UserDBService#findByUserName(java
	 * .lang.String)
	 */
	public User findByUserName(String userName) {
		LOGGER.info("Queried user repository for user name : {}", userName);
		return userRepository.findByUserName(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.UserDBService#save(com.excilys.
	 * computerdatabase.core.model.User)
	 */
	@Override
	public void save(User user) {
		userRepository.save(user);
		LOGGER.info("Persisted user  : {}", user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.computerdatabase.service.UserDBService#delete(com.excilys
	 * .computerdatabase.core.model.User)
	 */
	@Override
	public void delete(User user) {
		LOGGER.info("Attempting to delete user  : {}", user);
		userRepository.delete(user);
		LOGGER.info("Successfully deleted user : {}", user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerdatabase.service.UserDBService#find()
	 */
	@Override
	public List<User> find() {
		LOGGER.info("Querying user repository for all users");
		return userRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return entityManager
				.createQuery("from User where username = :username", User.class)
				.setParameter("username", username).getSingleResult();

	}

}
