package example.transform;

/**
 * @author zhaoyu
 * @date 2019-01-29
 */
@FunctionalInterface
public interface Transformer<T>  {
    T transform(T input);
}
