package com.readlab.services;

import com.readlab.exceptions.InvalidUserException;
import com.readlab.models.*;

import java.util.HashMap;
import java.util.Scanner;

public class UserService {
    private HashMap<String, User> users;

    public UserService() {
        users = new HashMap<>();
        users.put("reader1", new Reader("reader1", "1234"));
        users.put("author1", new Author("author1", "5678"));
    }
    
    public User register(Scanner scanner) throws InvalidUserException {
        System.out.println("Daftar sebagai: [Author/Reader]");
        String type = scanner.nextLine().trim().toLowerCase();
         
        if (!type.equals("author") && !type.equals("reader")) {
            throw new InvalidUserException("Jenis pengguna tidak valid. Harap pilih 'Author' atau 'Reader'.");
        }
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        if (users.containsKey(username)) {
            throw new InvalidUserException("Username sudah terdaftar. Harap pilih username lain.");
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user;
        if (type.equals("author")) {
            user = new Author(username, password);
        } else {
            user = new Reader(username, password);
        }

        users.put(username, user);
        System.out.println("Pendaftaran berhasil. Anda sekarang dapat login.");
        return user;
    }

    public User login(Scanner scanner) throws InvalidUserException {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authenticate(username, password);
        if (user == null) {
            throw new InvalidUserException("Login gagal. Username atau password salah.");
        }
        return user;
    }

    private User authenticate(String username, String password) throws InvalidUserException {
        User user = users.get(username);
        if (user == null || !user.authenticate(password)) {
            throw new InvalidUserException("Login gagal. Username atau password salah.");
        }
        return user;
    }
}
