package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_127
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-03-27 0:03
 **/
public class BFS_127 {
    public static void main(String[] args) {

    }

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord))
                return 0;
            Set<String> set = new HashSet<String>(wordList);
            if (set.contains(beginWord))
                set.remove(beginWord);

            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            String str;
            int level = 1; //the start string already count for 1
            int curnum = 1;//the candidate num on current level
            int nextnum = 0;//counter for next level

            while (!queue.isEmpty()) {
                String cur = queue.poll();
                curnum--;

                char[] cc = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++) {
                    char tmp = cc[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == tmp) continue;
                        cc[i] = c;
                        str = new String(cc);

                        if (set.contains(str)) {
                            if (str.equals(endWord)) {
                                return level + 1;
                            }
                            // System.out.println(str);
                            set.remove(str);
                            nextnum++;
                            queue.add(str);
                        }
                    }
                    cc[i] = tmp;
                }
                if (curnum == 0) {
                    curnum = nextnum;
                    nextnum = 0;
                    level++;
                }
            }
            return 0;
        }


        public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord))
                return 0;
            Set<String> set = new HashSet<String>(wordList);
            if (set.contains(beginWord))
                set.remove(beginWord);

            int res = 1;
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            // BFS遍历找到的第一个匹配就是最短转换,空字符串是层与层之间的分隔标志
            queue.add("");
            String str;
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                if (cur != "") {
                    char[] cc = cur.toCharArray();
                    for (int i = 0; i < cur.length(); i++) {
                        char tmp = cc[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == tmp) continue;
                            cc[i] = c;
                            str = new String(cc);

                            if (set.contains(str)) {
                                if (str.equals(endWord)) {
                                    return res + 1;
                                }
                                // System.out.println(str);
                                set.remove(str);
                                queue.add(str);
                            }
                        }
                        cc[i] = tmp;
                    }
                } else if (!queue.isEmpty()) {
                    res++;
                    queue.add("");
                }
            }
            return 0;
        }
    }
}
