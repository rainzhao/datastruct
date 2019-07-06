package example.pattern.observerpattern;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class SecondObserver extends Observer {

    public SecondObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("second observer:" + subject.getState());
    }
}
