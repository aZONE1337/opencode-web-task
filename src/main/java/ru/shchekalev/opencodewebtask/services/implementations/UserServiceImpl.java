package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.repository.UserRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
//                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found")); <-- breaks the entire auth
    }

    @Override
    public List<User> findAllByCompletedSurvey(Survey survey) {
        return userRepository.findAllByCompletedSurveys(survey);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + id + "' not found"));
    }

    @Override
    public void updateAnswers(Long id, Set<Answer> answers) {
        User user = userRepository.getOne(id);

        answers.stream()
                .findFirst()
                .ifPresent(answer -> user.getAnswers().removeAll(answer.getQuestion().getAnswers()));
        user.getAnswers().addAll(answers);

        userRepository.save(user);
    }

    @Override
    public void addCompletedSurvey(Long id, Survey survey) {
        User user = userRepository.getOne(id);

        user.getCompletedSurveys().add(survey);

        userRepository.save(user);
    }

    @Override
    public void removeCompletedSurvey(Long id, Survey survey) {
        User user = userRepository.getOne(id);
        Set<Answer> possibleAnswers = survey.getQuestions().stream()
                .flatMap(question -> question.getAnswers().stream())
                .collect(Collectors.toSet());

        user.getAnswers().removeAll(possibleAnswers);
        user.getCompletedSurveys().remove(survey);

        userRepository.save(user);
    }
}
