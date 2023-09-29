package com.thanksen.osahaneat.Service.imp;

import com.thanksen.osahaneat.dto.RestaurantDto;

import java.util.Date;
import java.util.List;

public interface RestaurantServiceImp {
    boolean insert(String title, String subtitle, String description, boolean is_freeship, String image, String address, Date isDate);
    List<RestaurantDto> getAllRestaurant();
    RestaurantDto getDetailRestaurant(int id);
}
