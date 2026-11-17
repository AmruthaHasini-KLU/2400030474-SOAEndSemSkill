package com.example.demo.service;

import com.example.demo.entity.Tourist;
import com.example.demo.repository.TouristRepository;
import com.example.demo.util.JwtUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class TouristService 
{
    private final TouristRepository repository;
    private final JwtUtil jwtUtil;

    public TouristService(TouristRepository repository,
                          JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public Tourist updateTourist(Long id,Tourist tourist,String token) 
                                 
    {
        if (!jwtUtil.validateToken(token)) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid Token"
            );
        }

        Tourist old = repository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tourist Not Found"
                )
            );

       
        old.setDestination(tourist.getDestination());
        old.setContactDetails(tourist.getContactDetails());
        return repository.save(old);
    }
}
