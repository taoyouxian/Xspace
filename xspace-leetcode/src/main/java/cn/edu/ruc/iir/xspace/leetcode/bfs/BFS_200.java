package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_200
 * @Description: 2 BFS_0 + 1 DFS
 * @author: tao
 * @date: Create in 2019-03-20 09:05
 **/
public class BFS_200 {
    public static void main(String[] args) {

    }

    class Solution {
        /**
         * two queue
         *
         * @param grid
         * @return
         */
        public int numIslands(char[][] grid) {
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            Queue<Integer> x = new LinkedList<>();
            Queue<Integer> y = new LinkedList<>();
            int ans = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        System.out.println(i + "," + j);
                        x.add(i);
                        y.add(j);
                        grid[i][j] = '0';

                        while (!x.isEmpty()) {
                            int xPeek = x.poll();
                            int yPeek = y.poll();

                            for (int k = 0; k < 4; k++) {
                                int xx = xPeek + direction[k][0];
                                int yy = yPeek + direction[k][1];
                                if (isValid(xx, yy, grid) && grid[xx][yy] == '1') {
                                    x.add(i);
                                    y.add(j);
                                    grid[xx][yy] = '0';
                                }
                            }
                        }
                        ans++;
                    }
                }
            }
            return ans;
        }

        private boolean isValid(int xx, int yy, char[][] grid) {
            return xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length;
        }

        /**
         * Use Point to save (x,y)
         *
         * @param grid
         * @return
         */
        public int numIslands1(char[][] grid) {
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            Queue<Point> queue = new LinkedList<>();
            int ans = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        System.out.println(i + "," + j);
                        Point p = new Point(i, j);
                        queue.add(p);
                        grid[i][j] = '0';

                        while (!queue.isEmpty()) {
                            Point t = queue.poll();
                            int xPeek = t.getX();
                            int yPeek = t.getY();

                            for (int k = 0; k < 4; k++) {
                                int xx = xPeek + direction[k][0];
                                int yy = yPeek + direction[k][1];
                                if (isValid(xx, yy, grid) && grid[xx][yy] == '1') {
                                    queue.add(new Point(xx, yy));
                                    grid[xx][yy] = '0';
                                }
                            }
                        }
                        ans++;
                    }
                }
            }
            return ans;
        }

        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

        }

        /**
         * DFS
         *
         * @param grid
         * @return
         */
        public int numIslands2(char[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        dfsFill(grid, i, j);
                        count++;
                    }
                }
            return count;
        }

        private void dfsFill(char[][] grid, int i, int j) {
            if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                dfsFill(grid, i + 1, j);
                dfsFill(grid, i - 1, j);
                dfsFill(grid, i, j + 1);
                dfsFill(grid, i, j - 1);
            }
        }

    }

}