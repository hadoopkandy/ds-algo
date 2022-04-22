package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 49. 字母异位词分组
 */
public class LC49字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
/*
{
    "aet": ["eat", "tea", "ate"],
    "ant": ["tan", "nat"],
    "abt": ["bat"]
}
*/
        HashMap<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            //转成char数组，排序分组
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String copy = new String(arr);
            if (!groups.containsKey(copy)) {
                groups.put(copy, new ArrayList<>());
            }
            groups.get(copy).add(s);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> group : groups.values()) {
            ans.add(group);
        }
        return ans;
    }
}
