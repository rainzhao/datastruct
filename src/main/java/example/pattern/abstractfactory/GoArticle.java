package example.pattern.abstractfactory;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class GoArticle extends Article {
    @Override
    public void produce() {
        System.out.println("生产Go手记");
    }
}
