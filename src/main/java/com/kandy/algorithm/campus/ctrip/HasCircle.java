package com.kandy.algorithm.campus.ctrip;
public class HasCircle {
	public static boolean hasCircle(LNode L)
	{
		if(L==null) return false;//单链表为空时，单链表没有环
		if(L.next==null) return false;//单链表中只有头结点，而且头结点的next为空，单链表没有环
		LNode p=L.next;//p表示从头结点开始每次往后走一步的指针
		LNode q=L.next.next;//q表示从头结点开始每次往后走两步的指针
		while(q!=null) //q不为空执行while循环
		{
			if(p==q) return true;//p与q相等，单链表有环
			p=p.next;
			q=q.next.next;
		}	
		return false;
	}

	public static void main(String[] args) {
		LNode node1 =new LNode();
		LNode node2 =new LNode();
		LNode node3 =new LNode();
		LNode node4 =new LNode();
		node1.setNext(node1);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node1);
		System.out.println(hasCircle(node1));
	}

}
//单链表的结点类
class LNode{
	//为了简化访问单链表,结点中的数据项的访问权限都设为public
	public int data;
	public LNode next;
	public void setNext(LNode next) {
		this.next = next;
	} 
}