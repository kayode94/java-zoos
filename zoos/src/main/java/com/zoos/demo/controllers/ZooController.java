package com.zoos.demo.controllers;

import com.zoos.demo.models.Zoo;
import com.zoos.demo.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/zoos")
public class ZooController
{
    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoos", produces = {"application/json"})
    public ResponseEntity<?> listAllZoos()
    {
        return new ResponseEntity<>(zooService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{id}", produces = {"application/json"})
    public ResponseEntity<?> findZooById(@PathVariable long id)
    {
        Zoo z = zooService.findZooById(id);
        return new ResponseEntity<>(z, HttpStatus.OK);
    }

    @PostMapping(value = "/zoo",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewZoo(@Valid @RequestBody Zoo newZoo) throws URISyntaxException
    {
        // nullify the zoo id
        newZoo.setZooid(0);
        newZoo = zooService.save(newZoo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zooid}")
                .buildAndExpand(newZoo.getZooid())
                .toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

}
