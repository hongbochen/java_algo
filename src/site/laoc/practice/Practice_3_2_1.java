package site.laoc.practice;

import java.util.LinkedList;

public class Practice_3_2_1<T> {

    LinkedList ll = new LinkedList();

    private class Node<T>{
        T data;
        Node<T> next;

        public Node(T data,Node<T> n){
            this.data = data;
            this.next = n;
        }
    }

    private int theSize;
    private
}
