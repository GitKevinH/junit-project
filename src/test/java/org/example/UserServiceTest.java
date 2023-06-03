package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserService userService;
    @InjectMocks
    UserService userServiceTest;
    User user;

    @BeforeEach
    public void setUp() {
        userServiceTest = new UserService();
        user = new User("Rick", "mortyismine", "rick@morty.com");
        userServiceTest.registerUser(user);
    }

    @AfterEach
    public void tearDown() {
        userServiceTest = null;
        user = null;
    }

    //registerUser method testing

    @Test
    public void testRegisterUser_Positive() {
        User newUser = new User("james", "jimmyjames1", "jimmy@james.com");
        boolean result = userServiceTest.registerUser(newUser);
        assertTrue(result);
    }

    @Test
    public void testRegisterUser_Negative() {
        boolean result = userServiceTest.registerUser(user);
        assertFalse(result);
    }

    @Test
    public void testRegisterUser_EdgeCase() {
        User newUser = new User("", "", "");
        boolean result = userServiceTest.registerUser(newUser);
        assertTrue(result);
    }

    //loginUser method testing

    @Test
    public void testLoginUser_Positive() {
        User result = userServiceTest.loginUser(user.getUsername(), user.getPassword());
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testLoginUser_Negative() {
        User result = userServiceTest.loginUser("madeupperson", "madeuppassword");
        assertNull(result);
    }

    @Test
    public void testLoginUser_EdgeCase() {
        User result = userServiceTest.loginUser("", "");
        assertNull(result);
    }

    //updateUserProfile method testing

    @Test
    public void testUpdateUserProfile_Positive() {
        String newUsername = "rick1";
        String newPassword = "mortyismine1";
        String newEmail = "morty@rick.com";
        boolean result = userServiceTest.updateUserProfile(user, newUsername, newPassword, newEmail);
        assertTrue(result);
        assertEquals(newUsername, user.getUsername());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    public void testUpdateUserProfile_Negative() {
        User otherUser = new User("james", "jimmyjames1", "jimmy@james.com");
        userServiceTest.registerUser(otherUser);
        String newUsername = "james";
        String newPassword = "jimmyjames2023";
        String newEmail = "james@jimbo.com";
        boolean result = userServiceTest.updateUserProfile(user, newUsername, newPassword, newEmail);
        assertFalse(result);
        assertNotEquals(newUsername, user.getUsername());
        assertNotEquals(newPassword, user.getPassword());
        assertNotEquals(newEmail, user.getEmail());
    }

    @Test
    public void testUpdateUserProfile_EdgeCase() {
        String newUsername = "";
        String newPassword = "";
        String newEmail = "";
        boolean result = userServiceTest.updateUserProfile(user, newUsername, newPassword, newEmail);
        assertTrue(result);
        assertEquals(newUsername, user.getUsername());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newEmail, user.getEmail());
    }


}