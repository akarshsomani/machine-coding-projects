package com.example.splitwise.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PendingBalance implements Serializable {

    Long friendId;

    Double amount;

    String comment;

}
