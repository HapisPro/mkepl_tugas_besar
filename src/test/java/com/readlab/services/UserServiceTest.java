package com.readlab.services;

import com.readlab.models.*;
import com.readlab.exceptions.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void register_shouldCreateReader() throws InvalidUserException {
        String input = "reader\nnewreader\npass123\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.register(scanner);

        assertNotNull(user);
        assertInstanceOf(Reader.class, user);
        assertEquals("newreader", user.getUsername());
    }

    @Test
    void register_shouldCreateAuthor() throws InvalidUserException {
        String input = "author\nnewauthor\npass456\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.register(scanner);

        assertNotNull(user);
        assertInstanceOf(Author.class, user);
        assertEquals("newauthor", user.getUsername());
    }

    @Test
    void register_shouldThrowExceptionForInvalidType() {
        String input = "admin\nsomeuser\npass\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InvalidUserException.class, () -> userService.register(scanner));
    }

    @Test
    void register_shouldThrowExceptionForDuplicateUsername() {
        String input = "reader\nreader1\ndup\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InvalidUserException.class, () -> userService.register(scanner));
    }

    @Test
    void register_shouldHandleCaseInsensitiveType() throws InvalidUserException {
        String input = "Author\ncaseuser\npass\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.register(scanner);

        assertInstanceOf(Author.class, user);
    }

    @Test
    void login_shouldSucceedWithValidCredentials() throws InvalidUserException {
        String input = "reader1\n1234\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.login(scanner);

        assertNotNull(user);
        assertEquals("reader1", user.getUsername());
        assertInstanceOf(Reader.class, user);
    }

    @Test
    void login_shouldSucceedForAuthor() throws InvalidUserException {
        String input = "author1\n5678\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.login(scanner);

        assertNotNull(user);
        assertEquals("author1", user.getUsername());
        assertInstanceOf(Author.class, user);
    }

    @Test
    void login_shouldThrowExceptionForWrongPassword() {
        String input = "reader1\nwrongpass\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InvalidUserException.class, () -> userService.login(scanner));
    }

    @Test
    void login_shouldThrowExceptionForNonExistentUser() {
        String input = "nonexistent\npass\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(InvalidUserException.class, () -> userService.login(scanner));
    }

    @Test
    void registerAndLogin_shouldWorkEndToEnd() throws InvalidUserException {
        String regInput = "reader\ne2euser\nmypass\n";
        Scanner regScanner = new Scanner(new ByteArrayInputStream(regInput.getBytes()));
        userService.register(regScanner);

        String loginInput = "e2euser\nmypass\n";
        Scanner loginScanner = new Scanner(new ByteArrayInputStream(loginInput.getBytes()));
        User user = userService.login(loginScanner);

        assertNotNull(user);
        assertEquals("e2euser", user.getUsername());
    }

    @Test
    void register_shouldTrimWhitespaceFromType() throws InvalidUserException {
        String input = "  reader  \nwhiteuser\npass\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        User user = userService.register(scanner);

        assertInstanceOf(Reader.class, user);
    }
}
