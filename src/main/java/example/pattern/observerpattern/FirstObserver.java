package example.pattern.observerpattern;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class FirstObserver extends Observer {

    public FirstObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("one observer:" + subject.getState());
    }


}
