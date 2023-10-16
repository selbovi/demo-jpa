для сборки нужна java17
БД H2
консоль БД доступна по (http://localhost:8080/h2-console)

//отчет по выручке
curl --location --request GET 'localhost:8080/takings?from=01.10.2021&to=02.10.2021'


//отчет по всем продажам (упорядоченный по дате заказа, внутри даты по ФИ клиента, внутри клиента по сумме заказа)
curl --location --request GET 'localhost:8080/orders'


//отчет с группировкой, также упорядоченный, но с возможностью увидеть кол-во заказов по каждому клиенту
curl --location --request GET 'localhost:8080/report'