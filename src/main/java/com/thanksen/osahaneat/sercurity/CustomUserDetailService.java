package com.thanksen.osahaneat.sercurity;

import com.thanksen.osahaneat.Reponsitory.UsersRepository;
import com.thanksen.osahaneat.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User doesn't exits");
        }

        return new User(username, user.getPassword(), new ArrayList<>());
    }
}
