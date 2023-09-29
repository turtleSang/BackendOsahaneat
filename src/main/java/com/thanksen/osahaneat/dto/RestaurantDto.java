package com.thanksen.osahaneat.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDto {
    private int id;
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeship;
    private Date openDate;

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    private List<CategoryDto> categoryDtoList;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public RestaurantDto() {
    }
}
