package com.example.springMicroService.controller;

import com.example.springMicroService.customException.UserNotFoundException;
import com.example.springMicroService.model.User;
import com.example.springMicroService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
public class UserResources {
    @Autowired
    UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    //HateOas
    @GetMapping(path = "/user/{id}")
    public EntityModel<User> getUserById(@PathVariable  int id){
        User user=userService.findById(id);
        if(user==null)
            throw new UserNotFoundException("Id : "+id);
        //Defining Setting
        //Define Link
        EntityModel<User> resource = EntityModel.of(user);
        Link link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers())
                .withRel("all-users");
        resource.add(link);

        return resource;
    }

    @PostMapping(path = "/user/add")
    public ResponseEntity<Object> addUser(@RequestBody @Valid  User user){

        userService.addUser(user);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/delete/{id}")
    public void deleteUserById(@PathVariable  int id){
        if(userService.findById(id)==null)
            throw new UserNotFoundException("Id : "+id);
        userService.deleteUserById(id);
//        return (ResponseEntity<Object>) ResponseEntity.noContent();

    }
//
//    //Internationlized
//    @GetMapping(path = "/helloWorldInternationalize")
//    public String helloWorldInternationalize(@RequestHeader(name = "Accept-Language",required = false) Locale locale){
//        //getMessage-> take code,array of args ,locale
//        return messageSource.getMessage("good.morning.message",null,locale);
//    }

    //Internationlized
    @GetMapping(path = "/helloWorldInternationalize")
    public String helloWorldInternationalize(){
        //getMessage-> take code,array of args ,locale
        //instead of passing Locale using LocaleContextHolder.getLocale()
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
