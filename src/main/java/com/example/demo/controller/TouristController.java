
package com.example.demo.controller;

import com.example.demo.entity.Tourist;
import com.example.demo.service.TouristService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public Tourist updateTourist(
            @PathVariable Long id,
            @RequestBody Tourist tourist,
            @RequestHeader("Authorization") String header) {

        String token = header.replace("Bearer ", "");

        return service.updateTourist(
            id, tourist, token
        );
    }
}
