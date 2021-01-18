package site.laoc.practice.practice_3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 双端队列
 */
public class Practice_3_28<T> {

    class Node<T>{
        T t;
        Node<T> next;
        Node<T> prev;

        public Node(T t,Node<T> next, Node<T> prev){
            this.t = t;
            this.next = next;
            this.prev = prev;
        }
    }

    private int theSize;
    private Node<T> startNode;
    private Node<T> endNode;

    public Practice_3_28(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        startNode = new Node<>(null,null,null);
        endNode = new Node<>(null,null,startNode);

        startNode.next = endNode;
    }

    /**
     * 将x插入到双端队列的前端
     * @param x 被插入的元素
     */
    public void push(T x){
        Node<T> node = new Node<>(x,startNode.next,startNode);
        startNode.next.prev = node;
        startNode.next = node;
        theSize++;
    }

    /**
     * 从双端队列中删除前端项并将其返回
     * @return 返回删除的元素
     */
    public T pop(){
        if(startNode.next == endNode)
            throw new IndexOutOfBoundsException();

        Node<T> node = startNode.next;

        startNode.next.next.prev = startNode;
        startNode.next = startNode.next.next;

        theSize--;
        return node.t;
    }

    /**
     * 将x插入到双端队列的尾端
     * @param x 被插入的元素
     */
    public void inject(T x){
        Node<T> node = new Node<>(x,endNode,endNode.prev);

        endNode.prev.next = node;
        endNode.prev = node;
        theSize++;
    }

    /**
     * 从双端队列中删除尾端项并将其返回
     * @return
     */
    public T eject(){
        if(startNode.next == endNode)
            throw new IndexOutOfBoundsException();

        Node<T> node = endNode.prev;

        endNode.prev.prev.next = endNode;
        endNode.prev = endNode.prev.prev;

        theSize--;
        return node.t;
    }

    public static void main(String args[]){
//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addLast(3);
//        deque.addLast(4);
//
//        System.out.println(deque.pop());
//        System.out.println(deque.pop());
//        System.out.println(deque.pop());
//        System.out.println(deque.pop());

        Practice_3_28<Integer> practice_3_28 = new Practice_3_28<>();
        practice_3_28.push(1);
        practice_3_28.push(2);

        practice_3_28.inject(3);
        practice_3_28.inject(4);

        System.out.println(practice_3_28.pop());
        System.out.println(practice_3_28.pop());
        System.out.println(practice_3_28.pop());
        System.out.println(practice_3_28.pop());
    }
}
