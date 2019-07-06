/**
 * @author zhaoyu
 * @date 2019-01-23
 */
public enum TestEnum {
    DATA("data");

    private String data;

    TestEnum(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
