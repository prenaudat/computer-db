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

@Service
public class UserDBServiceImpl implements UserDBService, UserDetailsService {
	@Autowired
	UserRepository userRepository;

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager newEm) {
		this.entityManager = newEm;
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> find() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return entityManager
				.createQuery("from User where username = :username", User.class)
				.setParameter("username", username).getSingleResult();

	}

}
