package com.kandy.algorithm.week09.homework;

public class LC58最后一个单词的长度 {
    //反向遍历
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }

    //字符串split
    public int lengthOfLastWord2(String s) {
        String[] strings = s.split(" ");
        return strings[strings.length - 1].length();
    }
}
