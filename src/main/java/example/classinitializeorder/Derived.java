package example.classinitializeorder;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */

/**
 * 派生类
 */
public class Derived extends Base {
    /*2*/
    static {
        System.out.println("Sub Static Block 1");
    }

    /*2*/
    private static String staticValue = Log.staticFieldInit("Sub");

    /*2*/
    static {
        System.out.println("Sub Static Block 2");
    }

    /*5*/ {
        System.out.println("Sub Normal Block 1");
    }

    /*5*/
    private String value = Log.fieldInit();

    /*5*/
    {
        System.out.println("Sub Normal Block 2");
    }

    /*6*/
    Derived() {
        System.out.println("Sub Derived Constructor");
    }

    public static void main(String[] args) {
        Derived d = new Derived();
        System.out.println("======");
        Derived d2=new Derived();
    }
}