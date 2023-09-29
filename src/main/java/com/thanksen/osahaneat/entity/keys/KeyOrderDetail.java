package com.thanksen.osahaneat.entity.keys;

import com.thanksen.osahaneat.entity.OrderDetailEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Set;

@Embeddable
public class KeyOrderDetail implements Serializable {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "food_id")
    private int foodId;

    public KeyOrderDetail() {
    }

    public KeyOrderDetail(int orderId, int foodId) {
        this.orderId = orderId;
        this.foodId = foodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
