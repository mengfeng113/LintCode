public class Solution {
    /**
     * @param A: an array
     * @return: total of reverse pairs
     */
    // use merge sort
    // standard merge sort only sort array
    // but in this problem, in the merge step, we can count reverse pairs
    public long reversePairs(int[] A) {
        return mergeSort(A, 0, A.length - 1);
    }
    
    private int mergeSort(int[] A, int s, int e) {
        if (s >= e) {
            return 0;
        }
        
        int m = (s + e) / 2;
        int res = 0;
        res += mergeSort(A, s, m);
        res += mergeSort(A, m + 1, e);
        res += merge(A, s, m + 1, e);
        return res;
    }
    
    private int merge(int[] A, int s1, int s2, int e) {
        int[] sorted = new int[e - s1 + 1];
        int res = 0;
        for (int i = 0, i1 = s1, i2 = s2; i < sorted.length; i++) {
            if (i1 < s2 && i2 <= e) {
                if (A[i1] <= A[i2]) {
                    sorted[i] = A[i1++];
                }
                else {
                    res += s2 - i1;
                    sorted[i] = A[i2++];
                }
            }
            else if (i1 < s2) {
                sorted[i] = A[i1++];
            }
            else {
                sorted[i] = A[i2++];
            }
        }
        
        for (int i = 0; i < sorted.length; i++) {
            A[s1 + i] = sorted[i];
        }
        
        return res;
    }
}