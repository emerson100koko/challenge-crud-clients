package com.crudclientes.crudclientes.dto;

import com.crudclientes.crudclientes.entiti.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    private String name;
    private String cpf;
    private Double income;

    @PastOrPresent
    private LocalDate bithDate;
    private Integer children;

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate bithDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.bithDate = bithDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        bithDate = entity.getBithDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBithDate() {
        return bithDate;
    }

    public Integer getChildren() {
        return children;
    }
}
