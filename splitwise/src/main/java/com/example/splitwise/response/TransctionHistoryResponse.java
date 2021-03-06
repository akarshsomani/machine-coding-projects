package com.example.splitwise.response;

import com.example.splitwise.model.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransctionHistoryResponse extends BaseResponse implements Serializable {

    List<Transactions> transactionsList;

    Double amount;

    String comment;
}
