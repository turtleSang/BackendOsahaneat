package com.thanksen.osahaneat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "created_At")
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<RatingFoodEntity> listRatingFoodEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<RatingFoodEntity> ListRestaurantEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<OrdersEntity> listOrdersEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public Set<RatingFoodEntity> getListRatingFoodEntities() {
        return listRatingFoodEntities;
    }

    public void setListRatingFoodEntities(Set<RatingFoodEntity> listRatingFoodEntities) {
        this.listRatingFoodEntities = listRatingFoodEntities;
    }

    public Set<RatingFoodEntity> getListRestaurantEntities() {
        return ListRestaurantEntities;
    }

    public void setListRestaurantEntities(Set<RatingFoodEntity> listRestaurantEntities) {
        ListRestaurantEntities = listRestaurantEntities;
    }

    public Set<OrdersEntity> getListOrdersEntities() {
        return listOrdersEntities;
    }

    public void setListOrdersEntities(Set<OrdersEntity> listOrdersEntities) {
        this.listOrdersEntities = listOrdersEntities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }

    public RolesEntity getRoles() {
        return roles;
    }

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }
}
