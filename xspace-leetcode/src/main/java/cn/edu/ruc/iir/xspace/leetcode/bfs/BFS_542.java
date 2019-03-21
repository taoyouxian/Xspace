package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_542
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-20 10:22
 **/
public class BFS_542 {
    public static void main(String[] args) {

    }

    class Solution {
        public int[][] updateMatrix(int[][] matrix) {
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            Queue<Point> queue = new LinkedList<>();

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        Point p = new Point(i, j);
                        queue.add(p);
                    } else {
                        matrix[i][j] = -1;
                    }
                }
            }

            while (!queue.isEmpty()) {
                Point t = queue.poll();
                int xPeek = t.getX();
                int yPeek = t.getY();

                for (int k = 0; k < 4; k++) {
                    int xx = xPeek + direction[k][0];
                    int yy = yPeek + direction[k][1];
                    if (isValid(xx, yy, matrix) && matrix[xx][yy] == -1) {
                        queue.add(new Point(xx, yy));
                        matrix[xx][yy] = matrix[xPeek][yPeek] + 1;
                    }
                }
            }

            return matrix;
        }

        private boolean isValid(int xx, int yy, int[][] grid) {
            return xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length;
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
    }
}
