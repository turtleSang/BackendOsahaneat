package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.FoodRepository;
import com.thanksen.osahaneat.Service.imp.FoodServiceImp;
import com.thanksen.osahaneat.dto.RestaurantDto;
import com.thanksen.osahaneat.entity.CategoriesEntity;
import com.thanksen.osahaneat.entity.FoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class MenuService implements FoodServiceImp {
    private FoodRepository foodRepository;

    @Autowired
    public MenuService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }


    @Override
    public boolean createMenu(String title, String image, boolean is_freeship, String time_ship, double price, int cate_id) {
        try {
            FoodEntity foodEntity = new FoodEntity();
            CategoriesEntity categoriesEntity = new CategoriesEntity();
            categoriesEntity.setId(cate_id);

            foodEntity.setTitle(title);
            foodEntity.setImageUrl(image);
            foodEntity.setIs_freeship(is_freeship);
            foodEntity.setTimeShip(time_ship);
            foodEntity.setPrice(price);
            foodEntity.setCategories(categoriesEntity);
            foodRepository.save(foodEntity);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
