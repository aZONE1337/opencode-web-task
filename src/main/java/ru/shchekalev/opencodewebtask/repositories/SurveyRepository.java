package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.models.Survey;
import ru.shchekalev.opencodewebtask.models.User;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
//
//    @Query(value = "select Survey from Survey join UserSurvey where Survey.status = ?1 " +
//            "and UserSurvey.user.id = ?2 and UserSurvey.answerStatus = ?3")
//    Optional<Survey> findCorrectSurvey(Availability status, Long id, AnswerStatus answerStatus);

    List<Survey> findAllByAuthor(User user);
}
