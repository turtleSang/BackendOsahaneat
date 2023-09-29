package com.thanksen.osahaneat.payload.Request;

public class RequestOrder {
    private int userId;
    private int resId;
    private int[] listFoodId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int[] getListFoodId() {
        return listFoodId;
    }

    public void setListFoodId(int[] listFoodId) {
        this.listFoodId = listFoodId;
    }
}
