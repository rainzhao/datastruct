package example.patterndesign.singletondesign.notsafeuse;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class SingleTonExpOne {

    private static SingleTonExpOne singleTonExpOne = null;

    private SingleTonExpOne() {

    }

    /**
     * 如下这种方式还是会有问题，synchronized虽然会保证线程的逐个执行，但是多个线程进入 == null 判断时会出现多个线程同时通过这层判断，
     * 导致对象还是会被初始化多次
     * @return
     */
    public static SingleTonExpOne getInstance() {
        if (singleTonExpOne == null) {
            synchronized (SingleTonExpOne.class) {
                singleTonExpOne = new SingleTonExpOne();
            }
        }
        return singleTonExpOne;
    }

}
