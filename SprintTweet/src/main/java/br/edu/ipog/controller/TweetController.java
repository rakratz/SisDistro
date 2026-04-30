package br.edu.ipog.controller;

import java.util.Date;
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

import br.edu.ipog.model.Tweet;
import br.edu.ipog.repository.TweetRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tweets")
@Tag(name="The Tweet Class")
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;

	@Operation(summary = "Get all Tweet's", method = "GET")
	@GetMapping
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
	
	 @GetMapping("/{id}")
	    public Tweet getTweetById(@PathVariable Integer id) {
	        return tweetRepository.findById(id).orElse(null);
	    }
	
	@PostMapping
	public Tweet createTweet(@RequestBody Tweet tweet) {
		tweet.setPostTime(new Date());
		return tweetRepository.save(tweet);
	}
	
	@PutMapping("{id}")
	public Tweet updateTweet(@PathVariable Integer id, @RequestBody Tweet updateTweet) {
		Tweet existingTweet = tweetRepository.findById(id).orElse(null);
		if (existingTweet != null) {
			existingTweet.setContent(updateTweet.getContent());
			existingTweet.setTweetUser(updateTweet.getTweetUser());
			// Mantendo a hora do Tweet original
			return tweetRepository.save(existingTweet);	
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteTweet(@PathVariable Integer id) {
		tweetRepository.deleteById(id);
	}
}
