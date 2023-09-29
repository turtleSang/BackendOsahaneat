package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.FileService;
import com.thanksen.osahaneat.Service.imp.FileServiceImp;
import com.thanksen.osahaneat.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/file")
public class FileController {
    private FileServiceImp fileServiceImp;

    @Autowired
    public FileController(FileServiceImp fileServiceImp){
        this.fileServiceImp = fileServiceImp;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("USER", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        String messenger = "";


        try {
            this.fileServiceImp.store(file);
            messenger = "Upload file successfully:" + file.getOriginalFilename();
            return new ResponseEntity<>(messenger, HttpStatus.ACCEPTED);
        }catch (Exception e){
            messenger = "Upload file faild:" + file.getOriginalFilename() + " error: " + e.getMessage();
            return new ResponseEntity<>(messenger, HttpStatus.EXPECTATION_FAILED);
        }
    }


}
