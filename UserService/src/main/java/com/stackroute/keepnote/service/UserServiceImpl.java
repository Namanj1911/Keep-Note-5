package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently
 * provide any additional behavior over the @Component annotation, but it's a good idea
 * to use @Service over @Component in service-layer classes because it specifies intent
 * better. Additionally, tool support and additional behavior might rely on it in the
 * future.
 * */

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	/*
	 * Autowiring should be implemented for the UserRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

	public User registerUser(User user) throws UserAlreadyExistsException {
		Optional<User> optionalCategory = userRepository.findById(user.getUserId());
		if (optionalCategory.isPresent()) {
			throw new UserAlreadyExistsException("User already exists");
		}
		User user1 =userRepository.insert(user);
		if (user1 == null) {
			throw new UserAlreadyExistsException("User not created");
		}
		return user1;
	}

	/*
	 * This method should be used to update a existing user.Call the corresponding
	 * method of Respository interface.
	 */

	public User updateUser(String userId, User user) throws UserNotFoundException {
		Optional<User> optionalCategory = userRepository.findById(userId);
		if (optionalCategory.isPresent()) {
			userRepository.save(user);
			return user;
		}
		return null;
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	public boolean deleteUser(String userId) throws UserNotFoundException {
		Optional<User> optionalCategory = userRepository.findById(userId);
		if (optionalCategory.isPresent()) {
			userRepository.deleteById(userId);
			return true;
		} else {
			throw new UserNotFoundException("Category does not exist");
		}
	}

	/*
	 * This method should be used to get a user by userId.Call the corresponding
	 * method of Respository interface.
	 */

	public User getUserById(String userId) throws UserNotFoundException {
		Optional<User> optionalCategory = userRepository.findById(userId);
		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			throw new UserNotFoundException("Category Not Found");
		}
	}

}
