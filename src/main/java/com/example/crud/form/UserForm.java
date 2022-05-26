package com.example.crud.form;

import com.example.crud.model.Role;
import com.example.crud.model.State;
import com.example.crud.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserForm {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;
    private State state;

    public static UserForm from(User user) {
        return UserForm.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                //.password(user.getPassword())
                .role(user.getRole())
                .state(user.getState())
                .build();
    }
}
