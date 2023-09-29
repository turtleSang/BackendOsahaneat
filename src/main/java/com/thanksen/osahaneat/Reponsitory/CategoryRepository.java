package com.thanksen.osahaneat.Reponsitory;

import com.thanksen.osahaneat.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<CategoriesEntity, Integer> {

}
