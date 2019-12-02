package example.patterndesign.singletondesign.notsafeuse;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class NormalSingleTon {


    private static NormalSingleTon normalSingleTon = null;

    private NormalSingleTon() {

    }

    public static NormalSingleTon getInstance() {
        if (normalSingleTon == null) {
            normalSingleTon = new NormalSingleTon();
        }
        return normalSingleTon;
    }


}
