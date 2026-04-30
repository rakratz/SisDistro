package br.edu.ipog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ipog.model.User;
import br.edu.ipog.repository.TweetRepository;
import br.edu.ipog.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User createUser(@RequestBody User user) {
		System.out.println(user.getRole());
		return userRepository.save(user);
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
		User existingUser = userRepository.findById(id).orElse(null);

		if (existingUser != null) {
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setScreenName(updatedUser.getScreenName());
			existingUser.setProfileImage(updatedUser.getProfileImage());
			existingUser.setFollowing(updatedUser.getFollowing());
			existingUser.setBio(updatedUser.getBio());
			existingUser.setRole(updatedUser.getRole());

			return userRepository.save(existingUser);
		}

		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}

}
