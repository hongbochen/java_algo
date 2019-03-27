package site.laoc.queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<T> {

    private class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        public Node(T x,Node<T> p, Node<T> n){
            data = x;
            pre = p;
            next = n;
        }
    }

    private int theSize;
    private Node<T> firstNode;
    private Node<T> endNode;

    public LinkedListQueue(){
        doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void add(T x){
        Node<T> nNode = new Node<>(x,firstNode,firstNode.next);
        firstNode.next.pre = nNode;
        firstNode.next = nNode;

        theSize++;
    }

    public T remove(){
        if(size() == 0)
           throw new NoSuchElementException();

        Node<T> node = endNode.pre;

        node.pre.next = endNode;
        endNode.pre = node.pre;

        theSize--;
        return node.data;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        firstNode = new Node<>(null,null,null);
        endNode = new Node<>(null,firstNode,null);

        firstNode.next = endNode;

        theSize = 0;
    }
}
