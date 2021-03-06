package example.patterndesign.singletondesign.safeuse;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class SingleTon {

    private static volatile SingleTon singleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }

}
