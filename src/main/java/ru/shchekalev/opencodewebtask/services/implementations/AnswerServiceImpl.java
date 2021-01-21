package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.repository.AnswerRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.AnswerService;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> findAllByQuestionId(Long id) {
        return answerRepository.findAllByQuestionId(id);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Answer with id " + id + " doesn't exist"));
    }

    @Override
    public Answer update(Long id, Answer newAnswer) {
        Answer answer = answerRepository.getOne(id);

        answer.setText(newAnswer.getText());

        return answerRepository.save(answer);
    }
}
