package cn.edu.ruc.iir.xspace.leetcode.bfs;

import java.util.*;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.leetcode.bfs
 * @ClassName: BFS_126
 * @Description: Reference : https://www.cnblogs.com/splash/p/4102786.html
 * @author: taoyouxian
 * @date: Create in 2019-03-27 23:15
 **/
public class BFS_126 {
    public static void main(String[] args) {

    }

    class Solution {

        Map<String, List<String>> nodeSet = new HashMap<String, List<String>>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord))
                return new ArrayList<List<String>>();
            Set<String> dict = new HashSet<String>(wordList);
            if (dict.contains(beginWord))
                dict.remove(beginWord);

            List<List<String>> res = new ArrayList<List<String>>();
            Queue<String> queue = new LinkedList<String>();
            HashSet<String> hs = new HashSet<String>();
            HashMap<String, Integer> dist = new HashMap<String, Integer>();
            queue.add(beginWord);
            nodeSet.put(beginWord, new ArrayList<String>());
            nodeSet.put(endWord, new ArrayList<String>());
            dist.put(beginWord, 1);
            while (!queue.isEmpty()) {
                String temp = queue.poll();
                int l = dist.get(temp);

                for (int i = 0; i < temp.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (temp.charAt(i) == c) continue;

                        StringBuilder sb = new StringBuilder(temp);
                        sb.setCharAt(i, c);
                        String next = sb.toString();
                        if (next.equals(endWord)) {
                            if (!dist.containsKey(endWord)) {
                                dist.put(endWord, l + 1);
                                nodeSet.get(endWord).add(temp);
                            } else if (dist.get(endWord) == l + 1) {
                                nodeSet.get(endWord).add(temp);
                            }
                        } else if (dict.contains(next) && !hs.contains(next)) {
                            if (!dist.containsKey(next)) {
                                queue.add(next);
                                dist.put(next, l + 1);
                                List<String> list = new ArrayList<>();
                                list.add(temp);
                                nodeSet.put(next, list);
                            } else if (dist.get(next) == l + 1) {
                                nodeSet.get(next).add(temp);
                            }
                        }
                    }

                }

            }

            List<String> path = new ArrayList<String>();
            path.add(endWord);
            //System.out.println(nodeSet.toString());

            collect(beginWord, res, path, nodeSet.get(endWord));
            return res;
        }

        private void collect(String beginWord, List<List<String>> res, List<String> path, List<String> prevNodes) {
            //System.out.println(path.toString());
            for (int i = 0; i < prevNodes.size(); i++) {
                String val = prevNodes.get(i);
                path.add(0, val);
                if (val.equals(beginWord)) {
                    List<String> list = new ArrayList<>(path);
                    res.add(list);
                } else {
                    collect(beginWord, res, path, nodeSet.get(val));
                }
                path.remove(0);
            }
        }
    }
}
