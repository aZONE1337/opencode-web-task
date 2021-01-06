package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shchekalev.opencodewebtask.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Override
    <S extends User> S save(S s);
}
