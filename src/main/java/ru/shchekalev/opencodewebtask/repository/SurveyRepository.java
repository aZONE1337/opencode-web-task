package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.assistant.Availability;
import ru.shchekalev.opencodewebtask.model.entity.Survey;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findAllByStatus(Availability status);
}
