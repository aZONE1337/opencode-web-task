package ru.shchekalev.opencodewebtask.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.shchekalev.opencodewebtask.models.User;

public interface UserService {

    void create(User user);

    User findByUsername(String username);
}
