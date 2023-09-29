package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.imp.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersServiceImp usersServiceImp;

    @Autowired
    public  UsersController(UsersServiceImp usersServiceImp){this.usersServiceImp = usersServiceImp;}

    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        Collection<? extends GrantedAuthority> ListRolesName = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean flag = false;

        for (GrantedAuthority role: ListRolesName ) {
            System.out.println(role.getAuthority());
            if (role.getAuthority().equalsIgnoreCase("admin")){
                flag = true;
            }
        }

        if (flag){
            return new ResponseEntity<>(usersServiceImp.getAllUser(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Cant access", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("USER", HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> forbidan(){
        return new ResponseEntity<>("ADMIN", HttpStatus.OK);
    }
}
