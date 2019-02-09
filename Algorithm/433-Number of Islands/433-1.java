public class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        int res = 0;
        if(grid == null || grid.length == 0) { 
            return res;
        }
        
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        for(int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                if(grid[i][j]){
                    bfs(grid, i, j, visited);
                    res++;
                }
            }
        }
        
        return res;
    }
    
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    
    class Location{
        int r, c;
        Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private void bfs(boolean[][] grid, int row, int col, boolean[][] visited){
        Queue<Location> q = new LinkedList();
        q.offer(new Location(row, col));
        visited[row][col] = false;
        while(!q.isEmpty()){
            Location loc = q.poll();
            int r = loc.r;
            int c = loc.c;
            
            for(int i = 0; i < dr.length; i++){
                Location next = new Location(r + dr[i], c + dc[i]);
                if(isInside(next, grid) && !visited[next.r][next.c] && grid[next.r][next.c]){
                    q.offer(next);
                    grid[next.r][next.c] = false;
                }
            }
        }
    }
    
    private boolean isInside(Location loc, boolean[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        return loc.r >= 0 && loc.c >= 0 && loc.r < M && loc.c < N;
    }
}