package com.kandy.algorithm.week05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 状态：digits的index  选的str
 * 当str作为共享变量时，需要还原现场
 */
public class L07_C17电话号码的字母组合 {
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        alphabet = new HashMap<>();
        ans = new ArrayList<>();
        alphabet.put('2', "abc");
        alphabet.put('3', "def");
        alphabet.put('4', "ghi");
        alphabet.put('5', "jkl");
        alphabet.put('6', "mno");
        alphabet.put('7', "pqrs");
        alphabet.put('8', "tuv");
        alphabet.put('9', "wxyz");
        if (digits.isEmpty()) return ans; //注意当digits 是空串时，返回空的List
        dfs(0, "");
        return ans;
    }

    // str也可以作为共享变量，记得要还原现场
    void dfs(int index, String str) {
        if (index == digits.length()) {
            ans.add(str);
            return;
        }
        for (char ch : alphabet.get(digits.charAt(index)).toCharArray()) {
            dfs(index + 1, str + ch);
        }
    }

    String digits;
    List<String> ans;
    HashMap<Character, String> alphabet;
}
