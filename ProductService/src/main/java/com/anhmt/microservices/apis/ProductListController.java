package com.anhmt.microservices.apis;

import com.anhmt.microservices.bloc.ProductListBloc;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductListController {

    private final ProductListBloc productListBloc;

    @GetMapping
    public ResponseEntity<?> fetchAll() {
        productListBloc.send();
        return new ResponseEntity<Object>("ok", OK);
    }
}
