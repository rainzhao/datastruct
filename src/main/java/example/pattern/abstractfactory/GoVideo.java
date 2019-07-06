package example.pattern.abstractfactory;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class GoVideo extends  Video {
    @Override
    public void produce() {
        System.out.println("生产go视频");
    }
}
