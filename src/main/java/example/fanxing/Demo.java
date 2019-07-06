package example.fanxing;

/**
 * @author zhaoyu
 * @date 2019-04-11
 */
public class Demo {

    /**
     * 泛型上界限定为某个接口  <T extends Comparable<T>>
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> T max(T[] arr){
        T max = arr[0];
        for(int i=1; i<arr.length; i++){
            if(arr[i].compareTo(max)>0){
                max = arr[i];
            }
        }
        return max;
    }

    class Data implements Comparable<Data> {

        private Integer price;

        public Data(Integer price) {
            this.price = price;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Override
        public int compareTo(Data o) {
            return this.price - o.getPrice();
        }
    }

    public static void main(String[] args) {
        Data[] arr = new Data[4];
        Demo demo = new Demo();
        arr[0] = demo.new Data(1);
        arr[1] = demo.new Data(2);
        arr[2] = demo.new Data(3);
        arr[3] = demo.new Data(4);

        Data max = max(arr);
        System.out.println(max.getPrice());
    }
}
