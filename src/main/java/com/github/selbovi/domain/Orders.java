package com.github.selbovi.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Дата продажи.
     */
    private LocalDate sellDate;
    /**
     * Цена продажи.
     */
    private Long price;
    /**
     * Менеджер.
     */
    @ManyToOne(optional = false)
    private Buyer buyer;

    /**
     * проданная модель.
     */
    @ManyToOne(optional = false)
    private Auto auto;
}
