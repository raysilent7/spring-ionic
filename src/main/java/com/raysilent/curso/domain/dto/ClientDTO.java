package com.raysilent.curso.domain.dto;

import com.raysilent.curso.domain.Client;
import com.raysilent.curso.services.validation.UpdateClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@UpdateClient
public class ClientDTO implements Serializable {

    private Integer id;

    @NotEmpty(message="Field can't be empty")
    @Length(min=5, max=120, message="Name must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message="Field can't be empty")
    @Email(message="Enter a valid email")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
