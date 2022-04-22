package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 30. 串联所有单词的子串
 */
public class LC30串联所有单词的子串 {
    /**
     * O(length of s * total length of words )
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        wordsMap = new HashMap<>();
        int tot = 0; // words单词总长度
        for (String word : words) {
            tot += word.length();
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i + tot <= s.length(); i++) {
            // 看：s的从下标i开始的tot个字符构成的子串
            // 是不是由 words里的单词 按某种顺序串联形成的
            if (valid(s.substring(i, i + tot), words)) {
                ans.add(i);
            }
        }
        return ans;
    }


    boolean valid(String str, String[] words) {
        // 设k=words每个单词的长度
        // 把子串str每k个字符分成一个单词
        // 一共形成一个单词数组，看跟words是否一样即可
        int k = words[0].length();
        HashMap<String, Integer> splitWordsMap = new HashMap();
        for (int i = 0; i < str.length(); i += k) {
            String key = str.substring(i, i + k);
            splitWordsMap.put(key, splitWordsMap.getOrDefault(key, 0) + 1);
        }
        return splitWordsMap.equals(wordsMap);
    }

    /*
      "barfoobar" =>["bar","foo","bar"] => {"bar":2,"foo":1}
      ["foo","bar","bar"] => {"foo":1,"bar":2}
     */
/*
first:0 s_map:{bar=1, foo=1}
the++ bar--
foo++ foo--
bar++ the--
man++ foo--
bar--
first:1 s_map:{arf=1, oot=1}
hef++ arf--
oob++ oot--
arm++ hef--
oob--
first:2 s_map:{oth=1, rfo=1}
efo++ rfo--
oba++ oth--
rma++ efo--
oba--
*/
    /**
     * 优化解法
     * O(length of s * length of one word )
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        wordsMap = new HashMap<>();

        int tot = 0; // words单词总长度
        for (String word : words) {
            tot += word.length();
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        int n = s.length();
        int m = words[0].length();

        List<Integer> ans = new ArrayList<>();
        for (int first = 0; first < m; first++) {
            if (first + tot > n) break;
            HashMap<String, Integer> s_map = new HashMap<>();

            int curr = first;
            for (int i = 0; i < words.length; i++) {

                //s_map[s.substr(curr, m)]++;
                String key = s.substring(curr, curr + m);
                s_map.put(key, s_map.getOrDefault(key, 0) + 1);

                curr += m;
            }
            for (int start = first, end = curr; start + tot <= n; start += m, end += m) {
                if (s_map.equals(wordsMap)) ans.add(start);

                //s_map[s.substr(end, m)]++;
                if (end + m <= n) {
                    String key = s.substring(end, end + m);
                    s_map.put(key, s_map.getOrDefault(key, 0) + 1);
                }

                //s_map[s.substr(start, m)]--;
                String key = s.substring(start, start + m);
                if (s_map.containsKey(key)) {
                    Integer value = s_map.get(key);
                    if (value == 1) {
                        s_map.remove(key);
                    } else {
                        s_map.put(key, value - 1);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC30串联所有单词的子串 code = new LC30串联所有单词的子串();
        System.out.println(code.findSubstring2("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(code.findSubstring2("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }

    HashMap<String, Integer> wordsMap;
}
