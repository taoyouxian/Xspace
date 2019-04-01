package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_752
 * @Description:
 * @author: tao
 * @date: Create in 2019-04-01 20:09
 **/
public class BFS_752 {

    class Solution {
        public int openLock(String[] deadends, String target) {
            Queue<String> queue = new LinkedList<>();
            queue.add("0000");
            Set<String> used = new HashSet<>();
            for (String d : deadends) {
                if (d.equals("0000"))
                    return -1;
                used.add(d);
            }
            int ans = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    String cur = queue.poll();
                    if (cur.equals(target))
                        return ans;
                    String[] newStr = getNext(cur);
                    for (int i = 0; i < 8; i++) {
                        if (!used.contains(newStr[i])) {
                            queue.add(newStr[i]);
                            used.add(newStr[i]);
                        }
                    }
                }
                ans++;
            }
            return ans;
        }

        private String[] getNext(String cur) {
            String[] res = new String[8];
            for (int i = 0; i < 4; i++) {
                res[2 * i] = cur.substring(0, i) + ((cur.charAt(i) - '0' + 1) % 10) + cur.substring(i + 1, 4);
                res[2 * i + 1] = cur.substring(0, i) + ((cur.charAt(i) - '0' + 9) % 10) + cur.substring(i + 1, 4);
            }
            return res;
        }

    }
}
