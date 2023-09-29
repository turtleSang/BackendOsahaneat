package com.thanksen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "is_freeship")
    private boolean isFreeship;

    @Column(name = "address")
    private String address;

    @Column(name = "open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurantEntity> listRatingRestaurantEntities;

    @OneToMany(mappedBy = "restaurant")
    private Set<OrdersEntity> listOrdersEntities;

    public Set<MenuRestaurantEntity> getListMenuRestaurantEntities() {
        return listMenuRestaurantEntities;
    }

    public void setListMenuRestaurantEntities(Set<MenuRestaurantEntity> listMenuRestaurantEntities) {
        this.listMenuRestaurantEntities = listMenuRestaurantEntities;
    }

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurantEntity> listMenuRestaurantEntities;

    public Set<RatingRestaurantEntity> getListRatingRestaurantEntities() {
        return listRatingRestaurantEntities;
    }

    public void setListRatingRestaurantEntities(Set<RatingRestaurantEntity> listRatingRestaurantEntities) {
        this.listRatingRestaurantEntities = listRatingRestaurantEntities;
    }

    public Set<OrdersEntity> getListOrdersEntities() {
        return listOrdersEntities;
    }

    public void setListOrdersEntities(Set<OrdersEntity> listOrdersEntities) {
        this.listOrdersEntities = listOrdersEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
