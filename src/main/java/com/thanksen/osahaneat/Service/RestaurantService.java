package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.CategoryRepository;
import com.thanksen.osahaneat.Reponsitory.RestaurantRepository;
import com.thanksen.osahaneat.Service.MenuService;
import com.thanksen.osahaneat.Service.imp.RestaurantServiceImp;
import com.thanksen.osahaneat.dto.CategoryDto;
import com.thanksen.osahaneat.dto.MenuDto;
import com.thanksen.osahaneat.dto.RestaurantDto;
import com.thanksen.osahaneat.entity.FoodEntity;
import com.thanksen.osahaneat.entity.MenuRestaurantEntity;
import com.thanksen.osahaneat.entity.RatingRestaurantEntity;
import com.thanksen.osahaneat.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {

    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService (RestaurantRepository restaurantRepository, CategoryRepository categoryRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean insert(String title, String subtitle, String description, boolean is_freeship, String image, String address, Date isDate) {

        try {
            RestaurantEntity restaurantEntity = new RestaurantEntity();

            restaurantEntity.setTitle(title);
            restaurantEntity.setSubtitle(subtitle);
            restaurantEntity.setDescription(description);
            restaurantEntity.setFreeship(is_freeship);
            restaurantEntity.setImageUrl(image);
            restaurantEntity.setAddress(address);
            restaurantEntity.setOpenDate(isDate);

            restaurantRepository.save(restaurantEntity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<RestaurantDto> getAllRestaurant() {

        Page<RestaurantEntity> list =  restaurantRepository.findAll(PageRequest.of(0, 6));
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();

        for (RestaurantEntity item: list) {
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(item.getId());
            restaurantDto.setTitle(item.getTitle());
            restaurantDto.setSubtitle(item.getSubtitle());
            restaurantDto.setImage(item.getImageUrl());
            restaurantDto.setFreeship(item.isFreeship());
            restaurantDto.setRating(calculateRestaurantRating(item.getListRatingRestaurantEntities()));
            restaurantDtoList.add(restaurantDto);
        }



        return restaurantDtoList;
    }

    @Override
    public RestaurantDto getDetailRestaurant(int id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        RestaurantDto restaurantDto = new RestaurantDto();
        if (restaurantEntity.isPresent()){
            restaurantDto.setId(restaurantEntity.get().getId());
            restaurantDto.setTitle(restaurantEntity.get().getTitle());
            restaurantDto.setSubtitle(restaurantEntity.get().getSubtitle());
            restaurantDto.setFreeship(restaurantEntity.get().isFreeship());
            restaurantDto.setRating(calculateRestaurantRating(restaurantEntity.get().getListRatingRestaurantEntities()));
            restaurantDto.setImage(restaurantEntity.get().getImageUrl());
            restaurantDto.setOpenDate(restaurantEntity.get().getOpenDate());

            //Get list Categories
            Set<MenuRestaurantEntity> list = restaurantEntity.get().getListMenuRestaurantEntities();
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for (MenuRestaurantEntity item : list){
                CategoryDto categoryDto = new CategoryDto();
                List<MenuDto> listMenuDto = new ArrayList<>();
                Set<FoodEntity> listFood = item.getCategories().getListFood();

                //Add menu
                for (FoodEntity food: listFood
                     ) {
                    MenuDto menuDto = new MenuDto();
                    menuDto.setImage(food.getImageUrl());
                    menuDto.setIsFreeship(food.isIs_freeship());
                    menuDto.setTitle(food.getTitle());
                    listMenuDto.add(menuDto);
                }

                categoryDto.setName(item.getCategories().getNameCate());
                categoryDto.setListMenu(listMenuDto);
                categoryDtoList.add(categoryDto);
            }

            restaurantDto.setCategoryDtoList(categoryDtoList);
            return restaurantDto;
        }

        return null;
    }

    public double calculateRestaurantRating( Set<RatingRestaurantEntity> list){
            double total = 0;
        for (RatingRestaurantEntity rating: list) {
            total += rating.getRatePoint();
        }
        return (total/list.size());
    }
}
