package example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhaoyu
 * @date 2019-01-23
 */
public class BookReflectTest {


    public static void main(String[] args) throws Exception {
        Class<?> bookClass = Class.forName("example.reflect.Book");

        Method[] methods = bookClass.getDeclaredMethods();

        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            String methodName = method.getName();
            System.out.println("方法名称: " + methodName);

            for (Class<?> parameterType : parameterTypes) {
                String name = parameterType.getName();
                System.out.println("参数类型: " + name);
            }
        }

        // 获取声明的属性
        Field[] declaredFields = bookClass.getDeclaredFields();

        Book book = (Book) bookClass.newInstance();

        /*通过反射操作属性值*/
        for (Field declaredField : declaredFields) {
            // 获取声明的属性名称：
            System.out.println("属性为：" + declaredField.getName());

            Field

                    field = bookClass.getDeclaredField(declaredField.getName());
            field.setAccessible(true);
            field.set(book, "haha");
            System.out.println(book.getName());
        }

        /*使用构造方法初始化*/
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Object ceshi = constructor.newInstance("1111");

        /*使用set方法赋值*/
        Method setNameMethod = bookClass.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(ceshi, "ceshi");

        Method methodName = bookClass.getDeclaredMethod("getName");
        Object invoke = methodName.invoke(ceshi);

        System.out.println(invoke);



    }

}
