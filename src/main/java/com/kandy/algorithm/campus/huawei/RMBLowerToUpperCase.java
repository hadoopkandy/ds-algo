package com.kandy.algorithm.campus.huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RMBLowerToUpperCase {

    /**
     * 人民币大小写转换
     */
    public static String[] number = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    public static String[] units = new String[]{"元", "拾", "佰", "仟"};

    public static String change(String smoney) {
        StringBuffer sb = new StringBuffer();

        //小数点的位置
        int index = smoney.indexOf(".");

        //biggerstr用于存放存有数字和单位的字符
        StringBuffer biggerstr = new StringBuffer();

        //处理小数部分
        biggerstr.append("分");
        biggerstr.append(number[Integer.parseInt(smoney.charAt(index + 2) + "")]);
        biggerstr.append("角");
        biggerstr.append(number[Integer.parseInt(smoney.charAt(index + 1) + "")]);


        //处理整数部分
        for (int i = 0; i < index; i++) {
            sb.append(number[Integer.parseInt(smoney.charAt(i) + "")]);
        }
        sb = sb.reverse();
        String insertstr = sb.toString();//玖捌柒陆伍肆叁贰壹
        for (int i = 0; i < insertstr.length(); i++) {
            //插入单位
            if (i == 0) {
                biggerstr.append("元");
            } else if ((i + 4) % 4 == 0 && i != 8) {
                biggerstr.append("万");
            } else if (i % 8 == 0) {
                biggerstr.append("亿");
            } else
                biggerstr.append(units[i % 4]);
            //插入数字
            biggerstr.append(insertstr.charAt(i));
        }

        //instead_zero字符串用于存放清除零之后的
        String instead_zero = biggerstr.reverse().toString();
        instead_zero = instead_zero.replaceAll("零分", "零");
        instead_zero = instead_zero.replaceAll("零角", "零");
        instead_zero = instead_zero.replaceAll("零佰", "零");
        instead_zero = instead_zero.replaceAll("零仟", "零");
        instead_zero = instead_zero.replaceAll("零拾", "零");

        //把相连的零去掉 比如输入1000000.12时,instead_zero=壹佰零零万零零零零元壹角贰分
        instead_zero = instead_zero.replaceAll("[零]+", "零");
        instead_zero = instead_zero.replaceAll("零元", "元");
        instead_zero = instead_zero.replaceAll("零万", "万");
        instead_zero = instead_zero.replaceAll("零亿", "亿");


        //把字符串中的最后一个零去掉,例如输入金额为123.00(壹佰贰拾叁元零)
        if (instead_zero.charAt(instead_zero.length() - 1) == '零') {
            instead_zero = instead_zero.substring(0, instead_zero.length() - 1);
        }


        //当用户输入金额是0时
        if (instead_zero.charAt(0) == '元' && "元".equals(instead_zero)) {
            return "零" + instead_zero;
        }
        //当用户输入金额是0.XX时
        if (instead_zero.charAt(0) == '元' && !"元".equals(instead_zero)) {
            return instead_zero.substring(1);
        }
        return instead_zero;

    }

    /**
     * 判断用户输入的金额是否合法(100,1234.5,1234.56,0.12,0为合法数字)
     *
     * @param s
     * @return
     */
    public static boolean isMoney(String s) {
        Pattern p = Pattern.compile("^-?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /*
     * 调整用户输入金额的格式，使得输入金额的格式为:XXXX.XX(小数点后保留2位)
     */
    public static String formatInput(String input) {
        String newInput = "";
        //没有小数点时，补充小数点后2位
        if (!input.contains(".")) {
            newInput = input + ".00";
            return newInput;
        }

        String subStr = input.substring(input.indexOf("."));
        //小数点后面有1位
        if (subStr.length() == 2) {
            newInput = input + "0";
            return newInput;
        }

        return input;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("请输入金额:(xxx或xxx.x或xxx.xx或0.xx或0.x)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            boolean isMoney = isMoney(input);
            if (!isMoney) {
                System.err.println("输入数字非法(含有非法字符)!");
                return;
            }
            input = formatInput(input);
            if (input.contains("-")) {
                System.err.println("负" + change(input.substring(1)));
            } else {
                System.out.println(change(input));
            }
        }

    }

}
