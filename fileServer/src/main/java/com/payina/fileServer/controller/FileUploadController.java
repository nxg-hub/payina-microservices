package com.payina.fileServer.controller;

import com.payina.fileServer.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUpload fileUpload;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        return ResponseEntity.ok(imageURL);
    }
}

//import com.payina.fileServer.service.FileUpload;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@RequiredArgsConstructor
//public class FileUploadController {
//
//    private final FileUpload fileUpload;
//
//    @RequestMapping("/")
//    public String home(){
//        return "home";
//    }
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("image")MultipartFile multipartFile,
//                             Model model) throws IOException {
//        String imageURL = fileUpload.uploadFile(multipartFile);
//        model.addAttribute("imageURL",imageURL);
//        return "gallery";
//    }
//}
