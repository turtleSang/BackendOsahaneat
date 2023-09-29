package com.thanksen.osahaneat.Reponsitory;

import com.thanksen.osahaneat.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {

}
