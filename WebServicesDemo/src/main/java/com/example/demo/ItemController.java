package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.sql.SQLException;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ItemController {

    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<String> getItems(@RequestParam(required = false) Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            // Returns item or all items based on ID
            if (id == null) {
                return new ResponseEntity<>(ItemClass.getAll().toString(), headers,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Objects.requireNonNull(ItemClass.getById(id)).toString(), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Adds item; returns item or error in response
     */
    @PostMapping("/items")
    public ResponseEntity<String> postItem(@RequestBody ItemClass.Item item) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            ItemClass.add(item);
            return new ResponseEntity<>(item.toString(),headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), headers,HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates item; returns item or error in response
     */
    @PutMapping("/items")
    public ResponseEntity<String> putItem(@RequestBody ItemClass.Item item) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            ItemClass.update(item);
            return new ResponseEntity<>(item.toString(),headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), headers,HttpStatus.BAD_REQUEST);
        }
    }

    // Deletes item by ID
    @DeleteMapping("/items")
    public ResponseEntity<String> deleteItem(@RequestParam int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Deletes item; returns success or SQL error
        try {
            ItemClass.delete(id);
            try {
                ItemClass.getById(id);
                return new ResponseEntity<>("Item not deleted",HttpStatus.BAD_REQUEST);
            } catch (NullPointerException e) {
                return new ResponseEntity<>("Item deleted",HttpStatus.OK);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
