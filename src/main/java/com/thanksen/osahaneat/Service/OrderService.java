package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.OrderDetailRepository;
import com.thanksen.osahaneat.Reponsitory.OrderRepository;
import com.thanksen.osahaneat.Service.imp.OrderServiceImp;
import com.thanksen.osahaneat.entity.*;
import com.thanksen.osahaneat.entity.keys.KeyOrderDetail;
import com.thanksen.osahaneat.payload.Request.RequestOrder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService implements OrderServiceImp {

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


    @Transactional
    @Override
    public boolean createOrder(RequestOrder requestOrder) {
        try {
            OrdersEntity ordersEntity = new OrdersEntity();

            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setId(requestOrder.getUserId());

            RestaurantEntity restaurantEntity = new RestaurantEntity();
            restaurantEntity.setId(requestOrder.getResId());


            ordersEntity.setUser(usersEntity);
            ordersEntity.setRestaurant(restaurantEntity);
            orderRepository.save(ordersEntity);

            List<OrderDetailEntity> listOrderDetail = new ArrayList<>();

            for (int foodId : requestOrder.getListFoodId()
            ) {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

                FoodEntity foodEntity = new FoodEntity();
                foodEntity.setId(foodId);
                orderDetailEntity.setFoods(foodEntity);
                orderDetailEntity.setOrder(ordersEntity);

                KeyOrderDetail keyOrderDetail = new KeyOrderDetail(ordersEntity.getId(), foodId);

                orderDetailEntity.setKey(keyOrderDetail);

                listOrderDetail.add(orderDetailEntity);
            }

            orderDetailRepository.saveAll(listOrderDetail);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
