package com.example.server;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */

public class LocalTest {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(0);
        System.out.println(LocalTest.isPalindrome(listNode));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode temp = head;
        ListNode node = reverse(temp);
        while (head != null) {
            if (node.val != head.val || node == null) {
                return false;
            }
            head = head.next;
            node = node.next;
        }
        if (node != null) {
            return false;
        }
        return true;
    }

    public static ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode head = node.next;
        ListNode next = node.next.next;
        head.next = node;
        node.next = reverse(next);
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
