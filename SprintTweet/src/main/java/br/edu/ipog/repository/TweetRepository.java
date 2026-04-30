package br.edu.ipog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ipog.model.Tweet;



public interface TweetRepository extends JpaRepository<Tweet, Integer> {
}
