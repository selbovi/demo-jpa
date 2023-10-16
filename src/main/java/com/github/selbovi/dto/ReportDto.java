package com.github.selbovi.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * выходные данные: фамилия и имя клиента, контактный номер, стоимость заказа, дата заказа, модель автомобиля, количество автомобилей в заказе)
 */
@Data
public class ReportDto {

    private String fio;
    private String phone;
    private LocalDate orderDate;
    private String model;
    private Long count;

}
