package com.thanksen.osahaneat.entity;

import com.thanksen.osahaneat.entity.keys.KeyOrderDetail;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "orders_detail")
public class OrderDetailEntity {
    @EmbeddedId
    private KeyOrderDetail key;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false )
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name = "food_id", insertable = false, updatable = false)
    private FoodEntity foods;

    @Column(name = "created_At")
    private Date createdAt;

    public KeyOrderDetail getKey() {
        return key;
    }

    public void setKey(KeyOrderDetail key) {
        this.key = key;
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public FoodEntity getFoods() {
        return foods;
    }

    public void setFoods(FoodEntity foods) {
        this.foods = foods;
    }
}
