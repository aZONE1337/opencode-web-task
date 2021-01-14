package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findAllByAvailableIsTrue();

    List<Survey> findAllByUsers(User user);
}
