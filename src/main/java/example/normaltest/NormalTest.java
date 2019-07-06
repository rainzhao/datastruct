package example.normaltest;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhaoyu
 * @date 2019-02-12
 */
public class NormalTest {

    static String B() {
        System.out.println("B()...");
        return "B";
    }

    static int getData(int i ) {
        return i;
    }

    @Test
    public void orElseTest() {
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // orElse
        System.out.println(optional1.orElse(1000) == 1);// true
        System.out.println(optional2.orElse(1000) == 1000);// true

        System.out.println(optional1.orElse(getData(2)) == 1);

        // orElseGet
        Optional<String> optional3 = Optional.ofNullable("A");
        System.out.println("optional is not null orElse: ");
        optional3.orElse(B());
        System.out.println("optional is not null orElseGet: ");
        optional3.orElseGet(() -> B());

        Optional<String> optional4 = Optional.ofNullable(null);
        System.out.println("optional is null orElseGet: ");
        optional4.orElseGet(() -> B());
        // 总结 orElseGet 只有optional的值为null的时候才会去执行orElseGet中的方法
        // orElse 是当Optional中值为null时会执行optional中的值返回
    }

    class User {
        private String name;

        private Integer age;

        private boolean sex;

        public User() {
        }

        public User(String name, Integer age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }
    }

    @Test
    public void immuTest() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setAge(i);
            user.setName("ceshi" + i);
            user.setSex(i % 2 == 0);
            userList.add(user);
        }

        userList.add(null);

        userList = null;

        List<User> users = Optional.ofNullable(userList)
                .orElse(ImmutableList.of(new User("haha", 21, true)))
                .stream()
                .peek(x -> {

                })
                .collect(Collectors.toList());
        System.out.println(users);

    }


    @Test
    public void pairTest() {
        Pair<Integer, String> pair = new MutablePair<>(3, "Three");
        pair.setValue("New Three");

        System.out.println(pair);
    }

    public int removeDuplicatesOne(int[] nums) {
        int len = nums.length;
        if (nums.length <= 1) {
            return nums.length;
        }

        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                break;
            }
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == nums[j]) {
                    nums[j] = Integer.MIN_VALUE;
                } else {
                    break;
                }
            }
        }

        int newLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MIN_VALUE) {
                newLen++;
            }
        }
        int[] newNums = new int[newLen];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MIN_VALUE) {
                newNums[index] = nums[i];
                index++;
            }
        }

        for (int i = 0; i < newNums.length; i++) {
            nums[i] = newNums[i];
        }

        return newLen;
    }

    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums.length;
        }
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[++j] = nums[i];
            }
        }
        return j;
    }

    @Test
    public void removeDuplicateTest() {
        int[] nums = new int[]{1, 1, 2};
        int i = removeDuplicates(nums);
        for (int num : nums) {
            System.out.println(num);
        }

        System.out.println("length: " + i);
    }
}
