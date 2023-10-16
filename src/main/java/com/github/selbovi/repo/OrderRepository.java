package com.github.selbovi.repo;

import java.time.LocalDate;
import java.util.List;

import com.github.selbovi.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

    /**
     * выручка автосалона за заданный период (входные данные: дата начала, дата окончания периода; выходные данные: выручка);
     *
     * @param from дата начала
     * @param to дата окончания
     * @return выручка
     */
    @Query(
        nativeQuery = true,
        value = "SELECT SUM(price) FROM orders WHERE sell_date >= :from AND sell_date <= :to"
    )
    Long takings(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = "SELECT o.sell_date, b.fio, b.phone, sum(o.price) as total, count(*)  FROM ORDERS o join buyer b on o.buyer_id = b.id \n"
        + "group by o.sell_date, b.fio, b.phone\n"
        + "order by  o.sell_date desc, b.fio asc, total desc",nativeQuery = true)
    List<?> withGrouping();

}
