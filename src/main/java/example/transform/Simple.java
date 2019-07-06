package example.transform;

import java.util.Arrays;

import static java.util.Comparator.comparing;

/**
 * @author zhaoyu
 * @date 2019-01-29
 */
public class Simple {
    public static void main(String[] args) {
        Order order = new Order(Arrays.asList(
                new OrderItem(1, 1225),
                new OrderItem(2, 983),
                new OrderItem(3, 1554)
        ));

        order.transformAndPrint((x) -> x.sorted(comparing(OrderItem::getPrice)));
    }

}
