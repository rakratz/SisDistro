package br.edu.ipog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    private String password;
    private String screenName;
    private String profileImage;
    private String following;
    private String bio;

  //  @OneToOne
  //  @JoinColumn(name = "role_id")
  //  private Role role;
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {
	}

	public User(Integer userid, String password, String screenName, String profileImage, String following, String bio,
			Role role) {
		this.userid = userid;
		this.password = password;
		this.screenName = screenName;
		this.profileImage = profileImage;
		this.following = following;
		this.bio = bio;
		this.role = role;
	}

	// getters e setters
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}