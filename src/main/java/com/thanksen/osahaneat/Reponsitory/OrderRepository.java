package com.thanksen.osahaneat.Reponsitory;

import com.thanksen.osahaneat.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity, Integer> {
}
