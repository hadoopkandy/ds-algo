package com.kandy.algorithm.week09.homework;

/**
 * 本地的思路和LC151相似
 */
public class LC557反转字符串中的单词III {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        reverseEachWord(sb);
        return sb.toString();
    }
    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0; //每个单词的开始位置、结束位置

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            //end 停在空格处理，所以单词结束位置是end-1
            //翻转单词
            reverse(sb, start, end - 1);

            // 更新start，去找下一个单词,end位置是空格，所以下一个单词的开始位置是end + 1
            start = end + 1;
            ++end;
        }
    }
    //套用反转字符串模板
    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            left++;
            right--;
        }
    }
}
