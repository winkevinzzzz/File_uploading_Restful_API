package com.istad.uploadfileapi.Controller;

import com.istad.uploadfileapi.Response.FileResponse;
import com.istad.uploadfileapi.Service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    private final FileService fileService;
    FileUploadController(FileService fileService){

        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("file") MultipartFile file){
        String filename = null;
        try {
            filename = this.fileService.uploadFile(file );

        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null, "images can not upload !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FileResponse("http://localhost:8080"+filename, "images is successfully upload to server!!"), HttpStatus.OK);
    }
}
