package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_130
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-20 09:50
 **/
public class BFS_130 {
    public static void main(String[] args) {

    }

    class Solution {
        public void solve(char[][] board) {
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            Queue<Point> queue = new LinkedList<>();

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if ((i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) && board[i][j] == 'O') {
                        Point p = new Point(i, j);
                        queue.add(p);
                        board[i][j] = 'Y';

                        while (!queue.isEmpty()) {
                            Point t = queue.poll();
                            int xPeek = t.getX();
                            int yPeek = t.getY();

                            for (int k = 0; k < 4; k++) {
                                int xx = xPeek + direction[k][0];
                                int yy = yPeek + direction[k][1];
                                if (isValid(xx, yy, board) && board[xx][yy] == 'O') {
                                    queue.add(new Point(xx, yy));
                                    board[xx][yy] = 'Y';
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'Y')
                        board[i][j] = 'O';
                    else
                        board[i][j] = 'X';
                }
            }

        }

        private boolean isValid(int xx, int yy, char[][] grid) {
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

