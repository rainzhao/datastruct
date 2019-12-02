package example.classinitializeorder;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
class Log {
    public static String baseFieldInit() {
        System.out.println("Base Normal Field");
        return "";
    }

    public static String baseStaticFieldInit() {
        System.out.println("Base Static Field");
        return "";
    }

    public static String fieldInit() {
        System.out.println("Sub Normal Field");
        return "";
    }

    public static String staticFieldInit(String type) {
        System.out.println(type + " Static Field");
        return "";
    }
}
