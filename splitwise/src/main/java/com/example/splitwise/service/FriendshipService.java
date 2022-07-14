package com.example.splitwise.service;

import com.example.splitwise.model.Friendship;
import com.example.splitwise.model.User;
import com.example.splitwise.repository.FriendshipRepository;
import com.example.splitwise.repository.UserRepository;
import com.example.splitwise.request.FriendshipRequest;
import com.example.splitwise.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class FriendshipService {


    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    public BaseResponse createFriend(FriendshipRequest friendshipRequest) {

        BaseResponse baseResponse = new BaseResponse();

        try{

            User user = userRepository.getById(friendshipRequest.getFriendId());

            if(user.getUserId() == null) {
                baseResponse.setSuccess(false);
                throw new Exception("Friend Does not Exist");
            }
                Friendship friendship = new Friendship();
                friendship.setFriendId1(friendshipRequest.getUserId());
                friendship.setFriendId2(friendshipRequest.getFriendId());
                friendship.setFriend1ToFriend2SettlementAmount(0d);
                friendship.setLastSettlementDate(new Date());
                friendshipRepository.save(friendship);
                baseResponse.setSuccess(true);

            } catch (Exception e){
            baseResponse.setError("Error making friend");
            baseResponse.setErrorMessage(e.getMessage());
            }
        return baseResponse;
    }
}
