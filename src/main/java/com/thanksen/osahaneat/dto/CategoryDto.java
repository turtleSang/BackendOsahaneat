package com.thanksen.osahaneat.dto;

import java.util.List;
import java.util.Set;

public class CategoryDto {
    private String name;
    private List<MenuDto> listMenu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDto> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<MenuDto> listMenu) {
        this.listMenu = listMenu;
    }
}
