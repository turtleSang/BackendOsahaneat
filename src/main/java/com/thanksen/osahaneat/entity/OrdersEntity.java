package com.thanksen.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private RestaurantEntity restaurant;

    @Column(name = "created_At")
    private Date createdAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetailEntity> listOrderItemEntities;

    public Set<OrderDetailEntity> getListOrderItemEntities() {
        return listOrderItemEntities;
    }

    public void setListOrderItemEntities(Set<OrderDetailEntity> listOrderItemEntities) {
        this.listOrderItemEntities = listOrderItemEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
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
