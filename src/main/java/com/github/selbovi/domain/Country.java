package com.github.selbovi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Country {

    @Id
    private String code;
    private String name;

}
