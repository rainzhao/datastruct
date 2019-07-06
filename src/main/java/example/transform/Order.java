package example.transform;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhaoyu
 * @date 2019-01-29
 */
public class Order {
    private List<OrderItem> items;

    public Order(List<OrderItem> orderItems) {
        items = orderItems;
    }

    public void transformAndPrint(Transformer<Stream<OrderItem>> transformOrderItems) {

        transformOrderItems.transform(items.stream()).forEach(System.out::println);
    }
}
