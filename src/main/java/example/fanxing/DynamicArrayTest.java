package example.fanxing;

/**
 * @author zhaoyu
 * @date 2019-04-11
 */
public class DynamicArrayTest {

    public static void main(String[] args) {
        DynamicArray<Number> numbers = new DynamicArray<>();
        DynamicArray<Integer> ints = new DynamicArray<>();
        DynamicArray<Long> longList = new DynamicArray<>();
        longList.add(200L);
        longList.add(300L);
        ints.add(100);
        ints.add(34);
        numbers.addAll(ints);
        numbers.addAll(longList);
        int size = numbers.size();

        for (int i = 0; i < size; i++) {
            System.out.println(numbers.get(i));
        }
    }
}
