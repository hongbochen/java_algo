package site.laoc.practice.practice_3;

import java.util.NoSuchElementException;

public class Practice_3_32<T> {

    class Node<T>{
        T x;
        Node<T> next;

        public Node(T x,Node<T> next){
            this.x = x;
            this.next = next;
        }
    }

    private int theSize;
    private Node<T> curNode;

    public Practice_3_32(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        curNode = null;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void push(T x){
        if(curNode == null){
            curNode = new Node<>(x,null);
        }else{
            Node<T> node = new Node<>(x,curNode);
            curNode = node;
        }

        theSize++;
    }

    public T pop(){
        if(size() == 0)
            throw new NoSuchElementException();

        Node<T> preNode = null;
        Node<T> node = curNode;

        while(node.next != null){
            preNode = node;
            node = node.next;
        }

        preNode.next = null;
        theSize--;
        return node.x;
    }

    public static void main(String[] args){
        Practice_3_32<Integer> practice_3_32 = new Practice_3_32<>();
        practice_3_32.push(1);
        practice_3_32.push(2);
        practice_3_32.push(3);
        practice_3_32.push(4);
        practice_3_32.push(5);

        System.out.println(practice_3_32.pop());
        System.out.println(practice_3_32.pop());
        System.out.println(practice_3_32.pop());
    }
}
