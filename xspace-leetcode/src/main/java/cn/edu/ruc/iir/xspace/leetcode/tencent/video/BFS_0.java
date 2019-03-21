package cn.edu.ruc.iir.xspace.leetcode.tencent.video;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.tencent.video
 * @ClassName: BFS_0
 * @Description: https://leetcode.com/problems/number-of-islands/
 * @author: tao
 * @date: Create in 2019-03-20 11:26
 **/
public class BFS_0 {
    public static void main(String[] args) {

    }

    class Solution {

        public int[] numIslands(char[][] grid) {
            List<Integer> list = new ArrayList<>();
            int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            Queue<Point> queue = new LinkedList<>();
            int ans = 0;

            int num = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        //System.out.println(i + "," + j);
                        Point p = new Point(i, j);
                        queue.add(p);
                        num++;
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
                                    num++;
                                    grid[xx][yy] = '0';
                                }
                            }
                        }
                        list.add(num); // 返回每个区域的个数
                        num = 0;
                        ans++; // 代表有几个区域
                    }
                }
            }
            int[] nums = new int[list.size()];
            int k = 0;
            for (int i : list) {
                nums[k++] = i;
            }
            return nums;
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

        private boolean isValid(int xx, int yy, char[][] grid) {
            return xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length;
        }
    }

}
