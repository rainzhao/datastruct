package example.pattern.abstractfactory;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class Test {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();

        video.produce();
        article.produce();
    }
}
