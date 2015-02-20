package com.excilys.computerdatabase.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.core.model.User;

/**
 * Repository Bean for User Database
 * 
 * @author excilys
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by name
	 * 
	 * @param userName
	 *            Name of user
	 * @return User with userName as user name
	 */
	public User findByUserName(String userName);

}
