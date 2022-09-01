package com.epam.spring.hotel.repository;

import com.epam.spring.hotel.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.idOrder >= ?1")
    List<Order> findOrderByIdUser(Long id, Pageable pageable);
}
