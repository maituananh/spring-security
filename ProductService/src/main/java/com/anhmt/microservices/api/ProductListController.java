package com.anhmt.microservices.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/products")
public class ProductListController {

    @GetMapping
    public ResponseEntity<?> fetchAll() {
        return new ResponseEntity<Object>("ok", OK);
    }
}
