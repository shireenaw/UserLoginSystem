package com.premiergaming.repository;

import com.premiergaming.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByUserId(int userId);
    @Query("Select u FROM Users u WHERE u.email=?1")
    public Optional<Users> findUserByEmail(String email);
    @Query("Select u FROM Users u WHERE u.email=?1")
    public Users getUserByEmail(String email);
}


