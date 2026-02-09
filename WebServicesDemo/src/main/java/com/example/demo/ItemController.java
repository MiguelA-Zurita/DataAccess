package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class ItemController {

    @GetMapping("/items")
    public String getItems() {
        try {
            return ItemClass.getAll().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("/items/{id}")
    public String getItem(@PathVariable int id) {
        try {
            return ItemClass.getById(id).toString();
        } catch(NullPointerException | SQLException e){
            return "Item not found:" + e.getMessage();
        }
    }
    @PostMapping("/items")
    public String postItem(@RequestBody ItemClass.Item item){
        try {
            ItemClass.add(item);
            return item.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
