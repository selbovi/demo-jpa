package com.github.selbovi.dbinit;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.selbovi.domain.Auto;
import com.github.selbovi.domain.Buyer;
import com.github.selbovi.domain.Orders;
import com.github.selbovi.repo.AutoRepository;
import com.github.selbovi.repo.BuyerRepository;
import com.github.selbovi.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GenerateData implements InitializingBean {

    public static final String BMW_X5 = "BMW X5";
    public static final String BMW_X6 = "BMW X6";
    public static final String BMW_X7 = "BMW X7";
    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        //заводим модели авто в бд
        addAutos(BMW_X5, BMW_X6, BMW_X7);
        //заводим клиентов
        addBuyers();
        //заказы
        fillOrders();
    }

    private void fillOrders() {
        //01.10.2021 – BMW X5 – 1шт. – 2’000’000 – Иванов Сергей +79107891122
        addOrder(LocalDate.of(2021, 10, 1), BMW_X5, "Иванов Сергей", 2_000_000L);

        //02.10.2021 – BMW X6 – 2шт. – 3’500’000 – Коробкин Олег +79107891155
        addOrder(LocalDate.of(2021, 10, 2), BMW_X6, "Коробкин Олег", 3_500_000L/2);
        addOrder(LocalDate.of(2021, 10, 2), BMW_X6, "Коробкин Олег", 3_500_000L/2);

        //02.10.2021 – BMW X7 – 1шт. – 2’000’000 – Олейкин Роман +79107891166
        addOrder(LocalDate.of(2021, 10, 2), BMW_X7, "Олейкин Роман", 2_000_000L);

        //02.10.2021 – BMW X7 – 1шт. – 2’000’000 – Коробкин Олег +79107891155
        addOrder(LocalDate.of(2021, 10, 2), BMW_X7, "Коробкин Олег", 2_000_000L);

        //02.10.2021 – BMW X5 – 2шт. – 2’000’000 – Коробкин Олег +79107891155
        addOrder(LocalDate.of(2021, 10, 2), BMW_X5, "Коробкин Олег", 2_000_000L);
        addOrder(LocalDate.of(2021, 10, 2), BMW_X5, "Коробкин Олег", 2_000_000L);

        //03.10.2021 – BMW X6 – 1шт. – 3’000’000 – Иванов Сергей +79107891122
        addOrder(LocalDate.of(2021, 10, 3), BMW_X6, "Иванов Сергей", 3_000_000L);
    }

    private void addOrder(LocalDate sellDate, String model, String buyerFio, Long price) {
        Orders order = new Orders();
        order.setSellDate(sellDate);
        order.setAuto(autoRepository.findAutoByModel(model));
        order.setBuyer(buyerRepository.findBuyerByFio(buyerFio));
        order.setPrice(price);
        orderRepository.save(order);

    }

    private void addBuyers() {
        List<Buyer> buyers = Arrays.asList(
            createBuyer("Иванов Сергей", "+79107891122"),
            createBuyer("Коробкин Олег", "+79107891155"),
            createBuyer("Олейкин Роман", "+79107891166")
        );

        buyerRepository.saveAll(buyers);

    }

    private Buyer createBuyer(String fio, String phone) {
        Buyer buyer = new Buyer();
        buyer.setFio(fio);
        buyer.setPhone(phone);

        return buyer;

    }

    private void addAutos(String... modelNames) {
        List<Auto> autos = Arrays.stream(modelNames).map(name -> {
            Auto auto = new Auto();
            auto.setModel(name);
            return auto;
        }).collect(Collectors.toList());

        autoRepository.saveAll(autos);
    }

}
