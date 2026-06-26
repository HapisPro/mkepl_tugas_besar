package com.readlab.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void authenticate_shouldReturnTrueForCorrectPassword() {
        User user = new Reader("user1", "secret");
        assertTrue(user.authenticate("secret"));
    }

    @Test
    void authenticate_shouldReturnFalseForWrongPassword() {
        User user = new Reader("user1", "secret");
        assertFalse(user.authenticate("wrongpass"));
    }

    @Test
    void authenticate_shouldBeCaseSensitive() {
        User user = new Reader("user1", "Secret123");
        assertFalse(user.authenticate("secret123"));
    }

    @Test
    void authenticate_shouldHandleEmptyPassword() {
        User user = new Reader("user1", "");
        assertTrue(user.authenticate(""));
        assertFalse(user.authenticate("notempty"));
    }

    @Test
    void getUsername_shouldReturnCorrectName() {
        User user = new Reader("myUser", "pass");
        assertEquals("myUser", user.getUsername());
    }

    @Test
    void equals_shouldReturnTrueForSameUsername() {
        User user1 = new Reader("sameUser", "pass1");
        User user2 = new Reader("sameUser", "pass2");
        assertEquals(user1, user2);
    }

    @Test
    void equals_shouldReturnFalseForDifferentUsername() {
        User user1 = new Reader("userA", "pass");
        User user2 = new Reader("userB", "pass");
        assertNotEquals(user1, user2);
    }

    @Test
    void equals_shouldReturnTrueForSameObject() {
        User user = new Reader("user", "pass");
        assertEquals(user, user);
    }

    @Test
    void equals_shouldReturnFalseForNull() {
        User user = new Reader("user", "pass");
        assertNotEquals(null, user);
    }

    @Test
    void hashCode_shouldBeConsistentWithEquals() {
        User user1 = new Reader("hashUser", "pass1");
        User user2 = new Reader("hashUser", "pass2");
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
