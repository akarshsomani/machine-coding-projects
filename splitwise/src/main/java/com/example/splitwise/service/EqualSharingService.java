package com.example.splitwise.service;

import com.example.splitwise.enums.SharingType;
import com.example.splitwise.model.Friendship;
import com.example.splitwise.model.Transactions;
import com.example.splitwise.object.UserShare;
import com.example.splitwise.repository.FriendshipRepository;
import com.example.splitwise.repository.TransactionsRepository;
import com.example.splitwise.request.SharingRequest;
import com.example.splitwise.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EqualSharingService implements SharingService{

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;


    @Override
    public void shareAmount(SharingRequest sharingRequest, BaseResponse baseResponse) {
        try{
            List<UserShare> userShares = sharingRequest.getUserShares();

            for(UserShare userShare : sharingRequest.getUserShares()){

                Friendship friendship = friendshipRepository.findFriendship(sharingRequest.getPaidByUserId(), userShare.getUserId());
                if(friendship.getFriendshipId() == null){
                    baseResponse.setError("Friendship does not exist with userId : " + userShare.getUserId());
                    baseResponse.setError("Please add the user as friend first");

                    return;
                }
                else{
                    Transactions transaction = new Transactions();
                    transaction.setSharingType(SharingType.EQUAL_SHARING.name());
                    transaction.setFriendshipId(friendship.getFriendshipId());
                    transaction.setSplitAmongCount(userShares.size());
                    transaction.setTotalAmount(sharingRequest.getTotalAmount());
                    transaction.setSplitAmount(sharingRequest.getTotalAmount() / userShares.size());
                    if(sharingRequest.getPaidByUserId() == friendship.getFriendId1()){
                        friendship.setFriend1ToFriend2SettlementAmount(friendship.getFriend1ToFriend2SettlementAmount() + sharingRequest.getTotalAmount() / userShares.size());
                        transaction.setPaidBy(friendship.getFriendId1());
                        transaction.setPaidTo(friendship.getFriendId2());
                    }
                    else{
                        friendship.setFriend1ToFriend2SettlementAmount(friendship.getFriend1ToFriend2SettlementAmount() - userShare.getShare() / userShares.size());
                        transaction.setPaidBy(friendship.getFriendId2());
                        transaction.setPaidTo(friendship.getFriendId1());
                    }

                    saveToDataBase(friendship, transaction);
                }
            }


            baseResponse.setSuccess(true);
        }
        catch (Exception e){
            baseResponse.setError("Error in split by Equal Amount");
            baseResponse.setErrorMessage(e.getMessage());
        }
    }

    @Transactional
    private void saveToDataBase(Friendship friendship, Transactions transaction) {
        friendshipRepository.save(friendship);
        transactionsRepository.save(transaction);
    }
}
