package com.crema.creamaspring.controllers;

import com.crema.creamaspring.services.AssemblyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api")
public class AssemblyController {
    final AssemblyService assemblyService;

    @Autowired
    public AssemblyController(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @PostMapping("/assemble")
    public ResponseEntity<String> assemble() {
        log.info("Scraping and adding posts and quotes");
        assemblyService.assemblePosts();
        return new ResponseEntity<>("Assembly completed", HttpStatus.OK);
    }

//    @GetMapping("/registration")
//    public ResponseEntity<String> registration() {
//        return new ResponseEntity<>("REGISTEREDZZ", HttpStatus.OK);
//    }
}
