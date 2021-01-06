package ru.shchekalev.opencodewebtask;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.shchekalev.opencodewebtask.models.Role;
import ru.shchekalev.opencodewebtask.models.User;
import ru.shchekalev.opencodewebtask.repositories.UserRepository;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode("100"));
        user.setUsername("user");
        user.setRole(Role.ADMIN);


    }
}
