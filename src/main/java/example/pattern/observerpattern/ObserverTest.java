package example.pattern.observerpattern;

import org.junit.Test;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class ObserverTest {

    @Test
    public void observerTest() {

        Subject subject = new Subject();
        new FirstObserver(subject);
        new SecondObserver(subject);
        subject.setState(10);
        System.out.println("do something ...");
        subject.setState(20);

    }

}
