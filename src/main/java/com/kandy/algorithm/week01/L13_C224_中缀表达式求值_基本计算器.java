package com.kandy.algorithm.week01;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/basic-calculator/description/
 * 224. 基本计算器
 * 把中缀表达式转换为后缀表达式
 * 建立一个用于存运算符的栈,逐一扫描中缀表达式中的元素。
 *  如果遇到一个数，输出该数。
 *  如果遇到左括号,把左括号入栈。
 *  如果遇到右括号,不断取出栈顶并输出,直到栈顶为左括号,然后把左括号出栈。
 *  如果遇到运算符,只要栈顶符号的优先级>=新符号,就不断取出栈顶并输出，最后把新符号进栈。优先级顺序为乘除号>加减号>左括号
 *
 *  1.后缀表达式求值比较简单
 *  2. 中缀表达式转为后缀表达式（需要用到2个栈:运算符栈 后缀表达式）
 *  3. 支持优先级  乘除优先级高于加减
 *  4. 支持括号   遇到右括号找到最近相关的左括号
 *  5. 为了支持负号 采用 补0的思路： a. 加减乘除和左括号后面跟着加减号时需要补0    b. 数字或右括号后面跟加减号时
 */
public class L13_C224_中缀表达式求值_基本计算器 {

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
        L13_C224_中缀表达式求值_基本计算器 code = new L13_C224_中缀表达式求值_基本计算器();
        System.out.println(code.calculate("3+2*2"));
    }
}
