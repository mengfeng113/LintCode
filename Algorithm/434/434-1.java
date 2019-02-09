/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    
    int M;
    int N;
    UnionFind uf = null;
    
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        this.M = n;
        this.N = m;
        
        uf = new UnionFind(m * n);
        List<Integer> res = new LinkedList<>();
        
        if (operators ==  null || operators.length == 0) return res;
        
        for (Point op : operators) {
            helper(op);
            res.add(uf.getCount());
        }
        return res;
    }
    
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    
    private void helper(Point op) {
        int x = op.x;
        int y = op.y;
        int id = getId(x, y, N);
        uf.union(id, id);
        
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nId = getId(nx, ny, N);
            if (isInside(nx, ny, M, N) && uf.isLand(nId)) {
                uf.union(id, nId);
            }
        }
    }
    
    private int getId(int row, int col, int elementPerColumn) {
        return row * elementPerColumn + col;
    }
    
    private boolean isInside(int r, int c, int R, int C) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
    
    class UnionFind {
        int[] father;
        int count;
        
        UnionFind(int size){
            father = new int[size];
            Arrays.fill(father, -1);
            count = 0;
        }
        
        public int getCount() {
            return count;
        }
        
        public boolean isLand(int id) {
            return father[id] != -1;
        }
        
        public void union(int a, int b) {
            if (a == b && father[a] == -1) {
                count++;
            }
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                count--;
            }
        }
        
        public int find(int id) {
            if (father[id] == -1) {
                return father[id] = id;
            }
            
            if (father[id] == id) {
                return id;
            }
            
            return father[id] = find(father[id]);
        }
    }
}