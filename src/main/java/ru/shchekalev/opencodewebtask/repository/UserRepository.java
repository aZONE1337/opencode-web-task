package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByCompletedSurveys(Survey survey);
}
