package com.excilys.computerdatabase.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.core.model.User;

@Repository
public interface UserRepository	 extends JpaRepository<User, Long>{

	public User findByUserName(String userName);

}
