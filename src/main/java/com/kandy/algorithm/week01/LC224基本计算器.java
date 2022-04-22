package com.kandy.algorithm.week01;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 224. 基本计算器
 */
public class LC224基本计算器 {

    public int calculate(String s) {
        Stack<Character> ops = new Stack<>();
        List<String> tokens = new ArrayList<>();

        s += " ";
        String number = "";

        //补零 当+-出现在左括号或者加减乘除后面时当正负号处理
        boolean needsZero = true;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                number += ch;
                needsZero = false;
                continue;
            } else {
                if (!number.isEmpty()) {
                    tokens.add(number);
                    number = "";
                }
            }
            if (ch == ' ') continue;
            if(ch == '('){
                ops.push(ch);
                needsZero = true;
                continue;
            }

            if(ch == ')'){
                while(ops.peek() !='('){
                    tokens.add(String.valueOf(ops.peek()));
                    ops.pop();
                }
                ops.pop();//把左括号出栈，后缀是不需要括号
                needsZero = false;
                continue;
            }
            if((ch == '+' || ch =='-') && needsZero){
                tokens.add("0");
            }

            int currRank = getRank(ch);
            while (!ops.empty() && getRank(ops.peek()) >= currRank) {
                tokens.add(String.valueOf(ops.peek()));//构造一个包含一个字符的字符串
                ops.pop();
            }
            ops.push(ch);
            needsZero =true;
        }
        while (!ops.empty()) {
            tokens.add(String.valueOf(ops.peek()));
            ops.pop();
        }
        return evalRPN(tokens.toArray(new String[0]));
    }

    int getRank(char ch) {
        if (ch == '*' || ch == '/') return 2;
        if (ch == '+' || ch == '-') return 1;
        return 0;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int y = s.peek();
                s.pop();

                int x = s.peek();
                s.pop();
                int z = calc(x, y, token);
                s.push(z);
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        return s.peek();
    }

    int calc(int x, int y, String op) {
        if (op.equals("+")) return x + y;
        if (op.equals("-")) return x - y;
        if (op.equals("*")) return x * y;
        if (op.equals("/")) return x / y;
        return 0;
    }

    public static void main(String[] args) {
        LC224基本计算器 code = new LC224基本计算器();
        System.out.println(code.calculate("3+2*2"));
    }
}
