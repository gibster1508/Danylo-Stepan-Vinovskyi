package com.epam.spring.hotel.repository;

import com.epam.spring.hotel.model.Order;
import com.epam.spring.hotel.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String oldLogin);

    @Query("select o from Order o where o.idOrder >= ?1")
    Page<Order> findOrderByIdUser(Long id, Pageable pageable);

}