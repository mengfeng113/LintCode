public class Solution {
    /**
     * @param A: an array
     * @return: total of reverse pairs
     */
    int[] A = null;
    int[] C = null;
    
    public long reversePairs(int[] nums) {
        A = preProcess(nums);
        
        int max_value = 0;
        for (int n : A) { 
            // System.out.print(n + ", ");
            max_value = Math.max(max_value, n);
        }
        
        C = new int[A.length + 1];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            res += query(max_value) - query(A[i]); 
            add(A[i], 1);
        }
        return res;
    }
    
    private void add(int x, int val) {
        x++;
        for (int i = x; i < C.length; i += lowbit(i)) {
            C[i] += val;
        }
    }
    
    private int query(int x) {
        x++;
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res += C[i];
        }
        return res;
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    private int[] preProcess(int[] A) {
        
        int[] AA = A.clone();
        Arrays.sort(AA);
        Map<Integer, Integer> map = new HashMap();
        int next = 0;
        
        for (int n : AA) {
            if (!map.containsKey(n)) {
                map.put(n, next++);
            }
        }
        
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = map.get(A[i]);
        }
        return res;
    }
}