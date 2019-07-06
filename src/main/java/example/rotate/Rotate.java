package example.rotate;

import org.junit.Test;

/**
 * @author zhaoyu
 * @date 2019-02-13
 */
public class Rotate {

    /**
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */
    public void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }

        // 从后往前遍历

        /*
        int e = nums.length - 1;
        while (k > 0) {
            int end = nums[e];
            int j = e;
            for (int i = nums.length - 1; i > 0; i--) {
                j--;
                nums[i] = nums[j];
            }
            nums[0] = end;
            k--;
        }*/
        // 从前往后遍历
        while (k > 0) {
            int temp = nums[0];
            nums[0] = nums[nums.length - 1];
            for (int i = 1; i < nums.length; i++) {
                int tempNext = nums[i];
                nums[i] = temp;
                temp = tempNext;
            }
            k--;
        }


    }

    @Test
    public void rotateTest() {
        int[] nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        for (int num : nums) {
            System.out.print(num + " , ");
        }
    }

}
