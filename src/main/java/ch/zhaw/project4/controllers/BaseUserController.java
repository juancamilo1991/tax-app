package ch.zhaw.project4.controllers;

import ch.zhaw.project4.services.BaseUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

public interface BaseUserController<T, D> {

    public ResponseEntity<?> getAllUsers();

    public ResponseEntity<?> getUserById(long id);

    public ResponseEntity<String> addUser(D d);
}

