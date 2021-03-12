package com.poc.spring.docker.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;

    @NotNull(message = "FirstName cannot be null")
    @NotBlank(message = "FirstName cannot be blank")
    String firstName;

    @NotNull(message = "LastName cannot be null")
    String lastName;
}
