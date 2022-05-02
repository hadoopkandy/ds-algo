package com.kandy.algorithm.campus.vip;

/*
   	克隆的实现需要一下几步：
	在派生类中覆盖基类的clone()方法，并声明为public。
	在派生类的clone()方法中，调用super.clone()。
	在派生类中实现Cloneable接口。Cloneable接口没有任何抽象的方法，这样的成为标识接口。实现这个接口，只是为了告诉编译器这个对象可以被克隆了。
 */
class Test {
    static void myMethod(Point pt1) {
        pt1.x = 23;
        System.out.println("x=" + pt1.x);
    }

    public static void main(String[] args) {
        Point pt = new Point(2, 4);
        System.out.println("x=" + pt.x);
        Point pt2 = (Point) pt.clone();
        myMethod(pt2);
        System.out.println("x=" + pt.x);
    }
}

class Point implements Cloneable {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Object clone() {
        Point p = null;
        try {
            p = (Point) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
