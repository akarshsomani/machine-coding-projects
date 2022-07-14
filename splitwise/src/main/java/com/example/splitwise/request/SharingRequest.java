package com.example.splitwise.request;


import com.example.splitwise.enums.SharingType;
import com.example.splitwise.object.UserShare;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SharingRequest implements Serializable {

    Long paidByUserId;

    SharingType sharingType;

    List<UserShare> userShares;

    Double totalAmount;


}
