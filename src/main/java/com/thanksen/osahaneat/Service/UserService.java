package com.thanksen.osahaneat.Service;

import com.thanksen.osahaneat.Reponsitory.UsersRepository;
import com.thanksen.osahaneat.Service.imp.UsersServiceImp;
import com.thanksen.osahaneat.dto.UserDto;
import com.thanksen.osahaneat.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UsersServiceImp {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUser() {
        List<UsersEntity> listUsers = usersRepository.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for (UsersEntity usersEntity : listUsers) {
            UserDto userDto = new UserDto();
            userDto.setId(usersEntity.getId());
            userDto.setFullname(usersEntity.getFullname());
            userDto.setUsername(usersEntity.getUsername());
            userDto.setPassword(usersEntity.getPassword());

            listUserDto.add(userDto);
        }
        return listUserDto;
    }
}
