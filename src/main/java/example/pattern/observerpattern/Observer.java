package example.pattern.observerpattern;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
