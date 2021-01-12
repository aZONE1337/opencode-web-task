package ru.shchekalev.opencodewebtask;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.shchekalev.opencodewebtask.model.assistant.Role;
import ru.shchekalev.opencodewebtask.model.entity.User;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode("100"));
        user.setUsername("user");
        user.setRole(Role.ADMIN);


    }
}
