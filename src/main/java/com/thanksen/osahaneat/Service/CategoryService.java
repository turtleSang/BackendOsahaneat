package com.thanksen.osahaneat.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thanksen.osahaneat.Reponsitory.CategoryRepository;
import com.thanksen.osahaneat.Service.imp.CategoryServiceImp;
import com.thanksen.osahaneat.dto.CategoryDto;
import com.thanksen.osahaneat.dto.MenuDto;
import com.thanksen.osahaneat.entity.CategoriesEntity;
import com.thanksen.osahaneat.entity.FoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService implements CategoryServiceImp {
    private CategoryRepository categoryRepository;
    private RedisTemplate redisTemplate;
    private Gson gson = new Gson();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, RedisTemplate redisTemplate) {
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;
    }

//    @Cacheable("categoryHome")
    @Override
    public List<CategoryDto> getAllCategory() {

        String dataRedis = (String) redisTemplate.opsForValue().get("category");
        List<CategoryDto> list = new ArrayList<>();
        if (dataRedis == null){
            System.out.println("chua co data");
            Page<CategoriesEntity> listCategory = categoryRepository.findAll(PageRequest.of(0, 3));
            for (CategoriesEntity item: listCategory) {
                CategoryDto categoryDto = new CategoryDto();

                categoryDto.setName(item.getNameCate());

                List<MenuDto> listMenuDto = new ArrayList<>();
                for (FoodEntity food: item.getListFood()
                ) {

                    MenuDto menuDto = new MenuDto();
                    menuDto.setTitle(food.getTitle());
                    menuDto.setImage(food.getImageUrl());
                    menuDto.setIsFreeship(food.isIs_freeship());
                    listMenuDto.add(menuDto);
                }

                categoryDto.setListMenu(listMenuDto);
                list.add(categoryDto);
            }
            String dataJson = gson.toJson(list);

            redisTemplate.opsForValue().set("category", dataJson);
        }else {
            System.out.println("co data");
            Type listType = new TypeToken<List<CategoryDto>>(){}.getType();

            list = gson.fromJson(dataRedis, listType);
        }







        return list;
    }
}
