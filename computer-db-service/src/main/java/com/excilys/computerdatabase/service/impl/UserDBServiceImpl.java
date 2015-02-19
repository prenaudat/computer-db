package com.excilys.computerdatabase.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.core.model.User;
import com.excilys.computerdatabase.persistence.UserRepository;
import com.excilys.computerdatabase.service.UserDBService;

/**
 * UserDBService for user authentification. Implements UserDBService. / UserDetailsService 
 * @author excilys
 *
 */
@Service
public class UserDBServiceImpl implements UserDBService, UserDetailsService {
	@Autowired
	UserRepository userRepository;

	private EntityManager entityManager;

	/**
	 * Set new entityManager
	 * @param newEm entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager newEm) {
		this.entityManager = newEm;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.UserDBService#findByUserName(java.lang.String)
	 */
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.UserDBService#save(com.excilys.computerdatabase.core.model.User)
	 */
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.UserDBService#delete(com.excilys.computerdatabase.core.model.User)
	 */
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.UserDBService#find()
	 */
	@Override
	public List<User> find() {
		return userRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return entityManager
				.createQuery("from User where username = :username", User.class)
				.setParameter("username", username).getSingleResult();

	}

}
