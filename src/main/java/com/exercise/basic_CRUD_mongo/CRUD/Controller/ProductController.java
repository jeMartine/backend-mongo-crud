package com.exercise.basic_CRUD_mongo.CRUD.Controller;

import java.util.List;

import com.exercise.basic_CRUD_mongo.global.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exercise.basic_CRUD_mongo.CRUD.Entity.Product;
import com.exercise.basic_CRUD_mongo.CRUD.Service.ProductService;
import com.exercise.basic_CRUD_mongo.CRUD.dto.ProductDTO;
import com.exercise.basic_CRUD_mongo.global.exceptions.AttributeException;
import com.exercise.basic_CRUD_mongo.global.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

import javax.swing.*;


@RestController
@RequestMapping("/product")
@CrossOrigin //necesario para poder enviar solicitudes a través de puertos diferentes (8080 - 4200)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable ("id") int id) throws ResourceNotFoundException{
        return ResponseEntity.ok(productService.getOne(id));
    }

    @PostMapping()
    public ResponseEntity<MessageDto> save (@Valid @RequestBody ProductDTO dto) throws AttributeException{
        Product product = productService.save(dto);
        String message = "Product " + product.getName() + " have been saved";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable ("id") int id, @Valid @RequestBody ProductDTO dto) throws ResourceNotFoundException, AttributeException{
        Product product = productService.update(id, dto);
        String message = "Product "+ product.getName()+ " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable ("id") int id) throws ResourceNotFoundException{
        Product product = productService.detele(id);
        String message = "Product " + product.getName() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
