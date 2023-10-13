package com.thanksen.osahaneat.controller;

import com.google.gson.Gson;
import com.thanksen.osahaneat.Service.imp.LoginServiceImp;
import com.thanksen.osahaneat.entity.RolesEntity;
import com.thanksen.osahaneat.entity.UsersEntity;
import com.thanksen.osahaneat.payload.Request.SignUpRequest;
import com.thanksen.osahaneat.payload.ResponseData;
import com.thanksen.osahaneat.unit.DataGenerate;
import com.thanksen.osahaneat.unit.JwtUtilsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginServiceImp loginServiceImp;
    private JwtUtilsHelper jwtUtilsHelper;
    Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public LoginController(LoginServiceImp loginServiceImp, JwtUtilsHelper jwtUtilsHelper) {
        this.jwtUtilsHelper = jwtUtilsHelper;
        this.loginServiceImp = loginServiceImp;
    }


    @GetMapping("/getallusers")
    public ResponseEntity<?> getDemo() {
        return new ResponseEntity<>(loginServiceImp.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> SignIn(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();

        logger.trace("Kiem tra trace");
        logger.debug("Kiem tra debug");
        logger.info("Kiem tra info");
        logger.warn("Kiem tra warn");
        logger.error("Kiem tra err");

        if (loginServiceImp.checkLogin(username, password)) {
            UsersEntity usersEntity = loginServiceImp.findInfoUser(username);
            //Find Role & username
            RolesEntity rolesEntity = usersEntity.getRoles();
            String role_name = rolesEntity.getName();

            //Set data to generate
            DataGenerate dataGenerate = new DataGenerate(username, role_name);

            String data = gson.toJson(dataGenerate);

            String token = jwtUtilsHelper.generate(data);

            responseData.setStatus(200);
            responseData.setObj(token);
            responseData.setDescription("Login Successful");
        } else {
            responseData.setStatus(404);
            responseData.setObj(false);
            responseData.setDescription("Login fails");
        }
        return new ResponseEntity<>(responseData, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();

        responseData.setObj(loginServiceImp.signUp(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
