package task;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {

     //   1. Создаём список заказов с разными продуктами и их стоимостями
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        // 2. Группируем заказы по продуктам
        Map<String, List<Order>> productGroup = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        //3. Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> productTotalCosts = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,Collectors.summingDouble(Order::getCost)));

        // 4. Сортируем продукты по убыванию общей стоимости
        List<Map.Entry<String, Double>> sortedProducts = productTotalCosts.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
        System.out.println(sortedProducts);

        // 5. Выбираем три самых дорогих продукта
        List<Map.Entry<String, Double>> top3Products = sortedProducts.stream()
                .limit(3)
                .collect(Collectors.toList());

        // 6. Выводим результат
        System.out.println("Top 3 most expensive products and their total costs:");
        top3Products.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

    }

}