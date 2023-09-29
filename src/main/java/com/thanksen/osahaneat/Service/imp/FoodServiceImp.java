package com.thanksen.osahaneat.Service.imp;

import com.thanksen.osahaneat.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FoodServiceImp {
    boolean createMenu(
            String title,
            String image,
            boolean is_freeship,
            String time_ship,
            double price,
            int cate_id
    );

}
