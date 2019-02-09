public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
     
    /*
     二分法，在result range中搜索，利用left + 1 < right 的二分法模板
    */
    class Result{
        int count;
        boolean exist;
        
        Result(int count, boolean exist) {
            this.exist = exist;
            this.count = count;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int l = matrix[0][0];
        int r = matrix[m - 1][n - 1];
        Result res = null;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            res = helper(matrix, mid);
            
            if (res.exist && res.count == k) {
                return mid;
            }
            
            if (res.count < k) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        
        res = helper(matrix, l);
        return (res.exist && res.count == k) ? l : r;
    }
    
    private Result helper(int[][] matrix, int num) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        boolean exist = false;
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == num)
                exist = true;
            if (matrix[i][j] <= num) {
                count += i + 1;
                j++;
            }
            else {
                i--;
            }
        }
        
        return new Result(count, exist);
    }
}