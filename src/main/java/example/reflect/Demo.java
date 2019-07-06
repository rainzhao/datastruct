package example.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author zhaoyu
 * @date 2019-01-31
 */
class Hello {

}

class Person{
    private String name;

    private int age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Demo{

    /**
     * 通过对象获取类类型，通过类类型获取包名
     */
    @Test
    public void testClassName() {
        Hello demo = new Hello();
        System.out.println(demo.getClass().getName());
    }

    /**
     * 获取类类型的几种方式
     * @throws Exception
     */
    @Test
    public void getClassTest() throws Exception{

        // 通过包名称获取类类型
        Class<?> helloClass = Class.forName("example.reflect.Hello");
        String name = helloClass.getName();
        System.out.println(name);

        // 通过实例对象获取类类型
        Hello hello = new Hello();
        Class<? extends Hello> helloClass1 = hello.getClass();
        System.out.println(helloClass1.getName());

        // 通过类获取类类型
        Class<Hello> helloClass2 = Hello.class;
        System.out.println(helloClass2.getName());

        /*example.reflect.Hello
        example.reflect.Hello
        example.reflect.Hello*/

    }

    /**
     * 通过类类型获取对象的实例对象
     * @throws Exception
     */
    @Test
    public void getInstanceByClassTest() throws Exception{
        Class<?> aClass = Class.forName("example.reflect.Person");

        Person person = (Person) aClass.newInstance();

        person.setAge(10);
        person.setName("test");

        System.out.println(person);
    }

    /**
     * 通过constructor获取构造函数 并初始化对象
     * @throws Exception
     */
    @Test
    public void getConstructTest() throws Exception {
        Class<?> personClass = Class.forName("example.reflect.Person");
        Constructor<?> constructor = personClass.getConstructor(new Class[]{String.class, int.class});

        Object tech = constructor.newInstance("tech", 20);

        System.out.println(tech);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getConstructorListTest() throws Exception {
        Class<?> personClass = Class.forName("example.reflect.Person");
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            
        }

        Class<Product> productClass = Product.class;
        Product product = productClass.newInstance();

    }

    @Test
    public void processProduct() throws Exception{
        ProductFactory productFactory = new ProductFactory();
        Product product = productFactory.getProduct(JavaProduct.class);
        product.printProduct();
    }


}


