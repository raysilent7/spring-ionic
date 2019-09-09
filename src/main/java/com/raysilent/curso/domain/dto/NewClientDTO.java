package com.raysilent.curso.domain.dto;

import com.raysilent.curso.services.validation.InsertClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@InsertClient
public class NewClientDTO implements Serializable {

    @NotEmpty(message="Field can't be empty")
    @Length(min=5, max=120, message="Name must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message="Field can't be empty")
    @Email(message="Enter a valid email")
    private String email;

    @NotEmpty(message="Field can't be empty")
    private String cpfOrCnpj;
    private Integer type;

    @NotEmpty(message="Field can't be empty")
    @Length(min=6, max=20, message="Password must be between 6 and 20 characters")
    private String password;

    @NotEmpty(message="Field can't be empty")
    private String location;

    @NotEmpty(message="Field can't be empty")
    private String number;
    private String complement;
    private String neighbr;

    @NotEmpty(message="Field can't be empty")
    private String cep;

    @NotEmpty(message="Field can't be empty")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public NewClientDTO() {
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbr() {
        return neighbr;
    }

    public void setNeighbr(String neighbr) {
        this.neighbr = neighbr;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
