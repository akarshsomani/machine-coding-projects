package com.example.splitwise.service;

import com.example.splitwise.enums.SharingType;
import com.example.splitwise.model.Friendship;
import com.example.splitwise.model.Transactions;
import com.example.splitwise.object.PendingBalance;
import com.example.splitwise.repository.FriendshipRepository;
import com.example.splitwise.repository.TransactionsRepository;
import com.example.splitwise.repository.UserRepository;
import com.example.splitwise.request.FriendshipRequest;
import com.example.splitwise.request.PendingBalanceRequest;
import com.example.splitwise.request.SharingRequest;
import com.example.splitwise.response.BaseResponse;
import com.example.splitwise.response.PendingBalanceResponse;
import com.example.splitwise.response.TransctionHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.splitwise.enums.SharingType.PERCENTAGE_SHARING;

@Service
public class SplitService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    EqualSharingService equalSharingService;

    @Autowired
    ExactSharingService exactSharingService;

    @Autowired
    PercentageSharingService percentageSharingService;

    public BaseResponse ShareSplitUp(SharingRequest sharingRequest){

        BaseResponse baseResponse = new BaseResponse();


        if(sharingRequest.getSharingType().equals(PERCENTAGE_SHARING)){
            percentageSharingService.shareAmount(sharingRequest, baseResponse);
        }
        else if(sharingRequest.getSharingType().equals(SharingType.EXACT_AMOUNT)){
            exactSharingService.shareAmount(sharingRequest, baseResponse);
        }
        else if(sharingRequest.getSharingType().equals(SharingType.EQUAL_SHARING)){
            equalSharingService.shareAmount(sharingRequest, baseResponse);
        }

        return baseResponse;
    }


    public TransctionHistoryResponse getTransactionHistory(FriendshipRequest friendshipRequest) {

        TransctionHistoryResponse transctionHistoryResponse = new TransctionHistoryResponse();

        try{
            Friendship friendship = friendshipRepository.findFriendship(friendshipRequest.getUserId(), friendshipRequest.getFriendId());
            if(friendship.getFriendshipId() == null){
                throw new Exception("Friendship not found");
            }

            List<Transactions> transactionsList = transactionsRepository.findByFriendshipId(friendship.getFriendshipId());

            transctionHistoryResponse.setTransactionsList(transactionsList);
            transctionHistoryResponse.setAmount(Math.abs(friendship.getFriend1ToFriend2SettlementAmount()));

            if(friendship.getFriendId1() == friendshipRequest.getUserId() ) {
                String friend2 =  userRepository.findById(friendship.getFriendId2()).get().getName();
                transctionHistoryResponse.setComment(friendship.getFriend1ToFriend2SettlementAmount() > 0 ?
                        friend2 + " owes Me" : "I owes "+friend2);
               }
            else{
                String friend1 =  userRepository.findById(friendship.getFriendId1()).get().getName();
                transctionHistoryResponse.setComment(friendship.getFriend1ToFriend2SettlementAmount() > 0 ?
                        "I owes " + friend1 : friend1 + " owes Me");

            }

            } catch (Exception e) {
            transctionHistoryResponse.setError("Error getting Transaction History");
            transctionHistoryResponse.setError(e.getMessage());
        }
        return transctionHistoryResponse;
    }

    public PendingBalanceResponse getPendingBalance(PendingBalanceRequest pendingBalanceRequest) {
        PendingBalanceResponse pendingBalanceResponse = new PendingBalanceResponse();

        try{
            List<Friendship> friendshipList =  friendshipRepository.findAllFriendship(pendingBalanceRequest.getUserId());
            List<PendingBalance> pendingBalances = new ArrayList<>();

            for(Friendship friendship: friendshipList){

                PendingBalance pendingBalance = new PendingBalance();

                if(friendship.getFriendId1() == friendship.getFriendId2()){
                    continue;
                }

                if(friendship.getFriendId1() == pendingBalanceRequest.getUserId() ) {
                    String friend2 =  userRepository.findById(friendship.getFriendId2()).get().getName();
                    pendingBalance.setComment(friendship.getFriend1ToFriend2SettlementAmount() > 0 ?
                            friend2 + " owes Me" : "I owes "+friend2);
                    pendingBalance.setFriendId(friendship.getFriendId2());
                    pendingBalance.setAmount(Math.abs(friendship.getFriend1ToFriend2SettlementAmount()));
                }
                else{
                    String friend1 =  userRepository.findById(friendship.getFriendId1()).get().getName();
                    pendingBalance.setComment(friendship.getFriend1ToFriend2SettlementAmount() > 0 ?
                            "I owes " + friend1 : friend1 + " owes Me");
                    pendingBalance.setFriendId(friendship.getFriendId1());
                    pendingBalance.setAmount(Math.abs(friendship.getFriend1ToFriend2SettlementAmount()));

                }

                pendingBalances.add(pendingBalance);
                }
            pendingBalanceResponse.setPendingBalances(pendingBalances);

        } catch (Exception e){
            pendingBalanceResponse.setError("Error while fetching pending Balance");
            pendingBalanceResponse.setErrorMessage(e.getMessage());
        }
        return pendingBalanceResponse;
    }
}
