class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);

        backtrack(0, nums, used, new ArrayList(), res);
        return res;
    }
    
    private void backtrack(int start, int[] nums, boolean[] used, List<Integer> tmp, List<List<Integer>> res){
        if (start == nums.length) {
            res.add(new ArrayList<Integer>(tmp));
            return;
        }
    
        // option 1: NOT use element nums[start]
        backtrack(start + 1, nums, used, tmp, res);
        // option 2: use element nums[start]
        if(!(start > 0 && nums[start] == nums[start - 1] && !used[start - 1])){
            tmp.add(nums[start]);
            used[start] = true;
            backtrack(start + 1, nums, used, tmp, res);
            tmp.remove(tmp.size() - 1);
            used[start] = false;
        }
    
    }
}