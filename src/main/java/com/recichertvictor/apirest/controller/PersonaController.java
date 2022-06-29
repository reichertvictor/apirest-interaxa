package com.recichertvictor.apirest.controller;

import com.recichertvictor.apirest.entities.Persona;
import com.recichertvictor.apirest.servicesimpl.PersonaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
//@CrossOrigin("/**")
@CrossOrigin(origins= {"*"}, allowCredentials = "false" )
public class PersonaController {

    private Logger log = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaServiceImpl personaService;

    @RequestMapping("")
    @GetMapping
    public ResponseEntity<?> index (){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.findAllByOrderByApellido());
    }

    @RequestMapping("/create")
    @PutMapping
    public ResponseEntity<?> create(@RequestBody Persona Entity){
        System.out.println("me llego: "+Entity);
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(personaService.create(Entity));
        }catch (Exception ex){
            log.error("create persona error: "+ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping("/edit")
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Persona Entity){
        Optional<Persona> EntityFound = personaService.findById(Entity.getId());
        if(EntityFound.isPresent()){
            try{
                BeanUtils.copyProperties(Entity,EntityFound.get());
                return ResponseEntity.status(HttpStatus.OK).body(personaService.edit(EntityFound.get()));
            }catch (Exception ex){
                log.error("edit persona error: "+ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/delete/{id}")
    @DeleteMapping
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            personaService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            log.error("delete persona error: "+ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
