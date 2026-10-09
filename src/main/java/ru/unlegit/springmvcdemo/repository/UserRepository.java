package ru.unlegit.springmvcdemo.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.unlegit.springmvcdemo.model.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, int id);
}