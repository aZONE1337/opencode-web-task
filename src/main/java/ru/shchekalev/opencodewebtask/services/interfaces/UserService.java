package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
