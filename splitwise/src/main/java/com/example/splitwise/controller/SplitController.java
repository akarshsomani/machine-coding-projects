package com.example.splitwise.controller;

import com.example.splitwise.request.FriendshipRequest;
import com.example.splitwise.request.PendingBalanceRequest;
import com.example.splitwise.request.SharingRequest;
import com.example.splitwise.response.BaseResponse;
import com.example.splitwise.response.PendingBalanceResponse;
import com.example.splitwise.response.TransctionHistoryResponse;
import com.example.splitwise.service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/splitwise")
public class SplitController {

    @Autowired
    private SplitService splitService;

    @PostMapping("/split")
    public BaseResponse splitup(@RequestBody SharingRequest sharingRequest) {
        return splitService.ShareSplitUp(sharingRequest);
    }

    @PostMapping("/transaction-history")
    public TransctionHistoryResponse getTransactionHistory(@RequestBody FriendshipRequest friendshipRequest) {
        return splitService.getTransactionHistory(friendshipRequest);
    }

    @PostMapping("/pending-balance")
    public PendingBalanceResponse getPendingBalance(@RequestBody PendingBalanceRequest pendingBalanceRequest) {
        return splitService.getPendingBalance(pendingBalanceRequest);
    }


}
