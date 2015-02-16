package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.core.model.User;
import com.excilys.computerdatabase.persistence.UserRepository;
import com.excilys.computerdatabase.service.UserDBService;

@Service
public class UserDBServiceImpl implements UserDBService, UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id);
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
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		return null;
	}

	

}
