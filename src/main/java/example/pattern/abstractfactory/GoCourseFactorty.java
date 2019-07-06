package example.pattern.abstractfactory;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class GoCourseFactorty implements CourseFactory{
    @Override
    public Video getVideo() {
        return new GoVideo();
    }

    @Override
    public Article getArticle() {
        return new GoArticle();
    }
}
