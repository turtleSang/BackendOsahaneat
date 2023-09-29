package com.thanksen.osahaneat.Reponsitory;

import com.thanksen.osahaneat.entity.OrderDetailEntity;
import com.thanksen.osahaneat.entity.keys.KeyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, KeyOrderDetail> {
}
