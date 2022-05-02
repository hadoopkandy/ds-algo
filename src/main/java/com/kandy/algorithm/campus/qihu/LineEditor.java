package com.kandy.algorithm.campus.qihu;

import java.util.Scanner;

/**
 * 行编辑器
 * 3
 * whli##ilr#e(s#*s)
 * outcha@putchar(*s=#++)
 * returnWA##A!!##C
 */
public class LineEditor {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int counts = cin.nextInt();
        String[] inputs = new String[counts];
        for (int i = 0; i < counts; i++) {
            inputs[i] = cin.next();
        }
        for (int i = 0; i < counts; i++) {
            System.out.println(lineEditor(inputs[i]));
        }

    }

    public static String lineEditor(String line) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
                count1++;
            }
            if (line.charAt(i) == '@') {
                count2++;
            }
        }
        for (int i = 0; i < count1; i++) {
            String str = line.substring(line.indexOf('#') - 1, line.indexOf('#') + 1);
            line = line.replace(str, "");
        }
        for (int i = 0; i < count2; i++) {
            String str = line.substring(0, line.indexOf('@') + 1);
            line = line.replace(str, "");
        }
        return line;
    }

}
