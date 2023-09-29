package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.UsersRepository;
import com.thanksen.osahaneat.Service.imp.LoginServiceImp;
import com.thanksen.osahaneat.dto.UserDto;
import com.thanksen.osahaneat.entity.RolesEntity;
import com.thanksen.osahaneat.entity.UsersEntity;
import com.thanksen.osahaneat.payload.Request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {


    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUser(){
        List<UsersEntity> listUser = usersRepository.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for (UsersEntity usersEntity : listUser) {
            UserDto userDto = new UserDto();
            userDto.setUsername(usersEntity.getUsername());
            userDto.setPassword(usersEntity.getPassword());
            userDto.setId(usersEntity.getId());
            userDto.setFullname(usersEntity.getFullname());
            userDto.setCreatedAt(usersEntity.getCreatedAt());
            listUserDto.add(userDto);
        }
        return listUserDto;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        UsersEntity user = usersRepository.findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UsersEntity findInfoUser(String username) {
        UsersEntity user = usersRepository.findByUsername(username);

        return user;
    }

    @Override
    public boolean signUp(SignUpRequest signUpRequest) {
        UsersEntity usersEntity = new UsersEntity();
        RolesEntity rolesEntity = new RolesEntity();

        rolesEntity.setId(signUpRequest.getRoles_id());

        usersEntity.setFullname(signUpRequest.getFullname());
        usersEntity.setUsername(signUpRequest.getUsername());
        usersEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        usersEntity.setRoles(rolesEntity);

        try {
            usersRepository.save(usersEntity);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
