package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.CategoryRepository;
import com.thanksen.osahaneat.Service.imp.CategoryServiceImp;
import com.thanksen.osahaneat.dto.CategoryDto;
import com.thanksen.osahaneat.dto.MenuDto;
import com.thanksen.osahaneat.entity.CategoriesEntity;
import com.thanksen.osahaneat.entity.FoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService implements CategoryServiceImp {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDto> getAllCategory() {
        Page<CategoriesEntity> listCategory = categoryRepository.findAll(PageRequest.of(0, 3));

        List<CategoryDto> list = new ArrayList<>();

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




        return list;
    }
}
