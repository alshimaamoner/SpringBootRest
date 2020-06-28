package com.example.springMicroService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

//To make Static Filtering in class level
//@JsonIgnoreProperties("name")
@ApiModel(description = "user model description")
@Entity
public class User {
    @Id
    private int id;
   // @Size(min =2,message = "Name should have 2 character")
    @Min(3)
    @Max(30)
    @NotEmpty(message = "Name should not empty")
    @ApiModelProperty(notes = "Name should not empty")
    private String name;
    //@JsonIgnore ->To Ignore static filtering
    @Past
    @NotEmpty(message = "Please Enter BirthDate")
    @ApiModelProperty(notes = "birth date should be in past ")
    @JsonIgnore
    private Date birthDate;

    public User() {
    }

    public User(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


}
