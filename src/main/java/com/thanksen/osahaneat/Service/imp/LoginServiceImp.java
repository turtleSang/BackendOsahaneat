package com.thanksen.osahaneat.Service.imp;

import com.thanksen.osahaneat.dto.UserDto;
import com.thanksen.osahaneat.entity.UsersEntity;
import com.thanksen.osahaneat.payload.Request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDto> getAllUser();
    boolean checkLogin(String username, String password);
    UsersEntity findInfoUser(String username);
    boolean signUp(SignUpRequest signUpRequest);
}
