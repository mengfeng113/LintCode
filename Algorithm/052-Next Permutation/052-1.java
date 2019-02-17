public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        
        if (i == -1) {
            reverse(nums, 0, nums.length - 1);
        } else {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) { j--; }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
        }
        return nums;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int s, int e) {
        while (s < e) {
            swap(nums, s, e);
            s++;
            e--;
        }
    }
}