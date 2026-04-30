package br.edu.ipog.model;

import br.edu.ipog.model.User;
import br.edu.ipog.model.Role;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Inicializa um objeto User para cada teste
        user = new User(1, "password123", "ScreenName", "profile.jpg", "100", "This is a bio", Role.USER);
    }

    @Test
    public void testUserConstructor() {
        // Verifica se o construtor inicializa corretamente os campos
        assertEquals(1, user.getUserid());
        assertEquals("password123", user.getPassword());
        assertEquals("ScreenName", user.getScreenName());
        assertEquals("profile.jpg", user.getProfileImage());
        assertEquals("100", user.getFollowing());
        assertEquals("This is a bio", user.getBio());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        // Teste os setters e getters para todos os campos

        user.setUserid(2);
        assertEquals(2, user.getUserid());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        user.setScreenName("NewScreenName");
        assertEquals("NewScreenName", user.getScreenName());

        user.setProfileImage("newProfile.jpg");
        assertEquals("newProfile.jpg", user.getProfileImage());

        user.setFollowing("200");
        assertEquals("200", user.getFollowing());

        user.setBio("New bio");
        assertEquals("New bio", user.getBio());

        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    public void testDefaultConstructor() {
        // Teste o construtor padrão
        User defaultUser = new User();
        assertNull(defaultUser.getUserid());
        assertNull(defaultUser.getPassword());
        assertNull(defaultUser.getScreenName());
        assertNull(defaultUser.getProfileImage());
        assertNull(defaultUser.getFollowing());
        assertNull(defaultUser.getBio());
        assertNull(defaultUser.getRole());
    }
}
