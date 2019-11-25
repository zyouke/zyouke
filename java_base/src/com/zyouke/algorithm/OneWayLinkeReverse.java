package com.zyouke.algorithm;

/**
 * @Author: zhoujun
 */
public class OneWayLinkeReverse {

   static class Node{
        int var;
        Node next;
        Node(int val){
            this.var = val;
        }
    }
    // temp--->1--->2--->3--->4--->5
    // temp--->2--->1--->3--->4--->5
    private static void reverse(Node headNode){
        Node tempNode = new Node(0);
        tempNode.next = headNode;
        Node reverse = tempNode.next.next;
        while (reverse != null){

        }
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        Node headNode = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        headNode.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        reverse(headNode);
    }








}
