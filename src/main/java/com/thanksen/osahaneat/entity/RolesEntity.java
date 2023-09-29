package com.thanksen.osahaneat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_At", columnDefinition = "timestamp default now()")
    private Date createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    private List<UsersEntity> listUsers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UsersEntity> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<UsersEntity> listUsers) {
        this.listUsers = listUsers;
    }
}
