package com.onlinebookstore.repository;

import com.onlinebookstore.model.OrderDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsDTO,Long> {
}
