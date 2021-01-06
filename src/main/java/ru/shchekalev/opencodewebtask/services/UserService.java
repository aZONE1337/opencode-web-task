package ru.shchekalev.opencodewebtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.models.Role;
import ru.shchekalev.opencodewebtask.models.User;
import ru.shchekalev.opencodewebtask.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Role role) {
        return role.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
    }


}
