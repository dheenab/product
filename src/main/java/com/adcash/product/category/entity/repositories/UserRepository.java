package com.adcash.product.category.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adcash.product.category.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param username the username to look for
     * @return the User that was found (if any)
     */
    Optional<User> findByUsername(String username);

}
