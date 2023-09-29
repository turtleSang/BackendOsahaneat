package com.thanksen.osahaneat.entity;

import com.thanksen.osahaneat.entity.keys.KeyMenuRestaurantMenu;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "menu_restaurant")
public class MenuRestaurantEntity {
    @EmbeddedId
    private KeyMenuRestaurantMenu key;

    @ManyToOne
    @JoinColumn(name = "cate_id", insertable = false, updatable = false)
    private CategoriesEntity categories;

    @ManyToOne
    @JoinColumn(name = "res_id", insertable = false, updatable = false)
    private RestaurantEntity restaurant;

    @Column(name = "created_At")
    private Date createdAt;

    public KeyMenuRestaurantMenu getKey() {
        return key;
    }

    public void setKey(KeyMenuRestaurantMenu key) {
        this.key = key;
    }

    public CategoriesEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoriesEntity categories) {
        this.categories = categories;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
