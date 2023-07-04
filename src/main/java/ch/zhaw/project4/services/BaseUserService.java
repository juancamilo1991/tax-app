package ch.zhaw.project4.services;

import ch.zhaw.project4.controllers.BaseUserController;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


public interface BaseUserService<T> {

    public List<T> getAllUsers();

    public T getUserById(Long id);

    public T addUser(T t);

    public T saveUser(T t);
}
