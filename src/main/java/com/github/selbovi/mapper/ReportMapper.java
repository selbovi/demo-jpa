package com.github.selbovi.mapper;

import com.github.selbovi.domain.Orders;
import com.github.selbovi.dto.ReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper( ReportMapper.class );

    @Mapping(source = "sellDate", target = "orderDate")
    @Mapping(source = "buyer.fio", target = "fio")
    @Mapping(source = "buyer.phone", target = "phone")
    @Mapping(source = "auto.model", target = "model")
    @Mapping(constant = "1L", target = "count")
    ReportDto ordersToReportDto(Orders orders);
}
