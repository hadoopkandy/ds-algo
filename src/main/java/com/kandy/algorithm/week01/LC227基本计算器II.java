package com.kandy.algorithm.week01;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 227. 基本计算器 II
 * 3+2*2
 * 运算符栈：+ *
 * 后缀表达式：3 2 2 * +
 *
 * 3 + 2 -2
 * 运算符栈:
 * 后缀表达式: 3 2 + 2 -
 *
 */
public class LC227基本计算器II {

    public int calculate(String s) {
        //运算符
        Stack<Character> ops = new Stack<>();
        //后缀表达式
        List<String> tokens = new ArrayList<>();

        s += " ";//保证累积的数字能放到tokens里
        String number = ""; //多个数字组成一个整数
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                number += ch;
                continue;
            } else {
                //number是一个数值
                if (!number.isEmpty()) {
                    tokens.add(number);
                    number = "";
                }
            }
            // 忽略空格
            if (ch == ' ') continue;
            int currRank = getRank(ch);
            //栈顶运算符的优先级比当前高，应该先算，级别一样，先算栈顶，再算当前运算符
            while (!ops.empty() && getRank(ops.peek()) >= currRank) {
                tokens.add(String.valueOf(ops.peek()));//构造一个包含一个字符的字符串
                ops.pop();
            }
            ops.push(ch);
        }
        while (!ops.empty()) {
            tokens.add(String.valueOf(ops.peek()));
            ops.pop();
        }
        return evalRPN(tokens.toArray(new String[0]));
    }

    //乘除的优化级比加减高
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
        LC227基本计算器II code = new LC227基本计算器II();
        System.out.println(code.calculate("3+2*2"));
    }
}
