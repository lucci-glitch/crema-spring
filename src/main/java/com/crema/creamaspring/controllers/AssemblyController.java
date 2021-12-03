package com.crema.creamaspring.controllers;

import com.crema.creamaspring.services.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AssemblyController {
    final AssemblyService assemblyService;

    @Autowired
    public AssemblyController(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @PostMapping("/assemble")
    public ResponseEntity<String> assemble() {
        assemblyService.assemblePosts();
        return new ResponseEntity<>("Assembly completed", HttpStatus.OK);
    }
}
