package example.pattern.abstractfactory;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class JavaFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
