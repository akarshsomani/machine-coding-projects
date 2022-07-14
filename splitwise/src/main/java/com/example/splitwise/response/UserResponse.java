package com.example.splitwise.response;

import com.example.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    Boolean Status;

    User user;

    String error;

    String errorMessage;

}
