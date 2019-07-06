package example.function;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author zhaoyu
 * @date 2019-01-28
 */
public class FunctionTest {

    public static String appendStr(String oriStr, Function<String, String> stringFunction) {

        return stringFunction.apply(oriStr);
    }

    @Test
    public void appendStrTest() {
        String s = appendStr("hahaha", (x) -> x + "bbb");
        System.out.println(s);
    }


    public static int modifyValue(Integer valueToBeOperate, Function<Integer, Integer> function) {
        return function.apply(valueToBeOperate);
    }

    @Test
    public void modifyValueTest() {
        int ori = modifyValue(10, (x) -> x + 20);
        System.out.println(ori);
    }


    public static <T> boolean isExist(Collection<T> list, Function<T, Boolean> function) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (T t : list) {
            if (function.apply(t)) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void isExistTest() {
        List<Integer> oriList = Arrays.asList(10, 20, 30, 40);
        boolean exist = isExist(oriList, x -> x.equals(30));
        System.out.println(exist);
    }


    interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> boolean exist(Collection<T> list, Predicate match) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (T t : list) {
            if (match.test(t)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void existTest() {
        List<Integer> oriList = Arrays.asList(10, 20, 30, 40);
        boolean exist = exist(oriList, (x) -> x.equals(30));
        exist(oriList, new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        });
        System.out.println(exist);
    }


    public static <T> T findData(Collection<T> list, Function<T, Boolean> function) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        long begin1 = System.currentTimeMillis();
        for (T t : list) {
            if (function.apply(t)) {
                // return t;
                break;
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("1时间间隔为：" + (end1 - begin1));

        long begin2= System.currentTimeMillis();
        Optional<T> first = list.stream().parallel().filter(x -> function.apply(x)).findFirst();
        long end2 = System.currentTimeMillis();
        System.out.println("2时间间隔为：" + (end2 - begin2));

        return first.orElse(null);
    }

    @Test
    public void findDataTest() {
        List<Integer> oriList = new ArrayList<>();
        for(int i = 0; i < 10000000; i++) {
            oriList.add(i);
        }
        Integer data = findData(oriList, x -> x.equals(9999999));
        System.out.println(data);
    }

    /**
     * UnaryOperator 操作
     * @param oriStr 原始字符串
     * @param unaryOperator
     * @param <T>
     * @return
     */
    public static <T> T appendStrUnary(T oriStr, UnaryOperator<T> unaryOperator) {
        return unaryOperator.apply(oriStr);
    }

    @Test
    public void appendStrUnaryTest() {
        String hello = appendStrUnary("hello", x -> x += " world !");
        System.out.println(hello);
    }

}
