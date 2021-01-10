package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.models.UserSurvey;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
}
