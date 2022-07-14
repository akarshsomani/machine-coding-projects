package com.example.splitwise.repository;

import com.example.splitwise.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "SELECT * " +
            "FROM   friendship " +
            "WHERE  ( friend_id1 = ?1" +
            "         AND friend_id2 = ?2 )" +
            "        OR ( friend_id1 = ?2" +
            "             AND friend_id2 = ?1 ) " +
            "LIMIT  1 ",
            nativeQuery = true)
    Friendship findFriendship(Long friend1, Long friend2);

    @Query(value = "SELECT * " +
            "FROM   friendship " +
            "WHERE  friend_id1 = ?1" +
            "         OR friend_id2 = ?1 ",
            nativeQuery = true)
    List<Friendship> findAllFriendship(Long friend1);
}
