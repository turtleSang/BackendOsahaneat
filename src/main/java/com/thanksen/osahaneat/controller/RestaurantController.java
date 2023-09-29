package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.MenuService;
import com.thanksen.osahaneat.Service.imp.FileServiceImp;
import com.thanksen.osahaneat.Service.imp.RestaurantServiceImp;
import com.thanksen.osahaneat.dto.RestaurantDto;
import com.thanksen.osahaneat.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private FileServiceImp fileServiceImp;
    private RestaurantServiceImp restaurantServiceImp;

    @Autowired
    public RestaurantController(FileServiceImp fileServiceImp, RestaurantServiceImp restaurantServiceImp) {
        this.restaurantServiceImp = restaurantServiceImp;
        this.fileServiceImp = fileServiceImp;
    }


    @PostMapping
    public ResponseEntity<?> createRestaurant(
            @RequestParam("file") MultipartFile file,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam String description,
            @RequestParam boolean is_freeship,
            @RequestParam String address,
            @RequestParam String isDate)
    {
        ResponseData responseData = new ResponseData();
        try {
            Date date =new SimpleDateFormat("dd/MM/yyyy").parse(isDate);
            fileServiceImp.store(file);
            String image = file.getOriginalFilename();
            boolean check = restaurantServiceImp.insert(title, subtitle, description, is_freeship, image, address, date);

            responseData.setDescription("upload successfully");
            responseData.setObj(check);

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setDescription("upload fail");
            responseData.setObj(false);
            return new ResponseEntity<>(responseData, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRestaurant(){
        return new ResponseEntity<>(restaurantServiceImp.getAllRestaurant(), HttpStatus.OK);
    }



    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getRestaurant(@PathVariable String filename){
        Resource resource =  fileServiceImp.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
        ResponseData responseData = new ResponseData();

        RestaurantDto restaurantDto = restaurantServiceImp.getDetailRestaurant(id);

        responseData.setObj(restaurantDto);
        responseData.setStatus(200);
        responseData.setDescription("Got it");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
