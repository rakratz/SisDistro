package com.example.SpringTweet.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "tweets_db")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private LocalDateTime postTime;

    @Column(nullable = false, length = 280)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User tweetUser;

    public Tweet() {
    }

    
    public Tweet(Integer id, LocalDateTime postTime, String content, User tweetUser) {
		this.id = id;
		this.postTime = postTime;
		this.content = content;
		this.tweetUser = tweetUser;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public LocalDateTime getPostTime() {
		return postTime;
	}



	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}



	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(User tweetUser) {
        this.tweetUser = tweetUser;
    }
}
