package com.example.splitwise.response;

import com.example.splitwise.object.PendingBalance;
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
public class PendingBalanceResponse extends BaseResponse implements Serializable {

    List<PendingBalance> pendingBalances;
}
