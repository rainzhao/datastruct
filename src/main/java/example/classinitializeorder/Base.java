package example.classinitializeorder;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
class Base {
    /*1*/
    private static String staticValue = Log.baseStaticFieldInit();

    /*1*/
    static {
        System.out.println("Base Static Block 1");
    }

    /*1*/
    static {
        System.out.println("Base Static Block 2");
    }

    /*3*/
    {
        System.out.println("Base Normal Block 1");
    }

    /*3*/
    private String value = Log.baseFieldInit();

    /*3*/
    {
        System.out.println("Base Normal Block 2");
    }

    /*4*/
    Base() {
        System.out.println("Base Constructor");
    }
}
