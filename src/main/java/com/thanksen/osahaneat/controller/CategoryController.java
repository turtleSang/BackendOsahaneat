package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.imp.CategoryServiceImp;
import com.thanksen.osahaneat.Service.imp.FileServiceImp;
import com.thanksen.osahaneat.dto.CategoryDto;
import com.thanksen.osahaneat.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryServiceImp categoryServiceImp;
    private FileServiceImp fileServiceImp;

    @Autowired
    public CategoryController(CategoryServiceImp categoryServiceImp, FileServiceImp fileServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
        this.fileServiceImp = fileServiceImp;
    }
    @GetMapping()
    public ResponseEntity<?> getAllCategory(){

        ResponseData responseData = new ResponseData();
        responseData.setObj(categoryServiceImp.getAllCategory());
        responseData.setDescription("Success");
        responseData.setStatus(200);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @CacheEvict("categoryHome")
    @GetMapping("/cleancache")
    public String clearCache(){
        return "Clear Cache";
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getRestaurant(@PathVariable String filename){
        Resource resource =  fileServiceImp.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
