package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.FileService;
import com.thanksen.osahaneat.Service.imp.FileServiceImp;
import com.thanksen.osahaneat.Service.imp.FoodServiceImp;
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
@RequestMapping("/menu")
public class MenuController {

    private FoodServiceImp foodServiceImp;
    private FileServiceImp fileServiceImp;

    @Autowired
    public MenuController(FoodServiceImp foodServiceImp, FileService fileServiceImp ){
        this.foodServiceImp = foodServiceImp;
        this.fileServiceImp = fileServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRestaurant(
            @RequestParam("file") MultipartFile file,
            @RequestParam String title,
            @RequestParam boolean is_freeship,
            @RequestParam String time_ship,
            @RequestParam double price,
            @RequestParam int cate_id)
    {
        ResponseData responseData = new ResponseData();
        try {
            fileServiceImp.store(file);
            String image = file.getOriginalFilename();

            boolean check = foodServiceImp.createMenu(title, image, is_freeship, time_ship, price, cate_id);

            responseData.setDescription("upload successfully");
            responseData.setObj(check);

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setDescription("upload fail");
            responseData.setObj(false);
            return new ResponseEntity<>(responseData, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getRestaurant(@PathVariable String filename){
        System.out.println(filename);
        Resource resource =  fileServiceImp.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


}
