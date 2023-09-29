package com.thanksen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "timeship")
    private String timeShip;

    @Column(name = "price")
    private double price;

    @Column(name = "is_freeship")
    private boolean is_freeship;



    @ManyToOne
    @JoinColumn(name = "cate_id")
    private CategoriesEntity categories;

    @OneToMany(mappedBy = "foods")
    private Set<OrderDetailEntity> listOrderDetailEntities;

    public Set<OrderDetailEntity> getListOrderDetailEntities() {
        return listOrderDetailEntities;
    }

    public void setListOrderDetailEntities(Set<OrderDetailEntity> listOrderDetailEntities) {
        this.listOrderDetailEntities = listOrderDetailEntities;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeShip() {
        return timeShip;
    }

    public void setTimeShip(String timeShip) {
        this.timeShip = timeShip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoriesEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoriesEntity categories) {
        this.categories = categories;
    }

    public boolean isIs_freeship() {
        return is_freeship;
    }

    public void setIs_freeship(boolean is_freeship) {
        this.is_freeship = is_freeship;
    }

}
