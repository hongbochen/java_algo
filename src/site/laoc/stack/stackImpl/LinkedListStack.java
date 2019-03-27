package site.laoc.stack.stackImpl;

import java.util.NoSuchElementException;

public class LinkedListStack<T> {

    private static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T d, Node<T> n){
            this.data = d;
            this.next = n;
        }
    }

    private int theSize = 0;
    private Node<T> startNode;

    public LinkedListStack(){
        doClear();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return theSize;
    }

    public T getTop(){
        if(startNode.next == null)
            throw new NoSuchElementException();
        Node<T> nNode = startNode.next;

        return nNode.data;
    }

    public void push(T x){
        Node<T> nNode = new Node<>(x,startNode.next);

        startNode.next = nNode;
        theSize++;
    }

    public T pop(){
        if(startNode.next == null)
            throw new NoSuchElementException();

        Node<T> nNode = startNode.next;
        startNode.next = nNode.next;

        nNode.next = null;
        theSize--;
        return nNode.data;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        startNode = new Node<>(null,null);

        theSize=0;
    }

}
