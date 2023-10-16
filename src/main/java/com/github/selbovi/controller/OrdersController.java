package com.github.selbovi.controller;

import java.time.LocalDate;
import java.util.List;

import com.github.selbovi.domain.Buyer;
import com.github.selbovi.domain.Orders;
import com.github.selbovi.dto.ReportDto;
import com.github.selbovi.mapper.ReportMapper;
import com.github.selbovi.repo.OrderRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * выручка автосалона за заданный период (входные данные: дата начала, дата окончания периода; выходные данные: выручка);
     *
     * @param from дата начала
     * @param to дата окончания
     * @return выручка
     */
    @RequestMapping("/takings")
    public ResponseEntity<Long> takings(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = "dd.MM.yyyy") LocalDate from,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = "dd.MM.yyyy") LocalDate to
    ) {
        Long result = orderRepository.takings(from, to);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/orders")
    public ResponseEntity<List<ReportDto>> orders() {
        List<Orders> report = orderRepository.findAll(spec());
        List<ReportDto> reportDtos =
            report.stream().map(order -> ReportMapper.INSTANCE.ordersToReportDto(order)).toList();

        return new ResponseEntity<>(reportDtos, HttpStatus.OK);
    }

    @RequestMapping("/report")
    public ResponseEntity report() {
        List<?> objects = orderRepository.withGrouping();
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    //SELECT * FROM ORDERS o
    // join buyer b
    // on o.buyer_id = b.id
    // order by o.sell_date desc,
    // b.fio asc,
    // o.price desc
    public static Specification<Orders> spec() {
        return (root, query, criteriaBuilder) -> {
            Join<Orders, Buyer> orderBuyers = root.join("buyer");
            query.orderBy(
                criteriaBuilder.desc(root.get("sellDate")),
                criteriaBuilder.asc(orderBuyers.get("fio")),
                criteriaBuilder.desc((root.get("price")))
            );
            return criteriaBuilder.and();
        };
    }

}
