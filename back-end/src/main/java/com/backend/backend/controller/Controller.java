package com.backend.backend.controller;

import com.backend.backend.model.Text;
import com.backend.backend.repository.TextRepository;
import com.backend.backend.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/text")
@CrossOrigin
public class Controller {

    @Autowired
    private TextService service;

    @PostMapping("/add")
    public Text addText(@RequestBody Text text){
        return service.saveText(text);
    }

    @GetMapping("/get")
    public List<Text> getOutput(){
        return service.getOutput();
    }



}
