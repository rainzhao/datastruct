package example.reflect;

/**
 * @author zhaoyu
 * @date 2019-01-23
 */
public class Book {
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getTestName(String name) {
        return name + this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
