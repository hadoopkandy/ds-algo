package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/group-anagrams/description/
 * 49. 字母异位词分组
 * 对字符串进行分组，其实就是进行Hash
 * 让同一组的字符串具有相同的Hash函数值,不同组的字符串具有不同的Hash函数值
 * 然后就可以用hash map分组了
 * 方案一：把每个字符串中的字母排序,排序后的串作为hash map的key map<string,string>
 * 方案二: 统计每个字符串中各个字母出现次数,把长度26的计数数组作为key
 * map<array<26,int>,group>(C++ std::array,Python tuple)
 *
 */
public class L03_C49字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
/*
{
    "aet": ["eat", "tea", "ate"],
    "ant": ["tan", "nat"],
    "abt": ["bat"]
}
*/
        //将字符串排序之后分组
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
