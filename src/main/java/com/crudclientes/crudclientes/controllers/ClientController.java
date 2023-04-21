package com.crudclientes.crudclientes.controllers;

import com.crudclientes.crudclientes.dto.ClientDTO;
import com.crudclientes.crudclientes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ClientDTO findyById(@PathVariable Long id){
        return service.findById(id);
    }
}
