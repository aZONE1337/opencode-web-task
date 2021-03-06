package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.repository.QuestionRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> findAllBySurveyId(Long id) {
        return questionRepository.findAllBySurveyId(id);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Question with id " + id + " doesn't exist"));
    }

    @Override
    public Question update(Long id, Question newQuestion) {
        Question question = questionRepository.getOne(id);

        question.setText(newQuestion.getText());
        question.setSingleChoice(newQuestion.isSingleChoice());

        return questionRepository.save(question);
    }
}
