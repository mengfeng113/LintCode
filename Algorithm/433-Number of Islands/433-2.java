public class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int R = grid.length;
        int C = grid[0].length;
        UnionFind uf = new UnionFind(R * C);
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!grid[r][c]) continue;
                
                int id = getId(r, c, grid);
                uf.union(id, id);
                
                for (int i = 0; i < dr.length; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (isInside(nr, nc, grid) && grid[nr][nc]){
                        int nId = getId(nr, nc, grid);
                        uf.union(id, nId);
                    }
                }
            }
        }
        
        return uf.getCount();
    }
    
    private int getId(int row, int col, boolean[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        return row * N + col;
    }
    
    private boolean isInside(int r, int c, boolean[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        return r >= 0 && c >= 0 && r < M && c < N;
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
        
        public void union(int a, int b) {
            if (a == b) {
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