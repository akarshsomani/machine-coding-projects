package com.example.splitwise.service;

import com.example.splitwise.model.Friendship;
import com.example.splitwise.model.User;
import com.example.splitwise.repository.FriendshipRepository;
import com.example.splitwise.repository.UserRepository;
import com.example.splitwise.request.UserRequest;
import com.example.splitwise.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public UserResponse createUser(UserRequest userRequest) {

        UserResponse userResponse = new UserResponse();
        try {
            User user = new User();
            user.setEnabled(true);
            user.setMobileNo(userRequest.getMobileNo());
            user.setName(userRequest.getUserName());
            user = userRepository.save(user);

            Friendship friendship = new Friendship();
            friendship.setFriendId1(user.getUserId());
            friendship.setFriendId2(user.getUserId());
            friendship.setFriend1ToFriend2SettlementAmount(0d);
            friendship.setLastSettlementDate(new Date());
            friendshipRepository.save(friendship);

            userResponse.setUser(user);
            userResponse.setStatus(true);
        }
        catch (Exception e){
            userResponse.setError("Error Creating User");
            userResponse.setErrorMessage(e.getMessage());
        }

        return userResponse;

    }

    public Boolean doesUserExist(Long userId) {

        try {
            User user = userRepository.getById(userId);
            if(user.getName() != null){
                return true;
            }
        }
        catch(Exception e){
            return false;
        }
        return false;
    }

}
