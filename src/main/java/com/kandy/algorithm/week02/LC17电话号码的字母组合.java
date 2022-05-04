package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17电话号码的字母组合 {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        this.digits = digits;
        this.chosen = new StringBuffer();

        combine(combinations, 0);
        return combinations;
    }

    public void combine(List<String> combinations, int index) {
        //直到处理完电话号码中的所有数字
        if (index == digits.length()) {
            combinations.add(chosen.toString());
        } else {
            char digit = digits.charAt(index); //根据index取电话号码的数字
            String letters = phoneMap.get(digit);//从哈希表中获得该数字对应的所有可能的字母
            for (int i = 0; i < letters.length(); i++) {
                chosen.append(letters.charAt(i));
                combine(combinations, index + 1);
                chosen.deleteCharAt(index);
            }
        }
    }

    StringBuffer chosen;
    String digits;
    //哈希表存储每个数字对应的所有可能的字母
    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
}
