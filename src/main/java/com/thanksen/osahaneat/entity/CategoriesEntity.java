package com.thanksen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "categories")
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_cate")
    private String nameCate;

    @Column(name = "created_At")
    private Date createdAt;

    @OneToMany(mappedBy = "categories")
    private Set<FoodEntity> listFood;

    @OneToMany(mappedBy = "categories")
    private Set<MenuRestaurantEntity> listMenuRestaurantEntities;

    public int getId() {
        return id;
    }

    public Set<FoodEntity> getListFood() {
        return listFood;
    }

    public void setListFood(Set<FoodEntity> listFood) {
        this.listFood = listFood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
