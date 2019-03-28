package site.laoc.practice;

import java.util.LinkedList;

public class Practice_3_2_1<T> {

    private class Node<T>{
        T data;
        Node<T> next;

        public Node(T data,Node<T> n){
            this.data = data;
            this.next = n;
        }
    }

    private int theSize;
    private Node<T> first;

    public Practice_3_2_1(){
        doClear();
    }

    /**
     * 交换两个相邻的元素
     * @param index
     */
    public void exchangeBefore(int index){
        if(index == 0 || index >= size())
            throw new IndexOutOfBoundsException();

        Node<T> n = getNode(index);
        Node<T> p = getNode(index-1);
        Node<T> pp = getNode(index - 2);

        p.next = n.next;
        n.next = p;
        pp.next = n;
    }

    public int size(){
        return theSize;
    }

    public void add(T x){
        add(0,x);
    }

    public void add(int index,T x){
        addAfter(getNode(index-1),x);
    }

    public T get(int index){
        return getNode(index).data;
    }

    private Node<T> getNode(int index){
        if(index >= size())
            throw new IndexOutOfBoundsException();

        Node<T> p = first;

        for(int i = 0;i < index + 1;i++){
            p = p.next;
        }

        return p;
    }

    private void addAfter(Node<T> p, T x){
        Node<T> newNode = new Node<>(x,p.next);
        p.next = newNode;
        theSize++;
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        first = new Node<>(null,null);
    }

    public static void main(String [] args){
        Practice_3_2_1<Integer> p = new Practice_3_2_1<>();
        p.add(3);
        p.add(2);
        p.add(1);
        p.add(0);

        for(int i = 0;i < p.size();i++){
            System.out.print(p.get(i) + " ");
        }

        p.exchangeBefore(1);

        System.out.println();

        for(int i = 0;i < p.size();i++){
            System.out.print(p.get(i) + " ");
        }
    }
}
