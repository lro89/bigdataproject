package org.fhmuenster.bde;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Inject
	UserRepository userRepository;

	public void findAll() {
		List<User> users = userRepository.findAll();
		System.out.println("Number of users = " + users.size());
		System.out.println(users);
	}
}
