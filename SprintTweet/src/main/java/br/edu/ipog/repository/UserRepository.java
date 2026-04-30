package br.edu.ipog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ipog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	 User findByScreenName(String screenName);
}
