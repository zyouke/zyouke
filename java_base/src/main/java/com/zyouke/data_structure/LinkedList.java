package com.zyouke.data_structure;

/**
 * @Author: zhoujun
 */
public class LinkedList {
    // 位置
    private int index;
    // 第一个节点
    private Node firstNode;
    // 当前节点
    private Node node;

    /**
     * 将指定元素添加到此列表的末尾（最后一个元素）。
     * @param data
     * @return
     */
    public boolean offer(int data){
        if (firstNode == null){
            node = new Node(data,null);
            firstNode = node;
        }else {
            Node newNode = new Node(data, null);
            node.setNextNode(newNode);
            node = newNode;
        }
        return true;
    }

    /**
     * 获取但不移除此列表的头
     * @return int 数据 data
     */
    public int poll(){
        int result = 0;
        if (firstNode == null ){
            return 0;
        }
        result = firstNode.data;
        // 删除
        firstNode = firstNode.nextNode;
        return result;
    }

    /**
     * 打印
     * @return
     */
    public String print(){
        StringBuffer buffer = new StringBuffer("[");
        Node firstNode = this.firstNode;
        if (firstNode != null){
            buffer.append(firstNode.data);
        }
        Node temp = firstNode;
        while (true){
            Node nextNode = temp.nextNode;
            if (nextNode == null){
                break;
            }
            int data = nextNode.data;
            temp = nextNode;
            buffer.append("-->");
            buffer.append(data);
        }
        buffer.append("]");
        return buffer.toString();
    }



    /**
     * 内部类定义节点
      */
    class Node{

        // 数据
        private int data;
        // 指向下个节点
        private Node nextNode;
        public Node(int data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }


}
