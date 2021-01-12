package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.entity.UserSurvey;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
}
