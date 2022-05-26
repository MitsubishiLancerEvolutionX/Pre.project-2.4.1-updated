package com.example.crud.service;

import com.example.crud.form.UserForm;
import com.example.crud.model.Role;
import com.example.crud.model.State;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser (User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void signUp(UserForm userForm) {

        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLogin())
                .password(hashPassword)
                //.password(userForm.getPassword())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        userRepository.save(user);
    }

    public void create(UserForm userForm) {

        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLogin())
                .password(hashPassword)
                //.password(userForm.getPassword())
                .role(userForm.getRole())
                .state(userForm.getState())
                .build();

        userRepository.save(user);
    }

    public void update(User user, UserForm userForm) {

        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userCandidate = userRepository.findByLogin(login);
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        } else throw new IllegalArgumentException("User not found");
    }
}
